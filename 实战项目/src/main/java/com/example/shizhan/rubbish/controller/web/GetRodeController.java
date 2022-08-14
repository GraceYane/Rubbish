package com.example.shizhan.rubbish.controller.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shizhan.rubbish.entity.Rode;
import com.example.shizhan.rubbish.mapper.EmployeeMapper;
import com.example.shizhan.rubbish.service.Interface.RodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
/*
chf的第一次提交
测试
@RestController = @Controller + @ResponseBody组成，等号右边两位同志简单介绍两句，就明白我们@RestController的意义了：
@Controller 将当前修饰的类注入SpringBoot IOC容器，使得从该类所在的项目跑起来的过程中，这个类就被实例化。当然也有语义化的作用，即代表该类是充当Controller的作用
@ResponseBody 它的作用简短截说就是指该类中所有的API接口返回的数据，甭管你对应的方法返回Map或是其他Object，它会以Json字符串的形式返回给客户端，
* */
@RequestMapping("/getLocation")
/*@RequestMapping来映射URL
    注解 @RequestMapping 可以用在类定义处和方法定义处。
        类定义处：规定初步的请求映射，相对于web应用的根目录；
        方法定义处：进一步细分请求映射，相对于类定义处的URL。*/
public class GetRodeController {

    @Autowired
    private Rode rode;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RodeService rodeService;
    @GetMapping("/XY")
    public double[][] getRode(BigInteger id) {
        // 根据页面提交的用户名查询数据库,queryWrapper里边放置查询条件和查询的id
        LambdaQueryWrapper<Rode> queryWrapper = new LambdaQueryWrapper<>();
        //设置查询条件， 采用Username等值查询
        queryWrapper.eq(Rode::getId,1);
        //根据查询条件进行查询，如果查到了返回对象给one，没查到的话 one=null
        Rode one = rodeService.getOne(queryWrapper);
        String rodes = one.getRode();

        String pattern = "\\d{2,}\\.\\d{2,}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(rodes);
        ArrayList<Double> arrX = new ArrayList<>();
        ArrayList<Double> arrY = new ArrayList<>();
        int II = -3;
        while (m.find( )) {
            if(II<0) {
                II ++;
                continue;
            }
            //System.out.println("Found value: " + m.group(0) );
            if(II%2==0){
                arrY.add(Double.parseDouble(m.group(0)));
            }else {
                arrX.add( Double.parseDouble(m.group(0)));
            }
            II++;
        }
       /* 测试代码
        for (int i = 0; i < arrX.size(); i++) {
            System.out.println(arrX.get(i)+"  "+arrY.get(i));
        }
        System.out.println(rodes);*/
        double[][] arr_xy = new double[arrX.size()][2];
        for (int i = 0; i < arrX.size(); i++) {
            arr_xy[i][0] = arrX.get(i);
            arr_xy[i][1] = arrY.get(i);

        }
        return arr_xy;
}

}
