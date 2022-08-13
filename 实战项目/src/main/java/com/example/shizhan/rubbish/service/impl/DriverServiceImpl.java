package com.example.shizhan.rubbish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.rubbish.entity.Driver;
import com.example.shizhan.rubbish.mapper.DriverMapper;
import com.example.shizhan.rubbish.service.Interface.DriverService;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements DriverService {
}
