package com.example.shizhan.rubbish.controller.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shizhan.rubbish.entity.Driver;
import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.entity.User;
import com.example.shizhan.rubbish.mapper.DriverMapper;
import com.example.shizhan.rubbish.service.Interface.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/driver")
public class DriverController {



    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private DriverService dc;

    /**
     * 新增司机
     * @param driver
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Driver driver){
        driver.setId(((long) (Math.random()*100)));
        driver.setUpdateTime(LocalDateTime.now().toString());
        System.out.println(driver);
        driverService.save(driver);
        request.getSession().setAttribute("username",driver.getUsername());
        return null;
    }

    /**
     * 司机分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> fanye(int page,int pageSize,String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);
        Page pageInfo = new Page(page,pageSize);

        //构建条件构造器
        LambdaQueryWrapper<Driver> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Driver::getName,name);


        //排序条件
        queryWrapper.orderByDesc(Driver::getId);


        //执行查询
        driverService.page(pageInfo,queryWrapper);


        return R.success(pageInfo);
    }

    /**
     * 司机登录by yy
     */
    @PostMapping("/login")
    public R<Driver> login(HttpServletRequest request,@RequestBody Driver driver){
        String password = driver.getPassword();

        // 根据页面提交的用户名查询数据库,queryWrapper里边放置查询条件和查询的id
        LambdaQueryWrapper<Driver> queryWrapper = new LambdaQueryWrapper<>();
        //设置查询条件， 采用Username等值查询
        queryWrapper.eq(Driver::getUsername,driver.getUsername());
        //根据查询条件进行查询，如果查到了返回对象给one，没查到的话 one=null
        Driver one = null;
        one = driverService.getOne(queryWrapper);
        if(one==null){
            return R.error("登录失败");
        }
        if(!one.getPassword().equals((password))){
            return R.error("登录失败");
        }
        if(one.getStatus()==0){
            return R.error("账号禁用");
        }
        request.getSession().setAttribute("username",one.getUsername());
        return R.success(one);
    }
}


