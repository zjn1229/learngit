package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("cart")
@ApiModel(value = "Cart实体", description = "购物车表")
public class Cart extends BaseEntity{
    @ApiModelProperty(value = "所属用户id，关联user表")
    private String userId;
    @ApiModelProperty(value = "商品id，关联pet、pet_product表")
    private String goodsId;
    @ApiModelProperty(value = "商品类型，0-宠物，1-宠物周边")
    private int goodsTpye;
    @ApiModelProperty(value = "商品数量")
    private int quantity;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
