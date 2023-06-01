package com.cyj.utils.documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.web.multipart.MultipartFile;

public class GetWordContent {
    public static void main(String[] args) {
        System.out.println(readWord("G:\\test.doc"));
    }
    //使用MultipartFile 接收文件流
    public static String importWord(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        String suff = fileName.substring(fileName.lastIndexOf(".") + 1);
        String content = "";
        if (suff.equals("docx")) {
            XWPFDocument xdoc = new XWPFDocument(inputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            content = extractor.getText();
//            System.out.println(content);
            extractor.close();
            return content;

        }
//        else if (suff.equals("doc")) {
//            WordExtractor ex = new WordExtractor(inputStream);
//            content = ex.getText();
//            System.out.println(content);
//            ex.close();
//        }
        else {
//
            return "非docx结尾的word文件";
        }

    }

    public static  String readWord(String path){
        String buffer = "";
        try {
            if (path.endsWith(".doc")){
                File file=new  File(path);
                InputStream is = new FileInputStream(file);
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
            }else if (path.endsWith(".docx")){
             /* 如果poi版本在4.0.0及以上那么可以使用注释的方式
             	OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();*/
                File file=new  File(path);
                FileInputStream fs = new FileInputStream(file);
                XWPFDocument xdoc = new XWPFDocument(fs);
                XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
                buffer = extractor.getText();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return buffer;
    }


}