package com.zy.im.client.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Timer;

import org.jivesoftware.smack.XMPPConnection;

import com.zy.im.client.config.PushConfig;

public class ClientSession implements java.io.Serializable{
    private static final long serialVersionUID = 2011012000L;
    
    /** IMClient status */
    private int state = ClientThreadState.DISCONNECTED;
    
    private XMPPConnection connection = null;
    
    /**Specify if the IM Service has added the Listening class**/
    private boolean listened = false;
    
    
	public boolean isListened() {
		return listened;
	}
	public void setListened(boolean listened) {
		this.listened = listened;
	}
	public XMPPConnection getConnection() {
		return connection;
	}
	public void setConnection(XMPPConnection connection) {
		this.connection = connection;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	
    
	
    
}
