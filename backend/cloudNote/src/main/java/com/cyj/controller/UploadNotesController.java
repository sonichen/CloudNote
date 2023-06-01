package com.cyj.controller;



import com.cyj.annotation.UserLoginToken;
import com.cyj.service.content.ContentService;
import com.cyj.service.ipfs.IPFSService;
import com.cyj.utils.ipfs.IPFSUtils;
import com.cyj.utils.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@CrossOrigin
@Api(tags = {"上传笔记管理"})
@RestController
@RequestMapping("/categories")
public class UploadNotesController {
    @Resource
    private IPFSService ipfsService;
    @Resource
    private ContentService contentService;

    @ApiOperation(value = "上传笔记", notes = "前端限制上传文件的格式：md,docx,pdf")
    @UserLoginToken
    @PostMapping("/uploadNotes")
    public R uploadNotes(@RequestParam("file") MultipartFile file, Long categoriesId) {
        try {
            contentService.addUploadNote(file,categoriesId);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("上传失败");
        }
    }

//    @PostMapping("/uploadNotes2")
//    public R uploadNotes2(@RequestParam("file") MultipartFile file) throws IOException {
//
//        try {
//            GetWordContent.importWord(file);
//            String name=file.getOriginalFilename();
//            System.out.println("文件名="+name);
////            String content=file.
//            System.out.println(ipfsService.saveFile(file));
//            return R.ok();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return R.fail("上传失败");
//        }
//    }
//
    @PostMapping("/download")
    public String download(String hash) throws IOException {
        return IPFSUtils.download(hash);
    }
//    @PostMapping("upload")
//    public String upload(String data) throws IOException {
//        return IPFSUtils.upload(data.getBytes(StandardCharsets.UTF_8));
//    }

}
