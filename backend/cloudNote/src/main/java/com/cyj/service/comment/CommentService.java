package com.cyj.service.comment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.dto.CommentDto;
import com.cyj.pojo.Comment;
import com.cyj.utils.response.JsonObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-04-21
 */
public interface CommentService extends IService<Comment> {

    /**
     * 查询评论列表
     * @param shareId
     * @return
     */
    JsonObject<CommentDto> queryComment(long shareId);

    /**
     * 添加
     *
     * @param comment 
     * @return int
     */
    int add(Comment comment);

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
     * @param comment 
     * @return int
     */
    int updateData(Comment comment);

    /**
     * id查询数据
     *
     * @param id id
     * @return Comment
     */
    Comment findById(Long id);
}
