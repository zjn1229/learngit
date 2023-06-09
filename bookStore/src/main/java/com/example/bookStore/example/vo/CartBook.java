package com.example.bookStore.example.vo;

import com.example.bookStore.example.entiey.Cart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CartBook实体",description = "购物车书籍表")
public class CartBook extends Cart {
    @ApiModelProperty(value = "书名")
    private String name;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty(value = "单价")
    private String price;
    @ApiModelProperty(value = "图片路径")
    private String image;
}
