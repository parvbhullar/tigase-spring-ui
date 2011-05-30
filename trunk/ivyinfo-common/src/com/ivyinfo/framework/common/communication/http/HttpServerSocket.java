/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivyinfo.framework.common.communication.http;

import com.ivyinfo.framework.common.resources.ResourcesConfig;
import java.io.IOException;
import java.net.ServerSocket;
/**
 *
 * @author 张砚
 */
public class HttpServerSocket extends Thread {
	ServerSocket ss = null;

	private static final int port = ResourcesConfig.HTTP_PORT;

	public HttpServerSocket() {
	}

	public void run() {
		try {
			ss = new ServerSocket(port);
                  
		} catch (IOException ex) {
			ex.printStackTrace();
			ss = null;
			return;
		}
		// 循环监听
		while (true) {
			HttpSocket hs = null;
			try {
				hs = new HttpSocket();
				hs.s = ss.accept();
				hs.start();
			} catch (IOException ex1) {
			}
		}
	}
}
