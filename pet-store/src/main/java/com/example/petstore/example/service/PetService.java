package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.Pet;

import java.util.List;

public interface PetService extends IService<Pet> {

    Boolean add(Pet pet);


    List<Pet> list(String name, Double priceMin, Double priceMax);

    void deleteByIds(String ids);

    Boolean update(Pet pet);


//    List<BookShop> listByIds(String ids);

    Page<Pet> page(String name, Integer pageNum, Integer pageSize);

    List<Pet> listByShopId(String shopId, String name, Double priceMin, Double priceMax);

}
