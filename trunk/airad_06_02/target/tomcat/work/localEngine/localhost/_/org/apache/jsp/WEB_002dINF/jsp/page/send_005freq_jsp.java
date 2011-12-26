package org.apache.jsp.WEB_002dINF.jsp.page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class send_005freq_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/WEB-INF/jsp/page/header_meta.jsp");
    _jspx_dependants.add("/WEB-INF/jsp/page/page_header.jsp");
    _jspx_dependants.add("/WEB-INF/jsp/page/page_footer.jsp");
  }

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

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<title>airAD － 智慧广告 智慧生活</title>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<meta name=\"keywords\" content=\"airAD, 智慧广告, lbs, 广告平台, 手机广告, 移动广告, 移动应用广告, 手机应用广告, 无线广告, 手机软件广告, 手机游戏广告, 精准投放, android广告, android应用广告, iphone广告, iphone应用广告\" />\r\n");
      out.write("<meta name=\"description\" content=\"airAD 是全球首家智慧广告平台，致力于为广告主提供智能化的优质广告服务，为移动应用开发者创造收益；airAD 让广告主、开发者、代理商、终端用户等所有人都从平台中获益。\" />\r\n");
      out.write("<link href=\"style/index.css\" rel=\"stylesheet\" type=\"text/css\" />");
      out.write("<style type=\"text/css\">\n");
      out.write("iframe{height:1100px;width:100%;border:none;}\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body style=\"overflow:auto;\">\n");
      out.write("<div id=\"header\">\r\n");
      out.write("<div class=\"headCon\">\r\n");
      out.write("<a href=\"http://www.airAD.com\" class=\"btn_logo fl\"></a></div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"nav\">\r\n");
      out.write("<div>\r\n");
      out.write("<span class=\"fr\">\r\n");
      out.write("<span id=\"_r1\" style=\"color:#192D2E\"><a href=\"/member.do?action=logon\"  >登录</a></span>\r\n");
      out.write("<span>|</span>\r\n");
      out.write("<span id=\"_r2\"><a href=\"/member.do?action=register\"  >注册&raquo;</a></span>\r\n");
      out.write("</span>\r\n");
      out.write("<a href=\"index.html\">首页</a> | <a href=\"about-us.html\">关于我们</a> | <a href=\"join-us.html\">加入我们</a> | <a href=\"help.html\">帮助中心</a>\r\n");
      out.write("</div>\r\n");
      out.write("</div>");
      out.write("<div id=\"main\" >\n");
      out.write("<div class=\"mainCon\">\n");
      out.write("<div class=\"leftCon\">\n");
      out.write("<div class=\"con\" >\n");
      out.write("<iframe title=\"&egrave;?frac34;&aring;&deg;?aring;&sup1;&iquest;&aring;&aring;&ordm;?ccedil;?uml;&ccedil;?sup3;&egrave;&macr;&middot;\" frameborder=\"0\" src=\"http://panyi.wufoo.com/embed/z7x3k7/\" onload=\"SetCwinHeight(this);\">\n");
      out.write("<a href=\"http://mitian.wufoo.com/forms/m7x3w7/\" title=\"airAD开发者申请\">Fill out my form!</a>\n");
      out.write("</iframe>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"rightCon\" >\n");
      out.write(" <div class=\"con\" style=\"height:880px;\">\n");
      out.write("<h2>相关链接</h2>\n");
      out.write("<ul>\n");
      out.write("<li><a href=\"index.html\">首页&raquo;</a></li>\n");
      out.write("<li><a href=\"about-us.html\">关于我们&raquo;</a></li>\n");
      out.write("<li><a href=\"policy.html\">隐私协议&raquo;</a></li>\n");
      out.write("<li><a href=\"terms.html\">服务条款&raquo;</a></li>\n");
      out.write("<li><a href=\"join-us.html\">加入我们&raquo;</a></li>\n");
      out.write("<li><a href=\"help.html\">帮助中心&raquo;</a></li>\n");
      out.write("</ul>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div id=\"footer\">\r\n");
      out.write("<div><a href=\"index.html\">首页</a> | <a href=\"about-us.html\">关于我们</a> | <a href=\"policy.html\">隐私协议</a> | <a href=\"terms.html\">服务条款</a> | <a href=\"join-us.html\">加入我们</a> | <a href=\"mailto:contact@airad.com\">联系我们</a> | <a href=\"help.html\">帮助中心</a> | <a href=\"/send_req.html\" target=\"_blank\">参加开发者收益保障活动</a>\r\n");
      out.write("</div>\r\n");
      out.write("&copy;2011 米田科技有限公司 版权所有\r\n");
      out.write("<div style=\"display:none\"><script language=\"javascript\" type=\"text/javascript\" src=\"/js/5291024.js\"></script></div>\r\n");
      out.write("<br />\r\n");
      out.write("备案证书号: <a href=\"http://www.miibeian.gov.cn\" target=\"_blank\">苏ICP备11020158号 </a> \r\n");
      out.write("增值电信业务经营许可证: <a href=\"http://www.jsca.gov.cn\" target=\"_blank\">苏B2-20110139</a>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"chat\">\r\n");
      out.write("<img src=\"/images/btn_chat.gif\" width=\"174px\" height=\"23px\" style=\"cursor:pointer\" onclick=\"javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=800008968&eid=2188z8p8p8p8p8z808x8z&o=www.airAD.com&q=7', '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');\" border=\"0\" alt=\"在线客服 800008968\" />\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/airad.jquery.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        $.rightBottom(\"#chat\",174,23);\r\n");
      out.write("        $(window).scroll(function(){\r\n");
      out.write("            $.rightBottom(\"#chat\",174,23);\r\n");
      out.write("        }); \r\n");
      out.write("        $(window).resize(function() {\r\n");
      out.write("           $.rightBottom(\"#chat\",174,23);\r\n");
      out.write("        });\r\n");
      out.write("        $(document).ready(function(){\r\n");
      out.write("        \t $.post(\"member.do?action=getemail\",\r\n");
      out.write("        \t\t\t    function (data, textStatus){\r\n");
      out.write("        \t\t\t       if (data.length > 1&&data.length<50) {\r\n");
      out.write("            \t\t\t       $('#_r1').html('<a href=\"/personal.do?action=index\">Hi,'+ data+'</a>');\r\n");
      out.write("            \t\t\t       $('#_r2').html('<a href=\"member.do?action=logout\">[退出]</a>');\r\n");
      out.write("        \t\t\t       }\r\n");
      out.write("        \t\t\t    }, \"text\");\r\n");
      out.write("        \t\r\n");
      out.write("        });\r\n");
      out.write("    </script>\r\n");
      out.write("<script>\n");
      out.write("function SetCwinHeight(obj)\n");
      out.write("{\n");
      out.write("  var cwin=obj;\n");
      out.write("  if (document.getElementById)\n");
      out.write("  {\n");
      out.write("    if (cwin && !window.opera)\n");
      out.write("    {\n");
      out.write("      if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight)\n");
      out.write("        cwin.height = cwin.contentDocument.body.offsetHeight + 20; //FF NS\n");
      out.write("      else if(cwin.Document && cwin.Document.body.scrollHeight)\n");
      out.write("        cwin.height = cwin.Document.body.scrollHeight + 10;//IE\n");
      out.write("    }\n");
      out.write("    else\n");
      out.write("    {\n");
      out.write("        if(cwin.contentWindow.document && cwin.contentWindow.document.body.scrollHeight)\n");
      out.write("            cwin.height = cwin.contentWindow.document.body.scrollHeight;//Opera\n");
      out.write("    }\n");
      out.write("  }\n");
      out.write("}\n");
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
}
