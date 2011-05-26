/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.framework.common.communication.sorcket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.*;

/**
 *
 * @author Administrator
 */
public class MultiThreadServer {

    private int port = 4745;
    private ServerSocket serverSocket;
    private ExecutorService executorService;//线程池
    private final int POOL_SIZE = 10;//单个CPU线程池大小

    public MultiThreadServer() throws IOException {
        serverSocket = new ServerSocket(port);
        //Runtime的availableProcessor()方法返回当前系统的CPU数目.
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
        System.out.println("服务器启动");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                //接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
                socket = serverSocket.accept();
                executorService.execute(new Handler(socket));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new MultiThreadServer().service();
    }
}

class Handler implements Runnable {

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();

//               if (socketIn != null) {
//            //InputStream 必须要read一次，才能获得到available的值，否则为0；
//            socketIn.read();
//            int isSize = socketIn.available();
//            if (isSize > 0) {
//                try {
//                    byte[] bytes = new byte[isSize];
//                    socketIn.read(bytes, 0, bytes.length);
//                    System.err.println(" getByte: "+byteArrayToString(bytes));
//                    //mina 的 ByteBuffer 转成 InputStream 必须要去掉报文的前1为字节
////                    byte[] value = new byte[isSize - 1];
////                    System.arraycopy(bytes, 1, value, 0, value.length);
////                    message = parseMessage(getObject_Bytes(value), message);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            } else {
//                throw new IOException("Cannot read message size");
//            }
//        }


        return new BufferedReader(new InputStreamReader(socketIn));
    }

                /**
     * This method is for debugging purpose only. It prints a byte array into a
     * string. Each byte is dumped as hex.
     */
    private static String byteArrayToString(byte[] array) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            String hexString = Integer.toHexString(array[i] & 0xFF);
            res.append(hexString + " ");
        }
        return res.toString();
    }

    public String echo(String msg) {
        return "echo:" + msg;
    }

    public void run() {
        try {
            System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println("========msg:"+msg);
                pw.println(echo(msg));
//                if (msg.equals("bye")) {
//                    break;
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
