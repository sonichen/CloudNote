package com.cyj.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ShareNoteDto {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "分享链接")
    private String link;

    @ApiModelProperty(value = "是否评论：0评论，1不评论")
    private Long allowComment;

    @ApiModelProperty(value = "是否加锁：0锁住，1不锁柱")
    private Long locked;
    @ApiModelProperty(value = "密码")
    private String password;



}
