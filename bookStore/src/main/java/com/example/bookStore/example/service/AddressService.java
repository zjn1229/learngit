package com.example.bookStore.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookStore.example.entiey.Address;

import java.util.List;

public interface AddressService extends IService<Address> {
    Boolean add(Address address);

    void deleteByIds(String ids);

    Boolean update(Address address);

    List<Address> list(String userId);

}
