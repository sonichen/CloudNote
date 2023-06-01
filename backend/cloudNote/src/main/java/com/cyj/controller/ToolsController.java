package com.cyj.controller;

import com.cyj.mapper.ContentMapper;
import com.cyj.pojo.Content;
import com.cyj.service.content.ContentService;
//import com.cyj.utils.documentType.PdfUtil;
import com.cyj.utils.ipfs.IPFSUtils;
import com.cyj.utils.tools.MarkdownUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@CrossOrigin
@Api(tags = {"文件格式转换导出（差pdf）"})
@RestController
@RequestMapping("/tools")
public class ToolsController {
    @Resource
    ContentService contentService;

    @Autowired
    ContentMapper contentMapper;

    @ApiOperation("word导出")
    @PostMapping(value = "/exportWord")
    public void exprotWord( HttpServletRequest request,
                           HttpServletResponse response,Long contentId) {
        // 数据库查富文本数据
        Content contentData=contentMapper.selectById(contentId);
        System.out.println("hash="+contentData.getContent());
        String content = IPFSUtils.download(contentData.getContent());
        System.out.println("content="+content);
        String title=contentData.getTitle();
        String richText = "<html><body>" + content + "</body></html>";
        try {
            //设置编码
            byte b[] = richText.getBytes("GBK");
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            POIFSFileSystem poifs = new POIFSFileSystem();
            // ##############下面这两个不能删掉
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
            //################这两个不能删掉
            //输出文件
//            String name = "测试";
            response.reset();
            response.setHeader("Content-Disposition",
                    "attachment;filename=" +
                            new String(title.getBytes("GB2312"), "iso-8859-1") + ".doc");
            response.setContentType("application/msword");
            OutputStream ostream = response.getOutputStream();
            //输出到本地文件的话，new一个文件流
            // FileOutputStream ostream = new FileOutputStream("E:\\桌面\\测试.doc");
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//    @ApiOperation(value = "富文本导出Word")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "contentId", value = "笔记Id")
//    })
//    @PostMapping("/fwbPrint2Word")
//    public R print2Word(HttpServletResponse response, long contentId) {
//        try {
//            ContentDto contentDto = contentService.findById(contentId);
//            System.out.println("contentDto="+contentDto);
//            String title = contentDto.getTitle();
//            String content = contentDto.getContent();
//            System.out.println("打印content="+content);
//            DownloadTools.downloadWord(response, title, content);
//            return R.ok();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return R.fail(Constants.FAIL_MSG);
//        }
//    }

    @ApiOperation(value = "markdown导出Word")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contentId", value = "笔记Id")
    })
    @PostMapping("/markdownPrint2Word")
    public ResponseEntity<byte[]> downloadCommonFile(Long contentId) throws Exception {
        try {
            //获取信息
            Content content = contentService.findOnlyById(contentId);
            String fileName = System.currentTimeMillis()+content.getTitle() + ".docx";
            String text = IPFSUtils.download(content.getContent());
            //打印
            HttpHeaders headers = new HttpHeaders();
            String html = MarkdownUtils.markdownToHtmlExtensitons(text);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("content-disposition", URLEncoder.encode(fileName, "UTF-8"));
            return new ResponseEntity<byte[]>(html.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.CREATED);
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }

//    @ApiOperation(value = "富文本导出PDF")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "contentId", value = "笔记Id")
//    })
//    @PostMapping("/fwbPrint2PDF")
//    public R fwbPrint2PDF(HttpServletRequest httpServletRequest,HttpServletResponse response, long contentId) {
////        PdfUtil.download(httpServletRequest,response,null,"test.pdf");
//        return R.ok();
////        try {
////            ContentDto contentDto = contentService.findById(contentId);
////            String title = contentDto.getTitle();
////            String content = contentDto.getContent();
////            DownloadTools.downloadWord(response, title, content);
////            return R.ok();
////        } catch (Exception e) {
////
////            return R.fail(Constants.searchFailInfo);
////        }
//    }
//
//    @ApiOperation(value = "markdown导出PDF")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "contentId", value = "笔记Id")
//    })
//    @PostMapping("/markdownPrint2PDF")
//    public ResponseEntity<byte[]> markdownPrint2PDF(Long contentId) throws Exception {
//        try {
//            //获取信息
//            Content content = contentService.findOnlyById(contentId);
//            String fileName = System.currentTimeMillis()+content.getTitle() + ".docx";
//            String text = content.getContent();
//            //打印
//            HttpHeaders headers = new HttpHeaders();
//            String html = MarkdownUtils.markdownToHtmlExtensitons(text);
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setContentDispositionFormData("content-disposition", URLEncoder.encode(fileName, "UTF-8"));
//            return new ResponseEntity<byte[]>(html.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    //
//    @ApiOperation(value = "翻译器")
//    @UserLoginToken
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "content", value = "翻译内容")
//    })
//    @PostMapping("/translator")
//    public ObjectData translator(String content) {
//        ObjectData objectData = new ObjectData();
//        try {
//            String result = Translator.translator(content);
//            objectData.setData(result);
//            objectData.setCode(Constants.OK_CODE);
//            objectData.setMsg(Constants.searchSuccessInfo);
//            return objectData;
//        } catch (Exception e) {
//            objectData.setCode(Constants.FAIL_CODE);
//            objectData.setMsg(Constants.searchFailInfo);
//            return objectData;
//        }
//
//    }
}
