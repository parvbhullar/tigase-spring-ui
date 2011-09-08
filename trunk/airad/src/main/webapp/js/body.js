if (!!window.jQuery) {
    $(document).ready(function(){
        $("#subBtn,#sub").mouseover(function(){
            $("#sub").show();
        })
        $("#subBtn,#sub").mouseout(function(){
            $("#sub").hide();
        })
        $(".navBox>ul>li").bind("mouseover", function(event){
            $(this).find("ul").stop(true, true);
            $(this).find("ul").slideDown("fast");
        });
        $(".navBox>ul>li").bind("mouseout", function(){
            $(this).find("ul").delay(1).slideUp("fast");
        });
        $(".navBox>ul>li>ul").bind("mouseover", function(event){
            $(this).stop(true, true);
            $(this).show();
        });
        $("a[name='CSonline']").bind("click", function(){
            $("<iframe id=\"online\" frameborder=\"0\" style=\"display:none\"></iframe>").appendTo("body");
            $("#online").attr("src", "/ins.do?xcase=onLine");
        });
    });
}
else {
    window.onload = function(){
        document.getElementById("subBtn").onmouseover = function(){
            document.getElementById("sub").style.display = "block";
        }
        document.getElementById("subBtn").onmouseout = function(){
            document.getElementById("sub").style.display = "none";
        }
    }
}
(function(){
    $.extend($.fn, {
        mask: function(msg, maskDivClass, opacity){
            this.unmask();
            var op = {
                opacity: opacity,
                z: 10000,
                bgcolor: '#000000'
            };
            var original = $(document.body);
            var position = {
                top: 0,
                left: 0
            };
            if (this[0] && this[0] !== window.document) {
                original = this;
                position = original.position();
            }
            var maskDiv = $('<div class="maskdivgen"></div>');
            maskDiv.appendTo(original);
            var maskWidth = original.outerWidth();
            if (!maskWidth) {
                maskWidth = original.width();
            }
            var maskHeight = original.outerHeight();
            if (!maskHeight) {
                maskHeight = original.height();
            }
            maskDiv.css({
                position: 'absolute',
                top: position.top,
                left: position.left,
                'z-index': op.z,
                width: maskWidth,
                height: maskHeight,
                'background-color': op.bgcolor,
                opacity: 0
            });
            if (maskDivClass) {
                maskDiv.addClass(maskDivClass);
            }
            if (msg && typeof(msg) == "string" && $.trim(msg) != "") {
                var msgDiv = $('<div style="position:absolute;border:#6593cf 1px solid; padding:1px;background:#ccca"><div style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px"><img align="absMiddle" src="/images/ico_loading.gif"/>&nbsp;' + msg + '</div></div>');
                msgDiv.appendTo(maskDiv);
                var widthspace = (maskDiv.width() - msgDiv.width());
                var heightspace = (maskDiv.height() - msgDiv.height());
                msgDiv.css({
                    cursor: 'wait',
                    top: (heightspace / 2 - 2),
                    left: (widthspace / 2 - 2)
                });
            }
            maskDiv.bgiframe();
            maskDiv.fadeIn('fast', function(){
                $(this).fadeTo('slow', op.opacity);
            });
            return maskDiv;
        },
        unmask: function(){
            var original = $(document.body);
            if (this[0] && this[0] !== window.document) {
                original = $(this[0]);
            }
            original.find("> div.maskdivgen").fadeOut('slow', function(){
                $(this).remove();
            });
        }
    });
})(jQuery);
(function($){
    $.fn.bgiframe = ($.browser.msie && /msie 6\.0/i.test(navigator.userAgent) ? function(s){
        s = $.extend({
            top: 'auto',
            left: 'auto',
            width: 'auto',
            height: 'auto',
            opacity: true,
            src: 'javascript:false;'
        }, s);
        var html = '<iframe class="bgiframe"frameborder="0"tabindex="-1"src="' + s.src + '"' + 'style="display:block;position:absolute;z-index:-1;' +
        (s.opacity !== false ? 'filter:Alpha(Opacity=\'0\');' : '') +
        'top:' +
        (s.top == 'auto' ? 'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')' : prop(s.top)) +
        ';' +
        'left:' +
        (s.left == 'auto' ? 'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')' : prop(s.left)) +
        ';' +
        'width:' +
        (s.width == 'auto' ? 'expression(this.parentNode.offsetWidth+\'px\')' : prop(s.width)) +
        ';' +
        'height:' +
        (s.height == 'auto' ? 'expression(this.parentNode.offsetHeight+\'px\')' : prop(s.height)) +
        ';' +
        '"/>';
        return this.each(function(){
            if ($(this).children('iframe.bgiframe').length === 0) 
                this.insertBefore(document.createElement(html), this.firstChild);
        });
    }
 : function(){
        return this;
    });
    $.fn.bgIframe = $.fn.bgiframe;
    function prop(n){
        return n && n.constructor === Number ? n + 'px' : n;
    }
})(jQuery);
(function($){
    $.fn.center = function(settings){
        var style = $.extend({
            position: 'absolute',
            top: '50%',
            left: '50%',
            zIndex: 10001,
            relative: true
        }, settings ||
        {});
        return this.each(function(){
            var $this = $(this);
            if (style.top == '50%') 
                style.marginTop = -$this.outerHeight() / 2;
            if (style.left == '50%') 
                style.marginLeft = -$this.outerWidth() / 2;
            if (style.relative && !$this.parent().is('body') && $this.parent().css('position') == 'static') 
                $this.parent().css('position', 'relative');
            delete style.relative;
            if (style.position == 'fixed' && $.browser.version == '6.0') {
                style.marginTop += $(window).scrollTop();
                style.position = 'absolute';
                $(window).scroll(function(){
                    $this.stop().animate({
                        marginTop: $(window).scrollTop() - $this.outerHeight() / 2
                    });
                });
            }
            $this.css(style);
        });
    };
})(jQuery);
(function(){
    $.fn.mimePost = function(){
        var href = $(this).get(0).getAttribute('href', 2);
        $(this).bind("click", function(){
            $(this).after("<form id=\"mimePost\" action=\"" + href + "\" method=\"post\"></form>");
            $("#mimePost").submit();
            return false;
        })
    }
    if ($("a#logout").length > 0) {
        $("a#logout").mimePost();
    }
})(jQuery);
var hrefFormat = function(href){
    if (typeof(href) == "string" && href != "") {
        var rurl = /^(\w+:)?\/\/([^\/?#]+)/;
        var parts = rurl.exec(href);
        var remote = parts && (parts[1] && parts[1] == location.protocol && parts[2] == location.host);
        if (remote) {
            href = href.split("/")[3];
        }
    }
    return href;
}
