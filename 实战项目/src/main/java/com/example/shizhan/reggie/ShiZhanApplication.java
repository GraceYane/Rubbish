package com.example.shizhan.reggie;

import com.example.shizhan.reggie.entity.A;
import com.example.shizhan.reggie.entity.B;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j // 1、省略类的get set方法 2、加上注解之后可以直接使用log这个变量
@SpringBootApplication
public class ShiZhanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiZhanApplication.class, args);
        log.info("项目启动成功");//输出日志
        log.info("自动装配成功");
    }


}
