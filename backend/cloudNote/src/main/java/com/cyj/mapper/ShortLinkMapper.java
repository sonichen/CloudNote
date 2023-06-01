package com.cyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyj.pojo.ShortLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortLinkMapper extends BaseMapper<ShortLink> {


}
