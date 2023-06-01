package com.cyj.dto.notebook;

import com.cyj.pojo.Categories;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteBookDto {
    @ApiModelProperty(value = "id")
    private long id;
    @ApiModelProperty(value = "所属分组")
    private long head;
    @ApiModelProperty(value = "分组名字")
    private String value;
//    List<Categories> children;
}
