package com.example.shizhan.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shizhan.reggie.entity.orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author bz_c
 * @version 1.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<orders> {
}
