package com.cyj.service.categories.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.dto.notebook.AddNoteBookDto;
import com.cyj.dto.notebook.AddNoteBookDto1;
import com.cyj.dto.notebook.NoteBookDto;
import com.cyj.dto.content.ContentDto;
import com.cyj.mapper.ContentMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Categories;
import com.cyj.mapper.CategoriesMapper;
import com.cyj.pojo.Content;
import com.cyj.pojo.Menu;
import com.cyj.service.categories.CategoriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.service.content.ContentService;
import com.cyj.utils.response.JsonObject;
import com.cyj.utils.secret.TokenUtil;
import com.cyj.utils.tree.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements CategoriesService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    CategoriesMapper categoriesMapper;
    @Autowired
    ContentMapper contentMapper;


    @Autowired
    ContentService contentService;
    /**
     * 查询笔记结构
     * @return
     */
    @Override
    public JsonObject<Menu> findNoteBookList() {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        List<Categories> categories=categoriesMapper.selectList(wrapper);
        List<Menu> menus=new ArrayList<>();
        for(int i=0;i<categories.size();i++){
            Categories categories1=categories.get(i);
            JsonObject<ContentDto> jsonObjectContent=  contentService.queryNoteList(categories1.getId(),0l);
            List<ContentDto> dtoList=jsonObjectContent.getData();

            menus.add(new Menu(categories1.getId()+"",categories1.getValue(),categories1.getHead()+"",null,dtoList));
        }
        List<Menu> menuList=new MenuTree(menus).buildTree();
        JsonObject<Menu> jsonObject=new JsonObject();
        jsonObject.setCount((long) menuList.size());
        jsonObject.setData(menuList);
        return jsonObject;
    }

    @Override
    public JsonObject<Menu> findNoteBookListInRecycleBin() {
        List<Categories> categories=categoriesMapper.selectNoteBooksInRecycleBin(TokenUtil.getTokenUserId());
        List<Menu> menus=new ArrayList<>();
        for(int i=0;i<categories.size();i++){
            Categories categories1=categories.get(i);
            JsonObject<ContentDto> jsonObjectContent=  contentService.queryNoteList(categories1.getId(),1l);
            List<ContentDto> dtoList=jsonObjectContent.getData();
            menus.add(new Menu(categories1.getId()+"",categories1.getValue(),categories1.getHead()+"",null,dtoList));
        }
        List<Menu> menuList=new MenuTree(menus).buildTree();
        List<Content>contents=contentMapper.findNotesInRB(TokenUtil.getTokenUserId(),"1");
        System.out.println("查询menuList="+menuList);
        System.out.println("查询结果="+contents);
        if(menuList.size()==0){
            for(int i=0;i<contents.size();i++){
                menuList.add(new Menu(contents.get(i).getId()+"",contents.get(i).getTitle()));
            }
        }
        System.out.println("查询结果111="+menuList);
        JsonObject<Menu> jsonObject=new JsonObject();
        jsonObject.setCount((long) menuList.size());
        jsonObject.setData(menuList);
        return jsonObject;
    }


    /**
     * 添加笔记本
     * @param noteBookDto
     * @return
     */
    @Override
    public int add(AddNoteBookDto noteBookDto){
        Categories categories=new Categories();
        categories.setDeleted(0l);
        categories.setHead(noteBookDto.getHead());
        categories.setValue(noteBookDto.getValue());
        categories.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        return baseMapper.insert(categories);
    }

    /**
     * 删除笔记本
     * @param id 主键
     * @return
     */
    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    /**
     * 更新笔记本信息
     * @param noteBookDto
     * @return
     */
    @Override
    public int updateData(AddNoteBookDto noteBookDto){
        Categories categories=new Categories();
        categories.setId((long)noteBookDto.getId());
        categories.setHead(noteBookDto.getHead());
        categories.setValue(noteBookDto.getValue());
        categories.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        return baseMapper.updateById(categories);
    }

    @Override
    public int updateName(AddNoteBookDto1 noteBookDto) {
        Categories categories=new Categories();
        categories.setId((long)noteBookDto.getId());
        categories.setValue(noteBookDto.getValue());
        return baseMapper.updateById(categories);
    }


    /**
     * 根据ID查询笔记本
     * @param id id
     * @return
     */
    @Override
    public NoteBookDto findById(Long id){
        Categories categories=  baseMapper.selectById(id);
        NoteBookDto noteBookDto=new NoteBookDto();
        noteBookDto.setId(categories.getId());
        noteBookDto.setHead(categories.getHead());
        noteBookDto.setValue(categories.getValue());
        return noteBookDto;
    }

    /**
     * 删除笔记本组
     * @param id
     * @return
     */
    @Override
    public int deleteNoteBook( Long id) {
      try{
          deleteNoteBooks(id);
          baseMapper.deleteById(id);
          deleteNoteList(id,0);
          return 1;
      }catch (Exception e){
          return 0;
      }
    }
    public int deleteNoteBooks( Long id) {
       try{
           QueryWrapper wrapper=new QueryWrapper();
           wrapper.eq("head",id);
           List<Categories> list= baseMapper.selectList(wrapper);
           int count=0;
           while (list!=null&&count<list.size()){
               long currentId=list.get(count).getId();
               baseMapper.deleteById(currentId);
               deleteNoteList(currentId,0);
               deleteNoteBooks(list.get(count++).getId());
           }
           return 1;
       }catch (Exception e){
           return 0;
       }
    }

    public List<ContentDto> deleteNoteList( long categoriesId, long deleted) {
        List<ContentDto> dtoList=  contentMapper.queryNoteList(categoriesId,deleted);
        System.out.println("list="+dtoList);
        for(int i=0;i<dtoList.size();i++){
            contentMapper.deleteById(dtoList.get(i).getId());
        }
        return dtoList;
    }
    public List<ContentDto> deleteNoteListFromRecycleBin( long categoriesId, long deleted) {
        List<ContentDto> dtoList=  contentMapper.queryNoteList(categoriesId,deleted);
        System.out.println("list="+dtoList);
        for(int i=0;i<dtoList.size();i++){
            contentMapper.deleteFromRecycleBin(dtoList.get(i).getId());
        }
        return dtoList;
    }
    public List<ContentDto> notesBackToSystemFromRecycleBin( long categoriesId, long deleted) {
        List<ContentDto> dtoList=  contentMapper.queryNoteList(categoriesId,deleted);
        System.out.println("list="+dtoList);
        for(int i=0;i<dtoList.size();i++){
            contentMapper.back2System(dtoList.get(i).getId());
        }
        return dtoList;
    }

    /**
     * 从回收站删除笔记本
     * @param id
     * @return
     */
    @Override
    public int deleteFromRecycleBin( long id) {
        dealWithNoteBooksRecycleBin(id);
        categoriesMapper.deleteFromRecycleBin(id);
        deleteNoteListFromRecycleBin(id,1);
        return 0;
    }

    public int dealWithNoteBooksRecycleBin( long id) {
        System.out.println("要删除"+id);
        List<Categories> list= baseMapper.queryDeletedNoteBook(id);
        System.out.println("list="+list);
        int count=0;
        while (list!=null&&count<list.size()){
            long currentId=list.get(count).getId();
            baseMapper.deleteFromRecycleBin(currentId);
            deleteNoteListFromRecycleBin(currentId,1);
            dealWithNoteBooksRecycleBin(list.get(count++).getId());
        }
        return 0;

    }

    /**
     * 从回收站恢复笔记本
     * @param id
     * @return
     */
    @Override
    public int back2System(long id) {
        back2Systems(id);
        categoriesMapper.back2System(id);
        notesBackToSystemFromRecycleBin(id,1);
        return 0;
    }


    public int back2Systems( long id) {
        System.out.println("id="+id);
        List<Categories> list= baseMapper.queryDeletedNoteBook(id);
        int count=0;
        while (list!=null&&count<list.size()){
            long currentId=list.get(count).getId();
            categoriesMapper.back2System(currentId);
            notesBackToSystemFromRecycleBin(currentId,1);
            back2Systems(list.get(count++).getId());
        }
        return 0;

    }




    /**
     * 回收站查询笔记本列表
     * @param
     * @param head
     * @param deleted
     * @return
     */
    @Override
    public JsonObject<NoteBookDto> queryNoteBookList( long head,long deleted) {
        JsonObject<NoteBookDto> jsonObject =new JsonObject<>();
        List<NoteBookDto> dtoList=  categoriesMapper.queryNoteBookList(Long.valueOf(TokenUtil.getTokenUserId()),head,deleted);
        jsonObject.setData(dtoList);
        jsonObject.setCount((long)dtoList.size());
        return jsonObject;
    }

}
