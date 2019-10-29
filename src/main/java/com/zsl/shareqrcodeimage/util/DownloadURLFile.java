package com.zsl.shareqrcodeimage.util;

/**
 * @ClassName DownloadURLFile
 * @Description 下载文件
 * @Author binggleW
 * @Date 2019-10-28 16:55
 * @Version 1.0
 **/

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class DownloadURLFile {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String url = "https://zs-1256645015.cos.ap-guangzhou.myqcloud.com/trace/2019/6/16/c33f5013-6dbb-4cba-a745-95e34913f926.jpg";
        String path = FilePathUtils.getTempFilePath() + File.separator;
        String res = downloadFromUrl(url, path);
        String name = getFileNameFromUrl(url);
        File file = new File(path+name);
        System.out.println(res+","+file.getPath());
    }


    public static String downloadFromUrl(String url, String dir) {

        try {
            URL httpurl = new URL(url);
            String fileName = getFileNameFromUrl(url);
            System.out.println(fileName);
            File f = new File(dir + fileName);
            FileUtils.copyURLToFile(httpurl, f);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fault!";
        }
        return "Successful!";
    }

    public static String getFileNameFromUrl(String url) {
        String name = new Long(System.currentTimeMillis()).toString() + ".X";
        int index = url.lastIndexOf("/");
        if (index > 0) {
            name = url.substring(index + 1);
            if (name.trim().length() > 0) {
                return name;
            }
        }
        return name;
    }
}