package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.User;

import java.util.List;

public interface UserService extends IService<User> {
    Boolean add(User user);

    void deleteByIds(String ids);

    Boolean update(User user);

    List<User> list(String name);

    Page<User> page(String name, String action, String permission, Integer pageNum, Integer pageSize);

    User getByName(String name);

    void updateUsefulByIds(String ids, Boolean useful);

    void updatePermissionByIds(String ids, int permission);

    User getByToken();

}