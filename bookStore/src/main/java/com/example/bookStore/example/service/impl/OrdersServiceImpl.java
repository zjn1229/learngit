package com.example.bookStore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookStore.common.utils.DateTool;
import com.example.bookStore.common.utils.JwtUtil;
import com.example.bookStore.example.entiey.Orders;
import com.example.bookStore.example.mapper.OrdersMapper;
import com.example.bookStore.example.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Boolean add(Orders orders) {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        orders.setUserId(userId);
        orders.setCreateTime(DateTool.getCurrTime());
        this.save(orders);
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
    public Boolean update(Orders orders) {
        this.updateById(orders);
        return true;
    }

    @Override
    public List<Orders> list(String userId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        if (userId != null) {
            queryWrapper.eq("user_id", userId);
        }
        return this.list(queryWrapper);
    }

}
