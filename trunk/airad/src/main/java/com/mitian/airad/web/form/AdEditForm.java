package com.mitian.airad.web.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AdEditForm extends AbstractForm {
    private String x;
    private String y;
    private String w;
    private String h;
    private CommonsMultipartFile file;
    private String fileId;

    /**
     * @return the fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the x
     */
    public String getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public String getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(String y) {
        this.y = y;
    }

    /**
     * @return the w
     */
    public String getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(String w) {
        this.w = w;
    }

    /**
     * @return the h
     */
    public String getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(String h) {
        this.h = h;
    }

    /**
     * @return the file
     */
    public CommonsMultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    @Override
    public void form2domain() {
    }
}
