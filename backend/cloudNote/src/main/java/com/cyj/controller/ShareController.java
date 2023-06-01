package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.ShareCheckDto;
import com.cyj.dto.ShareNoteDto;
import com.cyj.pojo.ShortLink;
import com.cyj.service.qzDate.QzDateService;
import com.cyj.service.share.ShareService;
import com.cyj.service.shortlink.ShortLinkServiceImpl;
import com.cyj.utils.constants.AddressConstants;
import com.cyj.utils.constants.Constants;
import com.cyj.utils.response.ObjectData;
import com.cyj.utils.response.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyj
 * @since 2022-04-21
 */
@CrossOrigin
@Api(tags = {"分享外链"})
@RestController
@RequestMapping("/share")
public class ShareController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ShareService shareService;
    @Autowired
    ShortLinkServiceImpl shortLinkService;

    @Autowired
    QzDateService qzDateService;


    @ApiOperation(value = "查看当前分享状态")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contentId", value = "笔记Id")
    })
    @PostMapping("/check")
    public ObjectData check(long contentId, HttpServletRequest request){
        ObjectData objectData=new ObjectData();
        try{
           ShareCheckDto checkDto=shareService.check(contentId);
           if(checkDto==null){
               objectData.setMsg("还未分享");
           }else{
               objectData.setData(checkDto);
           }

            objectData.setCode(Constants.OK_CODE);
        }catch (Exception e){
            e.printStackTrace();
            objectData.setCode(Constants.FAIL_CODE);
        }
        return objectData;
    }

    @ApiOperation(value = "新增")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contentId", value = "笔记Id")
    })
    @PostMapping("/share.do")
    public ObjectData add(long contentId, HttpServletRequest request){
        ObjectData objectData=new ObjectData();
        try{
//            String lonkLink="http://mycloudnote.gz2vip.91tunnel.com/share/read?shareId="+contentId;
            String lonkLink= AddressConstants.SHARE_ADDRESS +contentId;
            System.out.println("原链接="+lonkLink);
            String head=getUrlStart(request);
            ShortLink shortLink=shortLinkService.addShortLink(lonkLink,getUrlStart(request));
            String link=shareService.add(contentId,shortLink,head);
            objectData.setData(link );
            objectData.setCode(Constants.OK_CODE);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("添加失败原因="+e.getMessage());
           objectData.setCode(Constants.FAIL_CODE);
        }
        return objectData;
    }



    @ApiOperation(value = "查阅")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shareId", value = "分享体")
    })
    @GetMapping("/read")
    public ObjectData read(long shareId){
        ObjectData objectData=new ObjectData();
        try{
            objectData.setData( shareService.read(shareId));
            objectData.setCode(Constants.OK_CODE);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("添加失败原因="+e.getMessage());
            objectData.setCode(Constants.FAIL_CODE);
        }
        return objectData;
    }

    @ApiOperation(value = "停止分享")
    @UserLoginToken
    @PostMapping("/delete")
    public R delete(long id){
        System.out.println("要删除的id="+id);
        try{
            shareService.delete(id);
            return R.ok();
        }catch (Exception e){
           e.printStackTrace();
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "更新")
    @UserLoginToken
    @PostMapping("/update")
    public R update(@RequestBody  ShareNoteDto share){
        try{
            shareService.updateData(share);
            return R.ok();
        }catch (Exception e){
            System.out.println("更新失败原因="+e.getMessage());
            return R.fail(Constants.updateFailInfo);
        }
    }

    //获取请求的协议域名，端口号生成连接的前半部分
    public String getUrlStart(HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme());
        System.out.println(url);
        url.append("://").append(request.getServerName());
        System.out.println(url);
        url.append(":").append(request.getServerPort());
        System.out.println(url);
//        url.append(request.getServletPath());
//        System.out.println(url);
        return url.toString();
    }





}
