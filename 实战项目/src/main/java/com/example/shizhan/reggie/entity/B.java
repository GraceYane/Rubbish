package com.example.shizhan.reggie.entity;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("b")
public class B {
    @Autowired
     private A a;
    public void show(){
        log.info(a.toString());
    }
}
