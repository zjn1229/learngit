package com.example.petstore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petstore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("orders")
@ApiModel(value = "Orders实体", description = "订单表")
public class Orders extends BaseEntity{
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "商品id")
    private String goodsId;
    @ApiModelProperty(value = "所属用户id")
    private String userId;
    @ApiModelProperty(value = "商品单价")
    private Double price;
    @ApiModelProperty(value = "商品数量")
    private int quantity;
    @ApiModelProperty(value = "商品总价格")
    private Double totalPrice;
    @ApiModelProperty(value = "收货人姓名")
    private String consigneeName;
    @ApiModelProperty(value = "电话")
    private String telephone;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "订单状态，0-待支付，1-待发货，2-待收货，3-待评价，4-已完成，-1-已取消，-2-申请退款，-3-商家直接退款，-4-退款成功")
    private int status;
    @ApiModelProperty(value = "记录上次的订单状态")
    private int preStatus;
    @ApiModelProperty(value = "原因")
    private String reason;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
    @ApiModelProperty(value = "更新时间 YYYY-MM-DD hh:mm:ss")
    private String updateTime;
}
