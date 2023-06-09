package com.example.petstore.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petstore.example.entiey.PetProduct;
//import com.example.petstore.example.vo.BookShop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PetProductMapper extends BaseMapper<PetProduct> {
//    @Select("SELECT b.*, s.name as `shop_name`, s.image as `shop_image` " +
//            "FROM book b, shop s " +
//            "WHERE b.id = '${bookId}' AND b.shop_id = s.id " +
//            "ORDER BY b.create_time DESC")
//    BookShop listByBookId(@Param("bookId") String bookId);
}
