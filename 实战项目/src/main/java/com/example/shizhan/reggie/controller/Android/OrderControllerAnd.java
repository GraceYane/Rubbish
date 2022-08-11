package com.example.shizhan.reggie.controller.Android;

import com.example.shizhan.reggie.common.R;
import com.example.shizhan.reggie.entity.orders;
import com.example.shizhan.reggie.service.Interface.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<orders> orders = os.list();  // 拿到所有的数据
        for (com.example.shizhan.reggie.entity.orders order : orders) {
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

        return R.success(os);
    }
}
