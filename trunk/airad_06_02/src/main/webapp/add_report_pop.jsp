<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2> <img src="images/ico_popclose.gif" alt="关闭" id="closePop" class="fr" onclick="closepop()"/>保存报表</h2>
<div class="popCon" id="eml">
<table border="0" cellspacing="0" cellpadding="0" class="tabNF" style="margin:0">
        <col width="30%" />
        <col width="70%" />
        <tr>
          <th>报表名称</th>
          <td><input name="reportName" id="reportName" class="half" style="height: 17px;" maxlength="40">
          <div class="wrBox" style="display:none" id="ts"><div class="wr" id="tscnt"></div>
           </div></td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox"><a href="javascript:savOrUpd(this);" class="btnY fl"><span>保存</span></a></div></td>
        </tr>
      </table>
</div>