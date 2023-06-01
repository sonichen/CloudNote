package com.cyj.utils.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.cyj.utils.ipfs.IPFSUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


/**
 * <p>Description: </p>
 * @author xuyangwei
 * @date 2019年9月13日
 */
public class QRCodeUtils {
    public static void main(String[] args) {
//        QRCodeUtils QRCodeUtils = new QRCodeUtils();
//        // 传参：二维码内容和生成路径
////        if (QRCodeUtils.orCode("https://blog.csdn.net/q15102780705/article/details/100060137", "D:\\1.jpg")) {
//            if (QRCodeUtils.orCode("https://blog.csdn.net/q15102780705/article/details/100060137")) {
//            System.out.println("ok,成功");
//        } else {
//            System.out.println("no,失败");
//        }


    }

    public static String  generateCode(String content) {
        /*
         * 图片的宽度和高度
         */
        int width = 300;
        int height = 300;
        // 图片的格式
        String format = "png";

        // 定义二维码的参数
        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 定义字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 纠错的等级 L > M > Q > H 纠错的能力越高可存储的越少，一般使用M
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置图片边距
        hints.put(EncodeHintType.MARGIN, 2);


        try {
            // 最终生成 参数列表 （1.内容 2.格式 3.宽度 4.高度 5.二维码参数）
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 写入到本地
            long n = (long)(Math.random() % 29) + 1;
            long n1 = (long)(Math.random() % 29) + 1;
            String filename="G:\\"+((new SnowflakeIdUtils(n,n1).nextId())+"")+".jpg";
            System.out.println(filename);
            Path file = new File(filename).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            String result=(IPFSUtils.upload(file.toString()));
            File file1=new File(filename);
            file1.deleteOnExit();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     * image流数据处理
     *
     * @author ianly
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }


}

