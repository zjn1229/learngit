package com.example.petstore.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petstore.common.utils.DateTool;
import com.example.petstore.example.entiey.PetProduct;
import com.example.petstore.example.mapper.PetProductMapper;
import com.example.petstore.example.service.PetProductService;
//import com.example.petstore.example.vo.BookShop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetProductServiceImpl extends ServiceImpl<PetProductMapper, PetProduct> implements PetProductService {

    @Override
    public Boolean add(PetProduct petProduct) {
        petProduct.setCreateTime(DateTool.getCurrTime());
        this.save(petProduct);
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
    public Boolean update(PetProduct petProduct) {
        this.updateById(petProduct);
        return true;
    }

    @Override
    public List<PetProduct> list(String name, Double priceMin, Double priceMax) {
        QueryWrapper<PetProduct> queryWrapper = new QueryWrapper<>();
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
    public Page<PetProduct> page(String name, Integer pageNum, Integer pageSize) {
        Page<PetProduct> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PetProduct> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("name", name);
            return this.page(page, queryWrapper);
        } else {
            return this.page(page);
        }
    }


    @Override
    public Boolean updateStockByIds(String petProductId, int quantity) {
        PetProduct petProduct = this.getById(petProductId);
        if (petProduct.getStock() >= quantity) {
            petProduct.setStock(petProduct.getStock() - quantity);
            this.updateById(petProduct);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<PetProduct> listByShopId(String shopId, String name, Double priceMin, Double priceMax) {
        QueryWrapper<PetProduct> queryWrapper = new QueryWrapper<>();
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
