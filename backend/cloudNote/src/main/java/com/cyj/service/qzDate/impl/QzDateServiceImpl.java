package com.cyj.service.qzDate.impl;

import com.cyj.pojo.QzDate;
import com.cyj.mapper.QzDateMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.service.qzDate.QzDateService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-05-17
 */
@Service
public class QzDateServiceImpl extends ServiceImpl<QzDateMapper, QzDate> implements QzDateService {


    @Override
    public int add(QzDate qzDate){
        return baseMapper.insert(qzDate);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(QzDate qzDate){
        return baseMapper.updateById(qzDate);
    }

    @Override
    public QzDate findById(Long id){
        return  baseMapper.selectById(id);
    }
}
