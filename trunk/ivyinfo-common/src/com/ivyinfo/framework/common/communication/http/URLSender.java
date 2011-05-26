package com.ivyinfo.framework.common.communication.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class URLSender {

    /**     * @param args */
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("syncxkx.vicp.net", 209);
            boolean autoflush = true;
            PrintWriter out = new PrintWriter(socket.getOutputStream(), autoflush);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //send an HTTP request to the web server
            out.println("GET / HTTP/1.1");
            out.println("Host: www.baidu.com");
            out.println("Connection: Close");
            out.println();
            //read the response
            boolean loop = true;
            StringBuffer sb = new StringBuffer(8096);
            while (loop) {
                if (in.ready()) {
                    int i = 0;
                    while (i != -1) {
                        i = in.read();
                        sb.append((char) i);
                    }
                    loop = false;
                }
                //Thread.currentThread().sleep(50);
            }
            //display the response to the out console
            System.out.println(sb.toString());
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: Victest.");
            //System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for " + "the connection to: Victest.");
            //System.exit(1);
        }
        //用Socket发送一个POST请求
        try {
            // Construct data
            String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
            data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");

            // Create a socket to the host
            String hostname = "hostname.com";
            int port = 80;
            InetAddress addr = InetAddress.getByName(hostname);
            Socket socket = new Socket(addr, port);

            // Send header
            String path = "/servlet/SomeServlet";
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            wr.write("POST " + path + " HTTP/1.0\r\n");
            wr.write("Content-Length: " + data.length() + "\r\n");
            wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
            wr.write("\r\n");

            // Send data
            wr.write(data);
            wr.flush();

            // Get response
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
        }
    }
}
