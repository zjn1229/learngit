package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("category")
@ApiModel(value = "Book实体", description = "分类表")
public class Category extends BaseEntity {
    @ApiModelProperty(value = "类名")
    private String name;

}
