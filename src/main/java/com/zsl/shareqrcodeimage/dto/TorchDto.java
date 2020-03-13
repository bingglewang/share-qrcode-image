package com.zsl.shareqrcodeimage.dto;

public class TorchDto {
    private String qrcode; //二维码路径
    private String nickName;  // 昵称
    private String headImg;   //头像
    private String preferCount; //点赞数
    private String title1; // 统计截止
    private String title2; //全国排名，全省排名
    private String productName; //产品名称
    private String productDesc; //产品描述
    private String shareId;//用户shareid

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPreferCount() {
        return preferCount;
    }

    public void setPreferCount(String preferCount) {
        this.preferCount = preferCount;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
