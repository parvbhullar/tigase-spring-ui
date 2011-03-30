package com.ivyinfo.mail;

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
import com.ivyinfo.mail.bean.SetupMailBean;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.services.OrganizationServices;
import com.ivyinfo.orgpurview.bean.OrgPurviewBean;
import com.ivyinfo.orgpurview.services.OrgPurviewServices;
import com.ivyinfo.permissions.bean.PermissionsBean;
import com.ivyinfo.permissions.services.PermissionsServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.UserServices;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;

public class Mail {
	private static final Logger LOGGER = LoggerFactory.getLogger(Mail.class);
	
	private OrganizationServices organizationServices = (OrganizationServices) SpringContextUtil
	.getBean("organizationServices");
	
	private UserServices userServices = (UserServices) SpringContextUtil
	.getBean("userServices");
	
	private OrgPurviewServices orgpurviewServices = (OrgPurviewServices) SpringContextUtil
	.getBean("orgpurviewServices");
	
	private PermissionsServices permissionsServices = (PermissionsServices) SpringContextUtil
	.getBean("permissionsServices");
	
	/**
	 * 查看机构详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * 查询所有的权限信息
	 * @return
	 * @throws Exception
	 */
	public JSONArray getOrgPurview() throws Exception{
		
		JSONArray jsonObj = new JSONArray();
		List attendeeList = permissionsServices.AllIndex();
		
		for(int i = 0;i < attendeeList.size();i++){
			PermissionsBean permissionsBean = (PermissionsBean) attendeeList.get(i);
			JSONObject cell = new JSONObject();
			cell.put("name", permissionsBean.getName());
			cell.put("id", permissionsBean.getNumber());
			jsonObj.add(cell);
		}
		
        // 将rows放入json对象中
        // 自控制台打印输出，以检验json对象生成是否正确
        LOGGER.info("\n arrayList.size()="+attendeeList.size()+
        		";\n要返回的json对象：\n" + jsonObj.toString());
        
        return jsonObj;
	}
	
}
