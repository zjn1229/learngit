package com.example.bookStore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookStore.common.utils.DateTool;
import com.example.bookStore.example.entiey.Book;
import com.example.bookStore.example.mapper.BookMapper;
import com.example.bookStore.example.service.BookService;
import com.example.bookStore.example.vo.BookShop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public Boolean add(Book book) {
        book.setUseful(true);
        book.setCreateTime(DateTool.getCurrTime());
        this.save(book);
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }

    @Override
    public Boolean update(Book book) {
        this.updateById(book);
        return true;
    }

    @Override
    public List<Book> list(String name, Double priceMin, Double priceMax) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (priceMin != null) {
            queryWrapper.ge("price", priceMin);
        }
        if (priceMax != null) {
            queryWrapper.lt("price", priceMax);
        }
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }

    @Override
    public List<BookShop> listByIds(String ids) {
        List<BookShop> list = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            list.add(baseMapper.listByBookId(id));
        }
        return list;
    }

    @Override
    public Page<Book> page(String name, Integer pageNum, Integer pageSize) {
        Page<Book> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("name", name);
            return this.page(page, queryWrapper);
        } else {
            return this.page(page);
        }
    }

    @Override
    public void updateUsefulByIds(String ids, Boolean useful) {
        String[] aryId = ids.split(",");
        for (String id : aryId) {
            Book book = this.getById(id);
            if (book != null) {
                book.setUseful(useful);
                this.updateById(book);
            }
        }

    }

    @Override
    public Boolean updateStockByIds(String bookId, int quantity) {
        Book book = this.getById(bookId);
        if (book.getStock() >= quantity) {
            book.setStock(book.getStock() - quantity);
            this.updateById(book);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Book> listByShopId(String shopId, String name, Double priceMin, Double priceMax) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id", shopId);
        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (priceMin != null) {
            queryWrapper.ge("price", priceMin);
        }
        if (priceMax != null) {
            queryWrapper.lt("price", priceMax);
        }
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }


}
