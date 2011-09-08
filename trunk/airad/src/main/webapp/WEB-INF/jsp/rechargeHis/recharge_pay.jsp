<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>充值历史记录查看</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function on(html){
		   window.open(html,'_blank');
	}
</script>
<style type="text/css">
<!--
.white_content {
	display: none;
	position: absolute;
	top: 165px;
	left: 50%;
	margin-left: -250px;
	background-color: #FAE6C7;
	z-index: 1002;
	overflow: auto;

}

.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 0);

}
-->
</style>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
	<div id="main">
		<div class="sCon">
		    <h1 class="tit">账户充值</h1>
			<div class="info">向您的帐户充值：<strong class="imp" id="ming"><sup>&yen;</sup><fmt:formatNumber type="number"  value="${f.money}" pattern="###,##0.00"/></strong> </div>
			<h2>请选择您的支付方式</h2>
			<div class="tag">
					<!--<a href="javascript:void(0)" >支付宝</a>-->
					<a href="javascript:void(0)" class="now">中国银联</a>
			</div>
			<div class="tagCBox">
				<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
				       <col width="20%" /><col width="80%" />
					  <tr>
					     <th><img src="images/logo_cu.gif" width="99" height="25" alt="支付宝" /></th>
					     <td style="padding-bottom:20px">中国银联是中国银行卡联合组织，建设和运营银联跨行交易清算系统，为银联卡持卡人提供优质、高效、安全的支付服务。</td>
					  </tr>
					  <tr>
					    <th>&nbsp;</th>
					    <td> 
					    <!--<div class="btnY fl"> <a href="javascript:void(0);" onclick="on('/rechargeHis.do?action=unionpay&ming=${f.money}');"  id=""><span>银联支付</span></a></div> -->
                   <!--<div class="btnBox"> <a href="javascript:void(0);" onclick="on('/rechargeHis.do?action=taobaoPay');" class="btnY fl" id="btn01"><span>同意以下协议并支付</span></a> </div> <small>将会在新页面打开支付窗口</small>
      --><div class="btnBox">
      ${pay}
       <!-- <a href="javascript:void(0)" onclick="on('/rechargeHis.do?action=unionpay&ming=${f.money}');showPopDev();" class="btnY fl" ><span>同意以下协议并支付</span></a> -->
       </div> <small>将会在新页面打开支付窗口</small>
                        </td>
					  </tr>
				</table>
			</div>
			<h3 class="grayTit">在线支付服务协议</h3>
			 <div class="hBox">
			     <ul>
			         <li>一旦用户选择airAD充值服务，在进行充值程序过程中点击"同意以下协议并支付"按钮即表示用户完全接受本协议项下的全部条款。</li>
			         <li>用户必须采用airAD平台所指定的充值方式进行充值。若使用非airAD平台所指定的充值系统进行充值，或以非法的方式进行充值而造成用户权益受损时，不得因此要求airAD平台作任何补偿或赔偿，airAD平台也保留随时终止其账户资格及使用各项充值服务的权利。</li>
			         <li>用户充值一旦成功，充值即确定完成，airAD平台将不提供任何更改、修正服务。用户不得向airAD平台要求退还充值金额。</li>
			         <li>在使用airAD平台充值系统时，用户必须仔细确认自己的账号，若因为用户自身输入账号错误、操作不当等因素造成充错账号等情形而损害自身权益，不得因此要求airAD平台作任何补偿或赔偿。</li>
			         <li>若因用户违反airAD平台用户协议条款或本充值协议，airAD平台因此冻结账号及终止账号使用资格者，不得因此要求airAD平台作任何补偿或赔偿。</li>
			         <li>使用任何方式进行充值后，必须保留充值凭据，以作为今后发生问题核对依据凭证。若因为未保存充值凭据而造成用户受损时，不得因此要求airAD平台作任何补偿或赔偿。</li>
                     <li>airAD广告平台的在线充值平台是使用相关支付平台软件系统实现，请阅读各支付平台相关规则，并确认接受其交易规则。</li>
                     <li>若因充值系统自身故障原因造成用户充值失败，在airAD平台恢复、存有有效数据和用户提供合法有效凭证的情况下，airAD平台将根据用户充值情况作出变动措施。</li>
                     <li>本服务协议适用中华人民共和国法律，如与本协议有关的某一特定事项缺乏明确法律规定，则应参照通用的国际商业惯例和（或）行业惯例。</li>
                     <li>如果本服务协议条款中的部分条款被有管辖权的法院认定为违法，那么这些条款并不影响其他条款的有效性并将应用其他有效条款按最接近双方意图的可能而推定。</li>
			         <li>系统因下列状况无法正常运作，使用户无法使用各项服务时，airAD广告平台对您不负损害赔偿责任，包括但不限于：
			           <ul>
			                 <li>airAD广告平台预定之系统停机维护期间。</li>
			                 <li>电信设备出现故障不能进行数据传输的。</li>
			                 <li>因台风、地震、海啸、洪水、停电、战争、恐怖袭击等不可抗力之因素，造成airAD广告平台系统障碍不能执行业务的。</li>
			                 <li>由于黑客攻击、通信部门技术调整或故障、网站升级、银行方面的问题等原因而造成的服务中断或者延迟。</li>
			             </ul>
			         </li>
			         <li>本协议的签订、解释、变更、执行及争议的解决均适用中华人民共和国法律。</li>
			         <li>因本协议产生的任何争议，双方应协商解决，协商不成的，应提交airAD广告平台所在地有管辖权的人民法院裁决。</li>
                     <li>本协议解释权归南京米田科技有限公司所有。</li>
			     </ul>
			 </div>
			<div class="buttonL r">
			    <a href="javascript:history.go(-1)">取消充值，返回上一页 &raquo;</a></div>
			</div>
		</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>

<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
addCss("rechargeHis");
$('form').submit(function(){
	showPopDev();
	return true;
	
});

});
function showPopDev(){

    $.openPopupLayer({
        name:'popDiv',
        url:'recharge_tip.jsp',
        cache:false,
        width:'500'
      });
}
function closepop(){
	  $.closePopupLayer('popDiv');
}


</script>
</body>
</html>