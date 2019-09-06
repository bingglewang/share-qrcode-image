package com.zsl.shareqrcodeimage.util;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName HttpUtils
 * @Description TODO
 * @Author binggleW
 * @Date 2019-08-28 15:41
 * @Version 1.0
 **/
public class HttpUtils {
    public static String uploadImageToCos(String qrCodePath,String qrCodeName) {

        File file = new File(qrCodePath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MultipartFile multipartFile = null;
        try {
            multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Ueditor ueditor = UploadUtil.upload(multipartFile,qrCodeName);
        if(ueditor == null)
            return "";
        return ueditor.getUrl();
    }
}
