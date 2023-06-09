package com.example.petstore.example.controller;

import com.example.petstore.common.utils.Result;
import com.example.petstore.example.entiey.Pet;
import com.example.petstore.example.service.PetService;
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
@RequestMapping("/pet")
@Api(tags = "PetController", description = "宠物controller，返回的baseUrl=http://localhost:8998/petstore/")
public class PetController {

    @Autowired
    private PetService petService;

    //添加宠物
    @ApiOperation(value = "添加宠物")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody Pet pet) {
        Result result = new Result();

        System.out.println("pet.getShopId():"+pet.getShopId());

        if (pet.getShopId() != null) {
            System.out.println("我进来啦");
            petService.add(pet);
            result.success("添加成功");
            result.setData(pet);
        } else {
            result.fail("添加失败，请返回重试");
        }
        return result;
    }


    //批量删除宠物-deleteByIds
    @ApiOperation(value = "批量删除宠物")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        petService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改宠物
    @ApiOperation(value = "修改宠物")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Pet pet) {
        Result result = new Result();
        petService.update(pet);
        result.success("修改成功");
        return result;
    }

    //查询所有宠物（可指定name）
    @ApiOperation(value = "查询所有宠物信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "宠物名关键字，可以为空"),
            @ApiImplicitParam(name = "priceMin", paramType = "query", value = "价格最小值，可以为空"),
            @ApiImplicitParam(name = "priceMax", paramType = "query", value = "价格最大值，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String name, Double priceMin, Double priceMax) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(petService.list(name, priceMin, priceMax));
        return result;
    }

//    //通过ids查询宠物
//    @ApiOperation(value = "通过ids查询宠物")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "ids", paramType = "query", required = true, value = "宠物id集合，用逗号,隔开"),
//    })
//    @RequestMapping(method = RequestMethod.POST, value = "/listByIds")
//    public Result listByIds(String ids) {
//        Result result = new Result();
//        result.success("查询list成功");
//        result.setData(petService.listByIds(ids));
//        return result;
//    }

    //查询指定店铺的所有宠物信息（可指定name）
    @ApiOperation(value = "查询指定店铺的所有宠物信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", required = true, value = "店铺id"),
            @ApiImplicitParam(name = "name", paramType = "query", value = "宠物名关键字，可以为空"),
            @ApiImplicitParam(name = "priceMin", paramType = "query", value = "价格最小值，可以为空"),
            @ApiImplicitParam(name = "priceMax", paramType = "query", value = "价格最大值，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/listByShopId")
    public Result listByShopId(String shopId, String name, Double priceMin, Double priceMax) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(petService.listByShopId(shopId, name, priceMin, priceMax));
        return result;
    }

    //分页查询（可指定name）
    @ApiOperation(value = "分页返回宠物信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "宠物名关键字，可以为空"),
            @ApiImplicitParam(name = "pageNum", required = true, paramType = "query", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", required = true, paramType = "query", value = "每页显示条数"),

    })
    @RequestMapping(method = RequestMethod.POST, value = "/page")
    public Result page(String name, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.success("查询page成功");
        result.setData(petService.page(name, pageNum, pageSize));
        return result;
    }

    //查-getById
    @ApiOperation(value = "通过id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "需要查询的id")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/getById")
    public Result getById(String id) {
        Result result = new Result();

        Pet pet = petService.getById(id);
        if (pet != null) {
            result.success("获取成功");
            result.setData(pet);
        } else {
            result.fail("获取失败");
        }
        return result;
    }
}
