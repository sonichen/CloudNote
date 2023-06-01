package com.cyj.mapper;

import com.cyj.dto.content.AddContentDto;
import com.cyj.dto.content.ContentDto;
import com.cyj.dto.content.TemplateDto;
import com.cyj.pojo.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@Repository
public interface ContentMapper extends BaseMapper<Content> {
    List<Content> findContentsByCategoriesId(long userId);
    /**
     * 添加笔记
     * @param userId
     * @param categoriesId
     * @return
     */
    AddContentDto queryContent(long userId, long categoriesId);

    /**
     * 查询笔记列表
     * @param categoriesId
     * @param deleted
     * @return
     */
    List<ContentDto> queryNoteList(long categoriesId,long deleted);

    /**
     * 查询加星的笔记
     * @param userId
     * @return
     */
    List<Content> queryStarNoteList(long userId);

    /**
     * 移动文章位置
     * @param id
     * @param categoriesId
     * @return
     */
    int updateCategories(long  id, long categoriesId);

    /**
     * 从回收站回复文章
     * @param id
     * @return
     */
    int back2System(long id);

    /**
     * 从回收站删除文章
     * @param id
     * @return
     */
    int deleteFromRecycleBin(long id);
    List<Content> queryAllContentListInRecycleBin( Long userId);
    /**
     * 模板
     *
     */
    public int deleteTemplate(long id);
    List<TemplateDto> queryTemplate(Long userId);
    public TemplateDto findTemplateById(Long id);
    public Content findOnlyById(Long id);

    List<Content>findNotesInRB(String userId,String deleted);

}
