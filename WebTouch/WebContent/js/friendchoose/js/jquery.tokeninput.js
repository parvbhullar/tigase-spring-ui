/**
 * jquery.tokeninput.js v0.1
 * jQuery Token Input Plugin
 * @author huozhe3136 <huozhe3136@hotmail.com>
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 * $Date: 2009-01-23 7:44  $
 * Revision: $Id: jquery.tokeninput.js $
 * Example please visit http://www.xiami.com/res/js/jquery/tokeninput/index.html
 */
;(function($){
	var Token;
	
       	
	$.fn.tokeninput = function(settings){
		//defaults
		var defaults ={
			tokenName : 'tokenName',//使用该名字为创建的<input type=hidden>赋予ID，以便在后台获得数据
			type    : '0',//设置显示的形式，0显示添加参与者按钮，1不添加
			url				: '',
			data			: [], //格式[{'id':1,'name':'hh'},{'id':2,'name':'hhh'}]
			initDataFromId	: '',
			cssWrap			: 'token-wrap',	//最外层div的css
			cssMain			: 'token-main',
			cssToken		: 'token-li', 	//div里是ul，ul里是li
			cssTokenFocus	: 'token-li-focus',
			cssArrowUp		: 'up',
			preAddFromId	: '',
			tokenLimit		: 0,	//0为不限制
			allowSelAll		: true,//是否允许全选
			inputItem		:{
				id				: 'token-inputItem',
				cssName			: 'token-inputItem',
				inputId			: 'token-input',
				inputCssName	: 'token-input',
				inputTabIndex	: '1'
			},
			dropDown		:{
				cssName			: 'token-dropdown',
				cssFocus		: 'focus',
				viewItemNum  	: 8
			},
			popWin			:{
				cssName 		: 'token-popwin',
				cssHead 		: 'head',
				cssBody 		: 'body',
				cssFoot 		: 'foot',
				headerText		: '请选择',
				cssBtnOk 		: 'bt_sub2',
				btnText 		: '确 定',
				selAllText		: '选择全部'
			}
		};
		
		$.extend(defaults, settings);
		
		//Data
		var Data={
			//InitFromUrl
			GetFromUrl : function(url){
				var data = [];
				if (url) {
					$.ajax({
						  async: false,
						  type : 'post',
						  url: defaults.url,
						  success: function(d) {
							var json = eval(d);
							for(var i=0; i<json.length; i++)
							  {
							     data[i]={name:json[i].name,id:json[i].id};	
							  }		
							defaults.data = data;
						  }							
						});	
					return data;
				}
			},
			
			//InitFromUl
			GetFromUl : function(ul){
				var data=[];
				$('li',$('#'+ul)).each(function(i){
					data[i]={name:$(this).text(),id:$(this).attr('val')};			 
				});
				return data;
			}
		};
		
		return this.each(function(){
			var isTokenFocus=false;
			var $this,$wrap,$main,$ul,$li,$input,$arrow,$popWin,$dropDown,$selAll,attendeeDlgTime;			
			$this = $(this);
			attendeeDlgTime = 0;
			//与token相关
			Token={
				//创建Token			
				Create : function(domObj,index){
					if(defaults.tokenLimit>0){
						if(Token.Count(index) >= defaults.tokenLimit){
							alert('最多只能选择'+defaults.tokenLimit+'个');
							if(domObj.tagName.toLowerCase() == 'input')
							domObj.checked=false;
							return false;
						}
					}
					
					var $domObj = $(domObj);
					var title = (domObj.tagName.toLowerCase() == 'input')  ? $domObj.attr('name') :  $domObj.html().replace(/<em>/gi,'').replace(/<\/em>/gi,'');;
					//增加的代码
				    title=title.split("|")[0]; //在input中只是显示名字
				    //增加代码结束
					var value = $domObj.attr('val') || $domObj.val();
					if (value == '') return;
					if (Token.IsExist(value,index))return;
			
					var $tli=$('<li/>')	
						.attr({'val': value, 'class':defaults.cssToken,'style':'cursor:pointer;'})
						.append(title)
						.click(function(){Token.FocusOn(this,index);return false;})
						.dblclick(function(){
							PopWin.UnCheckItemByValue($(this).children('input').val(),index);
							$('#selAll'+index).attr({'checked':false});
							$(this).fadeOut('fast',function() {$(this).remove();});
							$input.focus();
						});
					$('<a/>').attr({'href': 'javascript:;'}).appendTo($tli).click(function() {
							PopWin.UnCheckItemByValue($(this).next().val(),index);
							$('#selAll'+index).attr({'checked':false});
							$(this).parent('li').fadeOut('fast',function() {$(this).remove();});
						});
					$('<input type="hidden"/>').appendTo($tli).attr({'name': defaults.tokenName, 'id': defaults.tokenName, 'value': value});

					var r = defaults.inputItem;
					var inputID = r.inputId+index;

					$('#'+inputID).val('').parent().before($tli);
					PopWin.CheckItemByValue(value,index);
					return $tli;
				},
				
				//计算Token总数
				Count : function(index){
					var $a = $('#ul'+index);
					return $('li',$a).size()-1;
				},
				
				//根据值来移除
				RemoveByValue : function(value,index){
					$a = $('#ul'+index);
					var $input = $("input[type=hidden]",$a);
					for(var i=0;i<$input.size();i++){
						if($($input[i]).val()==value) $($input[i]).parent().remove();	
					}
					if($input.size()>0) $selAll.attr('checked',false);
				},
				
				//检查token是否已经存在
				IsExist : function(value,index){
					var $a = $('#ul'+index);
					var input = $("input[type=hidden]",$a);
					for(var i=0;i<input.size();i++){
						if($(input[i]).val()==value) return true;	
					}
					return false;
				},
				
				//FocusOn
				FocusOn : function(token,index){
					Token.FocusOff(index);
					$(token).addClass(defaults.cssTokenFocus);
					isTokenFocus = true;
				},
				
				//FocusOff
				FocusOff: function(index){
					$a = $('#ul'+index);
					$('li',$a).removeClass(defaults.cssTokenFocus);
					isTokenFocus = false;
				}
			};
			
			//与下拉相关
			var DropDown = {
				FocusItem : null,
				
				//重建下拉菜单
				ReBuild : function(index){
					var $dropD = $('#dropDown'+index);
					var r = defaults.inputItem;
					var inputID = r.inputId+index;
					var $inputVar = $('#'+inputID);

					$dropD.empty();
					var str = $inputVar.val();
					if (str == ''){DropDown.FocusItem = null;return;}
					if(defaults.tokenLimit>0){
						if(Token.Count(index) >= defaults.tokenLimit){return;}
					}
					$.each(defaults.data, function(i, row){					
						if (row.name && row.name.indexOf(str)!= -1 && !Token.IsExist(row.id,index)){
							$('<a/>').attr({'val': row.id,'href':'javascript:;'})
								.html(row.name.replace(str, '<em>' + str + '</em>'))
								.appendTo($dropD)
								.click(function(){
									Token.Create(this,index);
									$input.focus();
									DropDown.Hide(index);
								})
								.hover(
									function(){
										DropDown.FocusItem = this;
										$('a',$(this).parent()).removeClass(defaults.dropDown.cssFocus);
										$(this).addClass(defaults.dropDown.cssFocus);
									},function(){
										DropDown.FocusItem = null;
										$(this).removeClass(defaults.dropDown.cssFocus);
									}
								);
						}      
					});
					$('a:first',$dropD).addClass(defaults.dropDown.cssFocus);
					DropDown.FocusItem = $('a:first',$dropD)[0];
					DropDown.SetAutoHeight(index);
				},
				
				//设置自动高度
				SetAutoHeight : function(index){
					var $dropD = $('#dropDown'+index);
					if ($dropD.children('a').size() > defaults.dropDown.viewItemNum) {
						$dropD.css({'height':(defaults.dropDown.viewItemNum*24)+'px', 'overflow':'auto'}); 
					} else {
						$dropD.css('height','auto');
					}   	
				},
				
				//显示下拉菜单
				Show : function(index){
					var $pop = $('#div' + index);
					$pop.hide();
					var $dropD = $('#dropDown'+index);
					$dropD.fadeIn('fast');			
				},
				
				//隐藏下拉菜单
				Hide : function(index){
					var $dropD = $('#dropDown'+index);
					$dropD.fadeOut('fast');
				}
			};
			
			//与弹出窗相关
			var PopWin = {
				//显示弹出窗
				Show : function(index){
					var $dropD = $('#dropDown'+index);
					var $pop = $('#div' + index);
					var arrow = $('#arrow'+index);
					//$dropDown.hide();	
					$dropD.hide();
					$pop.fadeIn('fast');
					arrow.addClass(defaults.cssArrowUp);
				},
				
				//隐藏弹出窗
				Hide : function(index){
					var $pop = $('#div' + index);
					var arrow = $('#arrow'+index);
					$pop.fadeOut('fast');
					arrow.removeClass();
				},
				
				//UnCheckItemByValue
				UnCheckItemByValue : function(value,index){
					var $pop = $('#div'+index);
					var $checkbox = $("input[type=checkbox]",$pop);
					for(var i=0;i<$checkbox.size();i++){
						if($checkbox[i].value==value) $checkbox[i].checked=false;
					}
					if($checkbox.size()>0) $selAll.attr('checked',false);
				},
				
				//CheckItemByValue
				CheckItemByValue : function(value,index){
					var $pop = $('#div'+index);
					var $checkbox = $("input[type=checkbox]",$pop);
					for(var i=0;i<$checkbox.size();i++){
						if($checkbox[i].value==value) $checkbox[i].checked=true;
					}
				},
				
				//Create
				Create : function(distinguishIndex){
					var $pop,$head,$body,$bodyUl,$foot,$footBtn;
					$pop = $('<div/>')
						.attr({'class':defaults.popWin.cssName,'id': 'div'+distinguishIndex})
						.click(function(e){
							if (e) e.stopPropagation();
							else window.event.cancelBubble = true;
						});
					
					$head = $('<div/>')
						.attr({'class':defaults.popWin.cssHead})
						.append('<h4/>'+defaults.popWin.headerText+'</h4>')
						.appendTo($pop);
						
					$body = $('<div/>')
						.attr({'class':defaults.popWin.cssBody})
						.appendTo($pop);
					
					$bodyUl = $('<ul/>').attr({'id':'bodyUl'+distinguishIndex}).appendTo($body);

					$.each(defaults.data, function(i, row){
						if (row.name) {
							var index = distinguishIndex;
							var $ch = $('<input type="checkbox"/>')
								.attr({'value':row.id,'name':row.name,'checked': Token.IsExist(row.id,distinguishIndex)})
								.click(function(){
									if(this.checked){Token.Create(this,index);}
									else{
										$('#selAll'+index).attr({'checked':false});
										Token.RemoveByValue($(this).val(),index);
									} 
								});
							
							var $lab = $('<label/>').attr({'id':'lab'+index}).append($ch).append(row.name);
							$('<li/>').append($lab).appendTo($bodyUl);
						}
					}
					);
					
					$foot = $('<div/>')
						.attr({'class':defaults.popWin.cssFoot})
						.appendTo($pop);
					
						
					if(defaults.allowSelAll){
						$selAll=$('<input type="checkbox"/>')
						.attr({'id':'selAll'+distinguishIndex})
						.click(function(){
							if(defaults.tokenLimit>0){
								if(Token.Count(distinguishIndex) >= defaults.tokenLimit){
									alert('最多只能选择'+defaults.tokenLimit+'个');
									return false;
								}
							}
							
							var $ch = $('input[type=checkbox]',$bodyUl);
							for(var i=0;i<$ch.size();i++){
								$ch[i].checked=this.checked;
								if(this.checked){
									if(!Token.IsExist($ch[i].value,distinguishIndex)) Token.Create($ch[i],distinguishIndex);
								}else
									Token.RemoveByValue($ch[i].value,distinguishIndex);
							}					
						});
						
						$('<label/>').append($selAll).append(defaults.popWin.selAllText).appendTo($foot);
					}else{
						$selAll=$('<input type="checkbox"/>').attr({'id':'selAll'+distinguishIndex});
						$('<label/>').appendTo($foot);					
					}
					
					$footBtn = $('<input type="button"/>')
						.attr({'value':defaults.popWin.btnText,'class':defaults.popWin.cssBtnOk})
						.click(
								function(){
									PopWin.Hide(distinguishIndex);
								})
						.appendTo($foot);
					//添加代码开始，添加参与者按钮
					if(defaults.type=='0'){
						$footBtn2 = $('<input type="button"/>')
								.attr({'value':'添加参与者','class':defaults.popWin.cssBtnOk})
								.click(
										function() { 
											//添加参与者窗口
											if(attendeeDlgTime==0)
												$( "#addAttendeeDiv" ).dialog({
													resizable: true,
													autoOpen: false,
													height:350,
													width:500,
													modal: true,
													buttons: {
														"添加": function() {
															var name  = $('#addAttendee input[name$="attendeeName"]').val();
															var email = $('#addAttendee input[name$="attendeeEmail"]').val();
															var phone = $('#addAttendee input[name$="attendeePhone"]').val();
															
															//异步添加用户
															$.post("MeetingServlet?action=addAttendee", $("#addAttendee").serialize(),
																	function (data, textStatus){		
																		if(textStatus == "success"){
																			$( "#addAttendeeDiv" ).dialog( "close" );
																		}else{
																			alert("创建预约会议出错！");
																		}
																	}, 
																	"json");
															
															//将新参与者添加
															var value = name+'|'+email+'|'+phone;
															var $c = $('<input type="checkbox"/>')
															.attr({'value':value,'name':value,'checked': Token.IsExist(value,distinguishIndex)})
															.click(function(){
																if(this.checked){Token.Create(this,distinguishIndex);}
																else{Token.RemoveByValue($(this).val(),distinguishIndex);} 
															});
															var $lab = $('<label/>').attr({'id':'lab'+distinguishIndex}).append($c).append(value);
															var $BODYUL = $('#bodyUl'+distinguishIndex);
															$('<li/>').append($lab).appendTo($BODYUL);
															
															$( this ).dialog( "close" );
														},
														"取消": function() {
															$( this ).dialog( "close" );
														}
													}
												});
											attendeeDlgTime = 1;
											$("#addAttendee").reset;
											$( "#addAttendeeDiv" ).dialog( "open" );
											return false;
										}
								)
								.appendTo($foot);
					}
					//添加参与者按钮结束					
					$pop.append('<b/><i/><u/><tt/>');
					return $pop;
				}			
			};
			
			//init
			function init(distinguishIndex){
				/*if(defaults.initDataFromId!=''){
					defaults.data = Data.GetFromUl(defaults.initDataFromId);
					
				}*/
				//从servlet中获得数据
				if(defaults.url!=''){
					Data.GetFromUrl('MeetingServlet?action=getAttendee');
					
				}
				var r = defaults.inputItem;
				$wrap = $('<div/>').attr('class',defaults.cssWrap);				
				$main = $('<div/>').attr('class',defaults.cssMain).appendTo($wrap);
				$ul	  = $('<ul/>').attr({'id' : 'ul'+distinguishIndex}).click(function(){$input.focus();}).appendTo($main);
				$li   = $('<li/>').attr({'class': r.cssName, 'id': r.id+distinguishIndex}).appendTo($ul);
			
				//create input
				$input = $('<input type="text"/>').appendTo($li)
					.attr({'id':r.inputId+distinguishIndex,'class': r.inputCssName,'tabindex':r.inputTabIndex,'autocomplete':'off'})
					.focus(function(){PopWin.Hide(distinguishIndex);Token.FocusOff(distinguishIndex);})
					.keyup(function(e){
						var inputStr = $(this).val();
						if(inputStr != ''){
							Token.FocusOff(distinguishIndex);
							if(e.keyCode!=40 && e.keyCode!=38){
								DropDown.ReBuild(distinguishIndex);
							}
							DropDown.Show(distinguishIndex);
						}else{						
							DropDown.Hide(distinguishIndex);
						}						
					})
					.keydown(function(e){
						var inputStr = $(this).val();
						//backspace press and then set pre token focus or remove last token
						if(e.keyCode==8 && inputStr==''){
							var lastToken = $(this).parent().prev();
							if(!isTokenFocus){
								Token.FocusOn(lastToken,distinguishIndex);
								isTokenFocus = true;
							}else{
								$(lastToken).fadeOut('fast',function() {
									$(this).remove();
									PopWin.UnCheckItemByValue($('input',$(this)).val(),distinguishIndex);
								});
								isTokenFocus = false;
							}
						}
						
						var cssFocus = defaults.dropDown.cssFocus;
						if(e.keyCode==40){
							var $dropD = $('#dropDown'+distinguishIndex);
							if(typeof(DropDown.FocusItem) == 'undefined' || DropDown.FocusItem.length ==0){
								DropDown.FocusItem = $('a:first',$dropD)[0];
							}else{
								$(DropDown.FocusItem).removeClass(cssFocus);
								DropDown.FocusItem = $(DropDown.FocusItem).next('a')[0];
								if(typeof(DropDown.FocusItem) == 'undefined' || DropDown.FocusItem.length ==0){
									DropDown.FocusItem = $('a:first',$dropD)[0];
								}
							}	
							$(DropDown.FocusItem).addClass(cssFocus);
						}
						
						if(e.keyCode==38){
							var $dropD = $('#dropDown'+distinguishIndex);
							if(typeof(DropDown.FocusItem) == 'undefined' || DropDown.FocusItem.length ==0){
								DropDown.FocusItem = $('a:last',$dropD)[0];
							}else{
								$(DropDown.FocusItem).removeClass(cssFocus);
								DropDown.FocusItem = $(DropDown.FocusItem).prev('a')[0];
								if(typeof(DropDown.FocusItem) == 'undefined' || DropDown.FocusItem.length ==0){
									DropDown.FocusItem = $('a:last',$dropD)[0];
								}
							}
							$(DropDown.FocusItem).addClass(cssFocus);
						}
						
						if(e.keyCode==13){
							if(DropDown.FocusItem != null){
								Token.Create(DropDown.FocusItem,distinguishIndex);
								$(this).val('');
								DropDown.Hide(distinguishIndex);
								DropDown.FocusItem = null;
							}
							return false;
						}
					});
				//create arrow
				$arrow=$('<span/>').attr({'id':'arrow'+distinguishIndex}).appendTo($main).click(function(){
						$popWin.css('display')=='none' ? PopWin.Show(distinguishIndex) : PopWin.Hide(distinguishIndex); return false;
					});
				
				//create preadd token item,maybe foreach
				if(defaults.preAddFromId!=''){
					var preadd = $('#'+defaults.preAddFromId+'>li');
					for(var i=0;i<preadd.size();i++){
						Token.Create(preadd[i],distinguishIndex);
					}
				}
				
				//create Dropdown
				$dropDown=$('<div/>').attr({'class':defaults.dropDown.cssName,'id' : 'dropDown'+distinguishIndex}).appendTo($wrap);			
				DropDown.ReBuild(distinguishIndex);
				
				//create popup window
				$popWin = $(PopWin.Create(distinguishIndex)).appendTo($wrap);
				
				//append all of this
				$this.before($wrap).remove();
				
				$(document).click(function(){
					PopWin.Hide(distinguishIndex);
					DropDown.Hide(distinguishIndex);
				});
			};
			//该变量用于区分不同插件
			var distiguishIndex = $this.attr('index');
			init(distiguishIndex);			
		});		
	}
})(jQuery);