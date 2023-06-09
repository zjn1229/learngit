package com.example.bookStore.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookStore.example.entiey.OrdersItem;
import com.example.bookStore.example.vo.OrdersItemBookShop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrdersItemMapper extends BaseMapper<OrdersItem> {
    @Select("SELECT o.*, b.name as `bookName`, b.category as `category`, b.author, b.publisher, b.image as `bookImage`, b.shop_id, s.name as `shopName`,s.image as `shopImage` " +
            "FROM orders_item o, book b, shop s " +     //！！行末有空格，否则变成sWHERE
            "WHERE o.user_id = '${userId}' AND b.name LIKE '%${bookName}%' AND o.book_id = b.id AND b.shop_id = s.id " +
            "ORDER BY o.update_time DESC")
    List<OrdersItemBookShop> listByBookName(@Param("userId") String userId, @Param("bookName") String bookName);

    @Select("SELECT o.*, b.name as `bookName`, b.category as `category`, b.author, b.publisher, b.image as `bookImage`, b.shop_id, s.name as `shopName`,s.image as `shopImage` " +
            "FROM orders_item o, book b, shop s " +
            "WHERE s.administrator_id = '${adminId}' AND o.book_id = b.id AND b.shop_id = s.id " +
            "ORDER BY o.update_time DESC")
    List<OrdersItemBookShop> listByAdminId(@Param("adminId") String adminId);
}

