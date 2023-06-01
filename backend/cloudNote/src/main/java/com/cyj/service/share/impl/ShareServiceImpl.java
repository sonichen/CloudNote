package com.cyj.service.share.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.dto.ShareCheckDto;
import com.cyj.dto.ShareNoteDto;
import com.cyj.mapper.ContentMapper;
import com.cyj.mapper.ShareMapper;
import com.cyj.mapper.ShortLinkMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Content;
import com.cyj.pojo.Share;

import com.cyj.pojo.ShortLink;
import com.cyj.pojo.User;
import com.cyj.service.share.ShareService;

import com.cyj.utils.constants.AddressConstants;
import com.cyj.utils.ipfs.IPFSUtils;
import com.cyj.utils.secret.TokenUtil;
import com.cyj.utils.tools.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-04-21
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {


    @Autowired
    ShareMapper shareMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    ShortLinkMapper shortLinkMapper;
    @Autowired
    UserMapper userMapper;


   public List<ShortLink> checkInfo(String link){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("long_link",link);
        List<ShortLink> list=shortLinkMapper.selectList(wrapper);
        return list;
    }

    public ShareCheckDto check(Long contentId){

        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("contentId",contentId);
        Share share=baseMapper.selectOne(wrapper);
        if(share==null){
            return null;
        }
        //查询短链接
       String shortLink= shortLinkMapper.selectById(share.getLinkId()).getShortLink();
       //查询文章
        Content content=contentMapper.selectById(contentId);
        ShareCheckDto shareCheckDto=new ShareCheckDto();
        shareCheckDto.setId(share.getId());
        shareCheckDto.setLink(shortLink);
//        shareCheckDto.setQrCode("http://cyjspace.5gzvip.91tunnel.com/ipfs/"+share.getQrcode());
        shareCheckDto.setQrCode(AddressConstants.IPFS_REQUEST_ADDRESS +share.getQrcode());
//        shareCheckDto.setQrCode(shortLink);
        shareCheckDto.setCreateTime(share.getCreateTime());
        shareCheckDto.setAllowComment(share.getAllowComment());
        shareCheckDto.setPassword(share.getPassword());
        shareCheckDto.setLocked(share.getLocked());
        shareCheckDto.setImage_url(AddressConstants.DEFAULT_SHARE_IMG);
        shareCheckDto.setTitle(content.getTitle());
        shareCheckDto.setDesc(IPFSUtils.download(content.getContent()));
        return shareCheckDto;
    }
    /**
     * 创建分享体
     * @param contentId
     * @param shortLink
     * @param head
     * @return
     */
    @Override
    public String add(long contentId, ShortLink shortLink,String head){
        /**
         * 检查是否已经生成
         */
        Share check=shareMapper.selectById(contentId);
        if(check!=null){
            return read(contentId).getLink();
        }
        Share share=new Share();
        Content content=contentMapper.selectById(contentId);
        System.out.println("检测="+contentId);
        share.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        share.setContentId(contentId);
        QueryWrapper<ShortLink>queryWrapper=new QueryWrapper<>();
         queryWrapper.eq("shorts",shortLink.getShorts());
        ShortLink findLink= shortLinkMapper.selectOne(queryWrapper);
        share.setLinkId((long)findLink.getId());
        share.setId(contentId);
//        String qrcode= QRCodeUtils.generateCode(shortLink.getShortLink());
        String qrcode= QRCodeUtils.generateCode(AddressConstants.SHARE_ADDRESS+contentId);
        share.setQrcode(qrcode);
        baseMapper.insert(share);
//        /**
//         * 封装ShareNoteDto
//         */
//        ShareNoteDto shareNoteDto=new ShareNoteDto();
//        shareNoteDto.setId((long)findLink.getId());
//        shareNoteDto.setAllowComment(0l);
//        shareNoteDto.setLocked(0l);
//        shareNoteDto.setLink(shortLink.getShortLink());
//        shareNoteDto.setCreateTime(new DateTime());
//        shareNoteDto.setUsername(userMapper.selectById(TokenUtil.getTokenUserId()).getUsername());


        return shortLink.getShortLink();
    }

    /**
     * 构建分享体
     * @param shareId
     * @return
     */
    @Override
    public ShareNoteDto read(long shareId) {
        Share share=shareMapper.selectById(shareId);
        Content content=contentMapper.selectById(share.getContentId());
        User user=userMapper.selectById(share.getUserId());
        ShortLink shortLink=shortLinkMapper.selectById(share.getLinkId());
        ShareNoteDto shareNoteDto=new ShareNoteDto();
        shareNoteDto.setId(share.getId());
        shareNoteDto.setTitle(content.getTitle());
        shareNoteDto.setContent(IPFSUtils.download(content.getContent()));
        shareNoteDto.setCreateTime(content.getCreateTime());
        shareNoteDto.setUsername(user.getUsername());
        shareNoteDto.setAllowComment(share.getAllowComment());
        shareNoteDto.setLocked(share.getLocked());
        shareNoteDto.setPassword(share.getPassword());
        shareNoteDto.setLink(shortLink.getShortLink());
        return shareNoteDto;
    }


    @Override
    public int delete(Long id){
        System.out.println("收到id="+id);
        Share share=baseMapper.selectById(id);
        System.out.println("share="+share);
        System.out.println("删除="+share.getLinkId());
        shortLinkMapper.deleteById(share.getLinkId());
        return baseMapper.deleteById(id);
    }

    /**
     * 更新分享体
     * 添加评论设置，锁设置，密码设置
     * TODO 密码加密
     * @param shareNoteDto
     * @return
     */
    @Override
    public int updateData(ShareNoteDto shareNoteDto){
        Share share=baseMapper.selectById(shareNoteDto.getId());
        share.setAllowComment(shareNoteDto.getAllowComment());
        share.setLocked(shareNoteDto.getLocked());
        if(shareNoteDto.getLocked()==0){
            share.setPassword(shareNoteDto.getPassword());
        }
        return baseMapper.updateById(share);
    }

    @Override
    public Share findById(Long id){
        return  baseMapper.selectById(id);
    }


}
