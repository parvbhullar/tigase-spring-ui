package com.ivyinfo.framework.common.file;

import java.io.*;

/**
 * Container of utility methods for io access
 *
 * @version $Id: IOHandle.java,v 1.2 2007-11-28 11:13:24 nichele Exp $
 */
public abstract class IOHandle {

    /**
     * 读取文件转成字节流
     * @param file
     * @return
     * @throws IOException
     */
    static public byte[] readFileBytes(File file) throws IOException {
        FileInputStream fis = null;

        byte[] buf = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(buf);
            fis.close();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return buf;
    }

    /**
     * 根据文件路径，将文件转成字节流
     * @param filename
     * @return
     * @throws IOException
     */
    static public byte[] readFileBytes(String filename) throws IOException {
        return readFileBytes(new File(filename));
    }

    /**
     * 读取文件流并转成String型
     * @param file
     * @return
     * @throws IOException
     */
    static public String readFileString(File file) throws IOException {
        return new String(readFileBytes(file));
    }

    /**
     * 根据文件名，读取文件流并转成String型
     * @param filename
     * @return
     * @throws IOException
     */
    static public String readFileString(String filename) throws IOException {
        return readFileString(new File(filename));
    }

    /**
     * 将inputStream 转成String型
     * @param is
     * @return
     * @throws IOException
     */
    static public String readFileString(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();

        byte[] buf = new byte[512];
        int n = 0;
        while ((n = is.read(buf)) >= 0) {
            sb.append(new String(buf, 0, n));
        }
        return sb.toString();
    }

    /**
     * 写文件
     * @param str  文件内容
     * @param file  写入的文件
     * @throws IOException
     */
    static public void writeFile(String str, File file) throws IOException {
        writeFile(str.getBytes(), file);
    }

    /**
     * 写文件
     * @param str  文件内容
     * @param filename  需写入的文件路径和文件名
     * @throws IOException
     */
    static public void writeFile(String str, String filename) throws IOException {
        writeFile(str.getBytes(), new File(filename));
    }

    /**
     * 写文件
     * @param buf  字节型文件内容
     * @param filename  需写入的文件路径和文件名
     * @throws IOException
     */
    static public void writeFile(byte[] buf, String filename) throws IOException {
        writeFile(buf, new File(filename));
    }

    /**
     * 写文件
     * @param buf  字节型文件内容
     * @param file  需写入的文件
     * @throws IOException
     */
    static public void writeFile(byte[] buf, File file) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(buf);
            fos.close();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 文件类型过滤
     * @param type
     * @return
     */
    public static FilenameFilter getFileTypeFilter(String type) {
        return new FileTypeFilter(type);
    }

    /**
     * 读取文件流
     * @param in
     * @param bufferSize
     * @return
     * @throws IOException
     */
    public static byte[] readContent(final InputStream in, int bufferSize)
            throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buf = new byte[bufferSize];

        int c = 0;
        int b = 0;
        while ((c < buf.length) && (b = in.read(buf, c, buf.length - c)) >= 0) {
            c += b;
            if (c == bufferSize) {
                bout.write(buf);
                buf = new byte[bufferSize];
                c = 0;
            }
        }
        if (c != 0) {
            bout.write(buf, 0, c);
        }
        return bout.toByteArray();
    }

    // -------------------------------------------------------------------------
    /**
     * This class is a <i>FilenameFilter</i> that accepts only the files of the
     * specified type (extension). The filtering is case-insensitive,
     */
    public static class FileTypeFilter implements FilenameFilter {

        private String type;

        /**
         * Creates the filter on the given type.
         *
         * @param type the type (the file extension) of the files to select. NULL
         *             means all files, the empty string means files without
         *             extension. The filtering is case-insensitive
         */
        public FileTypeFilter(final String type) {
            this.type = type.toUpperCase();
        }

        public boolean accept(File dir, String name) {
            if (type == null) {
                return true;
            }

            if (type.length() == 0) {
                return (name.indexOf('.') < 0);
            }

            return (name.toUpperCase().endsWith(type));
        }
    }
}
