<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2> <img src="images/ico_popclose.gif" alt="关闭" id="closePop" class="fr" onclick="closepop()"/>活动复制</h2>
<div class="popCon" id="eml">
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="30%" />
        <col width="70%" />
        <tr>
          <th>活动名称</th>
          <td><input name='campaignName_copy' id='campaignName_copy' class="half" style="height: 17px;"/> <span id="ckeml" class="red"></span></td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox"><a class="btnY fl" id="btn03" onclick="copyNames();"><span>复制</span></a>
          <a class="btnY fl" id="btn03" onclick="closepop();"><span>关闭</span></a></div></td>
        </tr>
      </table>
</div>