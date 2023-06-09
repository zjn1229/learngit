package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("shop")
@ApiModel(value = "O", description = "商店表")
public class Shop extends BaseEntity {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "商店名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "图片")
    private String image;
    @ApiModelProperty(value = "商店位置，经度")
    private String longitude;
    @ApiModelProperty(value = "商店位置，纬度")
    private String latitude;
    @ApiModelProperty(value = "商店id")
    private String ownerId;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
