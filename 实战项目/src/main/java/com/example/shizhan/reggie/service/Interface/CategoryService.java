package com.example.shizhan.reggie.service.Interface;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shizhan.reggie.entity.Category;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);

}
