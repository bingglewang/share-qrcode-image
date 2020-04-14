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
    String mergeImageTorch(String qrcode,String nickName,String urlSrc,  String dianZan, String title1, String title2,String productName,String productDesc,String qrCodeName) throws  Exception;

    /**
     * （板根商城）
     *  用户，代理商 邀请海报生成
     * @param backUrl  背景图片地址
     * @param qrCodeContent  二维码内容
     * @param nickName  昵称
     * @param shareText  分享标题
     * @param destPath  图片保存路径（一般用户id，或者时间戳，或者能够唯一标识）
     * @return
     */
    String createUpUserShareImg(String backUrl,String qrCodeContent,String nickName,String shareText,String destPath) throws Exception;


    /**
     *  （板根商城）
     *  快洁安，板根分享海报
     * @param backUrl  背景图片地址
     * @param headImg  头像图片地址
     * @param qrCodeImg 二维码图片地址
     * @param nickName  昵称
     * @param shareText  分享标题
     * @param price  价格
     * @param productDesc  商品描述
     * @param destPath  图片保存地址（一般用户id，或者时间戳，或者能够唯一标识）
     * @return
     */
    String createUpProductShareImg(String backUrl,String headImg,String qrCodeImg,String nickName,String shareText,String price,String productDesc,String destPath)  throws Exception;
}
