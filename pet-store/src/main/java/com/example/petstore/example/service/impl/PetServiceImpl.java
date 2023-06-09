package com.example.petstore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petstore.common.utils.DateTool;
import com.example.petstore.example.entiey.Pet;
import com.example.petstore.example.mapper.PetMapper;
import com.example.petstore.example.service.PetService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements PetService {

    @Override
    public Boolean add(Pet pet) {
        pet.setCreateTime(DateTool.getCurrTime());
        this.save(pet);
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
    public Boolean update(Pet pet) {
        this.updateById(pet);
        return true;
    }
    @Override
    public List<Pet> list(String name, Double priceMin, Double priceMax) {
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
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

//    @Override
//    public List<BookShop> listByIds(String ids) {
//        List<BookShop> list = new ArrayList<>();
//        String[] aryIds = ids.split(",");
//        for (String id : aryIds) {
//            list.add(baseMapper.listByBookId(id));
//        }
//        return list;
//    }

    @Override
    public Page<Pet> page(String name, Integer pageNum, Integer pageSize) {
        Page<Pet> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("name", name);
            return this.page(page, queryWrapper);
        } else {
            return this.page(page);
        }
    }


    @Override
    public List<Pet> listByShopId(String shopId, String name, Double priceMin, Double priceMax) {
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id", shopId);
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
