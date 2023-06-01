package com.cyj.service.tag;

import com.cyj.dto.TagsDto;
import com.cyj.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.utils.response.JsonObject;
import com.cyj.utils.response.R;
import jdk.nashorn.internal.scripts.JO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
public interface TagService extends IService<Tag> {
    JsonObject<Tag> queryUsersTagsList( );
    /**
     * 标签列表
     * @param contentId
     * @return
     */
    JsonObject<Tag> queryTagsList(long contentId);

    /**
     * 添加
     *
     * @param tag 
     * @return int
     */
    int add(Tag tag);
    int   addTags(TagsDto tags);
    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param tag 
     * @return int
     */
    int updateData(Tag tag);

    /**
     * id查询数据
     *
     * @param id id
     * @return Tag
     */
    Tag findById(Long id);
}
