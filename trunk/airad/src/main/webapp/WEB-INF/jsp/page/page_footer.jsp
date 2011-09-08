<%@ page contentType="text/html; charset=UTF-8"%><%@ page trimDirectiveWhitespaces="true" %>
 <div id="footer">
<div><a href="index.html">首页</a> | <a href="about-us.html">关于我们</a> | <a href="policy.html">隐私协议</a> | <a href="terms.html">服务条款</a> | <a href="join-us.html">加入我们</a> | <a href="mailto:contact@airad.com">联系我们</a> | <a href="help.html">帮助中心</a> | <a href="/send_req.html" target="_blank">参加开发者收益保障活动</a>
</div>
&copy;2011 米田科技有限公司 版权所有
<div style="display:none"><script language="javascript" type="text/javascript" src="/js/5291024.js"></script></div>
<br />
备案证书号: <a href="http://www.miibeian.gov.cn" target="_blank">苏ICP备11020158号 </a> 
增值电信业务经营许可证: <a href="http://www.jsca.gov.cn" target="_blank">苏B2-20110139</a>
</div>
<div id="chat">
<img src="/images/btn_chat.gif" width="174px" height="23px" style="cursor:pointer" onclick="javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=800008968&eid=2188z8p8p8p8p8z808x8z&o=www.airAD.com&q=7', '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');" border="0" alt="在线客服 800008968" />
</div>
<script tyep="text/javascript" src="/js/jquery.js"></script>
<script tyep="text/javascript" src="/js/airad.jquery.js"></script>
    <script type="text/javascript">
        $.rightBottom("#chat",174,23);
        $(window).scroll(function(){
            $.rightBottom("#chat",174,23);
        }); 
        $(window).resize(function() {
           $.rightBottom("#chat",174,23);
        });
        $(document).ready(function(){
        	 $.post("member.do?action=getemail",
        			    function (data, textStatus){
        			       if (data.length > 1&&data.length<50) {
            			       $('#_r1').html('<a href="/personal.do?action=index">Hi,'+ data+'</a>');
            			       $('#_r2').html('<a href="member.do?action=logout">[退出]</a>');
        			       }
        			    }, "text");
        	
        });
    </script>
