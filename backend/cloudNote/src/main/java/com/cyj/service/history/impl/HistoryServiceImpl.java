package com.cyj.service.history.impl;

import com.cyj.dto.HistoryDto;
import com.cyj.pojo.History;
import com.cyj.mapper.HistoryMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.service.history.HistoryService;
import com.cyj.utils.ipfs.IPFSUtils;
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
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

    @Autowired
    HistoryMapper historyMapper;
    /**
     * 查询历史版本列表
     * @param contentId
     * @return
     */
    @Override
    public JsonObject<HistoryDto> findHistory(Long contentId) {
       JsonObject<HistoryDto> jsonObject=new JsonObject<>();
        List<HistoryDto> historyDtoList=historyMapper.findHistory(contentId);
       for(int i=0;i<historyDtoList.size();i++){
           historyDtoList.get(i).setContent(IPFSUtils.download(historyDtoList.get(i).getContent()));
       }
        jsonObject.setData(historyDtoList);
        jsonObject.setCount((long) historyDtoList.size());
        return jsonObject;
    }

    @Override
    public int add(History history){
        return baseMapper.insert(history);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(History history){
        return baseMapper.updateById(history);
    }

    @Override
    public History findById(Long id){
        return  baseMapper.selectById(id);
    }
}
