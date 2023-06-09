package com.example.bookStore.example.controller;

import com.example.bookStore.common.utils.Result;
import com.example.bookStore.example.entiey.Orders;
import com.example.bookStore.example.service.OrdersService;
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
@RequestMapping("/orders")
@Api(tags = "OrdersController", description = "订单controller，返回的baseUrl=http://localhost:8998/bookStore/")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    //添加订单
    @ApiOperation(value = "添加订单")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add() {
        Result result = new Result();
        Orders orders = new Orders();
        ordersService.add(orders);
        result.success("添加成功");
        result.setData(orders);
        return result;
    }

    //批量删除订单
    @ApiOperation(value = "批量删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        ordersService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改订单
    @ApiOperation(value = "修改订单")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Orders orders) {
        Result result = new Result();
        ordersService.update(orders);
        result.success("修改成功");
        return result;
    }
    

    //查询订单信息
    @ApiOperation(value = "查询订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", paramType = "query", value = "需要查询订单的用户id，可为空，空则查询所有订单")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String userId) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(ordersService.list(userId));
        return result;
    }

    //查询指定订单id的订单信息
    @ApiOperation(value = "查询指定订单id的订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ordersId", required = true, paramType = "query", value = "需要查询的订单id")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/getById")
    public Result getById(String ordersId) {
        Result result = new Result();
        result.success("查询成功");
        result.setData(ordersService.getById(ordersId));
        return result;
    }

}
