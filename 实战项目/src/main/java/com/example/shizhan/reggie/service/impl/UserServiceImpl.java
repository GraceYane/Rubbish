package com.example.shizhan.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.reggie.entity.User;
import com.example.shizhan.reggie.mapper.UserMapper;
import com.example.shizhan.reggie.service.Interface.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
