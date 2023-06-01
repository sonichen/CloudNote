package com.cyj.mapper;

import com.cyj.dto.content.ContentDto;
import com.cyj.pojo.Content;
import com.cyj.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jdk.jfr.Registered;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 查询标签列表
     * @param contentId
     * @return
     */
    List<Tag> queryTagsList( long contentId);

    /**
     * 通过标签id查询带有该标签的笔记
     * @param tagId
     * @return
     */
    List<Tag>queryContentsByTag(long tagId);


}
