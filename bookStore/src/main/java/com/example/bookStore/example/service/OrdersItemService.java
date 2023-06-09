package com.example.bookStore.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.example.entiey.OrdersItem;
import com.example.bookStore.example.vo.OrdersItemBookShop;

import java.util.List;

public interface OrdersItemService extends IService<OrdersItem> {
    Boolean add(OrdersItem ordersItem);

    void deleteByIds(String ids);

    Boolean update(OrdersItem ordersItem);

    List<OrdersItemBookShop> list(String bookName);

    List<OrdersItemBookShop> listByAdminId();

    Boolean setStatus(String ids, int status, String reason);
}
