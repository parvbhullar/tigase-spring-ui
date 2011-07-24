package com.ivyinfo.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.service.sequence.ISequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.UserServices;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;

public class User {
	private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
	
	private UserServices userServices = (UserServices) SpringContextUtil
	.getBean("userServices");
	
	/**
	 * 查看用户详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * 查询用户列表信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUserList(HttpServletRequest request,UserBean suserBean) throws Exception{
		String page1=(String)request.getParameter("page");
		page1 = (page1 == null)?"":page1;
		String sidx=(String)request.getParameter("sidx");
		sidx = (sidx == null)?"a.id":sidx;
		String sord=(String)request.getParameter("sord");
		sord = (sord == null)?"desc":sord;
		
		String state=(String)request.getParameter("state");
		state = (state == null)?"2":state;
		
		String orgid = suserBean.getOrganizationid();
		
		Page page=new Page(Constant.PAGE_SIZE_10);
		
		if("".equals(page1)){
			page1="1";
		}
		
		List arrayList=new ArrayList();
		
		int totalCount=0;
		page.setPageNo(new Integer(page1).intValue());
		page.setTotalCount(totalCount);
		
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
        	UserBean userBean =  (UserBean)arrayList.get(i);
                // 存放一条记录的对象
        	String statename = "";
        	if("0".equals(userBean.getState())){
        		statename = "未审批";
        	}else if("1".equals(userBean.getState())){
        		statename = "审批未通过";
        	}else if("2".equals(userBean.getState())){
        		statename = "在职";
        	}else if("3".equals(userBean.getState())){
        		statename = "离职";
        	}
                JSONObject cell = new JSONObject();
                cell.put("id", userBean.getId());
                cell.put("a.logname", userBean.getLogname());
                cell.put("b.name", userBean.getName());
                cell.put("c.email", userBean.getEmail());
                cell.put("c.officephone", userBean.getOfficephone());
                cell.put("c.mobilephone", userBean.getMobilephone());
                cell.put("a.state", statename);
//                cell.put("operation", "冲值卡号");
                // 将该记录放入rows中
                rows.add(cell);
        }
        // 将rows放入json对象中
        jsonObj.put("rows", rows);
        // 自控制台打印输出，以检验json对象生成是否正确
        LOGGER.info("\n sidx="+sidx+";sord="+sord+
        		"\n;(page.getStart()=="+page.getStart()+";;page.getEnd()="+page.getEnd()+";;page.getTotalPages()="+page.getTotalPages()+";totalCount="+totalCount+";page1="+page1+";arrayList.size()="+arrayList.size()+
        		";\n要返回的json对象：\n" + jsonObj.toString());
        
        return jsonObj;
	}
	
	
	
}
