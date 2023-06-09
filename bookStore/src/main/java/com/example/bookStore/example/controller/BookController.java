package com.example.bookStore.example.controller;

import com.example.bookStore.common.utils.Result;
import com.example.bookStore.example.entiey.Book;
import com.example.bookStore.example.service.BookService;
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
@RequestMapping("/book")
@Api(tags = "BookController", description = "书籍controller，返回的baseUrl=http://localhost:8998/bookStore/")
public class BookController {

    @Autowired
    private BookService bookService;

    //添加书籍
    @ApiOperation(value = "添加书籍")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody Book book) {
        Result result = new Result();
        if (book.getShopId() != null) {
            bookService.add(book);
            result.success("添加成功");
            result.setData(book);
        } else {
            result.fail("添加失败，请返回重试");
        }
        return result;
    }

    //批量删除书籍-deleteByIds
    @ApiOperation(value = "批量删除书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        bookService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改书籍
    @ApiOperation(value = "修改书籍")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Book book) {
        Result result = new Result();
        bookService.update(book);
        result.success("修改成功");
        return result;
    }

    //更新useful-updateUsefulByIds
    @ApiOperation(value = "批量更新书籍的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要更新的多个id，用逗号,隔开"),
            @ApiImplicitParam(name = "useful", required = true, paramType = "query", value = "是否可用")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String ids, Boolean useful) {
        Result result = new Result();
        bookService.updateUsefulByIds(ids, useful);
        result.success("更新成功");
        return result;
    }

    //更新书籍库存
    @ApiOperation(value = "更新书籍库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookId", required = true, paramType = "query", value = "书籍Id"),
            @ApiImplicitParam(name = "quantity", required = true, paramType = "query", value = "减少的数量")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updateStockById")
    public Result updateStockById(String bookId, int quantity) {
        Result result = new Result();
        Boolean flag = bookService.updateStockByIds(bookId, quantity);
        if (flag) {
            result.success("更新成功");
        } else {
            result.fail("库存不足");
        }
        return result;
    }

    //查询所有书籍信息（可指定name）
    @ApiOperation(value = "查询所有书籍信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "书籍名关键字，可以为空"),
            @ApiImplicitParam(name = "priceMin", paramType = "query", value = "价格最小值，可以为空"),
            @ApiImplicitParam(name = "priceMax", paramType = "query", value = "价格最大值，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String name, Double priceMin, Double priceMax) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(bookService.list(name, priceMin, priceMax));
        return result;
    }

    //通过ids查询书籍
    @ApiOperation(value = "通过ids查询书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", paramType = "query", required = true, value = "书籍id集合，用逗号,隔开"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/listByIds")
    public Result listByIds(String ids) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(bookService.listByIds(ids));
        return result;
    }

    //查询指定店铺的所有书籍信息（可指定name）
    @ApiOperation(value = "查询指定店铺的所有书籍信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", required = true, value = "店铺id"),
            @ApiImplicitParam(name = "name", paramType = "query", value = "书籍名关键字，可以为空"),
            @ApiImplicitParam(name = "priceMin", paramType = "query", value = "价格最小值，可以为空"),
            @ApiImplicitParam(name = "priceMax", paramType = "query", value = "价格最大值，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/listByShopId")
    public Result listByShopId(String shopId, String name, Double priceMin, Double priceMax) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(bookService.listByShopId(shopId, name, priceMin, priceMax));
        return result;
    }

    //分页查询（可指定name）
    @ApiOperation(value = "分页返回书籍信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "书籍名关键字，可以为空"),
            @ApiImplicitParam(name = "pageNum", required = true, paramType = "query", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", required = true, paramType = "query", value = "每页显示条数"),

    })
    @RequestMapping(method = RequestMethod.POST, value = "/page")
    public Result page(String name, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.success("查询page成功");
        result.setData(bookService.page(name, pageNum, pageSize));
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

        Book book = bookService.getById(id);
        if (book != null) {
            result.success("获取成功");
            result.setData(book);
        } else {
            result.fail("获取失败");
        }

        return result;
    }


}
