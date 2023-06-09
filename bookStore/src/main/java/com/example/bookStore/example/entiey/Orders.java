package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("orders")
@ApiModel(value = "Orders实体", description = "订单表")
public class Orders extends BaseEntity{
    @ApiModelProperty(value = "所属用户id")
    private String userId;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
