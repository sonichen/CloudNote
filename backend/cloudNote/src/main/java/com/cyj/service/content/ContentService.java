package com.cyj.service.content;

import com.cyj.dto.content.AddContentDto;
import com.cyj.dto.content.ContentDto;
import com.cyj.dto.content.TemplateDto;
import com.cyj.pojo.Content;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.utils.response.JsonObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
public interface ContentService extends IService<Content> {


    int addUploadNote(MultipartFile file, Long categoriesId) throws Exception;

    /**
     * 更新笔记
     * @param id
     * @param categoriesId
     * @return
     */
    int updateCategories(long  id, long categoriesId);

    /**
     * 查询笔记列表
     * @param
     * @param categoriesId
     * @param deleted
     * @return
     */
    JsonObject<ContentDto>queryNoteList( long categoriesId,long deleted);

    JsonObject<Content>queryContentsByKeyword( String keyword);
    AddContentDto queryContent( long categoriesId);

    /**
     * 笔记分页返回
     * @param page
     * @param pageCount
     * @return
     */
    JsonObject<Content> findListByPage(Integer page, Integer pageCount);

    /**
     * 查询加星笔记列表
     * @param
     * @return
     */
    JsonObject<ContentDto> queryStarNoteList();

    /**
     * 从回收站 恢复笔记
     * @param id
     * @return
     */
    int back2System(long id);

    /**
     * 从回收站 删除笔记
     * @param id
     * @return
     */
    int deleteFromRecycleBin(long id);

 /**
     * 添加
     *
     * @param content 
     * @return int
     */
    int add(AddContentDto content) throws Exception;

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
     * @param content 
     * @return int
     */
    int updateData(AddContentDto content);

    /**
     * id查询数据
     *
     * @param id id
     * @return Content
     */
    ContentDto findById(Long id);

    /**
     * 通过标签查询文章列表
     * @param tagId
     * @return
     */
    JsonObject<ContentDto> queryContentsByTag(String tagsName);

    JsonObject<ContentDto> queryAllContentListInRecycleBin( );

    /**
     * 模板
     */
    public int addTemplate(TemplateDto templateDto);
    public int deleteTemplate(long id);
    public  int updateTemplate(TemplateDto templateDto);
    JsonObject<TemplateDto> querySystemTemplate();
    JsonObject<TemplateDto>queryMyTemplate();
    public TemplateDto findTemplateById(Long id);
    public Content findOnlyById(Long id);
}
