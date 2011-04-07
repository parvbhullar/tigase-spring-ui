package com.ivyinfo.im.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zy.im.client.service.ClientSession;
import com.zy.im.core.GenericCommand;
import com.zy.im.core.Message;

public class Byte2Object {
	/*private static final Logger LOGGER = LoggerFactory.getLogger(Byte2Object.class);

    *//**
     * 将对象转成byte
     * @param obj
     * @return
     * @throws IOException
     *//*
    public static byte[] getBytes_Object(Object obj) throws IOException {
        ByteArrayOutputStream bout = null;
        ObjectOutputStream out = null;
        byte[] bytes = null;
        try {
            bout = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bout);
            out.writeObject(obj);
            out.flush();
            bytes = bout.toByteArray();
            return bytes;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("[IMClient]error getBytes_Object:" + ex.getMessage());
        } finally {
            if (bout != null) {
                bout.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    *//**
     * 将byte转成对象
     * @param bytes
     * @return
     * @throws IOException
     *//*
    public static Object getObject_Bytes(byte[] bytes) throws IOException {
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            Object obj = oi.readObject();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("[IMClient]error getObject_Bytes:" + ex.getMessage());
        } finally {
            bi.close();
            oi.close();
        }
    }
    *//********************************************************************//*    
    public static byte[] getBytes(byte[] msgBytes) {
        // Compute the total length (msgBytes size)
        int msgLength = 4; // header size (msgBytes size)
        byte[] bytes = new byte[msgLength + msgBytes.length];
        // Msg length (does not include this field)
        bytes = intToByte(msgBytes.length, bytes);

        for (int i = 0; i < msgBytes.length; i++) {
            bytes[i + msgLength] = msgBytes[i];

        }
        return bytes;
    }

    public static byte[] intToByte(int i, byte[] bytes) {
        bytes[0] = (byte) (0xff & i);
        bytes[1] = (byte) ((0xff00 & i) >> 8);
        bytes[2] = (byte) ((0xff0000 & i) >> 16);
        bytes[3] = (byte) ((0xff000000 & i) >> 24);
        return bytes;
    }
    *//********************************************************************//*
    
    public static String byteArrayToString(byte[] array) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            String hexString = Integer.toHexString(array[i] & 0xFF);
            res.append(hexString + " ");
        }
        return res.toString();
    }
    *//********************************************************************//*
    
    public static Message parseMessage(ClientSession cSession, Object obj, Message msg) {
//      try {
//          Class cls = Class.forName(obj.getClass().getName());
//          for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
//              Field[] field = cls.getDeclaredFields();
//              for (Field f : field) {
//                  f.setAccessible(true);
//                  System.out.println(f.getName()+"       "+f.get(obj));
//              }
//          }
//      } catch (SecurityException e) {
//          e.printStackTrace();
//      } catch (Exception e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }

      Class cls = null;
      try {
          if (obj.getClass().getDeclaredField("protocolVersion") != null && obj.getClass().getDeclaredField("CMD_NAME") != null) {
              cls = Class.forName(obj.getClass().getName());
          }
      } catch (NoSuchFieldException ex) {
          try {
              //ex.printStackTrace();
              cls = Class.forName(obj.getClass().getName());
              cls = cls.getSuperclass();
          } catch (ClassNotFoundException ex1) {
        	  LOGGER.error("[IMClient][parseMessage] ERROR message parseMessage" + ex1.getMessage());
          }
      } catch (ClassNotFoundException ex) {
    	  LOGGER.error("[IMClient][parseMessage] ERROR message parseMessage" + ex.getMessage());
      }


      try {
          Field f_p_version = null;
          Field f_cmd_name = null;
          if (cls.getDeclaredField("protocolVersion") == null) {
        	  LOGGER.error("[IMClient][parseMessage] protocolVersion obj is null !");
        	  ClientEngine.disconnect(cSession);
              return null;
          }
          if (cls.getDeclaredField("CMD_NAME") == null) {
        	  LOGGER.error("[IMClient][parseMessage] CMD_NAME obj is null !");
        	  ClientEngine.disconnect(cSession);
              return null;
          }
          f_p_version = cls.getDeclaredField("protocolVersion");
          f_cmd_name = cls.getDeclaredField("CMD_NAME");
          //取消 Java 语言访问检查,不去检查private和public
          f_p_version.setAccessible(true);
          f_cmd_name.setAccessible(true);


          msg.setProtocolVersion(String.valueOf(f_p_version.get(obj)));
          //String commandName = String.valueOf(f_cmd_name.get(obj));

//          msg.setCommand(String.valueOf(f_cmd_name.get(obj)));
//          this.CMD_NAME = msg.getCommand();
          msg.setCommand((GenericCommand) obj);
//          System.err.println("====version:"+msg.getProtocolVersion()+"   command:"+msg.getCommand());
      } catch (NoSuchFieldException ex) {
    	  LOGGER.error("[IMClient][parseMessage][NoSuchFieldException] ERROR message parseMessage" + ex.getMessage());
      } catch (SecurityException ex) {
    	  LOGGER.error("[IMClient][parseMessage][SecurityException] ERROR message parseMessage" + ex.getMessage());
      } catch (Exception ex) {
    	  LOGGER.error("[IMClient][parseMessage][Exception] ERROR message parseMessage" + ex.getMessage());
      }
      return msg;
  }*/
}
