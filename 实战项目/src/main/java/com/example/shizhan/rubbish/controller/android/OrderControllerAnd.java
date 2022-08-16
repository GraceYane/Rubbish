
package com.example.shizhan.rubbish.controller.android;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.entity.orders;
import com.example.shizhan.rubbish.service.Interface.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author bz_c
 * @version 1.0
 */
// http://localhost:8080/android/user/order/order.html
@Slf4j
@RestController
@RequestMapping("/orderAndroid")
public class OrderControllerAnd {

    @Autowired
    private orderService os;

    @GetMapping("/map1")
    public R<List<orders>> getAll1(HttpServletRequest request) {
        LambdaQueryWrapper<orders> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.ne(orders::getStatus, 4);  // 不等于4的数据
        queryWrapper.ne(orders::getStatus, 3);
        // 对于用户来说只需要拿到自己所在的下单的数据
        // 设置对应的条件即可
        String account = (String) request.getSession().getAttribute("userName");
        queryWrapper.eq(orders::getAccount, account);

        // 拿到所有的数据
        List<orders> orders = os.list(queryWrapper);  // 拿到所有的数据

        for (com.example.shizhan.rubbish.entity.orders order : orders) {
            if(order.getStatus() == 1) {
                order.setStatus_Str("待运输");
            }else if(order.getStatus() == 2) {
                order.setStatus_Str("运输中");
            }else if(order.getStatus() == 3) {
                order.setStatus_Str("已完成");
            }else if(order.getStatus() == 4) {
                order.setStatus_Str("已取消");
            }
        }

       /* ArrayList<HashMap<Object, String>> arrayList = new ArrayList<>();

        HashMap<Object, String> map = new HashMap<>();
        map.put("userName", "河南工业大学站");
        map.put("status", "正在运输");

        HashMap<Object, String> map1 = new HashMap<>();
        map1.put("userName", "郑州大学站");
        map1.put("status", "已经完成");

        arrayList.add(map);
        arrayList.add(map1);*/

        return R.success(orders);
    }

    @GetMapping("/map2")
    public R<List<orders>> getAll2(HttpServletRequest request) {
        LambdaQueryWrapper<orders> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(orders::getStatus, 1);  // 不等于4的数据

        // 对数据进行查找的时候需要判断每个司机只能拿到自己的订单
        String userName = (String) request.getSession().getAttribute("username");
        // 即等于1和属于自己的订单的
        queryWrapper.or(
                wrapper -> wrapper.eq(orders::getStatus, 2)
                        .eq(orders::getAccount1, userName));

        // 拿到所有的数据
        List<orders> orders = os.list(queryWrapper);  // 拿到所有的数据

        for (com.example.shizhan.rubbish.entity.orders order : orders) {
            if(order.getStatus() == 1) {
                order.setStatus_Str("待运输");
            }else if(order.getStatus() == 2) {
                order.setStatus_Str("运输中");
            }else if(order.getStatus() == 3) {
                order.setStatus_Str("已完成");
            }else if(order.getStatus() == 4) {
                order.setStatus_Str("已取消");
            }
        }

       /* ArrayList<HashMap<Object, String>> arrayList = new ArrayList<>();

        HashMap<Object, String> map = new HashMap<>();
        map.put("userName", "河南工业大学站");
        map.put("status", "正在运输");

        HashMap<Object, String> map1 = new HashMap<>();
        map1.put("userName", "郑州大学站");
        map1.put("status", "已经完成");

        arrayList.add(map);
        arrayList.add(map1);*/

        return R.success(orders);
    }

    @GetMapping("/getById")
    public R<orders> getById(int id) {
        orders os = this.os.getById(id);

        if(os.getStatus() == 1) {
            os.setStatus_Str("待运输");
            // 对日期进行赋空值
            os.setOverTime(null);
        }else if(os.getStatus() == 2) {
            os.setStatus_Str("运输中");
            os.setOverTime(null);
        }else if(os.getStatus() == 3) {
            os.setStatus_Str("已完成");
        }else if(os.getStatus() == 4) {
            os.setStatus_Str("已取消");
        }

        return R.success(os);
    }

    @GetMapping("/cancelOrder")
    public R<Boolean> cancelOrder(int id) {
        orders o = new orders();
        o.setId(BigInteger.valueOf(id));
        o.setStatus(4);
        os.updateById(o);   // 更新该状态

        return R.success(true);
    }

    @GetMapping("changeState")
    public R<Boolean> changeState(HttpServletRequest request, int id) {
        // 先拿到数据库中的id
        R<orders> ordersR = getById(id);
        int status = ordersR.getData().getStatus();
        // 让状态自增
        orders o = new orders();
        o.setId(BigInteger.valueOf(id));
        o.setStatus(status + 1);
        // 如果当前订单从待运输转换为正在运输的话则其它司机则看不到该订单
        // 需要对当前订单进行特定的账号的设置的处理
        String userName = (String) request.getSession().getAttribute("userName");
        o.setAccount1(userName);
        // 如果当前订单已经完成的话则需要设置订单完成的时间
        if(status + 1 == 3) {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNowStr = sdf.format(d);
            o.setOverTime(dateNowStr);
        }
        // 进行数据的更新
        os.updateById(o);
        return R.success(true);
    }
}
