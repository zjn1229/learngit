package com.example.bookStore.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookStore.example.entiey.Book;
import com.example.bookStore.example.vo.BookShop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends BaseMapper<Book> {
    @Select("SELECT b.*, s.name as `shop_name`, s.image as `shop_image` " +
            "FROM book b, shop s " +
            "WHERE b.id = '${bookId}' AND b.shop_id = s.id " +
            "ORDER BY b.create_time DESC")
    BookShop listByBookId(@Param("bookId") String bookId);
}
