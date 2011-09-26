package org.apache.jsp.WEB_002dINF.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.mitian.airad.web.form.MemberForm;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.mitian.airad.CommonDef.userRoleCon;
import com.mitian.airad.web.auth.roles.*;
import com.mitian.airad.*;
import com.mitian.airad.common.auth.*;
import com.mitian.airad.common.auth.context.SecurityContext;
import com.mitian.airad.common.auth.context.SecurityContextHolder;
import com.mitian.airad.web.form.*;
import com.mitian.airad.model.SysConfig;
import com.mitian.airad.model.CoreMemberInfo;

public final class member_005fdetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(18);
    _jspx_dependants.add("/WEB-INF/jspf/taglibs.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/header.jsp");
    _jspx_dependants.add("/WEB-INF/jspf/role_dev_header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/role_dev_and_adv_header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/role_adv_header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/role_agent_header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/role_shopper_header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/role_oss_sales_header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/role_general_header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/role_not_logon.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/errors_detail.jsp");
    _jspx_dependants.add("/WEB-INF/jspf/success.jsp");
    _jspx_dependants.add("/WEB-INF/jspf/footer.jsp");
    _jspx_dependants.add("/WEB-INF/tags/c.tld");
    _jspx_dependants.add("/WEB-INF/tags/fn.tld");
    _jspx_dependants.add("/WEB-INF/tags/fmt.tld");
    _jspx_dependants.add("/WEB-INF/tags/airad.tld");
    _jspx_dependants.add("/WEB-INF/tags/elfuncs.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fform_0026_005fname_005fmethod_005fid_005fcommandName_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005fform_0026_005fname_005fmethod_005fid_005fcommandName_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fform_005fform_0026_005fname_005fmethod_005fid_005fcommandName_005faction.release();
    _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.release();
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html\n");
      out.write("\txmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("<title>会员信息</title>\n");
      out.write("<link type=\"text/css\" href=\"/style/main.css\" rel=\"stylesheet\" />\n");
      out.write("</head>\n");
      out.write("<body>\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;

SecurityContext context=SecurityContextHolder.getContext();
BaseRole baseRole=null;
String loginEmail=null;
CoreMemberInfo memberInfo=null;
AbstractForm abstractForm=null;
if(context!=null){
memberInfo=context.getMemberInfo();
    loginEmail=memberInfo.getEmail();
  baseRole=memberInfo.getRole();
  abstractForm=(AbstractForm)request.getAttribute(Constants.DEFAULT_COMMAND);
  Boolean isMemberPage=abstractForm instanceof MemberForm;
  String navClass="";
  String navAccountClass="";
  if(isMemberPage){
      navAccountClass="now";
  }else{
      navClass="now";
  }
  request.setAttribute("loginEmail",loginEmail);
  request.setAttribute("isMemberPage",isMemberPage);
  request.setAttribute("navClass",navClass);
  request.setAttribute("navAccountClass",navAccountClass);
  request.setAttribute("baseRole",baseRole);
}

      out.write("<link type=\"text/css\" href=\"/style/main.css\" rel=\"stylesheet\" />\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery.js\"></script>\n");
      out.write("<div id=\"header\">\n");
      out.write("  <div class=\"headCon\">\n");
      out.write("      <input type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.action }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" name=\"actionType\" />\n");
      if (_jspx_meth_c_005fchoose_005f0(_jspx_page_context))
        return;
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<script>\n");
      out.write("function exchange(){\n");
      out.write("    $(\"#imenu\").toggle();             \n");
      out.write("}\n");
      out.write("function addTitleCss(){\n");
      out.write("  var d=document.getElementById(\"logonShowDiv\");\n");
      out.write("  if(d==null||d=='undefine'){\n");
      out.write("    $(\"#header\").css(\"height\",\"35px\");//添加样式\n");
      out.write("  }\n");
      out.write("}\n");
      out.write("addTitleCss();\n");
      out.write("</script>");
      out.write("<div id=\"main\">\n");
      out.write("<div class=\"mainCon\">\n");
      out.write("  ");
 //æäº¤ä¹åæ¾ç¤ºéè¯¯ä¿¡æ¯ï¼è·éæ¯ä¸ªinput 
      if (_jspx_meth_c_005fchoose_005f15(_jspx_page_context))
        return;
      out.write("<script>\n");
      out.write(" $(document).ready(function(){\n");
      out.write("\t  $(\"div[name='_errorinfo']\").each(function(){\n");
      out.write("\t\t  var tid=$(this).attr(\"id\").replace(\"_error\",\"#\").replace(/\\./g,\"\\\\.\");\n");
      out.write("\t\t  $(tid).after($(this));\n");
      out.write("\t\t  });\n");
      out.write("\n");
      out.write("\t });\n");
      out.write("</script>");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("<!--\n");
      out.write("<div class=\"wa\">只有在您提供会员信息并通过审核之后，您的广告才可以被发布。</div> -->\n");
      out.write("<h1 class=\"tit\">会员信息 </h1>\n");
      out.write("<div class=\"leftCon\">\n");
      out.write("<h1>账号基本信息</h1>\n");
      if (_jspx_meth_form_005fform_005f0(_jspx_page_context))
        return;
      out.write("</div>\n");
      out.write("<div class=\"rightCon\">\n");
      out.write("<div class=\"infoCon\">\n");
      out.write("<h2>提示</h2>\n");
      out.write("<ul>\n");
      out.write("<li>请您尽快完善个人资料，以便我们更好的为您提供服务，以及提高您账号的安全性。</li>\n");
      out.write("</ul>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<!-- 开发嵌入end-->\n");
      out.write("<div id=\"footer\">\r\n");
      out.write("<div><a href=\"/\">首页</a> | <a href=\"/about-us.html\">关于我们</a> | <a href=\"/policy.html\">隐私协议</a> | <a href=\"/terms.html\">服务条款</a> | <a href=\"/join-us.html\">加入我们</a> | <a href=\"mailto:contact@airad.com\">联系我们</a> | <a href=\"help.html\">帮助中心</a> | <a href=\"/send_req.html\">参加开发者收益保障活动</a>\r\n");
      out.write("</div>\r\n");
      out.write("&copy;2011 米田科技有限公司 版权所有\r\n");
      out.write("<div style=\"display:none\"><script language=\"javascript\" type=\"text/javascript\" src=\"/js/5291024.js\"></script></div></div>\r\n");
      out.write("<div id=\"chat\">\r\n");
      out.write("<img src=\"/images/btn_chat.gif\" width=\"174px\" height=\"23px\" style=\"cursor:pointer\" onclick=\"javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=800008968&eid=2188z8p8p8p8p8z808x8z&o=www.airAD.com&q=7', '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');\" border=\"0\" alt=\"在线客服 800008968\" />\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/member.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/campaign.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/app.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/ad.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/adGroup.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/recharge.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/account.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/cookie.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/common.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/withdraw.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/airad.jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        $.rightBottom(\"#chat\",174,23);\r\n");
      out.write("        $(window).scroll(function(){\r\n");
      out.write("            $.rightBottom(\"#chat\",174,23);\r\n");
      out.write("        }); \r\n");
      out.write("        $(window).resize(function() {\r\n");
      out.write("           $.rightBottom(\"#chat\",174,23);\r\n");
      out.write("        });\r\n");
      out.write("    </script>\r\n");
      out.write("<script>\n");
      out.write("$(document).ready(function(){\n");
      out.write("  addCss(\"queryMember\");\n");
      out.write("});\n");
      out.write("</script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/jspf/header.jsp(10,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cookie.ct.value eq '2'}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jspf/header.jsp(10,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("isCurrentDevRole");
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f0.setParent(null);
    int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
    if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fwhen_005f6(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fwhen_005f12(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fwhen_005f14(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fwhen_005f16(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fwhen_005f18(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fwhen_005f19(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f14(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jspf/header.jsp(43,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseRole.dev}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"popNav\" id=\"imenu\" style=\"display: none\">\r\n");
        out.write("                    <div>\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fchoose_005f1(_jspx_th_c_005fwhen_005f0, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"memInfo\">\r\n");
        out.write("                    Hi, <strong>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginEmail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</strong>\r\n");
        out.write("                    <span>|</span>\r\n");
        out.write("                    <a href=\"javascript:void(0);\" id=\"_roleToggle\"><img height=\"16\" align=\"absmiddle\" width=\"16\" alt=\"切换角色\" src=\"images/ico_mem.gif\" title=\"切换角色\">切换角色</a><span>|</span>\r\n");
        out.write("                    <a href=\"member.do?action=logout\">[退出]</a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"logo\">\r\n");
        out.write("                \t\t   ");
        if (_jspx_meth_c_005fchoose_005f2(_jspx_th_c_005fwhen_005f0, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                <div id=\"logonShowDiv\" style=\"display: block\">\r\n");
        out.write("                    <div id=\"nav\">\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fchoose_005f3(_jspx_th_c_005fwhen_005f0, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                    <div id=\"navChl\" class=\"navChl\">\r\n");
        out.write("                      ");
        if (_jspx_meth_c_005fchoose_005f4(_jspx_th_c_005fwhen_005f0, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                </div>");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f1 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f0);
    int _jspx_eval_c_005fchoose_005f1 = _jspx_th_c_005fchoose_005f1.doStartTag();
    if (_jspx_eval_c_005fchoose_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f1(_jspx_th_c_005fchoose_005f1, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f0(_jspx_th_c_005fchoose_005f1, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f1 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f1);
    // /WEB-INF/jspf/role_dev_header.jspf(5,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isCurrentDevRole}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f1 = _jspx_th_c_005fwhen_005f1.doStartTag();
    if (_jspx_eval_c_005fwhen_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"#\" id=\"_switchAdv\">角色切换为广告主</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f1);
    int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"#\" id=\"_switchDev\">角色切换为开发者</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f2 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f0);
    int _jspx_eval_c_005fchoose_005f2 = _jspx_th_c_005fchoose_005f2.doStartTag();
    if (_jspx_eval_c_005fchoose_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f2(_jspx_th_c_005fchoose_005f2, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f1(_jspx_th_c_005fchoose_005f2, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f2 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f2);
    // /WEB-INF/jspf/role_dev_header.jspf(22,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isCurrentDevRole}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f2 = _jspx_th_c_005fwhen_005f2.doStartTag();
    if (_jspx_eval_c_005fwhen_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=developlist\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f1 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f2);
    int _jspx_eval_c_005fotherwise_005f1 = _jspx_th_c_005fotherwise_005f1.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=advlist\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f3 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f0);
    int _jspx_eval_c_005fchoose_005f3 = _jspx_th_c_005fchoose_005f3.doStartTag();
    if (_jspx_eval_c_005fchoose_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f3(_jspx_th_c_005fchoose_005f3, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f2(_jspx_th_c_005fchoose_005f3, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f3 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f3);
    // /WEB-INF/jspf/role_dev_header.jspf(33,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isCurrentDevRole}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f3 = _jspx_th_c_005fwhen_005f3.doStartTag();
    if (_jspx_eval_c_005fwhen_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/personal.do?action=developlist\">我是开发者</a>\r\n");
        out.write("                     <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navAccountClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" href=\"/member.do?action=queryMember\">账户管理</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f2 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f3);
    int _jspx_eval_c_005fotherwise_005f2 = _jspx_th_c_005fotherwise_005f2.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/personal.do?action=advlist\">我是广告主</a>\r\n");
        out.write("                       <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navAccountClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/member.do?action=queryMember\">账户管理</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f4 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f0);
    int _jspx_eval_c_005fchoose_005f4 = _jspx_th_c_005fchoose_005f4.doStartTag();
    if (_jspx_eval_c_005fchoose_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f4(_jspx_th_c_005fchoose_005f4, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f3(_jspx_th_c_005fchoose_005f4, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f4 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f4);
    // /WEB-INF/jspf/role_dev_header.jspf(45,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isMemberPage }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f4 = _jspx_th_c_005fwhen_005f4.doStartTag();
    if (_jspx_eval_c_005fwhen_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/member.do?action=queryMember\">会员信息</a>\r\n");
        out.write("            \t\t   <a href=\"/member.do?action=updateLoginPassword\">修改密码</a>     \r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f3 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f4);
    int _jspx_eval_c_005fotherwise_005f3 = _jspx_th_c_005fotherwise_005f3.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fchoose_005f5(_jspx_th_c_005fotherwise_005f3, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f5 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f3);
    int _jspx_eval_c_005fchoose_005f5 = _jspx_th_c_005fchoose_005f5.doStartTag();
    if (_jspx_eval_c_005fchoose_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f5(_jspx_th_c_005fchoose_005f5, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f4(_jspx_th_c_005fchoose_005f5, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f5 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f5);
    // /WEB-INF/jspf/role_dev_header.jspf(51,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isCurrentDevRole}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f5 = _jspx_th_c_005fwhen_005f5.doStartTag();
    if (_jspx_eval_c_005fwhen_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=developlist\">个人中心</a>\r\n");
        out.write("                        <a href=\"/app.do?action=listApp\">应用程序管理</a>\r\n");
        out.write("                        <a href=\"/report.do?action=listReport\">报表统计</a>\r\n");
        out.write("                        <a href=\"/withdraw.do?action=queryWithdrawListByMemberId\">账务账本</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f4 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f5);
    int _jspx_eval_c_005fotherwise_005f4 = _jspx_th_c_005fotherwise_005f4.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=advlist\">个人中心</a>\r\n");
        out.write("\t\t\t\t            <a href=\"/adGroup.do?action=list\">广告管理</a>\r\n");
        out.write("\t\t\t\t            <a href=\"/ad.do?action=detailEdit\">新建广告</a>\r\n");
        out.write("\t\t\t\t            <a href=\"/report.do?action=listReport\">报表统计</a>\r\n");
        out.write("\t\t\t\t            <a href=\"/rechargeHis.do?action=list\">账务账本</a>\r\n");
        out.write("\t\t\t\t       \t<a href=\"/advertiser.do?action=authenticatePage\">身份认证</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f6 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jspf/header.jsp(46,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f6.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseRole.advAndDev}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f6 = _jspx_th_c_005fwhen_005f6.doStartTag();
    if (_jspx_eval_c_005fwhen_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"popNav\" id=\"imenu\" style=\"display: none\">\r\n");
        out.write("                    <div>\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fchoose_005f6(_jspx_th_c_005fwhen_005f6, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"memInfo\">\r\n");
        out.write("                    Hi, <strong>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginEmail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</strong>\r\n");
        out.write("                    <span>|</span>\r\n");
        out.write("                    <a href=\"javascript:void(0);\" id=\"_roleToggle\"><img height=\"16\" align=\"absmiddle\" width=\"16\" alt=\"切换角色\" src=\"images/ico_mem.gif\" title=\"切换角色\">切换角色</a><span>|</span>\r\n");
        out.write("                    <a href=\"member.do?action=logout\">[退出]</a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"logo\">\r\n");
        out.write("                \t\t   ");
        if (_jspx_meth_c_005fchoose_005f7(_jspx_th_c_005fwhen_005f6, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                <div id=\"logonShowDiv\" style=\"display: block\">\r\n");
        out.write("                    <div id=\"nav\">\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fchoose_005f8(_jspx_th_c_005fwhen_005f6, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                    <div id=\"navChl\" class=\"navChl\">\r\n");
        out.write("                      ");
        if (_jspx_meth_c_005fchoose_005f9(_jspx_th_c_005fwhen_005f6, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                </div>");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f6 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f6);
    int _jspx_eval_c_005fchoose_005f6 = _jspx_th_c_005fchoose_005f6.doStartTag();
    if (_jspx_eval_c_005fchoose_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f7(_jspx_th_c_005fchoose_005f6, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f5(_jspx_th_c_005fchoose_005f6, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f7 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f6);
    // /WEB-INF/jspf/role_dev_and_adv_header.jspf(5,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f7.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isCurrentDevRole}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f7 = _jspx_th_c_005fwhen_005f7.doStartTag();
    if (_jspx_eval_c_005fwhen_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"javascript:void(0);\" id=\"_switchAdv\">角色切换为广告主</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f5 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f6);
    int _jspx_eval_c_005fotherwise_005f5 = _jspx_th_c_005fotherwise_005f5.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"javascript:void(0);\" id=\"_switchDev\">角色切换为开发者</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f7 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f6);
    int _jspx_eval_c_005fchoose_005f7 = _jspx_th_c_005fchoose_005f7.doStartTag();
    if (_jspx_eval_c_005fchoose_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f8(_jspx_th_c_005fchoose_005f7, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f6(_jspx_th_c_005fchoose_005f7, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f8 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f7);
    // /WEB-INF/jspf/role_dev_and_adv_header.jspf(22,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f8.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isCurrentDevRole}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f8 = _jspx_th_c_005fwhen_005f8.doStartTag();
    if (_jspx_eval_c_005fwhen_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=developlist\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f6 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f7);
    int _jspx_eval_c_005fotherwise_005f6 = _jspx_th_c_005fotherwise_005f6.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=advlist\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f8 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f6);
    int _jspx_eval_c_005fchoose_005f8 = _jspx_th_c_005fchoose_005f8.doStartTag();
    if (_jspx_eval_c_005fchoose_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f9(_jspx_th_c_005fchoose_005f8, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f7(_jspx_th_c_005fchoose_005f8, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f9 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f8);
    // /WEB-INF/jspf/role_dev_and_adv_header.jspf(33,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f9.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isCurrentDevRole}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f9 = _jspx_th_c_005fwhen_005f9.doStartTag();
    if (_jspx_eval_c_005fwhen_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/personal.do?action=developlist\">我是开发者</a>\r\n");
        out.write("                     <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navAccountClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" href=\"/member.do?action=queryMember\">账户管理</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f9);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f7 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f8);
    int _jspx_eval_c_005fotherwise_005f7 = _jspx_th_c_005fotherwise_005f7.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/personal.do?action=advlist\">我是广告主</a>\r\n");
        out.write("                       <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navAccountClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/member.do?action=queryMember\">账户管理</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f9 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f6);
    int _jspx_eval_c_005fchoose_005f9 = _jspx_th_c_005fchoose_005f9.doStartTag();
    if (_jspx_eval_c_005fchoose_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f10(_jspx_th_c_005fchoose_005f9, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f8(_jspx_th_c_005fchoose_005f9, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f9);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f10 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f9);
    // /WEB-INF/jspf/role_dev_and_adv_header.jspf(45,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f10.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isMemberPage }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f10 = _jspx_th_c_005fwhen_005f10.doStartTag();
    if (_jspx_eval_c_005fwhen_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/member.do?action=queryMember\">会员信息</a>\r\n");
        out.write("            \t\t   <a href=\"/member.do?action=updateLoginPassword\">修改密码</a>     \r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f8 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f9);
    int _jspx_eval_c_005fotherwise_005f8 = _jspx_th_c_005fotherwise_005f8.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fchoose_005f10(_jspx_th_c_005fotherwise_005f8, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f10 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f8);
    int _jspx_eval_c_005fchoose_005f10 = _jspx_th_c_005fchoose_005f10.doStartTag();
    if (_jspx_eval_c_005fchoose_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f11(_jspx_th_c_005fchoose_005f10, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f9(_jspx_th_c_005fchoose_005f10, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f11 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f10);
    // /WEB-INF/jspf/role_dev_and_adv_header.jspf(51,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f11.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isCurrentDevRole}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f11 = _jspx_th_c_005fwhen_005f11.doStartTag();
    if (_jspx_eval_c_005fwhen_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=developlist\">个人中心</a>\r\n");
        out.write("                        <a href=\"/app.do?action=listApp\">应用程序管理</a>\r\n");
        out.write("                        <a href=\"/report.do?action=listReport\">报表统计</a>\r\n");
        out.write("                        <a href=\"/withdraw.do?action=queryWithdrawListByMemberId\">账务账本</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f11);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f9 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f10);
    int _jspx_eval_c_005fotherwise_005f9 = _jspx_th_c_005fotherwise_005f9.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a  href=\"/personal.do?action=advlist\">个人中心</a>\r\n");
        out.write("\t\t\t\t            <a href=\"/adGroup.do?action=list\">广告管理</a>\r\n");
        out.write("\t\t\t\t            <a href=\"/ad.do?action=detailEdit\">新建广告</a>\r\n");
        out.write("\t\t\t\t            <a href=\"/report.do?action=listReport\">报表统计</a>\r\n");
        out.write("\t\t\t\t            <a href=\"/rechargeHis.do?action=list\">账务账本</a>\r\n");
        out.write("\t\t\t\t          <a href=\"/accMailNotice.do?action=openNotice\">通知设置</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f9);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f12 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f12.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jspf/header.jsp(49,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f12.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseRole.advertisers}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f12 = _jspx_th_c_005fwhen_005f12.doStartTag();
    if (_jspx_eval_c_005fwhen_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"memInfo\">\r\n");
        out.write("                    Hi, <strong>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginEmail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</strong>\r\n");
        out.write("                    <span>|</span>\r\n");
        out.write("                    <a href=\"member.do?action=logout\">[退出]</a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"logo\">\r\n");
        out.write("                        <a href=\"/personal.do?action=advlist\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div id=\"logonShowDiv\" style=\"display: block\">\r\n");
        out.write("                    <div id=\"nav\">\r\n");
        out.write("                        <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"   href=\"/personal.do?action=advlist\">我是广告主</a>\r\n");
        out.write("                        <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navAccountClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/member.do?action=queryMember\">账户管理</a>\r\n");
        out.write("                    </div>\r\n");
        out.write("                    <div id=\"navChl\" class=\"navChl\">\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fchoose_005f11(_jspx_th_c_005fwhen_005f12, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                </div>");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f12);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f11 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f12);
    int _jspx_eval_c_005fchoose_005f11 = _jspx_th_c_005fchoose_005f11.doStartTag();
    if (_jspx_eval_c_005fchoose_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f13(_jspx_th_c_005fchoose_005f11, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f10(_jspx_th_c_005fchoose_005f11, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f11);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f13 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f13.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f11);
    // /WEB-INF/jspf/role_adv_header.jspf(17,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f13.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isMemberPage }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f13 = _jspx_th_c_005fwhen_005f13.doStartTag();
    if (_jspx_eval_c_005fwhen_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/member.do?action=queryMember\">会员信息</a>\r\n");
        out.write("            \t\t   <a href=\"/member.do?action=updateLoginPassword\">修改密码</a>     \r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f13);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f10 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f11);
    int _jspx_eval_c_005fotherwise_005f10 = _jspx_th_c_005fotherwise_005f10.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=advlist\">个人中心</a>\r\n");
        out.write("\t\t\t            <a href=\"/adGroup.do?action=list\">广告管理</a>\r\n");
        out.write("\t\t\t            <a href=\"/ad.do?action=detailEdit\">新建广告</a>\r\n");
        out.write("\t\t\t            <a href=\"/report.do?action=listReport\">报表统计</a>\r\n");
        out.write("\t\t\t            <a href=\"/rechargeHis.do?action=list\">账务账本</a>\r\n");
        out.write("\t\t\t            <a href=\"/accMailNotice.do?action=openNotice\">通知设置</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f14 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f14.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jspf/header.jsp(52,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f14.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseRole.agents}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f14 = _jspx_th_c_005fwhen_005f14.doStartTag();
    if (_jspx_eval_c_005fwhen_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"memInfo\">\r\n");
        out.write("                    Hi, <strong>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginEmail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</strong>\r\n");
        out.write("                    <span>|</span>\r\n");
        out.write("                    <a href=\"member.do?action=logout\">[退出]</a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"logo\">\r\n");
        out.write("                         <a href=\"/personal.do?action=advlist\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div id=\"logonShowDiv\" style=\"display: block\">\r\n");
        out.write("                     <div id=\"nav\">\r\n");
        out.write("                        <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"   href=\"/agentRelation.do?action=agencyList\">我是代理商</a>\r\n");
        out.write("                        <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navAccountClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/member.do?action=queryMember\">账户管理</a>\r\n");
        out.write("                    </div>\r\n");
        out.write("                    <div id=\"navChl\" class=\"navChl\">\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fchoose_005f12(_jspx_th_c_005fwhen_005f14, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                </div>");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f14);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f12 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f12.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f14);
    int _jspx_eval_c_005fchoose_005f12 = _jspx_th_c_005fchoose_005f12.doStartTag();
    if (_jspx_eval_c_005fchoose_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f15(_jspx_th_c_005fchoose_005f12, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f11(_jspx_th_c_005fchoose_005f12, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f12);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f15 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f15.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f12);
    // /WEB-INF/jspf/role_agent_header.jspf(17,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f15.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isMemberPage }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f15 = _jspx_th_c_005fwhen_005f15.doStartTag();
    if (_jspx_eval_c_005fwhen_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/member.do?action=queryMember\">会员信息</a>\r\n");
        out.write("            \t\t   <a href=\"/member.do?action=updateLoginPassword\">修改密码</a>     \r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f15.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f15);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f11 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f12);
    int _jspx_eval_c_005fotherwise_005f11 = _jspx_th_c_005fotherwise_005f11.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/agentRelation.do?action=agencyList\">广告商管理</a>\r\n");
        out.write("\t\t\t            <a href=\"/agentRelation.do?action=hisList\">广告商充值记录管理</a>\r\n");
        out.write("\t\t\t            <a href=\"/report.do?action=listReport\">报表统计</a>\r\n");
        out.write("\t\t\t            <a href=\"/withdraw.do?action=queryWithdrawListByMemberId\">账务账本</a>     \r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f11);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f16 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f16.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jspf/header.jsp(55,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f16.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseRole.shopper}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f16 = _jspx_th_c_005fwhen_005f16.doStartTag();
    if (_jspx_eval_c_005fwhen_005f16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"memInfo\">\r\n");
        out.write("                    Hi, <strong>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginEmail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</strong>\r\n");
        out.write("                    <span>|</span>\r\n");
        out.write("                    <a href=\"member.do?action=logout\">[退出]</a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"logo\">\r\n");
        out.write("                        <a href=\"/personal.do?action=index\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div id=\"logonShowDiv\" style=\"display: block\">\r\n");
        out.write("                    <div id=\"nav\">\r\n");
        out.write("                        <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"   href=\"/personal.do?action=index\">我是商铺主</a>\r\n");
        out.write("                        <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navAccountClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/member.do?action=queryMember\">账户管理</a>\r\n");
        out.write("                    </div>\r\n");
        out.write("                    <div id=\"navChl\" class=\"navChl\">\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fchoose_005f13(_jspx_th_c_005fwhen_005f16, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("                </div>");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f16.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f16);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f13 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f13.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f16);
    int _jspx_eval_c_005fchoose_005f13 = _jspx_th_c_005fchoose_005f13.doStartTag();
    if (_jspx_eval_c_005fchoose_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f17(_jspx_th_c_005fchoose_005f13, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f12(_jspx_th_c_005fchoose_005f13, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f13);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f17 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f17.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f13);
    // /WEB-INF/jspf/role_shopper_header.jspf(17,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f17.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isMemberPage }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f17 = _jspx_th_c_005fwhen_005f17.doStartTag();
    if (_jspx_eval_c_005fwhen_005f17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/member.do?action=queryMember\">会员信息</a>\r\n");
        out.write("            \t\t   <a href=\"/member.do?action=updateLoginPassword\">修改密码</a>     \r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f17.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f17);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f12 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f12.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f13);
    int _jspx_eval_c_005fotherwise_005f12 = _jspx_th_c_005fotherwise_005f12.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/personal.do?action=index\">个人中心</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f12);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f18(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f18 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f18.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jspf/header.jsp(58,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f18.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseRole.ossSales}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f18 = _jspx_th_c_005fwhen_005f18.doStartTag();
    if (_jspx_eval_c_005fwhen_005f18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"memInfo\">\r\n");
        out.write("                    Hi, <strong>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginEmail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</strong>\r\n");
        out.write("                    <span>|</span>\r\n");
        out.write("                    <a href=\"/oss/logout\">[退出]</a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"logo\">\r\n");
        out.write("                        <a href=\"/personal.do?action=index\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div id=\"logonShowDiv\" style=\"display: block\">\r\n");
        out.write("                    <div id=\"nav\">\r\n");
        out.write("                        <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"   href=\"/personal.do?action=index\">我是商铺主</a>\r\n");
        out.write("                    </div>\r\n");
        out.write("                    <div id=\"navChl\" class=\"navChl\">\r\n");
        out.write("                         <a href=\"/personal.do?action=index\">个人中心</a>\r\n");
        out.write("                         <a href=\"/adGroup.do?action=list\">广告管理</a>\r\n");
        out.write("                    </div>\r\n");
        out.write("                </div>");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f18.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f18);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f19(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f19 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f19.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/jspf/header.jsp(61,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f19.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseRole.general}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f19 = _jspx_th_c_005fwhen_005f19.doStartTag();
    if (_jspx_eval_c_005fwhen_005f19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"memInfo\">\r\n");
        out.write("                    Hi, <strong>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginEmail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</strong>\r\n");
        out.write("                    <span>|</span>\r\n");
        out.write("                    <a href=\"member.do?action=logout\">[退出]</a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div class=\"logo\">\r\n");
        out.write("                         <a href=\"/personal.do?action=advlist\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                </div>\r\n");
        out.write("                <div id=\"logonShowDiv\" style=\"display: block\">\r\n");
        out.write("                     <div id=\"nav\">\r\n");
        out.write("                         <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" href=\"/personal.do?action=advlist\">我是广告主</a>\r\n");
        out.write("                        <a class=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${navAccountClass}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  href=\"/member.do?action=queryMember\">账户管理</a>\r\n");
        out.write("                    </div>\r\n");
        out.write("                    <div id=\"navChl\" class=\"navChl\">\r\n");
        out.write("                    ");
        if (_jspx_meth_c_005fchoose_005f14(_jspx_th_c_005fwhen_005f19, _jspx_page_context))
          return true;
        out.write("</div>\r\n");
        out.write("            </div>\r\n");
        out.write("        <div id=\"authentication\" style=\"display:none;\">\r\n");
        out.write("  <a href=\"/advertiser.do?action=authenticatePage\">身份认证</a>\r\n");
        out.write("</div>");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f19.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f19);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f19, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f14 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f14.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f19);
    int _jspx_eval_c_005fchoose_005f14 = _jspx_th_c_005fchoose_005f14.doStartTag();
    if (_jspx_eval_c_005fchoose_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f20(_jspx_th_c_005fchoose_005f14, _jspx_page_context))
          return true;
        if (_jspx_meth_c_005fotherwise_005f13(_jspx_th_c_005fchoose_005f14, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f14);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f20(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f20 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f20.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f14);
    // /WEB-INF/jspf/role_general_header.jspf(17,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f20.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isMemberPage }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f20 = _jspx_th_c_005fwhen_005f20.doStartTag();
    if (_jspx_eval_c_005fwhen_005f20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a href=\"/member.do?action=queryMember\">会员信息</a>\r\n");
        out.write("            \t\t   <a href=\"/member.do?action=updateLoginPassword\">修改密码</a>     \r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f20.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f20);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f13 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f13.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f14);
    int _jspx_eval_c_005fotherwise_005f13 = _jspx_th_c_005fotherwise_005f13.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<a class=\"now\" href=\"/personal.do?action=advlist\">个人中心</a>\r\n");
        out.write("\t\t\t            <a href=\"/adGroup.do?action=list\">广告管理</a>\r\n");
        out.write("\t\t\t            <a href=\"/ad.do?action=detailEdit\">新建广告</a>\r\n");
        out.write("\t\t\t            <a href=\"/report.do?action=listReport\">报表统计</a>\r\n");
        out.write("\t\t\t            <a href=\"/rechargeHis.do?action=list\">账务账本</a>  \r\n");
        out.write("  \t\t\t\t\t\t<a href=\"/advertiser.do?action=authenticatePage\">身份认证</a>\r\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f13);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f14 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f14.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    int _jspx_eval_c_005fotherwise_005f14 = _jspx_th_c_005fotherwise_005f14.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"logo\">\r\n");
        out.write("                       <a href=\"/personal.do?action=advlist\"><img alt=\"airAD\" src=\"/images/logo.gif\"></a>\r\n");
        out.write("                </div>\r\n");
        out.write("            </div>\r\n");
        out.write("</div>");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f14);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f15(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f15 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f15.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f15.setParent(null);
    int _jspx_eval_c_005fchoose_005f15 = _jspx_th_c_005fchoose_005f15.doStartTag();
    if (_jspx_eval_c_005fchoose_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fwhen_005f21(_jspx_th_c_005fchoose_005f15, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f15.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f15);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f21(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f21 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f21.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f15);
    // /WEB-INF/jspf/errors_detail.jsp(3,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f21.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty command.errors }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f21 = _jspx_th_c_005fwhen_005f21.doStartTag();
    if (_jspx_eval_c_005fwhen_005f21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        if (_jspx_meth_c_005fforEach_005f0(_jspx_th_c_005fwhen_005f21, _jspx_page_context))
          return true;
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f21.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f21);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fwhen_005f21, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fwhen_005f21);
    // /WEB-INF/jspf/errors_detail.jsp(4,14) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.errors}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jspf/errors_detail.jsp(4,14) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("errorinfo");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("<div class=\"wrBox\"  name=\"_errorinfo\"   id=\"_error");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errorinfo.key}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" >\n");
          out.write("              <div  class=\"wr\" >");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errorinfo.value}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</div>\n");
          out.write("              </div>\n");
          out.write("              ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/jspf/success.jsp(1,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty successinfo}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div class=\"suc mt\">\r\n");
        out.write("         <div>\r\n");
        out.write("           <h2>æäº¤æç¤º</h2>\r\n");
        out.write("            <ul>\r\n");
        out.write("              <li style=\"color: black;\"> ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${successinfo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</li>\r\n");
        out.write("           </ul>\r\n");
        out.write("        </div>\r\n");
        out.write("    </div>\r\n");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_form_005fform_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:form
    org.springframework.web.servlet.tags.form.FormTag _jspx_th_form_005fform_005f0 = (org.springframework.web.servlet.tags.form.FormTag) _005fjspx_005ftagPool_005fform_005fform_0026_005fname_005fmethod_005fid_005fcommandName_005faction.get(org.springframework.web.servlet.tags.form.FormTag.class);
    _jspx_th_form_005fform_005f0.setPageContext(_jspx_page_context);
    _jspx_th_form_005fform_005f0.setParent(null);
    // /WEB-INF/jsp/member/member_detail.jsp(25,0) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005fform_005f0.setName("memberDetail");
    // /WEB-INF/jsp/member/member_detail.jsp(25,0) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005fform_005f0.setId("memberDetail");
    // /WEB-INF/jsp/member/member_detail.jsp(25,0) name = method type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005fform_005f0.setMethod("post");
    // /WEB-INF/jsp/member/member_detail.jsp(25,0) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005fform_005f0.setAction("/member.do?action=doUpdateMemberInfo");
    // /WEB-INF/jsp/member/member_detail.jsp(25,0) name = commandName type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005fform_005f0.setCommandName("command");
    int[] _jspx_push_body_count_form_005fform_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_form_005fform_005f0 = _jspx_th_form_005fform_005f0.doStartTag();
      if (_jspx_eval_form_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tabNF\">\n");
          out.write("\t\t<col width=\"30%\" />\n");
          out.write("\t\t<col width=\"70%\" />\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th><span class=\"must\">*</span>注册邮箱</th>\n");
          out.write("\t\t\t<td>\n");
          out.write("\t\t\t");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.coreMemberInfo.email}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th><span class=\"must\"></span>手机号码</th>\n");
          out.write("\t\t\t<td>");
          if (_jspx_meth_form_005finput_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th><span class=\"must\"></span>通信地址</th>\n");
          out.write("\t\t\t<td>");
          if (_jspx_meth_form_005finput_005f1(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th><span class=\"must\"></span>邮政编码</th>\n");
          out.write("\t\t\t<td>");
          if (_jspx_meth_form_005finput_005f2(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th>QQ</th>\n");
          out.write("\t\t\t<td>");
          if (_jspx_meth_form_005finput_005f3(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th><span class=\"must\"></span>MSN</th>\n");
          out.write("\t\t\t<td>");
          if (_jspx_meth_form_005finput_005f4(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th>固定电话</th>\n");
          out.write("\t\t\t<td>");
          if (_jspx_meth_form_005finput_005f5(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th>公司名称</th>\n");
          out.write("\t\t\t<td>");
          if (_jspx_meth_form_005finput_005f6(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th>会员类型</th>\n");
          out.write("\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.coreMemberInfo.memberTypeShowName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th>注册时间</th>\n");
          out.write("\t\t\t<td>\n");
          out.write("\t\t\t ");
          if (_jspx_meth_fmt_005fformatDate_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th>上次登录时间</th>\n");
          out.write("\t\t\t<td>\n");
          out.write("\t\t\t");
          if (_jspx_meth_fmt_005fformatDate_005f1(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
            return true;
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th>上次登录IP</th>\n");
          out.write("\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.userHis.loginIp}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t</table>\n");
          out.write("\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tabNF\">\n");
          out.write("\t\t<col width=\"30%\" />\n");
          out.write("\t\t<col width=\"70%\" />\n");
          out.write("\t\t<tr>\n");
          out.write("\t\t\t<th>&nbsp;</th>\n");
          out.write("\t\t\t<td>\n");
          out.write("\t\t\t<div class=\"btnBox\">\n");
          out.write("\t\t\t   <button type=\"submit\" class=\"btnBY fl\">提交</button>\n");
          out.write("\t\t  </div>\n");
          out.write("\t\t\t</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t</table>\n");
          int evalDoAfterBody = _jspx_th_form_005fform_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_form_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005fform_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005fform_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005fform_005f0.doFinally();
      _005fjspx_005ftagPool_005fform_005fform_0026_005fname_005fmethod_005fid_005fcommandName_005faction.reuse(_jspx_th_form_005fform_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_form_005finput_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_form_005finput_005f0 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_form_005finput_005f0.setPageContext(_jspx_page_context);
    _jspx_th_form_005finput_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(38,7) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f0.setPath("coreMemberInfo.mobile");
    // /WEB-INF/jsp/member/member_detail.jsp(38,7) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f0.setCssClass("short");
    // /WEB-INF/jsp/member/member_detail.jsp(38,7) name = maxlength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f0.setMaxlength("11");
    int[] _jspx_push_body_count_form_005finput_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_form_005finput_005f0 = _jspx_th_form_005finput_005f0.doStartTag();
      if (_jspx_th_form_005finput_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005finput_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005finput_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005finput_005f0.doFinally();
      _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.reuse(_jspx_th_form_005finput_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_form_005finput_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_form_005finput_005f1 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_form_005finput_005f1.setPageContext(_jspx_page_context);
    _jspx_th_form_005finput_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(43,7) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f1.setPath("coreMemberInfo.address");
    // /WEB-INF/jsp/member/member_detail.jsp(43,7) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f1.setCssClass("half");
    // /WEB-INF/jsp/member/member_detail.jsp(43,7) name = maxlength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f1.setMaxlength("50");
    int[] _jspx_push_body_count_form_005finput_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_form_005finput_005f1 = _jspx_th_form_005finput_005f1.doStartTag();
      if (_jspx_th_form_005finput_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005finput_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005finput_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005finput_005f1.doFinally();
      _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.reuse(_jspx_th_form_005finput_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_form_005finput_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_form_005finput_005f2 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_form_005finput_005f2.setPageContext(_jspx_page_context);
    _jspx_th_form_005finput_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(47,7) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f2.setPath("coreMemberInfo.zip");
    // /WEB-INF/jsp/member/member_detail.jsp(47,7) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f2.setCssClass("short");
    // /WEB-INF/jsp/member/member_detail.jsp(47,7) name = maxlength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f2.setMaxlength("6");
    int[] _jspx_push_body_count_form_005finput_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_form_005finput_005f2 = _jspx_th_form_005finput_005f2.doStartTag();
      if (_jspx_th_form_005finput_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005finput_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005finput_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005finput_005f2.doFinally();
      _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.reuse(_jspx_th_form_005finput_005f2);
    }
    return false;
  }

  private boolean _jspx_meth_form_005finput_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_form_005finput_005f3 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_form_005finput_005f3.setPageContext(_jspx_page_context);
    _jspx_th_form_005finput_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(51,7) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f3.setPath("coreMemberInfo.qq");
    // /WEB-INF/jsp/member/member_detail.jsp(51,7) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f3.setCssClass("short");
    // /WEB-INF/jsp/member/member_detail.jsp(51,7) name = maxlength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f3.setMaxlength("20");
    int[] _jspx_push_body_count_form_005finput_005f3 = new int[] { 0 };
    try {
      int _jspx_eval_form_005finput_005f3 = _jspx_th_form_005finput_005f3.doStartTag();
      if (_jspx_th_form_005finput_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005finput_005f3[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005finput_005f3.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005finput_005f3.doFinally();
      _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.reuse(_jspx_th_form_005finput_005f3);
    }
    return false;
  }

  private boolean _jspx_meth_form_005finput_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_form_005finput_005f4 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_form_005finput_005f4.setPageContext(_jspx_page_context);
    _jspx_th_form_005finput_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(55,7) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f4.setPath("coreMemberInfo.msn");
    // /WEB-INF/jsp/member/member_detail.jsp(55,7) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f4.setCssClass("short");
    // /WEB-INF/jsp/member/member_detail.jsp(55,7) name = maxlength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f4.setMaxlength("50");
    int[] _jspx_push_body_count_form_005finput_005f4 = new int[] { 0 };
    try {
      int _jspx_eval_form_005finput_005f4 = _jspx_th_form_005finput_005f4.doStartTag();
      if (_jspx_th_form_005finput_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005finput_005f4[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005finput_005f4.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005finput_005f4.doFinally();
      _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.reuse(_jspx_th_form_005finput_005f4);
    }
    return false;
  }

  private boolean _jspx_meth_form_005finput_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_form_005finput_005f5 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_form_005finput_005f5.setPageContext(_jspx_page_context);
    _jspx_th_form_005finput_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(59,7) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f5.setPath("coreMemberInfo.phone");
    // /WEB-INF/jsp/member/member_detail.jsp(59,7) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f5.setCssClass("short");
    // /WEB-INF/jsp/member/member_detail.jsp(59,7) name = maxlength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f5.setMaxlength("20");
    int[] _jspx_push_body_count_form_005finput_005f5 = new int[] { 0 };
    try {
      int _jspx_eval_form_005finput_005f5 = _jspx_th_form_005finput_005f5.doStartTag();
      if (_jspx_th_form_005finput_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005finput_005f5[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005finput_005f5.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005finput_005f5.doFinally();
      _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.reuse(_jspx_th_form_005finput_005f5);
    }
    return false;
  }

  private boolean _jspx_meth_form_005finput_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_form_005finput_005f6 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_form_005finput_005f6.setPageContext(_jspx_page_context);
    _jspx_th_form_005finput_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(63,7) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f6.setPath("coreMemberInfo.companyName");
    // /WEB-INF/jsp/member/member_detail.jsp(63,7) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f6.setCssClass("half");
    // /WEB-INF/jsp/member/member_detail.jsp(63,7) name = maxlength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f6.setMaxlength("50");
    int[] _jspx_push_body_count_form_005finput_005f6 = new int[] { 0 };
    try {
      int _jspx_eval_form_005finput_005f6 = _jspx_th_form_005finput_005f6.doStartTag();
      if (_jspx_th_form_005finput_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005finput_005f6[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005finput_005f6.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005finput_005f6.doFinally();
      _005fjspx_005ftagPool_005fform_005finput_0026_005fpath_005fmaxlength_005fcssClass_005fnobody.reuse(_jspx_th_form_005finput_005f6);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatDate_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatDate
    org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jspx_th_fmt_005fformatDate_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag) _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag.class);
    _jspx_th_fmt_005fformatDate_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatDate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(72,4) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setValue((java.util.Date) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.coreMemberInfo.registerTime}", java.util.Date.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/member/member_detail.jsp(72,4) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setType("both");
    // /WEB-INF/jsp/member/member_detail.jsp(72,4) name = pattern type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f0.setPattern("yyyy-MM-dd HH:mm");
    int _jspx_eval_fmt_005fformatDate_005f0 = _jspx_th_fmt_005fformatDate_005f0.doStartTag();
    if (_jspx_th_fmt_005fformatDate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fformatDate_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatDate
    org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jspx_th_fmt_005fformatDate_005f1 = (org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag) _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag.class);
    _jspx_th_fmt_005fformatDate_005f1.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fformatDate_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/member/member_detail.jsp(78,3) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f1.setValue((java.util.Date) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${command.userHis.loginTime}", java.util.Date.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/member/member_detail.jsp(78,3) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f1.setType("both");
    // /WEB-INF/jsp/member/member_detail.jsp(78,3) name = pattern type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fformatDate_005f1.setPattern("yyyy-MM-dd HH:mm");
    int _jspx_eval_fmt_005fformatDate_005f1 = _jspx_th_fmt_005fformatDate_005f1.doStartTag();
    if (_jspx_th_fmt_005fformatDate_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fformatDate_0026_005fvalue_005ftype_005fpattern_005fnobody.reuse(_jspx_th_fmt_005fformatDate_005f1);
    return false;
  }
}
