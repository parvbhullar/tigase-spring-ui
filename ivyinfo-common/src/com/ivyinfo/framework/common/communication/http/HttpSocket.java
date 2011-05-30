package com.ivyinfo.framework.common.communication.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

public class HttpSocket extends Thread {

    public Socket s = null;
    private InputStream input;
    private String uri;
    private StringBuffer request = new StringBuffer(); // 用于保存所有内容
    private int CONTENT_LENGTH = 0; // 实际包内容数据长
    private boolean bePost = false;
    private boolean beHttpResponse = false;
    private boolean beChucked = false;
    private boolean beGet = false;
    private byte crlf13 = (byte) 13; // '\r'
    private byte crlf10 = (byte) 10; // '\n'

    public HttpSocket() {
    }

    public void run() {
        PrintWriter os = null;
        String msg = "";
        String content = "";
        try {
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            System.err.println("=========into run============");

            InputStream is = s.getInputStream();
            content = ReadHeader(is);

            // 由Socket对象得到输出流，并构造PrintWriter对象
            os = new PrintWriter(s.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error   on   get   Buffere");
        }

        String temp = "";
        try {
            // 立刻回发消息
            System.err.println("===========back content===========");

            msg = content;
            os.write("HTTP/1.1 200 OK\r\n");
            os.write("Content-Type:text;charset=utf-8\r\n");
            os.write(("Content-Length:" + msg.length() + "\r\n"));
            os.write("\r\n");
            os.write(msg);
            os.flush();
            System.err.println("===========END===========");

            msg = "";
        } catch (Exception ex1) {
            System.out.println("Error   on   read   or   write   Buffered");
            ex1.printStackTrace();
        }
    }

    private String ReadHeader(InputStream input) // 读取头部 并获得大小
    {
        byte[] crlf = new byte[1];
        int crlfNum = 0; // 已经连接的回车换行数 crlfNum=4为头部结束
        try {
            while (input.read(crlf) != -1) // 读取头部
            {
                if (crlf[0] == crlf13 || crlf[0] == crlf10) {
                    crlfNum++;
                } else {
                    crlfNum = 0;
                } // 不是则清
                request = request.append(new String(crlf, 0, 1)); // byte数组相加
                if (crlfNum == 4) {
                    break;
                }
            }
            System.err.println("=======" + request.toString());

        } catch (IOException e) {
            System.out.println("Read Http Header Error!");
            return "";
        }

        String tempStr = (new String(request));//.toUpperCase();

        // 这里我只处理了GET与POST方法
        String strMethod = tempStr.substring(0, 4);
        String content = "";
        if (strMethod.equals("GET ")) // 前
        {
            beGet = true;
            //content = getContent(tempStr);
        }
        return content;
    }

    private String getContent(String tempStr) {
        return "";
    }
}
