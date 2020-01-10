package com.zsl.shareqrcodeimage.util;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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



    public static void main(String[] args) throws Exception {
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


    public static void createQrCode(String text, int width, int height, String format, String qrCodeName) throws Exception {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        File outputFile = new File(FilePathUtils.getTempFilePath() + File.separator + qrCodeName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }

}