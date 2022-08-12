package com.example.shizhan.reggie.controller.android;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shizhan.reggie.common.R;
import com.example.shizhan.reggie.entity.orders;
import org.apache.commons.lang.StringUtils;
import com.example.shizhan.reggie.service.Interface.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Map;


/**
 * 安卓用户端，下单
 */
@Slf4j
@RestController
@RequestMapping("/submitOrder")
public class SubmitOrderController {
    @Autowired
    private orderService os;
    @PostMapping
    public R<String> submitOrder(@RequestBody orders order){

        order.setId(BigInteger.valueOf((long) (Math.random()*100)));
        order.setNumber(String.valueOf(((long) (Math.random()*100))));
        order.setStatus(1);
        order.setUserId(BigInteger.valueOf((long) (Math.random()*100)));
//        order.setUserName("yyyyyyyyy");kkkk
//        order.setDriverPhone("123");
        order.setUserPhone("123423");
//        order.setStartAddress("中国");
        order.setOrderTime(LocalDateTime.now().toString());
        order.setOverTime(LocalDateTime.now().toString());
        os.save(order);
        return null;
    }
}
