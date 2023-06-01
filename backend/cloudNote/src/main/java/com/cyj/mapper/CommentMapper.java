package com.cyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyj.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyj
 * @since 2022-04-21
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 查询评论列表
     * @param shareId
     * @return
     */
    List<Comment> queryComment(long shareId);
}
