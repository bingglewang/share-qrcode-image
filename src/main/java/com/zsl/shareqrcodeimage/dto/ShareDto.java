package com.zsl.shareqrcodeimage.dto;

/**
 * @ClassName ShareDto
 * @Description TODO
 * @Author binggleW
 * @Date 2019-08-28 17:41
 * @Version 1.0
 **/
public class ShareDto {
    private String content;//二维码内容
    private String shareId;//用户shareid
    private int width;
    private int height;
    private int marginRight;
    private int marginBottom;

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }
}
