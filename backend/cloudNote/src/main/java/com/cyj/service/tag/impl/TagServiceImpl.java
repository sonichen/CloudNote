package com.cyj.service.tag.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.dto.TagsDto;
import com.cyj.pojo.Tag;
import com.cyj.mapper.TagMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.service.tag.TagService;
import com.cyj.utils.secret.TokenUtil;
import com.cyj.utils.response.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public JsonObject<Tag> queryUsersTagsList() {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.select("distinct tagsName").eq("userId",Long.valueOf(TokenUtil.getTokenUserId()));
//        List<Tag> tagList=tagMapper.queryUsersTagsList(Long.valueOf(TokenUtil.getTokenUserId()));
        List<Tag> tagList=baseMapper.selectList(wrapper);
        System.out.println("tagsList="+tagList);
        JsonObject <Tag>jsonObject=new JsonObject<>();
        jsonObject.setData(tagList);
        jsonObject.setCount((long) tagList.size());
        return jsonObject;
    }

    /**
     * 标签列表
     * @param contentId
     * @return
     */
    @Override
    public JsonObject<Tag> queryTagsList( long contentId) {
        JsonObject<Tag> jsonObject =new JsonObject<>();
        List<Tag> dtoList=  tagMapper.queryTagsList(contentId);
        jsonObject.setData(dtoList);
        jsonObject.setCount((long)dtoList.size());
        return jsonObject;
    }



    @Override
    public int add(Tag tag){
        tag.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        return baseMapper.insert(tag);
    }

    @Override
    public int addTags(TagsDto tags) {
        String arr[]=tags.getTags();
        for(int i=0;i<arr.length;i++){
            baseMapper.insert(new Tag(arr[i],tags.getContentId()));
        }
        return 0;
    }


    public int editTags(TagsDto tags) {
        String arr[]=tags.getTags();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("contentId",tags.getContentId());
        List<Tag> tagList=baseMapper.selectList(wrapper);
//        String oldArrs[]=baseMapper.selectList()
        for(int i=0;i<arr.length;i++){
//           [1,2,3]
//            [2,3,1]

        }
        return 0;
    }
    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Tag tag){
        return baseMapper.updateById(tag);
    }

    @Override
    public Tag findById(Long id){
        return  baseMapper.selectById(id);
    }
}
