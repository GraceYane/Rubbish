package com.example.shizhan.rubbish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.rubbish.entity.orders;
import com.example.shizhan.rubbish.mapper.OrderMapper;
import com.example.shizhan.rubbish.service.Interface.orderService;
import org.springframework.stereotype.Service;

/**
 * @author bz_c
 * @version 1.0
 */
@Service
public class orderServiceImpl extends ServiceImpl<OrderMapper, orders> implements orderService {
}
