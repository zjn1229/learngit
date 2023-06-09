package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("video")
@ApiModel(value = "商品分类", description = "分类表")
public class Video extends BaseEntity {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "视频地址")
    private String video;
    @ApiModelProperty(value = "商品id，与pet、pet_product关联")
    private String goodsId;
    @ApiModelProperty(value = "商品id，与pet、pet_product关联")
    private String shopId;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
