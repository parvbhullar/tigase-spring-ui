package com.ivyinfo.im.controller;

import java.util.HashMap;
import com.ivyinfo.im.listener.ChatListener;
import com.ivyinfo.im.listener.ChatSender;
import com.ivyinfo.im.listener.RosterListener;

public class TrafficController {
	private static HashMap listeners = new HashMap();
	private static HashMap senders = new HashMap();
	private static HashMap rosterListeners = new HashMap();
	/**
	 * 
	 * @param user
	 * @param listener
	 */
	public static void addListener(String user, ChatListener listener)throws Exception {
		String usrTrim = getTrimmed(user);
		
		ChatListener tmp = (ChatListener)listeners.get(usrTrim);
		if (tmp != null) {
			tmp.terminate();
		}
		listeners.put(usrTrim, listener);
	}
	/**
	 * 
	 * @param user
	 */
	public static void removeListener(String user) throws Exception{
		String usrTrim = getTrimmed(user);
		listeners.remove(usrTrim);
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	public static ChatListener getListener(String user)throws Exception {
		String usrTrim = getTrimmed(user);
		return (ChatListener)listeners.get(usrTrim);
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	public static void addRosterListener(String user,RosterListener rl){
		String usrTrim = getTrimmed(user);
		
		RosterListener r = (RosterListener) rosterListeners.get(usrTrim);
		if(r!=null)
			r = null;
		rosterListeners.put(usrTrim, rl);
		
	}
	public static RosterListener getRosterListener(String user)throws Exception{
		String usrTrim = getTrimmed(user);
		return (RosterListener)rosterListeners.get(usrTrim);
	}
	/**
	 * 
	 * @param user
	 */
	public static void removeRosterListener(String user) throws Exception{
		String usrTrim = getTrimmed(user);
		rosterListeners.remove(usrTrim);
	}	
	/**
	 * 
	 * @param user
	 * @return
	 */
	private static String getTrimmed(String user) {
		String usrTrim = user;
		if (usrTrim.indexOf("/") > 0) {
			usrTrim = usrTrim.substring(0, usrTrim.indexOf("/"));
		}
		return usrTrim;
	}
	/**
	 * 
	 * @param user
	 * @param sender
	 */
	public static void addSender(String user, ChatSender sender) {
		String usrTrim = getTrimmed(user);

		ChatSender tmp = (ChatSender)senders.get(usrTrim);
		if (tmp != null) {
			tmp = null;
		}
		senders.put(usrTrim, sender);
	}
	/**
	 * 
	 * @param user
	 */
	public static void removeSender(String user) {
		String usrTrim = getTrimmed(user);
		senders.remove(usrTrim);
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	public static ChatSender getSender(String user) {
		String usrTrim = getTrimmed(user);
		return (ChatSender)senders.get(usrTrim);
	}
}
