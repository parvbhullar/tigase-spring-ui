package com.mitian.airad.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mitian.airad.common.utils.ImageDomain;

/**
 * UtilController.java
 * 
 * @author baojun
 */
@Controller
@RequestMapping("/utilAjax.do")
public class UtilController extends AbstractController {

    // 上传图片
    @RequestMapping(params = "action=upLoadImg")
    public void uploadImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 分为两种 一种原图压缩
        // 获得图片类型 没有 都认为是原图 1需要压缩
        String imgeType = request.getParameter("type");
        if (imgeType == null) {
            int ix1 = 0, ix2 = 0, iy1 = 0, iy2 = 0;
            String x1 = request.getParameter("x1");
            if (x1 != null) {
                ix1 = Integer.parseInt(x1);
            }
            String x2 = request.getParameter("x2");
            if (x1 != null) {
                ix2 = Integer.parseInt(x2);
            }
            String y1 = request.getParameter("y1");
            if (x1 != null) {
                iy1 = Integer.parseInt(y1);
            }
            String y2 = request.getParameter("y2");
            if (x1 != null) {
                iy2 = Integer.parseInt(y2);
            }

            ImageDomain.saveImage(new File("D:\\mitian\\wk\\airad\\WebRoot\\images\\test_img01.jpg"),
                    "D:\\mitian\\wk\\airad\\WebRoot\\images\\test_img02.jpg", iy1, ix1, 300, 100);

        }
        else if (imgeType.equals("1")) {
            // ImageDomain.compressionImage(new File("D:\\mitian\\wk\\airad\\WebRoot\\images\\test_img01.jpg"), new
            // File("D:\\mitian\\wk\\airad\\WebRoot\\images\\test_img03.jpg"), Integer.parseInt("250"),
            // Integer.parseInt("250"),Integer.parseInt("2"),null,null);
            java.io.InputStream in =
                    new java.io.FileInputStream("D:\\mitian\\wk\\airad\\WebRoot\\images\\test_img01.jpg");
            if (in != null) {
                byte[] b = new byte[1024];
                int len;
                while ((len = in.read(b)) != -1) {
                    response.getOutputStream().write(b);
                }

                in.close();
            }
        }

    }

    // 获取
    @RequestMapping(params = "action=loadImg")
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

}
