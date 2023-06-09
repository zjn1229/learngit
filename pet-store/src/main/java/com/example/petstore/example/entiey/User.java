package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("user")
@ApiModel(value = "user", description = "用户表")
public class User extends BaseEntity {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "年龄")
    private int age;
    @ApiModelProperty(value = "电话")
    private String telephone;
    @ApiModelProperty(value = "email")
    private String email;
    @ApiModelProperty(value = "图片")
    private String image;
    @ApiModelProperty(value = "用户权限，0-用户，1-商家，2-管理员")
    private int permission;
    @ApiModelProperty(value = "权限是否可用")
    private Boolean useful;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}