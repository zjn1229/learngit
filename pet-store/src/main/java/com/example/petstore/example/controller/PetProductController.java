package com.example.petstore.example.controller;

import com.example.petstore.common.utils.Result;
import com.example.petstore.example.entiey.PetProduct;
import com.example.petstore.example.service.PetProductService;
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
@RequestMapping("/petProduct")
@Api(tags = "PetProductController", description = "商品controller，返回的baseUrl=http://localhost:8998/petStore/")
public class PetProductController {

    @Autowired
    private PetProductService petProductService;

    //添加商品
    @ApiOperation(value = "添加商品")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody PetProduct petProduct) {
        Result result = new Result();
        if (petProduct.getShopId() != null) {
            petProductService.add(petProduct);
            result.success("添加成功");
            result.setData(petProduct);
        } else {
            result.fail("添加失败，请返回重试");
        }
        return result;
    }

    //批量删除商品-deleteByIds
    @ApiOperation(value = "批量删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        petProductService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改商品信息
    @ApiOperation(value = "修改商品信息")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody PetProduct petProduct) {
        Result result = new Result();
        petProductService.update(petProduct);
        result.success("修改成功");
        return result;
    }


    //更新商品库存
    @ApiOperation(value = "更新商品库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "petProductId", required = true, paramType = "query", value = "商品Id"),
            @ApiImplicitParam(name = "quantity", required = true, paramType = "query", value = "减少的数量")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updateStockById")
    public Result updateStockById(String petProductId, int quantity) {
        Result result = new Result();
        Boolean flag = petProductService.updateStockByIds(petProductId, quantity);
        if (flag) {
            result.success("更新成功");
        } else {
            result.fail("库存不足");
        }
        return result;
    }

    //查询所有商店信息（可指定name）
    @ApiOperation(value = "查询所有商店信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "商品名关键字，可以为空"),
            @ApiImplicitParam(name = "priceMin", paramType = "query", value = "价格最小值，可以为空"),
            @ApiImplicitParam(name = "priceMax", paramType = "query", value = "价格最大值，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String name, Double priceMin, Double priceMax) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(petProductService.list(name, priceMin, priceMax));
        return result;
    }

//    //通过ids查询商店
//    @ApiOperation(value = "通过ids查询商店")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "ids", paramType = "query", required = true, value = "商品id集合，用逗号,隔开"),
//    })
//    @RequestMapping(method = RequestMethod.POST, value = "/listByIds")
//    public Result listByIds(String ids) {
//        Result result = new Result();
//        result.success("查询list成功");
//        result.setData(petProductService.listByIds(ids));
//        return result;
//    }

    //查询指定店铺的所有商品信息（可指定name）
    @ApiOperation(value = "查询指定店铺的所有商品信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", required = true, value = "店铺id"),
            @ApiImplicitParam(name = "name", paramType = "query", value = "商品名关键字，可以为空"),
            @ApiImplicitParam(name = "priceMin", paramType = "query", value = "价格最小值，可以为空"),
            @ApiImplicitParam(name = "priceMax", paramType = "query", value = "价格最大值，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/listByShopId")
    public Result listByShopId(String shopId, String name, Double priceMin, Double priceMax) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(petProductService.listByShopId(shopId, name, priceMin, priceMax));
        return result;
    }

    //分页查询（可指定name）
    @ApiOperation(value = "分页返回商品信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "商品名关键字，可以为空"),
            @ApiImplicitParam(name = "pageNum", required = true, paramType = "query", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", required = true, paramType = "query", value = "每页显示条数"),

    })
    @RequestMapping(method = RequestMethod.POST, value = "/page")
    public Result page(String name, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.success("查询page成功");
        result.setData(petProductService.page(name, pageNum, pageSize));
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

        PetProduct petProduct = petProductService.getById(id);
        if (petProduct != null) {
            result.success("获取成功");
            result.setData(petProduct);
        } else {
            result.fail("获取失败");
        }

        return result;
    }


}

