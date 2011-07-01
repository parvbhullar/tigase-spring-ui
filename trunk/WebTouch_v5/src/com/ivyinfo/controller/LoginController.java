package com.ivyinfo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;
  
@Controller  
public class LoginController{   
    protected final Log logger = LogFactory.getLog(getClass());   
  
    @RequestMapping("/login")   
    public ModelAndView helloWorld(){   
        logger.info("Return View");   
        return new ModelAndView("layout_tree.jsp");   
    }   
    
    @RequestMapping(value="/mail/mailReceive.do",method = RequestMethod.POST)
	public void mailReceive(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		
		LOGGER.info("收件箱 session="+session+";sessionUserBean="+sessionUserBean);
		userBean=(UserBean)sessionUserBean.getUserBean();
		LOGGER.info("收件箱 userBean="+userBean);
		LOGGER.info("收件箱 userBean.getLogname()="+userBean.getLogname()+";userBean"+userBean.getName());
		
		String page1=(String)request.getParameter("page");
		String sidx=(String)request.getParameter("sidx");
		String sord=(String)request.getParameter("sord");
		Page page=new Page(Constant.PAGE_SIZE_10);
		
		if("".equals(page1))
			page1="1";
		List arrayList=new ArrayList();
		int intpage1=new Integer(page1).intValue();
		int totalCount=0;
		try {
			totalCount=auxiliaryMailServices.ReceiveListCount(userBean.getLogname());
			page.setPageNo(new Integer(page1).intValue());
			page.setTotalCount(totalCount);
			arrayList=auxiliaryMailServices.ReceiveList(page.getStart(), page.getEnd(), userBean.getLogname(),sidx,sord);
//			purviewServices.ValidationLogin(logname, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
        // 定义返回的数据类型：json，使用了json-lib
        JSONObject jsonObj = new JSONObject();
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值
        jsonObj.put("page", page1);                // 当前页
        jsonObj.put("total",page.getTotalPages());        // 总页数
        jsonObj.put("records", totalCount);        // 总记录数
        // 定义rows，存放数据
        JSONArray rows = new JSONArray();
        for(int i=0;i<arrayList.size();i++)
        {
        	ReceiveMailBean receiveMailBean =  (ReceiveMailBean)arrayList.get(i);
                // 存放一条记录的对象
                JSONObject cell = new JSONObject();
                cell.put("id", receiveMailBean.getId());
                cell.put("state", receiveMailBean.getState());
                cell.put("sendname", receiveMailBean.getSendname());
                cell.put("subject", receiveMailBean.getSubject());
                cell.put("datetime", receiveMailBean.getDatetime());
                cell.put("filename", receiveMailBean.getFilename());
                cell.put("name", userBean.getLogname());
                // 将该记录放入rows中
                rows.add(cell);
        }
        // 将rows放入json对象中
        jsonObj.put("rows", rows);
        // 自控制台打印输出，以检验json对象生成是否正确
        LOGGER.info("\n sidx="+sidx+";sord="+sord+
        		"\n;(page.getStart()=="+page.getStart()+";;page.getEnd()="+page.getEnd()+";;page.getTotalPages()="+page.getTotalPages()+";totalCount="+totalCount+";page1="+page1+";arrayList.size()="+arrayList.size()+
        		";\n要返回的json对象：\n" + jsonObj.toString());
        // 设置字符编码
        
        // 返回json对象（通过PrintWriter输出）
        response.getWriter().print(jsonObj);
	}
}