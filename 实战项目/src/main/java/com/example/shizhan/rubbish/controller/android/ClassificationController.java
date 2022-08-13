package com.example.shizhan.rubbish.controller.android;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.entity.Classification;
import com.example.shizhan.rubbish.entity.Employee;
import com.example.shizhan.rubbish.service.Interface.ClassificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
