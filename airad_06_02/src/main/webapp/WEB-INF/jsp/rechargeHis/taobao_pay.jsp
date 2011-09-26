<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付宝支付</title>
</head>
<body>
<form action="/rechargeHis.do?action=pay" method="post" target="_blank">

    <TABLE cellSpacing=0 cellPadding=0 width=740 border=0>
        <TR>
          <TD class=form-left>付款金额： </TD>
          <TD class=form-star>*</TD>
          <TD class=form-right>
          <input id="alimoney" name="alimoney" type="text" maxlength="16" size="30" onfocus="if(Number(this.value)==0){this.value='';}" value="0.00"/>
            <span>如：112.21</span></TD>
        </TR>
        <TR>
          <TD class=form-left>支付方式：</TD>
          <TD class=form-star></TD>
          <TD class=form-right>
               <table>
                 <tr>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="directPay" checked><img src="images/alipay_1.gif" border="0"/></td>
                 </tr>
                 <tr>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="ICBCB2C"/><img src="images/ICBC_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="CMB"/><img src="images/CMB_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="CCB"/><img src="images/CCB_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="BOCB2C"><img src="images/BOC_OUT.gif" border="0"/></td>
                 </tr>
                 <tr>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="ABC"/><img src="images/ABC_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="COMM"/><img src="images/COMM_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="SPDB"/><img src="images/SPDB_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="GDB"><img src="images/GDB_OUT.gif" border="0"/></td>
                 </tr>
                 <tr>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="CITIC"/><img src="images/CITIC_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="CEBBANK"/><img src="images/CEB_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="CIB"/><img src="images/CIB_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="SDB"><img src="images/SDB_OUT.gif" border="0"/></td>
                 </tr>
                 <tr>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="CMBC"/><img src="images/CMBC_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="HZCBB2C"/><img src="images/HZCB_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="SHBANK"/><img src="images/SHBANK_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="NBBANK "><img src="images/NBBANK_OUT.gif" border="0"/></td>
                 </tr>
                 <tr>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="SPABANK"/><img src="images/SPABANK_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="BJRCB"/><img src="images/BJRCB_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="ICBCBTB"/><img src="images/ENV_ICBC_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="CCBBTB"/><img src="images/ENV_CCB_OUT.gif" border="0"/></td>
                 </tr>
                 <tr>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="SPDBB2B"/><img src="images/ENV_SPDB_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="ABCBTB"/><img src="images/ENV_ABC_OUT.gif" border="0"/></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="fdb101" /><img src="images/FDB_OUT.gif" border="0" /></td>
                   <td><input type="radio" id="pay_bank" name="pay_bank" value="PSBC-DEBIT" /><img src="images/PSBC_OUT.gif" border="0" /></td>
                 </tr>
               </table>
          </td>
        </tr>
         <tr>
          <td class=form-left></td>
          <td class=form-star></td>
          <td class=form-right><input type="image" 
            src="images/button_sure.gif" value=确认订单 
            name="nextstep" ></td>
        </tr>
    </table>
</form>
</body>
</html>