package com.ivyinfo.im.client;

import java.io.IOException;
import java.util.HashMap;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ivyinfo.im.controller.TrafficController;
import com.ivyinfo.im.listener.ChatListener;
import com.ivyinfo.im.listener.ChatSender;
import com.ivyinfo.im.listener.RosterListener;
import com.ivyinfo.im.meettouch.bean.MeetTouchMessage;
import com.zy.im.client.service.ClientSession;
import com.zy.im.client.service.ClientThreadState;
import com.zy.im.core.Message;
import com.zy.im.core.MessageException;


public class ClientEngine implements ICientEngine{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientEngine.class);
    
    //save the chat which you have talked to someone
    private HashMap chatMap = new HashMap();
    
    	
	public ClientSession Login(String ip,int port,String username,String pass) throws Exception{
		ClientSession cs = new ClientSession();
		cs.setState(ClientThreadState.DISCONNECTED);
		
		if (cs.getState() != ClientThreadState.CONNECTED) {
			 ConnectionConfiguration config = new ConnectionConfiguration(ip,port);
			 config.setReconnectionAllowed(true);
			 config.setSASLAuthenticationEnabled(false);
			 
			 XMPPConnection connection = new XMPPConnection(config);
			 
			 connection.connect();
			 connection.login(username, pass);
			 
			 //放入client的Session中
			 cs.setConnection(connection);
			 cs.setState(ClientThreadState.CONNECTED);
			 System.out.println("login complete!!!");
        }
        if (cs.getState() == ClientThreadState.CONNECTED) {
        	 // Authenticate
            //String authStatus = authenticate(cs);
        	return cs;
        }
        else{
        	System.out.println("connect error");
        }
        return null;	
		
	}

	
	//注册聊天聊天监听类
	public void addChatListener(String username, ClientSession csession)throws Exception{
		//添加处理接受信息的Listener
		ChatListener list = new ChatListener(username, csession.getConnection());
		TrafficController.addListener(username, list);
		
		//添加处理发送信息的Listener
		ChatSender sender = new ChatSender(csession.getConnection());
		TrafficController.addSender(username, sender);
		
		csession.setListened(true);
		list.start();
	}
	
	//注册好友状态监听
	public void addRosterListener(String username,ClientSession csession) throws Exception{
		 RosterListener rosterListener = new RosterListener(csession.getConnection());
		 TrafficController.addRosterListener(username, rosterListener);
		 csession.setListened(true);
	}
    
    /**
     * Stops the service. 
     */
    public void stopClient(String username ,ClientSession cSession) {
    	LOGGER.debug("[IMClient] asked to stop");
        try {
            if (cSession.getState() >= ClientThreadState.CONNECTED) {
            	try {
    				XMPPConnection conn = (XMPPConnection)cSession.getConnection();
    				if (conn != null) {
    					conn.disconnect();
    					cSession.setConnection(null);
    					cSession.setListened(false);
    				}
    			} catch (Throwable e) {
    				e.printStackTrace();
    			}

    			try {
    				ChatListener listener = TrafficController.getListener(username);
    				if (listener != null) {
    					listener.terminate();
    					if (username != null) {
    						TrafficController.removeListener(username);
    					}
    				}
    				ChatSender sender = TrafficController.getSender(username);
    				if(sender!=null)
    					if(username!=null)
    						TrafficController.removeSender(username);
    			} catch (Throwable e) {
    				e.printStackTrace();
    			}
            }
            // If we have any IO operation pending we must awake the the
            // corresponding thread. We do this by closing the connection which
            // will
            // cause some exceptions.
            disconnect(cSession);
        } catch (Exception e) {
        	LOGGER.error("[IMClient] Send of BYE command failed");
        }
    }
    
    /**
     * Disconnect from server.
     */
    private void disconnect(ClientSession cSession) {
    	cSession.setState(ClientThreadState.DISCONNECTED);
    }

	public Message listenMessages(ClientSession cSession) throws IOException,
			MessageException {
		// TODO Auto-generated method stub
		return null;
	}
   
}
