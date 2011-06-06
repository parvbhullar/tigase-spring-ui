/*
 * jQuery UI Menu @VERSION
 *
 * Copyright (c) 2009 AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://docs.jquery.com/UI/Menu
 *
 * Depends:
 *      jquery.ui.core.js
 */
(function($) {

$.widget("ui.menu", {
        _init: function() {
                this.element.attr("role", "menu").attr("aria-activedescendant", "ui-active-menuitem").addClass("ui-menu ui-widget ui-widget-content ui-corner-bottom");
                var items = this.element.children("li");
                var self = this;
                items.addClass("ui-menu-item").attr("role", "menuitem")
                .children("a").attr("tabindex", "-1").addClass("ui-corner-all")
                // replace with event delegation
                .click(function(e) {
                        // temporary
                        e.preventDefault();
                        self._trigger("selected", null, { item: $(this).parent() });
                })
                .mouseenter(function() {
                        self.activeitem && self.deactivate(self.activeitem);
                        self.activate($(this));
                });
                
        },
        
        activate: function(item) {
                this._trigger("focus", null, { item: item.parent() });
                this.activeitem = item.addClass("ui-state-hover").attr("id", "ui-active-menuitem");
        },
        
        deactivate: function(item) {
                item.removeClass("ui-state-hover").removeAttr("id", "ui-active-menuitem");
        },
        
        next: function() {
                if (!this.activeitem) {
                        this.activate(this.element.children("li:first").children("a"));
                        return;
                }
                this.deactivate(this.activeitem);
                this.activate(this.activeitem.parent().next().children("a"));
        },
        
        previous: function() {
                if (!this.activeitem) {
                        this.activate(this.element.children("li:last").children("a"));
                        return;
                }
                this.deactivate(this.activeitem);
                this.activate(this.activeitem.parent().prev().children("a"));
        },
        
        select: function() {
                // merge with click handler
                this._trigger("selected", null, { item: $(this.activeitem).parent() });
        }
        
});

})(jQuery);