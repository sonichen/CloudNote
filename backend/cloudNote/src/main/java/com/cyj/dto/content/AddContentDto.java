package com.cyj.dto.content;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class AddContentDto {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "模式")
    private String mode;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "是否收藏：0不收藏1收藏")
    private Long star;
    @ApiModelProperty(value = "分组id")
    private Long categoriesId;
//    private Long userId;

}
