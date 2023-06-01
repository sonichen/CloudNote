package com.cyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.naming.ldap.LdapName;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tag对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "tag名字")
    @TableField("tagsName")
    private String tagsName;

    @ApiModelProperty(value = "文章id")
    @TableField("contentId")
    private Long contentId;
    @TableField("userId")
    private Long userId;

    public Tag(String tagsName, Long contentId) {
        this.tagsName = tagsName;
        this.contentId = contentId;
    }
}
