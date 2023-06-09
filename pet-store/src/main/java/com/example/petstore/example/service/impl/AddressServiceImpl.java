package com.example.petstore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petstore.common.utils.DateTool;
import com.example.petstore.common.utils.JwtUtil;
import com.example.petstore.example.entiey.Address;
import com.example.petstore.example.mapper.AddressMapper;
import com.example.petstore.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Boolean add(Address address) {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        address.setUserId(userId);
        address.setCreateTime(DateTool.getCurrTime());
        //取消原默认地址
        if (address.getIsDefault()) {
            QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_default", true);
            Address oldAddress = this.getOne(queryWrapper);
            if (oldAddress != null) {
                oldAddress.setIsDefault(false);
                update(oldAddress);
            }
        }
        this.save(address);
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
    public Boolean update(Address address) {
        //取消原默认地址
        if (address.getIsDefault()) {
            QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_default", true);
            Address oldAddress = this.getOne(queryWrapper);
            if (oldAddress != null) {
                oldAddress.setIsDefault(false);
                this.update(oldAddress);
            }
        }
        this.updateById(address);
        return true;
    }

    @Override
    public List<Address> list(String userId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        if (userId == null || userId.isEmpty()) {
            String token = request.getHeader("Authorization");
            userId = JwtUtil.validateToken(token);
        }
        queryWrapper.eq("user_id", userId);
        return this.list(queryWrapper);
    }
}