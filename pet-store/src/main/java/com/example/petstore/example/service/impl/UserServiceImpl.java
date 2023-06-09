package com.example.petstore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petstore.common.utils.DateTool;
import com.example.petstore.common.utils.JwtUtil;
import com.example.petstore.example.entiey.User;
import com.example.petstore.example.mapper.UserMapper;
import com.example.petstore.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Boolean add(User user) {
        user.setPermission(0);
        user.setUseful(true);
        user.setCreateTime(DateTool.getCurrTime());

        this.save(user);
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
    public Boolean update(User user) {
        this.updateById(user);
        return true;
    }

    @Override
    public List<User> list(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("name", name);
        }
        return this.list(queryWrapper);
    }

    @Override
    public Page<User> page(String name, String action, String permission, Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            if (action != null && action.equals("all")) {
                queryWrapper.and(wrapper -> wrapper.like("name", name).or().like("nickname", name));
            } else if (action != null) {
                queryWrapper.like(action, name);
            } else {
                queryWrapper.like("name", name);
            }
        }
        if (permission != null) {
            queryWrapper.eq("permission", Integer.parseInt(permission));
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public User getByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return this.getOne(queryWrapper);
    }

    @Override
    public void updateUsefulByIds(String ids, Boolean useful) {
        String[] aryId = ids.split(",");
        for (String id : aryId) {
            User user = this.getById(id);
            if (user != null) {
                user.setUseful(useful);
                this.updateById(user);
            }
        }

    }

    @Override
    public void updatePermissionByIds(String ids, int permission) {
        String[] aryId = ids.split(",");
        for (String id : aryId) {
            User user = this.getById(id);
            if (user != null) {
                user.setPermission(permission);
                this.updateById(user);
            }
        }
    }

    @Override
    public User getByToken() {
        String token = request.getHeader("Authorization");
        String userId = JwtUtil.validateToken(token);
        User user = getById(userId);
        return user;
    }


}

