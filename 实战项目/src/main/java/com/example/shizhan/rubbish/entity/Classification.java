package com.example.shizhan.rubbish.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("rubbish_classification")
public class Classification implements Serializable {
    private Long id;
    private String name;
    @TableField(value = "")
    private String imageUrl;
    private Integer sortId;
    private String sort;
}
