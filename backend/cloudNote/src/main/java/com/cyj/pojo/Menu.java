package com.cyj.pojo;

import com.cyj.dto.content.ContentDto;
import com.cyj.dto.content.ShortContentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "目录实体")
public class Menu {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("上级ID")
    private String head;
    @ApiModelProperty("下级集合")
    private List<Menu> children;
    @ApiModelProperty("笔记合集")
    private List<ContentDto> contentList;

    public Menu(String id,String name) {
        this.id=id;
        this.name = name;
    }
}