package com.example.bookStore.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.example.entiey.Orders;

import java.util.List;

public interface OrdersService extends IService<Orders> {
    Boolean add(Orders orders);

    void deleteByIds(String ids);

    Boolean update(Orders orders);

    List<Orders> list(String userId);

}
