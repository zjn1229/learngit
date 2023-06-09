package com.example.bookStore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.example.entiey.Shop;

import java.util.List;

public interface ShopService extends IService<Shop> {
    Boolean add(Shop shop);

    void deleteByIds(String ids);

    Boolean update(Shop shop);

    List<Shop> list(String name);

    Page<Shop> page(String adminId, String name, Integer pageNum, Integer pageSize);

    void updateUsefulByIds(String ids, Boolean useful);
}
