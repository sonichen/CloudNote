package com.cyj.utils.documents;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

public class GetPDFContent {
    public static void main(String[] args) {
//        System.out.println(testPdf());
    }
    /**
     * MultipartFile 转换为 File 文件
     *
     * @param multipartFile
     * @return
     */
    public final static File transferToFile(MultipartFile multipartFile) {
        System.out.println("multipartFile="+multipartFile.getOriginalFilename());
        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            System.out.println("arr="+ Arrays.toString(filename));
            file = File.createTempFile(filename[0], ".pdf");    //注意下面的 特别注意！！！
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static  String getPDFContent(MultipartFile multipartFile) {
//        String path = "G:\\MyNote\\大学笔记\\大三\\大三下\\比赛\\云笔记系统\\云笔记系统-c小队.pdf";
        File file = transferToFile(multipartFile);
        System.out.println("file="+file);
        System.out.println("fileName="+file.getName());
        InputStream is = null;
        PDDocument document = null;
        String content=null;
        try {
            if (file.getName().endsWith(".pdf")) {
                document = PDDocument.load(file);
                int pageSize = document.getNumberOfPages();
                // 一页一页读取
                for (int i = 0; i < pageSize; i++) {
                    // 文本内容
                    PDFTextStripper stripper = new PDFTextStripper();
                    // 设置按顺序输出
                    stripper.setSortByPosition(true);
                    stripper.setStartPage(i + 1);
                    stripper.setEndPage(i + 1);
                    String text = stripper.getText(document);
//                    System.out.println(text.trim());
//                    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
                    content+=text.trim();
                    System.out.println(content);
                    // 图片内容
//                    PDPage page = document.getPage(i);
//                    PDResources resources = page.getResources();
//                    Iterable<COSName> cosNames = resources.getXObjectNames();
//                    if (cosNames != null) {
//                        Iterator<COSName> cosNamesIter = cosNames.iterator();
//                        while (cosNamesIter.hasNext()) {
//                            COSName cosName = cosNamesIter.next();
//                            if (resources.isImageXObject(cosName)) {
//                                PDImageXObject Ipdmage = (PDImageXObject) resources.getXObject(cosName);
//                                BufferedImage image = Ipdmage.getImage();
//                                FileOutputStream out = new FileOutputStream("G:\\MyNote\\大学笔记\\大三\\大三下\\比赛\\云笔记系统\\" + UUID.randomUUID() + ".png");
//                                try {
//                                    ImageIO.write(image, "png", out);
//                                } catch (IOException e) {
//                                } finally {
//                                    try {
//                                        out.close();
//                                    } catch (IOException e) {
//                                    }
//                                }
//                            }
//                        }
//                    }
                }
            }
        } catch (InvalidPasswordException e) {
            return "pdf去读失败";
        } catch (IOException e) {
            return "pdf去读失败";
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            System.out.println("content="+content);
           return content;
        }
    }

}

