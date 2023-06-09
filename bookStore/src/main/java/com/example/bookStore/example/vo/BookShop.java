package com.example.bookStore.example.vo;

import com.example.bookStore.example.entiey.Book;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BookShop实体",description = "书籍商店表")
public class BookShop extends Book {
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "店铺图片路径")
    private String shopImage;
}
