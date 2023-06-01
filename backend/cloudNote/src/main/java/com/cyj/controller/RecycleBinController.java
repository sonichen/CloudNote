package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.pojo.Menu;
import com.cyj.service.categories.CategoriesService;
import com.cyj.service.content.ContentService;
import com.cyj.utils.constants.Constants;
import com.cyj.utils.response.JsonObject;
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
@Api(tags = {"回收站管理"})
@RestController
@RequestMapping("/recycleBin")
public class RecycleBinController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CategoriesService categoriesService;
    @Resource
    private ContentService contentService;

    @ApiOperation(value = "查询回收站内容",notes = "返回结构和目录一样")
    @UserLoginToken
    @GetMapping("/findNoteBookListInRecycleBin")
    public JsonObject<Menu> findNoteBookListInRecycleBin() {
        JsonObject<Menu> jsonObject =null;
        try{
            jsonObject =categoriesService.findNoteBookListInRecycleBin();
            if(jsonObject==null)
                jsonObject.setMsg(Constants.NONEData);
            jsonObject.setMsg(Constants.OK_MSG);
            jsonObject.setCode(Constants.OK_CODE);
        }
        catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.FAIL_MSG);

        }
        return jsonObject;
    }

    @ApiOperation(value = "恢复笔记本/组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "笔记本id")
    })
    @UserLoginToken
    @PostMapping("/noteBookBack2System")
    public R noteBookBack2System(long id){
        try{
            categoriesService.back2System(id);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "永久删除笔记本/组")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "笔记本id")
    })
    @PostMapping("/deleteNoteBook")
    public R deleteNoteBook(long id){
        System.out.println("要删除的id="+id);
        try{
            categoriesService.deleteFromRecycleBin(id);
            return R.ok();
        }catch (Exception e){
            System.out.println("删除失败原因="+e.getMessage());
            return R.fail(Constants.FAIL_MSG);
        }
    }

    //处理文章
    @ApiOperation(value = "永久删除笔记")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "笔记id")
    })
    @PostMapping("/moveNotesFromSystem")
    public R moveNotesFromSystem(long id){
        System.out.println("要删除的id="+id);
        try{
            contentService.deleteFromRecycleBin(id);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(Constants.FAIL_MSG);
        }
    }
    @ApiOperation(value = "恢复笔记")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "笔记id")
    })
    @PostMapping("/notesBack2System")
    public R notesBack2System(long id){
        System.out.println("要删除的id="+id);
        try{
            contentService.back2System(id);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(Constants.deleteFailInfo);
        }
    }


}
