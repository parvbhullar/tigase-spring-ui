package com.mitian.airad.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.mitian.airad.model.LibImg;

/**
 * FileUploadingForm.java
 * 
 * @author baojun
 */
public class FileUploadingForm extends AbstractForm {

    private MultipartFile fileToUpload;
    private String comment;// 文件备注
    private String folder;// 文件保存路径
    private String uploadWay;// 文件保存方式：file，database,默认file
    private String uploadedFileName; // 已经上传的文件名（带路径）
    private Integer imgType;// 图片类型
    private Integer sourceType;// 来源类型
    private Integer imgTypeId;// 图片类型id
    private Integer imgId;// 图片id
    private byte[] imgCode;
    private LibImg img;
    private Integer imgWidth;// 图片高
    private Integer imgHeight;// 图片宽
    private String[] imgWidthArray;// 图片高组
    private String[] imgHeightArray;// 图片宽组
    private String[] sourceTypeArray;// 来源类型组

    // 图片信息

    public MultipartFile getFileToUpload() {
        return fileToUpload;
    }

    public void setFileToUpload(MultipartFile fileToUpload) {
        this.fileToUpload = fileToUpload;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getUploadWay() {
        return uploadWay;
    }

    public void setUploadWay(String uploadWay) {
        this.uploadWay = uploadWay;
    }

    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public void setImgCode(byte[] imgCode) {
        this.imgCode = imgCode;
    }

    public byte[] getImgCode() {
        return imgCode;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgTypeId(Integer imgTypeId) {
        this.imgTypeId = imgTypeId;
    }

    public Integer getImgTypeId() {
        return imgTypeId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getImgId() {
        return imgId;
    }

    /**
     * @param sourceType the sourceType to set
     */
    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @return the sourceType
     */
    public Integer getSourceType() {
        return sourceType;
    }

    /**
     * @return the img
     */
    public LibImg getImg() {
        form2domain();
        return img;
    }

    /**
     * @param imgWidth the imgWidth to set
     */
    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    /**
     * @return the imgWidth
     */
    public Integer getImgWidth() {
        return imgWidth;
    }

    /**
     * @param imgHeight the imgHeight to set
     */
    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }

    /**
     * @return the imgHeight
     */
    public Integer getImgHeight() {
        return imgHeight;
    }

    @Override
    public void form2domain() {
        img = new LibImg();
        img.setSourceType(sourceType);
        img.setImgName(getFileToUpload().getName());
        img.setImgCode(imgCode);
    }

    /**
     * @param sourceTypeArray the sourceTypeArray to set
     */
    public void setSourceTypeArray(String[] sourceTypeArray) {
        this.sourceTypeArray = sourceTypeArray;
    }

    /**
     * @return the sourceTypeArray
     */
    public String[] getSourceTypeArray() {
        return sourceTypeArray;
    }

    /**
     * @param imgWidthArray the imgWidthArray to set
     */
    public void setImgWidthArray(String[] imgWidthArray) {
        this.imgWidthArray = imgWidthArray;
    }

    /**
     * @return the imgWidthArray
     */
    public String[] getImgWidthArray() {
        return imgWidthArray;
    }

    /**
     * @param imgHeightArray the imgHeightArray to set
     */
    public void setImgHeightArray(String[] imgHeightArray) {
        this.imgHeightArray = imgHeightArray;
    }

    /**
     * @return the imgHeightArray
     */
    public String[] getImgHeightArray() {
        return imgHeightArray;
    }

}
