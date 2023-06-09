package com.example.petstore.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petstore.common.utils.DateTool;
import com.example.petstore.example.entiey.Video;
import com.example.petstore.example.mapper.VideoMapper;
import com.example.petstore.example.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public Boolean add(Video video) {
        video.setCreateTime(DateTool.getCurrTime());
        this.save(video);
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
    public Boolean update(Video video) {
        this.updateById(video);
        return true;
    }



    @Override
    public List<Video> listByShopId(String goodsId, String name, Double priceMin, Double priceMax) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", goodsId);
        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (priceMin != null) {
            queryWrapper.ge("price", priceMin);
        }
        if (priceMax != null) {
            queryWrapper.lt("price", priceMax);
        }
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }


}
