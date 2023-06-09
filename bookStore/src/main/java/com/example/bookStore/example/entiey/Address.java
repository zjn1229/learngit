package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("address")
@ApiModel(value = "Address实体", description = "地址表")
public class Address extends BaseEntity {
    @ApiModelProperty(value = "收货人姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String tel;
    @ApiModelProperty(value = "所属用户id，关联user表")
    private String userId;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "区")
    private String county;
    @ApiModelProperty(value = "城市编码")
    private String areaCode;
    @ApiModelProperty(value = "详细地址")
    private String addressDetail;
    @ApiModelProperty(value = "是否设为默认地址，0-默认，1-非默认")
    private Boolean isDefault;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
