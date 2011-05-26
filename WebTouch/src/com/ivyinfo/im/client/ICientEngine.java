package com.ivyinfo.im.client;

import java.io.IOException;

import com.zy.im.client.service.ClientSession;
import com.zy.im.core.Message;
import com.zy.im.core.MessageException;

public interface ICientEngine {
	
	public void addChatListener(String username, ClientSession csession) throws Exception;

	public ClientSession Login(String ip,int port,String username,String pass) throws Exception;
	
	public void addRosterListener(String username,ClientSession csession) throws Exception;
	
	//public void sendMsg(ClientSession cSession,Message msg) throws IOException ;
	
	public Message listenMessages(ClientSession cSession) throws IOException, MessageException;
	
	 public void stopClient(String username,ClientSession cSession);
}
