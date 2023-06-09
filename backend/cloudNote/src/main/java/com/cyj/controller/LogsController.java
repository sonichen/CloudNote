package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.LogsDto;
import com.cyj.service.logs.LogsService;
import com.cyj.utils.constants.Constants;
import com.cyj.utils.response.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@Api(tags = {"日志管理"})
@RestController
@RequestMapping("/logs")
public class LogsController {
    @Resource
    LogsService logsService;

    @ApiOperation(value = "查看日志")
    @UserLoginToken
    @GetMapping("/findLogs")
    public JsonObject findLogssByArticle( ){

        JsonObject<LogsDto> jsonObject =null;
        try{
            jsonObject =logsService.queryLogsList();
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
