package com.zsl.shareqrcodeimage.service.impl;

import com.zsl.shareqrcodeimage.service.ShareService;
import com.zsl.shareqrcodeimage.util.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

    @Override
    public String mergeImageQrcode(String backUrl,String text, String qrCodeName, int width, int height, int marginRight, int marginBottom) {
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
            String url = backUrl;
            String path = FilePathUtils.getTempFilePath() + File.separator;
            String res = DownloadURLFile.downloadFromUrl(url, path);
            String name = DownloadURLFile.getFileNameFromUrl(url);
            file = new File(path+name);
        } catch (Exception e) {
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

    @Override
    public String detailShareImage(String price,String title,String backUrl, String text, String qrCodeName, int width, int height, int marginRight, int marginBottom) {
        if(width == 0){
            width = 250;
        }
        if(height == 0){
            height = 250;
        }
        String format = "jpg";// 二维码的图片格式
        try {
            MatrixToImageWriter.createQrCode(text,width,height,format,qrCodeName);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        File file = null;
        try {
            String url = backUrl;
            String path = FilePathUtils.getTempFilePath() + File.separator;
            String res = DownloadURLFile.downloadFromUrl(url, path);
            String name = DownloadURLFile.getFileNameFromUrl(url);
            file = new File(path+name);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        String qrCodePath = FilePathUtils.getTempFilePath()+File.separator+qrCodeName;
        MatrixToImageWriter.mergeImage1(price,title,file.getPath(),qrCodePath,qrCodePath,marginRight,marginBottom);
        String haibaoPath = HttpUtils.uploadImageToCos(qrCodePath,qrCodeName);

        File file1 = new File(qrCodePath);
        //如果存在则删除
        if(file1.exists()){
            file1.delete();
        }
        return haibaoPath;
    }

    @Override
    public String mergeImageTree(String price, String title, String backUrl, String srcUrl,String qrCodeName) {
        File file = null;
        File file1 = null;
        try {
            String url = backUrl;
            String urlSrc = srcUrl;
            String path = FilePathUtils.getTempFilePath() + File.separator;
            String res = DownloadURLFile.downloadFromUrl(url, path);
            String res1 = DownloadURLFile.downloadFromUrl(urlSrc, path);
            String name = DownloadURLFile.getFileNameFromUrl(url);
            String name1 = DownloadURLFile.getFileNameFromUrl(urlSrc);
            file = new File(path+name);
            file1 = new File(path + name1);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        String qrCodePath = FilePathUtils.getTempFilePath()+File.separator+qrCodeName;
        MatrixToImageWriter.mergeImage2(price,title,file.getPath(),file1.getPath(),qrCodePath,0,0);
        String haibaoPath = HttpUtils.uploadImageToCos(qrCodePath,qrCodeName);

        File file2 = new File(qrCodePath);
        //如果存在则删除
        if(file2.exists()){
            file2.delete();
        }
        return haibaoPath;
    }


    @Override
    public String mergeImageTorch(String qrcode,String nickName,String urlSrc,  String dianZan, String title1, String title2,String productName,String productDesc,String qrCodeName) throws  Exception{

        String changan = "长按保存图片";
        String phone = "公司电话：020-3142 0284";
        String weixin = "公司公众号：中追溯源";

        File file3 = ResourceUtils.getFile("classpath:static/image/back3.png");

        // 获取图片的流
        BufferedImage urlFile2 =
                ImgToCircleUtil.getUrlByBufferedImage(qrcode);
        // 处理图片将其压缩成正方形的小图
        BufferedImage convertImageFile2 = ImgToCircleUtil.scaleByPercentage(urlFile2, 90, 90);
        // 生成的图片位置
        String imagePathFile2 = FilePathUtils.getTempFilePath() + File.separator + "qrcode.png";

        ImageIO.write(convertImageFile2, imagePathFile2.substring(imagePathFile2.lastIndexOf(".") + 1), new File(imagePathFile2));


        //File file2 = ResourceUtils.getFile("classpath:static/image/back2.png");
        File file4 = ResourceUtils.getFile("classpath:static/image/back4.png");



        // http://avatar.csdn.net/3/1/7/1_qq_27292113.jpg?1488183229974
        // 是头像地址
        // 获取图片的流
        BufferedImage url =
                ImgToCircleUtil.getUrlByBufferedImage(urlSrc);

        // 处理图片将其压缩成正方形的小图
        BufferedImage convertImage = ImgToCircleUtil.scaleByPercentage(url, 100, 100);
        // 裁剪成圆形 （传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的）
        convertImage = ImgToCircleUtil.convertCircular(url);
        // 生成的图片位置
        String imagePath = FilePathUtils.getTempFilePath() + File.separator + "touxiang.png";

        ImageIO.write(convertImage, imagePath.substring(imagePath.lastIndexOf(".") + 1), new File(imagePath));


        String qrCodePath = FilePathUtils.getTempFilePath() + File.separator + qrCodeName;
        MatrixToImageWriter.mergeImage3(file3.getPath(),imagePath , imagePathFile2, file4.getPath(), qrCodePath,nickName,dianZan,title1,title2,productName,productDesc,changan,phone,weixin);

        String haibaoPath = HttpUtils.uploadImageToCos(qrCodePath,qrCodeName);

        File fileDelete = new File(qrCodePath);
        //如果存在则删除
        if(fileDelete.exists()){
            fileDelete.delete();
        }
        return haibaoPath;
    }
}
