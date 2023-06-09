package com.example.petstore.example.controller;

import com.example.petstore.common.utils.Result;
import com.example.petstore.example.entiey.Category;
import com.example.petstore.example.service.CategoryService;
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
@RequestMapping("/category")
@Api(tags = "CategoryController", description = "类别controller，返回的baseUrl=http://localhost:8998/categoryStore/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //添加类别
    @ApiOperation(value = "添加类别")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody Category category) {
        Result result = new Result();
        categoryService.add(category);
        result.success("添加成功");
        result.setData(category);
        return result;
    }

    //批量删除类别-deleteByIds
    @ApiOperation(value = "批量删除类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        categoryService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改类别
    @ApiOperation(value = "修改类别")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Category category) {
        Result result = new Result();
        categoryService.update(category);
        result.success("修改成功");
        return result;
    }

    //查询所有类别信息（可指定name）
    @ApiOperation(value = "查询所有类别信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "类别关键字，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String name) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(categoryService.list(name));
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
        Category category = categoryService.getById(id);
        if (category != null){
            result.success("获取成功");
            result.setData(category);
        }else{
            result.fail("获取失败");
        }
        return result;
    }


}
