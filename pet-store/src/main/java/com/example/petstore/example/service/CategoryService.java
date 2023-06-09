package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    Boolean add(Category category);

    void deleteByIds(String ids);

    Boolean update(Category category);

    List<Category> list(String name);
}
