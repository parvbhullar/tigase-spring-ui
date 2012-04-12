package org.eredlab.g4.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.rif.web.BaseAction;

/**
 * 演示用 Action
 * 
 * @author XiongChun
 * @since 2010-06-13
 * @see BaseAction
 */
public class TestAction extends BaseAction {

	/**
	 * 测试页面1初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test1Init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return mapping.findForward("test1View");
	}

	/**
	 * 测试页面2初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test2Init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("ID", "测试");
		return mapping.findForward("test2View");
	}

	/**
	 * 测试3页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test3Init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return mapping.findForward("test3View");
	}

	/**
	 * 测试4页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test4Init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return mapping.findForward("test4View");
	}

	/**
	 * 测试5页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward test5Init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return mapping.findForward("test5View");
	}

}
