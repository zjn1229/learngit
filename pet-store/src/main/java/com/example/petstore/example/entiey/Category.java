package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("category")
@ApiModel(value = "商品分类", description = "分类表")
public class Category extends BaseEntity {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "类名")
    private String name;
}