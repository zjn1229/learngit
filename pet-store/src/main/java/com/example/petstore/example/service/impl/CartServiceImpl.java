package com.example.petstore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petstore.common.utils.DateTool;
import com.example.petstore.common.utils.JwtUtil;
import com.example.petstore.example.entiey.Cart;
import com.example.petstore.example.mapper.CartMapper;
import com.example.petstore.example.service.CartService;
//import com.example.petstore.example.vo.CartBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Boolean add(Cart cart) {
        cart.setCreateTime(DateTool.getCurrTime());
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        cart.setUserId(userId);
        Cart cartExit = getByGoodsId(cart.getGoodsId());
        if (cartExit == null) {
            this.save(cart);
        } else {
            cartExit.setQuantity(cartExit.getQuantity() + 1);
            this.updateById(cartExit);
        }
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
    public Cart getByGoodsId(String goodsId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("goods_id", goodsId);
        return this.getOne(queryWrapper);
    }

    @Override
    public Boolean update(Cart cart) {
        this.updateById(cart);
        return true;
    }

//    @Override
//    public Page<CartBook> page(String name, Integer pageNum, Integer pageSize) {
//        Page<CartBook> page = new Page<>(pageNum, pageSize);
//        String token = request.getHeader("Authorization");
//        String userId = JwtUtil.validateToken(token);
//        return baseMapper.pageByBookName(page, userId, name);
//    }


//    @Override
//    public List<CartBook> listByBookName(String name) {
//        String token = request.getHeader("Authorization");
//        String userId = JwtUtil.validateToken(token);
//        return baseMapper.listByBookName(userId, name);
//    }


}