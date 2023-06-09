package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("book")
@ApiModel(value = "Book实体", description = "书籍表")
public class Book extends BaseEntity {
    @ApiModelProperty(value = "书名")
    private String name;
    @ApiModelProperty(value = "价格")
    private Double price;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty(value = "出版社")
    private String publisher;
    @ApiModelProperty(value = "类别")
    private String category;
    @ApiModelProperty(value = "库存")
    private int stock;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "图片路径")
    private String image;
    @ApiModelProperty(value = "所属店铺id，关联shop表")
    private String shopId;
    @ApiModelProperty(value = "是否可用，0-不可用，1-可用")
    private Boolean useful;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
