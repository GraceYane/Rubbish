package com.example.shizhan.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shizhan.reggie.common.R;
import com.example.shizhan.reggie.entity.orders;
import org.apache.commons.lang.StringUtils;
import com.example.shizhan.reggie.service.Interface.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        Integer id_status = null;
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
        }
        queryWrapper.like(StringUtils.isNotEmpty(status), orders::getStatus, id_status);
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
}