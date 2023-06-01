package com.cyj.dto.notebook;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNoteBookDto1 {
    @ApiModelProperty(value = "id")
    private long id;
    @ApiModelProperty(value = "分组名字")
    private String value;
}
