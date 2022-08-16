package com.example.shizhan.rubbish.controller.android;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.entity.Employee;
import com.example.shizhan.rubbish.entity.User;
import com.example.shizhan.rubbish.mapper.UserMapper;
import com.example.shizhan.rubbish.service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/userAndroid")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public R<User> login(HttpServletRequest request,@RequestBody User user){

        String password = user.getPassword();

        // 根据页面提交的用户名查询数据库,queryWrapper里边放置查询条件和查询的id
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //设置查询条件， 采用Username等值查询
        queryWrapper.eq(User::getUsername,user.getUsername());
        //根据查询条件进行查询，如果查到了返回对象给one，没查到的话 one=null
        User one = userService.getOne(queryWrapper);
        if(one==null){
            return R.error("登录失败");
        }
        if(!one.getPassword().equals((password))){
            return R.error("登录失败");
        }
        if(one.getStatus()==0){
            return R.error("账号禁用");
        }
        request.getSession().setAttribute("userName",one.getUsername());
        return R.success(one);
    }
    @PostMapping("/register")
    public R<User> register(HttpServletRequest request,@RequestBody User user){
        //通过post请求访问这个路径，参数data是一个json形式，需要带上注解
        user.setId(BigInteger.valueOf((long) (Math.random()*100)));
        userService.save(user);
        // 使用账号作为唯一身份识别
        request.getSession().setAttribute("userName",user.getUsername());
        return R.success(user);
    }

}
