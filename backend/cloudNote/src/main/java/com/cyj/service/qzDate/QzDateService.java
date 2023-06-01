package com.cyj.service.qzDate;

import com.cyj.pojo.QzDate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-05-17
 */
public interface QzDateService extends IService<QzDate> {

    /**
     * 添加
     *
     * @param qzDate 
     * @return int
     */
    int add(QzDate qzDate);

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
     * @param qzDate 
     * @return int
     */
    int updateData(QzDate qzDate);

    /**
     * id查询数据
     *
     * @param id id
     * @return QzDate
     */
    QzDate findById(Long id);
}
