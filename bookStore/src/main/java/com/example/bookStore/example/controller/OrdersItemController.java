package com.example.bookStore.example.controller;

import com.example.bookStore.common.utils.Result;
import com.example.bookStore.example.entiey.OrdersItem;
import com.example.bookStore.example.service.OrdersItemService;
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
@RequestMapping("/ordersItem")
@Api(tags = "OrdersItemController", description = "订单项目controller，返回的baseUrl=http://localhost:8998/bookStore/")
public class OrdersItemController {

    @Autowired
    private OrdersItemService ordersItemService;

    //添加订单项目
    @ApiOperation(value = "添加订单项目")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody OrdersItem ordersItem) {
        Result result = new Result();
        ordersItemService.add(ordersItem);
        result.success("添加成功");
        result.setData(ordersItem);
        return result;
    }

    //批量删除订单项目
    @ApiOperation(value = "批量删除订单项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        ordersItemService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改订单项目
    @ApiOperation(value = "修改订单项目")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody OrdersItem ordersItem) {
        Result result = new Result();
        ordersItemService.update(ordersItem);
        result.success("修改成功");
        return result;
    }
    

    //查询当前用户的订单项目信息
    @ApiOperation(value = "查询查询当前用户的订单项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookName", paramType = "query", value = "需要查询订单项目的书名，可为空")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String bookName) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(ordersItemService.list(bookName));
        return result;
    }

    //查询指定id的订单项目信息
    @ApiOperation(value = "查询指定id的订单项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ordersItemId", required = true, paramType = "query", value = "需要查询的订单项目id")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/getById")
    public Result getById(String ordersItemId) {
        Result result = new Result();
        result.success("查询成功");
        result.setData(ordersItemService.getById(ordersItemId));
        return result;
    }

    //查询指定管理员id所关联的订单项目信息
    @ApiOperation(value = "查询指定管理员id所关联的订单项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", required = true, paramType = "query", value = "需要查询的订单所关联的管理员id"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/listByAdminId")
    public Result listByAdminId() {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(ordersItemService.listByAdminId());
        return result;
    }

    //批量修改订单项目状态
    @ApiOperation(value = "批量修改订单项目状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要修改的多个id，用逗号,隔开"),
            @ApiImplicitParam(name = "status", required = true, paramType = "query", value = "要修改成的状态"),
            @ApiImplicitParam(name = "reason", paramType = "query", value = "退单理由，可为空")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/setStatusByIds")
    public Result setStatusByIds(String ids, int status, String reason) {
        Result result = new Result();
        ordersItemService.setStatus(ids, status, reason);
        result.success("修改成功");
        return result;
    }

}
