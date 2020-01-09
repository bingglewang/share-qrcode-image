package com.zsl.shareqrcodeimage.service;

/**
 * @ClassName ShareService
 * @Description TODO
 * @Author binggleW
 * @Date 2019-08-28 17:27
 * @Version 1.0
 **/
public interface ShareService {

    /**
     * 生成海报
     * @param text
     * @param qrCodeName
     * @return
     */
    String createShareImage(String text,String qrCodeName,int width,int height,int marginRight,int marginBottom);


    String mergeImageQrcode(String backUrl,String text,String qrCodeName,int width,int height,int marginRight,int marginBottom);


    String detailShareImage(String price,String title,String backUrl, String text,String qrCodeName,int width,int height,int marginRight,int marginBottom);

    String mergeImageTree(String price,String title,String backUrl,String srcUrl,String qrCodeName);
}
