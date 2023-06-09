package com.example.petstore.example.controller;

import com.example.petstore.common.utils.Result;
import com.example.petstore.example.entiey.Evaluation;
import com.example.petstore.example.service.EvaluationService;
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
@RequestMapping("/evaluation")
@Api(tags = "EvaluationController", description = "评价controller，返回的baseUrl=http://localhost:8998/bookStore/")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    //添加评价
    @ApiOperation(value = "添加评价")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody Evaluation evaluation) {
        Result result = new Result();
        evaluationService.add(evaluation);
        result.success("添加成功");
        result.setData(evaluation);
        return result;
    }

    //批量删除评价
    @ApiOperation(value = "批量删除评价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        evaluationService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改评价
    @ApiOperation(value = "修改评价")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Evaluation evaluation) {
        Result result = new Result();
        evaluationService.update(evaluation);
        result.success("修改成功");
        return result;
    }


//    //查询当前用户评价信息
//    @ApiOperation(value = "查询当前用户评价信息")
//    @RequestMapping(method = RequestMethod.POST, value = "/list")
//    public Result list() {
//        Result result = new Result();
//        result.success("查询list成功");
//        result.setData(evaluationService.listByUserId());
//        return result;
//    }

//    //通过书籍id查询评价信息
//    @ApiOperation(value = "通过书籍id查询评价信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "bookId", required = true, paramType = "query", value = "需要查询评价的书籍id")
//    })
//    @RequestMapping(method = RequestMethod.POST, value = "/pageByBookId")
//    public Result pageByBookId(String bookId, Integer pageNum, Integer pageSize) {
//        Result result = new Result();
//        result.success("查询list成功");
//        result.setData(evaluationService.pageByBookId(bookId, pageNum, pageSize));
//        return result;
//    }

    //查询指定评价id的评价信息
    @ApiOperation(value = "查询指定评价id的评价信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluationId", required = true, paramType = "query", value = "需要查询的评价id")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/getById")
    public Result getById(String evaluationId) {
        Result result = new Result();
        result.success("查询成功");
        result.setData(evaluationService.getById(evaluationId));
        return result;
    }

}
