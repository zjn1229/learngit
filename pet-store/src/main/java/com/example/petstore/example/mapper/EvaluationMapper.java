package com.example.petstore.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.petstore.example.entiey.Evaluation;
//import com.example.petstore.example.vo.EvaluationBookShop;
//import com.example.petstore.example.vo.EvaluationUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EvaluationMapper extends BaseMapper<Evaluation> {
//    @Select("SELECT e.*, b.id as `bookId`, b.name as `bookName`, b.category as `category`, b.author, b.publisher, " +
//            "b.image as `bookImage`, s.id as `shopId`, s.name as `shopName`,s.image as `shopImage`, " +
//            "o.price, o.quantity, o.total_price " +
//            "FROM evaluation e, book b, shop s , orders_item o " +
//            "WHERE e.user_id = '${userId}' AND e.orders_item_id = o.id AND o.book_id = b.id AND b.shop_id = s.id " +
//            "ORDER BY e.create_time DESC ")
//    List<EvaluationBookShop> listByUserId(@Param("userId") String userId);
//
//    @Select("SELECT e.*, u.nickname as `userNickname`, u.image as `userImage` " +
//            "FROM evaluation e, user u, orders_item o " +
//            "WHERE e.user_id = u.id AND e.orders_item_id = o.id AND o.book_id = '${bookId}' " +
//            "ORDER BY e.create_time ")
//    Page<EvaluationUser> pageByBookId(@Param("page") Page<EvaluationUser> page, @Param("bookId") String bookId);
}
