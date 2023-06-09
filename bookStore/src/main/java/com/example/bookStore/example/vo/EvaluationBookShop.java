package com.example.bookStore.example.vo;

import com.example.bookStore.example.entiey.Evaluation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "EvaluationBookShop实体",description = "评价书籍商店表")
public class EvaluationBookShop extends Evaluation {
    @ApiModelProperty(value = "书籍Id")
    private String bookId;
    @ApiModelProperty(value = "书名")
    private String bookName;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty(value = "书籍分类")
    private String category;
    @ApiModelProperty(value = "出版社")
    private String publisher;
    @ApiModelProperty(value = "书籍图片路径")
    private String bookImage;
    @ApiModelProperty(value = "店铺id")
    private String shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "店铺图片路径")
    private String shopImage;
    @ApiModelProperty(value = "单价")
    private double price;
    @ApiModelProperty(value = "数量")
    private int quantity;
    @ApiModelProperty(value = "总价")
    private double totalPrice;
}
