package com.example.shizhan.reggie.dto;

import com.example.shizhan.reggie.entity.Setmeal;
import com.example.shizhan.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
