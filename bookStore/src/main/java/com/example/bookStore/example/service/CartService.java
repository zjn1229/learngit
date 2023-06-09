package com.example.bookStore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.example.entiey.Cart;
import com.example.bookStore.example.vo.CartBook;

import java.util.List;

public interface CartService extends IService<Cart> {
    Boolean add(Cart cart);

    void deleteByIds(String ids);

    Cart getByBookId(String bookId);

    Boolean update(Cart cart);

    Page<CartBook> page(String bookName, Integer pageNum, Integer pageSize);

    void updateUsefulByIds(String ids, Boolean useful);

    List<CartBook> listByBookName(String name);
}
