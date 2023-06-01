package com.cyj.service.share;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.dto.ShareCheckDto;
import com.cyj.dto.ShareNoteDto;
import com.cyj.pojo.Share;
import com.cyj.pojo.ShortLink;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-04-21
 */
public interface ShareService extends IService<Share> {

    List<ShortLink> checkInfo(String link);

    ShareNoteDto read(long shareId);
    public ShareCheckDto  check(Long contentId);

    /**
     * 添加
     *
     * @param share 
     * @return int
     */
    public String add(long contentId, ShortLink shortLink,String head);

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
     * @param share 
     * @return int
     */
    int updateData(ShareNoteDto share);

    /**
     * id查询数据
     *
     * @param id id
     * @return Share
     */
    Share findById(Long id);
}
