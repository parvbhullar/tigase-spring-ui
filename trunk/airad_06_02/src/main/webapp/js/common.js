function isIE6(){
  $.browser.msie&&($.browser.version == "6.0")&&!$.support.style ? true:false;
}
function submit(formName) {
	document.getElementById(formName).submit();
}

/**
 * 二级菜单的样式
 * 
 * @param {Object}
 *            str
 * 
 */

function addCssByURL(str) {
//  alert(str);
  var winUrl = window.location.pathname + window.location.search;
  var isHighLight = false;
  var navHref = $("#navChl").children("a");
  navHref.each(function() {
    var url = $(this).attr("href");
   // alert(url);
    if (url.indexOf(str) != -1 ) {
      $(this).addClass("now");
    } else {
      $(this).removeClass("now");
    }
  });
}

function addCss(str) {
	var winUrl = window.location.pathname + window.location.search;
	var isHighLight = false;
	var navHref = $("#navChl").children("a");
	navHref.each(function() {
		var url = $(this).attr("href");
		if (winUrl.indexOf(url) != -1 && !isHighLight) {
			$(this).addClass("now");
			isHighLight = true;
		} else {
			$(this).removeClass("now");
		}
	});
	if (!isHighLight) {
		navHref.each(function() {
			var url = $(this).attr("href");
       
			if (url.indexOf(str) != -1 && !isHighLight) {
				$(this).addClass("now");
				isHighLight = true;
			} else {
				$(this).removeClass("now");
			}
		});
	}
}

/**
 * 重复提交判断
 * 
 * @param {Object}
 *            obj
 */
function clckimg(obj) {
	var ctimes = obj.ctimes;
	if (parseInt(ctimes) > 0) {
		// alert('请不要重复点击，谢谢！');
		return false;
	} else {
		obj.ctimes = "1";
		return true;
	}
}

var clearMenu;
$(document).ready(function() {
	$('#_roleToggle').hover(function() {
		clearTimeout(clearMenu);
		$("#imenu").show();
	}, function() {
		clearMenu = setTimeout(function() {
			$("#imenu").hide();
		}, 200);
	});
	$("#imenu").hover(function() {
		clearTimeout(clearMenu);
	}, function() {
		$("#imenu").hide();
	});
	$('#_switchDev').click(function() {
		changeRank('2')
	});
	$('#_switchAdv').click(function() {
		changeRank('1')
	});
});

function showTips(value,isWarn) {
  //  var value = $.cookie('_m');
  var cssvar = 'ok';
  if (isWarn) {
    cssvar =  'wrTip';
  }
 
    var s = "<div class='okBox'><div class='"+cssvar+"'>" + value + "</div></div>";
    $('body').prepend(s);
    setTimeout(function() {
      $('.okBox').fadeOut(800);
    }, 1000)

 //   $.cookie('_m', null);
 
}

function changeRank(str) {
	var rurl = "/member.do?action=getRoleType";
	jQuery.get(rurl, {
		roleId : str,
		r : Math.random()
	}, function(data) {
		if (data == "2") {
			location.href = "/personal.do?action=developlist";
		} else if(data=='1'){
			location.href = "/personal.do?action=advlist";
		}
	},"text");
}
$(document).ready(function() {
	var value = $.cookie('_m');
	if (value) {
		var s = "<div class='okBox'><div class='ok'>" + value + "</div></div>";
		$('body').prepend(s);
		setTimeout(function() {
			$('.okBox').fadeOut(800);
		}, 1000)

		$.cookie('_m', null);
	}
});
