package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.Cart;
//import com.example.petstore.example.vo.CartBook;

import java.util.List;

public interface CartService extends IService<Cart> {
    Boolean add(Cart cart);

    void deleteByIds(String ids);

    Cart getByGoodsId(String goodsId);

    Boolean update(Cart cart);

//    Page<CartBook> page(String bookName, Integer pageNum, Integer pageSize);
//
//    List<CartBook> listByBookName(String name);
}
