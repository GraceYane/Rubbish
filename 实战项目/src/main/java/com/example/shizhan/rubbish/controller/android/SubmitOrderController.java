package com.example.shizhan.rubbish.controller.android;

import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.entity.orders;
import com.example.shizhan.rubbish.service.Interface.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDateTime;


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
