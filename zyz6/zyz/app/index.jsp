<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link  href="yjstyle_index.css"  rel="stylesheet" type="text/css"/>
<title>南通志愿者网站</title>

</head>

<body background="img/bg.jpg">

<div style="width:1002px; margin:auto">
	
<div class="doc" style="background:url(img/bannar5.jpg)">
		
	</div>
	<div>
		    <!--表格开始-->
	<div class="contectindex">
		<div class="newsbg" align="center" >
		    <table width="253" height="211" border="0">
			  <tr>
				<td width="235" height="201">
					<script type="text/javascript">
				        var image = 'img/1.jpg|img/2.jpg|img/3.jpg|img/4.jpg|img/5.jpg|img/6.jpg';
						var link = '';
						var text = '';
		      
			
					 var focus_width=244;
					 var focus_height=202;
					 var text_height=0;
					 var swf_height = focus_height+text_height;
					 document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
					 document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="focus1.swf"><param name="quality" value="high"><param name="bgcolor" value="#F0F0F0">');
					 document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
					 document.write('<param name="FlashVars" value="pics='+image+'&links='+link+'&texts='+text+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">');
					 document.write('</object>');
			        </script>
	        <script type="text/javascript" src="flash.js"></script>
				</td>
			  </tr>
		  </table>
		</div>
		
		<div  class="newsbg2">
		  
<script>
function ck1()
{
	document.getElementById("tbc_01").style.display = "block";
	document.getElementById("tbc_02").style.display = "none";
	document.getElementById("tbc_03").style.display = "none";
	
	document.getElementById("tbc1").style.backgroundImage = "url(img/anv1.jpg)";	
	document.getElementById("tbc2").style.backgroundImage = "url(img/anv2.jpg)";	
	document.getElementById("tbc3").style.backgroundImage = "url(img/anv2.jpg)";	
	
	document.getElementById("tbc1").style.fontWeight = "bold";
	document.getElementById("tbc2").style.fontWeight = "normal";
	document.getElementById("tbc3").style.fontWeight = "normal";

}

function ck2()
{
	document.getElementById("tbc_02").style.display = "block";
	document.getElementById("tbc_01").style.display = "none";
	document.getElementById("tbc_03").style.display = "none";	
	
	document.getElementById("tbc1").style.backgroundImage = "url(img/anv2.jpg)";	
	document.getElementById("tbc2").style.backgroundImage = "url(img/anv1.jpg)";	
	document.getElementById("tbc3").style.backgroundImage = "url(img/anv2.jpg)";	
	
	document.getElementById("tbc1").style.fontWeight = "normal";
	document.getElementById("tbc2").style.fontWeight = "bold";
	document.getElementById("tbc3").style.fontWeight = "normal";			
}
function ck3()
{
	document.getElementById("tbc_03").style.display = "block";
	document.getElementById("tbc_01").style.display = "none";
	document.getElementById("tbc_02").style.display = "none";
	
	document.getElementById("tbc1").style.backgroundImage = "url(img/anv2.jpg)";	
	document.getElementById("tbc2").style.backgroundImage = "url(img/anv2.jpg)";	
	document.getElementById("tbc3").style.backgroundImage = "url(img/anv1.jpg)";
	
	document.getElementById("tbc1").style.fontWeight = "normal";
	document.getElementById("tbc2").style.fontWeight = "normal";
	document.getElementById("tbc3").style.fontWeight = "bold";			
}
</script>		  	  
		  
		  	  <div style="height:34px;" class="index_menu">
			  <a id="tbc1" style="background:url(img/anv1.jpg); font-weight:bold" onmouseover="ck1()">招募公告</a>
			  <a id="tbc2" onmouseover="ck2()">文件政策</a>
			  <a id="tbc3" onmouseover="ck3()">权利义务</a>
			  </div>
		      
		      <div class="w936">

  <div class="ctt">
  
  <div class="dis" id="tbc_01" >
  	<ul class="tbc">
  	<li><a href='news-1.html' target="_blank">团市委“关爱农民工子女志愿服务行动”启动</a>   2010-7-16</li>
  	<li><a href='news-2.html' target="_blank">文明交通志愿者招募通知 </a>  2010-7-16</li>
  	<li><a href='news-3.html' target="_blank">团市委迅速启动文明创建集中整治行动</a>   2010-7-16</li>
  	<li><a href='news-4.html' target="_blank">抗震救灾倡议书</a>   2010-7-16</li>
  	<li><a href='news-5.html' target="_blank">江苏省志愿服务风采展示活动暨南通市“小手拉大手”</a>   2010-7-16</li>
  	<li><a href='news-6.html' target="_blank">团市委组织开展青年文明号、江海志愿者慰问特困户活动</a>   2010-7-16</li>
	</ul>
  </div>
  
  <div class="undis" id="tbc_02" style="display:none">
    <ul class="tbc">
  	<li><a href='news-7.html' target="_blank">关于报送2009年志愿者工作相关材料的通知</a>   2010-7-16</li>
  	<li><a href='news-8.html' target="_blank">关于招募未成年人网络文明教育宣讲活动志愿者的通知 </a>  2010-7-16</li>
  	<li><a href='news-9.html' target="_blank">江苏省保护国土资源青年志愿者行动”试点工作实施意见</a>   2010-7-16</li>
  	<li><a href='news-10.html' target="_blank">关于统筹实施引导高校毕业生到农村基层服务项目工作的通知</a>   2010-7-16</li>
  	<li><a href='news-11.html' target="_blank">关于认真做好2009-2010年度大学生志愿服务西部计划</a>   2010-7-16</li>
  	<li><a href='news-12.html' target="_blank">关于开展“迎国庆讲文明树新风”文明交通活动的通知</a>   2010-7-16</li>
	</ul>
  </div>

  <div class="undis" id="tbc_03"  style="display:none">
    <ul class="tbc">
	<li><a href='mzsm.html' target="_blank">南通志愿服务平台免责声明</a>   2010-7-16</li>
  	<li><a href='agreement.html' target="_blank">江海志愿者协议</a>   2010-7-16</li>
  	<li><a href='bx.html' target="_blank">“江海绿洲”保险方案  </a>  2010-7-16</li>
  	<li><a href='fwzzd.html' target="_blank">江海志愿者服务站服务证制度</a>   2010-7-16</li>
  	<li><a href='gzzz.html' target="_blank">江海志愿者服务站工作人员职责 </a>   2010-7-16</li>
  	<li><a href='kh.html' target="_blank">江海志愿者考核评比制度 </a>   2010-7-16</li>
  	
	</ul>
	<div style=" text-align:right; padding-right:15px;"><a href="news.html">更多>></a></div>
  </div>




 </div>
</div>
		      
		      
		
		</div>
		<div class="loginbg">
		
		
		   <table width="242" height="355" border="0">
			  <tr>
				<td width="40" height="46">&nbsp;</td>
				<td width="192"><strong><font color="#FF0000">登录区</font></strong></td>
			  </tr>
			  <tr>
				<td height="145" colspan="2" align="right">
				 
				 <form name='memberf' action='${ctx}/app/admin/reg/login.action' method="post">
				  <table width="227" height="138" border="0">
			  <tr>
				<td width="77" height="37" align="right">用户名：</td>
				<td width="140" align="left">
			    <input type="text" name="name" size="15" />				</td>
			  </tr>
			  <tr>
				<td height="28" align="right">密&nbsp; &nbsp;码：</td>
				<td  align="left"><input size="15" type="password" name="password" /></td>
			  </tr>
			  <tr>
				<td height="32" colspan="2"  align="center"> 
				<a href="#">
				<img src="img/login2.jpg"  border="0" onclick="doLoginMember()"/></a>&nbsp; &nbsp;<a href="admin/reg/register2.action">
				<img src="img/zhuce.jpg" width="68" height="25" border="0" /></a></td>
			  </tr>
			  <tr>
				<td height="16" colspan="2" align="center"><a href="#"></a></td>
			  </tr>
		  </table>
		  </form>
		  
		  
		 
				</td>
			  </tr>
			  
			  <form name='f' action='${ctx}/manage/j_spring_security_check' method="post">
			  <tr>
				<td height="156" colspan="2" align="center">
				    <table width="239" height="150" border="0">
			  <tr>
				<td width="92" height="37" align="right">用户名：</td>
				<td width="137" colspan="2" align="left">
			    <input name="j_username" class="editbox4" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" size="20" />
			    			</td>
			  </tr>
			  <tr>
				<td height="28" align="right">密&nbsp; &nbsp;码：</td>
				<td colspan="2"  align="left">
				<input class="editbox4" type="password" size="20" name="j_password" />
				</td>
			  </tr>
	          <tr>
				<td height="38" align="right">验证码：</td>
				<td  align="left">
				<input type="text" name="j_captcha" style="width:34px;font-size:10px" onkeydown="javascript:if(event.keyCode==13){doLogin();}" />
			   </td>
			    <td  align="left">
			    	
					<img id="captchaImage" src="/zyz/manage/security/jcaptcha.jpg" width="60px" height="18px" align="middle" onclick="onImageClick(this);" />
			    </td>
	          </tr>
			  <tr>
				<td height="28" colspan="3"  align="center">&nbsp; &nbsp; <a href="#" onclick="this.blur();doLogin();"><img src="img/login2.jpg"  border="0"/></a></td>
			  </tr>
			  <tr>
				<td height="7" colspan="3" align="center"> </td>
			  </tr>
			  </form>
		  </table>
				</td>
			  </tr>
		  </table>
	  </div>
		<div class="adv">
		  <table width="716" height="129" border="0" cellspacing="0" cellpadding="0" background="img/bannar3.jpg" >
		  <tr>
			<td align="left" valign="bottom">
			     <table width="710" height="92" border="0" cellpadding="0" cellspacing="0" > 
				  <tr>
				  <td width="14"></td>
					<td width="83" ><a href="http://www.ntwenming.com/home/index.viso" target="_blank"><img src="img/logo1.jpg" border="0" /></a></td>
					<td><img src="img/logo8.jpg" /></td>
					
					<td width="83" ><a href="http://mzj.nantong.gov.cn/" target="_blank"><img src="img/logo2.jpg" /></a></td>
					<td width="83"><a href="http://www.ntjy.net/" target="_blank"><img src="img/logo3.jpg" /></a></td>
					<td><a href="http://whj.nantong.gov.cn/" target="_blank"><img src="img/logo4.jpg" /></a></td>
					<td><a href="http://www.ntzgh.org/" target="_blank"><img src="img/logo7.jpg" /></a></td>
					<td><a href="http://tyj.nantong.gov.cn/" target="_blank"><img src="img/logo5.jpg" /></a></td>
					<td width="83"><a href="http://www.ntwomen.org/" target="_blank"><img src="img/logo9.jpg" /></a></td>

					<td width="14">&nbsp;</td>
				  </tr>
				 <tr >
			    	 <td width="14" height="47"></td>
					 <td><a href="http://www.ntlgb.gov.cn/" target="_blank"><img src="img/logo6.jpg" /></a></td>
					
					
					<td><a href="http://www.ntredcross.org.cn/" target="_blank"><img src="img/logo11.jpg" /></a></td>
				 	<td><img src="img/logo12.jpg" /></td>
					<td><a href="http://www.ntkx.com/" target="_blank"><img src="img/logo10.jpg" /></a></td>
					
					<td width="82"><img src="img/logo13.jpg" /></td>
					<td width="82"><img src="img/logo14.jpg" /></td>
					<td width="82"><A href="http://10086.cn/" target="_blank"><img src="img/yidong.jpg"  /></A></td>
					<td width="83"><a href="http://www.12580777.com" target="_blank"><img src="img/12580.jpg" /></a></td>
					<td>&nbsp;</td>
				  </tr>
				  <tr >
				    <td width="14" height="4"></td>
				    <td width="83" ></td>
					<td width="83"></td>
					<td width="83"></td>
					<td width="82"></td>
					<td width="82"></td>
					<td width="82"></td>
					<td width="83"></td>
					<td width="92"></td>
					<td width="12" ></td>
				  </tr>
			  </table>
			</td>
		  </tr>
		</table>
	  </div>
		<div class="adv2">
		   <table width="100%" height="20" border="0" cellpadding="0" cellspacing="0">
			   <tr >
				<td width="225" height="30" bgcolor="#e74109" ></td>
			    <td width="286" bgcolor="#e74109" ><font color="#FFFFFF">版权所有：南通市精神文明建设指导委员会</font> </td>
			    <td width="467" bgcolor="#e74109">&nbsp;&nbsp;<font color="#FFFFFF">技术支持：中国移动通信集团江苏有限公司</font></td>
			  </tr>
		  </table>
	  </div>
	</div>
	<!--表格结束-->
	</div>
<script type="text/javascript">
		function doLogin(){
			var flag=valid();
			if(flag){
				document.f.submit();
			}
		}

		function doLoginMember(){
			var flag=validmemberf();
			if(flag){
				document.memberf.submit();
			}
		}

		function validmemberf(){
			if(!(document.memberf.name.value)||document.memberf.name.value.length<1){
				alert("请输入合法的登陆名称！");
				return false;
			}
			if(!(document.memberf.password.value)||document.memberf.password.value.length<1){
				alert("请输入合法的登陆密码！");
				return false;
			}
			return true;
		}
		
		function valid(){
			if(!(document.f.j_username.value)||document.f.j_username.value.length<1){
				alert("请输入合法的登陆名称！");
				return false;
			}
			if(!(document.f.j_password.value)||document.f.j_password.value.length<1){
				alert("请输入合法的登陆密码！");
				return false;
			}
			if(!(document.f.j_captcha.value)||document.f.j_captcha.value.length!=4){
				alert("请输入验证码！");
				return false;
			}
			return true;
		}
		function onImageClick(o){
			o.src = "/ecommerce/manage/security/jcaptcha.jpg?update=" + Math.random();   
		}
		</script>
</body>
</html>
