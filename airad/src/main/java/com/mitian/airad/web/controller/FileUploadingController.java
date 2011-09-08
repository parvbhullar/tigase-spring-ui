package com.mitian.airad.web.controller;

/*
 * Copyright 2010 Focus Technology, Co., Ltd. All rights reserved.
 */

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mitian.airad.service.FileUploadingService;
import com.mitian.airad.web.form.FileUploadingForm;

@Controller
@RequestMapping("/fileuploading.do")
public class FileUploadingController extends AbstractController {

    // @Override
    // public String toString() {
    // return new ToStringBuilder(this).append("fileToUpload", fileToUpload)
    // .append("comment", comment).toString();
    // }
    @Autowired
    private FileUploadingService fileService;

    /**
     * 上传图片
     * 
     * @param request
     * @param response
     * @param file
     */
    @RequestMapping(params = "action=upload")
    public void upload(HttpServletRequest request, HttpServletResponse response, FileUploadingForm file) {
        MultipartFile multipartFile = file.getFileToUpload();
        String uiId = request.getParameter("uiId");
        try {
            byte[] fileContent;
            fileContent = multipartFile.getBytes();
            // file.setImgType(Integer.parseInt("2"));// banner 原图
            file.setImgCode(fileContent);
            int imgId = fileService.txAddImg(file);

            StringBuilder uploadDir = new StringBuilder(File.separator + imgId);
            response.setContentType("application/json; charset=UTF-8");
            if (uiId != null) {
                // 获取页面控件标识+上传图片id
                response.getWriter().print(uiId + "," + imgId);
            }
            else {
                response.getWriter().print(uploadDir);
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 获取图片
     * 
     * @param request
     * @param response
     * @param file
     */
    @RequestMapping(params = "action=download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException,
            Exception {
        String imgId = request.getParameter("fileId");
        if (StringUtils.isNotBlank(imgId)) {
            response.getOutputStream().write(fileService.queryImgById(Integer.parseInt(imgId)));
        }

    }

    // 获取的图片
    @RequestMapping(params = "action=loadCropImg")
    public void loadImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        java.io.InputStream in = new java.io.FileInputStream("D:\\mitian\\wk\\airad\\WebRoot\\images\\test_img02.jpg");
        if (in != null) {
            byte[] b = new byte[1024];
            int len;
            while ((len = in.read(b)) != -1) {
                response.getOutputStream().write(b);
            }

            in.close();
        }
    }

    // 获取session中的图片
    @RequestMapping(params = "action=loadSessionCropImg")
    public void loadSessionCropImg(HttpServletRequest request, HttpServletResponse response) {
        String imgId = request.getParameter("fileName");
        OutputStream out = null;
        if (StringUtils.isNotBlank(imgId)) {
            try {
                byte[] content = fileService.queryImgById(Integer.parseInt(imgId));
                out = response.getOutputStream();
                if (content != null) {
                    out.write(fileService.queryImgById(Integer.parseInt(imgId)));
                }
            }
            catch (Exception e) {
                logger.error("loadSessionCropImg error", e);
                IOUtils.closeQuietly(out);
            }
        }

    }
}
