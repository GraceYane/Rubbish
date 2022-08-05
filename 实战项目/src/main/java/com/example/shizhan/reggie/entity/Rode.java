package com.example.shizhan.reggie.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 经纬度实体类
 */
@Component
@Data  //@Data注解主要是帮助解决Setter 和 Getter以及 toString这种重复的无脑工作,需要导入 lombok依赖
public class Rode implements Serializable {
    private BigInteger id;
    private String rode;

}
