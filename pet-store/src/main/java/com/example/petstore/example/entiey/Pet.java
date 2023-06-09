package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("pet")
@ApiModel(value = "O", description = "宠物表")
public class Pet extends BaseEntity{
    @ApiModelProperty(value = "宠物名称")
    private String name;
    @ApiModelProperty(value = "宠物单价")
    private Double price;
    @ApiModelProperty(value = "宠物品种")
    private String category;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "图片")
    private String image;
    @ApiModelProperty(value = "商店id")
    private String shopId;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}