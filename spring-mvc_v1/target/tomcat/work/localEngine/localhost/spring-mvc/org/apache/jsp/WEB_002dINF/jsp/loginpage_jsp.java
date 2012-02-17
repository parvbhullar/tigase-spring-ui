package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loginpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write(" \r\n");
      out.write("<h1>\r\n");
      out.write(" \r\n");
      out.write("Login</h1><div id=\"login-error\">\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</div><form action=\"../../j_spring_security_check\" method=\"post\" >\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("<p>\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("<label for=\"j_username\">Username</label>\r\n");
      out.write("<input id=\"j_username\" name=\"j_username\" type=\"text\" />\r\n");
      out.write("</p><p>\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("<label for=\"j_password\">Password</label>\r\n");
      out.write("<input id=\"j_password\" name=\"j_password\" type=\"password\" />\r\n");
      out.write("</p><input  type=\"submit\" value=\"Login\"/>        \r\n");
      out.write(" \r\n");
      out.write("</form>\r\n");
      out.write("<ul>\r\n");
      out.write("\t<h4>账号及登录对应页面</h4>\r\n");
      out.write("\t<li>john/admin <a href=\"../main/admin\"> 管理员admin页面 </a> </li>\r\n");
      out.write("\t<li>jane/user  <a href=\"../main/common\">普通用户页面    </a></li>\r\n");
      out.write("</ul>\r\n");
      out.write(" <ul>\r\n");
      out.write(" \t<h4>security 规则</h4>\r\n");
      out.write(" \t<li>以普通用户登录,访问<a href=\"../main/admin\">管理员admin页面 </a>的话会被拒绝</li>\r\n");
      out.write(" \t<li>不登录访问<a href=\"../main/admin\">管理员admin页面 </a>,<a href=\"../main/common\">普通用户页面    </a>都被跳转到登录页面</li>\r\n");
      out.write(" </ul>\r\n");
      out.write("</body>\r\n");
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
}
