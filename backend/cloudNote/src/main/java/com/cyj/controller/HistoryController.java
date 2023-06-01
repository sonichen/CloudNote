package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.HistoryDto;
import com.cyj.service.history.HistoryService;
import com.cyj.utils.constants.Constants;
import com.cyj.utils.response.JsonObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
@CrossOrigin
@Api(tags = {"版本追踪 "})
@RestController
@RequestMapping("/history")
public class HistoryController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private HistoryService historyService;
    @ApiOperation(value = "查询")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contentId", value = "文章ID")
    })
    @GetMapping("/findHistory")
    public JsonObject findHistory(@RequestParam long  contentId){

        JsonObject<HistoryDto> jsonObject =null;
        try{
            jsonObject =historyService.findHistory(contentId);
            if(jsonObject==null)
                jsonObject.setMsg(Constants.NONEData);
            jsonObject.setMsg(Constants.searchSuccessInfo);
            jsonObject.setCode(Constants.OK_CODE);

        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
            jsonObject.setMsg(Constants.searchFailInfo);

        }
        return jsonObject;
    }




}
