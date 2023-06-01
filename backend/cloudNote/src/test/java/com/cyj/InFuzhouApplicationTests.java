package com.cyj;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.mapper.CategoriesMapper;
import com.cyj.mapper.ContentMapper;
import com.cyj.mapper.ShortLinkMapper;
import com.cyj.pojo.Categories;
import com.cyj.pojo.Content;
import com.cyj.pojo.Menu;
import com.cyj.utils.tools.HttpUtils;
import com.cyj.utils.tools.MarkdownUtils;
import com.youbenzi.md2.export.FileFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class InFuzhouApplicationTests {


    @Autowired
    ContentMapper contentMapper;

    @Autowired
    CategoriesMapper categoriesMapper;
    @Test
    void  testTree(){
//        List<Categories> categories=categoriesMapper.queryNoteBookByHeadAndUserId(1l);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("userId",1);
        List<Categories> categories=categoriesMapper.selectList(wrapper);
        List<Menu> menus=new ArrayList<>();
        for(int i=0;i<categories.size();i++){
            Categories categories1=categories.get(i);
            menus.add(new Menu(categories1.getId()+"",categories1.getValue(),categories1.getHead()+"",null,null));
        }
        String jsonString= JSON.toJSONString(menus);
        System.out.println(jsonString);
    }

    @Test
    void test(){
        Content content=contentMapper.selectById(1);
        System.out.println(content.getContent());
        System.out.println("转换后");
        System.out.println(MarkdownUtils.markdownToHtmlExtensitons(content.getContent()));
        try {
            // 导出文本
            FileFactory.produce(new File("C:\\Users\\lenovo\\Desktop\\file.md"), "C:\\Users\\lenovo\\Desktop\\file.docx");
            FileFactory.produce(new File("C:\\Users\\lenovo\\Desktop\\file.md"), "C:\\Users\\lenovo\\Desktop\\file.pdf");
            FileFactory.produce(new File("C:\\Users\\lenovo\\Desktop\\file.md"), "C:\\Users\\lenovo\\Desktop\\file.html");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testLocation(){
        String host = "https://regeo.market.alicloudapi.com";
        String path = "/v3/geocode/regeo";
        String method = "GET";
        String appcode = "83359fd73fe94948385f570e3c139105";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("batch", "false");
        querys.put("callback", "callback");
        querys.put("extensions", "base");
        querys.put("homeorcorp", "homeorcorp");
        querys.put("location", "福州");
        querys.put("output", "output");
        querys.put("radius", "1000");
        querys.put("roadlevel", "roadlevel");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    ShortLinkMapper shortLinkMapper;
    @Test
    void testDelete(){
        shortLinkMapper.deleteById(26l);
    }
}
