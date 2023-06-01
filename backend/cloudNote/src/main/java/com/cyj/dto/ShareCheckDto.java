package com.cyj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class ShareCheckDto {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "分享链接")
    private String link;
    @ApiModelProperty(value = "二维码")
    private String qrCode;
    @ApiModelProperty(value = "是否评论：0评论，1不评论")
    private Long allowComment;
    @ApiModelProperty(value = "是否加锁：0锁住，1不锁柱")
    private Long locked;
    @ApiModelProperty(value = "密码")
    private String password;

    private String title;
    private String desc;
    private String image_url;
}
