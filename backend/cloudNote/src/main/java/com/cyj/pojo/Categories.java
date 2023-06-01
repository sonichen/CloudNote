package com.cyj.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Categories对象", description="")
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("userId")
    private Long userId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "所属")
    private Long head;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Long deleted;

//    List<Categories> children;

}
