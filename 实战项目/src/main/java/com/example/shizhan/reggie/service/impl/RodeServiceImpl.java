package com.example.shizhan.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.reggie.entity.Rode;
import com.example.shizhan.reggie.mapper.RodeMapper;
import com.example.shizhan.reggie.service.Interface.RodeService;
import org.springframework.stereotype.Service;

@Service
public class RodeServiceImpl extends ServiceImpl<RodeMapper, Rode> implements RodeService {
}
