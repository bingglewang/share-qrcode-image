package com.zsl.shareqrcodeimage.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;


public class FilePathUtils {

    public static final String TEMP_PREFIX = "file/temp/";

    public static final String FILE_PATH_PREFIX = "file/upload/";

    public static String ROOT_PATH;

    /**
     * 获取临时文件地址
     * @return
     * @throws FileNotFoundException
     */
    public static String getTempFilePath() {
        File upload = new File(getPath(),"static/file/temp/");
        if(!upload.exists()){
            upload.mkdirs();
        }
        return upload.getAbsolutePath() + "/";
    }

        public static String getRootPath() {
        File upload = new File(getPath(),"static/");
        if(!upload.exists()){
            upload.mkdirs();
        }
        return upload.getAbsolutePath() + "/";
    }

    /**
     * 获取文件地址
     * @return
     * @throws FileNotFoundException
     */
   /* public static String getFilePath() {
        return getClasspath() + "static/" + FILE_PATH_PREFIX;
    }*/

    /*public static String getClasspath() {
        String path = getPath("classpath:");
        return path.indexOf("/") == 0 ? path.substring(1) : path;
    }

    public static String getResourcePath() {
        String path = getPath("classpath:");
        return (path.indexOf("/") == 0 ? path.substring(1) : path) + "static/";
    }*/

    private static String getPath() {
        if (ROOT_PATH == null) {
            try {
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                if (!path.exists()){
                    path = new File("");
                }
                ROOT_PATH = URLDecoder.decode(path.getAbsolutePath(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ROOT_PATH;
    }

    public static File mkdirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取文件名后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

   /* public static void replace(String dir, File sourceFile,
                               String newFileName, String oldFilePath) throws IOException {
        // 临时文件
        File tempFile = sourceFile;

        // 新头像的路径
        String newPath = FilePathUtils.getResourcePath() + dir + newFileName;
        FileUtils.copyFile(tempFile, new File(newPath));
    }*/
}
