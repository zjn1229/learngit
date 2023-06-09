package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("user")
@ApiModel(value = "User实体", description = "用户表")
public class User extends BaseEntity{
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "年龄")
    private int age;
    @ApiModelProperty(value = "电话")
    private String telephone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "用户头像路径")
    private String image;
    @ApiModelProperty(value = "用户权限")
    private int permission;
    @ApiModelProperty(value = "是否可用，0-不可用，1-可用")
    private Boolean useful;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
