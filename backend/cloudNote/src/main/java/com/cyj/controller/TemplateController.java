package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;

import com.cyj.dto.content.TemplateDto;

import com.cyj.service.content.ContentService;
import com.cyj.utils.constants.Constants;
import com.cyj.utils.response.JsonObject;
import com.cyj.utils.response.ObjectData;
import com.cyj.utils.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@Api(tags = {"模板管理"})
@RestController
@RequestMapping("/template")
public class TemplateController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ContentService contentService;



    @ApiOperation(value = "新增模板",notes = "id不用写")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody TemplateDto content){
        try{
            contentService.addTemplate(content);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(Constants.FAIL_MSG);
        }
    }


    @ApiOperation(value = "删除模板")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "模板ID")
    })
    @PostMapping("/delete")
    public R delete(long id){
        try{
            contentService.deleteTemplate(id);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "更新模板")
    @UserLoginToken
    @PostMapping("/update")
    public R update(@RequestBody TemplateDto content){
        try{
            contentService.updateTemplate(content);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "查询系统模板")
    @UserLoginToken
    @GetMapping("/findSystemTemplate")
    public JsonObject findSystemTemplate(){
        JsonObject<TemplateDto> jsonObject =null;
        try{
            jsonObject =contentService.querySystemTemplate();
            if(jsonObject!=null)
                 jsonObject.setMsg(Constants.searchSuccessInfo);
            jsonObject.setMsg(Constants.NONEData);
            jsonObject.setCode(Constants.OK_CODE);


        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.FAIL_MSG);

        }
        return jsonObject;
    }

    @ApiOperation(value = "查询我的模板")
    @UserLoginToken
    @GetMapping("/findMyTemplate")
    public JsonObject findMyTemplate(){
        JsonObject<TemplateDto> jsonObject =null;
        try{
            jsonObject =contentService.queryMyTemplate();
            if(jsonObject!=null)
                jsonObject.setMsg(Constants.OK_MSG);
            jsonObject.setMsg(Constants.NONEData);
            jsonObject.setCode(Constants.OK_CODE);


        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.FAIL_MSG);

        }
        return jsonObject;
    }

    @ApiOperation(value = "通过ID查询模板")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "笔记id")
    })
    @GetMapping("findById")
    public ObjectData findById(long id){
        try{
            TemplateDto content=contentService.findTemplateById(id);
            if(content==null)
                return ObjectData.fail(Constants.FAIL_MSG);
            return ObjectData.ok(content);
        }catch (Exception e){
            e.printStackTrace();
            return ObjectData.fail();
        }
    }
}
