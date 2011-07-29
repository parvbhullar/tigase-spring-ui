<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统一通信</title>

<link rel="shortcut icon"	href="<spring:url value="/static/css/index/favicon.ico" htmlEscape="true" />" 	type="image/x-ico" />
<link rel="stylesheet" 		href="<spring:url value="/static/css/index/common.css" htmlEscape="true" />" 	type="text/css"/>
<link rel="stylesheet" 		href="<spring:url value="/static/css/index/account.css" htmlEscape="true" />" 	type="text/css"/>

<script type="text/javascript">
var root = '';debugMode=false;
//var cookieid = '';
var staticExt = '';
var cookieDomain = '';
var isExecuteJobTime = false;
var jobUrl = '/handler/job.html';
</script>
<script type="text/javascript" src="js/index/max-lib.js"></script>
<script type="text/javascript" src="js/jqueryui/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="reg.js"></script>
<script type="text/javascript" src="js/jquery-validate/jquery.validate.min.js"></script> 

</head>
<body>
<div class="container">
<div class="toolbar">
    <div class="clearfix toolbar-inner">
        <div class="quicklink">
            <script type="text/javascript">
                var TopBarMenu = {
                    create: function (target, menucontents) {
                        if (!document.getElementById(menucontents)) {
                            return;
                        }
                        var contents_wrap = document.getElementById(menucontents);
                        var contents = contents_wrap.innerHTML;
                        target.className += " hover";
                        var dropdownmenu_wrap = document.createElement("div");
                        dropdownmenu_wrap.className = "dropdownmenu-wrap";
                        var dropdownmenu = document.createElement("div");
                        dropdownmenu.className = "dropdownmenu";
                        dropdownmenu.style.width = "auto";
                        var dropdownmenu_inner = document.createElement("div");
                        dropdownmenu_inner.className = "dropdownmenu-inner";
                        dropdownmenu_wrap.appendChild(dropdownmenu);
                        dropdownmenu.appendChild(dropdownmenu_inner);
                        dropdownmenu_inner.innerHTML = contents;
                        if (target.getElementsByTagName("div").length == 0) {
                            target.appendChild(dropdownmenu_wrap);
                        }
                    },
                    clear: function (target) {
                        target.className = target.className.replace("hover", "");
                    }
                }
            </script>
            <ul class="accesslink" id="">
            

            </ul>
            <div class="topbar-hiddencontents" id="">
                
            </div>
            <div class="topbar-hiddencontents" id="">
                
            </div>
        </div>
        <div class="userbar">
        
            <a href="login">立即登录</a>
            <a href="register">注册新帐号</a>
        
        </div>
    </div>
</div>



<div class="header">
    <div class="clearfix header-inner">
        <div class="brand">
            
        </div>
        
    </div>
</div>

<div class="main section-account section-account-register" id="main">
    <div class="clearfix main-inner">
        <div class="content">
            <div class="clearfix content-inner">
                <div class="content-main">
                    <div class="content-main-inner">
        
                        <div class="clearfix registerstep">
                            <ul class="clearfix registerstep-inner">
                                <li class="step1"><span class="active"><span><span>填写会员信息</span></span></span></li>
                            </ul>
                        </div>
            
                            <div class="formgroup registerform">
                                <form method="post" action="" id="signupform">
                            
                            
                            <div class="formrow">
                                <label for=" " class="label">电子邮箱</label>
                                <div class="form-enter">
                                    <input type="text" value="" id="email" name="email" class="text">
                                    <span id="err_email"></span>
                                </div>
                                
                                <div class="form-note">请准确填入您的邮箱. 在忘记密码, 或者您使用邮件通知功能时, 会发送邮件到该邮箱.</div>
                                
                            </div>
                             
                            <div class="formrow">
                                <label for="username" class="label">用户名</label>
                                <div class="form-enter">
                                    <input type="text" value=""  id="username" name="username" class="text">
                                    <span id="err_username"></span>
                                </div>
                                <div class="form-note">用户名由3到14位的中文字符、英文字母、数字组成</div>
                            </div>
                            <div class="formrow">
                                <label for="orgpassword" class="label">密码</label>
                                <div class="form-enter">
                                    <input type="password" id="orgpassword" value="" name="orgpassword" class="text">
                                    <span id="err_orgpassword"></span>
                                </div>
                            </div>
                            <div class="formrow">
                                <label for="resurePassword" class="label">确认密码</label>
                                <div class="form-enter">
                                    <input type="password" id="resurePassword" value="" name="resurePassword" class="text">
                                    <span id="err_resurePassword"></span>
                                </div>
                            </div>
                            
                            <div class="formrow">
                                <label for="orglogname" class="label">组织机构</label>
                                <div class="form-enter">
                                    <input type="text" value=""  id="orglogname" name="orglogname" class="text">
                                    <span id="err_orglogname"></span>
                                </div>
                                <div class="form-note">用户名由3到14位的中文字符、英文字母、数字组成</div>
                            </div>
                            
                            <!--  
                            <div class="formrow formrow-captcha">
                                <label for="validatecode" class="label">验证码</label>
                                <div class="clearfix form-enter">
                                    <input type="text" autocomplete="off" value="" name="validateCodeInput_register" class="text validcode">
                                    <span class="captcha">
                                        <img onclick="javascript:this.src=this.src+'&amp;rnd=' + Math.random();" title="看不清,点击刷新" alt="" src="/handler/vcode.html?type=register&amp;isstyletype=0&amp;id=&amp;r=634372520939303750">
                                    </span>
                                    
                                </div>
                                <div class="form-note">请输入图片中的字符</div>
                            </div>
                            -->
                            
                            <div class="formrow formrow-agreement">
                                <div class="registeragreement">
                                    <h3 class="agreement-title">用户注册条款</h3>
                                    <div class="agreement">
                                        <p><font size="3" color="#000033"><strong>继续注册前请先阅读以下协议</strong></font><font size="4"><font color="#cc0000"><br></font></font></p><font size="4"><font color="#cc0000"><strong></strong></font></font>
<p>欢迎您加入本站点参加交流和讨论，为维护网上公共秩序和社会稳定，请您自觉遵守以下条款：</p>
<p><font color="#000033">一、 不得利用本站危害国家安全、泄露国家秘密，不得侵犯国家社会集体的和公民的合法权益，不得利用本站制作、复制和传播下列信息：</font></p>
<p><font color="#000033">（一）煽动抗拒、破坏宪法和法律、行政法规实施的；</font></p>
<p><font color="#000033">（二）煽动颠覆国家政权，推翻社会主义制度的；</font></p>
<p><font color="#000033">（三）煽动分裂国家、破坏国家统一的；</font></p>
<p><font color="#000033">（四）煽动民族仇恨、民族歧视，破坏民族团结的；</font></p>
<p><font color="#000033">（五）捏造或者歪曲事实，散布谣言，扰乱社会秩序的；</font></p>
<p><font color="#000033">（六）宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖、教唆犯罪的；</font></p>
<p><font color="#000033">（七）公然侮辱他人或者捏造事实诽谤他人的，或者进行其他恶意攻击的；</font></p>
<p><font color="#000033">（八）损害国家机关信誉的；</font></p>
<p><font color="#000033">（九）其他违反宪法和法律行政法规的；</font></p>
<p><font color="#000033">（十）进行商业广告行为的。</font></p>
<p>二、互相尊重，对自己的言论和行为负责。</p>
<p>三、您必需同意不发表带有辱骂，淫秽，粗俗，诽谤，带有仇恨性，恐吓的，不健康的或是任何违反法律的内容。 如果您这样做将导致您的账户将立即和永久性的被封锁。（您的网络服务提供商也会被通知）。 在这个情况下，这个IP地址的所有用户都将被记录。您必须同意系统管理成员们有在任何时间删除，修改，移动或关闭任何内容的权力。 作为一个使用者， 您必须同意您所提供的任何资料都将被存入数据库中，这些资料除非有您的同意，系统管理员们绝不会对第三方公开，然而我们不能保证任何可能导致资料泄露的骇客入侵行为。 本系统使用cookie来储存您的个人信息（在您使用的本地计算机）， 这些cookie不包含任何您曾经输入过的信息，它们只为了方便您能更方便的浏览。 电子邮件地址只用来确认您的注册和发送密码使用。（如果您忘记了密码，将会发送新密码的地址） 点击下面的按钮代表您同意受到这些服务条款的约束。</p>
                                    </div>
                                    
                                </div>
                            </div>
                            <div class="formrow formrow-action">
                                
                                <input type="hidden" name="agree" value="1">
                                
                                <span class="btn-wrap"><span class="btn"><input type="submit" class="button" value="同意以上条款, 提交注册" name="register" id="max_unnamedsubmit_6"></span></span>
                            </div>
                        </form>
                            </div>
                    
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

<div class="footer">
   <div class="clearfix footer-inner">
        <p class="extralink">
            
        </p>
        <p class="copyright">
            CopyRight &copy; 2002-2011 <a href="">统一通信平台</a> 
        </p>
        <p class="extrainfo">
            <a href="" rel="nofollow">宁ICP备01103201号</a>
            增值电信业务经营许可证宁C2-20060001号
        </p>
        
    </div>
</div>
</body>
</html>