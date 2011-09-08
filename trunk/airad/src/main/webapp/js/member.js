
//重置密码邮件发送页面提交
function registerPwdSendEmailPageSubmti(obj) {
	if(clckimg(obj)){
		obj.className="btnG fl";
		obj.innerHTML="<span>邮件发送中....</span>";
		document.smyfrm.submit();
	}
}

//找回密码（重置密码）页面提交
function registerPwdPageSubmti(obj) {
    var errNum = 0;
    errNum = errNum + validatePasswordsValue();
    errNum = errNum + validatePasswordValue();
    if (errNum == 0 ) {
      if (clckimg(obj)) {
        document.myFrm.submit();
      }
    }
}

/**
 * 验证找回密码邮件发送页面邮件格式
 * @param {Object} obj
 */
function validatePasswordRetrieve(obj){
  var emailValidat =/^([a-zA-Z0-9_\.\-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if (!emailValidat.test(obj.value)) {
          document.getElementById('resettingErrDiv').style.display='';
          document.getElementById('errMagessDiv').innerHTML="邮箱格式错误";
        }else{
          document.getElementById('resettingErrDiv').style.display='none';
        }
}

//验证邮箱
function validateEmailValue() {
    var email = document.myFrm.email.value;
    if ("" != email) {
       // var emailValidat = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        var emailValidat =/^([a-zA-Z0-9_\.\-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if (!emailValidat.test(email)) {
            document.getElementById("emailDiv1").style.display="block";
            document.getElementById("emailDiv").innerHTML = "邮箱格式错误！";
            return 1;
        }
        ajaxFunction();
        document.getElementById("emailDiv1").style.display="none";
        document.getElementById("emailDiv").innerHTML = "";
        return 0;
    }
    else {
        document.getElementById("emailDiv1").style.display="block";
        document.getElementById("emailDiv").innerHTML = "请输入邮箱！";
        return 1;
    }
}

//ajax验证用户是否存在
function ajaxFunction() {
  var email = document.myFrm.email.value;
  $.post("member.do?action=ajaxValidataUEmail&email="+email,
    function (data, textStatus){
      // data 可以是 xmlDoc, jsonObj, html, text, 等等.
      //this; // 这个Ajax请求的选项配置信息，请参考jQuery.get()说到的this
       if (data.length < 1) {
         document.getElementById("emailDiv1").style.display = "none";
       }
       else {
         document.getElementById("emailDiv1").style.display = "block";
         document.getElementById("emailDiv").innerHTML = data;
       }
    }, "text");
}

//验证密码是否输入以及长度
function validatePasswordValue() {
    if (document.myFrm.password) {
      var password=document.myFrm.password.value;
      var emailValidat =/^[a-z,A-Z,0-9,-]{6,20}$/;
      if(!emailValidat.test(password)){
         document.getElementById("passwordDiv1").style.display = "block";
            document.getElementById("passwordDiv").innerHTML = "密码至少要6位,最多20位！(密码只能包含A-Z，a-z，0-9，连字符-)"
            return 1;
        }
        document.getElementById("passwordDiv1").style.display = "none";
        document.getElementById("passwordDiv").innerHTML = "";
        return 0;
    }
    else {
        document.getElementById("passwordDiv1").style.display = "block";
        document.getElementById("passwordDiv").innerHTML = "请输入密码！";
        return 1;
    }
}

//验证码
function validateVerifyCodeValue() {
    if (document.myFrm.verifyCode.value != "") {
        document.getElementById("verifyCodeDiv1").style.display = "none";
        document.getElementById("verifyCodeDiv").innerHTML = "";
        return 0;
    }
    else {
        document.getElementById("verifyCodeDiv1").style.display = "block";
        document.getElementById("verifyCodeDiv").innerHTML = "请输入验证码！";
        return 1;
    }
}

//验证确认密码是否输入以及长度
function validatePasswordsValue() {
    if (document.myFrm.passwords) {
        var passwords = document.myFrm.passwords.value;
        var password = document.myFrm.password.value;
        if (password != passwords) {
            document.getElementById("passwordsDiv1").style.display = "block";
            document.getElementById("passwordsDiv").innerHTML = "确认密码和密码不一致！";
            return 1;
        }
        document.getElementById("passwordsDiv1").style.display = "none";
        document.getElementById("passwordsDiv").innerHTML = "";
        return 0;
    }
    else {
        document.getElementById("passwordsDiv1").style.display = "block";
        document.getElementById("passwordsDiv").innerHTML = "请输入确认密码！";
        return 1;
    }
}


/**
 * 图片验证码刷新
 */
function getValidataImg() {
    setTimeout(function(){
      jQuery("#validataImg").attr("src","canpanta.png?_sed=" +
    new Date().getTime());
    },0);
}

//验证身份证号码
function validateCardIdValue() {
    var cardId = document.getElementById("cardId").value;
    if ("" != cardId) {
       var errorMgs = checkIdcard(cardId);
        if (""!=errorMgs) {
            document.getElementById("cardIdDiv1").style.display = "block";
            document.getElementById("cardIdDiv").innerHTML = errorMgs;
            return 1;
        }
        document.getElementById("cardIdDiv1").style.display = "none";
        document.getElementById("cardIdDiv").innerHTML = "";
        return 0;
    }
    else {
        document.getElementById("cardIdDiv1").style.display = "block";
        document.getElementById("cardIdDiv").innerHTML = "请输入身份证号码！";
        return 1;
    }
}

//验证是否选择了照片
function validatePhotoValue(){
   if (document.myFrm.photo.value != "") {
     document.getElementById("photoDiv1").style.display = "none";
     document.getElementById("photoDiv").innerHTML = "";
    }
    return 0;
    //else {
       // document.getElementById("photoDiv1").style.display = "block";
        //document.getElementById("photoDiv").innerHTML = "请选择上传身份证图片！";
        //return 1;
    //}
}

//验证身份证是否合法
function checkIdcard(idcard){
  if(idcard=='111111111111111'){
    return "请输入正确的身份证号!";
  }
var Errors=new Array(
"",
"身份证号码位数不对!",
"身份证号码出生日期超出范围或含有非法字符!",
"身份证号码校验错误!",
"请输入正确的身份证号!"
);
var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}

var idcard,Y,JYM;
var S,M;
var idcard_array = new Array();
idcard_array = idcard.split("");
//地区检验
if(area[idcard.substr(0,2)]==null) return Errors[4];

//身份号码位数及格式检验
switch(idcard.length){
case 15:
if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
} else {
ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
}

if (ereg.test(idcard)) {
  return Errors[0];
}
else return Errors[2];
alert(Errors[2]);
break;
case 18:
//18位身份号码检测
//出生日期的合法性检查
//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
} else {
ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
}
if(ereg.test(idcard)){//测试出生日期的合法性
//计算校验位
S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
+ parseInt(idcard_array[7]) * 1
+ parseInt(idcard_array[8]) * 6
+ parseInt(idcard_array[9]) * 3 ;
Y = S % 11;
M = "F";
JYM = "10X98765432";
M = JYM.substr(Y,1);//判断校验位
if(M == idcard_array[17]) return Errors[0]; //检测ID的校验位
else return Errors[3];
}
else return Errors[2];
break;
default:
return Errors[1];
break;
}

}

//广告主认证页面提交
function advertisersAutSubmti() {
    var errNum=0;
    errNum = errNum +  validatePhotoValue();
    errNum = errNum + validateCardIdValue();
    if(errNum==0){
    //  document.myFrm.action="UploadServlet.servlet?cardId="+document.getElementById("cardId").value;
      document.myFrm.submit();
    }
}
function updateLoginPasswordSubmit() {
  var currKey=0,e=e||event;
  if(e.keyCode==13)document.updateLoginPassword.submit();
}



