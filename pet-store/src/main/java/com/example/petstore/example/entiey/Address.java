package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("address")
@ApiModel(value = "Address实体", description = "地址表")
public class Address extends BaseEntity {
    @ApiModelProperty(value = "所属用户id，关联user表")
    private String userId;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "电话")
    private String tel;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "国家")
    private String county;
    @ApiModelProperty(value = "地区编号")
    private String areaCode;
    @ApiModelProperty(value = "详细地址")
    private String address_detail;
    @ApiModelProperty(value = "是否为默认地址，0-否，1-是")
    private Boolean isDefault;
    @ApiModelProperty(value = "创建时间")
    private String createTime;

}