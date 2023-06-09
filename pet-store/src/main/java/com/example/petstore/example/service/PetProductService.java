package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.PetProduct;
//import com.example.petstore.example.vo.BookShop;

import java.util.List;

public interface PetProductService extends IService<PetProduct> {
    Boolean add(PetProduct petProduct);

    void deleteByIds(String ids);

    Boolean update(PetProduct petProduct);

    List<PetProduct> list(String name, Double priceMin, Double priceMax);

//    List<BookShop> listByIds(String ids);

    Page<PetProduct> page(String name, Integer pageNum, Integer pageSize);

    Boolean updateStockByIds(String bookId, int quantity);

    List<PetProduct> listByShopId(String shopId, String name, Double priceMin, Double priceMax);

}
