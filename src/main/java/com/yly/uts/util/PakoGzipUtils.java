package com.yly.uts.util;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.ZipUtil;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Author: ZiWo
 * CreateTime: 2023-05-22  18:11
 * Description: TODO
 * Version: 1.0
 */
public class PakoGzipUtils {


    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }



    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toString();
    }

    /**
     * json 转 字节数组
     * @param json
     * @return 字节数组
     */
    public static byte[] jsonToByteArray(String json) {
        if (StringUtils.isEmpty(json)) {
            return new byte[0];
        }
        String encodeJson = URLEncodeUtil.encode(json, StandardCharsets.UTF_8);
        return cn.hutool.core.util.ZipUtil.gzip(encodeJson.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 字节数组 转 json
     * @param byteArray
     * @return json
     */
    public static String byteArrayToJson(byte[] byteArray) {
        if (byteArray == null || byteArray.length == 0) {
            return null;
        }
        String json = new String(ZipUtil.unGzip(byteArray), StandardCharsets.UTF_8);
        return URLDecoder.decode(json, StandardCharsets.UTF_8);
    }

    /**
     * json 转 uint8Array
     * @param json
     * @return uint8Array
     */
    public static byte[] jsonToUint8Array(String json) {
        if (StringUtils.isEmpty(json)) {
            return new byte[0];
        }
        String encodeJson = URLEncodeUtil.encode(json, StandardCharsets.UTF_8);
        byte[] blockData = cn.hutool.core.util.ZipUtil.gzip(encodeJson.getBytes(StandardCharsets.UTF_8));
        return PakoGzipUtils.encode(blockData);
    }

    /**
     * uint8Array 转 json
     * @param uint8Array
     * @return json
     */
    public static String uint8ArrayToJson(byte[] uint8Array) {
        if (uint8Array == null || uint8Array.length == 0) {
            return null;
        }
        byte[] blockData = ZipUtil.unGzip(PakoGzipUtils.decode(uint8Array));
        String json = new String(blockData, StandardCharsets.UTF_8);
        return URLDecoder.decode(json, StandardCharsets.UTF_8);
    }

    /**
     * 前端 uint8Array 转字节数组
     * @param uint8Array
     * @return 实际需传输的字节数组
     */
    public static byte[] decode(byte[] uint8Array) {
        if (uint8Array == null || uint8Array.length == 0) {
            return new byte[0];
        }

        String uint8ArrayStr = new String(uint8Array);
        String[] utf8ArraySplit = uint8ArrayStr.split(",");
        // 实际需传输的数据
        byte[] data = new byte[utf8ArraySplit.length];
        for (int i = 0; i < utf8ArraySplit.length; i++) {
            data[i] = Integer.valueOf(utf8ArraySplit[i]).byteValue();
        }

        return data;
    }

    /**
     * 字节数组转前端 uint8Array
     * @param data 实际需传输的字节数组
     * @return uint8Array
     */
    public static byte[] encode(byte[] data) {
        if (data == null || data.length == 0) {
            return new byte[0];
        }

        String[] uint8ArraySplit = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            int temp = data[i];
            if (temp < 0) {
                temp &= 0xff;
            }
            uint8ArraySplit[i] = String.valueOf(temp);
        }

        return String.join(",", uint8ArraySplit).getBytes();
    }

    public static void main(String[] args) {
        String base64Str = "aHR0cDovLzEyNC4yMjEuMTM1Ljg0L2FwaS9maWxlL2ZpbGVkb3dubG9hZC9kb2N1bWVudC/kuqTmjqXmlofmoaMuZG9jeA==";
        String url = new String(Base64.getDecoder().decode(base64Str));
        System.out.println(url);

//        String url = "http://124.221.135.84/api/file/filedownload/document/交接文档.docx";
//        url = Base64.getEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
//        System.out.println(url);


//        byte[] blockData = PakoGzipUtils.decode(Base64.getDecoder().decode(base64Str));
//        // 解压
//        String blockDataJson = new String(ZipUtil.unGzip(blockData), StandardCharsets.UTF_8);
//        System.out.println(blockDataJson);
//
//        byte[] encode = PakoGzipUtils.encode(blockData);
//        System.out.println(Base64.getEncoder().encodeToString(encode));
    }

}
