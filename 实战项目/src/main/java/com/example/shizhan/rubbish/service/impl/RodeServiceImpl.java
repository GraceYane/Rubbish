package com.example.shizhan.rubbish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.rubbish.entity.Rode;
import com.example.shizhan.rubbish.mapper.RodeMapper;
import com.example.shizhan.rubbish.service.Interface.RodeService;
import org.springframework.stereotype.Service;

@Service
public class RodeServiceImpl extends ServiceImpl<RodeMapper, Rode> implements RodeService {
}
