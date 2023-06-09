package com.example.bookStore.example.controller;

import com.example.bookStore.common.utils.Result;
import com.example.bookStore.example.entiey.Cart;
import com.example.bookStore.example.service.CartService;
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
@RequestMapping("/cart")
@Api(tags = "CartController", description = "购物车controller，返回的baseUrl=http://localhost:8998/bookStore/")
public class CartController {

    @Autowired
    private CartService cartService;

    //添加购物车
    @ApiOperation(value = "添加购物车")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody Cart cart) {
        Result result = new Result();
        cartService.add(cart);
        result.success("添加成功");
        result.setData(cart);
        return result;
    }

    //批量删除购物车-deleteByIds
    @ApiOperation(value = "批量删除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        cartService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改购物车
    @ApiOperation(value = "修改购物车")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Cart cart) {
        Result result = new Result();
        cartService.update(cart);
        result.success("修改成功");
        return result;
    }

    //更新useful-updateUsefulByIds
    @ApiOperation(value = "批量更新购物车的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要更新的多个id，用逗号,隔开"),
            @ApiImplicitParam(name = "useful", required = true, paramType = "query", value = "是否可用")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String ids, Boolean useful) {
        Result result = new Result();
        cartService.updateUsefulByIds(ids, useful);
        result.success("更新成功");
        return result;
    }

    //查询用户所有购物车信息（可指定书名）
    @ApiOperation(value = "查询用户所有购物车信息（可指定书名）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookName", paramType = "query", value = "书名关键字，可以为空")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String bookName) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(cartService.listByBookName(bookName));
        return result;
    }

    //分页查询（可指定书名）
    @ApiOperation(value = "分页返回购物车信息（可指定书名）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",  required = true, paramType = "userId", value = "用户id"),
            @ApiImplicitParam(name = "name", paramType = "query", value = "书名关键字，可以为空"),
            @ApiImplicitParam(name = "pageNum", required = true, paramType = "query", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", required = true, paramType = "query", value = "每页显示条数"),

    })
    @RequestMapping(method = RequestMethod.POST, value = "/page")
    public Result page(String userId, String bookName, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.success("查询page成功");
        result.setData(cartService.page(bookName, pageNum, pageSize));
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
        Cart cart = cartService.getById(id);
        if (cart != null){
            result.success("获取成功");
            result.setData(cart);
        }else{
            result.fail("获取失败");
        }
        return result;
    }

    //通过bookId查询
    @ApiOperation(value = "通过bookId查询")
    @RequestMapping(method = RequestMethod.POST, value = "/getByBookId")
    public Result getByBookId(String bookId) {
        Result result = new Result();
        Cart cart = cartService.getByBookId(bookId);
        result.success("查询成功");
        result.setData(cart);
        return result;
    }

}
