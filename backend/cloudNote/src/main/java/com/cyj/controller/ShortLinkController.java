package com.cyj.controller;

import com.cyj.pojo.ShortLink;
import com.cyj.service.shortlink.ShortLinkServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Api(value = "链接转换接口", tags = "链接转换接口")
public class ShortLinkController {
    @Autowired
    ShortLinkServiceImpl shortLinkService;

    //生成分享链接
    @PostMapping(value = "/share.do")
    @ApiOperation(value = "长链转短链", tags ="链接转换接口")
    @ApiImplicitParam(name = "longLink", value = "长链", dataType = "string", paramType = "query", example = "www.baidu.com", required = true)
    public String shareLink(HttpServletRequest request, String longLink) {

        return shortLinkService.addShortLink(longLink, getUrlStart(request)).getShortLink();
    }

    //实现短链跳转
    @GetMapping(value = "/short/{shortLink}")
    @ApiOperation(value = "实现短链跳转", tags ="链接转换接口")
    @ApiImplicitParam(name = "shortLink", value = "短链", dataType = "string", paramType = "path", example = "NbI6qbyb", required = true)
    public void sendRedirect(HttpServletResponse response, @PathVariable String shortLink) {
        ShortLink shorts = shortLinkService.getLongLink(shortLink);
        if (shorts != null) {
            try {
                response.sendRedirect(shorts.getLongLink());
            } catch (IOException e) {
                e.printStackTrace();
            }
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
