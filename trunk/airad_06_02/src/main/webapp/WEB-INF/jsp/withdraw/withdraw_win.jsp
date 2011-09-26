<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>

<html>
<body>
<%
     

%>
<h2><img class="fr" id="closePop" alt="关闭" src="images/ico_popclose.gif" onclick="closepop()">资金转换</h2>
 
  <form action="withdraw.do?action=doRecharge" method="post"  name="myForm125" onsubmit="return recMoney();">
   <input type="hidden" value="${incomeBalance}" id="incomeBalance" name="incomeBalance">
  <table cellspacing="0" cellpadding="0" border="0" class="tabNF" >
  <col width="30%"><col>
  <tbody>
  <tr>
    <th>资金转至帐户</th>
    <td>
   <select name="advertistorId" >
   <option value="self" >给自己充值</option>
   <c:forEach items="${form.advertistorList}" var="CoreAgentRelation">
   <option value="${CoreAgentRelation.advertiserNum}" >${CoreAgentRelation.email}</option>
   </c:forEach>
   </select>
   </td>
  </tr>
  <tr>
  <th>转换金额</th>
  <td><input name="money" id="conversionMoney"/>
   <div id="cMoney1" class="wrBox" style="display: none">
          <div id="cMoney"  class="wr">
          </div>
          </div>
  </td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td>
    <div style="padding: 0pt;" class="btnBox"><a class="btnY fl" href="#" onclick="recMoney();"><span>提交</span></a></div></td>
  </tr>
  </tbody></table>
  
  </form>
  
 <script type="text/javascript">
 $(function(){
	    $(document).keydown(function(){
	      if(event.keyCode == 13){
	    	  return recMoney();
	      }
	    });
	});
     function  recMoney(){
    	 var _money=$('#conversionMoney');
    	 var val=_money.val();
    	    if($.trim(val).lenth==0){
    	    
    	      document.getElementById("cMoney1").style.display = "block";
    	      document.getElementById("cMoney").innerHTML = "请输入金额";
    	      _money.val("").focus();
    	          return false;
    	    }
    	   var result = (new RegExp("^[5-9]{1}[0-9]{1,11}[.]{0,1}[0-9]{0,2}$|^[1-9]{1}[0-9]{2,11}[.]{0,1}[0-9]{0,2}$")).test(val);
    	
    	    if(!result){
    	            document.getElementById("cMoney1").style.display = "block";
    	            document.getElementById("cMoney").innerHTML = "请根据提示信息填写正确的金额,最低转换金额为￥50.0";
    	            _money.val("").focus();
    	                return false;
    	            }
    	    var  incomeBalance= document.getElementById('incomeBalance').value;
          if(parseInt(incomeBalance)<parseInt(val)){
        	  document.getElementById("cMoney1").style.display = "block";
            document.getElementById("cMoney").innerHTML = "余额不足";
            _money.val("").focus();
            return false;
           }
            return true;
         }
    
      

 
 </script>
</body>

</html>
