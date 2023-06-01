package com.cyj.mapper;

import com.cyj.dto.HistoryDto;
import com.cyj.pojo.History;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
@Repository
public interface HistoryMapper extends BaseMapper<History> {
    /**
     * 查询历史版本
     * @param contentId
     * @return
     */
    List<HistoryDto> findHistory(Long contentId);
}
