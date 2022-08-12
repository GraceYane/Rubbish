package com.example.shizhan.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.reggie.entity.Driver;
import com.example.shizhan.reggie.mapper.DriverMapper;
import com.example.shizhan.reggie.service.Interface.DriverService;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements DriverService {
}
