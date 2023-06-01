package com.cyj.service.history;

import com.cyj.dto.HistoryDto;
import com.cyj.pojo.History;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.utils.response.JsonObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
public interface HistoryService extends IService<History> {
    /**
     * 查询历史版本列表
     * @param contentId
     * @return
     */
    JsonObject<HistoryDto> findHistory(Long contentId);

    /**
     * 添加
     *
     * @param history 
     * @return int
     */
    int add(History history);

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
     * @param history 
     * @return int
     */
    int updateData(History history);

    /**
     * id查询数据
     *
     * @param id id
     * @return History
     */
    History findById(Long id);
}
