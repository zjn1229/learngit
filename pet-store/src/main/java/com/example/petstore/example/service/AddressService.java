package com.example.petstore.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.petstore.example.entiey.Address;
import java.util.List;

public interface AddressService extends IService<Address>{
    Boolean add(Address address);

    Boolean update(Address address);

    void deleteByIds(String ids);

    List<Address> list(String userId);
}
