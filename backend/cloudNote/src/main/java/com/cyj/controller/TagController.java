package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.TagsDto;
import com.cyj.dto.content.ContentDto;
import com.cyj.pojo.Tag;
import com.cyj.service.content.ContentService;
import com.cyj.service.tag.TagService;
import com.cyj.utils.constants.Constants;
import com.cyj.utils.response.JsonObject;
import com.cyj.utils.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@CrossOrigin
@Api(tags = {"标签管理"})
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    TagService tagService;
    @Resource
    ContentService contentService;
    @ApiOperation(value = "查看该标签下的文章")
    @ApiImplicitParams({

    })
    @UserLoginToken
    @GetMapping("/findArticlesByTags")
    public JsonObject findArticlesByTags(@RequestParam  String tagsName
    ){

        JsonObject<ContentDto> jsonObject =null;
        try{
            jsonObject =contentService.queryContentsByTag(tagsName);
            if(jsonObject==null)
                jsonObject.setMsg("无笔记");
            jsonObject.setMsg(Constants.searchSuccessInfo);
            jsonObject.setCode(Constants.OK_CODE);


        }catch (Exception e){
           e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.searchFailInfo);

        }
        return jsonObject;
    }
    @ApiOperation(value = "查看该文章的标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contentId", value = "文章ID")
    })
    @UserLoginToken
    @GetMapping("/findTagsByArticle")
    public JsonObject findTagsByArticle(@RequestParam long contentId ){

        JsonObject<Tag> jsonObject =null;
        try{
            jsonObject =tagService.queryTagsList(contentId);
            if(jsonObject==null)
                jsonObject.setMsg("无笔记");
            jsonObject.setMsg(Constants.searchSuccessInfo);
            jsonObject.setCode(Constants.OK_CODE);


        }catch (Exception e){
            System.out.println("查询失败原因="+e.getMessage());
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.searchFailInfo);

        }
        return jsonObject;
    }

    @ApiOperation(value = "查看该用户的所有标签")
    @ApiImplicitParams({

    })
    @UserLoginToken
    @GetMapping("/findTagsList")
    public JsonObject findTagsList( ){

        JsonObject<Tag> jsonObject =null;
        try{
            jsonObject =tagService.queryUsersTagsList();
            if(jsonObject==null)
                jsonObject.setMsg("无笔记");
            jsonObject.setMsg(Constants.OK_MSG);
            jsonObject.setCode(Constants.OK_CODE);


        }catch (Exception e){
           e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.FAIL_MSG);

        }
        return jsonObject;
    }

//    @UserLoginToken
//    @ApiOperation(value = "新增tag")
//    @PostMapping("/addTags")
//    public R addTags(TagsDto tags){
//        try{
//            tagService.addTags(tags);
//            return R.ok();
//        }catch (Exception e){
//            System.out.println("添加失败原因="+e.getMessage());
//            return R.fail(Constants.addFailInfo);
//        }
//    }

    @UserLoginToken
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public R add(@RequestBody Tag tag){
        try{
            tagService.add(tag);
            return R.ok();
        }catch (Exception e){
            System.out.println("添加失败原因="+e.getMessage());
            return R.fail(Constants.addFailInfo);
        }
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签id")
    })
    @UserLoginToken
    @PostMapping("/delete")
    public R delete(long id){
        System.out.println("要删除的id="+id);
        try{
            tagService.delete(id);
            return R.ok();
        }catch (Exception e){
            System.out.println("删除失败原因="+e.getMessage());
            return R.fail(Constants.deleteFailInfo);
        }
    }

    @ApiOperation(value = "更新")
    @UserLoginToken
    @PostMapping("/update")
    public R update(@RequestBody Tag tag){
        try{
            tagService.updateData(tag);
            return R.ok();
        }catch (Exception e){
            System.out.println("更新失败原因="+e.getMessage());
            return R.fail(Constants.updateFailInfo);
        }
    }
}
