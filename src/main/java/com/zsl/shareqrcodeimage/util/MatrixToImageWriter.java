package com.zsl.shareqrcodeimage.util;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Hashtable;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import org.springframework.util.ResourceUtils;

/**
 * 二维码的生成需要借助MatrixToImageWriter类，该类是由Google提供的，可以将该类直接拷贝到源码中使用
 */
public class MatrixToImageWriter {
    private static final int BLACK = 0x00000000;
    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriter() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format "
                    + format + " to " + file);
        }
    }

    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    /**
     * 功能描述: <br>0
     * 〈利用画布生成新的图片〉
     *
     * @param backImage 背景图文件地址
     * @param srcImage  前景图文件地址
     * @param descImage 生成图文件地址
     * @return
     */
    public static void mergeImage(String backImage, String srcImage, String descImage, int marginRight, int marginBottom) {
        try {
            int offset = 0;
            BufferedImage backBufferedImage = ImageIO.read(new File(backImage));
            BufferedImage srcBufferedImage = ImageIO.read(new File(srcImage));

            int x = backBufferedImage.getWidth() - (srcBufferedImage.getWidth() + marginRight);
            int y = backBufferedImage.getHeight() - (srcBufferedImage.getHeight() + marginBottom);

            // 输出图片宽度
            int width = backBufferedImage.getWidth() + offset;
            // 输出图片高度
            int height = backBufferedImage.getHeight() + offset;
            BufferedImage descBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2d = (Graphics2D) descBufferedImage.getGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            // 往画布上添加图片,并设置边距
            graphics2d.drawImage(backBufferedImage, 0, 0, backBufferedImage.getWidth(), backBufferedImage.getHeight(), null);
            graphics2d.drawImage(srcBufferedImage, x, y, srcBufferedImage.getWidth(), srcBufferedImage.getHeight(), null);
            graphics2d.dispose();
            // 输出新图片
            ImageIO.write(descBufferedImage, "png", new File(descImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 功能描述: <br>1
     * 〈利用画布生成新的图片〉
     *
     * @return
     */
    public static void mergeImage1(String price, String title, String backImage, String srcImage, String descImage, int marginRight, int marginBottom) {
        try {
            int offset = 0;
            BufferedImage backBufferedImage = ImageIO.read(new File(backImage));
            BufferedImage srcBufferedImage = ImageIO.read(new File(srcImage));

            //int x = backBufferedImage.getWidth() - (srcBufferedImage.getWidth());
            //int y = backBufferedImage.getHeight();

            int x = 738;
            int y = 1038;

            // 输出图片宽度
            //int width = backBufferedImage.getWidth() + offset;
            int width = 1038;
            int height = 1338;
            // 输出图片高度
            //int height = backBufferedImage.getHeight() + srcBufferedImage.getHeight();
            Font font = new Font("宋体", Font.CENTER_BASELINE, 50);
            Font font1 = new Font("宋体", Font.BOLD, 45);
            BufferedImage descBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2d = (Graphics2D) descBufferedImage.getGraphics();
            Color color = new Color(255, 255, 255);
            graphics2d.setBackground(color);
            graphics2d.setPaint(Color.BLACK);
            graphics2d.setFont(font);
            graphics2d.clearRect(0, 0, width, height);//通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            // 往画布上添加图片,并设置边距

            graphics2d.drawImage(backBufferedImage, 0, 0, 1038, 1038, null);
            graphics2d.drawImage(srcBufferedImage, x, y, srcBufferedImage.getWidth(), srcBufferedImage.getHeight(), null);
            //graphics2d.drawString(title, 50, y + 80);
            int count =  drawString(graphics2d,font,title,50,y+60,680);
            graphics2d.setPaint(Color.red);
            graphics2d.setFont(font1);
            graphics2d.drawString(price, 50, (y + 60)+(count+1)*57);

            graphics2d.dispose();
            // 输出新图片
            ImageIO.write(descBufferedImage, "png", new File(descImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 功能描述: <br>2
     * 〈利用画布生成新的图片〉
     *
     * @return
     */
    public static void mergeImage2(String price, String title, String backImage, String srcImage, String descImage, int marginRight, int marginBottom) {
        try {
            int offset = 0;
            BufferedImage backBufferedImage = ImageIO.read(new File(backImage));
            BufferedImage srcBufferedImage = ImageIO.read(new File(srcImage));

            int x = backBufferedImage.getWidth();
            int y = backBufferedImage.getHeight();

            // 输出图片宽度
            int width =  x;
            int height = y;
            Font font = new Font("宋体", Font.BOLD, 14);
            Font font1 = new Font("宋体", Font.PLAIN, 13);
            BufferedImage descBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2d = (Graphics2D) descBufferedImage.getGraphics();
            Color color = new Color(255, 255, 255);
            graphics2d.setBackground(color);
            graphics2d.setPaint(new Color(144,93,73));
            graphics2d.setFont(font);
            graphics2d.clearRect(0, 0, width, height);//通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            // 往画布上添加图片,并设置边距

            graphics2d.drawImage(backBufferedImage, 0, 0,x,y, null);
            graphics2d.drawImage(srcBufferedImage, x-80, y-80, 60, 60, null);
            //graphics2d.drawString(title, 50, y + 80);
            int count =  drawString(graphics2d,font,title,20,y-100/2-15,300);
            graphics2d.setPaint(new Color(144,93,73));
            graphics2d.setFont(font1);
            graphics2d.drawString(price, 20, y-100/2 -15 +(count+1)*19);

            graphics2d.dispose();
            // 输出新图片
            ImageIO.write(descBufferedImage, "png", new File(descImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 功能描述: <br>2
     * 火炬315 海报生成
     *
     * @return
     */
    public static void mergeImage3(String backImage,String srcImage1,String srcImage2,String srcImage3,String targetImage,String nickaName,String dianZan,String title1,String title2,String productName,String productDesc,String changan,String phone,String weixin) {
        try {
            int offset = 0;
            BufferedImage backBufferedImage = ImageIO.read(new File(backImage));
            BufferedImage bufferSrcImage1 = ImageIO.read(new File(srcImage1));
            BufferedImage bufferSrcImage2 = ImageIO.read(new File(srcImage2));
            BufferedImage bufferSrcImage3 = ImageIO.read(new File(srcImage3));

            int x = backBufferedImage.getWidth();
            int y = backBufferedImage.getHeight();

            // 输出图片宽度
            int width =  x;
            int height = y;
            Font font = new Font("黑体", Font.BOLD, 14);
            Font font1 = new Font("黑体", Font.BOLD, 32);
            Font font2 = new Font("黑体", Font.BOLD, 40);
            BufferedImage descBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2d = (Graphics2D) descBufferedImage.getGraphics();
            Color color = new Color(255, 255, 255);
            graphics2d.setBackground(color);
            graphics2d.setPaint(new Color(144,93,73));
            graphics2d.setFont(font);
            graphics2d.clearRect(0, 0, width, height);//通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            // 往画布上添加图片,并设置边距

            //背景
            graphics2d.drawImage(backBufferedImage, 0, 0,x,y, null);
            graphics2d.setPaint(new Color(255,255,255));
            graphics2d.setFont(font1);
           //标题 1
           // int titleCount1 =  drawString(graphics2d,font,title1, 80,270,300);
            //标题 2
           // int titleCount2 =  drawString(graphics2d,font,title2, 80,270 + 50 - 30,300);


            //头像 (居中)
            graphics2d.drawImage(bufferSrcImage1, (x- (bufferSrcImage1.getWidth()-20)) / 2, 250 + 60 + 50+50, bufferSrcImage1.getWidth()-20, bufferSrcImage1.getHeight()-20, null);

            //昵称
            // 居中
            FontMetrics fm = graphics2d.getFontMetrics(font1);
            int textWidth = fm.stringWidth(nickaName);
            int nickx = (x- textWidth) / 2;

           int nickLenth = fontToCenter(nickaName);
            int nickCount =  drawString(graphics2d,font,nickaName,nickx,270+bufferSrcImage1.getHeight() + 60 + 50 +50,300);
            //int nickCount =  drawString(graphics2d,font,nickaName,x/2- bufferSrcImage1.getWidth()/2 - nickLenth,270+bufferSrcImage1.getHeight() + 60 + 50 +50,300);
            //点赞
           // graphics2d.drawImage(bufferSrcImage3, x/2+ bufferSrcImage1.getWidth()/2 + 30, 250 + bufferSrcImage3.getHeight()/2 + 60 + 50,bufferSrcImage3.getWidth() - 15,bufferSrcImage3.getHeight() - 15, null);
            //int dianCount =  drawString(graphics2d,font,dianZan,x/2+ bufferSrcImage1.getWidth()/2 +bufferSrcImage1.getWidth()-44,250 + bufferSrcImage3.getHeight()+4 + 60 + 50,300);
            //产品名称 （改成 第几位网络火炬传递手）
            graphics2d.setPaint(new Color(255,255,255));
            graphics2d.setFont(font2);

            String dianzanStr = dianZan.toString();
            int marginLeft = 0;
            if(dianzanStr.length() <= 3){
                marginLeft = 38;
            }else if(dianzanStr.length() == 4){
                marginLeft = 15;
            }else if(dianzanStr.length() == 5){
                marginLeft = 10;
            }
            drawString(graphics2d,font,"第 ",marginLeft + 40+60-25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80,300);
            drawString(graphics2d,font,dianZan+" ", marginLeft + 100+60-25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80,300);
           /* if(dianzanStr.length() <= 3){
                drawString(graphics2d,font,"_",100+40,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
                drawString(graphics2d,font,"_",100+50,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
                drawString(graphics2d,font,"_",100+60,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
                drawString(graphics2d,font,"_",100+70,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
                drawString(graphics2d,font,"_",100+80,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
                drawString(graphics2d,font,"位网络火炬传递手",200,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80,300);
            }else {

            }*/
            int dianzenLen = dianzanStr.length();

            for(int i = 0; i < dianzenLen;i++){
                if(dianzenLen == 1){
                    for(int j = 1; j <= 5;j++){
                        drawString(graphics2d,font,"_",marginLeft + 130+(i * 5 + j)*10-25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
                    }
                }else if (dianzenLen == 2){
                    for(int j = 1; j <= 4;j++){
                        drawString(graphics2d,font,"_",marginLeft + 130+(i * 4 + j)*10-25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
                    }
                }else if (dianzenLen >= 3){
                    if(i <= 6){
                        for(int j = 1; j <= 3;j++){
                            drawString(graphics2d,font,"_",marginLeft + 130+(i * 3 + j)*10-25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
                        }
                    }
                }
            }

            if(dianzenLen == 1){
                drawString(graphics2d,font,"位网络火炬传递手",marginLeft + 150 + dianzenLen * 50 -25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80,300);
            }else if (dianzenLen == 2){
                drawString(graphics2d,font,"位网络火炬传递手",marginLeft + 150 + dianzenLen * 40 -25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80,300);
            }else if (dianzenLen >= 3 ){
                if(dianzenLen >= 7){
                    drawString(graphics2d,font,"位网络火炬传递手",marginLeft + 150 + 7 * 30 -25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80,300);
                }else{
                    drawString(graphics2d,font,"位网络火炬传递手",marginLeft + 150 + dianzenLen * 30 -25,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80,300);
                }
            }


          /*  drawString(graphics2d,font,"_",100+40,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
            drawString(graphics2d,font,"_",100+50,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
            drawString(graphics2d,font,"_",100+60,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
            drawString(graphics2d,font,"_",100+70,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
            drawString(graphics2d,font,"_",100+80,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
            drawString(graphics2d,font,"_",100+90,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
            drawString(graphics2d,font,"_",100+100,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
            drawString(graphics2d,font,"_",100+110,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);
            drawString(graphics2d,font,"_",100+120,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +90,300);*/
           // drawString(graphics2d,font,"位网络火炬传递手",180+60,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80,300);
           // int productLenth = fontToCenter(productName);
            //int productCount =  drawString(graphics2d,font,productName,x/2- bufferSrcImage1.getWidth()/2 - productLenth - 30,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +100,300);
            // 产品介绍
          //  graphics2d.setPaint(new Color(255,255,255));
           // graphics2d.setFont(font2);
            //int productDescCount =  drawString1(graphics2d,font,productDesc, 40,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 + 50 -30,320);

            //下方二维码和公众号
            graphics2d.drawImage(bufferSrcImage2,x/2- bufferSrcImage2.getWidth()/2,270+bufferSrcImage1.getHeight() + 60 + 50 + 40 +80+40,bufferSrcImage2.getWidth(),bufferSrcImage2.getHeight(),null);
            graphics2d.setPaint(new Color(255,255,255));
            graphics2d.setFont(font1);
            //drawString(graphics2d,font,changan,40 + bufferSrcImage2.getWidth()*2 + 60,y- bufferSrcImage2.getWidth()*2+20,300);

            //graphics2d.setPaint(new Color(255,255,255));
           // graphics2d.setFont(font2);
            //drawString(graphics2d,font,phone,40 + bufferSrcImage2.getWidth()*2 + 60,y- bufferSrcImage2.getWidth()*2+20 + 60-30,300);
            //drawString(graphics2d,font,weixin,40 + bufferSrcImage2.getWidth()*2 + 60,y- bufferSrcImage2.getWidth()*2+20 + 60 + 40 -30,300);

           // drawString(graphics2d,font,"了解更多",40 + bufferSrcImage2.getWidth()*2 + 60,y- bufferSrcImage2.getWidth()*2+20 + 60-30,300);
           // drawString(graphics2d,font,"关注【中追溯源】公众号",40 + bufferSrcImage2.getWidth()*2 + 60,y- bufferSrcImage2.getWidth()*2+20 + 60 + 40 -30,300);


            graphics2d.dispose();
            // 输出新图片
            ImageIO.write(descBufferedImage, "png", new File(targetImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * （板根商城）
     *  用户，代理商 邀请海报生成
     * @param backImage 海报背景图
     * @param qrCodeImage 二维码图片
     * @param nickName 昵称
     * @param shareText 分享标题
     * @param descImage 生成图片能保存路径
     * @return
     */
    public static void mergeImage4(String backImage, String qrCodeImage, String nickName,String shareText,String descImage) {
        try {
            BufferedImage backBufferedImage = ImageIO.read(new File(backImage));
            BufferedImage qrCodeBufferedImage = ImageIO.read(new File(qrCodeImage));

            int x = backBufferedImage.getWidth();
            int y = backBufferedImage.getHeight();

            // 输出图片宽度
            int width =  x;
            int height = y + 120;
            Font font = new Font("黑体", Font.PLAIN, 18);
            Font font1 = new Font("黑体", Font.BOLD, 22);
            BufferedImage descBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2d = (Graphics2D) descBufferedImage.getGraphics();
            Color color = new Color(255, 255, 255);
            graphics2d.setBackground(color);

            graphics2d.clearRect(0, 0, width, height);//通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            // 往画布上添加图片,并设置边距
            //背景图
            graphics2d.drawImage(backBufferedImage, 0, 0,x,y, null);
            //二维码
            graphics2d.drawImage(qrCodeBufferedImage, 0+30, y+10,qrCodeBufferedImage.getWidth(),qrCodeBufferedImage.getHeight(), null);
            //昵称
            graphics2d.setPaint(new Color(38, 50, 137));
            graphics2d.setFont(font1);
            graphics2d.drawString(nickName,0+35+qrCodeBufferedImage.getWidth() + 24,y+20+30);
            //分享标题
            graphics2d.setPaint(new Color(34, 32, 32));
            graphics2d.setFont(font);
            graphics2d.drawString(shareText,0+35+qrCodeBufferedImage.getWidth() + 24,y+20+20+40);

            graphics2d.dispose();
            // 输出新图片
            ImageIO.write(descBufferedImage, "png", new File(descImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  （板根商城）
     *  快洁安，板根分享海报
     * @param backImg 海报背景图
     * @param headImg 头像
     * @param nickName 昵称
     * @param shareText 分享标题
     * @param price 商品价格
     * @param productDesc 商品描述
     * @param qrCodeImg 二维码图片
     * @param descImage 生成图片能保存路径
     * @return
     */
    public static void mergeImage5(String backImg,String headImg, String nickName, String shareText, String price, String productDesc,String qrCodeImg,String descImage) {
        try {

            String middleCircleImg = mergeCircleImg(backImg,price,productDesc,qrCodeImg);
            BufferedImage backTmpBufferedImage = ImageIO.read(new File(middleCircleImg));
            // 生成的图片位置
            String tmpMiddleSave = FilePathUtils.getTempFilePath() + File.separator + "tmpMiddleSave.png";
            String middleBackImg = makeCircularImg(middleCircleImg,tmpMiddleSave,backTmpBufferedImage.getWidth(),backTmpBufferedImage.getHeight(),40);
            // 中间背景图片
            BufferedImage backBufferedImage = ImageIO.read(new File(middleBackImg));
            // 头像
            BufferedImage headBufferedImage = ImageIO.read(new File(headImg));

            int x = backBufferedImage.getWidth();
            int y = backBufferedImage.getHeight();

            // 输出图片宽度
            int width =  x+40;
            int height = y + 145;
            Font font = new Font("黑体", Font.PLAIN, 18);
            Font font1 = new Font("黑体", Font.BOLD, 22);
            BufferedImage descBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2d = (Graphics2D) descBufferedImage.getGraphics();
            Color color = new Color(255, 255, 255);
            graphics2d.setBackground(color);

            graphics2d.clearRect(0, 0, width, height);//通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            // 往画布上添加图片,并设置边距
            //背景图
            BufferedImage shadow = createDropShadow(backBufferedImage,4);
            graphics2d.drawImage(shadow, 12, 102,x,y, null);
            graphics2d.drawImage(backBufferedImage, 20, 110,x,y, null);
            //头像
            graphics2d.drawImage(headBufferedImage, 20, 20,headBufferedImage.getWidth(),headBufferedImage.getHeight(), null);
            //昵称
            graphics2d.setPaint(new Color(29, 28, 28));
            graphics2d.setFont(font1);
            graphics2d.drawString(nickName,20+headBufferedImage.getWidth() + 24,50);
            //分享标题
            graphics2d.setPaint(new Color(167, 165, 166));
            graphics2d.setFont(font);
            graphics2d.drawString(shareText,20+headBufferedImage.getWidth() + 24,78);

            graphics2d.dispose();
            // 输出新图片
            ImageIO.write(descBufferedImage, "png", new File(descImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception{
        // 生成二维码
        String qrCodeContent = "https://zs-1256645015.cos.ap-guangzhou.myqcloud.com/trace/2020/3/13/c139b00d-759f-4e8b-a2d0-ccc14db099e0.jpg";
        BufferedImage urlFile2 =
                ImgToCircleUtil.getUrlByBufferedImage(qrCodeContent);
        BufferedImage convertImageFile2 = ImgToCircleUtil.scaleByPercentage(urlFile2, 100, 100);
        convertImageFile2 = ImgToCircleUtil.convertCircular(convertImageFile2);
        // 生成的图片位置 ( 二维码)
        String qrCodePath = FilePathUtils.getTempFilePath() + File.separator + "upQrCode.png";
        ImageIO.write(convertImageFile2, qrCodePath.substring(qrCodePath.lastIndexOf(".") + 1), new File(qrCodePath));


        //背景图片路径
        File file = ResourceUtils.getFile("classpath:static/image/upUserBack.png");
        String backImg = file.getPath();
        String nickName = "基督教大家";
        String shareText = "解决房价发生了附近昆仑山地方";
        // 生成的图片位置
        String destPath = FilePathUtils.getTempFilePath() + File.separator + "upUserShare.png";
        mergeImage4(backImg,qrCodePath,nickName,shareText,destPath);
    }


  /*  public static void main(String[] args) throws Exception{
        String text = "sdfsdfsdf"; // 二维码内容
        int width = 200; // 二维码图片宽度
        int height = 200; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式
        String qrCodeName = "upFastQrCode.jpg";
        createUPQrCode(text, width, height, format, qrCodeName);
        //二维码路径
        String qrCodePath = FilePathUtils.getTempFilePath()+File.separator+qrCodeName;
        //背景图片路径
        File file = ResourceUtils.getFile("classpath:static/image/upFastBack.png");
        String backImg = file.getPath();
        String price = "￥29.9";
        String productDesc = "【快洁安】次氯酸免洗多用途消毒健康喷剂100ml";

        // 头像
        String urlSrc = "http://thirdwx.qlogo.cn/mmopen/vi_32/zrH3ibmkZB6sjwphQe5EQkArqxUPsenB78vLjSgQIcWrNhn99t4yCZPeDXU2gUHtibUgz31q9njJLR5sRlkwsHQQ/132";
        // http://avatar.csdn.net/3/1/7/1_qq_27292113.jpg?1488183229974
        // 是头像地址
        // 获取图片的流
        BufferedImage url =
                ImgToCircleUtil.getUrlByBufferedImage(urlSrc);

        // 处理图片将其压缩成正方形的小图
        BufferedImage convertImage = ImgToCircleUtil.scaleByPercentage(url, 20, 20);
        // 裁剪成圆形 （传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的）
        convertImage = ImgToCircleUtil.convertCircular(url);
        // 生成的图片位置
        String imagePath = FilePathUtils.getTempFilePath() + File.separator + "touxiangUp.png";

        ImageIO.write(convertImage, imagePath.substring(imagePath.lastIndexOf(".") + 1), new File(imagePath));
        //昵称
        String nickName = "对方正在输入...";
        String shareText = "范德萨就开了分手的借口了";
        // 生成的图片位置
        String destPath = FilePathUtils.getTempFilePath() + File.separator + "upFastShareImg.png";
        mergeImage5(backImg,imagePath,nickName,shareText,price,productDesc,qrCodePath,destPath);
    }*/



    /**
     *  （板根商城）
     *  快洁安，板根分享海报  (中间圆角图片)
     * @param backImg 海报背景图
     * @param price 商品价格
     * @param productDesc 商品描述
     * @param qrCodeImg 二维码图片
     * @return 生成图片路径
     */
    public static String mergeCircleImg(String backImg,String price, String productDesc,String qrCodeImg) {
        try {
            BufferedImage backBufferedImage = ImageIO.read(new File(backImg));
            BufferedImage qrCodeBufferedImage = ImageIO.read(new File(qrCodeImg));

            int x = backBufferedImage.getWidth();
            int y = backBufferedImage.getHeight();

            // 输出图片宽度
            int width =  x;
            int height = y + 170;
            Font font = new Font("黑体", Font.BOLD, 18);
            Font font1 = new Font("黑体", Font.BOLD, 30);
            BufferedImage descBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics2d = (Graphics2D) descBufferedImage.getGraphics();
            Color color = new Color(250, 250, 250);
            graphics2d.setBackground(color);

            graphics2d.clearRect(0, 0, width, height);//通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            // 往画布上添加图片,并设置边距
            //背景图
            graphics2d.drawImage(backBufferedImage, 0, 0,x,y, null);
            //二维码
            graphics2d.drawImage(qrCodeBufferedImage, x - qrCodeBufferedImage.getWidth() - 25, y+10,qrCodeBufferedImage.getWidth(),qrCodeBufferedImage.getHeight(), null);
            //价格
            graphics2d.setPaint(new Color(91, 142, 219));
            graphics2d.setFont(font1);
            graphics2d.drawString(price,  24,y+20+20);
            //商品描述
            graphics2d.setPaint(new Color(34, 32, 32));
            graphics2d.setFont(font);
            drawString(graphics2d,font, productDesc,24,y+20+20+30,230);

            graphics2d.dispose();

            // 生成的图片位置
            String destPath = FilePathUtils.getTempFilePath() + File.separator + "middleCircleImg.png";
            // 输出新图片
            ImageIO.write(descBufferedImage, "png", new File(destPath));
            return destPath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }




   /* public static void main(String[] args) throws Exception {
        String text = "shabi"; // 二维码内容
        int width = 100; // 二维码图片宽度
        int height = 100; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式
        String qrCodeName = "new.jpg";
        String title = "恭喜您！获得十月芬芳牙膏套装";
        //String backUrl = "https://zs-1256645015.cos.ap-guangzhou.myqcloud.com/trace/2019/6/16/921bab3c-fe3f-4445-af55-14f477d10675.jpg";
        String backUrl = "https://zs-1256645015.cos.ap-guangzhou.myqcloud.com/trace/2020/1/9/02383ca1-933c-46ab-86e3-d8eec98d0ad1.png";
        //createQrCode(text, width, height, format, qrCodeName);
        String srcUrl = "https://zs-1256645015.cos.ap-guangzhou.myqcloud.com/trace/2020/1/9/5dd864ef-bcdc-46cd-8e5f-6620102cd0eb.jpg";

        String urlSrc = srcUrl;
        String url = backUrl;
        String path = FilePathUtils.getTempFilePath() + File.separator;
        String res = DownloadURLFile.downloadFromUrl(url, path);
        String res1 = DownloadURLFile.downloadFromUrl(urlSrc, path);
        String name1 = DownloadURLFile.getFileNameFromUrl(urlSrc);
        String name = DownloadURLFile.getFileNameFromUrl(url);
        File file = new File(path + name);
        File file1 = new File(path + name1);
        String price = "请添加官方客服微信号领取：z16606636065";

        String qrCodePath = FilePathUtils.getTempFilePath() + File.separator + qrCodeName;
        mergeImage2(price, title, file.getPath(), file1.getPath(), qrCodePath, 250, 250);
    }*/

    /*public static void main(String[] args) throws Exception{
        String qrCodeName = "new315.jpg";

        String nickName = "parker";
        String dianZan = "123443";
        String title1 = "统计截至：2019-12-23 14:23";
        String title2 = "全国排名13名，广东省排名213名";
        String productName = "产品名称：XXXXX产品";
        String productDesc = "产品介绍：Lorem ip sum dolor sit amet,consectetur adipiscing elit,sed o eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis ipsum suspendisse ul-trices gravida.";

        String changan = "长按保存图片";
        String phone = "公司电话：020-3142 0284";
        String weixin = "公司公众号：中追溯源";

        File file3 = ResourceUtils.getFile("classpath:static/image/back3.png");
        File file2 = ResourceUtils.getFile("classpath:static/image/back2.png");
        File file4 = ResourceUtils.getFile("classpath:static/image/back4.png");


        String urlSrc = "http://thirdwx.qlogo.cn/mmopen/vi_32/zrH3ibmkZB6sjwphQe5EQkArqxUPsenB78vLjSgQIcWrNhn99t4yCZPeDXU2gUHtibUgz31q9njJLR5sRlkwsHQQ/132";
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
        mergeImage3(file3.getPath(),imagePath , file2.getPath(), file4.getPath(), qrCodePath,nickName,dianZan,title1,title2,productName,productDesc,changan,phone,weixin);
    }
*/

    public static int fontToCenter(String str){
        int length = str.length();
        int nickLenth = 0;
      /*  if(length > 3){
            nickLenth = 10 * (length - 2);
        }else if(length == 2){
            nickLenth = -20;
        }else if(length == 1){
            nickLenth = -40;
        }*/
      char[] strChars = str.toCharArray();
      for(int i = 0; i < length; i++){
        if(isChinese(strChars[i])){
            if(length > 3){
                if(i > 1){
                    nickLenth += 10;
                }
            }else if(length == 2){
                nickLenth = -20;
            }else if(length == 1){
                nickLenth = -40;
            }
        }else{
           if(length == 2){
                nickLenth = -20;
            }else if(length == 1){
                nickLenth = -40;
            }
        }
      }
      return nickLenth;
    }



    /**
     * 是否是中文
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {

        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;

        }

        return false;

    }


    public static int drawString(Graphics2D g, Font font, String text, int x, int y, int maxWidth) {

        int count = 0;

        JLabel label = new JLabel(text);
        label.setFont(font);
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        int textH = metrics.getHeight();
        int textW = metrics.stringWidth(label.getText()); //字符串的宽
        String tempText = text;

        while (textW > maxWidth) {
            count++;
            int n = textW / maxWidth;
            int subPos = tempText.length() / n;
            String drawText = tempText.substring(0, subPos);
            int subTxtW = metrics.stringWidth(drawText);
            while (subTxtW > maxWidth) {
                subPos--;
                drawText = tempText.substring(0, subPos);
                subTxtW = metrics.stringWidth(drawText);
            }
            g.drawString(drawText, x, y);
            y += textH;
            textW = textW - subTxtW;
            tempText = tempText.substring(subPos);
        }

        g.drawString(tempText, x, y);
        return count;
    }


    public static int drawString1(Graphics2D g, Font font, String text, int x, int y, int maxWidth) {

        int count = 0;

        JLabel label = new JLabel(text);
        label.setFont(font);
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        int textH = metrics.getHeight()+12;
        int textW = metrics.stringWidth(label.getText()); //字符串的宽
        String tempText = text;

        while (textW > maxWidth) {
            count++;
            int n = textW / maxWidth;
            int subPos = tempText.length() / n;
            String drawText = tempText.substring(0, subPos);
            int subTxtW = metrics.stringWidth(drawText);
            while (subTxtW > maxWidth) {
                subPos--;
                drawText = tempText.substring(0, subPos);
                subTxtW = metrics.stringWidth(drawText);
            }
            g.drawString(drawText, x, y);
            y += textH;
            textW = textW - subTxtW;
            tempText = tempText.substring(subPos);
        }

        g.drawString(tempText, x, y);
        return count;
    }


    public static void createQrCode(String text, int width, int height, String format, String qrCodeName) throws Exception {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        File outputFile = new File(FilePathUtils.getTempFilePath() + File.separator + qrCodeName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }


    /**
     * 创建板根商城二维码
     * @param text
     * @param width
     * @param height
     * @param format
     * @param qrCodeName
     * @throws Exception
     */
    public static void createUPQrCode(String text, int width, int height, String format, String qrCodeName) throws Exception {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height, hints);
        bitMatrix = deleteWhite(bitMatrix);
        // 生成二维码
        File outputFile = new File(FilePathUtils.getTempFilePath() + File.separator + qrCodeName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }


    /**
     * 去除二维码白色边边
     * @param matrix
     * @return
     */
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }


    /***
     *
     * @param srcFilePath 源图片文件路径
     * @param circularImgSavePath 新生成的图片的保存路径，需要带有保存的文件名和后缀
     * @param widthSize 文件的边长，单位：像素，最终得到的是一张正方形的图，所以要求targetSize<=源文件的最小边长
     * @param cornerRadius 圆角半径，单位：像素。如果=targetSize那么得到的是圆形图
     * @return  文件的保存路径
     * @throws IOException
     */
    public static String makeCircularImg(String srcFilePath, String circularImgSavePath,int widthSize,int heightSize, int cornerRadius) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(srcFilePath));
        BufferedImage circularBufferImage = roundImage(bufferedImage,widthSize,heightSize,cornerRadius);
        ImageIO.write(circularBufferImage, "png", new File(circularImgSavePath));
        return circularImgSavePath;
    }

    private static BufferedImage roundImage(BufferedImage image, int widthSize,int heightSize, int cornerRadius) {
        BufferedImage outputImage = new BufferedImage(widthSize, heightSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = outputImage.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, widthSize, heightSize, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return outputImage;
    }


    /**
     * 给指定图片创建阴影
     * @param image
     * @param size
     * @return
     */
    public static BufferedImage createDropShadow(BufferedImage image,
                                                 int size) {
        BufferedImage shadow = new BufferedImage(image.getWidth() + 4
                * size, image.getHeight() + 4 * size,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = shadow.createGraphics();
        g2.drawImage(image, size * 2, size * 2, null);

        g2.setComposite(AlphaComposite.SrcIn);
        g2.setColor(Color.gray);
        g2.fillRoundRect(0, 0, shadow.getWidth(), shadow.getHeight(), 30,
                30);
        g2.dispose();
        shadow = getGaussianBlurFilter(size, true).filter(shadow, null);
        shadow = getGaussianBlurFilter(size, false).filter(shadow, null);
        return shadow;
    }

    public static ConvolveOp getGaussianBlurFilter(int radius,
                                                   boolean horizontal) {
        if (radius < 1) {
            throw new IllegalArgumentException("Radius must be >= 1");
        }
        int size = radius * 2 + 1;
        float[] data = new float[size];
        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;
        for (int i = -radius; i <= radius; i++) {
            float distance = i * i;
            int index = i + radius;
            data[index] = (float) Math.exp(-distance / twoSigmaSquare)
                    / sigmaRoot;
            total += data[index];
        }
        for (int i = 0; i < data.length; i++) {
            data[i] /= total;
        }
        Kernel kernel;
        if (horizontal) {
            kernel = new Kernel(size, 1, data);
        } else {
            kernel = new Kernel(1, size, data);
        }
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }
}