package org.apache.jsp.WEB_002dINF.jsp.page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7\" />\r\n");
      out.write("<head>\r\n");
      out.write("<title>airAD － 智慧广告 智慧生活</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<meta name=\"keywords\" content=\"airAD, 智慧广告, lbs, 广告平台, 手机广告, 移动广告, 移动应用广告, 手机应用广告, 无线广告, 手机软件广告, 手机游戏广告, 精准投放, android广告, android应用广告, iphone广告, iphone应用广告\" />\r\n");
      out.write("<meta name=\"description\" content=\"airAD 是全球首家智慧广告平台，致力于为广告主提供智能化的优质广告服务，为移动应用开发者创造收益；airAD 让广告主、开发者、代理商、终端用户等所有人都从平台中获益。\" />\r\n");
      out.write("<link href=\"style/index.css\" rel=\"stylesheet\" type=\"text/css\" />");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<input id=\"indexP\" value=\"0\" type=\"hidden\"></input>\r\n");
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
      out.write("<div id=\"introBox\" display=\"table\">\r\n");
      out.write("<div id=\"introVip\" class=\"adPic\">\r\n");
      out.write("<div class=\"introConVip\"><div><a href=\"/vip.html\" style=\"float:right;height:160px;width:250px;margin:60px 130px 10px;cursor:pointer\"></a></div></div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"moneyPlan adPic\">\r\n");
      out.write("<div class=\"introConMPlan\"></div>\r\n");
      out.write("<div class=\"introConMPlanDown\"><a href=\"money-plan.html\" style=\"width:85px;height:85px;float:right;margin:0 115px;cursor:pointer\"></a></div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"intro\" class=\"adPic\">\r\n");
      out.write("  <div class=\"introCon\">\r\n");
      out.write("  <div class=\"showflash\">\r\n");
      out.write("  <a href=\"/video/intro.flv\" style=\"display:block;width:261px;height:195px\" id=\"player\"></a> \r\n");
      out.write("</div>\r\n");
      out.write("  <div class=\"index_leftCon\">\r\n");
      out.write("  <div class=\"fl\" style=\"padding:35px 0 0 26px;overflow:hidden\"><div class=\"pic_intro01\"></div></div>\r\n");
      out.write("  <div class=\"clean fl\" style=\"padding-left:28px;color:#c0e5e8;width:400px\">\r\n");
      out.write("  <p style=\"line-height:1.2\">以中国八亿手机用户为广告渠道，用简单、便捷的在线广告制作及生动的广告展示效果，帮助广告主一站式解决推广问题。</p>\r\n");
      out.write("  </div>\r\n");
      out.write("  <div class=\"clean fl\" style=\"margin-top:25px;padding-left:29px\"><a href=\"/member.do?action=register\" class=\"btn_intro fl\"></a></div>\r\n");
      out.write("  </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"main\">\r\n");
      out.write("<div class=\"pic_nav\"></div>\r\n");
      out.write("<div class=\"mainCon\">\r\n");
      out.write("<div style=\"overflow:hidden;margin:10px 0 15px\">\r\n");
      out.write("<div class=\"pic_text\">\r\n");
      out.write("<div class=\"pic_pic01\"></div>\r\n");
      out.write("<div style=\"width:206px\">\r\n");
      out.write("<h2 style=\"margin:10px 0 0\">面向8亿手机用户的广度营销</h2>\r\n");
      out.write("<p>airAD为您创造将广告推送至全国各省市及港澳台地区，手机即时消费带来不可忽视的高消费转换率，8亿手机用户手中的机会！</p>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"pic_text\">\r\n");
      out.write("<div class=\"pic_pic02\"></div>\r\n");
      out.write("<div style=\"width:206px\">\r\n");
      out.write("<h2 style=\"margin:10px 0 0\">智能数据分析系统</h2>\r\n");
      out.write("<p>海量数据分析及市场趋势分析报告，及时调整广告投放策略，成为您广告投放策略的智能助手。</p>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"pic_text\">\r\n");
      out.write("<div class=\"pic_pic03\"></div>\r\n");
      out.write("<div style=\"width:206px\">\r\n");
      out.write("<h2 style=\"margin:10px 0 0\">0起点广告制作平台</h2>\r\n");
      out.write("<p>可提供丰富表现形式的模板式广告制作后台；简单、可靠的广告托管服务；0技术起点，制作属于您自己的广告。</p>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"pic_text\" style=\"padding-right:0\">\r\n");
      out.write("<div class=\"pic_pic04\"></div>\r\n");
      out.write("<div style=\"width:206px\">\r\n");
      out.write("<h2 style=\"margin:10px 0 0\">自主设置投放条件精细化营销</h2>\r\n");
      out.write("<p>自由设定投放时间与区域，帮助您充分掌握用户的特性，精准的将广告投放给目标受众。</p>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"clean\" style=\"margin:5px 0;overflow:hidden\">\r\n");
      out.write("    <div class=\"index_leftCon\">\r\n");
      out.write("    <h1 class=\"tit\">新闻中心</h1>\r\n");
      out.write("    <div>\r\n");
      out.write("    <div class=\"picBox fl\">\r\n");
      out.write("    <a href=\"devregister_page\" class=\"pic_news_20110610_s fl\"></a>\r\n");
      out.write("    </div>\r\n");
      out.write("    <ul class=\"newsList fl\">\r\n");
      out.write("    <li><a href=\"news-detail-08.html\"> airAD \"移动开发者收益保障计划\" 第三季上线。</a> <small>2011-09-13</small></li>\r\n");
      out.write("    <li><a href=\"devregister_page\">做有价值的APP - airAD移动智慧广告全国开发者沙龙。</a> <small>2011-09-07</small></li>\r\n");
      out.write("    <li><a href=\"news-detail-06.html\">持续给力！airAD \"移动开发者收益保障计划\" 第二阶段。</a> <small>2011-06-10</small></li>\r\n");
      out.write("    <li><a href=\"news-detail-05.html\">决定！airAD.com 启动\"移动开发者收益保障计划\"。</a> <small>2011-03-07</small></li>\r\n");
      out.write("    <li><a href=\"news-detail-03.html\">2011 大声说出我的爱。</a> <small>2011-02-07</small></li>\r\n");
      out.write("    </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"index_rightCon\">\r\n");
      out.write("    <div>\r\n");
      out.write("    <h1 class=\"tit fl\">我是开发者</h1>\r\n");
      out.write("    <a href=\"send_req.html\" class=\"fr\">参加开发者收益保障活动&raquo;</a>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"clean rightList\">\r\n");
      out.write("    <ul style=\"*padding:5px 15px 0 15px\">\r\n");
      out.write("    <li>智慧 SDK 支持各种个性化设置。</li>\r\n");
      out.write("    <li>HTML5 富媒体广告。</li>\r\n");
      out.write("    <li>国内最高 CPC 让 eCPM 倍增。</li>\r\n");
      out.write("    <li>详尽的数据报表与分析报告。</li>\r\n");
      out.write("    </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"noticeSdk\">公告：<a href=\"news-update.html\">airAD账户封停公告。</a> <small>2011-09-23</small></div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"con clean\">\r\n");
      out.write("<h1 class=\"tit\" style=\"margin-bottom:5px\">合作伙伴</h1>\r\n");
      out.write("<table border=\"0\"cellpadding=\"0\" cellspacing=\"0\" class=\"link\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"http://www.girlsintechchina.com\" target=\"_blank\" class=\"logo_girl upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.adsidd.com\" target=\"_blank\" class=\"logo_aidd upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.google.com\" target=\"_blank\" class=\"logo_google upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.sina.com.cn\" target=\"_blank\" class=\"logo_sina upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.mobilemonday.com\" target=\"_blank\" class=\"logo_mm upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.500startups.com\" target=\"_blank\" class=\"logo_500 upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.mobile20.com.cn\" target=\"_blank\" class=\"logo_mobile2 upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.gamesdragon.net\" target=\"_blank\" class=\"logo_gamedragon upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.mit.edu\" target=\"_blank\" class=\"logo_mit upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.eoeandroid.com\" target=\"_blank\" class=\"logo_eoe upLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.smg.cn\" target=\"_blank\" class=\"logo_smg downLogo\"></a>\r\n");
      out.write("    <a href=\"http://bbs.goapk.com\" target=\"_blank\" class=\"logo_goapk downLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.nduoa.com\" target=\"_blank\" class=\"logo_nn downLogo\"></a> \r\n");
      out.write("    <a href=\"http://www.maiyadi.com\" target=\"_blank\" class=\"logo_maiyadi downLogo\"></a>   \r\n");
      out.write("    <a href=\"http://www.starandroid.com\" target=\"_blank\" class=\"logo_starandroid downLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.cmd100.com\" target=\"_blank\" class=\"logo_cmd100 downLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.adview.cn\" target=\"_blank\" class=\"logo_adview downLogo\"></a>\r\n");
      out.write("    <a href=\"http://www.androidba.net/\" target=\"_blank\" class=\"logo_androidBa downLogo\"></a>\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"popBg collapsed\"></div>\r\n");
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
      out.write("<script type=\"text/javascript\" src=\"video/flowplayer-3.2.6.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery.scroll.main.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        $(function(){\r\n");
      out.write("          var inPic = $(\"#introBox\")\r\n");
      out.write("             inPic.scrollDown({\r\n");
      out.write("               adPicClass: 'adPic',\r\n");
      out.write("               buttonClass: 'adPicButton',\r\n");
      out.write("               moveHeigh: 242,\r\n");
      out.write("               interval:5000,\r\n");
      out.write("               beforeClick:function(){\r\n");
      out.write("                 $(\".adPicButton\").each(function(){\r\n");
      out.write("                  if ($(this).hasClass(\"now\")) {\r\n");
      out.write("                    if ($(this).attr(\"value\") == \"0\") {\r\n");
      out.write("                     // $(\".showflash\").hide();\r\n");
      out.write("                    }\r\n");
      out.write("                  }});\r\n");
      out.write("                  return true;\r\n");
      out.write("               },\r\n");
      out.write("               aftefClick:function(){\r\n");
      out.write("                $(\".adPicButton\").each(function(){\r\n");
      out.write("                  if ($(this).hasClass(\"now\")) {\r\n");
      out.write("                    if ($(this).attr(\"value\") == \"1\") {\r\n");
      out.write("                      //$(\".showflash\").show();\r\n");
      out.write("                    }\r\n");
      out.write("                  }\r\n");
      out.write("                });\r\n");
      out.write("               }\r\n");
      out.write("             });\r\n");
      out.write("          flowplayer(\"player\", \"/video/flowplayer-3.2.7.swf\",{\r\n");
      out.write("              onFullscreen :function(){\r\n");
      out.write("                  return false;\r\n");
      out.write("              },\r\n");
      out.write("                plugins: {\r\n");
      out.write("                      controls: null\r\n");
      out.write("                  },clip: {\r\n");
      out.write("                    autoPlay: false, \r\n");
      out.write("                    autoBuffering: true,\r\n");
      out.write("                    onResume   : function(clip){inPic.stop();inPic.noStart()},  \r\n");
      out.write("                    onPause   : function(clip){}\r\n");
      out.write("                  }\r\n");
      out.write("          });\r\n");
      out.write("        })\r\n");
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
