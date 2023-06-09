package com.example.bookStore.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookStore.common.utils.DateTool;
import com.example.bookStore.common.utils.JwtUtil;
import com.example.bookStore.common.utils.SendMessageUtil;
import com.example.bookStore.example.entiey.OrdersItem;
import com.example.bookStore.example.mapper.OrdersItemMapper;
import com.example.bookStore.example.service.OrdersItemService;
import com.example.bookStore.example.vo.OrdersItemBookShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersItemServiceImpl extends ServiceImpl<OrdersItemMapper, OrdersItem> implements OrdersItemService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SendMessageUtil sendMessageUtil;

    @Override
    public Boolean add(OrdersItem ordersItem) {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        ordersItem.setUserId(userId);
        ordersItem.setCreateTime(DateTool.getCurrTime());
        ordersItem.setUpdateTime(DateTool.getCurrTime());
        this.save(ordersItem);
        //加入消息队列
        Map<String, Object> map = new HashMap<>();
        map.put("ordersItemId", ordersItem.getId());
        sendMessageUtil.sendDirectMessage(map);
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
    public Boolean update(OrdersItem ordersItem) {
        ordersItem.setUpdateTime(DateTool.getCurrTime());
        this.updateById(ordersItem);
        return true;
    }

    @Override
    public List<OrdersItemBookShop> list(String bookName) {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        return baseMapper.listByBookName(userId, bookName);
    }

    @Override
    public List<OrdersItemBookShop> listByAdminId() {
        String token = request.getHeader("Authorization");
        String adminId = JwtUtil.validateToken(token);
        return baseMapper.listByAdminId(adminId);
    }

    @Override
    public Boolean setStatus(String ids, int status, String reason) {
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            OrdersItem ordersItem = this.getById(id);
            if (status == -2) {
                //记录申请退款前的订单状态
                ordersItem.setBeforeStatus(ordersItem.getStatus());
            }
            ordersItem.setStatus(status);
            ordersItem.setReason(reason);
            ordersItem.setUpdateTime(DateTool.getCurrTime());
            this.updateById(ordersItem);
        }
        return true;
    }

}
