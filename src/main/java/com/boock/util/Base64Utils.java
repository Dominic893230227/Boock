package com.boock.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64Utils {
    //    public static String outputFilePath = "D:/SOFTWARE/CODE/idea/workspace/Boock/src/main/resources/static/uploadImage/boock/";
    private static final String windowsBoockPath = "D:/SOFTWARE/CODE/uploadFile/boock/";
    private static final String linuxBoockPath = "/opt/uploadFile/boock/";

    public static String convertBase64ToURL(String html) {
        Pattern pattern = Pattern.compile("<img src=\"data:image/(jpeg|png|jpg|gif);base64,(.*?)\"");
//        Pattern pattern = Pattern.compile("(<img src=\"data:image/jpeg;base64,)(.*?)\"");

        Matcher matcher = pattern.matcher(html);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String prefix = matcher.group(1);
            String base64 = matcher.group(2);
            String urlStr = saveImage(prefix,base64);
            urlStr = urlStr.substring(urlStr.lastIndexOf("/")+1);
            String dataURL = urlStr; // 完整的 Data URL
            matcher.appendReplacement(sb, "<img src=\"/boockImg/" + dataURL + "\"");
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static String saveImage(String fileType, String base64String) {

        String uuid = UUID.randomUUID().toString();
        String filePath = "";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            filePath = windowsBoockPath;
        } else {
            filePath = linuxBoockPath;
        }
        filePath = filePath + uuid + "." + fileType;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // 解码Base64字符串为字节数组
            byte[] imageBytes = Base64.getDecoder().decode(base64String);

            // 直接写入字节数组
            fos.write(imageBytes);

            System.out.println("图片已保存到：" + filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}


