package com.cyj.service.categories;

import com.cyj.dto.notebook.AddNoteBookDto;
import com.cyj.dto.notebook.AddNoteBookDto1;
import com.cyj.dto.notebook.NoteBookDto;
import com.cyj.pojo.Categories;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.pojo.Menu;
import com.cyj.utils.response.JsonObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
public interface CategoriesService extends IService<Categories> {

    /**
     * 查询笔记结构
     * @return
     */
    public JsonObject<Menu> findNoteBookList();
    public JsonObject<Menu> findNoteBookListInRecycleBin();
    /**
     * 查询笔记本列表
     * @param
     * @param head
     * @param deleted
     * @return
     */
    JsonObject<NoteBookDto> queryNoteBookList( long head,long deleted);


    /**
     * 添加
     *
     * @param
     * @return int
     */
    int add(AddNoteBookDto noteBookDto);

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
     * @param
     * @return int
     */
    int updateData(AddNoteBookDto noteBookDto);
    int updateName(AddNoteBookDto1 noteBookDto);
    /**
     * id查询数据
     *
     * @param id id
     * @return Categories
     */
    NoteBookDto findById(Long id);

    /**
     * 删除笔记本
     * @param id
     * @return
     */
    int deleteNoteBook(Long id);

    /**
     * 从回收站删除笔记本
     * @param id
     * @return
     */
    int deleteFromRecycleBin(long id);

    /**
     * 从回收站恢复笔记本
     * @param id
     * @return
     */
    int back2System(long id);


}
