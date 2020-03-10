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

    /**
     * 火炬海报生成
     * @param nickName 昵称
     * @param urlSrc 头像
     * @param dianZan 点赞数
     * @param title1 截止标题
     * @param title2 全国排名
     * @param productName 产品名称
     * @param productDesc 产品描述
     * @param qrCodeName 海报名称
     * @return
     * @throws Exception
     */
    String mergeImageTorch(String nickName,String urlSrc,  String dianZan, String title1, String title2,String productName,String productDesc,String qrCodeName) throws  Exception;
}
