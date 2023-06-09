package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("orders_item")
@ApiModel(value = "OrdersItem实体", description = "订单项目表")
public class OrdersItem extends BaseEntity{
    @ApiModelProperty(value = "商品id")
    private String bookId;
    @ApiModelProperty(value = "所属用户id")
    private String userId;
    @ApiModelProperty(value = "所属订单id")
    private String ordersId;
    @ApiModelProperty(value = "商品单价")
    private double price;
    @ApiModelProperty(value = "商品数量")
    private int quantity;
    @ApiModelProperty(value = "商品总价")
    private double totalPrice;
    @ApiModelProperty(value = "收货人姓名")
    private String name;
    @ApiModelProperty(value = "收货人电话")
    private String telephone;
    @ApiModelProperty(value = "收货地址")
    private String address;
    @ApiModelProperty(value = "订单状态，0-已下单（待支付），1-已支付（待发货），2-已发货（待收货），3-已收货（待评价），4-已评价，-1-取消订单，-2-申请退单，-3-管理员审核通过，允许退单，退单成功（结束），-4-管理员直接退单")
    private int status;
    @ApiModelProperty(value = "申请退款前的订单状态")
    private int beforeStatus;
    @ApiModelProperty(value = "退单理由")
    private String reason;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
    @ApiModelProperty(value = "更新时间 YYYY-MM-DD hh:mm:ss")
    private String updateTime;
}
