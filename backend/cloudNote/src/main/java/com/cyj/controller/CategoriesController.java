package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.notebook.AddNoteBookDto;
import com.cyj.dto.notebook.AddNoteBookDto1;
import com.cyj.pojo.Menu;
import com.cyj.service.categories.CategoriesService;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@CrossOrigin
@Api(tags = {"笔记本管理"})
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CategoriesService categoriesService;

    @ApiOperation(value = "查询笔记本列表")
    @UserLoginToken
    @GetMapping("/findNoteBookList")
    public JsonObject<Menu> findNoteBookList() {
        JsonObject<Menu> jsonObject =null;
        try{
            jsonObject =categoriesService.findNoteBookList();
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


    @ApiOperation(value = "新增笔记本/组",notes = "id不用填")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody AddNoteBookDto noteBookDto){
        try{
            if(categoriesService.add(noteBookDto)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }


    @ApiOperation(value = "删除笔记本/组")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "笔记本ID")
    })
    @PostMapping("/delete")
    public R delete(long id){
        try{
            if(categoriesService.deleteNoteBook(id)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "更新笔记本/组",notes = "移动目录，改名")
    @UserLoginToken
    @PostMapping("/update")
    public R update(@RequestBody AddNoteBookDto noteBookDto){
        try{
            if( categoriesService.updateData(noteBookDto)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }
    @ApiOperation(value = "更新笔记本/组",notes = "改名")
    @UserLoginToken
    @PostMapping("/updateName")
    public R updateName(@RequestBody AddNoteBookDto1 noteBookDto){
        try{
            if( categoriesService.updateName(noteBookDto)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }
}
