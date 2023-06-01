package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.content.AddContentDto;
import com.cyj.dto.content.ContentDto;
import com.cyj.service.content.ContentService;
import com.cyj.service.ipfs.IPFSService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@CrossOrigin
@Api(tags = {"笔记管理 "})
@RestController
@RequestMapping("/content")
public class ContentController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ContentService contentService;
    @ApiOperation(value = "搜索",notes = "keyword关键字")
    @UserLoginToken
    @PostMapping("/queryContentsByKeyword")
    public JsonObject queryContentsByKeyword(String keyword){
        JsonObject jsonObject=new JsonObject();
        try{
            jsonObject=contentService.queryContentsByKeyword(keyword);
            jsonObject.setCode(Constants.OK_CODE);
            jsonObject.setMsg(Constants.OK_MSG);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.FAIL_MSG);
        }
        return jsonObject;
    }
    

    @ApiOperation(value = "新增笔记",notes = "id不用写")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody AddContentDto content){
        try{

//
            contentService.add(content);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("添加失败原因="+e.getMessage());
            return R.fail(Constants.addFailInfo);
        }
    }


    @ApiOperation(value = "删除笔记")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "笔记ID")
    })
    @PostMapping("/delete")
    public R delete(long id){
        System.out.println("要删除的id="+id);
        try{
            contentService.delete(id);
            return R.ok();
        }catch (Exception e){
            System.out.println("删除失败原因="+e.getMessage());
            return R.fail(Constants.deleteFailInfo);
        }
    }

    @ApiOperation(value = "更新笔记",notes = "0不加星，1加星")
    @UserLoginToken
    @PostMapping("/update")
    public R update(@RequestBody AddContentDto content){
        System.out.println("AddContentDto="+content);
        try{
            contentService.updateData(content);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "查询笔记列表")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoriesId", value = "笔记本id")
    })
    @GetMapping("/findNoteList")
    public JsonObject findNoteList(
                                     @RequestParam Long categoriesId){

        JsonObject<ContentDto> jsonObject =null;
        try{
            jsonObject =contentService.queryNoteList(categoriesId,0);
            if(jsonObject==null)
                jsonObject.setMsg(Constants.NONEData);
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


    @ApiOperation(value = "通过ID查询笔记")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "笔记id")
    })
    @GetMapping("findById")
    public ObjectData findById(long id){
        try{
            ContentDto content=contentService.findById(id);
            if(content==null)
                return ObjectData.fail(Constants.searchFailInfo);
            return ObjectData.ok(content);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查询失败原因="+e.getMessage());
            return ObjectData.fail();
        }
    }

    @ApiOperation(value = "移动笔记位置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noteId", value = "笔记id"),
            @ApiImplicitParam(name = "categoriesId", value = "新的目录id(笔记本id)")
    })
    @UserLoginToken
    @GetMapping("move")
    public R move(long noteId, long categoriesId){
        try{
            contentService.updateCategories(noteId,categoriesId);
            return R.ok();
        }catch (Exception e){
            System.out.println("查询失败原因="+e.getMessage());
            return R.fail(Constants.updateFailInfo);
        }
    }
    @Autowired
    private IPFSService ipfsService;
    @ApiOperation(value = "上传图片")
    @PostMapping("/upload")
    public ObjectData saveFile(@RequestParam("file")MultipartFile file){
      try{
          String result="http://cyjspace.5gzvip.91tunnel.com/ipfs/"+ipfsService.saveFile(file);
          return ObjectData.ok(result);
      }catch (Exception e){
          return ObjectData.fail();
      }
    }

}
