package com.example.shizhan.rubbish.controller.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shizhan.rubbish.entity.Driver;
import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.mapper.DriverMapper;
import com.example.shizhan.rubbish.service.Interface.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R<String> save(@RequestBody Driver driver){

        System.out.println(driver);
        driverService.save(driver);
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
}


