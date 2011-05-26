package com.zy.im.client.service;

public interface ClientThreadState {
	/**
     * IM client thread state
     */
    public static final int DISCONNECTED = 0;
    public static final int CONNECTING = 1;
    public static final int CONNECTED = 2;
    public static final int AUTHENTICATING = 3;
    public static final int AUTHENTICATED = 4;
    public static final int LISTENING = 5;
    
    public static final int IDLE = 6;
}
