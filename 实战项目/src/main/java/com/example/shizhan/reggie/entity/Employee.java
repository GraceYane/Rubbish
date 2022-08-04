package com.example.shizhan.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工实体类
 */

@Data  //@Data注解主要是帮助解决Setter 和 Getter以及 toString这种重复的无脑工作,需要导入 lombok依赖
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;  //身份证号码

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)   // 表示插入时起作用
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)  // 表示插入和修改时起作用
    private Long updateUser;

}
