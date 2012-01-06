jQuery
		.extend(
				Array.prototype,
				{
					remove : function(c) {
						var b = this.clone();
						this.length = 0;
						for ( var a = 0; a < b.length; a++)
							b[a].id != c && this.push(b[a]);
						return this
					},
					has : function(b) {
						for ( var a = 0; a < this.length; a++) {
							if (this[a] == b)
								return true;
							if (typeof b == "object" && this[a].id == b.id)
								return true
						}
						return false
					},
					GetFather : function(b) {
						for ( var a = 0; a < this.length; a++) {
							if (this[a].parObj == null)
								continue;
							if (this[a].parObj.id == b.id)
								return true;
							if (typeof b == "object" && this[a].id == b.id)
								return true
						}
						return false
					},
					parItems : getLoc(),
					GetFaFather : function(c) {
						for ( var a = 0; a < this.length; a++) {
							if (this[a].parObj == null)
								continue;
							if (this[a].parObj.parObj == null)
								continue;
							var b = this.parItems[parseInt(this[a].parObj.parObj)];
							if (b != null) {
								if (b.id == c.id)
									return true
							} else if (typeof this[a].parObj.parObj == "object"
									&& this[a].parObj.parObj.id == c.id)
								return true
						}
						return false
					},
					findById : function(d) {
						for ( var a = 0; a < this.length; a++) {
							if (this[a].id == d) {
								this[a].parObj = null;
								return this[a]
							}
							if (this[a].subItems != null
									&& this[a].subItems != "undefined")
								for ( var b = 0; b < this[a].subItems.length; b++) {
									if (this[a].subItems[b].id == d) {
										this[a].subItems[b].parObj = this[a];
										return this[a].subItems[b]
									}
									if (this[a].subItems[b].subItems != null
											&& this[a].subItems[b].subItems != "undefined")
										for ( var c = 0; c < this[a].subItems[b].subItems.length; c++)
											if (this[a].subItems[b].subItems[c].id == d) {
												this[a].subItems[b].subItems[c].parObj = this[a].subItems[b];
												this[a].subItems[b].subItems[c].parObj.parObj = this[a];
												return this[a].subItems[b].subItems[c]
											}
								}
						}
						return null
					},
					findById : function(d, e) {
						for ( var a = 0; a < this.length; a++) {
							if (this[a].id == d) {
								this[a].parObj = null;
								return this[a]
							}
							if (e)
								continue;
							if (this[a].subItems != null
									&& this[a].subItems != "undefined")
								for ( var b = 0; b < this[a].subItems.length; b++) {
									if (this[a].subItems[b].id == d) {
										this[a].subItems[b].parObj = this[a];
										return this[a].subItems[b]
									}
									if (this[a].subItems[b].subItems != null
											&& this[a].subItems[b].subItems != "undefined")
										for ( var c = 0; c < this[a].subItems[b].subItems.length; c++)
											if (this[a].subItems[b].subItems[c].id == d) {
												this[a].subItems[b].subItems[c].parObj = this[a].subItems[b];
												this[a].subItems[b].subItems[c].parObj.parObj = this[a];
												return this[a].subItems[b].subItems[c]
											}
								}
						}
						return null
					},
					clear : function() {
						this.length = 0;
						return this
					},
					clone : function() {
						return [].concat(this)
					}
				});
var requestFromStr = {
	QueryString : function(a, c) {
		var b = a.substring(0, 1) == "?" ? a : "?" + a, d = new RegExp("[&?]"
				+ c + "=([^&?]*)", "ig");
		return b.match(d) ? b.match(d)[0].substr(c.length + 2) : ""
	}
};
function locConverter(d) {
	for ( var b = d.split(","), c = [], a = 0; a < b.length; a++)
		c.push(ConverterLoc(b[a]));
	return c
}
function traversal(e, c) {
	var a = c.findById(e);
	if (a != null)
		return {
			id : a.id,
			name : a.name,
			parObj : null
		};
	else
		for ( var f, b, d = 0; d < c.length; d++) {
			a = c[d];
			b = a.subItems.findById(e);
			if (b != null)
				return {
					id : b.id,
					name : b.name,
					parObj : {
						id : a.id,
						name : a.name,
						parObj : null
					}
				}
		}
	return null
}
function dbcToSbc(a) {
	return a.replace(/\uff08/g, "(").replace(/\uff09/g, ")")
}
function addSpan(a) {
	a = dbcToSbc(a);
	if (a.length > 9)
		a = "<span>" + a + "</span>";
	return a
}
var PopupSelector = {
	loadSelected : function(k) {
		var b = [], f = requestFromStr.QueryString(k, "occParentIDList");
		if (f != "")
			if (f.indexOf(",") > 0)
				f = f.split(",");
			else {
				f = f.replace(/%2C/g, "%2c");
				f = f.split("%2c")
			}
		var d = requestFromStr.QueryString(k, "occIDList");
		if (d.indexOf(",") > 0)
			d = d.split(",");
		else {
			d = d.replace(/%2C/g, "%2c");
			d = d.split("%2c")
		}
		var p = requestFromStr.QueryString(k, "addOccIDList");
		if (p != "") {
			d.clear();
			d[0] = p
		}
		this._selItems.cat.clear();
		var t = getCat();
		if (p != "")
			for ( var l, a = 0; a < d.length; a++) {
				l = traversal(d[a], t);
				if (l != null) {
					this._selItems.cat.push(l);
					b.push(l.name)
				}
			}
		if (p == "" && f == "") {
			for ( var x = [], a = 0; a < d.length; a++) {
				var s = t.findById(d[a]);
				if (s != null)
					if (s.parObj == null)
						x.push(s.id);
					else
						x.push(s.parObj.id)
			}
			f = x
		}
		if (d.length > 0 && d[0] != "" && f != "" && p == "")
			for ( var a = 0; a < f.length; a++)
				if (f[a] == d[a]) {
					if (d[a] != 255) {
						var l = t.findById(d[a]);
						this._selItems.cat.push( {
							id : l.id,
							name : l.name,
							parObj : null
						});
						b.push(l.name)
					}
				} else {
					var y = t.findById(f[a]), z = y.subItems.findById(d[a]);
					this._selItems.cat.push( {
						id : z.id,
						name : z.name,
						parObj : {
							id : y.id,
							name : y.name,
							parObj : null
						}
					});
					b.push(z.name)
				}
		jQuery("#txtCat").val(b.join("+"));
		this.InitClass(b, "cat");
		b.length = 0;
		var c = requestFromStr.QueryString(k, "myLocIDList");
		if (c.indexOf(",") > 0)
			c = c.split(",");
		else {
			c = c.replace(/%2C/g, "%2c");
			c = c.split("%2c")
		}
		var e = requestFromStr.QueryString(k, "myLocParentIDList");
		if (e != "")
			if (e.indexOf(",") > 0)
				e = e.split(",");
			else {
				e = e.replace(/%2C/g, "%2c");
				e = e.split("%2c")
			}
		var r = requestFromStr.QueryString(k, "addLocIDList");
		if (r != "") {
			c.clear();
			c[0] = r
		}
		if (e == "" && c != "") {
			for ( var w = [], a = 0; a < c.length; a++) {
				var B = this.allItems.loc().findById(c[a]);
				if (B.parObj == null)
					w.push(B.id);
				else
					w.push(B.parObj.id)
			}
			e = w
		}
		this._selItems.loc.clear();
		var u = getLoc();
		if (r != "" || e == "")
			for ( var g, a = 0; a < c.length; a++) {
				g = u.findById(c[a]);
				if (g != null) {
					this._selItems.loc.push(g);
					if (g.parObj != null)
						if (g.parObj.parObj != null)
							b.push(g.parObj.parObj.name + "-" + g.parObj.name
									+ "-" + g.name);
						else
							b.push(g.parObj.name + "-" + g.name);
					else
						b.push(g.name)
				}
			}
		if (c.length > 0 && c[0] != "" && e != "" && r == "")
			for ( var a = 0; a < e.length; a++)
				if (e[a] == c[a] || e[a] == -1) {
					if (c[a] != 255) {
						var g = u.findById(c[a]);
						this._selItems.loc.push( {
							id : g.id,
							name : g.name,
							parObj : null
						});
						b.push(g.name)
					}
				} else {
					var i = u.findById(e[a]);
					if (i != null) {
						var n = i.subItems.findById(c[a]), F = this.allItems
								.loc(), A = "", i = u.findById(e[a]);
						if (i != null && i.parObj != null) {
							var m = F[parseInt(i.parObj)];
							if (m == "undefined" || m == null)
								m = i.parObj;
							if (m.name == i.parObj.name) {
								A = m.name + "-" + i.name + "-" + n.name;
								this._selItems.loc.push( {
									id : n.id,
									name : n.name,
									parObj : {
										id : i.id,
										name : i.name,
										parObj : {
											id : m.id,
											name : m.name,
											parObj : null
										}
									}
								})
							}
						} else {
							A = i.name + "-" + n.name;
							this._selItems.loc.push( {
								id : n.id,
								name : n.name,
								parObj : {
									id : i.id,
									name : i.name,
									parObj : null
								}
							})
						}
						b.push(A)
					}
				}
		jQuery("#txtLoc").val(b.join("+"));
		this.InitClass(b, "loc");
		b.length = 0;
		var j = requestFromStr.QueryString(k, "indIDList");
		if (j.indexOf(",") > 0)
			j = j.split(",");
		else {
			j = j.replace(/%2C/g, "%2c");
			j = j.split("%2c")
		}
		var E = requestFromStr.QueryString(k, "addIndIDList"), C = getInd(), o = C
				.findById(E);
		this._selItems.ind.clear();
		if (o != null) {
			this._selItems.ind.push( {
				id : o.id,
				name : o.name,
				parObj : null
			});
			b.push(o.name)
		}
		if (j.length > 0 && j[0] != "" && o == null)
			for ( var a = 0; a < j.length; a++)
				if (j[a] != 255) {
					var v = C.findById(j[a]);
					if (v == null)
						continue;
					this._selItems.ind.push( {
						id : v.id,
						name : v.name,
						parObj : null
					});
					b.push(v.name)
				}
		jQuery("#txtInd").val(b.join("+"));
		this.InitClass(b, "ind");
		b.length = 0;
		if (jQuery("#txtAdvanceCompType").length > 0) {
			var h = requestFromStr.QueryString(k, "companyTypeIDList");
			if (h != "")
				if (h.indexOf(",") > 0)
					h = h.split(",");
				else {
					h = h.replace(/%2C/g, "%2c");
					h = h.split("%2c")
				}
			var D = getCompanyType();
			if (h.length > 0 && h[0] != "")
				for ( var a = 0; a < h.length; a++)
					if (h[a] != 255) {
						var q = D.findById(h[a]);
						if (q == null)
							continue;
						this._selItems.comptype.push( {
							id : q.id,
							name : q.name,
							parObj : null
						});
						b.push(q.name)
					}
			jQuery("#txtAdvanceCompType").val(b.join("+"));
			b.length = 0
		}
	},
	RenderSelectedHTML : function() {
		var b = ""
	},
	InitClass : function(b, a) {
		if (b.length != 0) {
			if (a == "cat") {
				$("#txtCat").removeClass(
						"inp_txt inp_txtsel inp_wl inp_cue gray");
				$("#txtCat").addClass("inp_txt inp_txtsel inp_wl")
			}
			if (a == "loc") {
				$("#txtLoc").removeClass(
						"inp_txt inp_txtsel inp_wm inp_cue gray");
				$("#txtLoc").addClass("inp_txt inp_txtsel inp_wm")
			}
			if (a == "ind") {
				$("#txtInd").removeClass(
						"inp_txt inp_txtsel inp_wm inp_cue gray");
				$("#txtInd").addClass("inp_txt inp_txtsel inp_wm")
			}
		} else {
			if (a == "cat") {
				$("#txtCat").removeClass("inp_txt inp_txtsel inp_wl");
				$("#txtCat").addClass("inp_txt inp_txtsel inp_wl inp_cue gray");
				$("#txtCat").val("\u8bf7\u9009\u62e9\u804c\u4e1a\u7c7b\u522b")
			}
			if (a == "loc") {
				$("#txtLoc").removeClass("inp_txt inp_txtsel inp_wm");
				$("#txtLoc").addClass("inp_txt inp_txtsel inp_wm inp_cue gray");
				$("#txtLoc").val("\u8bf7\u9009\u62e9\u5de5\u4f5c\u5730\u70b9")
			}
			if (a == "ind") {
				$("#txtInd").removeClass("inp_txt inp_txtsel inp_wm");
				$("#txtInd").addClass("inp_txt inp_txtsel inp_wm inp_cue gray");
				$("#txtInd").val("\u8bf7\u9009\u62e9\u884c\u4e1a\u7c7b\u522b")
			}
		}
	},
	popup : function(g, a, c, f, d) {
		typeof Mask != "undefined" && Mask != null && Mask.Display();
		this.AarearenderSelItemEleId = c;
		this.AareatagPrefix = f;
		this.AarearenderSelItemDiv = a;
		var e;
		if (this._box == null)
			this._box = jQuery("#popupSelector");
		if (this._subBox == null)
			this._subBox = document.getElementById("subItems");
		this._ref = a;
		this._type = g.toString().toLowerCase();
		if (this.AarearenderSelItemEleId != null)
			e = document.getElementById(this.AarearenderSelItemEleId).value
					.split(",");
		var h = getLoc();
		this._curItems.length = 0;
		this._curItems = this._selItems[this._type].clone();
		if (arguments.length == 3)
			this._checkType = d;
		var b = jQuery("#" + a.id).offset();
		this._style.left = b.left;
		this._style.top = b.top;
		this.render()
	},
	render : function() {
		var e = {
			top : this._style.top + this._style.offset.levelOne.Y,
			left : this._style.left + this._style.offset.levelOne.X
		};
		if (this._type != "cat" && this._type != "comptype")
			if (this._type == "ind")
				e.left = jQuery(jQuery("#txtCat")[0]).offset().left;
			else if (this._type == "loc")
				if (this.InSalaryPage)
					e.left = this._style.left;
				else if (this.InAdvanceSearchPage)
					e.left = this._style.left;
				else
					e.left = this._style.left
							- this._style.offset.levelOne[this._type].X;
		var g;
		if (jQuery.browser.msie && jQuery.browser.version == "6.0")
			g = document.body.scrollTop;
		else
			g = document.documentElement.scrollTop;
		var l = jQuery(window).height() - e.top + g;
		if (l < this._style.height[this._type])
			e.top = e.top - (this._style.height[this._type] - l);
		if (this._style.height[this._type] > jQuery(window).height())
			e.top = g;
		for ( var m = jQuery("#pslayer"), b = 0; b < this._types.length; b++)
			m.removeClass(this._style.className.levelOne[this._types[b]]);
		m.addClass(this._style.className.levelOne[this._type]);
		if (this.InSalaryPage)
			this._gtYourSelected.ind = "\u8bf7\u9009\u62e9\u4f60\u7684\u884c\u4e1a\u7c7b\u522b\uff1a";
		jQuery("#selectingHeader").html(this._gtYourSelected[this._type]);
		jQuery("#psHeader").html(this._gtPopupSelectorHeader[this._type]);
		var a = [], o, d = this.allItems[this._type](), n = 0, i = d.length;
		if (this._type == "loc") {
			n = 0;
			i = 34;
			jQuery("#subHeader1").html(
					"<span>\u6240\u6709\u7701\u5e02\uff1a</span>");
			jQuery("#subHeader2")
					.html(
							"<span>\u5176\u5b83\u56fd\u5bb6\u548c\u5730\u533a\uff1a</span>")
					.show();
			for ( var b = i; b < d.length; b++) {
				if (this._curItems.has( {
					id : d[b].id,
					name : d[b].name,
					parObj : null
				}))
					a.push("<li id=jQuery" + b + " name=" + d[b].id
							+ ' class="layon"><a href="javascript:void(0);">');
				else
					a
							.push("<li id=jQuery"
									+ b
									+ " name="
									+ d[b].id
									+ ' class="nonelay"><a href="javascript:void(0);">');
				a.push('<label for="pcbx');
				a.push(d[b].id);
				a.push('">');
				a.push('<input id="pcbx');
				a.push(d[b].id);
				a.push('" type="' + PopupSelector._checkType + '" value="');
				a.push(d[b].id);
				a.push("@");
				a.push(d[b].name);
				a.push('"');
				this._curItems.has( {
					id : d[b].id,
					name : d[b].name,
					parObj : null
				}) && a.push(" checked");
				a.push(' onclick="PopupSelector.click(' + b + ",this, null,"
						+ d[b].id + ')" />');
				a.push(d[b].name);
				a.push("</label>");
				a.push("</a></li>")
			}
			jQuery("#subHeader1").show();
			jQuery("#subHeader2").show();
			jQuery("#allItems2").html(a.join("")).show()
		} else {
			jQuery("#subHeader1").hide();
			jQuery("#subHeader2").hide();
			jQuery("#allItems2").hide()
		}
		a = [];
		for ( var h = false, j = 0; j < this._curItems.length; j++)
			if (this._curItems[j].id == "999999")
				h = true;
		for ( var b = n; b < i; b++) {
			var c = d[b];
			if (c.subItems && c.subItems.length > 1) {
				if (this._curItems.has( {
					id : c.id,
					name : c.name,
					parObj : null
				}) || this._curItems.GetFather( {
					id : c.id,
					name : c.name,
					parObj : null
				}) || this._curItems.GetFaFather( {
					id : c.id,
					name : c.name,
					parObj : null
				}))
					a
							.push("<li id=jQuery"
									+ b
									+ " name="
									+ c.id
									+ ' class="layicon" onmouseover="PopupSelector.showSubItems(');
				else
					a.push("<li id=jQuery" + b + " name=" + c.id
							+ ' onmouseover="PopupSelector.showSubItems(');
				a.push(b);
				a
						.push(","
								+ c.id
								+ ', this, true)" onmouseout="PopupSelector.hideSubItems(this)">');
				a.push('<a href="javascript:void(0);">');
				a.push('<input id="pcbx');
				a.push(c.id);
				a.push('" type="' + PopupSelector._checkType + '" value="');
				a.push(c.id);
				a.push("@");
				a.push(c.name);
				a.push('"');
				if (h) {
					c.id != "999999" && a.push(' disabled="true"');
					a.push(" checked")
				} else
					this._curItems.has( {
						id : c.id,
						name : c.name,
						parObj : null
					}) && a.push(" checked");
				a.push(' onclick="PopupSelector.click(');
				a.push(b + ",this, null," + c.id + ')" />');
				a.push(dbcToSbc(c.name));
				a.push("</a></li>")
			} else {
				if (this._curItems.has( {
					id : c.id,
					name : c.name,
					parObj : null
				}))
					a.push("<li id=jQuery" + b + " name=" + c.id
							+ ' class="layon">');
				else
					a.push("<li id=jQuery" + b + " name=" + c.id
							+ ' class="nonelay">');
				a.push('<a href="javascript:void(0);">');
				a.push('<label for="pcbx');
				a.push(d[b].id);
				a.push('">');
				a.push('<input id="pcbx');
				a.push(c.id);
				a.push('" type="' + PopupSelector._checkType + '" value="');
				a.push(c.id);
				a.push("@");
				a.push(c.name);
				a.push('"');
				if (h) {
					c.id != "999999" && a.push(' disabled="true"');
					a.push(" checked")
				} else
					this._curItems.has( {
						id : c.id,
						name : c.name,
						parObj : null
					}) && a.push(" checked");
				a.push(' onclick="PopupSelector.click(');
				a.push(b + ",this, null," + c.id + ')" />');
				a.push(dbcToSbc(c.name));
				a.push("</label>");
				a.push("</a></li>")
			}
		}
		jQuery("#allItems1").html(a.join("").toString());
		a.length = 0;
		jQuery("#divSelecting").css("display",
				this._curItems.length == 0 ? "none" : "block");
		var k = this._curItems.length == 0 ? "block" : "none";
		if (this._type == "cat") {
			jQuery("#noSelectedCat").css("display", k);
			jQuery("#noSelectedLoc").hide()
		} else if (this._type == "loc") {
			jQuery("#noSelectedLoc").css("display", k);
			jQuery("#noSelectedCat").hide()
		} else {
			jQuery("#noSelectedCat").hide();
			jQuery("#noSelectedLoc").hide()
		}
		for ( var b = 0; b < this._curItems.length; b++) {
			a.push('<li id="');
			a.push(this._curItems[b].id);
			a.push('"><a href="###" onclick="PopupSelector.remove(');
			a.push(this._curItems[b].id);
			if (this._curItems[b].parObj != null)
				if (this._curItems[b].parObj.parObj != null)
					a.push("," + this._curItems[b].parObj.parObj.id + ')">');
				else
					a.push("," + this._curItems[b].parObj.id + ')">');
			else
				a.push(')">');
			var f;
			if (this._curItems[b].parObj && this._type == "loc")
				if (this._curItems[b].parObj.parObj != null) {
					c = d[parseInt(this._curItems[b].parObj.parObj)];
					if (c == "undefined" || c == null)
						c = this._curItems[b].parObj.parObj;
					if (c.name == this._curItems[b].parObj.name)
						f = this._curItems[b].parObj.name + "-"
								+ this._curItems[b].name;
					else
						f = c.name + "-" + this._curItems[b].parObj.name + "-"
								+ this._curItems[b].name
				} else
					f = this._curItems[b].parObj.name + "-"
							+ this._curItems[b].name;
			else
				f = this._curItems[b].name;
			a.push(dbcToSbc(f));
			a.push("</a></li>")
		}
		jQuery("#selecting").html(a.join(""));
		if (this._type == "ind" || this._type == "comptype") {
			jQuery("#divSelecting").show();
			jQuery("#selecting").hide()
		} else
			jQuery("#selecting").show();
		jQuery("#shield").width(this._style.width[this._type]).height(
				this._style.height[this._type]);
		jQuery(this._box).css(e).show();
		jQuery("#mask").height(jQuery(document).height()).show();
		$("#mask iframe").height(jQuery(document).height());
		$("#mask iframe").width(jQuery(document).width());
		jQuery("#pcbx30000").focus()
	},
	close : function() {
		jQuery("#mask").hide();
		this._ref = null;
		jQuery(this._box).hide();
		typeof Mask != "undefined" && Mask != null && Mask.Hide()
	},
	OK : function() {
		var a = [], h = popupLayer.selectedItems, d = this.AarearenderSelItemDiv, g = this.AarearenderSelItemEleId, e = this.AareatagPrefix, f = [], c = [], b = this;
		jQuery
				.each(
						this._curItems,
						function() {
							var h;
							if (this.parObj && b._type == "loc") {
								var i = b.allItems[b._type]();
								if (this.parObj.parObj != null) {
									parItem = i[parseInt(this.parObj.parObj)];
									if (parItem == "undefined"
											|| parItem == null)
										parItem = this.parObj.parObj;
									if (parItem.name == this.parObj.name)
										h = this.parObj.name + "-" + this.name;
									else
										h = parItem.name + "-"
												+ this.parObj.name + "-"
												+ this.name
								} else
									h = this.parObj.name + "-" + this.name
							} else
								h = this.name;
							c.push(h);
							a.push('<div class="list_m" id="' + e
									+ "_divSelItem_" + this.id + '">');
							a.push('<div class="list_c">');
							a.push(h);
							a.push("</div>");
							a
									.push('<input type="button" value="\u5220\u9664" title="\u5220\u9664"');
							a.push("onclick=\"pLayer.remove('" + e
									+ "_divSelItem_" + this.id + "','" + g
									+ "','" + d + "')\" />");
							a.push("</div>");
							f.push(this.id)
						});
		$(d).innerHTML = a.join("");
		$("#renderSelIds").val(f.join(","));
		if (this.InSalaryPage)
			if (this._curItems) {
				this._type == "loc" && this._curItems[0] != null
						&& jQuery("#hidLoc").val(this._curItems[0].id);
				this._type == "ind" && this._curItems[0] != null
						&& jQuery("#hidInd").val(this._curItems[0].id);
				this._type == "cat" && this._curItems[0] != null
						&& jQuery("#hidCat").val(this._curItems[0].id)
			}
		jQuery(this._ref).val(dbcToSbc(c.join("+")));
		this.InitClass(c, this._type);
		this._ref = null;
		this._selItems[this._type] = this._curItems.clone();
		this._curItems.clear();
		jQuery("#selecting").html("");
		jQuery("#mask").hide();
		jQuery(this._box).hide();
		typeof Mask != "undefined" && Mask != null && Mask.Hide()
	},
	empty : function() {
		jQuery("#selecting").html("");
		jQuery("#allItems1 input").each(function() {
			this.checked = false;
			this.disabled = false
		});
		jQuery("#allItems2 input").each(function() {
			this.checked = false
		});
		jQuery("#allItems1 li").each(function() {
			if (this.className == "layicon")
				this.className = "";
			if (this.className == "layon")
				this.className = "nonelay"
		});
		jQuery("#allItems2 li").each(function() {
			if (this.className == "layicon")
				this.className = "";
			if (this.className == "layon")
				this.className = "nonelay"
		});
		this._curItems.clear();
		PopupSelector.showtips()
	},
	InSalaryPage : false,
	InAdvanceSearchPage : false,
	click : function(c, e, b, o, j) {
		if (e.checked && this._curItems.length == this._maxSize) {
			alert(this._gtMaxLimit);
			e.checked = false;
			return
		}
		var d = {
			id : e.value.split("@")[0],
			name : e.value.split("@")[1],
			parObj : b
		}, i = document.getElementById("selecting"), f = null;
		if (this.InSalaryPage) {
			jQuery(".sech_layb input[type='radio']").each(function() {
				this.checked = false
			});
			jQuery("#subBox input[type='radio']").each(function() {
				this.checked = false
			});
			if (this._type != "ind") {
				var u = this._type;
				jQuery(".layon").each(
						function() {
							if (u == "loc")
								if (this.id == "jQuery1"
										|| this.id == "jQuery2"
										|| this.id == "jQuery3"
										|| this.id == "jQuery4"
										|| this.id == "jQuery32"
										|| this.id == "jQuery33"
										|| this.id == "jQuery34"
										|| this.id == "jQuery35"
										|| this.id == "jQuery36"
										|| this.id == "jQuery37"
										|| this.id == "jQuery38"
										|| this.id == "jQuery39"
										|| this.id == "jQuery40")
									this.className = "nonelay";
								else
									this.className = "";
							else if (this.id == "jQuery29"
									|| this.id == "jQuery31")
								this.className = "nonelay";
							else
								this.className = ""
						})
			} else
				jQuery(".layon").each(function() {
					this.className = "nonelay"
				});
			jQuery(".layicon").each(function() {
				this.className = ""
			});
			e.checked = true;
			f = '<a href="javascript:void(0);" onclick="PopupSelector.remove('
					+ d.id + ',null);">' + dbcToSbc(d.name) + "</a>";
			if (b != null)
				f = '<a href="javascript:void(0);" onclick="PopupSelector.remove('
						+ d.id
						+ ","
						+ b.id
						+ ');">'
						+ dbcToSbc(d.name)
						+ "</a>";
			jQuery(i).html("<li id='" + o + "'>" + f + "</li>");
			if (b != null) {
				document.getElementById("^" + c).className = "layon";
				var v = b.id;
				jQuery("li[@name=" + v + "]").addClass("layicon")
			} else
				document.getElementById("jQuery" + c).className = "layon";
			this._curItems.clear();
			this._curItems.push(d);
			this.showHideSelecting(this);
			PopupSelector.showtips();
			return
		}
		if (e.checked) {
			if (o != "999999") {
				f = document.createElement("li");
				f.id = d.id;
				if (b == null) {
					var a = this.allItems[this._type]()[c], g = this;
					jQuery(g._curItems)
							.each(
									function() {
										var d = this.id;
										itemSubstring = document
												.getElementById(d);
										if (itemSubstring != null) {
											var c = false;
											if (a.subItems
													&& a.subItems.length > 0)
												for ( var b = 0; b < a.subItems.length; b++) {
													if (a.subItems[b].id == d)
														c = true;
													a.subItems[b].subItems
															&& a.subItems[b].subItems.length > 0
															&& jQuery(
																	a.subItems[b].subItems)
																	.each(
																			function() {
																				if (this.id == d)
																					c = true
																			})
												}
											if (c == true) {
												i.removeChild(itemSubstring);
												g._curItems.remove(this.id)
											}
										}
									});
					f.innerHTML = '<a href="javascript:void(0);" onclick="PopupSelector.remove('
							+ d.id + ',null);">' + dbcToSbc(d.name) + "</a>"
				} else {
					var l;
					if (j == 3) {
						var r = this.allItems[this._type](), s = parseInt(b.parObj);
						a = r[s];
						l = a.name + "-" + b.name + "-" + d.name;
						f.innerHTML = '<a href="javascript:void(0);" onclick="PopupSelector.removelv3('
								+ d.id
								+ ","
								+ b.id
								+ ","
								+ a.id
								+ ","
								+ b.parObj + ');">' + dbcToSbc(l) + "</a>"
					} else {
						var a = this.allItems[this._type]()[b.parObj].subItems[c], g = this;
						jQuery(g._curItems).each(
								function() {
									var c = this.id;
									itemSubstring = document.getElementById(c);
									if (itemSubstring != null) {
										var b = false;
										a.subItems
												&& a.subItems.length > 0
												&& jQuery(a.subItems).each(
														function() {
															if (this.id == c)
																b = true
														});
										if (b == true) {
											i.removeChild(itemSubstring);
											g._curItems.remove(this.id)
										}
									}
								});
						if (this._type == "loc")
							l = b.name + "-" + d.name;
						else
							l = d.name;
						f.innerHTML = '<a href="javascript:void(0);" onclick="PopupSelector.remove('
								+ d.id
								+ ","
								+ b.id
								+ ","
								+ b.parObj
								+ ');">'
								+ dbcToSbc(l) + "</a>"
					}
				}
				i.appendChild(f);
				this._curItems.push(d)
			} else {
				var g = this;
				g._curItems.length > 0 && jQuery(g._curItems).each(function() {
					var b = this.id;
					itemSubstring = document.getElementById(b);
					if (itemSubstring != null) {
						var a = false;
						jQuery("#allItems2 input").each(function() {
							if (this.value.split("@")[0] == b)
								a = true
						});
						if (a != true) {
							i.removeChild(itemSubstring);
							g._curItems.remove(this.id)
						}
					}
				});
				PopupSelector.showtips();
				jQuery("#allItems1 input")
						.each(
								function() {
									if (this.id != "pcbx999999") {
										var a = null, b = null;
										a = {
											id : this.value.split("@")[0],
											name : this.value.split("@")[1],
											parObj : null
										};
										b = document.createElement("li");
										b.id = a.id;
										b.innerHTML = '<a href="javascript:void(0);" onclick="PopupSelector.remove('
												+ a.id
												+ ',null);">'
												+ dbcToSbc(a.name) + "</a>";
										i.appendChild(b);
										g._curItems.push(a)
									}
								})
			}
			this.showHideSelecting(this)
		} else {
			if (d.id == "999999") {
				var g = this;
				jQuery("#allItems1 input").each(function() {
					if (this.id != "pcbx999999") {
						var a = null;
						a = document.getElementById(this.value.split("@")[0]);
						if (a != null) {
							i.removeChild(a);
							g._curItems.remove(this.value.split("@")[0])
						}
					}
				})
			} else {
				f = document.getElementById(d.id);
				i.removeChild(f);
				this._curItems.remove(d.id)
			}
			PopupSelector.showtips()
		}
		if (b == null) {
			var m = this.allItems[this._type]();
			a = m[c];
			if (e.checked)
				if (a.subItems && a.subItems.length > 1)
					document.getElementById("jQuery" + c).className = "layicon";
				else
					document.getElementById("jQuery" + c).className = "layon";
			else if (a.subItems && a.subItems.length > 1)
				document.getElementById("jQuery" + c).className = "";
			else
				document.getElementById("jQuery" + c).className = "nonelay";
			if (o == "999999")
				jQuery("#allItems1 input")
						.each(
								function(a) {
									if (this.id != "pcbx999999")
										this.checked = this.disabled = e.checked;
									var b = m[a];
									if (e.checked)
										if (b.subItems && b.subItems.length > 1)
											document.getElementById("jQuery"
													+ a).className = "layicon";
										else
											document.getElementById("jQuery"
													+ a).className = "layon";
									else if (b.subItems
											&& b.subItems.length > 1)
										document.getElementById("jQuery" + a).className = "";
									else
										document.getElementById("jQuery" + a).className = "nonelay"
								});
			else
				try {
					if (a.subItems && a.subItems.length > 1)
						var t = this._type;
					jQuery("#subItems input").each(function(d) {
						this.checked = this.disabled = e.checked;
						var c = a.subItems[d], f = "^" + d;
						if (t != "loc")
							f = "^" + (d + 1);
						var b = document.getElementById(f);
						if (b != null)
							if (e.checked)
								if (c.subItems && c.subItems.length > 1)
									b.className = "layicon";
								else
									b.className = "layon";
							else if (c.subItems && c.subItems.length > 1)
								b.className = "";
							else
								b.className = "nonelay"
					})
				} catch (w) {
				}
		} else {
			for ( var m = this.allItems[this._type](), n, h = 0; h < m.length; h++)
				if (m[h].id == b.id) {
					a = m[h];
					n = h;
					break
				}
			if (e.checked) {
				if (a.subItems[c].subItems && a.subItems[c].subItems.length > 1)
					document.getElementById("^" + c).className = "layicon";
				else
					document.getElementById("^" + c).className = "layon";
				if (j == 2)
					document.getElementById("jQuery" + n).className = "layicon";
				else if (j == 3) {
					document.getElementById("jQuery" + b.parObj).className = "layicon";
					document.getElementById(b.tid).className = "layon"
				}
			} else if (j == 2) {
				if (!this._curItems.GetFather( {
					id : b.id,
					name : null,
					parObj : null
				}))
					document.getElementById("jQuery" + n).className = "layshow";
				if (a.subItems[c].subItems && a.subItems[c].subItems.length > 1)
					document.getElementById("^" + c).className = "";
				else
					document.getElementById("^" + c).className = "nonelay"
			} else if (j == 3) {
				for ( var q = false, p = this.allItems[this._type]()[b.parObj].subItems, h = 0; h < p.length; h++)
					for ( var k = 0; k < this._curItems.length; k++) {
						if (this._curItems[k].id != null
								&& this._curItems[k].id == p[h].id)
							q = true;
						if (this._curItems[k].parObj != null
								&& this._curItems[k].parObj.id == p[h].id)
							q = true
					}
				if (q == false)
					document.getElementById("jQuery" + b.parObj).className = "layshow";
				if (!this._curItems.GetFather( {
					id : b.id,
					name : null,
					parObj : null
				}))
					document.getElementById("^" + c).className = "layshow";
				document.getElementById(b.tid).className = "nonelay"
			}
			if (j == "2") {
				jQuery("#thirdItems input").each(function() {
					this.checked = this.disabled = e.checked
				});
				if (e.checked)
					jQuery("#thirdItems li").each(function() {
						this.className = "layon"
					});
				else
					jQuery("#thirdItems li").each(function() {
						this.className = "nonelay"
					})
			}
		}
	},
	showtips : function() {
		if (this._curItems.length == 0) {
			this._type != "ind"
					&& jQuery("#divSelecting").css("display", "none");
			if (this._type == "cat") {
				jQuery("#noSelectedLoc").hide();
				this.InSalaryPage
						&& jQuery("#noSelectedCat p")
								.html(
										jQuery("#noSelectedCat p")
												.html()
												.replace(
														"\u60a8\u6700\u591a\u53ef\u4ee5\u9009\u62e95\u4e2a\u804c\u4f4d\u7c7b\u522b",
														"&nbsp;"));
				jQuery("#noSelectedCat").show()
			}
			if (this._type == "loc") {
				jQuery("#noSelectedCat").hide();
				this.InSalaryPage
						&& jQuery("#noSelectedLoc p")
								.html(
										jQuery("#noSelectedLoc p")
												.html()
												.replace(
														"\u60a8\u6700\u591a\u53ef\u4ee5\u9009\u62e95\u4e2a\u5de5\u4f5c\u5730\u70b9",
														""));
				jQuery("#noSelectedLoc").show()
			}
		}
	},
	remove : function(b, d, i) {
		document.getElementById("selecting").removeChild(
				document.getElementById(b));
		var j = document.getElementById("pcbx" + b);
		if (j)
			j.checked = false;
		var g = this._curItems.clone();
		this._curItems.clear();
		for ( var a = 0; a < g.length; a++)
			g[a].id != b && this._curItems.push(g[a]);
		if (d == null) {
			jQuery("li[@name=" + b + "]").removeClass("layicon");
			jQuery("li[@name=" + b + "]").removeClass("layon");
			for ( var a = 32; a < this.allItems[this._type]().length; a++)
				if (this.allItems[this._type]()[a].id == b
						&& this.allItems[this._type]()[a].subItems.length <= 1) {
					jQuery("li[@name=" + b + "]").addClass("nonelay");
					break
				}
		} else {
			var h = false;
			if (i != undefined)
				for ( var f = this.allItems[this._type]()[i].subItems, a = 0; a < f.length; a++)
					for ( var c = 0; c < this._curItems.length; c++) {
						if (this._curItems[c].id != null
								&& this._curItems[c].id == f[a].id)
							h = true;
						if (this._curItems[c].parObj != null
								&& this._curItems[c].parObj.id == f[a].id)
							h = true
					}
			if (h == false)
				!this._curItems.GetFather( {
					id : d,
					name : null,
					parObj : null
				}) && !this._curItems.GetFaFather( {
					id : d,
					name : null,
					parObj : null
				}) && jQuery("li[@name=" + d + "]").removeClass("layicon")
		}
		var k = jQuery("#allItems1 li[@name=" + b + "]");
		if (k.length > 0) {
			var e = document.getElementById("pcbx999999");
			if (e != null && e.checked) {
				jQuery("#allItems1 input").each(function() {
					this.disabled = false
				});
				e.checked = false;
				jQuery("li[@name=999999]").attr("class", "nonelay")
			}
		}
		PopupSelector.showtips()
	},
	removelv3 : function(c, h, i, j) {
		document.getElementById("selecting").removeChild(
				document.getElementById(c));
		var g = document.getElementById("pcbx" + c);
		if (g)
			g.checked = false;
		var e = this._curItems.clone();
		this._curItems.clear();
		for ( var a = 0; a < e.length; a++)
			e[a].id != c && this._curItems.push(e[a]);
		if (h == null) {
			jQuery("li[@name=" + c + "]").removeClass("layicon");
			jQuery("li[@name=" + c + "]").removeClass("layon")
		} else {
			for ( var f = false, d = this.allItems[this._type]()[j].subItems, a = 0; a < d.length; a++)
				for ( var b = 0; b < this._curItems.length; b++) {
					if (this._curItems[b].id != null
							&& this._curItems[b].id == d[a].id)
						f = true;
					if (this._curItems[b].parObj != null
							&& this._curItems[b].parObj.id == d[a].id)
						f = true
				}
			f == false && jQuery("li[@name=" + i + "]").removeClass("layicon")
		}
		PopupSelector.showtips()
	},
	showHideSelecting : function(a) {
		jQuery("#noSelectedCat").hide();
		jQuery("#noSelectedLoc").hide();
		a._curItems.length > 0 && jQuery("#divSelecting").show()
	},
	showSubItems : function(b, e, c, f) {
		console.info("1245");
		var a = jQuery("#subItems");
		this._hideTimer && clearTimeout(this._hideTimer);
		this._showTimer && clearTimeout(this._showTimer);
		if (b == this._lastPopupIndex && a.css("display") == "block") {
			jQuery(c).addClass("layshow");
			return
		}
		!f && d(b, c, this);
		var g = this;
		this._showTimer = setTimeout(function() {
			d(b, c, g)
		}, this._delay);
		function d(h, k, c) {
			var f = c.allItems[c._type]()[h], o = jQuery(k).offset(), i = {
				top : o.top + c._style.offset.levelTwo[c._type].Y,
				left : o.left + c._style.offset.levelTwo[c._type].X
			};
			if (document.body.clientWidth < i.left + 296)
				i.left = o.left - 296;
			var j = f.subItems.length - 1, p = j % 2 == 0 ? j / 2 : j / 2 + 1;
			if (j <= c._oneColumnLimit[c._type])
				p = j;
			var n = c._style.lineHeight * parseInt(p)
					+ c._style.topBottomMargin, s = jQuery(window).height(), m;
			if (jQuery.browser.msie || jQuery.browser.mozilla)
				m = document.body.scrollTop;
			else
				m = document.documentElement.scrollTop;
			var q = s - i.top + m;
			if (q < n)
				i.top = i.top - (n - q);
			if (n > s)
				i.top = m;
			var l = k.getElementsByTagName("input")[0].checked == true ? " checked disabled "
					: "", v = {
				id : f.id,
				name : f.name,
				parObj : null
			}, b = [];
			b.push("<ol >");
			var r = 1;
			if (c._type == "loc")
				r = 0;
			for ( var g = r; g < f.subItems.length; g++) {
				var d = f.subItems[g];
				d.parObj = v;
				if (k.getElementsByTagName("input")[0].checked == true
						|| c._curItems.has(d) || c._curItems.GetFather(d))
					if (f.subItems[g].subItems
							&& f.subItems[g].subItems.length > 0) {
						b
								.push("<li id=^"
										+ g
										+ " name="
										+ f.id
										+ ' class="layicon" onmouseover="PopupSelector.showThirdItems(');
						b.push(h);
						b.push(",");
						b.push(g);
						b
								.push(', this, true)" onmouseout="PopupSelector.hideThirdItems(this)">');
						b.push('<a href="javascript:void(0);">');
						b.push('<input id="scbx');
						b.push(d.id);
						b
								.push('" type="' + PopupSelector._checkType + '" value="');
						b.push(d.id);
						b.push("@");
						b.push(d.name);
						b.push('"');
						b.push(l);
						c._curItems.has(d) && b.push(" checked");
						b.push(' onclick="PopupSelector.click(' + g
								+ ",this, { id: ");
						b.push(f.id);
						b.push(", name: '");
						b.push(f.name);
						b.push("', parObj: " + h + " }");
						b.push(",'','2')\" />");
						b.push(dbcToSbc(d.name));
						b.push("</a></li>")
					} else {
						b
								.push("<li id=^"
										+ g
										+ " name="
										+ f.id
										+ " class=\"layon\" onmouseover='PopupSelector.clearThirdHidden()' onmouseover='PopupSelector.hideThirdItems(this)'>");
						b
								.push('<a href="javascript:void(0);"><label for="scbx');
						b.push(d.id);
						b.push('">');
						b.push('<input id="scbx');
						b.push(d.id);
						b
								.push('" type="' + PopupSelector._checkType + '" value="');
						b.push(d.id);
						b.push("@");
						b.push(d.name);
						b.push('"');
						b.push(l);
						c._curItems.has(d) && b.push(" checked");
						b.push(' onclick="PopupSelector.click(' + g
								+ ",this, { id: ");
						b.push(f.id);
						b.push(", name: '");
						b.push(f.name);
						b.push("', parObj: " + h + " }");
						b.push(",'','2')\" />");
						b.push(dbcToSbc(d.name));
						b.push("</label></a></li>")
					}
				else if (f.subItems[g].subItems
						&& f.subItems[g].subItems.length > 0) {
					b.push("<li id=^" + g + " name=" + f.id
							+ ' onmouseover="PopupSelector.showThirdItems(');
					b.push(h);
					b.push(",");
					b.push(g);
					b
							.push(', this, true)" onmouseout="PopupSelector.hideThirdItems(this)">');
					b.push('<a href="javascript:void(0);">');
					b.push('<input id="scbx');
					b.push(d.id);
					b.push('" type="' + PopupSelector._checkType + '" value="');
					b.push(d.id);
					b.push("@");
					b.push(d.name);
					b.push('"');
					b.push(l);
					c._curItems.has(d) && b.push(" checked");
					b.push(' onclick="PopupSelector.click(' + g
							+ ",this, { id: ");
					b.push(f.id);
					b.push(", name: '");
					b.push(f.name);
					b.push("', parObj: " + h + " }");
					b.push(",'','2')\" />");
					b.push(dbcToSbc(d.name));
					b.push("</a></li>")
				} else {
					b
							.push("<li id=^"
									+ g
									+ " name="
									+ f.id
									+ " class=\"nonelay\" onmouseover='PopupSelector.clearThirdHidden()' onmouseover='PopupSelector.hideThirdItems(this)'>");
					b.push('<a href="javascript:void(0);"><label for="scbx');
					b.push(d.id);
					b.push('">');
					b.push('<input id="scbx');
					b.push(d.id);
					b.push('" type="' + PopupSelector._checkType + '" value="');
					b.push(d.id);
					b.push("@");
					b.push(d.name);
					b.push('"');
					b.push(l);
					c._curItems.has(d) && b.push(" checked");
					b.push(' onclick="PopupSelector.click(' + g
							+ ",this, { id: ");
					b.push(f.id);
					b.push(", name: '");
					b.push(f.name);
					b.push("', parObj: " + h + " }");
					b.push(",'','2')\" />");
					b.push(dbcToSbc(d.name));
					b.push("</label></a></li>")
				}
			}
			b.push("</ol>");
			for ( var u = jQuery("#subBox"), g = 0; g < c._types.length; g++) {
				a.removeClass(c._style.className.levelTwo1[c._types[g]]);
				a.removeClass(c._style.className.levelTwo2[c._types[g]])
			}
			var t = j > c._oneColumnLimit[c._type] ? c._style.className.levelTwo2[c._type]
					: c._style.className.levelTwo1[c._type];
			if (c._type == "loc")
				if (f.id == "8000" || f.id == "15000")
					t = c._style.className.levelTwo1[c._type];
			c._lastPopupIndex = h;
			jQuery("#subItems").hover(function(a) {
				c.showSubItems(h, e, k, true, a)
			}, function() {
				c.hideSubItems(k)
			});
			u.html(b.join(""));
			a.addClass(t).css(i).show()
		}
	},
	hideSubItems : function(a) {
		jQuery(a).removeClass("layshow");
		this._showTimer && clearTimeout(this._showTimer);
		this._hideTimer && clearTimeout(this._hideTimer);
		this._hideTimer = setTimeout(function() {
			jQuery("#subItems").hide()
		}, 100)
	},
	clearThirdHidden : function() {
		this._showThirdTimer && clearTimeout(this._showThirdTimer);
		this._hideThirdTimer && clearTimeout(this._hideThirdTimer);
		jQuery("#thirdItems").hide()
	},
	showThirdItems : function(c, b, d, f) {
		var a = jQuery("#thirdItems");
		this._showThirdTimer && clearTimeout(this._showThirdTimer);
		this._hideThirdTimer && clearTimeout(this._hideThirdTimer);
		if (b + 50 == this._lastPopupIndex && a.css("display") == "block") {
			jQuery("#jQuery" + c).addClass("layshow");
			jQuery(d).addClass("layshow");
			return
		}
		!f && e(c, b, d, this);
		var g = this;
		this._showThirdTimer = setTimeout(function() {
			e(c, b, d, g)
		}, this._delay);
		function e(n, i, k, b) {
			var e = b.allItems[b._type]()[n].subItems[i], m = jQuery(k)
					.offset(), g = {
				top : m.top + b._style.offset.levelTwo[b._type].Y,
				left : m.left + b._style.offset.levelTwo[b._type].X
			};
			if (document.body.clientWidth < g.left + 296)
				g.left = m.left - 296;
			var h = e.subItems.length - 1, o = h % 2 == 0 ? h / 2 : h / 2 + 1;
			if (h <= b._oneColumnLimit[b._type])
				o = h;
			var l = b._style.lineHeight * parseInt(o)
					+ b._style.topBottomMargin, q = jQuery(window).height(), j;
			if (jQuery.browser.msie || jQuery.browser.mozilla)
				j = document.body.scrollTop;
			else
				j = document.documentElement.scrollTop;
			var p = q - g.top + j;
			if (p < l)
				g.top = g.top - (l - p);
			if (l > q)
				g.top = j;
			var s = k.getElementsByTagName("input")[0].checked == true ? " checked disabled "
					: "", u = {
				id : e.id,
				name : e.name,
				parObj : null
			}, c = [];
			c.push("<ol >");
			for ( var d = 0; d < e.subItems.length; d++) {
				var f = e.subItems[d];
				f.parObj = u;
				if (k.getElementsByTagName("input")[0].checked == true
						|| b._curItems.has(f))
					c.push("<li id=Q" + d + ' class="layon" >');
				else
					c.push("<li id=Q" + d + ' class="nonelay" >');
				c.push('<a href="javascript:void(0);"><label for="tcbx');
				c.push(f.id);
				c.push('"><input id="tcbx');
				c.push(f.id);
				c.push('" type="checkbox" value="');
				c.push(f.id);
				c.push("@");
				c.push(f.name);
				c.push('"');
				c.push(s);
				b._curItems.has(f) && c.push(" checked");
				c.push(' onclick="PopupSelector.click(' + i + ",this, { id: ");
				c.push(e.id);
				c.push(", name: '");
				c.push(e.name);
				c.push("', parObj: " + n + ",tid:'Q" + d + "'}");
				c.push(",'','3')\" />");
				c.push(addSpan(f.name));
				c.push("</label></a></li>")
			}
			c.push("</ol>");
			for ( var t = jQuery("#thirdBox"), d = 0; d < b._types.length; d++) {
				a.removeClass(b._style.className.levelThree1[b._types[d]]);
				a.removeClass(b._style.className.levelThree2[b._types[d]])
			}
			var r = h > b._oneColumnLimit[b._type] ? b._style.className.levelThree2[b._type]
					: b._style.className.levelThree1[b._type];
			if (b._type == "loc")
				if (e.id == "8000" || e.id == "15000")
					r = b._style.className.levelThree1[b._type];
			b._lastPopupIndex = i + 50;
			jQuery("#thirdItems").hover(function(a) {
				b._hideTimer && clearTimeout(b._hideTimer);
				b._hideThirdTimer && clearTimeout(b._hideThirdTimer);
				b.showThirdItems(n, i, k, true, a)
			}, function() {
				b.hideThirdItems()
			});
			t.html(c.join(""));
			a.css("display", "block");
			a.addClass(r).css(g).show()
		}
	},
	hideThirdItems : function(a) {
		jQuery(a).removeClass("layshow");
		this._showTimer && clearTimeout(this._showTimer);
		this._hideTimer && clearTimeout(this._hideTimer);
		this._showThirdTimer && clearTimeout(this._showThirdTimer);
		this._hideThirdTimer && clearTimeout(this._hideThirdTimer);
		this._hideThirdTimer = setTimeout(function() {
			jQuery("#thirdItems").hide();
			jQuery("#subItems").hide()
		}, 100)
	},
	_showTimer : null,
	_hideTimer : null,
	_showThirdTimer : null,
	_hideThirdTimer : null,
	_lastPopupIndex : null,
	_box : null,
	_subbox : null,
	_ref : null,
	_type : null,
	_types : [ "cat", "ind", "loc", "comptype" ],
	_maxSize : 5,
	_checkType : "checkbox",
	_curItems : [],
	_selItems : {
		cat : [],
		loc : [],
		ind : [],
		comptype : []
	},
	_gtMaxLimit : "\u5bf9\u4e0d\u8d77,\u60a8\u7684\u5df2\u9009\u9879\u5df2\u7ecf\u8fbe\u5230\u4e865\u9879.\u8bf7\u51cf\u5c11\u5df2\u9009\u9879,\u518d\u7ee7\u7eed\u9009\u62e9",
	_gtYourSelected : {
		cat : "\u60a8\u9009\u62e9\u7684\u804c\u4f4d\u7c7b\u522b\u662f\uff1a",
		ind : "\u60a8\u6700\u591a\u53ef\u4ee5\u9009\u62e9\uff15\u4e2a\u884c\u4e1a\u7c7b\u522b",
		loc : "\u60a8\u9009\u62e9\u7684\u5730\u533a\u662f",
		comptype : "\u60a8\u9009\u62e9\u7684\u516c\u53f8\u6027\u8d28\u662f\uff1a"
	},
	_gtPopupSelectorHeader : {
		cat : "\u804c\u4f4d\u7c7b\u522b",
		ind : "\u884c\u4e1a\u7c7b\u522b",
		loc : "\u8bf7\u9009\u62e9\u5730\u533a",
		comptype : "\u516c\u53f8\u6027\u8d28"
	},
	_oneColumnLimit : {
		cat : 12,
		loc : 11
	},
	_delay : 500,
	_style : {
		className : {
			levelOne : {
				cat : "lay_wl",
				loc : "lay_wls",
				ind : "lay_wll",
				comptype : "lay_wms"
			},
			levelTwo1 : {
				cat : "lay_wm",
				loc : "lay_ws lm",
				ind : "",
				comptype : ""
			},
			levelTwo2 : {
				cat : "lay_wl2",
				loc : "lay_ws lm",
				ind : "",
				comptype : ""
			},
			levelThree1 : {
				cat : "lay_wm",
				loc : "lay_ws",
				ind : "",
				comptype : ""
			},
			levelThree2 : {
				cat : "lay_wl2",
				loc : "lay_ws",
				ind : "",
				comptype : ""
			}
		},
		left : 0,
		top : 0,
		width : {
			cat : 594,
			loc : 466,
			ind : 746,
			comptype : 360
		},
		height : {
			cat : 517,
			loc : 419,
			ind : 510,
			comptype : 173
		},
		lineHeight : 20,
		topBottomMargin : 17,
		offset : {
			levelOne : {
				X : 0,
				Y : 20,
				cat : {
					X : 0,
					Y : 20
				},
				loc : {
					X : 250,
					Y : 20
				}
			},
			levelTwo : {
				cat : {
					X : 304,
					Y : 0
				},
				loc : {
					X : 130,
					Y : 0
				}
			}
		}
	},
	allItems : {
		cat : function() {
			return getCat()
		},
		ind : function() {
			return getInd()
		},
		loc : function() {
			return getLoc()
		},
		comptype : function() {
			return getCompanyType()
		}
	}
};
jQuery(document).ready(function() {

	if (typeof PopCheckType_Cat != "undefined" && PopCheckType_Cat != null) {
		jQuery("#txtCat").click(function() {
			PopupSelector.popup("cat", this, PopCheckType_Cat)
		});
		jQuery("#txtInd").click(function() {
			PopupSelector.popup("ind", this, PopCheckType_Ind)
		});
		jQuery("#txtLoc").click(function() {
			PopupSelector.popup("loc", this, PopCheckType_Loc)
			console.info("txtLoc click");
		});
		jQuery("#txtCatDrop").click(function() {
			PopupSelector.popup("cat", jQuery("#txtCat")[0], PopCheckType_Cat)
		});
		jQuery("#txtIndDrop").click(function() {
			PopupSelector.popup("ind", jQuery("#txtInd")[0], PopCheckType_Ind)
		});
		jQuery("#txtLocDrop").click(function() {
			PopupSelector.popup("loc", jQuery("#txtLoc")[0], PopCheckType_Loc)
		})
	} else {
		jQuery("#txtCat").click(function() {
			PopupSelector.popup("cat", this)
		});
		jQuery("#txtInd").click(function() {
			PopupSelector.popup("ind", this)
		});
		jQuery("#txtLoc").click(function() {
			PopupSelector.popup("loc", this);
			//解决关闭后再打开不显示的问题
			$("#pslayer").show();
		});
		jQuery("#txtCatDrop").click(function() {
			PopupSelector.popup("cat", jQuery("#txtCat")[0])
		});
		jQuery("#txtIndDrop").click(function() {
			PopupSelector.popup("ind", jQuery("#txtInd")[0])
		});
		jQuery("#txtLocDrop").click(function() {
			PopupSelector.popup("loc", jQuery("#txtLoc")[0])
		})
	}
	popupLayer = $(".sech_layb");
	jQuery("#lnkEmpty").click(function() {
		PopupSelector.empty()
	});
	jQuery("#lnkCancel").click(function() {
		PopupSelector.cancel()
	});
	jQuery("#lnkOK").click(function() {
		PopupSelector.OK()
	});
	jQuery("#btnOk").click(function() {
		PopupSelector.OK()
	});
	jQuery("#btnOkLoc").click(function() {
		PopupSelector.OK()
	});
	jQuery("#imgClose").click(function() {
		PopupSelector.close();
	})
});