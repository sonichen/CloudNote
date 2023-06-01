package com.cyj.mapper;

import com.cyj.dto.content.ShortContentDto;
import com.cyj.dto.notebook.NoteBookDto;
import com.cyj.pojo.Categories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyj.pojo.Content;
import com.cyj.pojo.Menu;
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
public interface CategoriesMapper extends BaseMapper<Categories> {




    /**
     * 查询笔记本[组]的列表
     * @param userId
     * @param head
     * @param deleted
     * @return
     */
    List<NoteBookDto> queryNoteBookList(long userId,long head,long deleted);

    /**
     * 删除笔记本[组]的列表
     * @param categoriesId
     * @param id
     * @return
     */
    List<Long> deleteNoteBook(Long categoriesId, Long id);

    /**
     * 从回收站 删除笔记本[组]的列表
     * @param id
     * @return
     */
    int deleteFromRecycleBin(long id);

    /**
     * 从回收站 恢复笔记本[组]
     * @param id
     * @return
     */
    int back2System(long id);

    /**
     * 从回收站 查询笔记本[组]的列表
     * @param head
     * @return
     */
    List<Categories> queryDeletedNoteBook(long head);

    /**
     * 从回收站查询被删除的目录
     * @param id
     * @return
     */
    String queryDeletedCategoriesName(long id);

    List<Categories> selectNoteBooksInRecycleBin(String userId);


}
