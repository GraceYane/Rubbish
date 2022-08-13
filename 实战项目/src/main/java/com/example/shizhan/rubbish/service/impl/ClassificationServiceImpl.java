package com.example.shizhan.rubbish.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shizhan.rubbish.entity.Classification;
import com.example.shizhan.rubbish.mapper.ClassificationMapper;
import com.example.shizhan.rubbish.service.Interface.ClassificationService;
import org.springframework.stereotype.Service;

@Service
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper,Classification> implements ClassificationService {
}
