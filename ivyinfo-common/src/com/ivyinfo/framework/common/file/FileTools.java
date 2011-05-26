/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.framework.common.file;

import com.ivyinfo.framework.common.exception.IvyinfoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Administrator
 */
public class FileTools extends IOHandle {

    /**
     * 将源文件拷贝到目标文件
     *
     * @param src 写源文件地址，需文件名
     * @param des 写目标文件地址，无需文件名
     */
    public boolean copyFile(String src, String des) throws IvyinfoException {
        File srcFile = new File(src);
        File desDir = new File(des);
        File desFile = new File(des + "/" + srcFile.getName());
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new IvyinfoException("源文件不存在！");
        } else if (!srcFile.isFile()) {
            throw new IvyinfoException("源文件格式错！");
        }
        // 判断源文件是否存在
        if (!desDir.exists()) {
            desDir.mkdir();
        } else if (!desDir.isDirectory()) {
            throw new IvyinfoException("不是有效的目录！");
        }

        try {
            writeFile(readFileBytes(srcFile), desFile);
        } catch (Exception e) {
            throw new IvyinfoException("拷贝文件错误：" + e.getMessage());
        }
        return true;
    }

    /**
     * 复制目录 将源目录下所有文件拷贝到目标目录下
     * 
     * @param src
     *            源目录
     * @param des
     *            目标目录
     */
    public boolean copyDir(String src, String des) throws IvyinfoException {
        List fileList = new ArrayList();
        File srcFile = new File(src);
        if (!srcFile.exists()) {
            throw new IvyinfoException("源目录不存在！");
        } else if (!srcFile.isDirectory()) {
            throw new IvyinfoException(src + "不是有效的目录！");
        }
        fileList = showFileList(srcFile);

        for (int i = 0; i < fileList.size(); i++) {
            String srcName = ((File) fileList.get(i)).getPath();
            String desName = srcName.substring(src.length(), srcName.length());
            desName = des + desName;
            File dir = new File(desName).getParentFile();
            mkdir(dir);

            if (!copyFile(srcName, dir.getPath())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 列出所有文件
     *
     * @param srcFile
     */
    private List showFileList(File srcFile) throws IvyinfoException {
        List fileList = new ArrayList();
        if (srcFile.isDirectory()) {
            String[] files = srcFile.list();

            for (int i = 0; i < files.length; i++) {
                File f = new File(srcFile + "/" + files[i]);
                // 如果是文件加入列表，否则递归列出
                if (f.isFile()) {
                    fileList.add(f);
                } else {
                    showFileList(f);
                }
            }
        } else {
            throw new IvyinfoException(srcFile.getAbsolutePath() + "不是目录");
        }
        return fileList;
    }

    /**
     * 建立目录
     *
     * @param des
     * @throws IOException
     */
    public static void mkdir(File des) {
        if (!des.exists() || !des.isDirectory()) {
            mkdir(des.getParentFile());
            if (des.exists()) {
                des.delete();
            }
            des.mkdir();
        }
    }

    /**
     * 多个文件压缩成一个文件
     * @param srcfile
     * @param zipfile
     */
    public static void ZipFiles(java.io.File[] srcfile, java.io.File zipfile) throws IvyinfoException {
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (Exception e) {
            throw new IvyinfoException("压缩错误：" + e.getMessage());
        }
    }

    /**
     * 多个文件压缩成多个文件
     * @param srcfile
     * @param zipfile
     * @param fileName
     * @param filePix
     */
    public static void zipFilesToOne(java.io.File[] srcfile, java.io.File zipfile, String[] fileName, String[] filePix) throws IvyinfoException {
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(fileName[i] + "." + filePix[i]));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (Exception e) {
            throw new IvyinfoException("压缩成多个文件错误：" + e.getMessage());
        }
    }
}
