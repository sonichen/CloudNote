package com.cyj.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CatalogClassifyVO  {
    private String id;

    private String name;

    private String parentId;

    public List<CatalogClassifyVO> getChildren() {
        return children;
    }

    public void setChildren(List<CatalogClassifyVO> children) {
        if (children!= null && !children.isEmpty()) {
            //相应的业务逻辑
            this.children=children;
        }
    }

    //子分类
    private List<CatalogClassifyVO> children;
}
