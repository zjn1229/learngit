package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("cart")
@ApiModel(value = "Cart实体", description = "购物车表")
public class Cart extends BaseEntity{
    @ApiModelProperty(value = "所属用户id，关联user表")
    private String userId;
    @ApiModelProperty(value = "书籍id，关联book表")
    private String bookId;
    @ApiModelProperty(value = "商品数量")
    private int quantity;
    @ApiModelProperty(value = "是否可用，0-不可用，1-可用")
    private Boolean useful;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
