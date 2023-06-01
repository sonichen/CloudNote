package com.cyj.service.comment.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.dto.CommentDto;
import com.cyj.mapper.CommentMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Comment;

import com.cyj.service.comment.CommentService;
import com.cyj.utils.secret.TokenUtil;
import com.cyj.utils.response.JsonObject;
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
 * @since 2022-04-21
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 查询评论列表
     * @param shareId
     * @return
     */
    @Override
    public JsonObject<CommentDto> queryComment(long shareId) {

        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("shareId",shareId);
        wrapper.orderByDesc("create_time");
        List<Comment> lists=baseMapper.selectList(wrapper);
        System.out.println("list="+lists);
        if(lists==null){
            return null;
        }
        List<CommentDto> commentDtos=new ArrayList<>();
        for(int i=0;i< lists.size();i++){
            Comment comment=lists.get(i);
            String username=userMapper.selectById(comment.getUserId()).getUsername();
            commentDtos.add(new CommentDto(comment.getId(),username,comment.getDescription(),comment.getCreateTime()));
//            commentDtos..setId(lists.get(i).getId());
//            commentDtos.get(i).setCreateTime(lists.get(i).getCreateTime());
//            commentDtos.get(i).setDescription(lists.get(i).getDescription());
//            commentDtos.get(i).setUsername(userMapper.selectById(lists.get(i).getId()).getUsername());
        }
        JsonObject<CommentDto> jsonObject=new JsonObject<>();
        jsonObject.setData(commentDtos);
        jsonObject.setCount((long)commentDtos.size());
        return jsonObject;
    }

    @Override
    public int add(Comment comment){
        comment.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        return baseMapper.insert(comment);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Comment comment){
        return baseMapper.updateById(comment);
    }

    @Override
    public Comment findById(Long id){
        return  baseMapper.selectById(id);
    }
}
