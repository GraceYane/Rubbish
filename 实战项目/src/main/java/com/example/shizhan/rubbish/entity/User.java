package com.example.shizhan.rubbish.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class User {
    private BigInteger id;
    private String name;
    private String phone;
    private String username;
    private String password;
    private String address;
    private String avatar;
    private int status;
    private String sex;
    private String idNumber;
    private int integral;

}
