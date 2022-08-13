package com.example.shizhan.rubbish.controller.Android;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shizhan.rubbish.common.R;
import com.example.shizhan.rubbish.entity.orders;
import com.example.shizhan.rubbish.service.Interface.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
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

    @GetMapping("/map")
    public R<List<orders>> getAll() {
        LambdaQueryWrapper<orders> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.ne(orders::getStatus, 4);  // 不等于4的数据
        queryWrapper.ne(orders::getStatus, 3);

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
        }else if(os.getStatus() == 2) {
            os.setStatus_Str("运输中");
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
    public R<Boolean> changeState(int id) {
        // 先拿到数据库中的id
        R<orders> ordersR = getById(id);
        int status = ordersR.getData().getStatus();
        // 让状态自增
        orders o = new orders();
        o.setId(BigInteger.valueOf(id));
        o.setStatus(status + 1);
        // 进行数据的更新
        os.updateById(o);
        return R.success(true);
    }
}
