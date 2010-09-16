<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/jstl_contexpath.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册第一步</title>
<script src="jquery-min.js" type="text/javascript" >
</script>
<script type="text/javascript" src="reg.js"></script>
<script type="text/javascript">

arrayOrgType0 = new Array();
arrayOrgType1 = new Array();
arrayOrgType2 = new Array();
<c:forEach items="${orgtype}" var="current">
<c:if test="${current.orgTypeParentId==0}">
	arrayOrgType0[arrayOrgType0.length]='<option value='+<c:out value="${current.orgTypeId}"/>+' label=<c:out value="${current.orgTypeName}"/>><c:out value="${current.orgTypeName}"/></option>';
</c:if>
<c:if test="${current.orgTypeParentId==100}">
arrayOrgType1[arrayOrgType1.length]='<option value='+<c:out value="${current.orgTypeId}"/>+' label=<c:out value="${current.orgTypeName}"/>><c:out value="${current.orgTypeName}"/></option>';
</c:if>
<c:if test="${current.orgTypeParentId==200}">
arrayOrgType2[arrayOrgType2.length]='<option value='+<c:out value="${current.orgTypeId}"/>+' label=<c:out value="${current.orgTypeName}"/>><c:out value="${current.orgTypeName}"/></option>';
</c:if>
</c:forEach>

var arrayAreaCity = new Array();
<c:forEach items="${orgarea}" var="current">
	<c:if test="${current.areaParentId==1}">
		arrayAreaCity[arrayAreaCity.length]='<option value='+<c:out value="${current.areaId}"/>+' label=<c:out value="${current.areaName}"/>"><c:out value="${current.areaName}"/></option>';
	</c:if>
</c:forEach>


</script>
</head>
<body>
<table width="70%" cellspacing="1" cellpadding="2" border="1" bgcolor="#ffffff" class="style2">
	        <tbody>
	        
	        <tr>
	          <td width="40%" valign="top">
				  <form action="register2.action" method="POST" name="form">
			      <table width="100%" cellspacing="1" cellpadding="2" border="0" class="style2">
			        <tbody><tr bgcolor="#ffb077">
			          <td class="style2" colspan="4"><strong>根据类别选择注册机构（必选）</strong></td>
			        </tr>
			        <!--  
			        <tr bgcolor="#ffffff">
			          <td width="20%" align="left">
			         <select  name="orgdiv" id="orgdiv">
			         	<option value="" label="请选择">请选择</option>
			          
			         </select></td>
			        </tr>
			        <tr bgcolor="#ffffff">
			          <td width="20%" align="left"><select  name="orgtyp" id="orgtyp">
			          <option value="">请选择</option>
			          </select><script language="javascript">var typarr = [];typarr['0001'] = [{txt:"高校", val:"0002"},{txt:"机关", val:"0003"},{txt:"企业", val:"0004"},{txt:"其他", val:"0005"}];typarr['0002'] = [{txt:"服务队", val:"0006"},{txt:"服务基地", val:"0007"},{txt:"爱心联盟", val:"0008"}];</script></td>
			        </tr>
			        -->
			        
			        <tr bgcolor="#ffffff">
			          <td width="20%" align="left">地区:<select  name="loccit" id="loccitid">
			          <option value="">请选择</option>
			          </select>
			          <select name="locare" id="locareid"><option value="">请选择</option></select>
			          </td>
			        </tr>
			        
			        <tr bgcolor="#ffffff">
			          <td align="left">所属团委:<select name="regorg" id="regorgid"><option value="">请选择</option><option value="1">江苏团省委</option></select><script language="javascript">var orgarr = [];orgarr[''] = [{txt:"江苏团省委", val:"1"}];orgarr['0003'] = [{txt:"江苏省监狱管理局系统团委", val:"745"},{txt:"江苏省劳教局团委", val:"746"},{txt:"中国科学院南京分院团委", val:"747"},{txt:"中国科学院紫金山天文台团委", val:"748"},{txt:"中国科学院南京土壤研究所团委", val:"749"},{txt:"中国科学院南京地质古生物研究所团委", val:"750"},{txt:"中国科学院南京地理与湖泊研究所团委", val:"751"},{txt:"南京中科天文仪器有限公司团委", val:"752"},{txt:"南京水利科学研究院团委", val:"753"},{txt:"国网电力科学研究院", val:"754"},{txt:"国电南京自动化股份有限公司团委", val:"755"},{txt:"南京地质矿产研究所团委", val:"756"},{txt:"环境保护部南京环境科学研究所团委", val:"757"},{txt:"农业部南京农业机械化研究所团委", val:"758"},{txt:"中国电子科技集团公司第十四研究所团委", val:"759"},{txt:"中国电子科技集团公司第二十八研究所团委", val:"760"},{txt:"中国电子科技集团公司第五十五研究所团委", val:"761"},{txt:"中船重工集团公司第七二四研究所团委团委", val:"762"},{txt:"中国航天科工集团八五一一研究所团委", val:"763"},{txt:"南京玻璃纤维研究设计院团委", val:"764"},{txt:"中国林科院林产化学工业研究所团委", val:"765"},{txt:"中科院南京天文光学技术研究所团委", val:"766"},{txt:"省级机关团工委", val:"799"}];orgarr['0004'] = [{txt:"中国石化集团南京化学工业有限公司团委", val:"767"},{txt:"中国石化仪征化纤股份有限公司团委", val:"768"},{txt:"江苏石油勘探局团委", val:"769"},{txt:"中国石油化工股份有限公司江苏石油分公司团委", val:"770"},{txt:"华东石油局团委", val:"771"},{txt:"江苏省电力公司团委", val:"772"},{txt:"中国核工业华兴建设有限公司团委", val:"773"},{txt:"江苏核电有限公司团委", val:"774"},{txt:"中国电信江苏分公司团委", val:"775"},{txt:"中国移动通信集团江苏有限公司团委", val:"776"},{txt:"中国联合通讯有限公司江苏分公司团委", val:"777"},{txt:"中国网络通信集团公司江苏省分公司团委", val:"778"},{txt:"江苏苏美达集团有限公司团委", val:"779"},{txt:"中国铁通集团有限公司江苏分公司团委", val:"780"},{txt:"上海铁路局南京铁路办事处团工委", val:"781"},{txt:"中国冶金南京有限公司团委", val:"782"},{txt:"江苏省国信资产管理集团有限公司团委", val:"783"},{txt:"江苏银行股份有限公司团委", val:"784"},{txt:"江苏省农村信用社联合社团委", val:"785"},{txt:"南京禄口国际机场有限公司团委", val:"786"},{txt:"江苏省丝绸集团有限公司团委", val:"787"},{txt:"中国江苏国际经济技术合作公司团委", val:"788"},{txt:"江苏省海外企业集团有限公司团委", val:"789"},{txt:"江苏省农垦集团有限公司团委", val:"790"},{txt:"江苏省建设集团公司团委", val:"791"},{txt:"江苏省纺织(集团)总公司团委", val:"792"},{txt:"江苏省物资集团总公司团委", val:"793"},{txt:"江苏省设备成套有限公司团委", val:"794"},{txt:"江苏舜天国际集团有限公司团委", val:"795"},{txt:"江苏汇鸿国际集团有限公司团委", val:"796"},{txt:"江苏东恒国际集团有限公司团委", val:"797"},{txt:"江苏开元国际集团有限公司团委", val:"798"},{txt:"江苏弘业国际集团有限公司团委", val:"800"},{txt:"华泰证券股份有限公司团委", val:"801"},{txt:"江苏省盐业集团有限公司团委", val:"802"},{txt:"江苏省滩涂开发投资有限公司团委", val:"803"},{txt:"江苏高科技投资集团有限公司团委", val:"804"},{txt:"江苏省粮食集团有限责任公司团委", val:"805"},{txt:"南京金陵饭店集团有限公司团委", val:"806"},{txt:"江苏钟山宾馆集团团委", val:"807"},{txt:"中国东方航空江苏有限公司团委", val:"808"},{txt:"新长铁路有限责任公司团委", val:"809"},{txt:"江苏省新华书店集团有限公司团委", val:"810"},{txt:"中国工商银行江苏省分行团委", val:"811"},{txt:"中国农业银行江苏省分行团委", val:"812"},{txt:"中国银行股份有限公司江苏省分行团委", val:"813"},{txt:"中国建设银行江苏省分行团委", val:"814"},{txt:"中国农业发展银行江苏省分行团委", val:"815"},{txt:"中国进出口银行南京分行团委", val:"816"},{txt:"交通银行南京分行团委", val:"817"},{txt:"中信银行南京分行团委", val:"818"},{txt:"华夏银行股份有限公司南京分公司团委", val:"819"},{txt:"上海浦东发展银行南京分行团委", val:"820"},{txt:"中国光大银行南京分行团委", val:"821"},{txt:"广东发展银行南京分行团委", val:"822"},{txt:"深圳发展银行南京分行团委", val:"823"},{txt:"天安保险股份有限公司江苏省分公司团委", val:"824"},{txt:"新华人寿保险股份有限公司江苏分公司团委", val:"825"}];orgarr['0002'] = [{txt:"南京大学团委", val:"826"},{txt:"东南大学团委 ", val:"827"},{txt:"南京航空航天大学团委", val:"828"},{txt:"南京理工大学团委　", val:"829"},{txt:"河海大学团委　", val:"830"},{txt:"南京农业大学团委　", val:"831"},{txt:"中国矿业大学团委", val:"832"},{txt:"中国药科大学团委　", val:"833"},{txt:"江南大学团委", val:"834"},{txt:"南京森林公安高等专科学校团委　", val:"835"},{txt:"南京师范大学团委　", val:"836"},{txt:"南京医科大学团委　", val:"837"},{txt:"南京中医药大学团委　", val:"838"},{txt:"南京艺术学院团委　", val:"839"},{txt:"南京体育学院团委　", val:"840"},{txt:"江苏教育学院团委　", val:"841"},{txt:"江苏广播电视大学/江苏城市职业学院团委", val:"842"},{txt:"南京工业大学团委　", val:"843"},{txt:"南京财经大学团委　", val:"844"},{txt:"南京信息工程大学团委　", val:"845"},{txt:"南京邮电大学团委　", val:"846"},{txt:"南京林业大学团委　", val:"847"},{txt:"南京人口管理干部学院团委　", val:"848"},{txt:"南京审计学院团委　", val:"849"},{txt:"南京工程学院团委　", val:"850"},{txt:"江苏警官学院团委　", val:"851"},{txt:"江苏经贸职业技术学院团委　", val:"852"},{txt:"南京工业职业技术学院团委　", val:"853"},{txt:"南京化工职业技术学院团委　", val:"854"},{txt:"南京交通职业技术学院团委　", val:"855"},{txt:"南京特殊教育职业技术学院团委　", val:"856"},{txt:"南京工程高等职业学校团委　", val:"857"},{txt:"南京信息职业技术学院团委　", val:"858"},{txt:"江苏海事职业技术学院团委　", val:"859"},{txt:"南京铁道职业技术学院团委", val:"860"},{txt:"三江学院团委　", val:"861"},{txt:"钟山职业技术学院团委", val:"862"},{txt:"应天职业技术学院团委　", val:"863"},{txt:"正德职业技术学院团委", val:"864"},{txt:"无锡职业技术学院团委", val:"865"},{txt:"徐州师范大学团委", val:"866"},{txt:"徐州医学院团委", val:"867"},{txt:"徐州建筑职业技术学院团委", val:"868"},{txt:"徐州工业职业技术学院团委", val:"869"},{txt:"江苏技术师范学院团委", val:"870"},{txt:"江苏工业学院团委", val:"871"},{txt:"常州信息职业技术学院团委", val:"872"},{txt:"常州纺织服装职业技术学院团委", val:"873"},{txt:"常州工程职业技术学院团委", val:"874"},{txt:"苏州大学团委", val:"875"},{txt:"苏州科技学院团委", val:"876"},{txt:"常熟理工学院团委", val:"877"},{txt:"苏州工艺美术职业技术学院团委", val:"878"},{txt:"苏州经贸职业技术学院团委", val:"879"},{txt:"南通大学团委", val:"880"},{txt:"南通航运职业技术学院团委", val:"881"},{txt:"南通纺织职业技术学院团委", val:"882"},{txt:"淮海工学院团委", val:"883"},{txt:"淮阴师范学院团委", val:"884"},{txt:"淮阴工学院团委", val:"885"},{txt:"江苏食品职业技术学院团委", val:"886"},{txt:"盐城师范学院团委", val:"887"},{txt:"盐城工学院团委", val:"888"},{txt:"扬州大学团委", val:"889"},{txt:"江苏大学团委", val:"890"},{txt:"江苏科技大学团委", val:"891"},{txt:"中国传媒大学南广学院团委　", val:"892"}];orgarr['0006'] = [{txt:"江苏省消防志愿者行动指导委员会", val:"1051"}];orgarr['0001'] = [{txt:"江苏省血液中心", val:"1206"}];</script></td>
			        </tr>
			      </tbody></table>
			      <table width="98%" cellspacing="0" cellpadding="5" border="0">
			        <tbody><tr>
			          <td align="center">
					    <input type="submit" value="下一步">
					  </td>
			        </tr>
			      </tbody></table>
				  </form>
	          </td>
	          <td width="60%" valign="top">
				  <form action="register2.action" method="POST" name="form1">
			      <table width="60%" cellspacing="1" cellpadding="2" border="0" class="style2">
			        <tbody><tr bgcolor="#ffb077">
			          <td class="style2"><strong>请选择注册机构（必选）</strong></td>
			        </tr>
			        
			        <tr bgcolor="#ffffff">
			          <td><select style="width: 400px; height: 280px;" size="40" name="regorg"><option value="1" label="江苏团省委">江苏团省委</option><option value="745" label="　江苏省监狱管理局系统团委">　江苏省监狱管理局系统团委</option><option value="746" label="　江苏省劳教局团委">　江苏省劳教局团委</option><option value="747" label="　中国科学院南京分院团委">　中国科学院南京分院团委</option><option value="748" label="　中国科学院紫金山天文台团委">　中国科学院紫金山天文台团委</option><option value="749" label="　中国科学院南京土壤研究所团委">　中国科学院南京土壤研究所团委</option><option value="750" label="　中国科学院南京地质古生物研究所团委">　中国科学院南京地质古生物研究所团委</option><option value="751" label="　中国科学院南京地理与湖泊研究所团委">　中国科学院南京地理与湖泊研究所团委</option><option value="752" label="　南京中科天文仪器有限公司团委">　南京中科天文仪器有限公司团委</option><option value="753" label="　南京水利科学研究院团委">　南京水利科学研究院团委</option><option value="754" label="　国网电力科学研究院">　国网电力科学研究院</option><option value="755" label="　国电南京自动化股份有限公司团委">　国电南京自动化股份有限公司团委</option><option value="756" label="　南京地质矿产研究所团委">　南京地质矿产研究所团委</option><option value="757" label="　环境保护部南京环境科学研究所团委">　环境保护部南京环境科学研究所团委</option><option value="758" label="　农业部南京农业机械化研究所团委">　农业部南京农业机械化研究所团委</option><option value="759" label="　中国电子科技集团公司第十四研究所团委">　中国电子科技集团公司第十四研究所团委</option><option value="760" label="　中国电子科技集团公司第二十八研究所团委">　中国电子科技集团公司第二十八研究所团委</option><option value="761" label="　中国电子科技集团公司第五十五研究所团委">　中国电子科技集团公司第五十五研究所团委</option><option value="762" label="　中船重工集团公司第七二四研究所团委团委">　中船重工集团公司第七二四研究所团委团委</option><option value="763" label="　中国航天科工集团八五一一研究所团委">　中国航天科工集团八五一一研究所团委</option><option value="764" label="　南京玻璃纤维研究设计院团委">　南京玻璃纤维研究设计院团委</option><option value="765" label="　中国林科院林产化学工业研究所团委">　中国林科院林产化学工业研究所团委</option><option value="766" label="　中科院南京天文光学技术研究所团委">　中科院南京天文光学技术研究所团委</option><option value="767" label="　中国石化集团南京化学工业有限公司团委">　中国石化集团南京化学工业有限公司团委</option><option value="768" label="　中国石化仪征化纤股份有限公司团委">　中国石化仪征化纤股份有限公司团委</option><option value="769" label="　江苏石油勘探局团委">　江苏石油勘探局团委</option><option value="770" label="　中国石油化工股份有限公司江苏石油分公司团委">　中国石油化工股份有限公司江苏石油分公司团委</option><option value="771" label="　华东石油局团委">　华东石油局团委</option><option value="772" label="　江苏省电力公司团委">　江苏省电力公司团委</option><option value="773" label="　中国核工业华兴建设有限公司团委">　中国核工业华兴建设有限公司团委</option><option value="774" label="　江苏核电有限公司团委">　江苏核电有限公司团委</option><option value="775" label="　中国电信江苏分公司团委">　中国电信江苏分公司团委</option><option value="776" label="　中国移动通信集团江苏有限公司团委">　中国移动通信集团江苏有限公司团委</option><option value="777" label="　中国联合通讯有限公司江苏分公司团委">　中国联合通讯有限公司江苏分公司团委</option><option value="778" label="　中国网络通信集团公司江苏省分公司团委">　中国网络通信集团公司江苏省分公司团委</option><option value="779" label="　江苏苏美达集团有限公司团委">　江苏苏美达集团有限公司团委</option><option value="780" label="　中国铁通集团有限公司江苏分公司团委">　中国铁通集团有限公司江苏分公司团委</option><option value="781" label="　上海铁路局南京铁路办事处团工委">　上海铁路局南京铁路办事处团工委</option><option value="782" label="　中国冶金南京有限公司团委">　中国冶金南京有限公司团委</option><option value="783" label="　江苏省国信资产管理集团有限公司团委">　江苏省国信资产管理集团有限公司团委</option><option value="784" label="　江苏银行股份有限公司团委">　江苏银行股份有限公司团委</option><option value="785" label="　江苏省农村信用社联合社团委">　江苏省农村信用社联合社团委</option><option value="786" label="　南京禄口国际机场有限公司团委">　南京禄口国际机场有限公司团委</option><option value="787" label="　江苏省丝绸集团有限公司团委">　江苏省丝绸集团有限公司团委</option><option value="788" label="　中国江苏国际经济技术合作公司团委">　中国江苏国际经济技术合作公司团委</option><option value="789" label="　江苏省海外企业集团有限公司团委">　江苏省海外企业集团有限公司团委</option><option value="790" label="　江苏省农垦集团有限公司团委">　江苏省农垦集团有限公司团委</option><option value="791" label="　江苏省建设集团公司团委">　江苏省建设集团公司团委</option><option value="792" label="　江苏省纺织(集团)总公司团委">　江苏省纺织(集团)总公司团委</option><option value="793" label="　江苏省物资集团总公司团委">　江苏省物资集团总公司团委</option><option value="794" label="　江苏省设备成套有限公司团委">　江苏省设备成套有限公司团委</option><option value="795" label="　江苏舜天国际集团有限公司团委">　江苏舜天国际集团有限公司团委</option><option value="796" label="　江苏汇鸿国际集团有限公司团委">　江苏汇鸿国际集团有限公司团委</option><option value="797" label="　江苏东恒国际集团有限公司团委">　江苏东恒国际集团有限公司团委</option><option value="798" label="　江苏开元国际集团有限公司团委">　江苏开元国际集团有限公司团委</option><option value="799" label="　省级机关团工委">　省级机关团工委</option><option value="800" label="　江苏弘业国际集团有限公司团委">　江苏弘业国际集团有限公司团委</option><option value="801" label="　华泰证券股份有限公司团委">　华泰证券股份有限公司团委</option><option value="802" label="　江苏省盐业集团有限公司团委">　江苏省盐业集团有限公司团委</option><option value="803" label="　江苏省滩涂开发投资有限公司团委">　江苏省滩涂开发投资有限公司团委</option><option value="804" label="　江苏高科技投资集团有限公司团委">　江苏高科技投资集团有限公司团委</option><option value="805" label="　江苏省粮食集团有限责任公司团委">　江苏省粮食集团有限责任公司团委</option><option value="806" label="　南京金陵饭店集团有限公司团委">　南京金陵饭店集团有限公司团委</option><option value="807" label="　江苏钟山宾馆集团团委">　江苏钟山宾馆集团团委</option><option value="808" label="　中国东方航空江苏有限公司团委">　中国东方航空江苏有限公司团委</option><option value="809" label="　新长铁路有限责任公司团委">　新长铁路有限责任公司团委</option><option value="810" label="　江苏省新华书店集团有限公司团委">　江苏省新华书店集团有限公司团委</option><option value="811" label="　中国工商银行江苏省分行团委">　中国工商银行江苏省分行团委</option><option value="812" label="　中国农业银行江苏省分行团委">　中国农业银行江苏省分行团委</option><option value="813" label="　中国银行股份有限公司江苏省分行团委">　中国银行股份有限公司江苏省分行团委</option><option value="814" label="　中国建设银行江苏省分行团委">　中国建设银行江苏省分行团委</option><option value="815" label="　中国农业发展银行江苏省分行团委">　中国农业发展银行江苏省分行团委</option><option value="816" label="　中国进出口银行南京分行团委">　中国进出口银行南京分行团委</option><option value="817" label="　交通银行南京分行团委">　交通银行南京分行团委</option><option value="818" label="　中信银行南京分行团委">　中信银行南京分行团委</option><option value="819" label="　华夏银行股份有限公司南京分公司团委">　华夏银行股份有限公司南京分公司团委</option><option value="820" label="　上海浦东发展银行南京分行团委">　上海浦东发展银行南京分行团委</option><option value="821" label="　中国光大银行南京分行团委">　中国光大银行南京分行团委</option><option value="822" label="　广东发展银行南京分行团委">　广东发展银行南京分行团委</option><option value="823" label="　深圳发展银行南京分行团委">　深圳发展银行南京分行团委</option><option value="824" label="　天安保险股份有限公司江苏省分公司团委">　天安保险股份有限公司江苏省分公司团委</option><option value="825" label="　新华人寿保险股份有限公司江苏分公司团委">　新华人寿保险股份有限公司江苏分公司团委</option><option value="826" label="　南京大学团委">　南京大学团委</option><option value="827" label="　东南大学团委 ">　东南大学团委 </option><option value="828" label="　南京航空航天大学团委">　南京航空航天大学团委</option><option value="829" label="　南京理工大学团委　">　南京理工大学团委　</option><option value="830" label="　河海大学团委　">　河海大学团委　</option><option value="831" label="　南京农业大学团委　">　南京农业大学团委　</option><option value="832" label="　中国矿业大学团委">　中国矿业大学团委</option><option value="833" label="　中国药科大学团委　">　中国药科大学团委　</option><option value="834" label="　江南大学团委">　江南大学团委</option><option value="835" label="　南京森林公安高等专科学校团委　">　南京森林公安高等专科学校团委　</option><option value="836" label="　南京师范大学团委　">　南京师范大学团委　</option><option value="837" label="　南京医科大学团委　">　南京医科大学团委　</option><option value="838" label="　南京中医药大学团委　">　南京中医药大学团委　</option><option value="839" label="　南京艺术学院团委　">　南京艺术学院团委　</option><option value="840" label="　南京体育学院团委　">　南京体育学院团委　</option><option value="841" label="　江苏教育学院团委　">　江苏教育学院团委　</option><option value="842" label="　江苏广播电视大学/江苏城市职业学院团委">　江苏广播电视大学/江苏城市职业学院团委</option><option value="843" label="　南京工业大学团委　">　南京工业大学团委　</option><option value="844" label="　南京财经大学团委　">　南京财经大学团委　</option><option value="845" label="　南京信息工程大学团委　">　南京信息工程大学团委　</option><option value="846" label="　南京邮电大学团委　">　南京邮电大学团委　</option><option value="847" label="　南京林业大学团委　">　南京林业大学团委　</option><option value="848" label="　南京人口管理干部学院团委　">　南京人口管理干部学院团委　</option><option value="849" label="　南京审计学院团委　">　南京审计学院团委　</option><option value="850" label="　南京工程学院团委　">　南京工程学院团委　</option><option value="851" label="　江苏警官学院团委　">　江苏警官学院团委　</option><option value="852" label="　江苏经贸职业技术学院团委　">　江苏经贸职业技术学院团委　</option><option value="853" label="　南京工业职业技术学院团委　">　南京工业职业技术学院团委　</option><option value="854" label="　南京化工职业技术学院团委　">　南京化工职业技术学院团委　</option><option value="855" label="　南京交通职业技术学院团委　">　南京交通职业技术学院团委　</option><option value="856" label="　南京特殊教育职业技术学院团委　">　南京特殊教育职业技术学院团委　</option><option value="857" label="　南京工程高等职业学校团委　">　南京工程高等职业学校团委　</option><option value="858" label="　南京信息职业技术学院团委　">　南京信息职业技术学院团委　</option><option value="859" label="　江苏海事职业技术学院团委　">　江苏海事职业技术学院团委　</option><option value="860" label="　南京铁道职业技术学院团委">　南京铁道职业技术学院团委</option><option value="861" label="　三江学院团委　">　三江学院团委　</option><option value="862" label="　钟山职业技术学院团委">　钟山职业技术学院团委</option><option value="863" label="　应天职业技术学院团委　">　应天职业技术学院团委　</option><option value="864" label="　正德职业技术学院团委">　正德职业技术学院团委</option><option value="865" label="　无锡职业技术学院团委">　无锡职业技术学院团委</option><option value="866" label="　徐州师范大学团委">　徐州师范大学团委</option><option value="867" label="　徐州医学院团委">　徐州医学院团委</option><option value="868" label="　徐州建筑职业技术学院团委">　徐州建筑职业技术学院团委</option><option value="869" label="　徐州工业职业技术学院团委">　徐州工业职业技术学院团委</option><option value="870" label="　江苏技术师范学院团委">　江苏技术师范学院团委</option><option value="871" label="　江苏工业学院团委">　江苏工业学院团委</option><option value="872" label="　常州信息职业技术学院团委">　常州信息职业技术学院团委</option><option value="873" label="　常州纺织服装职业技术学院团委">　常州纺织服装职业技术学院团委</option><option value="874" label="　常州工程职业技术学院团委">　常州工程职业技术学院团委</option><option value="875" label="　苏州大学团委">　苏州大学团委</option><option value="876" label="　苏州科技学院团委">　苏州科技学院团委</option><option value="877" label="　常熟理工学院团委">　常熟理工学院团委</option><option value="878" label="　苏州工艺美术职业技术学院团委">　苏州工艺美术职业技术学院团委</option><option value="879" label="　苏州经贸职业技术学院团委">　苏州经贸职业技术学院团委</option><option value="880" label="　南通大学团委">　南通大学团委</option><option value="881" label="　南通航运职业技术学院团委">　南通航运职业技术学院团委</option><option value="882" label="　南通纺织职业技术学院团委">　南通纺织职业技术学院团委</option><option value="883" label="　淮海工学院团委">　淮海工学院团委</option><option value="884" label="　淮阴师范学院团委">　淮阴师范学院团委</option><option value="885" label="　淮阴工学院团委">　淮阴工学院团委</option><option value="886" label="　江苏食品职业技术学院团委">　江苏食品职业技术学院团委</option><option value="887" label="　盐城师范学院团委">　盐城师范学院团委</option><option value="888" label="　盐城工学院团委">　盐城工学院团委</option><option value="889" label="　扬州大学团委">　扬州大学团委</option><option value="890" label="　江苏大学团委">　江苏大学团委</option><option value="891" label="　江苏科技大学团委">　江苏科技大学团委</option><option value="892" label="　中国传媒大学南广学院团委　">　中国传媒大学南广学院团委　</option><option value="1051" label="　江苏省消防志愿者行动指导委员会">　江苏省消防志愿者行动指导委员会</option><option value="1206" label="　江苏省血液中心">　江苏省血液中心</option></select></td>
			        </tr>
			      </tbody></table>
			      <table width="98%" cellspacing="0" cellpadding="5" border="0">
			        <tbody><tr>
			          <td align="center">
					    <input type="submit" value="下一步" id="nextstep">
					  </td>
			        </tr>
			      </tbody></table>
				  </form>
	          </td>
	        </tr>
	      </tbody></table>
</body>
</html>