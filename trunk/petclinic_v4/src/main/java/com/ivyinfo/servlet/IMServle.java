package com.ivyinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IMServle extends HttpServlet{
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
throws IOException, ServletException
		{
		response.setContentType("text/html;charset=utf-8");  
        PrintWriter out=response.getWriter();  
        String account=request.getParameter("account"); 
        System.out.println("gettt");
        if("iamcrzay".equals(account)){  
            out.print("Sorry,the user is exist");  
        }  
        else{  
        	response.setCharacterEncoding("utf-8");
    		String json="{'total': 2,'page': 1,'records': 13,'rows': [{'cell': ['1','2007-10-01','100.0','20.0','120.0','note 1']},{'cell': ['10','2007-10-06','100.0','20.0','120.0','null']}]}";
    		System.out.println(json);
    		response.getWriter().print(json);
            
        }  
        out.close(); 

		}

		public void doPost(HttpServletRequest request,
            HttpServletResponse response)
			throws IOException, ServletException
	{
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("rows"));
		System.out.println("page="+page+";limit="+limit);
		response.setCharacterEncoding("utf-8");
		String json="{'total': 2,'page': 1,'records': 13,'rows': [{'cell': ['1','2007-10-01','100.0','20.0','120.0','note 1']},{'cell': ['10','2007-10-06','100.0','20.0','120.0','null']}]}";
		System.out.println(json);
		response.getWriter().print(json);
		
	}
}
