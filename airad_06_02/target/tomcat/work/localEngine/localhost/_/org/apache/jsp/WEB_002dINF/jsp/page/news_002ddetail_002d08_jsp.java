package org.apache.jsp.WEB_002dINF.jsp.page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class news_002ddetail_002d08_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<title>airAD － 智慧广告 智慧生活</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<meta name=\"keywords\" content=\"airAD, 智慧广告, lbs, 广告平台, 手机广告, 移动广告, 移动应用广告, 手机应用广告, 无线广告, 手机软件广告, 手机游戏广告, 精准投放, android广告, android应用广告, iphone广告, iphone应用广告\" />\r\n");
      out.write("<meta name=\"description\" content=\"airAD 是全球首家智慧广告平台，致力于为广告主提供智能化的优质广告服务，为移动应用开发者创造收益；airAD 让广告主、开发者、代理商、终端用户等所有人都从平台中获益。\" />\r\n");
      out.write("<link href=\"style/index.css\" rel=\"stylesheet\" type=\"text/css\" />");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
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
      out.write("<div id=\"nmPic\">\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"main\">\r\n");
      out.write("<div class=\"mainCon\">\r\n");
      out.write("<div class=\"leftCon\">\r\n");
      out.write("<div class=\"clean\"><a href=\"index.html\">首页</a> &raquo; 新闻中心</div>\r\n");
      out.write("<h2 class=\"c\">一直给力！ airAD \"移动开发者收益保障计划\" 第三季上线！<br />\r\n");
      out.write("<small>2011-09-13</small></h2>\r\n");
      out.write("\r\n");
      out.write("<div class=\"con\">\r\n");
      out.write("<div class=\"c\">\r\n");
      out.write("  <img src=\"images/index/news_20110913.jpg\" alt=\"移动开发者收益保障计划 第三季\" width=\"451\" height=\"251\" /></div>\r\n");
      out.write("<p>据不完全统计，2010年为止中国移动软件开发者接近百万，而开发者平均月收益却不足于100元。2010年底airaAD.com从500份国内免费软件开发者答卷中进行了数据分析。其结果为12.2%的开发者对目前的广告收益表示满意t，48.6%的开发者对收益表示不满意，22.8％的开发者表示不依靠软件收益生存，剩余16.4％开发者则表示打算放弃免费软件开发。</p>\r\n");
      out.write("<p>由于答卷采用网络方式进行，在其中不免听到一些相似的呼声、例如国内嵌入式广告的ECPM过低，填充率不足等问题。面对这样的问题该如何解决？</p>\r\n");
      out.write("<p>再成功举办了3月15日-9月15日的第一季、第二季活动之后，受到了广大开发者热烈的反响，同时也收集到了很多建议，在这里我们先向广大的开发者说声谢谢！同时，为了扶持广大开发者的利益，为了让整体产业走上和谐共盈的良性循环，airAD将把&quot;移动开发者收益保障计划&quot;升级并继续进行下去！</p>\r\n");
      out.write("<p>airAD愿作中国移动开发者们坚强的后盾，让大家可以再次回归激情燃烧的岁月，实现梦想提升价值。</p>\r\n");
      out.write("<p>“在中国！在此刻我们将闪耀！为了我们共同的理想激情！！” － By airADers</p>\r\n");
      out.write("<h2>活动时间</h2>\r\n");
      out.write("<strong  class=\"red\">9月16日 – 12月15日 活动阶段</strong>\r\n");
      out.write("<h2>活动对象与人数：</h2>\r\n");
      out.write("<strong>对象：</strong>手机应用开发者与开发商<br />\r\n");
      out.write("<strong>人数：</strong>500人\r\n");
      out.write("<h2>活动内容：</h2>\r\n");
      out.write("<p>开发者可以在 airAD 平台（www.airad.com）注册账号成为开发者，并下载SDK，同时可通过申请页面申请参加开发者收益保障活动活动。申请后我们将在两个工作日内审核，无论审核是否通过我们都会以电子邮件的方式通知您，请您注意查收邮件。</p>\r\n");
      out.write("<p>9月16日到12月15日为正式活动阶段，活动阶段内每个月为一次计算周期。活动期间正常广告收入将按照全国最高开发者70%的比例分成给开发者，同时参与活动并达到标准的应用最低保障300元每月、500元每月甚至1000元每月的收益（参照活动规则）。</p>\r\n");
      out.write("<h2>活动规则：</h2>\r\n");
      out.write("<p>活动期间内每个月为一次计算周期，凡计算时符合标准的应用都将获得收益保障。</p>\r\n");
      out.write("<ul>\r\n");
      out.write("<li>日均展示达到2000的应用可获得每月300元的收益保障。</li>\r\n");
      out.write("<li>日均展示达到4000的应用可获得每月500元的收益保障。</li>\r\n");
      out.write("<li>日均展示达到10000的应用可获得每月1000元的收益保障。</li>\r\n");
      out.write("</ul>\r\n");
      out.write("<h2>特别提示</h2>\r\n");
      out.write("<ul>\r\n");
      out.write("<li>随着更多的开发者报名参加活动，为了能让更多的开发者有参与的机会，每位开发者（根据提现所填写的银行帐户信息）可以有一个应用（一个数据最高的应用）获得收益保障。</li>\r\n");
      out.write("<li>Android应用需要1.2以上版本的sdk发出的请求才会计入本次活动的数据。</li>\r\n");
      out.write("<li>9月16日之前已经通过活动申请的应用视为自动参加本活动无需再次申请，9月16日之后参加活动的应用需要申请参加活动。</li>\r\n");
      out.write("<li>所有中国地区（包括港澳台）所发出的请求数均计入本次活动的请求数。</li>\r\n");
      out.write("<li>每月收益保障将在每次计算周期结束后5个工作日内发到开发者的收益账户中。</li>\r\n");
      out.write("</ul>\r\n");
      out.write("<h2>FAQ</h2>\r\n");
      out.write("<div class=\"whiteCon\">\r\n");
      out.write("    <h2 class=\"titGreen\">为什么要举办这样的活动？</h2>\r\n");
      out.write("    <div class=\"articleBox\">说为了理想可能没人信。但还是要说，airAD团队是一只年轻的具有社会责任心的团队，半数成员来自海外归国人士，半数则来自与中国本土各大IT上市企业。聚在airAD只有一个理由！那就是我们有着共同的理想！我们愿意并乐意为社会做贡献，为产业注入活力。这～就是我们的全部</div>\r\n");
      out.write("    <h2 class=\"titGreen\">董事决策很艰难么？</h2>\r\n");
      out.write("    <div class=\"articleBox\">\r\n");
      out.write("    是的！必须说这是很艰难的一步，但对airAD来说是具有历史意义的一步。因为这样airAD产生了质的提升，团队都很兴奋，一切努力都值得！\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <h2 class=\"titGreen\">活动还会继续么？</h2>\r\n");
      out.write("    <div class=\"articleBox\">airAD对于开发者的宗旨是让开发者的利益最大化，airAD将争取把活动长期经营下去。中国移动产业还属于萌芽期，远远不是500个人3个月就能改变的事情。\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <h2 class=\"titGreen\">那airAD如何生存？</h2>\r\n");
      out.write("    <div class=\"articleBox\">这个问题问的好。airAD在成立之时就已获得来自国内基金与硅谷基金（包括Facebook早期投资人）的风险投资。可以说airAD.com刚出生就是备注期待的目光下成长起来的。我们坚信产业中存在共赢的良性生态圈，基于这个基础上，想必airAD也将会茁壮成长起来。\r\n");
      out.write("    </div>\r\n");
      out.write("\t\r\n");
      out.write("    <h2 class=\"titGreen\">为什么有最低日均PV2000－10000的限制？</h2>\r\n");
      out.write("    <div class=\"articleBox\">airAD团队本身有着众多的应用 Developer，据经验日均2000PV并不是一个很难的界限，只要用心制作的应用基本上都可以达到这个水平。此外10000PV是个毕业成绩，如果达到这个级别基本上可以脱离扶持计划独立运营。</div>\r\n");
      out.write("    </div>\r\n");
      out.write("<div class=\"c\" style=\"margin:15px auto 30px auto;overflow:hidden;width:299px\"><a href=\"send_req.html\" class=\"btn_apply fl\"></a></div>\r\n");
      out.write("<div class=\"r\"><a href=\"index.html\">返回&raquo;</a></div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"rightCon\">\r\n");
      out.write("<div class=\"con\">\r\n");
      out.write("<h2>相关链接</h2>\r\n");
      out.write("<ul>\r\n");
      out.write("<li><a href=\"index.html\">首页&raquo;</a></li>\r\n");
      out.write("<li><a href=\"about-us.html\">关于我们&raquo;</a></li>\r\n");
      out.write("<li><a href=\"policy.html\">隐私协议&raquo;</a></li>\r\n");
      out.write("<li><a href=\"terms.html\">服务条款&raquo;</a></li>\r\n");
      out.write("<li><a href=\"join-us.html\">加入我们&raquo;</a></li>\r\n");
      out.write("<li><a href=\"help.html\">帮助中心&raquo;</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
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
