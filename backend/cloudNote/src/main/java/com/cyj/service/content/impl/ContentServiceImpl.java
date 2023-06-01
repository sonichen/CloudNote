package com.cyj.service.content.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.dto.content.AddContentDto;
import com.cyj.dto.content.ContentDto;
import com.cyj.dto.content.TemplateDto;
import com.cyj.mapper.*;
import com.cyj.pojo.Content;
import com.cyj.pojo.History;
import com.cyj.pojo.Tag;
import com.cyj.service.content.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.service.ipfs.IPFSService;
import com.cyj.utils.documents.GetPDFContent;
import com.cyj.utils.documents.GetWordContent;
import com.cyj.utils.ipfs.IPFSUtils;
import com.cyj.utils.response.JsonObject;
import com.cyj.utils.secret.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

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
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CategoriesMapper categoriesMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    HistoryMapper historyMapper;

    @Autowired
    IPFSService ipfsService;





    /**
     * 添加笔记
     * @param content
     * @return
     * @throws Exception
     */
    @Override
    public int add(AddContentDto content) throws Exception {
        Content content1=new Content();
        content1.setCategoriesId(content.getCategoriesId());
        content1.setStar(content.getStar());
        content1.setTitle(content.getTitle());
        content1.setMode(content.getMode());
        content1.setType("note");
        content1.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        String hash=ipfsService.saveFile2(content.getContent());
        content1.setContent(hash);
        int result= baseMapper.insert(content1);
        if(result==0){
            throw new Exception("添加失败");
        }
        QueryWrapper<Content> contentQueryWrapper=new QueryWrapper<>();
        contentQueryWrapper.eq("title",content.getTitle()).
                eq("content",hash)
                .eq("type","note")
        .eq("userId",Long.valueOf(Long.valueOf(TokenUtil.getTokenUserId())))
       .eq("categoriesId",content.getCategoriesId());
        Content content2=baseMapper.selectOne(contentQueryWrapper);
        System.out.println("content2="+content2);
        long contentId=content2.getId();
        /**
         * 增加历史版本
         */
        History history=new History();
        history.setVersionId(1l);
        history.setTitle(content.getTitle());
        history.setContent(hash);
        history.setContentId(contentId);
        history.setMode(content.getMode());
        result= historyMapper.insert(history);
        return result;
    }

    /**
     * 添加上传的笔记
     * @param file
     * @param categoriesId
     * @return
     * @throws Exception
     */
    @Override
    public int addUploadNote(MultipartFile file, Long categoriesId) throws Exception {
        String title = file.getOriginalFilename();

        Content contentDto = new Content();
        contentDto.setTitle(title);
        contentDto.setCategoriesId(categoriesId);

        contentDto.setContent(ipfsService.saveFile(file));
        contentDto.setType("note");
        contentDto.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        if (title.contains(".pdf")) {
            contentDto.setContent(ipfsService.saveFile2(GetPDFContent.getPDFContent(file)));
            contentDto.setMode("1");
        } else if (title.contains(".docx") ) {
            contentDto.setContent(ipfsService.saveFile2(GetWordContent.importWord(file)));
            contentDto.setMode("1");
        }else if (title.contains(".md")){
            contentDto.setMode("2");
        }else {
            contentDto.setMode("1");
        }


        System.out.println(contentDto);
        int result= baseMapper.insert(contentDto);
        if(result==0){
            throw new Exception("添加失败");
        }
        QueryWrapper<Content> contentQueryWrapper=new QueryWrapper<>();
        contentQueryWrapper.eq("title",contentDto.getTitle()).
                eq("content",contentDto.getContent())
                .eq("type","note")
                .eq("userId",Long.valueOf(Long.valueOf(TokenUtil.getTokenUserId())))
                .eq("categoriesId",categoriesId);
        Content content2=baseMapper.selectOne(contentQueryWrapper);
        System.out.println("content2="+content2);
        long contentId=content2.getId();
        /**
         * 增加历史版本
         */
        History history=new History();
        history.setVersionId(1l);
        history.setTitle(contentDto.getTitle());
        history.setContent(contentDto.getContent());
        history.setContentId(contentId);
        history.setMode(contentDto.getMode());
        historyMapper.insert(history);
        return result;
    }

    /**
     * 删除笔记
     * @param id 主键
     * @return
     */
    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    /**
     * 更新笔记
     * @param content
     * @return
     */
    @Override
    public int updateData(AddContentDto content){
        Content content1=baseMapper.selectById(content.getId());
//        System.out.println("content="+content.getContent());
        if(content.getContent()==null){
            content1.setStar(content.getStar());
           return   baseMapper.updateById(content1);
        }
        String hash=ipfsService.saveFile2(content.getContent());

        content1.setCategoriesId(content.getCategoriesId());
        content1.setMode(content.getMode());
        content1.setStar(content.getStar());
        content1.setTitle(content.getTitle());
        content1.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        content1.setContent(hash);
        /**
         * 增加历史版本
         */
        History history=new History();
        history.setTitle(content.getTitle());
        history.setContent(hash);
        history.setMode(content.getMode());
        history.setContentId(content.getId());
        long newVersionId=content1.getVersion()+1;
        history.setVersionId(newVersionId);
        historyMapper.insert(history);
        return baseMapper.updateById(content1);
    }

    /**
     * 通过笔记id查询笔记
     * @param id id
     * @return
     */
    @Override
    public ContentDto findById(Long id){
        Content content=  baseMapper.selectById(id);
        ContentDto contentDto=new ContentDto();
        contentDto.setId(id);
        contentDto.setMode(content.getMode());
        System.out.println("findById---content="+content.getContent());
        contentDto.setContent(IPFSUtils.download(content.getContent()));
        System.out.println("findById---content1="+contentDto.getContent());
        contentDto.setTitle(content.getTitle());
        contentDto.setCreateTime(content.getCreateTime());
        contentDto.setUpdateTime(content.getUpdateTime());
        contentDto.setStar(content.getStar());
        //查询作者
        String username=userMapper.selectById(content.getUserId()).getUsername();
        //查询分类名字
        String categoriesName=categoriesMapper.selectById(content.getCategoriesId()).getValue();
        contentDto.setUsername(username);
        contentDto.setCategoriesName(categoriesName);
        return contentDto;
    }

    public Content findOnlyById(Long id){
        return contentMapper.findOnlyById(id);
    }
//收藏管理
    /**
     * 查询收藏的笔记
     * @return
     */
    public JsonObject<ContentDto> queryStarNoteList() {
        JsonObject<ContentDto> jsonObject =new JsonObject<>();
        List<Content> dtoList=  contentMapper.queryStarNoteList(Long.valueOf(TokenUtil.getTokenUserId()));
        List<ContentDto> result=new ArrayList<>();
        System.out.println("dtoList="+dtoList);
        if(dtoList==null){
            return null;
        }
        String username=userMapper.selectById(Long.valueOf(TokenUtil.getTokenUserId())).getUsername();

        for(int i=0;i<dtoList.size();i++){
            Content content=dtoList.get(i);
//            String categoriesName=categoriesMapper.selectById(content.getId()).getValue();
            result.add(new ContentDto(content.getId(),content.getTitle(),content.getMode(),
                    IPFSUtils.download(content.getContent()),content.getCreateTime(),content.getUpdateTime(),
                    username,content.getStar(),null));
            content.setContent(IPFSUtils.download(dtoList.get(i).getContent()));
        }
        jsonObject.setData(result);
        jsonObject.setCount((long)dtoList.size());
        return jsonObject;
    }

//    标签管理
    /**
     * 通过标签查询文章列表

     * @return
     */
    public JsonObject<ContentDto> queryContentsByTag(String tagsName) {
        //根据名字查询
        QueryWrapper <Tag>wrapper=new QueryWrapper();
        wrapper.eq("tagsName",tagsName);
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        List<Tag> findByNames=tagMapper.selectList(wrapper);
        System.out.println("根据名字查询="+findByNames);
        List<ContentDto>contentDtos=new ArrayList<>();
        for(int i=0;i<findByNames.size();i++){
//            Content content=contentMapper.selectById(findByNames.get(i).getContentId());
//            contents.add(content);
            ContentDto content=findById(findByNames.get(i).getContentId());
            contentDtos.add(content);
        }
        System.out.println("contentDto="+contentDtos);
        JsonObject<ContentDto> jsonObject =new JsonObject<>();
//        List<Tag> tagList=  tagMapper.queryContentsByTag(tagId);
//        List<ContentDto> contentDtos=new ArrayList<>();
//        for(int i=0;i<contents.size();i++){
//            ContentDto contentDto= findById(contents.get(i).getContentId());
//            contentDto.setContent(IPFSUtils.download(contentDto.getContent()));
//            contentDtos.add(contentDto);
//        }
        jsonObject.setData(contentDtos);
        jsonObject.setCount((long)contentDtos.size());
        return jsonObject;
    }
    //回收站管理

    /**
     * 查询回收站的笔记
     * @return
     */
    @Override
    public JsonObject<ContentDto> queryAllContentListInRecycleBin() {
        JsonObject <ContentDto>jsonObject=new JsonObject<>();
        List <Content> list=contentMapper.queryAllContentListInRecycleBin(Long.valueOf(TokenUtil.getTokenUserId()));
        List<ContentDto> result=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Content temp=list.get(i);
            result.add(new ContentDto(temp.getId(),temp.getTitle(),temp.getMode(),IPFSUtils.download(temp.getContent()),temp.getCreateTime(),
                    temp.getUpdateTime(),userMapper.selectById(Long.valueOf(TokenUtil.getTokenUserId())).getUsername(),
                    temp.getStar(),categoriesMapper.queryDeletedCategoriesName(temp.getId())));
        }
        jsonObject.setData(result);
        jsonObject.setCount((long) list.size());
        return jsonObject;
    }
//   模板模块

    /**
     * 增加模板
     * @param templateDto
     * @return
     */
    @Override
    public int addTemplate(TemplateDto templateDto) {
        Content content=new Content();
        content.setTitle(templateDto.getTitle());
        content.setMode(templateDto.getMode());
        content.setContent(ipfsService.saveFile2(templateDto.getContent()));
        content.setType("template");
        content.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        content.setCategoriesId(1l);
        return baseMapper.insert(content);
    }

    /**
     * 删除模板
     * @param id
     * @return
     */
    @Override
    public int deleteTemplate(long id) {
       return contentMapper.deleteTemplate(id);
    }

    /**
     * 更新模板
     * @param templateDto
     * @return
     */
    @Override
    public int updateTemplate(TemplateDto templateDto) {
        Content content=new Content();
        content.setId(templateDto.getId());
        content.setTitle(templateDto.getTitle());
        content.setMode(templateDto.getMode());
        content.setContent(ipfsService.saveFile2(templateDto.getContent()));
        return baseMapper.updateById(content);
    }

    /**
     * 查询系统模板
     * @return
     */
    @Override
    public JsonObject<TemplateDto> querySystemTemplate() {
       List<TemplateDto> templateDtoList=contentMapper.queryTemplate(0l);
        for(int i=0;i<templateDtoList.size();i++){
            templateDtoList.get(i).setContent(IPFSUtils.download(templateDtoList.get(i).getContent()));
        }
       JsonObject<TemplateDto> jsonObject=new JsonObject<>();
       jsonObject.setData(templateDtoList);
       jsonObject.setCount((long) templateDtoList.size());
       return jsonObject;
    }

    /**
     * 查询我的模板
     * @return
     */
    @Override
    public JsonObject<TemplateDto> queryMyTemplate() {
        List<TemplateDto> templateDtoList=contentMapper.queryTemplate(Long.valueOf(TokenUtil.getTokenUserId()));
        for(int i=0;i<templateDtoList.size();i++){
            templateDtoList.get(i).setContent(IPFSUtils.download(templateDtoList.get(i).getContent()));
        }
        JsonObject<TemplateDto> jsonObject=new JsonObject<>();
        jsonObject.setData(templateDtoList);
        jsonObject.setCount((long) templateDtoList.size());
        return jsonObject;
    }

    /**
     * 通过ID查询模板
     * @param id
     * @return
     */
    public TemplateDto findTemplateById(Long id){
       TemplateDto templateDto=contentMapper.findTemplateById(id);
        return templateDto;
    }
    /**
     * 从回收站 恢复笔记
     * @param id
     * @return
     */
    @Override
    public int back2System(long id) {
        return contentMapper.back2System(id);
    }
    /**
     * 从回收站 删除笔记
     * @param id
     * @return
     */
    @Override
    public int deleteFromRecycleBin(long id) {
        return contentMapper.deleteFromRecycleBin(id);
    }



    /**
     * 更新笔记
     * @param id
     * @param categoriesId
     * @return
     */
    @Override
    public int updateCategories(long id, long categoriesId) {
        return contentMapper.updateCategories(id,categoriesId);
    }
    /**
     * 查询笔记列表
     * @param
     * @param categoriesId
     * @param deleted
     * @return
     */
    @Override
    public JsonObject<ContentDto> queryNoteList( long categoriesId,long deleted) {
        JsonObject<ContentDto> jsonObject =new JsonObject<>();
        List<ContentDto> dtoList=  contentMapper.queryNoteList(categoriesId,deleted);
        String username=userMapper.selectById(Long.valueOf(TokenUtil.getTokenUserId())).getUsername();
        String categoriesName=categoriesMapper.queryDeletedCategoriesName(categoriesId);
        for(int i=0;i<dtoList.size();i++){
            dtoList.get(i).setContent(IPFSUtils.download(dtoList.get(i).getContent()));
            dtoList.get(i).setCategoriesName(categoriesName);
            dtoList.get(i).setUsername(username);
        }
        jsonObject.setData(dtoList);
        jsonObject.setCount((long)dtoList.size());
        return jsonObject;
    }

    @Override
    public JsonObject<Content> queryContentsByKeyword(String keyword) {
        JsonObject<Content> jsonObject =new JsonObject<>();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        List<Content> contentList=baseMapper.selectList(wrapper);
        List<Content> result=new ArrayList<>();
        for(int i=0;i<contentList.size();i++){
            Content temp=contentList.get(i);
            String content=IPFSUtils.download(temp.getContent());
            if(content.contains(keyword)||temp.getTitle().contains(keyword)){
                System.out.println(content.contains(keyword));
                temp.setContent(content);
                result.add(temp);
            }
        }
        jsonObject.setData(result);
        jsonObject.setCount((long)result.size());
        return jsonObject;
    }


    @Override
    public AddContentDto queryContent(  long categoriesId) {
        return contentMapper.queryContent(Long.valueOf(TokenUtil.getTokenUserId()),categoriesId);
    }

    /**
     * 笔记分页返回
     * @param page
     * @param pageCount
     * @return
     */
    @Override
    public JsonObject<Content> findListByPage(Integer page, Integer pageCount){
        JsonObject<Content> jsonObject =new JsonObject<>();
        IPage<Content> wherePage = new Page<>(page, pageCount);
        contentMapper.selectPage(wherePage,null);
        jsonObject.setData(wherePage.getRecords());
        jsonObject.setCount(wherePage.getTotal());
        return jsonObject;
    }
}
