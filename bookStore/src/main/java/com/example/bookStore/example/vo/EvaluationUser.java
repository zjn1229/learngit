package com.example.bookStore.example.vo;

import com.example.bookStore.example.entiey.Evaluation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "EvaluationBookShop实体",description = "评价书籍商店表")
public class EvaluationUser extends Evaluation {
    @ApiModelProperty(value = "用户昵称")
    private String userNickname;
    @ApiModelProperty(value = "用户头像路径")
    private String userImage;
}
