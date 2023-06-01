package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.CommentDto;
import com.cyj.pojo.Comment;

import com.cyj.service.comment.CommentService;
import com.cyj.utils.constants.Constants;
import com.cyj.utils.response.JsonObject;
import com.cyj.utils.response.R;
import io.swagger.annotations.Api;
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
 * @since 2022-04-21
 */
@CrossOrigin
@Api(tags = {"评论管理"})
@RestController
@RequestMapping("/comment")
public class CommentController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CommentService commentService;

    @ApiOperation(value = "新增",notes = "只要shareId和description（评论内容）")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody Comment commentDto){
        try{
            commentService.add(commentDto);
            return R.ok();
        }catch (Exception e){
            System.out.println("添加失败原因="+e.getMessage());
            return R.fail(Constants.addFailInfo);
        }
    }


//    @ApiOperation(value = "删除")
//    @UserLoginToken
//    @PostMapping("/delete")
//    public R delete(long id){
//        System.out.println("要删除的id="+id);
//        try{
//            commentService.delete(id);
//            return R.ok();
//        }catch (Exception e){
//            System.out.println("删除失败原因="+e.getMessage());
//            return R.fail(Constants.deleteFailInfo);
//        }
//    }

//    @ApiOperation(value = "更新")
//    @UserLoginToken
//    @PostMapping("/update")
//    public R update(@RequestBody Comment comment){
//        try{
//            commentService.updateData(comment);
//            return R.ok();
//        }catch (Exception e){
//            System.out.println("更新失败原因="+e.getMessage());
//            return R.fail(Constants.updateFailInfo);
//        }
//    }
    @ApiOperation(value = "查询")
    @UserLoginToken
    @ApiImplicitParams({

    })
    @GetMapping("/findCommentList")
    public JsonObject findListByPage(@RequestParam long shareId){

        JsonObject<CommentDto> jsonObject =null;
        try{
            jsonObject =commentService.queryComment(shareId);
            if(jsonObject==null)
                jsonObject.setMsg("暂无评论");
            jsonObject.setMsg(Constants.OK_MSG);
            jsonObject.setCode(Constants.OK_CODE);


        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.FAIL_MSG);

        }
        return jsonObject;
    }



}
