package com.example.shizhan.reggie.Test;

import com.example.shizhan.reggie.ShiZhanApplication;
import com.example.shizhan.reggie.config.TextCon;
import com.example.shizhan.reggie.entity.A;
import com.example.shizhan.reggie.entity.B;
import com.example.shizhan.reggie.entity.Employee;
import com.example.shizhan.reggie.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
public class test {
    @Autowired
    A a;
    @Autowired
     EmployeeMapper employeeMapper;
    public static void main(String[] args) {
//        SpringApplication.run(ShiZhanApplication.class, args);
//        Employee employee = new Employee();
//
//        employee.setName("哈哈哈哈哈");
//        System.out.println(employeeMapper);
//        employeeMapper.insert(employee);
        ApplicationContext context = new AnnotationConfigApplicationContext(TextCon.class);
        B b = context.getBean(B.class);
        A a = (A)context.getBean("a");
        System.out.println(a);
        System.out.println("展示b");
        b.show();
        B b1 = new B();
        b1.show();
    }
    @Test
    public void setA(){
        System.out.println(a);
    }

    @Test
    public void testSave(){
        Employee employee = new Employee();
        employee.setName("fafasfasdfsda");
        System.out.println(a);
        System.out.println(employeeMapper);
//        employeeMapper.insert(employee);
        List<Employee> list = employeeMapper.selectList(null);
        System.out.println(list);
    }
}
