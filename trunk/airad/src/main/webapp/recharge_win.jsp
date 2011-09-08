<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<html>
<body>
<h2> <img src="images/ico_popclose.gif" alt="关闭" id="closePop" class="fr" onclick="closepop ()"/>账户充值</h2>
 <form name="formSubmit1" id="formSubmit1" method="post" action="rechargeHis.do?action=rechargePay">
  <table border="0" cellspacing="0" cellpadding="0" class="tabNF">
  <col width="30%" /><col />
  <tr>
    <th>充值金额</th>
    <td>
    <input name="money" id="money" /> 元
    <div id="rMoney1" class="wrBox" style="display: none">
          <div id="rMoney"  class="wr">
          </div>
          </div>
    </td>
    
    </tr>
  <tr>
    <th>&nbsp;</th>
    <td><div class="ctl">
    <input type="button" value="提交"  class="btnBY" onclick="paying();"/> </div></td>

  </tr>
  </table>
  </form>

 <script type="text/javascript">

	function paying() {
		var _money=$('#money');
		var val=_money.val();
		if($.trim(val).lenth==0){
		//	alert('请输入金额');
			document.getElementById("rMoney1").style.display = "block";
	    document.getElementById("rMoney").innerHTML = "请输入金额";
			_money.val("").focus();
	        return false;
			}
		var result = (new RegExp("^[1-9]{1}[0-9]{2,11}[.]{0,1}[0]{0,2}$")).test(val);

		if (!result) {
			  //alert('输入金额格式不正确,最低充值￥100!');
			  document.getElementById("rMoney1").style.display = "block";
	      document.getElementById("rMoney").innerHTML = "输入金额格式不正确,最低充值￥100";
			  _money.val("").focus();
			  return false;
		}
			document.getElementById('formSubmit1').submit();
	}


</script>
</body>
</html>
