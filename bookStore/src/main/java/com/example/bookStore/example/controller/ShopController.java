package com.example.bookStore.example.controller;

import com.example.bookStore.common.utils.Result;
import com.example.bookStore.example.entiey.Shop;
import com.example.bookStore.example.service.ShopService;
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
@RequestMapping("/shop")
@Api(tags = "ShopController", description = "店铺controller，返回的baseUrl=http://localhost:8998/shopStore/")
public class ShopController {

    @Autowired
    private ShopService shopService;

    //添加店铺
    @ApiOperation(value = "添加店铺")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody Shop shop) {
        Result result = new Result();
        shopService.add(shop);
        result.success("添加成功");
        result.setData(shop);
        return result;
    }

    //批量删除店铺-deleteByIds
    @ApiOperation(value = "批量删除店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        shopService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改店铺
    @ApiOperation(value = "修改店铺")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Shop shop) {
        Result result = new Result();
        shopService.update(shop);
        result.success("修改成功");
        return result;
    }

    //更新useful-updateUsefulByIds
    @ApiOperation(value = "批量更新店铺的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要更新的多个id，用逗号,隔开"),
            @ApiImplicitParam(name = "useful", required = true, paramType = "query", value = "是否可用")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String ids, Boolean useful) {
        Result result = new Result();
        shopService.updateUsefulByIds(ids, useful);
        result.success("更新成功");
        return result;
    }

    //查询所有店铺信息（可指定name）
    @ApiOperation(value = "查询所有店铺信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "店铺名关键字，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String name) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(shopService.list(name));
        return result;
    }

    //分页查询（可指定name或管理员id）
    @ApiOperation(value = "分页返回店铺信息（可指定name或管理员id）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", paramType = "query", value = "管理员id，可以为空"),
            @ApiImplicitParam(name = "name", paramType = "query", value = "店铺名关键字，可以为空"),
            @ApiImplicitParam(name = "pageNum", required = true, paramType = "query", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", required = true, paramType = "query", value = "每页显示条数"),

    })
    @RequestMapping(method = RequestMethod.POST, value = "/page")
    public Result page(String adminId, String name, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.success("查询page成功");
        result.setData(shopService.page(adminId, name, pageNum, pageSize));
        return result;
    }

    //查-getById
    @ApiOperation(value = "通过id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true,paramType = "query",value = "需要查询的id")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/getById")
    public Result getById(String id) {
        Result result = new Result();

        Shop shop = shopService.getById(id);
        if (shop != null){
            result.success("获取成功");
            result.setData(shop);
        }else{
            result.fail("获取失败");
        }

        return result;
    }


}
