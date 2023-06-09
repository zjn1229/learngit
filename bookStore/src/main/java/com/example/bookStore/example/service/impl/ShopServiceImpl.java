package com.example.bookStore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookStore.common.utils.DateTool;
import com.example.bookStore.common.utils.JwtUtil;
import com.example.bookStore.example.entiey.Shop;
import com.example.bookStore.example.mapper.ShopMapper;
import com.example.bookStore.example.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Boolean add(Shop shop) {
        String token = request.getHeader("Authorization");
        String administratorId = JwtUtil.validateToken(token);
        shop.setAdministratorId(administratorId);
        shop.setUseful(true);
        shop.setCreateTime(DateTool.getCurrTime());
        this.save(shop);
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
    public Boolean update(Shop shop) {
        this.updateById(shop);
        return true;
    }

    @Override
    public List<Shop> list(String name) {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("useful", true);
        if (name != null) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }

    @Override
    public Page<Shop> page(String adminId, String name, Integer pageNum, Integer pageSize) {
        Page<Shop> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("useful", true);
        if (adminId == null || adminId.isEmpty()) {
            String token = request.getHeader("Authorization");
            String administratorId = JwtUtil.validateToken(token);
            queryWrapper.eq("administrator_id", administratorId);
        } else {
            queryWrapper.eq("administrator_id", adminId);
        }
        if (name != null) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("create_time");
        return this.page(page, queryWrapper);
    }

    @Override
    public void updateUsefulByIds(String ids, Boolean useful) {
        String[] aryId = ids.split(",");
        for (String id : aryId) {
            Shop shop = this.getById(id);
            if (shop != null) {
                shop.setUseful(useful);
                this.updateById(shop);
            }
        }

    }


}
