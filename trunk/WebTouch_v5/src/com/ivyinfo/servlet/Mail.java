package com.ivyinfo.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Mail extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(Mail.class);
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String json="[{'attr':{'id':'node_2','rel':'drive'},'data':'C:','state':'closed'},{'attr':{'id':'node_6','rel':'drive'},'data':'D:','state':''}]";
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String action=(String)request.getParameter("action");
		if("SendMail".equals(action))
		{
			
			String to=(String)request.getParameter("to")+";";
			String subject=(String)request.getParameter("subject");
			String content=(String)request.getParameter("content");
			try {
				String json="[{'attr':{'id':'node_2','rel':'drive'},'data':'C:','state':'closed'},{'attr':{'id':'node_6','rel':'drive'},'data':'D:','state':''}]";
				response.getWriter().print(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}