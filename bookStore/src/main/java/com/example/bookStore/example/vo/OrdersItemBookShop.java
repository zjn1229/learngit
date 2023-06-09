package com.example.bookStore.example.vo;

import com.example.bookStore.example.entiey.OrdersItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "OrdersItemBookShop实体",description = "订单项目书籍商店表")
public class OrdersItemBookShop extends OrdersItem {
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
}
