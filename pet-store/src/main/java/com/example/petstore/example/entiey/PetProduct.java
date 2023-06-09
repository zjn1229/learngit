package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("pet_product")
@ApiModel(value = "O", description = "商品表")
public class PetProduct extends BaseEntity {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "商品单价")
    private Double price;
    @ApiModelProperty(value = "库存")
    private int stock;
    @ApiModelProperty(value = "商店id")
    private String shopId;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "图片")
    private String image;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}