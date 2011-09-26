package com.mitian.airad.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.utils.ImageDomain;
import com.mitian.airad.dao.LibImgDAO;
import com.mitian.airad.model.LibImg;
import com.mitian.airad.web.form.FileUploadingForm;

/**
 * FileUploadingService.java
 * 
 * @author baojun
 */
@Service
public class FileUploadingService {

    @Autowired
    private LibImgDAO libImgDAO;

    /**
     * 保存用户图片信息
     * 
     * @param file
     */
    public void txAddFileInfo(FileUploadingForm file) {
        // txAddImg(file);
        // saveMemberImg(file);
    }

    /**
     * 提交图片到图片库
     * 
     * @param file
     * @throws IOException
     */
    public int txAddImg(FileUploadingForm file) throws IOException {
        resize(file);
        int imgId = libImgDAO.insert(file.getImg());
        return imgId;
    }

    /**
     * 缩放图片
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public FileUploadingForm resize(FileUploadingForm file) throws IOException {
        ImageDomain img = new ImageDomain(file.getFileToUpload().getInputStream());

        byte[] bys = img.resize(file.getImgWidth(), file.getImgHeight(), 0);
        file.setImgCode(bys);
        return file;
    }

    /**
     * 获取图片
     * 
     * @param imgId
     * @return
     */
    public byte[] queryImgById(Integer imgId) {
        LibImg im = libImgDAO.selectByPrimaryKey(imgId);
        if (im != null) {
            return im.getImgCode();
        }
        return null;
    }

    /**
     * 提交用户图片到数据库
     * 
     * @param file
     */
    private void saveMemberImg(FileUploadingForm file) {
    }

}
