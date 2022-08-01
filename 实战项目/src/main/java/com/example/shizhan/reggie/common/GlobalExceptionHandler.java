package com.example.shizhan.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局处理异常
 */
@ControllerAdvice( annotations = {RestController.class,Controller.class})
@ResponseBody  //这个注解表示方法返回json数据
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法,处理SQLIntegrityConstraintViolationException这种类型的异常
     * 存在id重复
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
     public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
         log.error(ex.getMessage());
         if(ex.getMessage().contains("Duplicate entry")){//"Duplicate entry"，这个报错多半是违反了键的唯一约束
             //Duplicate entry '123' for key 'idx_username'。“123”用户重复
             String[] split = ex.getMessage().split(" ");//通过空格将字符串分成数据
             String msg = split[2] + "已存在";
             return R.error(msg);
         }
         return R.error("未知错误!");
     }


}
