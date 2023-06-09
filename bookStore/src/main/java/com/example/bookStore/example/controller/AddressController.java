package com.example.bookStore.example.controller;

import com.example.bookStore.common.utils.Result;
import com.example.bookStore.example.entiey.Address;
import com.example.bookStore.example.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@Api(tags = "AddressController", description = "收货地址controller，返回的baseUrl=http://localhost:8998/bookStore/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    //添加收货地址
    @ApiOperation(value = "添加收货地址")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody Address address) {
        Result result = new Result();
        addressService.add(address);
        result.success("添加成功");
        result.setData(address);
        return result;
    }

    //批量删除收货地址
    @ApiOperation(value = "批量删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        addressService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改收货地址
    @ApiOperation(value = "修改收货地址")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Address address) {
        Result result = new Result();
        addressService.update(address);
        result.success("修改成功");
        return result;
    }
    

    //查询收货地址信息
    @ApiOperation(value = "查询收货地址信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", paramType = "query", value = "需要查询收货地址的用户id，可为空，空则查询当前登录用户的收货地址")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String userId) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(addressService.list(userId));
        return result;
    }

    //查询指定地址id的地址信息
    @ApiOperation(value = "查询指定地址id的地址信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", required = true, paramType = "query", value = "需要查询的收货地址id")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/getById")
    public Result getById(String addressId) {
        Result result = new Result();
        result.success("查询成功");
        result.setData(addressService.getById(addressId));
        return result;
    }

}
