package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.content.ContentDto;
import com.cyj.pojo.Content;
import com.cyj.service.content.ContentService;
import com.cyj.utils.constants.Constants;
import com.cyj.utils.response.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@CrossOrigin
@Api(tags = {"加星管理"})
@RestController
@RequestMapping("/star")
public class StarController {
    @Resource
    private ContentService contentService;

    @ApiOperation(value = "查询加星笔记列表")
    @UserLoginToken
    @GetMapping("/findStarList")
    public JsonObject findStarList(){

        JsonObject<ContentDto> jsonObject =null;
        try{
            jsonObject =contentService.queryStarNoteList();
            if(jsonObject==null)
                jsonObject.setMsg("无笔记");
            jsonObject.setMsg(Constants.searchSuccessInfo);
            jsonObject.setCode(Constants.OK_CODE);


        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查询失败原因="+e.getMessage());
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.searchFailInfo);

        }
        return jsonObject;
    }

}
