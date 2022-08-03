package com.example.shizhan.reggie.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shizhan.reggie.common.R;
import com.example.shizhan.reggie.entity.A;
import com.example.shizhan.reggie.entity.Employee;
import com.example.shizhan.reggie.mapper.EmployeeMapper;
import com.example.shizhan.reggie.service.Interface.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
/*
chf的第一次提交
@RestController = @Controller + @ResponseBody组成，等号右边两位同志简单介绍两句，就明白我们@RestController的意义了：
@Controller 将当前修饰的类注入SpringBoot IOC容器，使得从该类所在的项目跑起来的过程中，这个类就被实例化。当然也有语义化的作用，即代表该类是充当Controller的作用
@ResponseBody 它的作用简短截说就是指该类中所有的API接口返回的数据，甭管你对应的方法返回Map或是其他Object，它会以Json字符串的形式返回给客户端，
* */
@RequestMapping("/employee")
/*@RequestMapping来映射URL
    注解 @RequestMapping 可以用在类定义处和方法定义处。
        类定义处：规定初步的请求映射，相对于web应用的根目录；
        方法定义处：进一步细分请求映射，相对于类定义处的URL。*/
public class EmployeeController {

    //@Resource
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private A a;


    @PostMapping("/login")                         //@RequestBody，接受json格式的数据
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){
        // 1、拿到前端传递过来的数据，拿到密码，对密码进行加密
        String password = employee.getPassword();
          password = DigestUtils.md5DigestAsHex(password.getBytes());

          List<Employee> list = employeeMapper.selectList(null);
          log.info("查询结果：");
          log.info(String.valueOf(a.x));
//        for (int i = 0; i < list.size(); i++) {
//            log.info(list.get(i).getUsername());
//        }
          log.info(employeeService.toString());


          // 根据页面提交的用户名查询数据库,queryWrapper里边放置查询条件和查询的id
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //设置查询条件， 采用Username等值查询
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        //根据查询条件进行查询，如果查到了返回对象给one，没查到的话 one=null
        Employee one = employeeService.getOne(queryWrapper);

        if(one==null){
            return R.error("登录失败");
        }
        if(!one.getPassword().equals((password))){
            return R.error("登录失败");
        }
        if(one.getStatus()==0){
            return R.error("账号禁用");
        }
        request.getSession().setAttribute("employee",one.getId());
        return R.success(one);
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    //@GetMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        //通过post请求访问这个路径，参数data是一个json形式，需要带上注解
        log.info("验证有没有密码有没有设置");
        log.info("新增员工信息："+employee.toString());
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Long empId = (Long)request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        //employee.setPassword("1234");
        employee.setUpdateUser(empId);
        employee.setCreateUser(empId);
        //用户访问这个地址，调用这个方法，然后直接调用保存方法，将以对象作为整体传入数据库
        employeeService.save(employee);
        return R.success("新增成功");
    }
    @GetMapping
    public void x(){
        log.info("hhhhhhh");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //Page是mybatis封装的一个类
        log.info("page = {},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);//查第page页，每页查pageSize条数据
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        if(name!=null){   //根据name进行查找
            queryWrapper.like(Employee::getUsername,name);
        }
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工的信息
     * @param request
     * @param employee
     * @return
     */
     @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info("employee:{}",employee);

        //获得当前用户的id
         Long empId = (Long)request.getSession().getAttribute("employee");
         employee.setUpdateUser(empId);
         employee.setUpdateTime(LocalDateTime.now());

         employeeService.updateById(employee);
         return R.success("修改成功");
     }
}
