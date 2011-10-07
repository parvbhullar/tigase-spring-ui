/**
 * 
 */
$(document).ready( function() {		
	      /*添加按钮点击事件开始*/
	      $('#area').click(function() { 
	          $.blockUI({ 
	              theme:     true, 
	              title:    '选择地区', 
	              message:  $("#jobLayerId"),
	              css: { 
	            	  top:  ($(window).height() - 400) /2 + 'px', 
	                  left: ($(window).width() - 400) /2 + 'px'
	              }
	          });
	          console.info($("#jobLayerId").css("width"));
	          $("#jobLayerId").parent().parent().css("width",(($(window).width() - 400)+"px"));
	          $("#jobLayerId").parent().parent().css("left",(($(window).width() - 700)/2+"px"));
	          $("#jobLayerId").parent().parent().css("top",(($(window).height() - 600)/2+"px"));
	          console.info($("#jobLayerId").parent().parent().css("width"));
	          $("#jobLayerId tbody tbody td").hover(
	        		  function () {
	        			  $(this).removeClass("zz_51Lower");
	        			  $(this).addClass("zz_51High"); 
	        			  }, 
	        			  function () { 
	        			  $(this).removeClass("zz_51High");
	        			  $(this).addClass("zz_51Lower");
	        			  } 
	        		  )
	          
	      });
	      
	      $('#parea').click(function() {
	    	  $("#pslayer").show();
	    	  $.blockUI({ 
	              title:    '选择地区', 
	              message:  $("#pslayer"),
	              css: { 
	            	  top:  ($(window).height() - 400) /2 + 'px', 
	                  left: ($(window).width() - 400) /2 + 'px'
	              }
	          });
	      })
	      
	      $('#imgClose').click(function() {
	    	  $("#pslayer").hide();
	    	  $.unblockUI();
	    	  return false;;
	      })
	      
	      
	      
	      $('#cancel').click(function() { 
	            $.unblockUI(); 
	            return false; 
	        });
})