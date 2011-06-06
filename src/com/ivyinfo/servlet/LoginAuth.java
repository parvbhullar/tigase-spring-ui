package com.ivyinfo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.login.bean.LoginBean;
import com.ivyinfo.purview.services.PurviewServices;
import com.ivyinfo.session.bean.SessionUserBean;

public class LoginAuth extends HttpServlet {
	private PurviewServices purviewServices = (PurviewServices) SpringContextUtil
			.getBean("purviewServices");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("get");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		try {
				SessionUserBean sessionUserBean = null;
				try {
					sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
				} catch (Exception e) {
					e.printStackTrace();
				}
				//response.getWriter().print(sessionUserBean.getUserBean().getType());
				response.getWriter().print("2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
