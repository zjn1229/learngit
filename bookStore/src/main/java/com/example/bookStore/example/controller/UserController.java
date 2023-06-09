package com.example.bookStore.example.controller;

import com.example.bookStore.common.utils.JwtUtil;
import com.example.bookStore.common.utils.Result;
import com.example.bookStore.example.entiey.User;
import com.example.bookStore.example.service.UserService;
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
@RequestMapping("/user")
@Api(tags = "UserController", description = "用户controller，返回的baseUrl=http://localhost:8998/bookStore/")
public class UserController {
    @Autowired
    private UserService userService;

    //添加用户
    @ApiOperation(value = "添加用户")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Result add(@RequestBody User user) {
        Result result = new Result();
        User userExit = userService.getByName(user.getName());
        if (userExit != null) {
            result.fail("用户名" + user.getName() + "已存在");
        } else {
            userService.add(user);
            result.success("添加成功");
            result.setData(user);
        }
        return result;
    }

    //批量删除用户-deleteByIds
    @ApiOperation(value = "批量删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        userService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

    //修改用户信息
    @ApiOperation(value = "修改用户信息")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Result update(@RequestBody User user) {
        Result result = new Result();
        User userExit = userService.getByName(user.getName());
        if (userExit != null && !user.getId().equals(userExit.getId())) {
            result.fail("用户名：" + user.getName() + ",已经存在，不能修改");
        } else {
            userService.update(user);
            result.success("修改成功");
        }
        return result;
    }

    //更新useful-updateUsefulByIds
    @ApiOperation(value = "批量更新用户的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要更新的多个id，用逗号,隔开"),
            @ApiImplicitParam(name = "useful", required = true, paramType = "query", value = "是否可用")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updateUsefulByIds")
    public Result updateUsefulByIds(String ids, Boolean useful) {
        Result result = new Result();
        userService.updateUsefulByIds(ids, useful);
        result.success("更新成功");
        return result;
    }

    //更新用户权限-updatePermissionByIds
    @ApiOperation(value = "批量更新用户权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, paramType = "query", value = "需要更新的多个id，用逗号,隔开"),
            @ApiImplicitParam(name = "permission", required = true, paramType = "query", value = "用户权限，1-前端用户，" +
                    "2-信息管理员，3-超级管理员")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updatePermissionByIds")
    public Result updatePermissionByIds(String ids, int permission) {
        Result result = new Result();
        userService.updatePermissionByIds(ids, permission);
        result.success("更新成功");
        return result;
    }

    //查询所有用户信息（可指定name）
    @ApiOperation(value = "查询所有商品信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "用户名关键字，可以为空"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    public Result list(String name) {
        Result result = new Result();
        result.success("查询list成功");
        result.setData(userService.list(name));
        return result;
    }

    //分页查询（可指定name）
    @ApiOperation(value = "分页返回用户信息（可指定name）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "用户名关键字，可以为空"),
            @ApiImplicitParam(name = "permission", paramType = "query", value = "用户权限，可以为空"),
            @ApiImplicitParam(name = "action", paramType = "query", value = "查找方式，可以为空，空表示按账号查找，" +
                    "name-按账号查找，nickname-按昵称查找，all-按账号或名称同时查找"),
            @ApiImplicitParam(name = "pageNum", required = true, paramType = "query", value = "当前页码"),
            @ApiImplicitParam(name = "pageSize", required = true, paramType = "query", value = "每页显示条数"),

    })
    @RequestMapping(method = RequestMethod.POST, value = "/page")
    public Result page(String name, String action, String permission, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.success("查询page成功");
        result.setData(userService.page(name, action, permission, pageNum, pageSize));
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

        User user = userService.getById(id);
        if (user != null) {
            result.success("获取成功");
            result.setData(user);
        } else {
            result.fail("获取失败");
        }
        return result;
    }

    //登录
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", required = true, paramType = "query", value = "账号"),
            @ApiImplicitParam(name = "password", required = true, paramType = "query", value = "密码")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Result login(String name, String password) {
        Result result = new Result();
        User user = userService.getByName(name);
        if (user != null) {
            if (!password.equals(user.getPassword())) {
                result.fail("密码错误");
            } else {
                result.success("登录成功");
                String token = JwtUtil.generateToken(user.getId());
                result.setData(token);
            }
        } else {
            result.fail("账号不存在");
        }
        return result;
    }

    //通过token查询
    @ApiOperation(value = "通过token查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", required = true, paramType = "query", value = "token")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/getByToken")
    public Result getByToken() {
        Result result = new Result();
        User user = userService.getByToken();
        if (user != null) {
            result.setMessage("获取成功");
            result.setData(user);
        }
        return result;
    }

}
