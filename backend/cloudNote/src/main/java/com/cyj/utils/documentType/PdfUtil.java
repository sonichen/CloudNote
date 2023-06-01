package com.cyj.utils.documentType;

import com.cyj.pojo.Content;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.jsoup.Jsoup;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.net.URLEncoder;

public class PdfUtil {

    public static void main(String[] args) {
        Content content=new Content();
        content.setContent("内容");
        content.setTitle("名字");
        content.setContent("<ul><li>常用标签</li>");
        System.out.println(  htmlToPdf(content,"F:\\test.pdf"));;
    }

    public static Boolean htmlToPdf(Content guideBook,String pdfPath) {
        try {//1.新建document

            Document document = new Document();
            //2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
            // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));//3.打开文档

            document.open();//要解析的html//html转换成普通文字,方法如下:

            org.jsoup.nodes.Document contentDoc = Jsoup.parseBodyFragment(getHtml(guideBook.getTitle()) + guideBook.getContent());

            org.jsoup.nodes.Document.OutputSettings outputSettings = new org.jsoup.nodes.Document.OutputSettings();

            outputSettings.syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);

            contentDoc.outputSettings(outputSettings);

            String parsedHtml = contentDoc.outerHtml();//这儿的font-family不支持汉字，{font-family:仿宋} 是不可以的。

            InputStream cssIs = new ByteArrayInputStream("* {font-family: PingFang-SC-Medium.otf;}".getBytes("UTF-8"));//第四个参数是html中的css文件的输入流//第五个参数是字体提供者，使用系统默认支持的字体时，可以不传。

            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(parsedHtml.getBytes()), cssIs);//5.关闭文档


            document.close();
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }


    }

    /**
     * 下载文件
     * <p>
     * wmy 9:54 2019/8/12
     *
     * @return void
     * @Param [request, response, inputStream, fileName]
     **/

    public static void download(HttpServletRequest request, HttpServletResponse response, InputStream inputStream, String fileName) {

        BufferedOutputStream bos = null;
        try {//定义输出缓冲 10k

            byte[] buffer = new byte[10240];//文件名称的处理// http://127.0.0.1:5002/guide-book/pdf?id=124

            fileName = fileName.replaceAll("[\\pP\\p{Punct}]", "-").replace(" ", "-").replaceAll("[-]+", "-") + ".pdf";

            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {

                fileName = URLEncoder.encode(fileName, "UTF-8");

            } else {

                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");

            }

            response.setCharacterEncoding("utf-8");

            response.setContentType("application/msword");

            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            bos = new BufferedOutputStream(response.getOutputStream());
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {

                bos.write(buffer, 0, bytesRead);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            if (bos != null) {
                try {

                    bos.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

    }

    /**
     * 获取html
     * <p>
     * wmy 10:39 2019/8/12
     *
     * @return java.lang.String
     * @Param [title]
     **/

    public static String getHtml(String title) {
        return ""+title+"";

    }

}
