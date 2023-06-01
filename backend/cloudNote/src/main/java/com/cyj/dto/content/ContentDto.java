package com.cyj.dto.content;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "模式")
    private String mode;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "是否收藏：0不收藏1收藏")
    private Long star;
    @ApiModelProperty(value = "分组名字")
    private String categoriesName;
}
