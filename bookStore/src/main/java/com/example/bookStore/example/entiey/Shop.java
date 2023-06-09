package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("shop")
@ApiModel(value = "Shop实体", description = "商店表")
public class Shop extends BaseEntity {
    @ApiModelProperty(value = "店铺名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "店铺头像路径")
    private String image;
    @ApiModelProperty(value = "所属管理员id，关联user表")
    private String administratorId;
    @ApiModelProperty(value = "是否可用，0-不可用，1-可用")
    private Boolean useful;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
