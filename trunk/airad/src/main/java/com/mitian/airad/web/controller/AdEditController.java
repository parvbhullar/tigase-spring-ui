package com.mitian.airad.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AdEditForm;

@Controller
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_DEVELOPERS,
        RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_OSS_SALES})
@RequestMapping("/adEdit.do")
public class AdEditController extends AbstractController {
    private static final Map<String, String> IMAGE_NAME_MAPPING = new HashMap<String, String>();

    /**
     * 到广告制作页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=addPage")
    public ModelAndView addPage(AdEditForm form) {
        ModelAndView mv = new ModelAndView("advertising/edit", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 到上传图片页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=uploadPage")
    public ModelAndView uploadPage(AdEditForm form) {
        ModelAndView mv = new ModelAndView("advertising/upload", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 上传图片
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doUpload")
    public ModelAndView doUpload(AdEditForm form) {
        ModelAndView mv = new ModelAndView("advertising/upload_and_crop_result", Constants.DEFAULT_COMMAND, form);
        if (!form.getErrors().isEmpty()) {
            mv.addObject("result", "{status:'fail'}");
            return mv;
        }
        // 得到上传图片的内容等
        byte[] bytes = form.getFile().getBytes();
        String fileName = form.getFile().getOriginalFilename();
        AiradImage image = new AiradImage(bytes, fileName);
        // 生成映射结构
        String fileId = String.valueOf(System.currentTimeMillis());
        String saveFileName = fileId + "_" + fileName;
        String saveFilePath = "c:/pics/" + saveFileName;
        // 保存图片
        image.saveAs(saveFilePath);
        IMAGE_NAME_MAPPING.put(fileId, saveFileName);
        mv.addObject("result", "{status:'succ',fileId:'" + fileId + "'}");
        return mv;
    }

    /**
     * 根据图片id得到图片
     * 
     * @param form
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "action=lookImage")
    public ModelAndView lookImage(AdEditForm form, HttpServletResponse response) throws IOException {
        String fileId = form.getFileId();
        String fileName = "c:/pics/" + IMAGE_NAME_MAPPING.get(fileId);
        response.setContentType("image/jpeg");
        InputStream imageIn = new FileInputStream(new File(fileName));// 文件流
        BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());// 输出缓冲流
        byte data[] = new byte[4096];// 缓冲字节数
        int size = 0;
        size = bis.read(data);
        while (size != -1) {
            bos.write(data, 0, size);
            size = bis.read(data);
        }
        bis.close();
        bos.flush();
        bos.close();
        return null;
    }

    /**
     * 剪切图片
     * 
     * @param form
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "action=doCrop")
    public ModelAndView doCrop(AdEditForm form, HttpServletResponse response) throws IOException {
        if (!form.getErrors().isEmpty()) {
            response.getWriter().write("{status:'fail'}");
            return null;
        }
        String fileId = form.getFileId();
        // 得到上传图片的内容等
        String fileName = IMAGE_NAME_MAPPING.get(fileId);
        String filePath = "c:/pics/" + fileName;
        // 进行剪切操作
        AiradImage image = new AiradImage(filePath);
        int x = Integer.parseInt(form.getX());
        int y = Integer.parseInt(form.getY());
        int w = Integer.parseInt(form.getW());
        int h = Integer.parseInt(form.getH());
        image.crop(x, y, x + w, y + h);
        // 生成映射结构
        String newFileId = String.valueOf(System.currentTimeMillis());
        String saveFileName = newFileId + "_" + fileName;
        String saveFilePath = "c:/pics/" + saveFileName;
        // 保存图片
        image.saveAs(saveFilePath);
        IMAGE_NAME_MAPPING.put(newFileId, saveFileName);
        response.getWriter().write("{status:'succ',fileId:'" + newFileId + "'}");
        return null;
    }
}
