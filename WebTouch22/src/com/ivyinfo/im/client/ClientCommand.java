package com.ivyinfo.im.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zy.im.client.service.ClientSession;
import com.zy.im.client.service.ClientThreadState;
import com.zy.im.core.Message;
import com.zy.im.core.command.Auth;

public class ClientCommand {
	/*private static final Logger LOGGER = LoggerFactory.getLogger(ClientCommand.class);
	
    public Auth createAuthMessage(ClientSession cSession) {
        Auth authMessage = new Auth();

        String username = cSession.getPushConfig().getCtpUsername();
        String password = cSession.getPushConfig().getCtpPassword();

        LOGGER.debug("[IMClient]: create credentials for " + username + "," + password);

        String OsName = System.getProperty("os.name").toLowerCase();

        authMessage.setDevtype(OsName);
        authMessage.setUsername(username);
        authMessage.setPass(password);
        authMessage.setFrom(cSession.getPushConfig().getClientForm());

        // TODO handle the FROM here
        return authMessage;
    }
    
    public void connect(ClientSession cSession) throws IOException {
        // Start connecting
    	cSession.setState(ClientThreadState.CONNECTING);
        try {
        	//在这里修改连接代码！！
        	
            Socket sc = new Socket(cSession.getPushConfig().getCtpServer(), cSession.getPushConfig().getCtpPort());
            LOGGER.debug("[IMClient]: connecting to " + cSession.getPushConfig().getCtpServer());
            sc.setTcpNoDelay(true);

            cSession.setOs(sc.getOutputStream());
            cSession.setIs(sc.getInputStream());

            cSession.setState(ClientThreadState.CONNECTED);
            LOGGER.debug("[IMClient]: Connected");
        } catch (IOException ioe) {
        	LOGGER.error("[IMClient]: Cannot open IM connection to: " + cSession.getPushConfig().getCtpServer());

        	cSession.setState(ClientThreadState.DISCONNECTED);
            throw ioe;
        }
    }
    
    public void sendMessageWithTimeout(ClientSession cSession, Message message) throws IOException {
    	// Set the timer
        ConnectionTimer connTimer = new ConnectionTimer();
        LOGGER.debug("[IMClient] Programming alarm in " + connTimer.getDelay() + " msec");
        cSession.getTimer().schedule(connTimer, connTimer.getDelay());
        // Perform the I/O operation
    	try {
        	ClientCommand command = new ClientCommand();
        	command.sendMessage(cSession, message);
        	connTimer.endOperation();
        } catch (IOException e) {
        	connTimer.endOperation();
            throw e;
        }
    }
    
    *//**
     * Receives a message from the server. The read operation is guarded by a
     * timeout. If nothing is received within the timeout then ConnectionTimer
     * will reset the connection, causing the method to trap an exception that
     * will be re-thrown.
     *
     * @return the CTP message received
     * @throws IOException
     *             if the socket writing fails
     *//*
    public Message receiveMessageWithTimeout(ClientSession cSession) throws IOException {
        ConnectionTimer connTimer = new ConnectionTimer();
        LOGGER.debug("[IMClient] Programming alarm in " + connTimer.getDelay() + " msec");
        cSession.getTimer().schedule(connTimer, connTimer.getDelay());
        try {
        	ClientCommand command = new ClientCommand();
            Message res = command.receiveMessage(cSession);
            connTimer.endOperation();
            return res;
        } catch (IOException e) {
            connTimer.endOperation();
            throw e;
        }
    }
    
    private void sendMessage(ClientSession cSession, Message message) throws IOException {
        byte[] msgBytes = Byte2Object.getBytes(Byte2Object.getBytes_Object(message.getCommand()));
        LOGGER.debug("[IMClient]: Sending message " + message.getCommand().getName());
        LOGGER.debug("[IMClient]: Sending message " + Byte2Object.byteArrayToString(msgBytes));
        try {
        	cSession.getOs().write(msgBytes);
        	cSession.getOs().flush();
        } catch (IOException e) {
            throw e;
        }
    }
    
    *//**
     * Receives a message from the server (wait till a message is received or an
     * error is encountered).
     *
     * @return the CTP message received
     * @throws IOException
     *             if the socket writing fails
     *//*
    public Message receiveMessage(ClientSession cSession) throws IOException {
    	LOGGER.debug("[IMClient]: start receiveMessage ");
        Message message = new Message();

        if (cSession.getIs() != null) {
            //InputStream 必须要read一次，才能获得到available的值，否则为0；
        	cSession.getIs().read();
            int isSize = cSession.getIs().available();
            if (isSize > 0) {
                try {
                    byte[] bytes = new byte[isSize];
                    cSession.getIs().read(bytes, 0, bytes.length);
                    //mina 的 ByteBuffer 转成 InputStream 必须要去掉报文的前1为字节
                    System.err.println("1=mina=receiveMessage:"+Byte2Object.byteArrayToString(bytes));
                    
                    if(cSession.getOtherValueMessage()!=null){
                    	bytes = getMergeBytes(cSession.getOtherValueMessage(),bytes);
                    }
                    
                    System.err.println("2=mina=receiveMessage:"+Byte2Object.byteArrayToString(bytes));
                    byte[] head = new byte[1];
                    System.arraycopy(bytes, 0, head, 0, 1);
                    String strHead = Byte2Object.byteArrayToString(head);
                    System.err.println("=mina=head:"+Byte2Object.byteArrayToString(head));
                    int messageLength = Integer.valueOf(strHead.trim(),16);  
                    System.err.println("=========messageLength:"+messageLength);
                    if(bytes.length > messageLength){
	                    byte[] value = new byte[messageLength];
	                    System.err.println("=mina=value==length:"+value.length);
	                    System.arraycopy(bytes, 1, value, 0, messageLength);
	                    System.err.println("==receiveMessage:"+Byte2Object.byteArrayToString(value));
	                    message = Byte2Object.parseMessage(cSession, Byte2Object.getObject_Bytes(value), message);
	                    //剩余字节保存到session中,供下次来读取
	                    if(bytes.length-messageLength > 1){
	                    	byte[] otherValue = new byte[bytes.length-messageLength];
	                    	System.err.println("=mina=otherValue:"+Byte2Object.byteArrayToString(otherValue));
	                    	cSession.setOtherValueMessage(otherValue);
	                    }
                    }
                } catch (IOException ex) {
                	LOGGER.error("Cannot read message：" + ex.getMessage());
                }
            } else {
                throw new IOException("Cannot read message size");
            }
        }
        return message;

    }  
    *//**  
     * 合并两个byte数组  
      * @param pByteA  
      * @param pByteB  
      * @return  
      *//*  
     public static byte[] getMergeBytes(byte[] pByteA, byte[] pByteB){   
         int aCount = pByteA.length;   
         int bCount = pByteB.length;   
         byte[] b = new byte[aCount + bCount];   
         for(int i=0;i<aCount;i++){   
             b[i] = pByteA[i];   
         }   
         for(int i=0;i<bCount;i++){   
             b[aCount + i] = pByteB[i];   
         }   
         return b;   
     }  

    
    *//**
     * Close the connection, forcing exceptions if there are pending network IO
     * operations.
     *//*
    public void closeConnection(ClientSession cSession) {
        try {
            if (cSession.getOs() != null) {
            	LOGGER.debug("[IMClient] Closing output stream");
                cSession.getOs().close();
            }
            if (cSession.getIs() != null) {
            	LOGGER.debug("[IMClient] Closing input stream");
            	cSession.getIs().close();
            }
            if (cSession.getSc() != null) {
            	LOGGER.debug("[IMClient] Closing socket connection");
            	cSession.getSc().close();
            }
        } catch (IOException e) {
        	LOGGER.error("[IMClient] Cannot force socket closure");
        } finally {
        	cSession.setOs(null);
            cSession.setIs(null);
            cSession.setSc(null);
        }
    }*/
}
