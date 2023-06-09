package com.example.bookStore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.example.entiey.Book;
import com.example.bookStore.example.vo.BookShop;

import java.util.List;

public interface BookService extends IService<Book> {
    Boolean add(Book book);

    void deleteByIds(String ids);

    Boolean update(Book book);

    List<Book> list(String name, Double priceMin, Double priceMax);

    List<BookShop> listByIds(String ids);

    Page<Book> page(String name, Integer pageNum, Integer pageSize);

    void updateUsefulByIds(String ids, Boolean useful);

    Boolean updateStockByIds(String bookId, int quantity);

    List<Book> listByShopId(String shopId, String name, Double priceMin, Double priceMax);

}
