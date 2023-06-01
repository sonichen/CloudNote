package com.cyj.dto;

import com.cyj.pojo.Tag;
import lombok.Data;

@Data
public class TagsDto {
    private Long id;
    private Long contentId;
    private String [] tags;
}
