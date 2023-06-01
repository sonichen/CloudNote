package com.cyj.service.shortlink;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.mapper.ShortLinkMapper;
import com.cyj.pojo.ShortLink;
import com.cyj.utils.url.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkServiceImpl  extends ServiceImpl<ShortLinkMapper, ShortLink>  {

    private ShortLinkMapper shortLinkMapper;

    @Autowired
    public void setShortLinkMapper(ShortLinkMapper shortLinkMapper) {
        this.shortLinkMapper = shortLinkMapper;
    }

    /**
     * 生成短链接
     */
    //通过长链生成对应短链并存在数据库中
    public ShortLink addShortLink(String longLink, String url) {
//        System.out.println("url="+url);
        url="http://mycloudnote.gz2vip.91tunnel.com";
        url += "/short";
        ShortLink shortLink = new ShortLink();
        shortLink.setLongLink(longLink);
        shortLink.setShorts(URLUtil.getShortURL());
        System.out.println("URLUtil.getShortURL()"+URLUtil.getShortURL());
        shortLink.setShortLink(url + "/" + shortLink.getShorts());
        shortLinkMapper.insert(shortLink);
        return shortLink;
    }

    //通过短链查询到对应长链
    public  ShortLink getLongLink(String shorts) {
        QueryWrapper<ShortLink> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shorts", shorts);
        return shortLinkMapper.selectOne(queryWrapper);
    }
}

