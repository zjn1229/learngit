package com.example.bookStore.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bookStore.example.entiey.Cart;
import com.example.bookStore.example.vo.CartBook;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper extends BaseMapper<Cart> {
    //列名要用`括起来
    @Select("SELECT c.*, b.name as `name`, b.author as `author`, b.price as `price`, b.image as `image`" +
            "FROM cart c, book b " +    //！！行末有空格，否则变成sWHERE
            "WHERE c.user_id = '${userId}' AND c.book_id = b.id AND b.name LIKE '%${bookName}%' " +
            "ORDER BY c.create_time DESC")
    List<CartBook> listByBookName(@Param("userId") String userId, @Param("bookName") String bookName);

    @Select("SELECT c.*, b.name as `name`, b.author as `author`, b.price as `price`, b.image as `image`" +
            "FROM cart c, book b " +
            "WHERE c.user_id = '${userId}' AND c.book_id = b.id AND b.name LIKE '%${bookName}%' " +
            "ORDER BY c.create_time DESC")
    Page<CartBook> pageByBookName(@Param("page") Page<CartBook> page, @Param("userId") String userId, @Param("bookName") String bookName);
}

