package com.example.shizhan.rubbish.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author bz_c
 * @version 1.0
 */
@Data
public class orders implements Serializable {
    private BigInteger id;

    private String number;

    private int status;

    @TableField(exist = false)  // 设置数据库中没有的字段
    private String status_Str;

    private BigInteger userId;

    private String userName;

    private String orderTime;

    private String overTime;

    private String remark;

    private String driverPhone;

    private String userPhone;

    private String startAddress;

    private String destination;

    private String longitude;

    private String latitude;
}
