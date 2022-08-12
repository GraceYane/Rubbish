package com.example.shizhan.rubbish.controller.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.entity.orders;
import org.apache.commons.lang.StringUtils;
import com.example.shizhan.rubbish.service.Interface.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;


/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private orderService os;

    @GetMapping("/page")
    public R<Page> getAll(
            int page, int pageSize,
            String number, String status, String beginTime, String endTime) {
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<orders> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        /**
         * 对订单号的过滤、模糊查询
         */
        queryWrapper.like(StringUtils.isNotEmpty(number), orders::getNumber, number);
        /**
         * 对订单状态的过滤
         */
        // System.out.println(status);
        /*Integer id_status = null;
        if(status != null) {
            if(status.equals("待付款")) {
                id_status = 1;
            }else if(status.equals("正在派送")) {
                id_status = 2;
            }else if(status.equals("已派送")) {
                id_status = 3;
            }else if(status.equals("已完成")) {
                id_status = 4;
            }else {
                id_status = 5;
            }
        }*/
        queryWrapper.eq(StringUtils.isNotEmpty(status), orders::getStatus, status);
        // 关于日期
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /**
         * 对订单日期的过滤
         */
        if(beginTime != null) {
            queryWrapper.ge(orders::getOrderTime, beginTime);
        }
        if(endTime != null) {
            queryWrapper.le(orders::getOverTime, endTime);
        }

        //添加排序条件
        // queryWrapper.orderByDesc(Employee::getUpdateTime);

        // 执行查询
        os.page(pageInfo, queryWrapper);
        // 返回
        return R.success(pageInfo);
    }

    /**
     * 完成运输状态的更改
     */
    @PutMapping
    public R<String> changeOrderState(@RequestBody Map<String, Object> json) {

        // 得到数据
        orders o = new orders();
        o.setStatus((Integer) json.get("status"));
        String id = json.get("id").toString();
        o.setId(BigInteger.valueOf(Integer.parseInt(id)));
        // 调用service修改数据
        os.updateById(o);
        // 返回对应的响应
        return R.success("");
    }
}