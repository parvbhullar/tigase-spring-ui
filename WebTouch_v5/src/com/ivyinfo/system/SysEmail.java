package com.ivyinfo.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.sequence.ISequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.services.OrganizationServices;
import com.ivyinfo.orgpurview.bean.OrgPurviewBean;
import com.ivyinfo.orgpurview.services.OrgPurviewServices;
import com.ivyinfo.permissions.bean.PermissionsBean;
import com.ivyinfo.permissions.services.PermissionsServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.sysemail.bean.SysEmailBean;
import com.ivyinfo.sysemail.services.SysEmailServices;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.UserServices;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;

public class SysEmail {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysEmail.class);
	
	private SysEmailServices sysemailServices = (SysEmailServices) SpringContextUtil
	.getBean("sysemailServices");
	
	/**
	 * 查看系统邮箱详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String ViewSysMail(String id) throws Exception{
		SysEmailBean sysemailBean=sysemailServices.ViewSysMail(new Integer(id).intValue());
		
		JSONObject  jsonObj=new JSONObject();
		
		jsonObj.put("id", sysemailBean.getId());
		jsonObj.put("name", sysemailBean.getName());
		jsonObj.put("smtpadd", sysemailBean.getSmtpadd());
		jsonObj.put("smtpport", sysemailBean.getSmtpport());
		jsonObj.put("popadd", sysemailBean.getPopadd());
		jsonObj.put("popport", sysemailBean.getPopport());
		jsonObj.put("sslvalidation", sysemailBean.getSslvalidation());
		
		return jsonObj.toString();
	}
	
	/**
	 * 查询系统邮箱列表信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject getMailList(HttpServletRequest request) throws Exception{
		String page1=(String)request.getParameter("page");
		String sidx=(String)request.getParameter("sidx");
		String sord=(String)request.getParameter("sord");
		
		Page page=new Page(Constant.PAGE_SIZE_10);
		
		if("".equals(page1)){
			page1="1";
		}
		
		List arrayList=new ArrayList();
		
		int totalCount=sysemailServices.MailListCount();
		page.setPageNo(new Integer(page1).intValue());
		page.setTotalCount(totalCount);
		arrayList=sysemailServices.AllIndex(page.getStart(), page.getEnd(),sidx,sord);
		
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
        	SysEmailBean sysemailBean =  (SysEmailBean)arrayList.get(i);
        	
        	String vc_sslvalidation = "";
    		String sslvalidation = sysemailBean.getSslvalidation();
    		if("true".equals(sslvalidation)){
    			vc_sslvalidation = "验证";
    		}else if("false".equals(sslvalidation)){
    			vc_sslvalidation = "不验证";
    		}
                // 存放一条记录的对象
                JSONObject cell = new JSONObject();
                cell.put("id", sysemailBean.getId());
                cell.put("name", sysemailBean.getName());
                cell.put("smtpadd", sysemailBean.getSmtpadd());
                cell.put("smtpport", sysemailBean.getSmtpport());
                cell.put("popadd", sysemailBean.getPopadd());
                cell.put("popport", sysemailBean.getPopport());
                cell.put("sslvalidation", vc_sslvalidation);
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
	
	/**
	 * 新增系统邮箱信息
	 * @param request
	 * @param userid
	 * @throws Exception
	 */
	public void AddSysMail(HttpServletRequest request) throws Exception{
		String name=(String)request.getParameter("name");
		System.err.println("name:"+name);
		String smtpadd=(String)request.getParameter("smtpadd");
		String smtpport=(String)request.getParameter("smtpport");
		String popadd=(String)request.getParameter("popadd");
		String popport=(String)request.getParameter("popport");
		String sslvalidation=(String)request.getParameter("sslvalidation");
		
		SysEmailBean sysemailBean = new SysEmailBean();
		sysemailBean.setName(name);
		sysemailBean.setSmtpadd(smtpadd);
		sysemailBean.setSmtpport(smtpport);
		sysemailBean.setPopadd(popadd);
		sysemailBean.setPopport(popport);
		sysemailBean.setSslvalidation(sslvalidation);
		
		ISequence sequenceService = (ISequence) SpringContextUtil.getBean("Sequence");
		long id = sequenceService.getMaxId("t_sys_mail_pact");
		sysemailBean.setId(String.valueOf(id));
		
		sysemailServices.AddSysMail(sysemailBean);
	}
	
	/**
	 * 修改系统邮箱信息
	 * @param request
	 * @param userid
	 * @throws Exception
	 */
	public void UpdSysMail(HttpServletRequest request) throws Exception{
		String id=(String)request.getParameter("id");
		System.err.println("id:"+id);
		String name=(String)request.getParameter("name");
		System.err.println("name:"+name);
		String smtpadd=(String)request.getParameter("smtpadd");
		String smtpport=(String)request.getParameter("smtpport");
		String popadd=(String)request.getParameter("popadd");
		String popport=(String)request.getParameter("popport");
		String sslvalidation=(String)request.getParameter("sslvalidation");
		
		SysEmailBean sysemailBean = new SysEmailBean();
		sysemailBean.setName(name);
		sysemailBean.setSmtpadd(smtpadd);
		sysemailBean.setSmtpport(smtpport);
		sysemailBean.setPopadd(popadd);
		sysemailBean.setPopport(popport);
		sysemailBean.setSslvalidation(sslvalidation);
		
		sysemailBean.setId(id);
		
		sysemailServices.UpdSysMail(sysemailBean);
	}
	
	/**
	 * 删除系统邮箱信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void DelSysMail(String id) throws Exception{
		System.err.println("id:"+id);
		id = id + ",";
		while(id.indexOf(",")>-1){
			String mailid = id.substring(0,id.indexOf(","));
			id = id.substring(id.indexOf(",")+1,id.length());
			
			sysemailServices.DelSysMail(Integer.parseInt(mailid));
		}
	}
}
