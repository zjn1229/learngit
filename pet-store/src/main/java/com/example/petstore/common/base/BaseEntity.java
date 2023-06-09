package com.example.petstore.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础实体类
 * @author bill
 *
 */
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "UUID，主键")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField(exist = false)
    private Object etc;


    @SuppressWarnings("unchecked")
    public void put(String key, Object value) {
        if(etc == null) {
            etc = new HashMap<String, Object>();
        }
        ((Map<String, Object>) etc).put(key, value);
    }
}

