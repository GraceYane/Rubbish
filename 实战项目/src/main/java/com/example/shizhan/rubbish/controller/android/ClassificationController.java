package com.example.shizhan.rubbish.controller.android;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.entity.Classification;
import com.example.shizhan.rubbish.entity.ClassifyData;
import com.example.shizhan.rubbish.service.Interface.ClassificationService;
import com.example.shizhan.rubbish.utils.Base64Util;
import com.example.shizhan.rubbish.utils.GsonUtils;
import com.example.shizhan.rubbish.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/classification")
public class ClassificationController {

    @Autowired
    private ClassificationService service;

    @GetMapping
    public R<Page> selectPage(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "") Integer sortId,
                              @RequestParam(defaultValue = "") String name){
        //Page是mybatis封装的一个类
        int pageSize=20;
        log.info("page = {},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);//查第page页，每页查pageSize条数据
        //构造条件构造器
        LambdaQueryWrapper<Classification> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.eq(null!=sortId, Classification::getSortId,sortId)
                    .like(Classification::getName,name);
        //添加排序条件
        queryWrapper.orderByAsc(Classification::getId);
        //执行查询
        service.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public R<ClassifyData> classify(@RequestParam MultipartFile file){
        String Result=null;

        //1.获取access_token
        String access_token = null;
        String getAccessTokenUrl = "https://aip.baidubce.com/oauth/2.0/token?"
                // grant_type为固定参数
                + "grant_type=client_credentials"
                + "&client_id=" + "5zGAVtf1TbkVWyQ9ByBY7uzT"
                + "&client_secret=" + "B6BLP4pFfrYptMAaYwFGRFQsUSksKj9C";
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = JSON.parseObject(result);
            access_token = jsonObject.getString("access_token");
            System.out.println(access_token);
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }

        //2.使用token进行请求
        // 请求url
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/wyxaa";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", Base64Util.encode(file.getBytes()));
            map.put("top_num", "1");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = access_token;

            Result =  HttpUtil.post(url, accessToken, "application/json", param);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Result="{\"log_id\":2216463407376389603,\"results\":[{\"name\":\"2_塑料袋_干垃圾\",\"score\":0.47071129083633423}]}";

        System.out.println(Result);
        //Map<String,String> mp=new HashMap<>();
        //mp.put("")
        String[] split = Result.split(",");

        //封装数据
        ClassifyData da=new ClassifyData();
        String r=StringUtils.substringBetween(split[2],":","}");
        da.setName(StringUtils.substringBetween(split[1],"_","_"));
        da.setSort(StringUtils.substringBetween(split[1],da.getName()+"_","\""));
        Double d=(Double.parseDouble(r.substring(0,8))*100);
        da.setRate(d.toString().substring(0,7)+"%");

        return R.success(da);

    }
}
