package com.mitian.airad.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 按最小边压缩
 * 
 * @author Administrator
 */
public class ImageDomain {
    private double imgWidth;// 图片宽

    private double imgHeight;// 图片高

    private double imgDemoWidth;// 图片demo宽

    private double imgDemoHeight;// 图片demo高

    private String imgLocalPatch;// 图片本地路径

    private int imgType;// 图片类型

    private int method;// 压缩方式

    private BufferedImage imgBuff;// 原图

    /**
     * 通过本地路径构造
     * 
     * @param imgLocalPatch
     */
    public ImageDomain(String imgLocalPatch) {
        this.imgLocalPatch = imgLocalPatch;
    }

    /**
     * 通过输入流构建
     * 
     * @param imgLocalPatch
     * @throws IOException
     */
    public ImageDomain(InputStream inputStream) throws IOException {
        imgBuff = javax.imageio.ImageIO.read(inputStream);
        imgWidth = imgBuff.getWidth(null);
        imgHeight = imgBuff.getHeight(null);
    }

    /**
     * 压缩图片
     * 
     * @param w
     * @param h
     * @param modality 0 等比最小 1 按宽 2按高 3定值
     * @throws IOException
     */
    public byte[] resize(int w, int h, int modality) throws IOException {
        imgDemoWidth = w;
        imgDemoHeight = h;
        // 得到合适的压缩大小，按比例。
        if (modality == 0) {
            if (imgWidth >= imgHeight) {
                h = (int) Math.round((imgHeight * w * 1.0 / imgWidth));
            }
            else {
                w = (int) Math.round((imgWidth * h * 1.0 / imgHeight));
            }
        }
        else if (modality == 1) {
            h = (int) Math.round((imgHeight * w * 1.0 / imgWidth));
        }

        // 构建图片对象
        BufferedImage _image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // 绘制缩小后的图
        _image.getGraphics().drawImage(imgBuff, 0, 0, w, h, null);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        ImageIO.write(_image, "jpg", bao);
        byte[] data = bao.toByteArray();
        // byte[] data = ((java.awt.image.DataBufferByte) _image.getData().getDataBuffer()).getData();

        return data;
        // 输出到文件流
        // FileOutputStream out = new FileOutputStream(destFile);
        // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        // encoder.encode(_image);
        // out.flush();
        // out.close();
    }

    /**
     * 保存图片
     * 
     * @param img 原图路径
     * @param dest 目标图路径
     * @param top 选择框的左边y坐标
     * @param left 选择框的左边x坐标
     * @param width 选择框宽度
     * @param height 选择框高度
     * @return
     * @throws IOException
     */
    public static boolean saveImage(File img, String dest, int top, int left, int width, int height) throws IOException {
        File fileDest = new File(dest);
        if (!fileDest.getParentFile().exists()) {
            fileDest.getParentFile().mkdirs();
        }
        String ext = getExtension(dest).toLowerCase();
        BufferedImage bi = ImageIO.read(img);
        height = Math.min(height, bi.getHeight());
        width = Math.min(width, bi.getWidth());
        if (height <= 0) {
            height = bi.getHeight();
        }
        if (width <= 0) {
            width = bi.getWidth();
        }
        top = Math.min(Math.max(0, top), bi.getHeight() - height);
        left = Math.min(Math.max(0, left), bi.getWidth() - width);

        BufferedImage bi_cropper = bi.getSubimage(left, top, width, height);
        return ImageIO.write(bi_cropper, ext.equals("png") ? "png" : "jpeg", fileDest);
    }

    public static String getExtension(File f) {
        return (f != null) ? getExtension(f.getName()) : "";
    }

    public static String getExtension(String filename) {
        return getExtension(filename, "");
    }

    public static String getExtension(String filename, String defExt) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');

            if ((i > -1) && (i < (filename.length() - 1))) {
                return filename.substring(i + 1);
            }
        }
        return defExt;
    }

    public static String trimExtension(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i > -1) && (i < (filename.length()))) {
                return filename.substring(0, i);
            }
        }
        return filename;
    }

    /**
     * @return the imgWidth
     */
    public double getImgWidth() {
        return imgWidth;
    }

    /**
     * @param imgWidth the imgWidth to set
     */
    public void setImgWidth(double imgWidth) {
        this.imgWidth = imgWidth;
    }

    /**
     * @return the imgHeight
     */
    public double getImgHeight() {
        return imgHeight;
    }

    /**
     * @param imgHeight the imgHeight to set
     */
    public void setImgHeight(double imgHeight) {
        this.imgHeight = imgHeight;
    }

    /**
     * @return the imgDemoWidth
     */
    public double getImgDemoWidth() {
        return imgDemoWidth;
    }

    /**
     * @param imgDemoWidth the imgDemoWidth to set
     */
    public void setImgDemoWidth(double imgDemoWidth) {
        this.imgDemoWidth = imgDemoWidth;
    }

    /**
     * @return the imgDemoHeight
     */
    public double getImgDemoHeight() {
        return imgDemoHeight;
    }

    /**
     * @param imgDemoHeight the imgDemoHeight to set
     */
    public void setImgDemoHeight(double imgDemoHeight) {
        this.imgDemoHeight = imgDemoHeight;
    }

    /**
     * @return the imgLocalPatch
     */
    public String getImgLocalPatch() {
        return imgLocalPatch;
    }

    /**
     * @param imgLocalPatch the imgLocalPatch to set
     */
    public void setImgLocalPatch(String imgLocalPatch) {
        this.imgLocalPatch = imgLocalPatch;
    }

    /**
     * @return the imgType
     */
    public int getImgType() {
        return imgType;
    }

    /**
     * @param imgType the imgType to set
     */
    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    /**
     * @return the method
     */
    public int getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     * @return the imgBuff
     */
    public BufferedImage getImgBuff() {
        return imgBuff;
    }

    /**
     * @param imgBuff the imgBuff to set
     */
    public void setImgBuff(BufferedImage imgBuff) {
        this.imgBuff = imgBuff;
    }
}
