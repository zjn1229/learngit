package com.example.petstore.example.controller;

import com.example.petstore.common.utils.Result;
import com.example.petstore.example.entiey.Video;
import com.example.petstore.example.service.VideoService;
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
@RequestMapping("/video")
@Api(tags = "VideoController", description = "书籍controller，返回的baseUrl=http://localhost:8998/bookStore/")
public class VideoController {

    @Autowired
    private VideoService videoService;

    //添加书籍
    @ApiOperation(value = "添加书籍")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody Video video) {
        Result result = new Result();
        if (video.getGoodsId() != null) {
            videoService.add(video);
            result.success("添加成功");
            result.setData(video);
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
        videoService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改书籍
    @ApiOperation(value = "修改书籍")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody Video video) {
        Result result = new Result();
        videoService.update(video);
        result.success("修改成功");
        return result;
    }




    //查询所有视频信息（可指定name）
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
        result.setData(videoService.list(name, priceMin, priceMax));
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
        result.setData(videoService.listByShopId(shopId, name, priceMin, priceMax));
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

        Video video = videoService.getById(id);
        if (video != null) {
            result.success("获取成功");
            result.setData(video);
        } else {
            result.fail("获取失败");
        }

        return result;
    }


}
