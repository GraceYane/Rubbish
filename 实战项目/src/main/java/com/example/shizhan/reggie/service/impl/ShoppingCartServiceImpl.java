package com.example.shizhan.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.reggie.entity.ShoppingCart;
import com.example.shizhan.reggie.mapper.ShoppingCartMapper;
import com.example.shizhan.reggie.service.Interface.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
