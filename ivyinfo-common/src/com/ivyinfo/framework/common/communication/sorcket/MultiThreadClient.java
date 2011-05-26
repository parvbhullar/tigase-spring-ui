/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivyinfo.framework.common.communication.sorcket;

import com.ivyinfo.framework.common.encryption.Hex;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Administrator
 */
public class MultiThreadClient {

    private static Vector paramsValue = new Vector();
    private static Vector paramsCode = new Vector();
    /** CTP Protocol version = 1.0 */
    private static final int PROTOCOL_VERSION = 0x10;
    private static final int TEST_CODE = 0x10;
    /** command or status code */
    private static int commandCode = -1;

    public static void main(String[] args) {
        int numTasks = 1;

        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < numTasks; i++) {
            exec.execute(createTask(i));
        }

    }

    // 定义一个简单的任务
    private static Runnable createTask(final int taskID) {
        return new Runnable() {

            private Socket socket = null;
            private int port = 4745;

            public void run() {
                System.out.println("Task " + taskID + ":start");
                try {
                    socket = new Socket("localhost", port);
                    // 发送关闭命令
                    OutputStream socketOut = socket.getOutputStream();

                    String message = "aaaaa\n";

                    IvyMessage im = new IvyMessage();
                    im.setTest("bbbbbbbbbbbb");


                    //paramsCode.addElement(new Integer(TEST_CODE));
                    //paramsValue.addElement(message.getBytes());

                    //byte[] msgBytes = getBytes_1();

                    //System.err.println("==========:" + byteArrayToString(msgBytes));

                    //socketOut.write(msgBytes);
                    socketOut.write(getBytes_object(im));
                    socketOut.flush();

                    // 接收服务器的反馈
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    String msg = null;
                    while ((msg = br.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private static String byteArrayToString(byte[] array) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            String hexString = Integer.toHexString(array[i] & 0xFF);
            res.append(hexString + " ");
        }
        return res.toString();
    }

    public static byte[] getBytes_object(Object obj) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(obj);
        out.flush();
        byte[] bytes = bout.toByteArray();
        bout.close();
        out.close();
        return bytes;
    }

    public static byte[] getBytes_1() {
        // Compute the total length (header + parameters size)
        int msgLength = 4; // header size (Protocol version, command)
        int numParams = paramsValue.size();
        for (int i = 0; i < numParams; ++i) {
            byte[] param = (byte[]) paramsValue.elementAt(i);
            msgLength += (1 + 1 + param.length); // param-type +
            // param-length + value
        }
        int idx = 0;
        byte[] bytes = new byte[msgLength];
        // Msg length (does not include this field)
        bytes[idx++] = (byte) ((msgLength - 2) >> 8);
        bytes[idx++] = (byte) ((msgLength - 2) & 0xFF);
        // Protocol version
        bytes[idx++] = (byte) PROTOCOL_VERSION;
        // Command
        bytes[idx++] = (byte) commandCode;
        // Parameters
        for (int i = 0; i < numParams; ++i) {
            Integer codeInt = (Integer) paramsCode.elementAt(i);
            byte code = codeInt.byteValue();
            byte[] param = (byte[]) paramsValue.elementAt(i);
            byte length = (byte) param.length;

            bytes[idx++] = code;
            bytes[idx++] = length;
            System.arraycopy(param, 0, bytes, idx, param.length);
            idx += param.length;
        }

        return bytes;
    }
}
