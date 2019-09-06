package com.zsl.shareqrcodeimage.service.impl;

import com.zsl.shareqrcodeimage.service.ShareService;
import com.zsl.shareqrcodeimage.util.FilePathUtils;
import com.zsl.shareqrcodeimage.util.HttpUtils;
import com.zsl.shareqrcodeimage.util.MatrixToImageWriter;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @ClassName SahreServiceImpl
 * @Description TODO
 * @Author binggleW
 * @Date 2019-08-28 17:29
 * @Version 1.0
 **/
@Service
public class SahreServiceImpl implements ShareService {
    @Override
    public String createShareImage(String text,String qrCodeName,int width,int height,int marginRight,int marginBottom) {
        if(width == 0){
            width = 250;
        }
        if(height == 0){
            height = 250;
        }
        //String text = "shabi"; // 二维码内容
        //int width = 250; // 二维码图片宽度
        //int height = 250; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式
        //String qrCodeName = "new.jpg";
        try {
            MatrixToImageWriter.createQrCode(text,width,height,format,qrCodeName);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/image/back1.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        String qrCodePath = FilePathUtils.getTempFilePath()+File.separator+qrCodeName;
        MatrixToImageWriter.mergeImage(file.getPath(),qrCodePath,qrCodePath,marginRight,marginBottom);
        String haibaoPath = HttpUtils.uploadImageToCos(qrCodePath,qrCodeName);

        File file1 = new File(qrCodePath);
        //如果存在则删除
        if(file1.exists()){
            file1.delete();
        }
        return haibaoPath;
    }
}
