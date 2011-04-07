package com.ivyinfo.im.listener;

import java.util.List;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Presence;

import com.ivyinfo.im.controller.TrafficController;

public class RosterListener {
	private XMPPConnection conn;
	
	public RosterListener(XMPPConnection c ){
		conn = c;
		Roster roster = c.getRoster();
		roster.setSubscriptionMode(Roster.SubscriptionMode.manual);
	}
	
	public void addRoster(String to){
		Presence pr = new Presence(Presence.Type.subscribe);
		pr.setTo(to);
		conn.sendPacket(pr);
	}
	
	public void authSubscription(String loginName) throws Exception{
		ChatListener listener = TrafficController.getListener(loginName);
		if(listener!=null){
			List subscriptions = listener.getNewSubscriptions();
			if (subscriptions != null &&subscriptions.size() > 0) {
				Presence prs = null;
				String from = null;

				for (int i=0;i<subscriptions.size(); i++) {
					prs = (Presence)subscriptions.get(i);
					from = prs.getFrom();
					//out.println("alert('" + from + "');");
					
					// TODO: subscribe onaylama olayı
					prs.setType(Presence.Type.subscribed);
					//prs.setFrom(listener.getUser());
					prs.setTo(from);
					conn.sendPacket(prs);
				}
			}
		}
	}
	
	public void removeRoster(String to){
		
	}
}
