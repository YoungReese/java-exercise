package com.ly.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * liyang 2021-03-26
 * Java 8 内置了 Base64 编码的编码器和解码器
 *
 * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
 */
public class Base64Demo {

    private static String s = "Hello world";

    public static void main(String[] args) {

        System.out.println("原始字符串：" + s);
        //        System.out.println();

        try {

            /**
             * 使用基本编码
             */
            // 编码
            String base64encodedString_BASE = Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (基本) => 编码后：" + base64encodedString_BASE);
            // 解码
            byte[] base64decodedBytes_BASE = Base64.getDecoder().decode(base64encodedString_BASE);
            System.out.println("Base64 解码字符串 (基本) => 解码后：" + new String(base64decodedBytes_BASE, "utf-8"));


            /**
             * 使用 URL 编解码
             */
            // 编码
            String base64encodedString_URL = Base64.getUrlEncoder().encodeToString(s.getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL) => 编码后：" + base64encodedString_URL);
            // 解码
            byte[] base64decodedBytes_URL = Base64.getUrlDecoder().decode(base64encodedString_URL);
            System.out.println("Base64 解码字符串 (URL) => 解码后：" + new String(base64decodedBytes_URL, "utf-8"));


            /******************************************************************/
            System.out.println();
            /******************************************************************/


            /**
             * 使用 MIME 编解码
             */
            // 构造待编码的字符串
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 2; ++i) stringBuilder.append(UUID.randomUUID().toString());
            System.out.println("构造待编码的字符串：==============> " + stringBuilder.toString());
            // 编码
            String mimeEncodedString_MIME = Base64.getMimeEncoder().encodeToString(stringBuilder.toString().getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (MIME) => 编码后：" + mimeEncodedString_MIME);
            // 解码
            byte[] base64decodedBytes_MIME = Base64.getMimeDecoder().decode(mimeEncodedString_MIME);
            System.out.println("Base64 解码字符串 (MIME) => 解码后：" + new String(base64decodedBytes_MIME, "utf-8"));

        } catch (UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }
}
