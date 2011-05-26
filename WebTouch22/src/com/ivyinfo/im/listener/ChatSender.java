package com.ivyinfo.im.listener;

import java.io.IOException;
import java.util.HashMap;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;

import com.ivyinfo.im.controller.DBController;
import com.ivyinfo.im.meettouch.bean.MeetTouchMessage;
import com.zy.im.client.service.ClientSession;
import com.zy.im.core.Message;

public class ChatSender {
	private XMPPConnection conn;
	HashMap chatMap = new HashMap();
	
	public ChatSender(XMPPConnection xmppconn){
		conn = xmppconn;
	}
	
	public void sendMsg(Message msg) throws IOException {
    	try{
	    	MeetTouchMessage mtm = (MeetTouchMessage) msg.getCommand();
	    	
	    	Chat chat = (Chat)chatMap.get(mtm.getTouser());
	    	//若未与当前用户通信过，则创建新的chat
	    	if(chat == null){
	    		chat = conn.getChatManager().createChat(mtm.getTouser(), new MessageListener() {
					public void processMessage(Chat arg0,
							org.jivesoftware.smack.packet.Message message) {
						// TODO Auto-generated method stub
						System.out.println("chat_msg: " + message.getBody());
					}
				});
				chatMap.put(mtm.getTouser(), chat);
	    	}
	    	chat.sendMessage(mtm.getMessage());
	    	//将其放入到数据库中！！
	    	saveMessageToDB(mtm);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	private void saveMessageToDB(MeetTouchMessage mtm){
		DBController.push(mtm.getSenduser(), mtm.getNicesenduser(),mtm.getTouser(),mtm.getNicetouser(), mtm.getMessage(), "send");
	}
}
