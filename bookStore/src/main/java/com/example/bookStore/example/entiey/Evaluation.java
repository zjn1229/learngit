package com.example.bookStore.example.entiey;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.bookStore.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("evaluation")
@ApiModel(value = "Orders实体", description = "评价表")
public class Evaluation extends BaseEntity {
    @ApiModelProperty(value = "所属用户id")
    private String userId;
    @ApiModelProperty(value = "所属订单项目id")
    private String ordersItemId;
    @ApiModelProperty(value = "评分，0.0-5.0")
    private Double score;
    @ApiModelProperty(value = "评价")
    private String evaluation;
    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;
}
