package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.Video;

import java.util.List;

public interface VideoService extends IService<Video> {
    Boolean add(Video video);

    void deleteByIds(String ids);

    Boolean update(Video Video);



    List<Video> listByShopId(String shopId, String name, Double priceMin, Double priceMax);
}
