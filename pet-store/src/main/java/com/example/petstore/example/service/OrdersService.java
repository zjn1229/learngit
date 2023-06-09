package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.Orders;
//import com.example.petstore.example.vo.OrdersBookShop;

import java.util.List;

public interface OrdersService extends IService<Orders> {
    Boolean add(Orders orders);

    void deleteByIds(String ids);

    Boolean update(Orders orders);

//    List<OrdersBookShop> list(String bookName);
//
//    List<OrdersBookShop> listByAdminId();

    Boolean setStatus(String ids, int status, String reason);
}
