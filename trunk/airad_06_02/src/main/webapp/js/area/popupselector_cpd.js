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
			PopupSelector.popup("loc", this)
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
		PopupSelector.close()
	})
});
function getJobType() {
	return [ {
		id : "1",
		name : "\u5168\u804c"
	}, {
		id : "2",
		name : "\u517c\u804c"
	}, {
		id : "4",
		name : "\u4e34\u65f6"
	}, {
		id : "8",
		name : "\u5b9e\u4e60"
	} ]
}
function getPostDate() {
	return [ {
		id : "1",
		name : "\u5f53\u5929"
	}, {
		id : "3",
		name : "3\u5929"
	}, {
		id : "7",
		name : "7\u5929"
	}, {
		id : "15",
		name : "15\u5929"
	}, {
		id : "30",
		name : "30\u5929"
	}, {
		id : "60",
		name : "60\u5929"
	}, {
		id : "90",
		name : "90\u5929"
	} ]
}
function getWorkExp() {
	return [ {
		id : "0",
		name : "\u4e00\u5e74\u4ee5\u4e0b"
	}, {
		id : "1",
		name : "1-2\u5e74"
	}, {
		id : "4",
		name : "3-5\u5e74"
	}, {
		id : "6",
		name : "6-7\u5e74"
	}, {
		id : "8",
		name : "8-10\u5e74\u53ca\u4ee5\u4e0a"
	} ]
}
function getCompanyType() {
	return [ {
		id : "10",
		name : "\u5916\u5546\u72ec\u8d44\u00b7\u5916\u4f01\u529e\u4e8b\u5904"
	}, {
		id : "20",
		name : "\u4e2d\u5916\u5408\u8425(\u5408\u8d44\u00b7\u5408\u4f5c)"
	}, {
		id : "21",
		name : "\u80a1\u4efd\u5236\u4f01\u4e1a"
	}, {
		id : "50",
		name : "\u56fd\u5185\u4e0a\u5e02\u516c\u53f8"
	}, {
		id : "30",
		name : "\u79c1\u8425\u00b7\u6c11\u8425\u4f01\u4e1a"
	}, {
		id : "40",
		name : "\u56fd\u6709\u4f01\u4e1a"
	}, {
		id : "60",
		name : "\u653f\u5e9c\u673a\u5173/\u975e\u8425\u5229\u673a\u6784"
	}, {
		id : "70",
		name : "\u4e8b\u4e1a\u5355\u4f4d"
	} ]
}
function getCompanySize() {
	return [ {
		id : "1",
		name : "1-49\u4eba"
	}, {
		id : "50",
		name : "50-99\u4eba"
	}, {
		id : "100",
		name : "100-499\u4eba"
	}, {
		id : "500",
		name : "500-999\u4eba"
	}, {
		id : "1000",
		name : "1000\u4eba\u4ee5\u4e0a"
	} ]
}
function getDegree() {
	return [ {
		id : "6",
		name : "\u535a\u58eb"
	}, {
		id : "5",
		name : "MBA"
	}, {
		id : "4",
		name : "\u7855\u58eb"
	}, {
		id : "3",
		name : "\u672c\u79d1"
	}, {
		id : "2",
		name : "\u5927\u4e13"
	}, {
		id : "1",
		name : "\u4e2d\u4e13"
	}, {
		id : "-5",
		name : "\u4e2d\u6280"
	}, {
		id : "-10",
		name : "\u9ad8\u4e2d"
	}, {
		id : "-15",
		name : "\u521d\u4e2d"
	} ]
}
function getSalary() {
	return [ {
		id : "600",
		name : "1000\u4ee5\u4e0b"
	}, {
		id : "700",
		name : "1000\uff5e1999"
	}, {
		id : "800",
		name : "2000\uff5e2999"
	}, {
		id : "900",
		name : "3000\uff5e3999"
	}, {
		id : "1000",
		name : "4000\uff5e5999"
	}, {
		id : "1100",
		name : "6000\uff5e7999"
	}, {
		id : "1200",
		name : "8000\uff5e9999"
	}, {
		id : "1300",
		name : "10000\uff5e14999"
	}, {
		id : "1400",
		name : "15000\uff5e19999"
	}, {
		id : "1500",
		name : "20000\uff5e29999"
	}, {
		id : "1600",
		name : "30000\uff5e49999"
	}, {
		id : "1700",
		name : "50000+"
	} ]
}
function getCat() {
	return [
			{
				id : "600",
				name : "\u8ba1\u7b97\u673a\u2022\u7f51\u7edc\u2022\u6280\u672f\u7c7b",
				subItems : [
						{
							id : "600",
							name : "\u8ba1\u7b97\u673a\u2022\u7f51\u7edc\u2022\u6280\u672f\u7c7b"
						},
						{
							id : "1001015",
							name : "\u9996\u5e2d\u6280\u672f\u5b98CTO\u2022\u9996\u5e2d\u4fe1\u606f\u5b98CIO"
						},
						{
							id : "1001028",
							name : "\u6280\u672f\u603b\u76d1\u2022\u6280\u672f\u7ecf\u7406"
						},
						{
							id : "1001029",
							name : "\u4fe1\u606f\u6280\u672f\u7ecf\u7406\u2022\u4fe1\u606f\u6280\u672f\u4e3b\u7ba1"
						},
						{
							id : "1001026",
							name : "\u4fe1\u606f\u6280\u672f\u4e13\u5458"
						},
						{
							id : "1001003",
							name : "\u4ea7\u54c1\u7ecf\u7406"
						},
						{
							id : "1001016",
							name : "\u9879\u76ee\u7ecf\u7406\u2022\u9879\u76ee\u4e3b\u7ba1"
						},
						{
							id : "1001017",
							name : "\u9879\u76ee\u6267\u884c\u2022\u534f\u8c03\u4eba\u5458"
						},
						{
							id : "1001129",
							name : "\u67b6\u6784\u5e08"
						},
						{
							id : "608",
							name : "\u7cfb\u7edf\u5206\u6790\u5458"
						},
						{
							id : "1001014",
							name : "\u7814\u53d1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001018",
							name : "\u9ad8\u7ea7\u8f6f\u4ef6\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001035",
							name : "\u8f6f\u4ef6\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001019",
							name : "\u4e92\u8054\u7f51\u8f6f\u4ef6\u5f00\u53d1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001020",
							name : "\u9ad8\u7ea7\u786c\u4ef6\u5de5\u7a0b\u5e08"
						},
						{
							id : "603",
							name : "\u7cfb\u7edf\u96c6\u6210\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001034",
							name : "\u786c\u4ef6\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001021",
							name : "\u901a\u4fe1\u6280\u672f\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001128",
							name : "\u5de5\u7a0b\u4e0e\u9879\u76ee\u5b9e\u65bd"
						},
						{
							id : "1001009",
							name : "ERP\u6280\u672f\u5e94\u7528\u987e\u95ee\u2022ERP\u5b9e\u65bd\u5de5\u7a0b\u5e08"
						},
						{
							id : "610",
							name : "\u6570\u636e\u5e93\u7ba1\u7406\u5458\u2022\u6570\u636e\u5e93\u5f00\u53d1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001022",
							name : "\u6280\u672f\u652f\u6301\u2022\u7ef4\u62a4\u7ecf\u7406"
						},
						{
							id : "1001030",
							name : "\u6280\u672f\u652f\u6301\u2022\u7ef4\u62a4\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001023",
							name : "\u54c1\u8d28\u7ecf\u7406\u2022\u8d28\u91cf\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001126",
							name : "\u7f51\u7edc\u4e0e\u4fe1\u606f\u5b89\u5168\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001004",
							name : "\u8f6f\u4ef6\u6d4b\u8bd5"
						},
						{
							id : "1001005",
							name : "\u786c\u4ef6\u6d4b\u8bd5"
						},
						{
							id : "1001013",
							name : "\u6587\u6863\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001031",
							name : "\u7cfb\u7edf\u7ba1\u7406\u5458\u2022\u7f51\u7ba1"
						},
						{
							id : "605",
							name : " \u8bed\u97f3\u2022\u89c6\u9891\u2022\u591a\u5a92\u4f53\u5f00\u53d1"
						},
						{
							id : "1001008",
							name : "\u6e38\u620f\u8bbe\u8ba1\u4e0e\u5f00\u53d1"
						},
						{
							id : "1001024",
							name : "\u7f51\u7ad9\u8425\u8fd0\u7ba1\u7406"
						},
						{
							id : "1001011",
							name : "\u7f51\u7ad9\u7f16\u8f91"
						},
						{
							id : "1001010",
							name : "\u7f51\u7ad9\u7b56\u5212"
						},
						{
							id : "1001033",
							name : "\u7f51\u7edc\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001032",
							name : "UI\u8bbe\u8ba1\u2022\u7f51\u9875\u8bbe\u8ba1\u4e0e\u5236\u4f5c"
						},
						{
							id : "1001127",
							name : "\u8ba1\u7b97\u673a\u8f85\u52a9\u8bbe\u8ba1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1001999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u8ba1\u7b97\u673a\u2022\u7f51\u7edc\u2022\u6280\u672f\u7c7b)"
						} ]
			},
			{
				id : "1035000",
				name : "\u884c\u653f\u2022\u540e\u52e4\u7c7b",
				subItems : [
						{
							id : "1035000",
							name : "\u884c\u653f\u2022\u540e\u52e4\u7c7b"
						},
						{
							id : "1035001",
							name : "\u884c\u653f\u603b\u76d1"
						},
						{
							id : "1035002",
							name : "\u884c\u653f\u7ecf\u7406\u2022\u884c\u653f\u4e3b\u7ba1\u2022\u529e\u516c\u5ba4\u4e3b\u4efb"
						},
						{
							id : "1035003",
							name : "\u884c\u653f\u4e13\u5458\u2022\u52a9\u7406"
						},
						{
							id : "1035008",
							name : "\u540e\u52e4"
						},
						{
							id : "1035004",
							name : "\u7ecf\u7406\u52a9\u7406\u2022\u79d8\u4e66\u2022\u6587\u5458"
						},
						{
							id : "1035005",
							name : "\u524d\u53f0\u2022\u603b\u673a\u2022\u63a5\u5f85"
						},
						{
							id : "1035006",
							name : "\u56fe\u4e66\u60c5\u62a5\u2022\u8d44\u6599\u2022\u6587\u6863\u7ba1\u7406"
						},
						{
							id : "1035007",
							name : "\u7535\u8111\u64cd\u4f5c\u5458\u2022\u6253\u5b57\u5458"
						},
						{
							id : "1035999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u884c\u653f\u2022\u540e\u52e4\u7c7b)"
						} ]
			},
			{
				id : "700",
				name : "\u7535\u5b50\u2022\u7535\u5668\u2022\u901a\u4fe1\u6280\u672f\u7c7b",
				subItems : [
						{
							id : "700",
							name : "\u7535\u5b50\u2022\u7535\u5668\u2022\u901a\u4fe1\u6280\u672f\u7c7b"
						},
						{
							id : "1002001",
							name : "\u7535\u5b50\u5de5\u7a0b\u5e08\u2022\u7535\u8def\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002002",
							name : "\u7535\u5668\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002003",
							name : "\u7535\u4fe1\u5de5\u7a0b\u5e08\u2022\u901a\u8baf\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002026",
							name : "\u79fb\u52a8\u901a\u4fe1\u5de5\u7a0b\u5e08\u2022\u65e0\u7ebf\u901a\u4fe1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002027",
							name : "\u6709\u7ebf\u4f20\u8f93\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002028",
							name : "\u589e\u503c\u4ea7\u54c1\u7814\u53d1"
						},
						{
							id : "1002029",
							name : "\u901a\u4fe1\u7535\u6e90\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002030",
							name : "\u6570\u636e\u901a\u4fe1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002015",
							name : "\u5de5\u827a\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002004",
							name : "\u7535\u58f0\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002005",
							name : "\u6570\u7801\u4ea7\u54c1\u5f00\u53d1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002024",
							name : "\u5d4c\u5165\u5f0f\u7cfb\u7edf\u8f6f\u4ef6\u5f00\u53d1"
						},
						{
							id : "1002007",
							name : "\u65e0\u7ebf\u7535\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002008",
							name : "\u534a\u5bfc\u4f53\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002009",
							name : "\u7535\u5b50\u5143\u5668\u4ef6\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002010",
							name : "\u7535\u5b50\u2022\u7535\u5668\u7ef4\u4fee"
						},
						{
							id : "1002012",
							name : "\u5149\u6e90\u4e0e\u7167\u660e\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002031",
							name : "\u6fc0\u5149\u2022\u5149\u7535\u5b50\u6280\u672f"
						},
						{
							id : "1002016",
							name : "\u96c6\u6210\u7535\u8def(IC)\u82af\u7247\u5f00\u53d1"
						},
						{
							id : "1002032",
							name : "\u7248\u56fe\u8bbe\u8ba1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002014",
							name : "\u6280\u672f\u6587\u6863\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002013",
							name : "\u6d4b\u8bd5\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002021",
							name : "\u673a\u68b0\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002022",
							name : "\u7535\u6c14\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002033",
							name : "\u7535\u6c60\u2022\u7535\u6e90\u5f00\u53d1"
						},
						{
							id : "1002011",
							name : "\u7814\u53d1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1002025",
							name : "\u5de5\u7a0b\u4e0e\u9879\u76ee\u5b9e\u65bd"
						},
						{
							id : "1002034",
							name : "\u73b0\u573a\u5e94\u7528\u5de5\u7a0b\u5e08\u2022FAE"
						},
						{
							id : "1002999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u7535\u5b50\u2022\u7535\u5668\u2022\u901a\u4fe1\u6280\u672f\u7c7b)"
						} ]
			},
			{
				id : "1016001",
				name : "\u7ffb\u8bd1\u7c7b",
				subItems : [
						{
							id : "1016001",
							name : "\u7ffb\u8bd1\u7c7b"
						},
						{
							id : "1801",
							name : "\u82f1\u8bed"
						},
						{
							id : "1802",
							name : "\u65e5\u8bed"
						},
						{
							id : "1803",
							name : "\u6cd5\u8bed"
						},
						{
							id : "1804",
							name : "\u5fb7\u8bed"
						},
						{
							id : "1805",
							name : "\u4fc4\u8bed"
						},
						{
							id : "1806",
							name : "\u897f\u73ed\u7259\u8bed"
						},
						{
							id : "1807",
							name : "\u97e9\u8bed"
						},
						{
							id : "1808",
							name : "\u963f\u62c9\u4f2f\u8bed\u7ffb\u8bd1"
						},
						{
							id : "1016999",
							name : "\u5176\u4ed6\u8bed\u79cd\u7ffb\u8bd1(\u7ffb\u8bd1\u7c7b)"
						} ]
			},
			{
				id : "100",
				name : "\u9500\u552e\u7c7b",
				subItems : [
						{
							id : "100",
							name : "\u9500\u552e\u7c7b"
						},
						{
							id : "1005017",
							name : "\u9500\u552e\u603b\u76d1"
						},
						{
							id : "1005009",
							name : "\u9500\u552e\u7ecf\u7406\u2022\u9500\u552e\u4e3b\u7ba1"
						},
						{
							id : "1005018",
							name : "\u9500\u552e\u4ee3\u8868\u2022\u5ba2\u6237\u7ecf\u7406"
						},
						{
							id : "1005010",
							name : "\u6e20\u9053\u603b\u76d1\u2022\u5206\u9500\u603b\u76d1"
						},
						{
							id : "1005019",
							name : "\u6e20\u9053\u5206\u9500\u7ecf\u7406\u2022\u6e20\u9053\u5206\u9500\u4e3b\u7ba1"
						},
						{
							id : "1005011",
							name : "\u6e20\u9053\u4ee3\u8868"
						},
						{
							id : "1005023",
							name : "\u533a\u57df\u9500\u552e\u7ba1\u7406"
						},
						{
							id : "1005012",
							name : "\u552e\u524d\u652f\u6301\u7ecf\u7406\u2022\u552e\u524d\u652f\u6301\u4e3b\u7ba1"
						},
						{
							id : "1005020",
							name : "\u552e\u524d\u652f\u6301\u5de5\u7a0b\u5e08"
						},
						{
							id : "1005006",
							name : "\u9500\u552e\u52a9\u7406"
						},
						{
							id : "1005013",
							name : "\u5546\u52a1\u7ecf\u7406\u2022\u5546\u52a1\u4e3b\u7ba1"
						},
						{
							id : "1005014",
							name : "\u5546\u52a1\u4e13\u5458\u2022\u5546\u52a1\u52a9\u7406"
						},
						{
							id : "1005015",
							name : "\u4e1a\u52a1\u62d3\u5c55(BD)\u7ecf\u7406"
						},
						{
							id : "1005016",
							name : "\u7535\u8bdd\u9500\u552e"
						},
						{
							id : "1005022",
							name : "\u533b\u836f\u4ee3\u8868"
						},
						{
							id : "1005024",
							name : "\u533b\u7597\u5668\u68b0\u9500\u552e"
						},
						{
							id : "1005021",
							name : "\u4fdd\u9669\u4ee3\u7406\u4eba\u2022\u7ecf\u7eaa\u4eba\u2022\u5ba2\u6237\u7ecf\u7406"
						},
						{
							id : "1005999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u9500\u552e\u7c7b)"
						} ]
			},
			{
				id : "900",
				name : "\u5ba2\u6237\u670d\u52a1\u7c7b",
				subItems : [
						{
							id : "900",
							name : "\u5ba2\u6237\u670d\u52a1\u7c7b"
						},
						{
							id : "1007008",
							name : "\u5ba2\u6237\u670d\u52a1\u603b\u76d1"
						},
						{
							id : "1007010",
							name : "\u5ba2\u6237\u670d\u52a1\u7ecf\u7406\u2022\u5ba2\u6237\u670d\u52a1\u4e3b\u7ba1"
						},
						{
							id : "1007002",
							name : "\u5ba2\u6237\u670d\u52a1\u4e13\u5458\u2022\u5ba2\u6237\u670d\u52a1\u52a9\u7406"
						},
						{
							id : "1007003",
							name : "\u5ba2\u6237\u5173\u7cfb\u7ba1\u7406"
						},
						{
							id : "1007011",
							name : "\u5ba2\u6237\u54a8\u8be2\u70ed\u7ebf\u2022\u547c\u53eb\u4e2d\u5fc3\u4eba\u5458"
						},
						{
							id : "1007009",
							name : "\u552e\u540e\u652f\u6301\u7ecf\u7406\u2022\u552e\u540e\u652f\u6301\u4e3b\u7ba1"
						},
						{
							id : "1007006",
							name : "\u552e\u540e\u652f\u6301\u5de5\u7a0b\u5e08"
						},
						{
							id : "1007999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u5ba2\u6237\u670d\u52a1\u7c7b)"
						} ]
			},
			{
				id : "3600",
				name : "\u5e02\u573a\u2022\u516c\u5173\u2022\u5a92\u4ecb\u7c7b",
				subItems : [
						{
							id : "3600",
							name : "\u5e02\u573a\u2022\u516c\u5173\u2022\u5a92\u4ecb\u7c7b"
						},
						{
							id : "1008032",
							name : "\u5e02\u573a\u603b\u76d1"
						},
						{
							id : "1008033",
							name : "\u5e02\u573a\u7ecf\u7406\u2022\u5e02\u573a\u4e3b\u7ba1"
						},
						{
							id : "1008007",
							name : "\u5e02\u573a\u4e13\u5458\u2022\u5e02\u573a\u52a9\u7406"
						},
						{
							id : "1008029",
							name : "\u516c\u5173\u5a92\u4ecb\u7ecf\u7406\u2022\u516c\u5173\u5a92\u4ecb\u4e3b\u7ba1"
						},
						{
							id : "1008030",
							name : "\u516c\u5173\u5a92\u4ecb\u4e13\u5458"
						},
						{
							id : "3630",
							name : "\u5e02\u573a\u8c03\u7814\u4e0e\u5206\u6790"
						},
						{
							id : "1008011",
							name : "\u4f1a\u52a1\u7ecf\u7406\u2022\u4f1a\u52a1\u4e3b\u7ba1"
						},
						{
							id : "1008012",
							name : "\u4f1a\u52a1\u4e13\u5458"
						},
						{
							id : "1008008",
							name : "\u4ea7\u54c1\u7ecf\u7406\u2022\u54c1\u724c\u7ecf\u7406\u2022\u4e3b\u7ba1"
						},
						{
							id : "1008005",
							name : "\u4ea7\u54c1\u4e13\u5458\u2022\u54c1\u724c\u4e13\u5458"
						},
						{
							id : "1008026",
							name : "\u4fc3\u9500\u7ecf\u7406\u2022\u4fc3\u9500\u4e3b\u7ba1"
						},
						{
							id : "1008027",
							name : "\u4fc3\u9500\u5458"
						},
						{
							id : "1008022",
							name : "\u5e02\u573a\u4f01\u5212\u7ecf\u7406\u2022\u5e02\u573a\u4f01\u5212\u4e3b\u7ba1"
						},
						{
							id : "1008023",
							name : "\u5e02\u573a\u4f01\u5212\u4e13\u5458"
						},
						{
							id : "1500",
							name : "\u5e7f\u544a\u521b\u610f\u2022\u7b56\u5212\u2022\u8bbe\u8ba1\u6216\u6587\u6848"
						},
						{
							id : "1008024",
							name : "\u5e7f\u544a\u5ba2\u6237\u7ecf\u7406"
						},
						{
							id : "1008025",
							name : "\u5e7f\u544a\u5ba2\u6237\u4e3b\u7ba1\u2022\u5e7f\u544a\u5ba2\u6237\u4e13\u5458"
						},
						{
							id : "1008031",
							name : "\u5e02\u573a\u8425\u9500\u7ecf\u7406\u2022\u5e02\u573a\u8425\u9500\u4e3b\u7ba1"
						},
						{
							id : "1008028",
							name : "\u5e02\u573a\u8425\u9500\u4e13\u5458"
						},
						{
							id : "1008999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u5e02\u573a\u2022\u516c\u5173\u2022\u5a92\u4ecb\u7c7b)"
						} ]
			},
			{
				id : "2100",
				name : "\u54a8\u8be2\u2022\u987e\u95ee\u7c7b",
				subItems : [
						{
							id : "2100",
							name : "\u54a8\u8be2\u2022\u987e\u95ee\u7c7b"
						},
						{
							id : "1010004",
							name : "\u4f01\u7ba1\u987e\u95ee\u2022\u4e13\u4e1a\u987e\u95ee\u2022\u7b56\u5212\u5e08"
						},
						{
							id : "1010009",
							name : "\u54a8\u8be2\u603b\u76d1"
						},
						{
							id : "1010010",
							name : "\u54a8\u8be2\u7ecf\u7406"
						},
						{
							id : "1010011",
							name : "\u54a8\u8be2\u5458"
						},
						{
							id : "1010006",
							name : "\u8c03\u7814\u5458"
						},
						{
							id : "1010007",
							name : "\u57f9\u8bad\u5e08"
						},
						{
							id : "1010008",
							name : "\u6d89\u5916\u54a8\u8be2\u5e08"
						},
						{
							id : "1010999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u54a8\u8be2\u2022\u987e\u95ee\u7c7b)"
						} ]
			},
			{
				id : "400",
				name : "\u8d22\u52a1\u2022\u5ba1\u8ba1\u2022\u7edf\u8ba1\u7c7b",
				subItems : [
						{
							id : "400",
							name : "\u8d22\u52a1\u2022\u5ba1\u8ba1\u2022\u7edf\u8ba1\u7c7b"
						},
						{
							id : "1012024",
							name : "\u9996\u5e2d\u8d22\u52a1\u5b98CFO"
						},
						{
							id : "1012011",
							name : "\u8d22\u52a1\u603b\u76d1"
						},
						{
							id : "1012012",
							name : "\u8d22\u52a1\u7ecf\u7406"
						},
						{
							id : "1012002",
							name : "\u8d22\u52a1\u4e3b\u7ba1\u2022\u603b\u5e10\u4e3b\u7ba1"
						},
						{
							id : "1012013",
							name : "\u4f1a\u8ba1\u7ecf\u7406\u2022\u4f1a\u8ba1\u4e3b\u7ba1"
						},
						{
							id : "1012014",
							name : "\u4f1a\u8ba1"
						},
						{
							id : "1012004",
							name : "\u51fa\u7eb3"
						},
						{
							id : "1012015",
							name : "\u8d22\u52a1\u5206\u6790\u7ecf\u7406\u2022\u8d22\u52a1\u5206\u6790\u4e3b\u7ba1"
						},
						{
							id : "1012016",
							name : "\u8d22\u52a1\u5206\u6790\u5458"
						},
						{
							id : "1012017",
							name : "\u6210\u672c\u7ecf\u7406\u2022\u6210\u672c\u4e3b\u7ba1"
						},
						{
							id : "1012018",
							name : "\u6210\u672c\u7ba1\u7406\u5458"
						},
						{
							id : "1012019",
							name : "\u5ba1\u8ba1\u7ecf\u7406\u2022\u5ba1\u8ba1\u4e3b\u7ba1"
						},
						{
							id : "1012020",
							name : "\u5ba1\u8ba1\u4e13\u5458\u2022\u5ba1\u8ba1\u52a9\u7406"
						},
						{
							id : "1012008",
							name : "\u8d22\u52a1\u52a9\u7406\u2022\u4f1a\u8ba1\u52a9\u7406"
						},
						{
							id : "1012009",
							name : "\u7edf\u8ba1"
						},
						{
							id : "1012021",
							name : "\u7a0e\u52a1\u7ecf\u7406\u2022\u7a0e\u52a1\u4e3b\u7ba1"
						},
						{
							id : "1012022",
							name : "\u7a0e\u52a1\u4e13\u5458\u2022\u7a0e\u52a1\u52a9\u7406"
						},
						{
							id : "1012023",
							name : "\u6295\u878d\u8d44\u7ecf\u7406\u2022\u6295\u878d\u8d44\u4e3b\u7ba1"
						},
						{
							id : "1012999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u8d22\u52a1\u2022\u5ba1\u8ba1\u2022\u7edf\u8ba1\u7c7b)"
						} ]
			},
			{
				id : "604",
				name : "\u9879\u76ee\u7ba1\u7406\u7c7b",
				subItems : [
						{
							id : "604",
							name : "\u9879\u76ee\u7ba1\u7406\u7c7b"
						},
						{
							id : "1006001",
							name : "\u9879\u76ee\u603b\u76d1"
						},
						{
							id : "1006002",
							name : "\u9879\u76ee\u7ecf\u7406"
						},
						{
							id : "1006999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u9879\u76ee\u7ba1\u7406\u7c7b)"
						} ]
			},
			{
				id : "300",
				name : "\u4eba\u529b\u8d44\u6e90\u7c7b",
				subItems : [
						{
							id : "300",
							name : "\u4eba\u529b\u8d44\u6e90\u7c7b"
						},
						{
							id : "1011020",
							name : "\u4eba\u529b\u8d44\u6e90\u603b\u76d1"
						},
						{
							id : "1011002",
							name : "\u4eba\u529b\u8d44\u6e90\u7ecf\u7406\u2022\u4eba\u529b\u8d44\u6e90\u4e3b\u7ba1"
						},
						{
							id : "1011003",
							name : "\u4eba\u529b\u8d44\u6e90\u4e13\u5458\u2022\u4eba\u529b\u8d44\u6e90\u52a9\u7406"
						},
						{
							id : "1011019",
							name : "\u4eba\u4e8b\u4e3b\u7ba1\u2022\u4eba\u4e8b\u4e13\u5458"
						},
						{
							id : "1011004",
							name : "\u62db\u8058\u7ecf\u7406\u2022\u62db\u8058\u4e3b\u7ba1"
						},
						{
							id : "1011005",
							name : "\u62db\u8058\u4e13\u5458\u2022\u62db\u8058\u52a9\u7406"
						},
						{
							id : "1011006",
							name : "\u57f9\u8bad\u7ecf\u7406\u2022\u57f9\u8bad\u4e3b\u7ba1"
						},
						{
							id : "1011007",
							name : "\u57f9\u8bad\u4e13\u5458\u2022\u57f9\u8bad\u52a9\u7406"
						},
						{
							id : "1011008",
							name : "\u57f9\u8bad\u5e08"
						},
						{
							id : "1011009",
							name : "\u85aa\u8d44\u798f\u5229\u7ecf\u7406\u2022\u4e3b\u7ba1"
						},
						{
							id : "1011010",
							name : "\u85aa\u8d44\u798f\u5229\u4e13\u5458\u2022\u85aa\u8d44\u798f\u5229\u52a9\u7406"
						},
						{
							id : "1011011",
							name : "\u7ee9\u6548\u8003\u6838\u7ecf\u7406\u2022\u7ee9\u6548\u8003\u6838\u4e3b\u7ba1"
						},
						{
							id : "1011012",
							name : "\u7ee9\u6548\u8003\u6838\u4e13\u5458\u2022\u7ee9\u6548\u8003\u6838\u52a9\u7406"
						},
						{
							id : "1011013",
							name : "\u5458\u5de5\u5173\u7cfb\u7ecf\u7406\u2022\u5458\u5de5\u5173\u7cfb\u4e3b\u7ba1"
						},
						{
							id : "1011014",
							name : "\u5458\u5de5\u5173\u7cfb\u4e13\u5458\u2022\u5458\u5de5\u5173\u7cfb\u52a9\u7406"
						},
						{
							id : "1011021",
							name : "\u730e\u5934\u987e\u95ee"
						},
						{
							id : "1011999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u4eba\u529b\u8d44\u6e90\u7c7b)"
						} ]
			},
			{
				id : "1900",
				name : "\u6559\u80b2\u2022\u57f9\u8bad\u7c7b",
				subItems : [
						{
							id : "1900",
							name : "\u6559\u80b2\u2022\u57f9\u8bad\u7c7b"
						},
						{
							id : "1023001",
							name : "\u6559\u5b66\u2022\u6559\u52a1\u7ba1\u7406\u4eba\u5458"
						},
						{
							id : "1023002",
							name : "\u5e7c\u513f\u6559\u80b2"
						},
						{
							id : "1023003",
							name : "\u6559\u5e08"
						},
						{
							id : "1023004",
							name : "\u8bb2\u5e08"
						},
						{
							id : "1023005",
							name : "\u52a9\u6559"
						},
						{
							id : "1023006",
							name : "\u5bb6\u6559"
						},
						{
							id : "1023007",
							name : "\u5065\u8eab\u987e\u95ee/\u6559\u7ec3"
						},
						{
							id : "1023008",
							name : "\u6559\u80b2\u4ea7\u54c1\u5f00\u53d1"
						},
						{
							id : "1023999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u6559\u80b2\u2022\u57f9\u8bad\u7c7b)"
						} ]
			},
			{
				id : "1200",
				name : "\u7ecf\u8425\u7ba1\u7406\u7c7b",
				subItems : [
						{
							id : "1200",
							name : "\u7ecf\u8425\u7ba1\u7406\u7c7b"
						},
						{
							id : "1009001",
							name : "\u9996\u5e2d\u6267\u884c\u5b98CEO\u2022\u603b\u88c1\u2022\u603b\u7ecf\u7406"
						},
						{
							id : "1009004",
							name : "\u9996\u5e2d\u8fd0\u8425\u5b98COO"
						},
						{
							id : "1009005",
							name : "\u9996\u5e2d\u6280\u672f\u5b98CTO\u2022\u9996\u5e2d\u4fe1\u606f\u5b98CIO"
						},
						{
							id : "1009006",
							name : "\u9996\u5e2d\u8d22\u52a1\u5b98CFO"
						},
						{
							id : "1009013",
							name : "\u526f\u603b\u88c1\u2022\u526f\u603b\u7ecf\u7406"
						},
						{
							id : "1009007",
							name : "\u5408\u4f19\u4eba"
						},
						{
							id : "1009008",
							name : "\u603b\u76d1\u2022\u4e8b\u4e1a\u90e8\u603b\u7ecf\u7406"
						},
						{
							id : "1009003",
							name : "\u603b\u88c1\u52a9\u7406\u2022\u603b\u7ecf\u7406\u52a9\u7406"
						},
						{
							id : "1009009",
							name : "\u5206\u516c\u53f8\u7ecf\u7406\u2022\u5206\u652f\u673a\u6784\u7ecf\u7406\u2022\u529e\u4e8b\u5904\u7ecf\u7406"
						},
						{
							id : "1009010",
							name : "\u90e8\u95e8\u7ecf\u7406"
						},
						{
							id : "1009011",
							name : "\u5382\u957f\u2022\u526f\u5382\u957f"
						},
						{
							id : "1009012",
							name : "\u5e97\u957f"
						},
						{
							id : "1009999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u7ecf\u8425\u7ba1\u7406\u7c7b)"
						} ]
			},
			{
				id : "1034000",
				name : "\u8d28\u91cf\u7ba1\u7406\u7c7b",
				subItems : [
						{
							id : "1034000",
							name : "\u8d28\u91cf\u7ba1\u7406\u7c7b"
						},
						{
							id : "1034001",
							name : "\u8d28\u91cf\u4fdd\u8bc1(QA)\u2022\u8d28\u91cf\u7ba1\u7406\u2022\u8d28\u91cf\u7763\u5bfc"
						},
						{
							id : "1034002",
							name : "\u8d28\u91cf\u63a7\u5236(QC)\u2022\u8d28\u91cf\u68c0\u9a8c"
						},
						{
							id : "1034003",
							name : "\u65b0\u4ea7\u54c1\u5f00\u53d1\u6d4b\u8bd5"
						},
						{
							id : "1034004",
							name : "\u4f9b\u5e94\u5546\u7ba1\u7406\u2022\u91c7\u8d2d\u8bbe\u5907\u4e0e\u6750\u6599\u8d28\u91cf\u7ba1\u7406"
						},
						{
							id : "1034005",
							name : "\u8d28\u91cf\u4f53\u7cfb\u8ba4\u8bc1"
						},
						{
							id : "1034999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u8d28\u91cf\u7ba1\u7406\u7c7b)"
						} ]
			},
			{
				id : "3800",
				name : "\u7f8e\u672f\u2022\u8bbe\u8ba1\u2022\u521b\u610f\u7c7b",
				subItems : [
						{
							id : "3800",
							name : "\u7f8e\u672f\u2022\u8bbe\u8ba1\u2022\u521b\u610f\u7c7b"
						},
						{
							id : "1021001",
							name : "\u8bbe\u8ba1\u7ba1\u7406\u4eba\u5458"
						},
						{
							id : "1021002",
							name : "\u7f8e\u672f\u2022\u56fe\u5f62\u8bbe\u8ba1"
						},
						{
							id : "1021003",
							name : "\u5de5\u4e1a\u2022\u4ea7\u54c1\u8bbe\u8ba1"
						},
						{
							id : "1021018",
							name : "\u670d\u88c5\u2022\u7eba\u7ec7\u54c1\u8bbe\u8ba1\u5e08"
						},
						{
							id : "1021015",
							name : "\u670d\u88c5\u6253\u6837\u2022\u670d\u88c5\u5236\u677f"
						},
						{
							id : "1021005",
							name : "\u5de5\u827a\u54c1\u2022\u73e0\u5b9d\u8bbe\u8ba1"
						},
						{
							id : "1021006",
							name : "\u5bb6\u5177\u8bbe\u8ba1"
						},
						{
							id : "1021007",
							name : "\u5e73\u9762\u8bbe\u8ba1"
						},
						{
							id : "1021008",
							name : "\u5a92\u4f53\u5e7f\u544a\u8bbe\u8ba1"
						},
						{
							id : "1021009",
							name : "\u9020\u578b\u8bbe\u8ba1"
						},
						{
							id : "1021011",
							name : "\u591a\u5a92\u4f53\u8bbe\u8ba1"
						},
						{
							id : "1021012",
							name : "\u52a8\u753b\u8bbe\u8ba1"
						},
						{
							id : "1021013",
							name : "\u5c55\u793a\u2022\u88c5\u6f62\u8bbe\u8ba1"
						},
						{
							id : "1021014",
							name : "\u521b\u610f\u2022\u7b56\u5212\u2022\u6587\u6848"
						},
						{
							id : "1021999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u7f8e\u672f\u2022\u8bbe\u8ba1\u2022\u521b\u610f\u7c7b)"
						} ]
			},
			{
				id : "3300",
				name : "\u6280\u5de5\u7c7b",
				subItems : [
						{
							id : "3300",
							name : "\u6280\u5de5\u7c7b"
						},
						{
							id : "1028001",
							name : "\u7535\u5de5\u2022\u94c6\u710a\u5de5\u2022\u94b3\u5de5"
						},
						{
							id : "1028002",
							name : "\u7a7a\u8c03\u5de5\u2022\u7535\u68af\u5de5\u2022\u9505\u7089\u5de5"
						},
						{
							id : "3313",
							name : "\u6cb9\u6f06\u2022\u94a3\u91d1"
						},
						{
							id : "3302",
							name : "\u952f\u5e8a\u2022\u8f66\u5e8a\u2022\u78e8\u5e8a\u2022\u94e3\u5e8a\u2022\u51b2\u5e8a\u2022\u9523\u5e8a"
						},
						{
							id : "3303",
							name : "\u94f2\u8f66\u2022\u53c9\u8f66\u5de5"
						},
						{
							id : "3304",
							name : "\u673a\u4fee\u5de5"
						},
						{
							id : "1028011",
							name : "\u5207\u5272\u6280\u5de5"
						},
						{
							id : "1028012",
							name : "\u4ea7\u54c1\u6216\u5de5\u827a\u5de5\u7a0b\u5e08"
						},
						{
							id : "1028004",
							name : "\u666e\u5de5"
						},
						{
							id : "1028006",
							name : "\u6c34\u5de5\u2022\u6728\u5de5\u2022\u6cb9\u6f06\u5de5"
						},
						{
							id : "1028013",
							name : "\u6a21\u5177\u5de5\u7a0b\u5e08"
						},
						{
							id : "1028999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u6280\u5de5\u7c7b)"
						} ]
			},
			{
				id : "1013000",
				name : "\u91d1\u878d\u7c7b\uff08\u94f6\u884c\u2022\u57fa\u91d1\u2022\u8bc1\u5238\u2022\u671f\u8d27\u2022\u6295\u8d44\u2022\u5178\u5f53\uff09",
				subItems : [
						{
							id : "1013000",
							name : "\u91d1\u878d\u7c7b\uff08\u94f6\u884c\u2022\u57fa\u91d1\u2022\u8bc1\u5238\u2022\u671f\u8d27\u2022\u6295\u8d44\u2022\u5178\u5f53\uff09"
						},
						{
							id : "1013003",
							name : "\u8bc1\u5238\u2022\u5916\u6c47\u2022\u671f\u8d27\u7ecf\u7eaa\u4eba"
						},
						{
							id : "1013016",
							name : "\u8bc1\u5238\u5206\u6790\u5e08"
						},
						{
							id : "1013018",
							name : "\u80a1\u7968\u2022\u671f\u8d27\u64cd\u76d8\u624b"
						},
						{
							id : "1013001",
							name : "\u884c\u957f\u2022\u526f\u884c\u957f\u2022\u9ad8\u7ea7\u7ba1\u7406"
						},
						{
							id : "2300",
							name : "\u6295\u8d44\u7ba1\u7406\u2022\u7814\u7a76\u5206\u6790\u2022\u987e\u95ee"
						},
						{
							id : "1013013",
							name : "\u6295\u8d44\u94f6\u884c\u4e1a\u52a1"
						},
						{
							id : "1013004",
							name : "\u878d\u8d44\u9879\u76ee\u7ba1\u7406"
						},
						{
							id : "1013006",
							name : "\u5ba2\u6237\u7ecf\u7406\u2022\u91d1\u878d\u4ea7\u54c1\u8425\u9500\u7ba1\u7406"
						},
						{
							id : "1013007",
							name : "\u8d44\u4ea7\u7ba1\u7406\u2022\u8d44\u4ea7\u8bc4\u4f30\u2022\u4ea4\u6613\u7ba1\u7406"
						},
						{
							id : "1013005",
							name : "\u98ce\u9669\u7ba1\u7406\u2022\u7a3d\u6838\u2022\u6cd5\u5f8b"
						},
						{
							id : "1013008",
							name : "\u4fe1\u8d37\u7ba1\u7406\u2022\u4fe1\u7528\u7ba1\u7406"
						},
						{
							id : "1013009",
							name : "\u8d44\u91d1\u7ba1\u7406\u2022\u8d22\u52a1\u7ba1\u7406\u2022\u6e05\u7b97\u2022\u7ed3\u7b97"
						},
						{
							id : "1013010",
							name : "\u67dc\u5458\u2022\u7406\u8d22\u54a8\u8be2\u2022\u5ba2\u6237\u670d\u52a1\u2022\u94f6\u884c\u4f1a\u8ba1"
						},
						{
							id : "1013014",
							name : "\u94f6\u884c\u5361\u2022\u7535\u5b50\u94f6\u884c\u2022\u65b0\u4e1a\u52a1\u5f00\u62d3"
						},
						{
							id : "1013015",
							name : "\u56fd\u9645\u7ed3\u7b97\u2022\u5916\u6c47\u4ea4\u6613"
						},
						{
							id : "1013019",
							name : "\u62cd\u5356\u5e08"
						},
						{
							id : "1013020",
							name : "\u9274\u5b9a\u2022\u4f30\u4ef7\u5e08"
						},
						{
							id : "1013999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u91d1\u878d\u7c7b\uff08\u94f6\u884c\u2022\u57fa\u91d1\u2022\u8bc1\u5238\u2022\u671f\u8d27\u2022\u6295\u8d44\u2022\u5178\u5f53\uff09)"
						} ]
			},
			{
				id : "1036000",
				name : "\u4fdd\u9669\u7c7b",
				subItems : [
						{
							id : "1036000",
							name : "\u4fdd\u9669\u7c7b"
						},
						{
							id : "1036001",
							name : "\u7cbe\u7b97\u2022\u4ea7\u54c1\u7814\u53d1\u2022\u6295\u8d44\u2022\u7a3d\u6838\u2022\u6cd5\u5f8b"
						},
						{
							id : "1036002",
							name : "\u6838\u4fdd\u2022\u7406\u8d54\u2022\u5951\u7ea6\u7ba1\u7406\u2022\u53d7\u7406\u53f0"
						},
						{
							id : "1036003",
							name : "\u7ec4\u8bad\u2022\u57f9\u8bad\u2022\u4eba\u5458\u7ba1\u7406\u2022\u4e1a\u52a1\u63a8\u52a8"
						},
						{
							id : "1036004",
							name : "\u4fdd\u9669\u4ee3\u7406\u4eba\u2022\u7ecf\u7eaa\u4eba\u2022\u5ba2\u6237\u7ecf\u7406"
						},
						{
							id : "1036005",
							name : "\u5ba2\u6237\u670d\u52a1\u2022\u7eed\u671f\u7ba1\u7406"
						},
						{
							id : "1036006",
							name : "\u4fdd\u9669\u5185\u52e4"
						},
						{
							id : "1036999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u4fdd\u9669\u7c7b)"
						} ]
			},
			{
				id : "1014000",
				name : "\u8d38\u6613\u2022\u7269\u6d41\u2022\u91c7\u8d2d\u2022\u8fd0\u8f93\u7c7b",
				subItems : [
						{
							id : "1014000",
							name : "\u8d38\u6613\u2022\u7269\u6d41\u2022\u91c7\u8d2d\u2022\u8fd0\u8f93\u7c7b"
						},
						{
							id : "1014001",
							name : "\u5916\u8d38\u7ecf\u7406\u2022\u4e3b\u7ba1\u2022\u4e13\u5458\u2022\u52a9\u7406"
						},
						{
							id : "1014002",
							name : "\u56fd\u5185\u8d38\u6613\u7ecf\u7406\u2022\u4e3b\u7ba1\u2022\u4e13\u5458\u2022\u52a9\u7406"
						},
						{
							id : "1014003",
							name : "\u4e1a\u52a1\u8ddf\u5355"
						},
						{
							id : "1014020",
							name : "\u5355\u8bc1\u5458\u2022\u62a5\u5173\u5458"
						},
						{
							id : "1014014",
							name : "\u9646\u8fd0\u2022\u6d77\u8fd0\u2022\u7a7a\u8fd0\u4eba\u5458"
						},
						{
							id : "1014005",
							name : "\u5546\u52a1\u7ecf\u7406\u2022\u4e3b\u7ba1\u2022\u4e13\u5458\u2022\u52a9\u7406"
						},
						{
							id : "1014021",
							name : "\u91c7\u8d2d\u7ecf\u7406\u2022\u4e3b\u7ba1"
						},
						{
							id : "1014015",
							name : "\u91c7\u8d2d\u5458\u2022\u52a9\u7406"
						},
						{
							id : "1014022",
							name : "\u7269\u6d41\u7ecf\u7406\u2022\u7269\u6d41\u4e3b\u7ba1"
						},
						{
							id : "1014016",
							name : "\u7269\u6d41\u4e13\u5458\u2022\u7269\u6d41\u52a9\u7406"
						},
						{
							id : "1014017",
							name : "\u7269\u6599\u7ba1\u7406"
						},
						{
							id : "1014008",
							name : "\u4ed3\u5e93\u7ba1\u7406"
						},
						{
							id : "1014009",
							name : "\u8fd0\u8f93\u7ecf\u7406\u2022\u4e3b\u7ba1"
						},
						{
							id : "1014010",
							name : "\u8d27\u8fd0\u4ee3\u7406"
						},
						{
							id : "1014011",
							name : "\u6d77\u9646\u7a7a\u4ea4\u901a\u8fd0\u8f93"
						},
						{
							id : "1014023",
							name : "\u53f8\u673a"
						},
						{
							id : "1014012",
							name : "\u8c03\u5ea6\u5458"
						},
						{
							id : "1014013",
							name : "\u5feb\u9012\u5458\u2022\u901f\u9012\u5458"
						},
						{
							id : "1014024",
							name : "\u4f9b\u5e94\u94fe\u7ba1\u7406"
						},
						{
							id : "1014025",
							name : "\u4e70\u624b"
						},
						{
							id : "1014026",
							name : "\u6d77\u5173\u4e8b\u52a1\u7ba1\u7406"
						},
						{
							id : "1024027",
							name : "\u96c6\u88c5\u7bb1\u4e1a\u52a1"
						},
						{
							id : "1014999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u8d38\u6613\u2022\u7269\u6d41\u2022\u91c7\u8d2d\u2022\u8fd0\u8f93\u7c7b)"
						} ]
			},
			{
				id : "1020000",
				name : "\u5546\u4e1a\u96f6\u552e\u7c7b",
				subItems : [
						{
							id : "1020000",
							name : "\u5546\u4e1a\u96f6\u552e\u7c7b"
						},
						{
							id : "1020001",
							name : "\u5e97\u957f"
						},
						{
							id : "1020002",
							name : "\u8425\u8fd0"
						},
						{
							id : "1020003",
							name : "\u751f\u9c9c\u2022\u9632\u635f\u6280\u672f\u4eba\u5458"
						},
						{
							id : "1020004",
							name : "\u7406\u8d27\u5458"
						},
						{
							id : "1020005",
							name : "\u8425\u4e1a\u5458\u2022\u670d\u52a1\u5458\u2022\u5e97\u5458\u2022\u5bfc\u8d2d\u5458"
						},
						{
							id : "3307",
							name : "\u6536\u94f6\u5458"
						},
						{
							id : "1020999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u5546\u4e1a\u96f6\u552e\u7c7b)"
						} ]
			},
			{
				id : "702",
				name : "\u5efa\u7b51\u2022\u623f\u5730\u4ea7\u2022\u88c5\u9970\u88c5\u4fee\u2022\u7269\u4e1a\u7ba1\u7406\u7c7b",
				subItems : [
						{
							id : "702",
							name : "\u5efa\u7b51\u2022\u623f\u5730\u4ea7\u2022\u88c5\u9970\u88c5\u4fee\u2022\u7269\u4e1a\u7ba1\u7406\u7c7b"
						},
						{
							id : "1015021",
							name : "\u9ad8\u7ea7\u5efa\u7b51\u5de5\u7a0b\u5e08\u2022\u603b\u5de5"
						},
						{
							id : "3103",
							name : "\u5efa\u7b51\u5e08"
						},
						{
							id : "1015001",
							name : "\u7ed3\u6784\u5de5\u7a0b\u5e08\u2022\u571f\u6728\u571f\u5efa\u5de5\u7a0b\u5e08"
						},
						{
							id : "1015002",
							name : "\u5efa\u7b51\u8bbe\u8ba1\u2022\u5efa\u7b51\u5236\u56fe"
						},
						{
							id : "1015011",
							name : "\u5ca9\u571f\u5de5\u7a0b\u5e08"
						},
						{
							id : "1015012",
							name : "\u5e55\u5899\u5de5\u7a0b\u5e08"
						},
						{
							id : "1015003",
							name : "\u5efa\u7b51\u5de5\u7a0b\u7ba1\u7406"
						},
						{
							id : "1015004",
							name : "\u5de5\u7a0b\u76d1\u7406"
						},
						{
							id : "1015013",
							name : "\u5efa\u7b51\u5de5\u7a0b\u9a8c\u6536"
						},
						{
							id : "1015014",
							name : "\u6d4b\u7ed8\u2022\u6d4b\u91cf"
						},
						{
							id : "3107",
							name : "\u7ed9\u6392\u6c34\u2022\u5f3a\u7535\u2022\u5f31\u7535\u2022\u5236\u51b7\u6696\u901a"
						},
						{
							id : "3101",
							name : "\u623f\u5730\u4ea7\u5f00\u53d1\u2022\u7b56\u5212"
						},
						{
							id : "1015020",
							name : "\u623f\u5730\u4ea7\u9879\u76ee\u62db\u6295\u6807\u4e13\u5458"
						},
						{
							id : "3102",
							name : "\u623f\u5730\u4ea7\u8bc4\u4f30"
						},
						{
							id : "1015015",
							name : "\u623f\u5730\u4ea7\u9500\u552e"
						},
						{
							id : "1015005",
							name : "\u8bbe\u5907\u5de5\u7a0b\u5e08"
						},
						{
							id : "1015006",
							name : "\u9020\u4ef7\u2022\u6982\u9884\u7b97"
						},
						{
							id : "3108",
							name : "\u8def\u6865\u2022\u96a7\u9053\u2022\u6e2f\u53e3\u2022\u822a\u9053\u5de5\u7a0b"
						},
						{
							id : "3109",
							name : "\u56ed\u827a\u8bbe\u8ba1\u2022\u56ed\u6797\u8bbe\u8ba1\u2022\u666f\u89c2\u8bbe\u8ba1"
						},
						{
							id : "3110",
							name : "\u5ba4\u5185\u5916\u88c5\u6f62\u8bbe\u8ba1"
						},
						{
							id : "2200",
							name : "\u7269\u4e1a\u7ba1\u7406"
						},
						{
							id : "1015016",
							name : "\u7269\u4e1a\u987e\u95ee"
						},
						{
							id : "1015017",
							name : "\u7269\u4e1a\u62db\u5546\u2022\u79df\u8d41\u2022\u79df\u552e"
						},
						{
							id : "1015018",
							name : "\u7269\u4e1a\u8bbe\u65bd\u7ba1\u7406"
						},
						{
							id : "1015019",
							name : "\u7269\u4e1a\u7ef4\u4fee"
						},
						{
							id : "1015007",
							name : "\u57ce\u5e02\u89c4\u5212\u4e0e\u8bbe\u8ba1"
						},
						{
							id : "1015008",
							name : "\u623f\u5730\u4ea7\u4e2d\u4ecb\u2022\u4ea4\u6613"
						},
						{
							id : "1015009",
							name : "\u516c\u8def\u6865\u6881\u8bbe\u8ba1\u2022\u516c\u8def\u6865\u6881\u9884\u7b97\u5e08"
						},
						{
							id : "1015010",
							name : "\u65bd\u5de5\u5458"
						},
						{
							id : "1015999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u5efa\u7b51\u2022\u623f\u5730\u4ea7\u2022\u88c5\u9970\u88c5\u4fee\u2022\u7269\u4e1a\u7ba1\u7406\u7c7b)"
						} ]
			},
			{
				id : "1700",
				name : "\u6cd5\u5f8b\u7c7b",
				subItems : [
						{
							id : "1700",
							name : "\u6cd5\u5f8b\u7c7b"
						},
						{
							id : "1024001",
							name : "\u5f8b\u5e08"
						},
						{
							id : "1024002",
							name : "\u6cd5\u5f8b\u987e\u95ee"
						},
						{
							id : "1024003",
							name : "\u5f8b\u5e08\u52a9\u7406"
						},
						{
							id : "1024004",
							name : "\u6cd5\u52a1\u4eba\u5458"
						},
						{
							id : "1024005",
							name : "\u77e5\u8bc6\u4ea7\u6743\u2022\u4e13\u5229\u987e\u95ee"
						},
						{
							id : "1024999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u6cd5\u5f8b\u7c7b)"
						} ]
			},
			{
				id : "1017000",
				name : "\u9152\u5e97\u2022\u9910\u996e\u2022\u65c5\u6e38\u2022\u670d\u52a1\u7c7b",
				subItems : [
						{
							id : "1017000",
							name : "\u9152\u5e97\u2022\u9910\u996e\u2022\u65c5\u6e38\u2022\u670d\u52a1\u7c7b"
						},
						{
							id : "1017007",
							name : "\u5bbe\u9986\u6216\u9152\u5e97\u7ba1\u7406"
						},
						{
							id : "1017001",
							name : "\u5a31\u4e50\u6216\u9910\u996e\u7ba1\u7406"
						},
						{
							id : "1017002",
							name : "\u5927\u5802\u7ecf\u7406\u2022\u526f\u7406"
						},
						{
							id : "1017003",
							name : "\u697c\u9762\u7ecf\u7406"
						},
						{
							id : "1017004",
							name : "\u53a8\u5e08\u2022\u8c03\u9152\u5e08"
						},
						{
							id : "1017008",
							name : "\u8425\u517b\u5e08"
						},
						{
							id : "1017009",
							name : "\u670d\u52a1\u5458"
						},
						{
							id : "3309",
							name : "\u793c\u4eea\u2022\u5546\u52a1\u2022\u63a5\u7ebf\u751f"
						},
						{
							id : "1017010",
							name : "\u8425\u4e1a\u5458\u2022\u6536\u94f6\u5458\u2022\u7406\u8d27\u5458"
						},
						{
							id : "1017005",
							name : "\u5bfc\u6e38\u2022\u8ba1\u8c03"
						},
						{
							id : "1017017",
							name : "\u7968\u52a1"
						},
						{
							id : "1017006",
							name : "\u5065\u8eab\u6559\u7ec3"
						},
						{
							id : "1017011",
							name : "\u7f8e\u5bb9\u7f8e\u53d1"
						},
						{
							id : "1017012",
							name : "\u53f8\u673a"
						},
						{
							id : "1017013",
							name : "\u4fdd\u5b89"
						},
						{
							id : "1017014",
							name : "\u5bfb\u547c\u5458\u2022\u8bdd\u52a1\u5458"
						},
						{
							id : "1017015",
							name : "\u793e\u533a\u6216\u5bb6\u653f\u670d\u52a1"
						},
						{
							id : "1017018",
							name : "\u4fdd\u6d01"
						},
						{
							id : "1017999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u9152\u5e97\u2022\u9910\u996e\u2022\u65c5\u6e38\u2022\u670d\u52a1\u7c7b)"
						} ]
			},
			{
				id : "1026000",
				name : "\u751f\u7269\u2022\u5236\u836f\u2022\u5316\u5de5\u2022\u73af\u4fdd\u7c7b",
				subItems : [
						{
							id : "1026000",
							name : "\u751f\u7269\u2022\u5236\u836f\u2022\u5316\u5de5\u2022\u73af\u4fdd\u7c7b"
						},
						{
							id : "1026001",
							name : "\u751f\u7269\u5de5\u7a0b\u2022\u751f\u7269\u5236\u836f"
						},
						{
							id : "1026002",
							name : "\u4e34\u5e8a\u8bd5\u9a8c\u2022\u836f\u54c1\u6ce8\u518c"
						},
						{
							id : "1026003",
							name : "\u533b\u836f\u7814\u53d1\u2022\u5316\u5b66\u5236\u5242\u7814\u53d1"
						},
						{
							id : "1026015",
							name : "\u836f\u54c1\u751f\u4ea7\u2022\u8d28\u91cf\u7ba1\u7406"
						},
						{
							id : "1026014",
							name : "\u836f\u54c1\u9500\u552e\u2022\u63a8\u5e7f\u2022\u4e1a\u52a1\u54a8\u8be2"
						},
						{
							id : "1026017",
							name : "\u533b\u836f\u62db\u5546"
						},
						{
							id : "1026016",
							name : "\u5316\u5de5\u6280\u672f"
						},
						{
							id : "1026018",
							name : "\u6d82\u6599\u5f00\u53d1\u5de5\u7a0b\u5e08"
						},
						{
							id : "1026019",
							name : "\u5851\u6599\u5236\u54c1\u7814\u53d1"
						},
						{
							id : "1026020",
							name : "\u65e5\u7528\u5316\u5de5\u4ea7\u54c1\u7814\u53d1"
						},
						{
							id : "1026013",
							name : "\u73af\u4fdd\u6280\u672f"
						},
						{
							id : "1026999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u751f\u7269\u2022\u5236\u836f\u2022\u5316\u5de5\u2022\u73af\u4fdd\u7c7b)"
						} ]
			},
			{
				id : "3500",
				name : "\u6587\u4f53\u2022\u5f71\u89c6\u2022\u5199\u4f5c\u2022\u5a92\u4f53\u7c7b",
				subItems : [
						{
							id : "3500",
							name : "\u6587\u4f53\u2022\u5f71\u89c6\u2022\u5199\u4f5c\u2022\u5a92\u4f53\u7c7b"
						},
						{
							id : "1022001",
							name : "\u4f5c\u5bb6\u2022\u64b0\u7a3f\u4eba"
						},
						{
							id : "1022002",
							name : "\u603b\u7f16\u2022\u526f\u603b\u7f16"
						},
						{
							id : "1600",
							name : "\u7f16\u8f91\u2022\u8bb0\u8005"
						},
						{
							id : "1022003",
							name : "\u7f8e\u672f\u7f16\u8f91"
						},
						{
							id : "1022004",
							name : "\u53d1\u884c\u603b\u76d1\u2022\u7ecf\u7406\u2022\u4e3b\u7ba1"
						},
						{
							id : "1022005",
							name : "\u51fa\u7248"
						},
						{
							id : "1022006",
							name : "\u6821\u5bf9\u2022\u5f55\u5165"
						},
						{
							id : "1022007",
							name : "\u6392\u7248\u8bbe\u8ba1"
						},
						{
							id : "1022008",
							name : "\u827a\u672f\u603b\u76d1\u2022\u8bbe\u8ba1\u603b\u76d1"
						},
						{
							id : "1022009",
							name : "\u5f71\u89c6\u7b56\u5212\u2022\u5f71\u89c6\u5236\u4f5c"
						},
						{
							id : "1022010",
							name : "\u5bfc\u6f14\u2022\u7f16\u5bfc"
						},
						{
							id : "1022011",
							name : "\u6444\u5f71\u2022\u6444\u50cf"
						},
						{
							id : "1022012",
							name : "\u5f55\u97f3\u2022\u97f3\u6548\u5e08"
						},
						{
							id : "1022013",
							name : "\u5316\u5986\u5e08\u2022\u9020\u578b\u5e08"
						},
						{
							id : "1022014",
							name : "\u6f14\u5458\u2022\u914d\u97f3\u2022\u6a21\u7279"
						},
						{
							id : "1022015",
							name : "\u4e3b\u6301\u4eba\u2022\u64ad\u97f3\u5458\u2022DJ"
						},
						{
							id : "1022016",
							name : "\u6f14\u827a\u6216\u4f53\u80b2\u7ecf\u7eaa\u4eba"
						},
						{
							id : "1022999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u6587\u4f53\u2022\u5f71\u89c6\u2022\u5199\u4f5c\u2022\u5a92\u4f53\u7c7b)"
						} ]
			},
			{
				id : "2500",
				name : "\u5728\u6821\u5b66\u751f\u7c7b",
				subItems : [ {
					id : "2500",
					name : "\u5728\u6821\u5b66\u751f\u7c7b"
				}, {
					id : "1032001",
					name : "\u5e94\u5c4a\u6bd5\u4e1a\u751f"
				}, {
					id : "1032002",
					name : "\u975e\u5e94\u5c4a\u6bd5\u4e1a\u751f"
				}, {
					id : "1032003",
					name : "\u5b9e\u4e60\u751f"
				}, {
					id : "1032999",
					name : "\u5176\u4ed6(\u5728\u6821\u5b66\u751f\u7c7b)"
				} ]
			},
			{
				id : "3420",
				name : "\u673a\u68b0\u2022\u4eea\u5668\u4eea\u8868\u7c7b",
				subItems : [
						{
							id : "3420",
							name : "\u673a\u68b0\u2022\u4eea\u5668\u4eea\u8868\u7c7b"
						},
						{
							id : "1004001",
							name : "\u673a\u68b0\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004002",
							name : "\u6a21\u5177\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004003",
							name : "\u673a\u68b0\u8bbe\u8ba1\u5e08"
						},
						{
							id : "1004004",
							name : "\u673a\u68b0\u5236\u56fe\u5458"
						},
						{
							id : "2800",
							name : "\u673a\u7535\u5de5\u7a0b\u5e08"
						},
						{
							id : "2900",
							name : "\u7cbe\u5bc6\u673a\u68b0\u2022\u4eea\u5668\u4eea\u8868\u5de5\u7a0b\u5e08\u2022\u6280\u672f\u5458"
						},
						{
							id : "1004005",
							name : "\u94f8\u9020\u2022\u953b\u9020\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004006",
							name : "\u6ce8\u5851\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004007",
							name : "CNC\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004008",
							name : "\u51b2\u538b\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004009",
							name : "\u5939\u5177\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004010",
							name : "\u9505\u7089\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004011",
							name : "\u710a\u63a5\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004012",
							name : "\u6c7d\u8f66\u2022\u6469\u6258\u8f66\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004013",
							name : "\u8239\u8236\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004014",
							name : "\u98de\u884c\u5668\u8bbe\u8ba1\u4e0e\u5236\u9020"
						},
						{
							id : "1004015",
							name : "\u673a\u68b0\u7ef4\u4fee\u5de5\u7a0b\u5e08"
						},
						{
							id : "1004016",
							name : "\u5305\u88c5\u2022\u5370\u5237\u673a\u68b0"
						},
						{
							id : "1004017",
							name : "\u98df\u54c1\u673a\u68b0"
						},
						{
							id : "1004018",
							name : "\u7eba\u7ec7\u673a\u68b0"
						},
						{
							id : "1004019",
							name : "\u8bbe\u5907\u4fee\u7406"
						},
						{
							id : "1004999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u673a\u68b0\u2022\u4eea\u5668\u4eea\u8868\u7c7b)"
						} ]
			},
			{
				id : "800",
				name : "\u79d1\u7814\u7c7b",
				subItems : [ {
					id : "800",
					name : "\u79d1\u7814\u7c7b"
				}, {
					id : "1027001",
					name : "\u79d1\u7814\u7ba1\u7406\u4eba\u5458"
				}, {
					id : "1027002",
					name : "\u79d1\u7814\u4eba\u5458"
				}, {
					id : "1027999",
					name : "\u5176\u4ed6\u804c\u4f4d(\u79d1\u7814\u7c7b)"
				} ]
			},
			{
				id : "3400",
				name : "\u5de5\u5382\u751f\u4ea7\u7c7b",
				subItems : [
						{
							id : "3400",
							name : "\u5de5\u5382\u751f\u4ea7\u7c7b"
						},
						{
							id : "1018001",
							name : "\u5382\u957f\u2022\u526f\u5382\u957f"
						},
						{
							id : "1018002",
							name : "\u603b\u5de5\u7a0b\u5e08\u2022\u526f\u603b\u5de5\u7a0b\u5e08"
						},
						{
							id : "1018010",
							name : "\u6280\u672f\u5de5\u7a0b\u5e08"
						},
						{
							id : "1018003",
							name : "\u91c7\u8d2d\u7ba1\u7406"
						},
						{
							id : "1018004",
							name : "\u7269\u6599\u6216\u7269\u6d41\u7ba1\u7406"
						},
						{
							id : "1018005",
							name : "\u5de5\u7a0b\u6216\u8bbe\u5907\u7ba1\u7406"
						},
						{
							id : "1018006",
							name : "\u5b89\u5168\u2022\u5065\u5eb7\u2022\u73af\u5883\u7ba1\u7406"
						},
						{
							id : "1018007",
							name : "\u4ea7\u54c1\u5f00\u53d1"
						},
						{
							id : "1018011",
							name : "\u6280\u672f\u6216\u5de5\u827a\u8bbe\u8ba1\u7ecf\u7406"
						},
						{
							id : "1018012",
							name : "\u8d28\u91cf\u7ba1\u7406(QA\u2022QC)"
						},
						{
							id : "1018013",
							name : "\u5316\u9a8c\u2022\u68c0\u9a8c"
						},
						{
							id : "3430",
							name : "\u4ed3\u5e93\u7ba1\u7406"
						},
						{
							id : "1018009",
							name : "\u7ef4\u4fee\u5de5\u7a0b\u5e08"
						},
						{
							id : "1018014",
							name : "\u751f\u4ea7\u7ecf\u7406\u2022\u8f66\u95f4\u4e3b\u4efb"
						},
						{
							id : "1018015",
							name : "\u7ec4\u957f\u2022\u62c9\u957f"
						},
						{
							id : "1018016",
							name : "\u8ba1\u5212\u2022\u8c03\u5ea6"
						},
						{
							id : "1018017",
							name : "\u4ea7\u54c1\u6216\u751f\u4ea7\u5de5\u827a\u5de5\u7a0b\u5e08(PE\u2022ME)"
						},
						{
							id : "1018018",
							name : "\u5de5\u4e1a\u5de5\u7a0b\u5e08\uff08IE\uff09"
						},
						{
							id : "1018019",
							name : "\u5236\u9020\u5de5\u7a0b\u5e08"
						},
						{
							id : "1018020",
							name : "\u751f\u4ea7\u6587\u5458"
						},
						{
							id : "1018999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u5de5\u5382\u751f\u4ea7\u7c7b)"
						} ]
			},
			{
				id : "2600",
				name : "\u516c\u52a1\u5458\u7c7b",
				subItems : [ {
					id : "2600",
					name : "\u516c\u52a1\u5458\u7c7b"
				} ]
			},
			{
				id : "2000",
				name : "\u533b\u7597\u536b\u751f\u2022\u7f8e\u5bb9\u4fdd\u5065\u7c7b",
				subItems : [
						{
							id : "2000",
							name : "\u533b\u7597\u536b\u751f\u2022\u7f8e\u5bb9\u4fdd\u5065\u7c7b"
						},
						{
							id : "1025001",
							name : "\u533b\u7597\u7ba1\u7406\u4eba\u5458"
						},
						{
							id : "1025002",
							name : "\u533b\u7597\u6280\u672f\u4eba\u5458"
						},
						{
							id : "1025003",
							name : "\u533b\u751f\u2022\u533b\u5e08"
						},
						{
							id : "1025018",
							name : "\u4e2d\u533b"
						},
						{
							id : "1025004",
							name : "\u5fc3\u7406\u533b\u751f"
						},
						{
							id : "1025005",
							name : "\u533b\u836f\u68c0\u9a8c"
						},
						{
							id : "1025006",
							name : "\u62a4\u58eb\u2022\u62a4\u7406\u4eba\u5458"
						},
						{
							id : "1025019",
							name : "\u8425\u517b\u5e08"
						},
						{
							id : "1025017",
							name : "\u836f\u5b66\u6280\u672f\u4e0e\u7ba1\u7406\u4eba\u5458"
						},
						{
							id : "1025008",
							name : "\u75be\u75c5\u63a7\u5236\u2022\u516c\u5171\u536b\u751f"
						},
						{
							id : "1025009",
							name : "\u7f8e\u5bb9\u2022\u6574\u5f62\u5e08"
						},
						{
							id : "1025010",
							name : "\u517d\u533b\u2022\u5ba0\u7269\u533b\u751f"
						},
						{
							id : "1025011",
							name : "\u836f\u5e93\u4e3b\u4efb\u2022\u836f\u5242\u5e08"
						},
						{
							id : "1025013",
							name : "\u9488\u7078\u63a8\u62ff"
						},
						{
							id : "1025014",
							name : "\u836f\u54c1\u6ce8\u518c"
						},
						{
							id : "1025015",
							name : "\u5065\u8eab\u987e\u95ee\u2022\u6559\u7ec3"
						},
						{
							id : "1025016",
							name : "\u533b\u836f\u4ee3\u8868"
						},
						{
							id : "1025020",
							name : "\u533b\u7597\u5668\u68b0\u9500\u552e"
						},
						{
							id : "1025999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u533b\u7597\u536b\u751f\u2022\u7f8e\u5bb9\u4fdd\u5065\u7c7b)"
						} ]
			},
			{
				id : "3700",
				name : "\u57f9\u8bad\u751f\u7c7b",
				subItems : [ {
					id : "3700",
					name : "\u57f9\u8bad\u751f\u7c7b"
				} ]
			},
			{
				id : "2700",
				name : "\u7535\u6c14\u2022\u80fd\u6e90\u2022\u52a8\u529b\u7c7b",
				subItems : [
						{
							id : "2700",
							name : "\u7535\u6c14\u2022\u80fd\u6e90\u2022\u52a8\u529b\u7c7b"
						},
						{
							id : "1003001",
							name : "\u7535\u6c14\u5de5\u7a0b\u5e08"
						},
						{
							id : "1003002",
							name : "\u5149\u6e90\u4e0e\u7167\u660e\u5de5\u7a0b"
						},
						{
							id : "1003003",
							name : "\u53d8\u538b\u5668\u4e0e\u78c1\u7535\u5de5\u7a0b\u5e08"
						},
						{
							id : "1003004",
							name : "\u7535\u8def\u5de5\u7a0b\u5e08"
						},
						{
							id : "2720",
							name : "\u667a\u80fd\u5927\u53a6\u2022\u7efc\u5408\u5e03\u7ebf\u2022\u5f31\u7535"
						},
						{
							id : "1003005",
							name : "\u7535\u529b\u5de5\u7a0b\u5e08"
						},
						{
							id : "1003006",
							name : "\u7535\u6c14\u7ef4\u4fee\u6280\u672f\u5458"
						},
						{
							id : "1003007",
							name : "\u6c34\u5229\u2022\u6c34\u7535\u5de5\u7a0b\u5e08"
						},
						{
							id : "1003008",
							name : "\u6838\u529b\u2022\u706b\u529b\u5de5\u7a0b\u5e08"
						},
						{
							id : "1003013",
							name : "\u71c3\u6c14\u8f6e\u673a\u5de5\u7a0b\u5e08"
						},
						{
							id : "1003009",
							name : "\u7a7a\u8c03\u2022\u70ed\u80fd\u5de5\u7a0b\u5e08"
						},
						{
							id : "1003010",
							name : "\u77f3\u6cb9\u5929\u7136\u6c14\u6280\u672f\u4eba\u5458"
						},
						{
							id : "1003011",
							name : "\u81ea\u52a8\u63a7\u5236"
						},
						{
							id : "1003012",
							name : "\u5236\u51b7\u2022\u6696\u901a"
						},
						{
							id : "1003999",
							name : "\u5176\u4ed6\u804c\u4f4d(\u7535\u6c14\u2022\u80fd\u6e90\u2022\u52a8\u529b\u7c7b)"
						} ]
			},
			{
				id : "1033000",
				name : "\u5176\u4ed6\u7c7b",
				subItems : [
						{
							id : "1033000",
							name : "\u5176\u4ed6\u7c7b"
						},
						{
							id : "1033001",
							name : "\u822a\u7a7a\u822a\u5929"
						},
						{
							id : "1033002",
							name : "\u5b89\u5168\u6d88\u9632"
						},
						{
							id : "1033003",
							name : "\u58f0\u5149\u5b66\u6280\u672f\u2022\u6fc0\u5149\u6280\u672f"
						},
						{
							id : "1033004",
							name : "\u6d4b\u7ed8\u6280\u672f"
						},
						{
							id : "1033005",
							name : "\u5730\u8d28\u77ff\u4ea7\u51b6\u91d1"
						},
						{
							id : "1033006",
							name : "\u6c14\u8c61"
						},
						{
							id : "1033007",
							name : "\u519c\u6797\u7267\u6e14"
						},
						{
							id : "1033999",
							name : "\u5176\u4ed6\u7c7b\u522b\u804c\u4f4d(\u5176\u4ed6\u7c7b)"
						} ]
			} ]
}
function getLoc() {
	return [
			{
				id : "30000",
				name : "\u5317\u4eac\u5e02",
				subItems : [ {
					id : "300001",
					name : "\u4e1c\u57ce\u533a"
				}, {
					id : "300002",
					name : "\u897f\u57ce\u533a"
				}, {
					id : "300003",
					name : "\u5d07\u6587\u533a"
				}, {
					id : "300004",
					name : "\u5ba3\u6b66\u533a"
				}, {
					id : "300005",
					name : "\u671d\u9633\u533a"
				}, {
					id : "300006",
					name : "\u4e30\u53f0\u533a"
				}, {
					id : "300007",
					name : "\u77f3\u666f\u5c71\u533a"
				}, {
					id : "300008",
					name : "\u6d77\u6dc0\u533a"
				}, {
					id : "300009",
					name : "\u95e8\u5934\u6c9f\u533a"
				}, {
					id : "300010",
					name : "\u623f\u5c71\u533a"
				}, {
					id : "300011",
					name : "\u901a\u5dde\u533a"
				}, {
					id : "300012",
					name : "\u987a\u4e49\u533a"
				}, {
					id : "300013",
					name : "\u660c\u5e73\u533a"
				}, {
					id : "300014",
					name : "\u5927\u5174\u533a"
				}, {
					id : "300015",
					name : "\u6000\u67d4\u533a"
				}, {
					id : "300016",
					name : "\u5e73\u8c37\u533a"
				}, {
					id : "300017",
					name : "\u5bc6\u4e91\u53bf"
				}, {
					id : "300018",
					name : "\u5ef6\u5e86\u53bf"
				} ]
			},
			{
				id : "31000",
				name : "\u4e0a\u6d77\u5e02",
				subItems : [ {
					id : "3100001",
					name : "\u9ec4\u6d66\u533a"
				}, {
					id : "3100002",
					name : "\u5362\u6e7e\u533a"
				}, {
					id : "3100003",
					name : "\u5f90\u6c47\u533a"
				}, {
					id : "3100004",
					name : "\u957f\u5b81\u533a"
				}, {
					id : "3100005",
					name : "\u9759\u5b89\u533a"
				}, {
					id : "3100006",
					name : "\u666e\u9640\u533a"
				}, {
					id : "3100007",
					name : "\u95f8\u5317\u533a"
				}, {
					id : "3100008",
					name : "\u8679\u53e3\u533a"
				}, {
					id : "3100009",
					name : "\u6768\u6d66\u533a"
				}, {
					id : "3100010",
					name : "\u95f5\u884c\u533a"
				}, {
					id : "3100011",
					name : "\u5b9d\u5c71\u533a"
				}, {
					id : "3100012",
					name : "\u5609\u5b9a\u533a"
				}, {
					id : "3100013",
					name : "\u6d66\u4e1c\u65b0\u533a"
				}, {
					id : "3100014",
					name : "\u91d1\u5c71\u533a"
				}, {
					id : "3100015",
					name : "\u677e\u6c5f\u533a"
				}, {
					id : "3100016",
					name : "\u9752\u6d66\u533a"
				}, {
					id : "3100017",
					name : "\u5949\u8d24\u533a"
				}, {
					id : "3100018",
					name : "\u5d07\u660e\u53bf"
				} ]
			},
			{
				id : "32000",
				name : "\u5929\u6d25\u5e02",
				subItems : [ {
					id : "14001",
					name : "\u548c\u5e73\u533a"
				}, {
					id : "14002",
					name : "\u6cb3\u4e1c\u533a"
				}, {
					id : "14003",
					name : "\u6cb3\u897f\u533a"
				}, {
					id : "14004",
					name : "\u5357\u5f00\u533a"
				}, {
					id : "14005",
					name : "\u6cb3\u5317\u533a"
				}, {
					id : "14006",
					name : "\u7ea2\u6865\u533a"
				}, {
					id : "14007",
					name : "\u4e1c\u4e3d\u533a"
				}, {
					id : "14008",
					name : "\u897f\u9752\u533a"
				}, {
					id : "14009",
					name : "\u6d25\u5357\u533a"
				}, {
					id : "14010",
					name : "\u5317\u8fb0\u533a"
				}, {
					id : "14011",
					name : "\u6b66\u6e05\u533a"
				}, {
					id : "14012",
					name : "\u5b9d\u577b\u533a"
				}, {
					id : "14013",
					name : "\u6ee8\u6d77\u65b0\u533a"
				}, {
					id : "14014",
					name : "\u5b81\u6cb3\u53bf"
				}, {
					id : "14015",
					name : "\u9759\u6d77\u53bf"
				}, {
					id : "14016",
					name : "\u84df\u53bf"
				} ]
			},
			{
				id : "33000",
				name : "\u91cd\u5e86\u5e02",
				subItems : [ {
					id : "330001",
					name : "\u6e1d\u4e2d\u533a"
				}, {
					id : "330005",
					name : "\u5927\u6e21\u53e3\u533a"
				}, {
					id : "330009",
					name : "\u6c5f\u5317\u533a"
				}, {
					id : "330013",
					name : "\u6c99\u576a\u575d\u533a"
				}, {
					id : "330002",
					name : "\u53cc\u6865\u533a"
				}, {
					id : "330003",
					name : "\u5408\u5ddd\u533a"
				}, {
					id : "330006",
					name : "\u6e1d\u5317\u533a"
				}, {
					id : "330007",
					name : "\u6c38\u5ddd\u533a"
				}, {
					id : "330010",
					name : "\u5df4\u5357\u533a"
				}, {
					id : "330011",
					name : "\u5357\u5ddd\u533a"
				}, {
					id : "330016",
					name : "\u4e5d\u9f99\u5761\u533a"
				}, {
					id : "330014",
					name : "\u4e07\u5dde\u533a"
				}, {
					id : "330017",
					name : "\u6daa\u9675\u533a"
				}, {
					id : "330020",
					name : "\u9ed4\u6c5f\u533a"
				}, {
					id : "330019",
					name : "\u5357\u5cb8\u533a"
				}, {
					id : "330022",
					name : "\u5317\u789a\u533a"
				}, {
					id : "330023",
					name : "\u957f\u5bff\u533a"
				}, {
					id : "330037",
					name : "\u4e07\u76db\u533a"
				}, {
					id : "330038",
					name : "\u6c5f\u6d25\u533a"
				}, {
					id : "330004",
					name : "\u7da6\u6c5f\u53bf"
				}, {
					id : "330008",
					name : "\u6f7c\u5357\u53bf"
				}, {
					id : "330012",
					name : "\u94dc\u6881\u53bf"
				}, {
					id : "330015",
					name : "\u5927\u8db3\u53bf"
				}, {
					id : "330018",
					name : "\u8363\u660c\u53bf"
				}, {
					id : "330021",
					name : "\u74a7\u5c71\u53bf"
				}, {
					id : "330024",
					name : "\u57ab\u6c5f\u53bf"
				}, {
					id : "330025",
					name : "\u4e30\u90fd\u53bf"
				}, {
					id : "330026",
					name : "\u5fe0\u53bf"
				}, {
					id : "330027",
					name : "\u77f3\u67f1\u53bf"
				}, {
					id : "330028",
					name : "\u57ce\u53e3\u53bf"
				}, {
					id : "330029",
					name : "\u5f6d\u6c34\u53bf"
				}, {
					id : "330030",
					name : "\u6881\u5e73\u53bf"
				}, {
					id : "330031",
					name : "\u9149\u9633\u53bf"
				}, {
					id : "330032",
					name : "\u5f00\u53bf"
				}, {
					id : "330033",
					name : "\u79c0\u5c71\u53bf"
				}, {
					id : "330034",
					name : "\u5deb\u6eaa\u53bf"
				}, {
					id : "330035",
					name : "\u5deb\u5c71\u53bf"
				}, {
					id : "330036",
					name : "\u5949\u8282\u53bf"
				}, {
					id : "330039",
					name : "\u6b66\u9686\u53bf"
				}, {
					id : "330040",
					name : "\u4e91\u9633\u53bf"
				} ]
			},
			{
				id : "16000",
				name : "\u5e7f\u4e1c\u7701",
				subItems : [ {
					id : "40",
					name : "\u5e7f\u5dde\u5e02",
					subItems : [ {
						id : "4001",
						name : "\u8d8a\u79c0\u533a"
					}, {
						id : "4002",
						name : "\u82b1\u90fd\u533a"
					}, {
						id : "4003",
						name : "\u8354\u6e7e\u533a"
					}, {
						id : "4004",
						name : "\u5357\u6c99\u533a"
					}, {
						id : "4005",
						name : "\u6d77\u73e0\u533a"
					}, {
						id : "4006",
						name : "\u841d\u5c97\u533a"
					}, {
						id : "4007",
						name : "\u5929\u6cb3\u533a"
					}, {
						id : "4009",
						name : "\u767d\u4e91\u533a"
					}, {
						id : "4011",
						name : "\u9ec4\u57d4\u533a"
					}, {
						id : "4012",
						name : "\u756a\u79ba\u533a"
					}, {
						id : "4008",
						name : "\u589e\u57ce\u5e02"
					}, {
						id : "4013",
						name : "\u4ece\u5316\u5e02"
					} ]
				}, {
					id : "16010",
					name : "\u6f6e\u5dde\u5e02",
					subItems : [ {
						id : "1601001",
						name : "\u6e58\u6865\u533a"
					}, {
						id : "1601002",
						name : "\u6f6e\u5b89\u53bf"
					}, {
						id : "1601003",
						name : "\u9976\u5e73\u53bf"
					} ]
				}, {
					id : "225",
					name : "\u4e1c\u839e\u5e02"
				}, {
					id : "16050",
					name : "\u4f5b\u5c71\u5e02",
					subItems : [ {
						id : "1605001",
						name : "\u7985\u57ce\u533a"
					}, {
						id : "1605002",
						name : "\u5357\u6d77\u533a"
					}, {
						id : "1605003",
						name : "\u4e09\u6c34\u533a"
					}, {
						id : "1605004",
						name : "\u9ad8\u660e\u533a"
					}, {
						id : "16040",
						name : "\u987a\u5fb7\u533a"
					} ]
				}, {
					id : "16020",
					name : "\u60e0\u5dde\u5e02",
					subItems : [ {
						id : "1602001",
						name : "\u60e0\u57ce\u533a"
					}, {
						id : "1602002",
						name : "\u60e0\u9633\u533a"
					}, {
						id : "1602003",
						name : "\u535a\u7f57\u53bf"
					}, {
						id : "1602004",
						name : "\u60e0\u4e1c\u53bf"
					}, {
						id : "1602005",
						name : "\u9f99\u95e8\u53bf"
					} ]
				}, {
					id : "16030",
					name : "\u6e05\u8fdc\u5e02",
					subItems : [ {
						id : "1603001",
						name : "\u6e05\u57ce\u533a"
					}, {
						id : "1603003",
						name : "\u82f1\u5fb7\u5e02"
					}, {
						id : "1603004",
						name : "\u8fde\u5dde\u5e02"
					}, {
						id : "1603002",
						name : "\u8fde\u5357\u53bf"
					}, {
						id : "1603005",
						name : "\u4f5b\u5188\u53bf"
					}, {
						id : "1603006",
						name : "\u9633\u5c71\u53bf"
					}, {
						id : "1603007",
						name : "\u6e05\u65b0\u53bf"
					}, {
						id : "1603008",
						name : "\u8fde\u5c71\u53bf"
					} ]
				}, {
					id : "117",
					name : "\u6c55\u5934\u5e02",
					subItems : [ {
						id : "11701",
						name : "\u91d1\u5e73\u533a"
					}, {
						id : "11702",
						name : "\u9f99\u6e56\u533a"
					}, {
						id : "11703",
						name : "\u6fe0\u6c5f\u533a"
					}, {
						id : "11704",
						name : "\u6f6e\u9633\u533a"
					}, {
						id : "11705",
						name : "\u6f6e\u5357\u533a"
					}, {
						id : "11706",
						name : "\u6f84\u6d77\u533a"
					}, {
						id : "11707",
						name : "\u5357\u6fb3\u53bf"
					} ]
				}, {
					id : "125",
					name : "\u6df1\u5733\u5e02",
					subItems : [ {
						id : "12501",
						name : "\u798f\u7530\u533a"
					}, {
						id : "12502",
						name : "\u7f57\u6e56\u533a"
					}, {
						id : "12503",
						name : "\u5357\u5c71\u533a"
					}, {
						id : "12504",
						name : "\u5b9d\u5b89\u533a"
					}, {
						id : "12505",
						name : "\u9f99\u5c97\u533a"
					}, {
						id : "12506",
						name : "\u76d0\u7530\u533a"
					} ]
				}, {
					id : "16060",
					name : "\u6e5b\u6c5f\u5e02",
					subItems : [ {
						id : "1606001",
						name : "\u8d64\u574e\u533a"
					}, {
						id : "1606003",
						name : "\u971e\u5c71\u533a"
					}, {
						id : "1606005",
						name : "\u5761\u5934\u533a"
					}, {
						id : "1606006",
						name : "\u9ebb\u7ae0\u533a"
					}, {
						id : "1606007",
						name : "\u5ec9\u6c5f\u5e02"
					}, {
						id : "1606008",
						name : "\u96f7\u5dde\u5e02"
					}, {
						id : "1606009",
						name : "\u5434\u5ddd\u5e02"
					}, {
						id : "1606002",
						name : "\u9042\u6eaa\u53bf"
					}, {
						id : "1606004",
						name : "\u5f90\u95fb\u53bf"
					} ]
				}, {
					id : "16080",
					name : "\u8087\u5e86\u5e02",
					subItems : [ {
						id : "1608001",
						name : "\u7aef\u5dde\u533a"
					}, {
						id : "1608003",
						name : "\u9f0e\u6e56\u533a"
					}, {
						id : "1608004",
						name : "\u9ad8\u8981\u5e02"
					}, {
						id : "1608005",
						name : "\u56db\u4f1a\u5e02"
					}, {
						id : "1608002",
						name : "\u5fb7\u5e86\u53bf"
					}, {
						id : "1608006",
						name : "\u5e7f\u5b81\u53bf"
					}, {
						id : "1608007",
						name : "\u6000\u96c6\u53bf"
					}, {
						id : "1608008",
						name : "\u5c01\u5f00\u53bf"
					} ]
				}, {
					id : "16070",
					name : "\u4e2d\u5c71\u5e02"
				}, {
					id : "180",
					name : "\u73e0\u6d77\u5e02",
					subItems : [ {
						id : "18001",
						name : "\u9999\u6d32\u533a"
					}, {
						id : "18002",
						name : "\u6597\u95e8\u533a"
					}, {
						id : "18003",
						name : "\u91d1\u6e7e\u533a"
					} ]
				}, {
					id : "16090",
					name : "\u6c5f\u95e8\u5e02",
					subItems : [ {
						id : "1609001",
						name : "\u6c5f\u6d77\u533a"
					}, {
						id : "1609002",
						name : "\u84ec\u6c5f\u533a"
					}, {
						id : "1609003",
						name : "\u65b0\u4f1a\u533a"
					}, {
						id : "1609004",
						name : "\u53f0\u5c71\u5e02"
					}, {
						id : "1609005",
						name : "\u9e64\u5c71\u5e02"
					}, {
						id : "1609006",
						name : "\u6069\u5e73\u5e02"
					}, {
						id : "16150",
						name : "\u5f00\u5e73\u5e02"
					} ]
				}, {
					id : "16100",
					name : "\u6c55\u5c3e\u5e02",
					subItems : [ {
						id : "1610001",
						name : "\u6c55\u5c3e\u57ce\u533a"
					}, {
						id : "1610002",
						name : "\u9646\u4e30\u5e02"
					}, {
						id : "1610003",
						name : "\u6d77\u4e30\u53bf"
					}, {
						id : "1610004",
						name : "\u9646\u6cb3\u53bf"
					} ]
				}, {
					id : "16110",
					name : "\u97f6\u5173\u5e02",
					subItems : [ {
						id : "1611001",
						name : "\u6d48\u6c5f\u533a"
					}, {
						id : "1611003",
						name : "\u6b66\u6c5f\u533a"
					}, {
						id : "1611005",
						name : "\u66f2\u6c5f\u533a"
					}, {
						id : "1611007",
						name : "\u4e50\u660c\u5e02"
					}, {
						id : "1611008",
						name : "\u5357\u96c4\u5e02"
					}, {
						id : "1611002",
						name : "\u7fc1\u6e90\u53bf"
					}, {
						id : "1611004",
						name : "\u65b0\u4e30\u53bf"
					}, {
						id : "1611006",
						name : "\u4e73\u6e90\u53bf"
					}, {
						id : "1611009",
						name : "\u59cb\u5174\u53bf"
					}, {
						id : "1611010",
						name : "\u4ec1\u5316\u53bf"
					} ]
				}, {
					id : "16120",
					name : "\u8302\u540d\u5e02",
					subItems : [ {
						id : "1612001",
						name : "\u8302\u5357\u533a"
					}, {
						id : "1612002",
						name : "\u8302\u6e2f\u533a"
					}, {
						id : "1612003",
						name : "\u9ad8\u5dde\u5e02"
					}, {
						id : "1612004",
						name : "\u5316\u5dde\u5e02"
					}, {
						id : "1612005",
						name : "\u4fe1\u5b9c\u5e02"
					}, {
						id : "1612006",
						name : "\u7535\u767d\u53bf"
					} ]
				}, {
					id : "16130",
					name : "\u6cb3\u6e90\u5e02",
					subItems : [ {
						id : "1613001",
						name : "\u6e90\u57ce\u533a"
					}, {
						id : "1613002",
						name : "\u7d2b\u91d1\u53bf"
					}, {
						id : "1613003",
						name : "\u9f99\u5ddd\u53bf"
					}, {
						id : "1613004",
						name : "\u8fde\u5e73\u53bf"
					}, {
						id : "1613005",
						name : "\u548c\u5e73\u53bf"
					}, {
						id : "1613006",
						name : "\u4e1c\u6e90\u53bf"
					} ]
				}, {
					id : "16140",
					name : "\u6885\u5dde\u5e02",
					subItems : [ {
						id : "1614001",
						name : "\u6885\u6c5f\u533a"
					}, {
						id : "1614003",
						name : "\u5174\u5b81\u5e02"
					}, {
						id : "1614002",
						name : "\u8549\u5cad\u53bf"
					}, {
						id : "1614004",
						name : "\u6885\u53bf"
					}, {
						id : "1614005",
						name : "\u5927\u57d4\u53bf"
					}, {
						id : "1614006",
						name : "\u4e30\u987a\u53bf"
					}, {
						id : "1614007",
						name : "\u4e94\u534e\u53bf"
					}, {
						id : "1614008",
						name : "\u5e73\u8fdc\u53bf"
					} ]
				}, {
					id : "16160",
					name : "\u9633\u6c5f\u5e02",
					subItems : [ {
						id : "1616001",
						name : "\u6c5f\u57ce\u533a"
					}, {
						id : "1616002",
						name : "\u9633\u6625\u5e02"
					}, {
						id : "1616003",
						name : "\u9633\u897f\u53bf"
					}, {
						id : "1616004",
						name : "\u9633\u4e1c\u53bf"
					} ]
				}, {
					id : "16170",
					name : "\u63ed\u9633\u5e02",
					subItems : [ {
						id : "1617001",
						name : "\u6995\u57ce\u533a"
					}, {
						id : "1617002",
						name : "\u666e\u5b81\u5e02"
					}, {
						id : "1617003",
						name : "\u63ed\u4e1c\u53bf"
					}, {
						id : "1617004",
						name : "\u63ed\u897f\u53bf"
					}, {
						id : "1617005",
						name : "\u60e0\u6765\u53bf"
					} ]
				}, {
					id : "16180",
					name : "\u4e91\u6d6e\u5e02",
					subItems : [ {
						id : "1618001",
						name : "\u4e91\u57ce\u533a"
					}, {
						id : "1618002",
						name : "\u7f57\u5b9a\u5e02"
					}, {
						id : "1618003",
						name : "\u65b0\u5174\u53bf"
					}, {
						id : "1618004",
						name : "\u90c1\u5357\u53bf"
					}, {
						id : "1618005",
						name : "\u4e91\u5b89\u53bf"
					} ]
				} ]
			},
			{
				id : "7000",
				name : "\u6c5f\u82cf\u7701",
				subItems : [ {
					id : "100",
					name : "\u5357\u4eac\u5e02",
					subItems : [ {
						id : "10001",
						name : "\u7384\u6b66\u533a"
					}, {
						id : "10002",
						name : "\u6816\u971e\u533a"
					}, {
						id : "10003",
						name : "\u767d\u4e0b\u533a"
					}, {
						id : "10004",
						name : "\u96e8\u82b1\u53f0\u533a"
					}, {
						id : "10005",
						name : "\u79e6\u6dee\u533a"
					}, {
						id : "10006",
						name : "\u6c5f\u5b81\u533a"
					}, {
						id : "10007",
						name : "\u5efa\u90ba\u533a"
					}, {
						id : "10008",
						name : "\u516d\u5408\u533a"
					}, {
						id : "10009",
						name : "\u9f13\u697c\u533a"
					}, {
						id : "10011",
						name : "\u4e0b\u5173\u533a"
					}, {
						id : "10012",
						name : "\u9ad8\u6df3\u53bf"
					}, {
						id : "10013",
						name : "\u6d66\u53e3\u533a"
					}, {
						id : "10014",
						name : "\u6ea7\u6c34\u53bf"
					} ]
				}, {
					id : "18",
					name : "\u5e38\u5dde\u5e02",
					subItems : [ {
						id : "1801",
						name : "\u5929\u5b81\u533a"
					}, {
						id : "1802",
						name : "\u949f\u697c\u533a"
					}, {
						id : "1803",
						name : "\u621a\u5885\u5830\u533a"
					}, {
						id : "1804",
						name : "\u65b0\u5317\u533a"
					}, {
						id : "1805",
						name : "\u6b66\u8fdb\u533a"
					}, {
						id : "7180",
						name : "\u6ea7\u9633"
					}, {
						id : "1806",
						name : "\u91d1\u575b\u5e02"
					} ]
				}, {
					id : "7070",
					name : "\u8fde\u4e91\u6e2f\u5e02",
					subItems : [ {
						id : "707001",
						name : "\u65b0\u6d66\u533a"
					}, {
						id : "707002",
						name : "\u8fde\u4e91\u533a"
					}, {
						id : "707003",
						name : "\u6d77\u5dde\u533a"
					}, {
						id : "707004",
						name : "\u8d63\u6986\u53bf"
					}, {
						id : "707005",
						name : "\u4e1c\u6d77\u53bf"
					}, {
						id : "707006",
						name : "\u704c\u4e91\u53bf"
					}, {
						id : "707007",
						name : "\u704c\u5357\u53bf"
					} ]
				}, {
					id : "7060",
					name : "\u5357\u901a\u5e02",
					subItems : [ {
						id : "706001",
						name : "\u5d07\u5ddd\u533a"
					}, {
						id : "706003",
						name : "\u6e2f\u95f8\u533a"
					}, {
						id : "706002",
						name : "\u6d77\u95e8\u5e02"
					}, {
						id : "706006",
						name : "\u542f\u4e1c\u5e02"
					}, {
						id : "706007",
						name : "\u5982\u768b\u5e02"
					}, {
						id : "706008",
						name : "\u901a\u5dde\u5e02"
					}, {
						id : "706004",
						name : "\u6d77\u5b89\u53bf"
					}, {
						id : "706005",
						name : "\u5982\u4e1c\u53bf"
					} ]
				}, {
					id : "220",
					name : "\u82cf\u5dde\u5e02",
					subItems : [ {
						id : "22001",
						name : "\u6ca7\u6d6a\u533a"
					}, {
						id : "22002",
						name : "\u5e73\u6c5f\u533a"
					}, {
						id : "22003",
						name : "\u91d1\u960a\u533a"
					}, {
						id : "22004",
						name : "\u864e\u4e18\u533a"
					}, {
						id : "22005",
						name : "\u5434\u4e2d\u533a"
					}, {
						id : "22006",
						name : "\u76f8\u57ce\u533a"
					}, {
						id : "7010",
						name : "\u5e38\u719f\u5e02"
					}, {
						id : "7110",
						name : "\u5f20\u5bb6\u6e2f\u5e02"
					}, {
						id : "7100",
						name : "\u5434\u6c5f\u5e02"
					}, {
						id : "7020",
						name : "\u6606\u5c71\u5e02"
					}, {
						id : "7040",
						name : "\u592a\u4ed3\u5e02"
					} ]
				}, {
					id : "152",
					name : "\u65e0\u9521\u5e02",
					subItems : [ {
						id : "15201",
						name : "\u5d07\u5b89\u533a"
					}, {
						id : "15202",
						name : "\u5357\u957f\u533a"
					}, {
						id : "15203",
						name : "\u5317\u5858\u533a"
					}, {
						id : "15204",
						name : "\u6ee8\u6e56\u533a"
					}, {
						id : "15205",
						name : "\u9521\u5c71\u533a"
					}, {
						id : "15206",
						name : "\u60e0\u5c71\u533a"
					}, {
						id : "7190",
						name : "\u5b9c\u5174\u5e02"
					}, {
						id : "7130",
						name : "\u6c5f\u9634\u5e02"
					} ]
				}, {
					id : "7030",
					name : "\u5f90\u5dde\u5e02",
					subItems : [ {
						id : "703001",
						name : "\u9f13\u697c\u533a"
					}, {
						id : "703003",
						name : "\u4e91\u9f99\u533a"
					}, {
						id : "703005",
						name : "\u4e5d\u91cc\u533a"
					}, {
						id : "703009",
						name : "\u6cc9\u5c71\u533a"
					}, {
						id : "703007",
						name : "\u8d3e\u6c6a\u533a"
					}, {
						id : "703006",
						name : "\u65b0\u6c82\u5e02"
					}, {
						id : "703008",
						name : "\u90b3\u5dde\u5e02"
					}, {
						id : "703004",
						name : "\u7762\u5b81\u53bf"
					}, {
						id : "703002",
						name : "\u94dc\u5c71\u53bf"
					}, {
						id : "703010",
						name : "\u4e30\u53bf"
					}, {
						id : "703011",
						name : "\u6c9b\u53bf"
					} ]
				}, {
					id : "167",
					name : "\u626c\u5dde\u5e02",
					subItems : [ {
						id : "16701",
						name : "\u5e7f\u9675\u533a"
					}, {
						id : "16702",
						name : "\u9097\u6c5f\u533a"
					}, {
						id : "16703",
						name : "\u7ef4\u626c\u533a"
					}, {
						id : "16705",
						name : "\u4eea\u5f81\u5e02"
					}, {
						id : "16706",
						name : "\u9ad8\u90ae\u5e02"
					}, {
						id : "16707",
						name : "\u6c5f\u90fd\u5e02"
					}, {
						id : "16704",
						name : "\u5b9d\u5e94\u53bf"
					} ]
				}, {
					id : "7080",
					name : "\u9547\u6c5f\u5e02",
					subItems : [ {
						id : "708001",
						name : "\u4eac\u53e3\u533a"
					}, {
						id : "708002",
						name : "\u6da6\u5dde\u533a"
					}, {
						id : "708003",
						name : "\u4e39\u5f92\u533a"
					}, {
						id : "7140",
						name : "\u4e39\u9633\u5e02"
					}, {
						id : "708004",
						name : "\u626c\u4e2d\u5e02"
					}, {
						id : "708005",
						name : "\u53e5\u5bb9\u5e02"
					} ]
				}, {
					id : "7090",
					name : "\u76d0\u57ce\u5e02",
					subItems : [ {
						id : "709001",
						name : "\u4ead\u6e56\u533a"
					}, {
						id : "709003",
						name : "\u76d0\u90fd\u533a"
					}, {
						id : "709002",
						name : "\u4e1c\u53f0\u5e02"
					}, {
						id : "709004",
						name : "\u5927\u4e30\u5e02"
					}, {
						id : "709005",
						name : "\u54cd\u6c34\u53bf"
					}, {
						id : "709006",
						name : "\u6ee8\u6d77\u53bf"
					}, {
						id : "709007",
						name : "\u961c\u5b81\u53bf"
					}, {
						id : "709008",
						name : "\u5c04\u9633\u53bf"
					}, {
						id : "709009",
						name : "\u5efa\u6e56\u53bf"
					} ]
				}, {
					id : "7120",
					name : "\u6cf0\u5dde\u5e02",
					subItems : [ {
						id : "712001",
						name : "\u6d77\u9675\u533a"
					}, {
						id : "712002",
						name : "\u9ad8\u6e2f\u533a"
					}, {
						id : "712003",
						name : "\u5174\u5316\u5e02"
					}, {
						id : "712004",
						name : "\u9756\u6c5f\u5e02"
					}, {
						id : "712005",
						name : "\u59dc\u5830\u5e02"
					}, {
						id : "7150",
						name : "\u6cf0\u5174\u5e02"
					} ]
				}, {
					id : "7160",
					name : "\u6dee\u5b89\u5e02",
					subItems : [ {
						id : "716001",
						name : "\u6e05\u6cb3\u533a"
					}, {
						id : "716003",
						name : "\u6e05\u6d66\u533a"
					}, {
						id : "716004",
						name : "\u695a\u5dde\u533a"
					}, {
						id : "716005",
						name : "\u6dee\u9634\u533a"
					}, {
						id : "716002",
						name : "\u91d1\u6e56\u53bf"
					}, {
						id : "716006",
						name : "\u6d9f\u6c34\u53bf"
					}, {
						id : "716007",
						name : "\u6d2a\u6cfd\u53bf"
					}, {
						id : "716008",
						name : "\u76f1\u7719\u53bf"
					} ]
				}, {
					id : "7170",
					name : "\u5bbf\u8fc1\u5e02",
					subItems : [ {
						id : "717001",
						name : "\u5bbf\u57ce\u533a"
					}, {
						id : "717002",
						name : "\u5bbf\u8c6b\u533a"
					}, {
						id : "717003",
						name : "\u6cad\u9633\u53bf"
					}, {
						id : "717004",
						name : "\u6cd7\u9633\u53bf"
					}, {
						id : "717005",
						name : "\u6cd7\u6d2a\u53bf"
					} ]
				} ]
			},
			{
				id : "8000",
				name : "\u6d59\u6c5f\u7701",
				subItems : [ {
					id : "55",
					name : "\u676d\u5dde\u5e02",
					subItems : [ {
						id : "5501",
						name : "\u62f1\u5885\u533a"
					}, {
						id : "5502",
						name : "\u4f59\u676d\u533a"
					}, {
						id : "5503",
						name : "\u4e0a\u57ce\u533a"
					}, {
						id : "5505",
						name : "\u4e0b\u57ce\u533a"
					}, {
						id : "5507",
						name : "\u6c5f\u5e72\u533a"
					}, {
						id : "5509",
						name : "\u897f\u6e56\u533a"
					}, {
						id : "8130",
						name : "\u8427\u5c71\u533a"
					}, {
						id : "5511",
						name : "\u6ee8\u6c5f\u533a"
					}, {
						id : "5504",
						name : "\u5efa\u5fb7\u5e02"
					}, {
						id : "5506",
						name : "\u5bcc\u9633\u5e02"
					}, {
						id : "5508",
						name : "\u4e34\u5b89\u5e02"
					}, {
						id : "5510",
						name : "\u6850\u5e90\u53bf"
					}, {
						id : "5512",
						name : "\u6df3\u5b89\u53bf"
					} ]
				}, {
					id : "107",
					name : "\u5b81\u6ce2\u5e02",
					subItems : [ {
						id : "10701",
						name : "\u6d77\u66d9\u533a"
					}, {
						id : "10702",
						name : "\u6c5f\u4e1c\u533a"
					}, {
						id : "10704",
						name : "\u6c5f\u5317\u533a"
					}, {
						id : "10706",
						name : "\u5317\u4ed1\u533a"
					}, {
						id : "10708",
						name : "\u9547\u6d77\u533a"
					}, {
						id : "10709",
						name : "\u911e\u5dde\u533a"
					}, {
						id : "10703",
						name : "\u5949\u5316\u5e02"
					}, {
						id : "10710",
						name : "\u4f59\u59da\u5e02"
					}, {
						id : "8120",
						name : "\u6148\u6eaa\u5e02"
					}, {
						id : "10705",
						name : "\u8c61\u5c71\u53bf"
					}, {
						id : "10707",
						name : "\u5b81\u6d77\u53bf"
					} ]
				}, {
					id : "147",
					name : "\u6e29\u5dde\u5e02",
					subItems : [ {
						id : "14701",
						name : "\u9e7f\u57ce\u533a"
					}, {
						id : "14703",
						name : "\u9f99\u6e7e\u533a"
					}, {
						id : "14705",
						name : "\u74ef\u6d77\u533a"
					}, {
						id : "14709",
						name : "\u4e50\u6e05\u5e02"
					}, {
						id : "14707",
						name : "\u745e\u5b89\u5e02"
					}, {
						id : "14704",
						name : "\u82cd\u5357\u53bf"
					}, {
						id : "14702",
						name : "\u5e73\u9633\u53bf"
					}, {
						id : "14706",
						name : "\u6587\u6210\u53bf"
					}, {
						id : "14708",
						name : "\u6cf0\u987a\u53bf"
					}, {
						id : "14710",
						name : "\u6d1e\u5934\u53bf"
					}, {
						id : "14711",
						name : "\u6c38\u5609\u53bf"
					} ]
				}, {
					id : "8050",
					name : "\u7ecd\u5174\u5e02",
					subItems : [ {
						id : "805001",
						name : "\u8d8a\u57ce\u533a"
					}, {
						id : "805002",
						name : "\u8bf8\u66a8\u5e02"
					}, {
						id : "805003",
						name : "\u4e0a\u865e\u5e02"
					}, {
						id : "805004",
						name : "\u5d4a\u5dde\u5e02"
					}, {
						id : "805005",
						name : "\u7ecd\u5174\u53bf"
					}, {
						id : "805006",
						name : "\u65b0\u660c\u53bf"
					} ]
				}, {
					id : "8060",
					name : "\u91d1\u534e\u5e02",
					subItems : [ {
						id : "806001",
						name : "\u5a7a\u57ce\u533a"
					}, {
						id : "806003",
						name : "\u91d1\u4e1c\u533a"
					}, {
						id : "806005",
						name : "\u5170\u6eaa\u5e02"
					}, {
						id : "806006",
						name : "\u4e49\u4e4c\u5e02"
					}, {
						id : "806007",
						name : "\u4e1c\u9633\u5e02"
					}, {
						id : "806008",
						name : "\u6c38\u5eb7\u5e02"
					}, {
						id : "806004",
						name : "\u78d0\u5b89\u53bf"
					}, {
						id : "806002",
						name : "\u6d66\u6c5f\u53bf"
					}, {
						id : "806009",
						name : "\u6b66\u4e49\u53bf"
					} ]
				}, {
					id : "8080",
					name : "\u53f0\u5dde\u5e02",
					subItems : [ {
						id : "808001",
						name : "\u6912\u6c5f\u533a"
					}, {
						id : "808003",
						name : "\u9ec4\u5ca9\u533a"
					}, {
						id : "808005",
						name : "\u8def\u6865\u533a"
					}, {
						id : "808006",
						name : "\u6e29\u5cad\u5e02"
					}, {
						id : "808007",
						name : "\u4e34\u6d77\u5e02"
					}, {
						id : "808002",
						name : "\u5929\u53f0\u53bf"
					}, {
						id : "808004",
						name : "\u4ed9\u5c45\u53bf"
					}, {
						id : "808008",
						name : "\u7389\u73af\u53bf"
					}, {
						id : "808009",
						name : "\u4e09\u95e8\u53bf"
					} ]
				}, {
					id : "8090",
					name : "\u6e56\u5dde\u5e02",
					subItems : [ {
						id : "809001",
						name : "\u5434\u5174\u533a"
					}, {
						id : "809002",
						name : "\u5357\u6d54\u533a"
					}, {
						id : "809003",
						name : "\u5fb7\u6e05\u53bf"
					}, {
						id : "809004",
						name : "\u957f\u5174\u53bf"
					}, {
						id : "809005",
						name : "\u5b89\u5409\u53bf"
					} ]
				}, {
					id : "73",
					name : "\u5609\u5174\u5e02",
					subItems : [ {
						id : "7301",
						name : "\u5357\u6e56\u533a"
					}, {
						id : "7302",
						name : "\u79c0\u6d32\u533a"
					}, {
						id : "7303",
						name : "\u6d77\u5b81\u5e02"
					}, {
						id : "7304",
						name : "\u5e73\u6e56\u5e02"
					}, {
						id : "7305",
						name : "\u6850\u4e61\u5e02"
					}, {
						id : "7306",
						name : "\u5609\u5584\u53bf"
					}, {
						id : "7307",
						name : "\u6d77\u76d0\u53bf"
					} ]
				}, {
					id : "8110",
					name : "\u8862\u5dde\u5e02",
					subItems : [ {
						id : "811001",
						name : "\u67ef\u57ce\u533a"
					}, {
						id : "811002",
						name : "\u8862\u6c5f\u533a"
					}, {
						id : "811003",
						name : "\u6c5f\u5c71\u5e02"
					}, {
						id : "811004",
						name : "\u5e38\u5c71\u53bf"
					}, {
						id : "811005",
						name : "\u5f00\u5316\u53bf"
					}, {
						id : "811006",
						name : "\u9f99\u6e38\u53bf"
					} ]
				}, {
					id : "8100",
					name : "\u4e3d\u6c34\u5e02",
					subItems : [ {
						id : "810001",
						name : "\u83b2\u90fd\u533a"
					}, {
						id : "810003",
						name : "\u9f99\u6cc9\u5e02"
					}, {
						id : "810002",
						name : "\u5e86\u5143\u53bf"
					}, {
						id : "810004",
						name : "\u666f\u5b81\u53bf"
					}, {
						id : "810005",
						name : "\u9752\u7530\u53bf"
					}, {
						id : "810006",
						name : "\u7f19\u4e91\u53bf"
					}, {
						id : "810007",
						name : "\u9042\u660c\u53bf"
					}, {
						id : "810008",
						name : "\u677e\u9633\u53bf"
					}, {
						id : "810009",
						name : "\u4e91\u548c\u53bf"
					} ]
				}, {
					id : "8070",
					name : "\u821f\u5c71\u5e02",
					subItems : [ {
						id : "807001",
						name : "\u5b9a\u6d77\u533a"
					}, {
						id : "807002",
						name : "\u666e\u9640\u533a"
					}, {
						id : "807003",
						name : "\u5cb1\u5c71\u53bf"
					}, {
						id : "807004",
						name : "\u5d4a\u6cd7\u53bf"
					} ]
				} ]
			},
			{
				id : "9000",
				name : "\u5b89\u5fbd\u7701",
				subItems : [ {
					id : "65",
					name : "\u5408\u80a5\u5e02",
					subItems : [ {
						id : "6501",
						name : "\u5e90\u9633\u533a"
					}, {
						id : "6502",
						name : "\u7476\u6d77\u533a"
					}, {
						id : "6503",
						name : "\u8700\u5c71\u533a"
					}, {
						id : "6504",
						name : "\u5305\u6cb3\u533a"
					}, {
						id : "6505",
						name : "\u957f\u4e30\u53bf"
					}, {
						id : "6506",
						name : "\u80a5\u4e1c\u53bf"
					}, {
						id : "6507",
						name : "\u80a5\u897f\u53bf"
					} ]
				}, {
					id : "9040",
					name : "\u5b89\u5e86\u5e02",
					subItems : [ {
						id : "904001",
						name : "\u8fce\u6c5f\u533a"
					}, {
						id : "904003",
						name : "\u5927\u89c2\u533a"
					}, {
						id : "904005",
						name : "\u5b9c\u79c0\u533a"
					}, {
						id : "904007",
						name : "\u6850\u57ce\u5e02"
					}, {
						id : "904002",
						name : "\u6000\u5b81\u53bf"
					}, {
						id : "904004",
						name : "\u5cb3\u897f\u53bf"
					}, {
						id : "904006",
						name : "\u671b\u6c5f\u53bf"
					}, {
						id : "904008",
						name : "\u6f5c\u5c71\u53bf"
					}, {
						id : "904009",
						name : "\u5bbf\u677e\u53bf"
					}, {
						id : "904010",
						name : "\u679e\u9633\u53bf"
					}, {
						id : "904011",
						name : "\u592a\u6e56\u53bf"
					} ]
				}, {
					id : "9030",
					name : "\u868c\u57e0\u5e02",
					subItems : [ {
						id : "903001",
						name : "\u868c\u5c71\u533a"
					}, {
						id : "903002",
						name : "\u9f99\u5b50\u6e56\u533a"
					}, {
						id : "903003",
						name : "\u79b9\u4f1a\u533a"
					}, {
						id : "903004",
						name : "\u6dee\u4e0a\u533a"
					}, {
						id : "903005",
						name : "\u6000\u8fdc\u53bf"
					}, {
						id : "903006",
						name : "\u56fa\u9547\u53bf"
					}, {
						id : "903007",
						name : "\u4e94\u6cb3\u53bf"
					} ]
				}, {
					id : "9020",
					name : "\u829c\u6e56\u5e02",
					subItems : [ {
						id : "902001",
						name : "\u955c\u6e56\u533a"
					}, {
						id : "902002",
						name : "\u5f0b\u6c5f\u533a"
					}, {
						id : "902003",
						name : "\u9e20\u6c5f\u533a"
					}, {
						id : "902004",
						name : "\u4e09\u5c71\u533a"
					}, {
						id : "902005",
						name : "\u829c\u6e56\u53bf"
					}, {
						id : "902006",
						name : "\u5357\u9675\u53bf"
					}, {
						id : "902007",
						name : "\u7e41\u660c\u53bf"
					} ]
				}, {
					id : "9060",
					name : "\u6dee\u5357\u5e02",
					subItems : [ {
						id : "906001",
						name : "\u7530\u5bb6\u5eb5\u533a"
					}, {
						id : "906002",
						name : "\u5927\u901a\u533a"
					}, {
						id : "906003",
						name : "\u8c22\u5bb6\u96c6\u533a"
					}, {
						id : "906004",
						name : "\u516b\u516c\u5c71\u533a"
					}, {
						id : "906005",
						name : "\u6f58\u96c6\u533a"
					}, {
						id : "906006",
						name : "\u51e4\u53f0\u53bf"
					} ]
				}, {
					id : "9070",
					name : "\u9a6c\u978d\u5c71\u5e02",
					subItems : [ {
						id : "907001",
						name : "\u96e8\u5c71\u533a"
					}, {
						id : "907002",
						name : "\u82b1\u5c71\u533a"
					}, {
						id : "907003",
						name : "\u91d1\u5bb6\u5e84\u533a"
					}, {
						id : "907004",
						name : "\u5f53\u6d82\u53bf"
					} ]
				}, {
					id : "9080",
					name : "\u6dee\u5317\u5e02",
					subItems : [ {
						id : "908001",
						name : "\u76f8\u5c71\u533a"
					}, {
						id : "908002",
						name : "\u675c\u96c6\u533a"
					}, {
						id : "908003",
						name : "\u70c8\u5c71\u533a"
					}, {
						id : "908004",
						name : "\u6fc9\u6eaa\u53bf"
					} ]
				}, {
					id : "9090",
					name : "\u9ec4\u5c71\u5e02",
					subItems : [ {
						id : "909001",
						name : "\u5c6f\u6eaa\u533a"
					}, {
						id : "909002",
						name : "\u9ec4\u5c71\u533a"
					}, {
						id : "909003",
						name : "\u5fbd\u5dde\u533a"
					}, {
						id : "909004",
						name : "\u4f11\u5b81\u53bf"
					}, {
						id : "909005",
						name : "\u6b59\u53bf"
					}, {
						id : "909006",
						name : "\u7941\u95e8\u53bf"
					}, {
						id : "909007",
						name : "\u9edf\u53bf"
					} ]
				}, {
					id : "9100",
					name : "\u6ec1\u5dde\u5e02",
					subItems : [ {
						id : "910001",
						name : "\u7405\u740a\u533a"
					}, {
						id : "910003",
						name : "\u5357\u8c2f\u533a"
					}, {
						id : "910004",
						name : "\u5929\u957f\u5e02"
					}, {
						id : "910005",
						name : "\u660e\u5149\u5e02"
					}, {
						id : "910002",
						name : "\u51e4\u9633\u53bf"
					}, {
						id : "910006",
						name : "\u5168\u6912\u53bf"
					}, {
						id : "910007",
						name : "\u6765\u5b89\u53bf"
					}, {
						id : "910008",
						name : "\u5b9a\u8fdc\u53bf"
					} ]
				}, {
					id : "9110",
					name : "\u961c\u9633\u5e02",
					subItems : [ {
						id : "911001",
						name : "\u988d\u5dde\u533a"
					}, {
						id : "911003",
						name : "\u988d\u4e1c\u533a"
					}, {
						id : "911004",
						name : "\u988d\u6cc9\u533a"
					}, {
						id : "911005",
						name : "\u754c\u9996\u5e02"
					}, {
						id : "911002",
						name : "\u592a\u548c\u53bf"
					}, {
						id : "911006",
						name : "\u4e34\u6cc9\u53bf"
					}, {
						id : "911007",
						name : "\u988d\u4e0a\u53bf"
					}, {
						id : "911008",
						name : "\u961c\u5357\u53bf"
					} ]
				}, {
					id : "9120",
					name : "\u5bbf\u5dde\u5e02",
					subItems : [ {
						id : "912001",
						name : "\u57c7\u6865\u533a"
					}, {
						id : "912002",
						name : "\u8427\u53bf"
					}, {
						id : "912003",
						name : "\u6cd7\u53bf"
					}, {
						id : "912004",
						name : "\u7800\u5c71\u53bf"
					}, {
						id : "912005",
						name : "\u7075\u74a7\u53bf"
					} ]
				}, {
					id : "9130",
					name : "\u5de2\u6e56\u5e02",
					subItems : [ {
						id : "913001",
						name : "\u5c45\u5de2\u533a"
					}, {
						id : "913002",
						name : "\u542b\u5c71\u53bf"
					}, {
						id : "913003",
						name : "\u65e0\u4e3a\u53bf"
					}, {
						id : "913004",
						name : "\u5e90\u6c5f\u53bf"
					}, {
						id : "913005",
						name : "\u548c\u53bf"
					} ]
				}, {
					id : "9140",
					name : "\u516d\u5b89\u5e02",
					subItems : [ {
						id : "914001",
						name : "\u91d1\u5b89\u533a"
					}, {
						id : "914002",
						name : "\u88d5\u5b89\u533a"
					}, {
						id : "914003",
						name : "\u5bff\u53bf"
					}, {
						id : "914004",
						name : "\u970d\u5c71\u53bf"
					}, {
						id : "914005",
						name : "\u970d\u90b1\u53bf"
					}, {
						id : "914006",
						name : "\u8212\u57ce\u53bf"
					}, {
						id : "914007",
						name : "\u91d1\u5be8\u53bf"
					} ]
				}, {
					id : "9150",
					name : "\u4eb3\u5dde\u5e02",
					subItems : [ {
						id : "915001",
						name : "\u8c2f\u57ce\u533a"
					}, {
						id : "915002",
						name : "\u5229\u8f9b\u53bf"
					}, {
						id : "915003",
						name : "\u6da1\u9633\u53bf"
					}, {
						id : "915004",
						name : "\u8499\u57ce\u53bf"
					} ]
				}, {
					id : "9160",
					name : "\u6c60\u5dde\u5e02",
					subItems : [ {
						id : "916001",
						name : "\u8d35\u6c60\u533a"
					}, {
						id : "916002",
						name : "\u4e1c\u81f3\u53bf"
					}, {
						id : "916003",
						name : "\u77f3\u53f0\u53bf"
					}, {
						id : "916004",
						name : "\u9752\u9633\u53bf"
					} ]
				}, {
					id : "9170",
					name : "\u5ba3\u57ce\u5e02",
					subItems : [ {
						id : "917001",
						name : "\u5ba3\u5dde\u533a"
					}, {
						id : "9050",
						name : "\u5b81\u56fd\u5e02"
					}, {
						id : "917002",
						name : "\u5e7f\u5fb7\u53bf"
					}, {
						id : "917003",
						name : "\u90ce\u6eaa\u53bf"
					}, {
						id : "917004",
						name : "\u6cfe\u53bf"
					}, {
						id : "917005",
						name : "\u65cc\u5fb7\u53bf"
					}, {
						id : "917006",
						name : "\u7ee9\u6eaa\u53bf"
					} ]
				}, {
					id : "9180",
					name : "\u94dc\u9675\u5e02",
					subItems : [ {
						id : "918001",
						name : "\u94dc\u5b98\u5c71\u533a"
					}, {
						id : "918002",
						name : "\u72ee\u5b50\u5c71\u533a"
					}, {
						id : "918003",
						name : "\u94dc\u9675\u90ca\u533a"
					}, {
						id : "918004",
						name : "\u94dc\u9675\u53bf"
					} ]
				} ]
			},
			{
				id : "10000",
				name : "\u798f\u5efa\u7701",
				subItems : [ {
					id : "35",
					name : "\u798f\u5dde\u5e02",
					subItems : [ {
						id : "3501",
						name : "\u9f13\u697c\u533a"
					}, {
						id : "3503",
						name : "\u53f0\u6c5f\u533a"
					}, {
						id : "3507",
						name : "\u9a6c\u5c3e\u533a"
					}, {
						id : "3505",
						name : "\u4ed3\u5c71\u533a"
					}, {
						id : "3509",
						name : "\u664b\u5b89\u533a"
					}, {
						id : "3512",
						name : "\u957f\u4e50\u5e02"
					}, {
						id : "3510",
						name : "\u798f\u6e05\u5e02"
					}, {
						id : "3502",
						name : "\u7f57\u6e90\u53bf"
					}, {
						id : "3506",
						name : "\u6c38\u6cf0\u53bf"
					}, {
						id : "3504",
						name : "\u95fd\u6e05\u53bf"
					}, {
						id : "3508",
						name : "\u5e73\u6f6d\u53bf"
					}, {
						id : "3511",
						name : "\u95fd\u4faf\u53bf"
					}, {
						id : "3513",
						name : "\u8fde\u6c5f\u53bf"
					} ]
				}, {
					id : "10030",
					name : "\u6cc9\u5dde\u5e02",
					subItems : [ {
						id : "1003001",
						name : "\u9ca4\u57ce\u533a"
					}, {
						id : "1003003",
						name : "\u4e30\u6cfd\u533a"
					}, {
						id : "1003005",
						name : "\u6d1b\u6c5f\u533a"
					}, {
						id : "1003007",
						name : "\u6cc9\u6e2f\u533a"
					}, {
						id : "1003006",
						name : "\u77f3\u72ee\u5e02"
					}, {
						id : "1003008",
						name : "\u664b\u6c5f\u5e02"
					}, {
						id : "1003010",
						name : "\u5357\u5b89\u5e02"
					}, {
						id : "1003004",
						name : "\u91d1\u95e8\u53bf"
					}, {
						id : "1003002",
						name : "\u5fb7\u5316\u53bf"
					}, {
						id : "1003012",
						name : "\u6c38\u6625\u53bf"
					}, {
						id : "1003009",
						name : "\u60e0\u5b89\u53bf"
					}, {
						id : "1003011",
						name : "\u5b89\u6eaa\u53bf"
					} ]
				}, {
					id : "155",
					name : "\u53a6\u95e8\u5e02",
					subItems : [ {
						id : "15501",
						name : "\u601d\u660e\u533a"
					}, {
						id : "15502",
						name : "\u6d77\u6ca7\u533a"
					}, {
						id : "15503",
						name : "\u6e56\u91cc\u533a"
					}, {
						id : "15504",
						name : "\u96c6\u7f8e\u533a"
					}, {
						id : "15505",
						name : "\u540c\u5b89\u533a"
					}, {
						id : "15506",
						name : "\u7fd4\u5b89\u533a"
					} ]
				}, {
					id : "10040",
					name : "\u6f33\u5dde\u5e02",
					subItems : [ {
						id : "1004001",
						name : "\u8297\u57ce\u533a"
					}, {
						id : "1004003",
						name : "\u9f99\u6587\u533a"
					}, {
						id : "1004008",
						name : "\u9f99\u6d77\u5e02"
					}, {
						id : "1004004",
						name : "\u5e73\u548c\u53bf"
					}, {
						id : "1004002",
						name : "\u5357\u9756\u53bf"
					}, {
						id : "1004005",
						name : "\u4e91\u9704\u53bf"
					}, {
						id : "1004006",
						name : "\u534e\u5b89\u53bf"
					}, {
						id : "1004007",
						name : "\u6f33\u6d66\u53bf"
					}, {
						id : "1004009",
						name : "\u8bcf\u5b89\u53bf"
					}, {
						id : "1004010",
						name : "\u957f\u6cf0\u53bf"
					}, {
						id : "1004011",
						name : "\u4e1c\u5c71\u53bf"
					} ]
				}, {
					id : "10050",
					name : "\u8386\u7530\u5e02",
					subItems : [ {
						id : "1005001",
						name : "\u57ce\u53a2\u533a"
					}, {
						id : "1005002",
						name : "\u6db5\u6c5f\u533a"
					}, {
						id : "1005003",
						name : "\u8354\u57ce\u533a"
					}, {
						id : "1005004",
						name : "\u79c0\u5c7f\u533a"
					}, {
						id : "1005005",
						name : "\u4ed9\u6e38\u53bf"
					} ]
				}, {
					id : "10020",
					name : "\u9f99\u5ca9\u5e02",
					subItems : [ {
						id : "1002001",
						name : "\u65b0\u7f57\u533a"
					}, {
						id : "1002007",
						name : "\u6f33\u5e73\u5e02"
					}, {
						id : "1002002",
						name : "\u957f\u6c40\u53bf"
					}, {
						id : "1002003",
						name : "\u6c38\u5b9a\u53bf"
					}, {
						id : "1002004",
						name : "\u4e0a\u676d\u53bf"
					}, {
						id : "1002005",
						name : "\u6b66\u5e73\u53bf"
					}, {
						id : "1002006",
						name : "\u8fde\u57ce\u53bf"
					} ]
				}, {
					id : "10060",
					name : "\u5b81\u5fb7\u5e02",
					subItems : [ {
						id : "1006001",
						name : "\u8549\u57ce\u533a"
					}, {
						id : "1006003",
						name : "\u798f\u9f0e\u5e02"
					}, {
						id : "10010",
						name : "\u798f\u5b89\u5e02"
					}, {
						id : "1006004",
						name : "\u53e4\u7530\u53bf"
					}, {
						id : "1006002",
						name : "\u971e\u6d66\u53bf"
					}, {
						id : "1006005",
						name : "\u5c4f\u5357\u53bf"
					}, {
						id : "1006006",
						name : "\u5bff\u5b81\u53bf"
					}, {
						id : "1006007",
						name : "\u5468\u5b81\u53bf"
					}, {
						id : "1006008",
						name : "\u67d8\u8363\u53bf"
					} ]
				}, {
					id : "10070",
					name : "\u5357\u5e73\u5e02",
					subItems : [ {
						id : "1007001",
						name : "\u5ef6\u5e73\u533a"
					}, {
						id : "1007002",
						name : "\u6b66\u5937\u5c71\u5e02"
					}, {
						id : "1007004",
						name : "\u5efa\u74ef\u5e02"
					}, {
						id : "1007006",
						name : "\u5efa\u9633\u5e02"
					}, {
						id : "1007010",
						name : "\u90b5\u6b66\u5e02"
					}, {
						id : "1007003",
						name : "\u987a\u660c\u53bf"
					}, {
						id : "1007005",
						name : "\u6d66\u57ce\u53bf"
					}, {
						id : "1007007",
						name : "\u5149\u6cfd\u53bf"
					}, {
						id : "1007008",
						name : "\u677e\u6eaa\u53bf"
					}, {
						id : "1007009",
						name : "\u653f\u548c\u53bf"
					} ]
				}, {
					id : "10080",
					name : "\u4e09\u660e\u5e02",
					subItems : [ {
						id : "1008001",
						name : "\u6885\u5217\u533a"
					}, {
						id : "1008003",
						name : "\u4e09\u5143\u533a"
					}, {
						id : "1008010",
						name : "\u6c38\u5b89\u5e02"
					}, {
						id : "1008002",
						name : "\u6c99\u53bf"
					}, {
						id : "1008004",
						name : "\u5c06\u4e50\u53bf"
					}, {
						id : "1008005",
						name : "\u660e\u6eaa\u53bf"
					}, {
						id : "1008006",
						name : "\u6cf0\u5b81\u53bf"
					}, {
						id : "1008007",
						name : "\u6e05\u6d41\u53bf"
					}, {
						id : "1008008",
						name : "\u5efa\u5b81\u53bf"
					}, {
						id : "1008009",
						name : "\u5b81\u5316\u53bf"
					}, {
						id : "1008011",
						name : "\u5927\u7530\u53bf"
					}, {
						id : "1008012",
						name : "\u5c24\u6eaa\u53bf"
					} ]
				} ]
			},
			{
				id : "24000",
				name : "\u7518\u8083\u7701",
				subItems : [ {
					id : "85",
					name : "\u5170\u5dde\u5e02",
					subItems : [ {
						id : "8501",
						name : "\u57ce\u5173\u533a"
					}, {
						id : "8503",
						name : "\u4e03\u91cc\u6cb3\u533a"
					}, {
						id : "8504",
						name : "\u897f\u56fa\u533a"
					}, {
						id : "8505",
						name : "\u5b89\u5b81\u533a"
					}, {
						id : "8506",
						name : "\u7ea2\u53e4\u533a"
					}, {
						id : "8502",
						name : "\u6986\u4e2d\u53bf"
					}, {
						id : "8507",
						name : "\u6c38\u767b\u53bf"
					}, {
						id : "8508",
						name : "\u768b\u5170\u53bf"
					} ]
				}, {
					id : "24020",
					name : "\u5609\u5cea\u5173\u5e02"
				}, {
					id : "24030",
					name : "\u9152\u6cc9\u5e02",
					subItems : [ {
						id : "2403001",
						name : "\u8083\u5dde\u533a"
					}, {
						id : "2403002",
						name : "\u7389\u95e8\u5e02"
					}, {
						id : "2403003",
						name : "\u6566\u714c\u5e02"
					}, {
						id : "2403004",
						name : "\u91d1\u5854\u53bf"
					}, {
						id : "2403005",
						name : "\u74dc\u5dde\u53bf"
					}, {
						id : "2403006",
						name : "\u8083\u5317\u53bf"
					}, {
						id : "2403007",
						name : "\u963f\u514b\u585e\u53bf"
					} ]
				}, {
					id : "24040",
					name : "\u91d1\u660c\u5e02",
					subItems : [ {
						id : "2404001",
						name : "\u91d1\u5ddd\u533a"
					}, {
						id : "2404002",
						name : "\u6c38\u660c\u53bf"
					} ]
				}, {
					id : "24050",
					name : "\u767d\u94f6\u5e02",
					subItems : [ {
						id : "2405001",
						name : "\u767d\u94f6\u533a"
					}, {
						id : "2405002",
						name : "\u5e73\u5ddd\u533a"
					}, {
						id : "2405003",
						name : "\u9756\u8fdc\u53bf"
					}, {
						id : "2405004",
						name : "\u4f1a\u5b81\u53bf"
					}, {
						id : "2405005",
						name : "\u666f\u6cf0\u53bf"
					} ]
				}, {
					id : "24060",
					name : "\u5929\u6c34\u5e02",
					subItems : [ {
						id : "2406001",
						name : "\u79e6\u5dde\u533a"
					}, {
						id : "2406002",
						name : "\u9ea6\u79ef\u533a"
					}, {
						id : "2406003",
						name : "\u6e05\u6c34\u53bf"
					}, {
						id : "2406004",
						name : "\u79e6\u5b89\u53bf"
					}, {
						id : "2406005",
						name : "\u7518\u8c37\u53bf"
					}, {
						id : "2406006",
						name : "\u6b66\u5c71\u53bf"
					}, {
						id : "2406007",
						name : "\u5f20\u5bb6\u5ddd\u53bf"
					} ]
				}, {
					id : "24070",
					name : "\u6b66\u5a01\u5e02",
					subItems : [ {
						id : "2407001",
						name : "\u51c9\u5dde\u533a"
					}, {
						id : "2407002",
						name : "\u6c11\u52e4\u53bf"
					}, {
						id : "2407003",
						name : "\u53e4\u6d6a\u53bf"
					}, {
						id : "2407004",
						name : "\u5929\u795d\u53bf"
					} ]
				}, {
					id : "24080",
					name : "\u5f20\u6396\u5e02",
					subItems : [ {
						id : "2408001",
						name : "\u7518\u5dde\u533a"
					}, {
						id : "2408002",
						name : "\u6c11\u4e50\u53bf"
					}, {
						id : "2408003",
						name : "\u4e34\u6cfd\u53bf"
					}, {
						id : "2408004",
						name : "\u9ad8\u53f0\u53bf"
					}, {
						id : "2408005",
						name : "\u5c71\u4e39\u53bf"
					}, {
						id : "2408006",
						name : "\u8083\u5357\u53bf"
					} ]
				}, {
					id : "24090",
					name : "\u5e73\u51c9\u5e02",
					subItems : [ {
						id : "2409001",
						name : "\u5d06\u5cd2\u533a"
					}, {
						id : "2409002",
						name : "\u6cfe\u5ddd\u53bf"
					}, {
						id : "2409003",
						name : "\u7075\u53f0\u53bf"
					}, {
						id : "2409004",
						name : "\u5d07\u4fe1\u53bf"
					}, {
						id : "2409005",
						name : "\u534e\u4ead\u53bf"
					}, {
						id : "2409006",
						name : "\u5e84\u6d6a\u53bf"
					}, {
						id : "2409007",
						name : "\u9759\u5b81\u53bf"
					} ]
				}, {
					id : "24100",
					name : "\u5e86\u9633\u5e02",
					subItems : [ {
						id : "2410001",
						name : "\u897f\u5cf0\u533a"
					}, {
						id : "2410002",
						name : "\u9547\u539f\u53bf"
					}, {
						id : "2410003",
						name : "\u5e86\u57ce\u53bf"
					}, {
						id : "2410004",
						name : "\u73af\u53bf"
					}, {
						id : "2410005",
						name : "\u534e\u6c60\u53bf"
					}, {
						id : "2410006",
						name : "\u5408\u6c34\u53bf"
					}, {
						id : "2410007",
						name : "\u6b63\u5b81\u53bf"
					}, {
						id : "2410008",
						name : "\u5b81\u53bf"
					} ]
				}, {
					id : "24110",
					name : "\u5b9a\u897f\u5e02",
					subItems : [ {
						id : "2411001",
						name : "\u5b89\u5b9a\u533a"
					}, {
						id : "2411002",
						name : "\u901a\u6e2d\u53bf"
					}, {
						id : "2411003",
						name : "\u9647\u897f\u53bf"
					}, {
						id : "2411004",
						name : "\u6e2d\u6e90\u53bf"
					}, {
						id : "2411005",
						name : "\u4e34\u6d2e\u53bf"
					}, {
						id : "2411006",
						name : "\u6f33\u53bf"
					}, {
						id : "2411007",
						name : "\u5cb7\u53bf"
					} ]
				}, {
					id : "24120",
					name : "\u9647\u5357\u5e02",
					subItems : [ {
						id : "2412001",
						name : "\u6b66\u90fd\u533a"
					}, {
						id : "2412002",
						name : "\u5fbd\u53bf"
					}, {
						id : "2412003",
						name : "\u6210\u53bf"
					}, {
						id : "2412004",
						name : "\u4e24\u5f53\u53bf"
					}, {
						id : "2412005",
						name : "\u6587\u53bf"
					}, {
						id : "2412006",
						name : "\u5b95\u660c\u53bf"
					}, {
						id : "2412007",
						name : "\u5eb7\u53bf"
					}, {
						id : "2412008",
						name : "\u897f\u548c\u53bf"
					}, {
						id : "2412009",
						name : "\u793c\u53bf"
					} ]
				}, {
					id : "24130",
					name : "\u4e34\u590f\u5dde",
					subItems : [ {
						id : "2413001",
						name : "\u4e34\u590f\u5e02"
					}, {
						id : "2413002",
						name : "\u79ef\u77f3\u5c71\u53bf"
					}, {
						id : "2413003",
						name : "\u4e34\u590f\u53bf"
					}, {
						id : "2413004",
						name : "\u5eb7\u4e50\u53bf"
					}, {
						id : "2413005",
						name : "\u6c38\u9756\u53bf"
					}, {
						id : "2413006",
						name : "\u5e7f\u6cb3\u53bf"
					}, {
						id : "2413007",
						name : "\u548c\u653f\u53bf"
					}, {
						id : "2413008",
						name : "\u4e1c\u4e61\u65cf\u81ea\u6cbb\u53bf"
					} ]
				}, {
					id : "24140",
					name : "\u7518\u5357\u5dde",
					subItems : [ {
						id : "2414001",
						name : "\u5408\u4f5c\u5e02"
					}, {
						id : "2414002",
						name : "\u590f\u6cb3\u53bf"
					}, {
						id : "2414003",
						name : "\u4e34\u6f6d\u53bf"
					}, {
						id : "2414004",
						name : "\u5353\u5c3c\u53bf"
					}, {
						id : "2414005",
						name : "\u821f\u66f2\u53bf"
					}, {
						id : "2414006",
						name : "\u8fed\u90e8\u53bf"
					}, {
						id : "2414007",
						name : "\u739b\u66f2\u53bf"
					}, {
						id : "2414008",
						name : "\u788c\u66f2\u53bf"
					} ]
				} ]
			},
			{
				id : "17000",
				name : "\u5e7f\u897f",
				subItems : [ {
					id : "105",
					name : "\u5357\u5b81\u5e02",
					subItems : [ {
						id : "10501",
						name : "\u5174\u5b81\u533a"
					}, {
						id : "10503",
						name : "\u9752\u79c0\u533a"
					}, {
						id : "10507",
						name : "\u897f\u4e61\u5858\u533a"
					}, {
						id : "10505",
						name : "\u6c5f\u5357\u533a"
					}, {
						id : "10509",
						name : "\u826f\u5e86\u533a"
					}, {
						id : "10511",
						name : "\u9095\u5b81\u533a"
					}, {
						id : "10502",
						name : "\u9686\u5b89\u53bf"
					}, {
						id : "10504",
						name : "\u9a6c\u5c71\u53bf"
					}, {
						id : "10508",
						name : "\u5bbe\u9633\u53bf"
					}, {
						id : "10506",
						name : "\u4e0a\u6797\u53bf"
					}, {
						id : "10510",
						name : "\u6a2a\u53bf"
					}, {
						id : "10512",
						name : "\u6b66\u9e23\u53bf"
					} ]
				}, {
					id : "17040",
					name : "\u5317\u6d77\u5e02",
					subItems : [ {
						id : "1704001",
						name : "\u6d77\u57ce\u533a"
					}, {
						id : "1704002",
						name : "\u94f6\u6d77\u533a"
					}, {
						id : "1704003",
						name : "\u94c1\u5c71\u6e2f\u533a"
					}, {
						id : "1704004",
						name : "\u5408\u6d66\u53bf"
					} ]
				}, {
					id : "42",
					name : "\u6842\u6797\u5e02",
					subItems : [ {
						id : "4201",
						name : "\u79c0\u5cf0\u533a"
					}, {
						id : "4204",
						name : "\u53e0\u5f69\u533a"
					}, {
						id : "4207",
						name : "\u8c61\u5c71\u533a"
					}, {
						id : "4210",
						name : "\u4e03\u661f\u533a"
					}, {
						id : "4212",
						name : "\u96c1\u5c71\u533a"
					}, {
						id : "4202",
						name : "\u7075\u5ddd\u53bf"
					}, {
						id : "4203",
						name : "\u8354\u6d66\u53bf"
					}, {
						id : "4205",
						name : "\u5168\u5dde\u53bf"
					}, {
						id : "4206",
						name : "\u9f99\u80dc\u53bf"
					}, {
						id : "4208",
						name : "\u5174\u5b89\u53bf"
					}, {
						id : "4209",
						name : "\u606d\u57ce\u53bf"
					}, {
						id : "4211",
						name : "\u6c38\u798f\u53bf"
					}, {
						id : "4213",
						name : "\u704c\u9633\u53bf"
					}, {
						id : "4214",
						name : "\u9633\u6714\u53bf"
					}, {
						id : "4215",
						name : "\u8d44\u6e90\u53bf"
					}, {
						id : "4216",
						name : "\u4e34\u6842\u53bf"
					}, {
						id : "4217",
						name : "\u5e73\u4e50\u53bf"
					} ]
				}, {
					id : "17020",
					name : "\u67f3\u5dde\u5e02",
					subItems : [ {
						id : "1702001",
						name : "\u57ce\u4e2d\u533a"
					}, {
						id : "1702003",
						name : "\u9c7c\u5cf0\u533a"
					}, {
						id : "1702007",
						name : "\u67f3\u5317\u533a"
					}, {
						id : "1702005",
						name : "\u67f3\u5357\u533a"
					}, {
						id : "1702004",
						name : "\u878d\u6c34\u53bf"
					}, {
						id : "1702002",
						name : "\u878d\u5b89\u53bf"
					}, {
						id : "1702006",
						name : "\u4e09\u6c5f\u53bf"
					}, {
						id : "1702008",
						name : "\u67f3\u6c5f\u53bf"
					}, {
						id : "1702009",
						name : "\u67f3\u57ce\u53bf"
					}, {
						id : "1702010",
						name : "\u9e7f\u5be8\u53bf"
					} ]
				}, {
					id : "17050",
					name : "\u7389\u6797\u5e02",
					subItems : [ {
						id : "1705001",
						name : "\u7389\u5dde\u533a"
					}, {
						id : "1705002",
						name : "\u5317\u6d41\u5e02"
					}, {
						id : "1705003",
						name : "\u5bb9\u53bf"
					}, {
						id : "1705004",
						name : "\u9646\u5ddd\u53bf"
					}, {
						id : "1705005",
						name : "\u535a\u767d\u53bf"
					}, {
						id : "1705006",
						name : "\u5174\u4e1a\u53bf"
					} ]
				}, {
					id : "17060",
					name : "\u767e\u8272\u5e02",
					subItems : [ {
						id : "1706001",
						name : "\u53f3\u6c5f\u533a"
					}, {
						id : "1706002",
						name : "\u51cc\u4e91\u53bf"
					}, {
						id : "1706003",
						name : "\u7530\u9633\u53bf"
					}, {
						id : "1706004",
						name : "\u4e50\u4e1a\u53bf"
					}, {
						id : "1706005",
						name : "\u7530\u4e1c\u53bf"
					}, {
						id : "1706006",
						name : "\u7530\u6797\u53bf"
					}, {
						id : "1706007",
						name : "\u5e73\u679c\u53bf"
					}, {
						id : "1706008",
						name : "\u897f\u6797\u53bf"
					}, {
						id : "1706009",
						name : "\u5fb7\u4fdd\u53bf"
					}, {
						id : "1706010",
						name : "\u9686\u6797\u53bf"
					}, {
						id : "1706011",
						name : "\u9756\u897f\u53bf"
					}, {
						id : "1706012",
						name : "\u90a3\u5761\u53bf"
					} ]
				}, {
					id : "17070",
					name : "\u8d35\u6e2f\u5e02",
					subItems : [ {
						id : "1707001",
						name : "\u6e2f\u5317\u533a"
					}, {
						id : "1707002",
						name : "\u6e2f\u5357\u533a"
					}, {
						id : "1707003",
						name : "\u8983\u5858\u533a"
					}, {
						id : "1707004",
						name : "\u6842\u5e73\u5e02"
					}, {
						id : "1707005",
						name : "\u5e73\u5357\u53bf"
					} ]
				}, {
					id : "17080",
					name : "\u68a7\u5dde\u5e02",
					subItems : [ {
						id : "1708001",
						name : "\u4e07\u79c0\u533a"
					}, {
						id : "1708002",
						name : "\u8776\u5c71\u533a"
					}, {
						id : "1708003",
						name : "\u957f\u6d32\u533a"
					}, {
						id : "1708004",
						name : "\u5c91\u6eaa\u5e02"
					}, {
						id : "1708005",
						name : "\u82cd\u68a7\u53bf"
					}, {
						id : "1708006",
						name : "\u85e4\u53bf"
					}, {
						id : "1708007",
						name : "\u8499\u5c71\u53bf"
					} ]
				}, {
					id : "17090",
					name : "\u9632\u57ce\u6e2f\u5e02",
					subItems : [ {
						id : "1709001",
						name : "\u6e2f\u53e3\u533a"
					}, {
						id : "1709002",
						name : "\u9632\u57ce\u533a"
					}, {
						id : "1709003",
						name : "\u4e1c\u5174\u5e02"
					}, {
						id : "1709004",
						name : "\u4e0a\u601d\u53bf"
					} ]
				}, {
					id : "17100",
					name : "\u94a6\u5dde\u5e02",
					subItems : [ {
						id : "1710001",
						name : "\u94a6\u5357\u533a"
					}, {
						id : "1710002",
						name : "\u94a6\u5317\u533a"
					}, {
						id : "1710003",
						name : "\u7075\u5c71\u53bf"
					}, {
						id : "1710004",
						name : "\u6d66\u5317\u53bf"
					} ]
				}, {
					id : "17110",
					name : "\u8d3a\u5dde\u5e02",
					subItems : [ {
						id : "1711001",
						name : "\u516b\u6b65\u533a"
					}, {
						id : "1711002",
						name : "\u662d\u5e73\u53bf"
					}, {
						id : "1711003",
						name : "\u949f\u5c71\u53bf"
					}, {
						id : "1711004",
						name : "\u5bcc\u5ddd\u53bf"
					} ]
				}, {
					id : "17120",
					name : "\u6cb3\u6c60\u5e02",
					subItems : [ {
						id : "1712001",
						name : "\u91d1\u57ce\u6c5f\u533a"
					}, {
						id : "1712003",
						name : "\u5b9c\u5dde\u5e02"
					}, {
						id : "1712002",
						name : "\u7f57\u57ce\u53bf"
					}, {
						id : "1712004",
						name : "\u73af\u6c5f\u53bf"
					}, {
						id : "1712005",
						name : "\u5357\u4e39\u53bf"
					}, {
						id : "1712006",
						name : "\u5df4\u9a6c\u53bf"
					}, {
						id : "1712007",
						name : "\u5929\u5ce8\u53bf"
					}, {
						id : "1712008",
						name : "\u90fd\u5b89\u53bf"
					}, {
						id : "1712009",
						name : "\u51e4\u5c71\u53bf"
					}, {
						id : "1712010",
						name : "\u5927\u5316\u53bf"
					}, {
						id : "1712011",
						name : "\u4e1c\u5170\u53bf"
					} ]
				}, {
					id : "17130",
					name : "\u6765\u5bbe\u5e02",
					subItems : [ {
						id : "1713001",
						name : "\u5174\u5bbe\u533a"
					}, {
						id : "1713002",
						name : "\u5408\u5c71\u5e02"
					}, {
						id : "1713003",
						name : "\u5ffb\u57ce\u53bf"
					}, {
						id : "1713004",
						name : "\u8c61\u5dde\u53bf"
					}, {
						id : "1713005",
						name : "\u6b66\u5ba3\u53bf"
					}, {
						id : "1713006",
						name : "\u91d1\u79c0\u53bf"
					} ]
				}, {
					id : "17140",
					name : "\u5d07\u5de6\u5e02",
					subItems : [ {
						id : "1714001",
						name : "\u6c5f\u5dde\u533a"
					}, {
						id : "1714002",
						name : "\u51ed\u7965\u5e02"
					}, {
						id : "1714003",
						name : "\u6276\u7ee5\u53bf"
					}, {
						id : "1714004",
						name : "\u5b81\u660e\u53bf"
					}, {
						id : "1714005",
						name : "\u9f99\u5dde\u53bf"
					}, {
						id : "1714006",
						name : "\u5927\u65b0\u53bf"
					}, {
						id : "1714007",
						name : "\u5929\u7b49\u53bf"
					} ]
				} ]
			},
			{
				id : "20000",
				name : "\u8d35\u5dde\u7701",
				subItems : [ {
					id : "45",
					name : "\u8d35\u9633\u5e02",
					subItems : [ {
						id : "4501",
						name : "\u4e4c\u5f53\u533a"
					}, {
						id : "4503",
						name : "\u5357\u660e\u533a"
					}, {
						id : "4505",
						name : "\u4e91\u5ca9\u533a"
					}, {
						id : "4507",
						name : "\u82b1\u6eaa\u533a"
					}, {
						id : "4508",
						name : "\u767d\u4e91\u533a"
					}, {
						id : "4509",
						name : "\u5c0f\u6cb3\u533a"
					}, {
						id : "4510",
						name : "\u6e05\u9547\u5e02"
					}, {
						id : "4502",
						name : "\u5f00\u9633\u53bf"
					}, {
						id : "4504",
						name : "\u606f\u70fd\u53bf"
					}, {
						id : "4506",
						name : "\u4fee\u6587\u53bf"
					} ]
				}, {
					id : "20020",
					name : "\u9075\u4e49\u5e02",
					subItems : [ {
						id : "2002001",
						name : "\u7ea2\u82b1\u5c97\u533a"
					}, {
						id : "2002003",
						name : "\u6c47\u5ddd\u533a"
					}, {
						id : "2002005",
						name : "\u8d64\u6c34\u5e02"
					}, {
						id : "2002007",
						name : "\u4ec1\u6000\u5e02"
					}, {
						id : "2002002",
						name : "\u6b63\u5b89\u53bf"
					}, {
						id : "2002004",
						name : "\u51e4\u5188\u53bf"
					}, {
						id : "2002006",
						name : "\u6e44\u6f6d\u53bf"
					}, {
						id : "2002008",
						name : "\u4f59\u5e86\u53bf"
					}, {
						id : "2002009",
						name : "\u9075\u4e49\u53bf"
					}, {
						id : "2002010",
						name : "\u4e60\u6c34\u53bf"
					}, {
						id : "2002011",
						name : "\u6850\u6893\u53bf"
					}, {
						id : "2002012",
						name : "\u9053\u771f\u53bf"
					}, {
						id : "2002013",
						name : "\u7ee5\u9633\u53bf"
					}, {
						id : "2002014",
						name : "\u52a1\u5ddd\u53bf"
					} ]
				}, {
					id : "20030",
					name : "\u516d\u76d8\u6c34\u5e02",
					subItems : [ {
						id : "2003001",
						name : "\u949f\u5c71\u533a"
					}, {
						id : "2003002",
						name : "\u516d\u679d\u7279\u533a"
					}, {
						id : "2003003",
						name : "\u6c34\u57ce\u53bf"
					}, {
						id : "2003004",
						name : "\u76d8\u53bf"
					} ]
				}, {
					id : "20040",
					name : "\u5b89\u987a\u5e02",
					subItems : [ {
						id : "2004001",
						name : "\u897f\u79c0\u533a"
					}, {
						id : "2004002",
						name : "\u5e73\u575d\u53bf"
					}, {
						id : "2004003",
						name : "\u666e\u5b9a\u53bf"
					}, {
						id : "2004004",
						name : "\u9547\u5b81\u53bf"
					}, {
						id : "2004005",
						name : "\u5173\u5cad\u53bf"
					}, {
						id : "2004006",
						name : "\u7d2b\u4e91\u53bf"
					} ]
				}, {
					id : "20050",
					name : "\u94dc\u4ec1",
					subItems : [ {
						id : "2005006",
						name : "\u4e07\u5c71\u7279\u533a"
					}, {
						id : "2005001",
						name : "\u94dc\u4ec1\u5e02"
					}, {
						id : "2005002",
						name : "\u6cbf\u6cb3\u53bf"
					}, {
						id : "2005003",
						name : "\u6c5f\u53e3\u53bf"
					}, {
						id : "2005004",
						name : "\u677e\u6843\u53bf"
					}, {
						id : "2005005",
						name : "\u77f3\u9621\u53bf"
					}, {
						id : "2005007",
						name : "\u601d\u5357\u53bf"
					}, {
						id : "2005008",
						name : "\u5fb7\u6c5f\u53bf"
					}, {
						id : "2005009",
						name : "\u7389\u5c4f\u53bf"
					}, {
						id : "2005010",
						name : "\u5370\u6c5f\u53bf"
					} ]
				}, {
					id : "20060",
					name : "\u6bd5\u8282",
					subItems : [ {
						id : "2006001",
						name : "\u6bd5\u8282\u5e02"
					}, {
						id : "2006002",
						name : "\u5a01\u5b81\u53bf"
					}, {
						id : "2006003",
						name : "\u5927\u65b9\u53bf"
					}, {
						id : "2006004",
						name : "\u9ed4\u897f\u53bf"
					}, {
						id : "2006005",
						name : "\u91d1\u6c99\u53bf"
					}, {
						id : "2006006",
						name : "\u7ec7\u91d1\u53bf"
					}, {
						id : "2006007",
						name : "\u7eb3\u96cd\u53bf"
					}, {
						id : "2006008",
						name : "\u8d6b\u7ae0\u53bf"
					} ]
				}, {
					id : "20070",
					name : "\u9ed4\u897f\u5357\u5dde",
					subItems : [ {
						id : "2007001",
						name : "\u5174\u4e49\u5e02"
					}, {
						id : "2007002",
						name : "\u5b89\u9f99\u53bf"
					}, {
						id : "2007003",
						name : "\u5174\u4ec1\u53bf"
					}, {
						id : "2007004",
						name : "\u666e\u5b89\u53bf"
					}, {
						id : "2007005",
						name : "\u6674\u9686\u53bf"
					}, {
						id : "2007006",
						name : "\u8d1e\u4e30\u53bf"
					}, {
						id : "2007007",
						name : "\u671b\u8c1f\u53bf"
					}, {
						id : "2007008",
						name : "\u518c\u4ea8\u53bf"
					} ]
				}, {
					id : "20080",
					name : "\u9ed4\u4e1c\u5357\u5dde",
					subItems : [ {
						id : "2008001",
						name : "\u51ef\u91cc\u5e02"
					}, {
						id : "2008002",
						name : "\u9526\u5c4f\u53bf"
					}, {
						id : "2008003",
						name : "\u9ebb\u6c5f\u53bf"
					}, {
						id : "2008004",
						name : "\u9ec4\u5e73\u53bf"
					}, {
						id : "2008005",
						name : "\u5251\u6cb3\u53bf"
					}, {
						id : "2008006",
						name : "\u4e39\u5be8\u53bf"
					}, {
						id : "2008007",
						name : "\u65bd\u79c9\u53bf"
					}, {
						id : "2008008",
						name : "\u53f0\u6c5f\u53bf"
					}, {
						id : "2008009",
						name : "\u4e09\u7a57\u53bf"
					}, {
						id : "2008010",
						name : "\u9ece\u5e73\u53bf"
					}, {
						id : "2008011",
						name : "\u9547\u8fdc\u53bf"
					}, {
						id : "2008012",
						name : "\u6995\u6c5f\u53bf"
					}, {
						id : "2008013",
						name : "\u5c91\u5de9\u53bf"
					}, {
						id : "2008014",
						name : "\u4ece\u6c5f\u53bf"
					}, {
						id : "2008015",
						name : "\u5929\u67f1\u53bf"
					}, {
						id : "2008016",
						name : "\u96f7\u5c71\u53bf"
					} ]
				}, {
					id : "20090",
					name : "\u9ed4\u5357\u5dde",
					subItems : [ {
						id : "2009001",
						name : "\u90fd\u5300\u5e02"
					}, {
						id : "2009003",
						name : "\u798f\u6cc9\u5e02"
					}, {
						id : "2009002",
						name : "\u7f57\u7538\u53bf"
					}, {
						id : "2009004",
						name : "\u957f\u987a\u53bf"
					}, {
						id : "2009005",
						name : "\u8354\u6ce2\u53bf"
					}, {
						id : "2009006",
						name : "\u9f99\u91cc\u53bf"
					}, {
						id : "2009007",
						name : "\u8d35\u5b9a\u53bf"
					}, {
						id : "2009008",
						name : "\u60e0\u6c34\u53bf"
					}, {
						id : "2009009",
						name : "\u74ee\u5b89\u53bf"
					}, {
						id : "2009010",
						name : "\u4e09\u90fd\u53bf"
					}, {
						id : "2009011",
						name : "\u72ec\u5c71\u53bf"
					}, {
						id : "2009012",
						name : "\u5e73\u5858\u53bf"
					} ]
				} ]
			},
			{
				id : "18000",
				name : "\u6d77\u5357\u7701",
				subItems : [ {
					id : "50",
					name : "\u6d77\u53e3\u5e02",
					subItems : [ {
						id : "5001",
						name : "\u9f99\u534e\u533a"
					}, {
						id : "5002",
						name : "\u79c0\u82f1\u533a"
					}, {
						id : "5003",
						name : "\u743c\u5c71\u533a"
					}, {
						id : "5004",
						name : "\u7f8e\u5170\u533a"
					} ]
				}, {
					id : "18020",
					name : "\u4e09\u4e9a\u5e02"
				}, {
					id : "18030",
					name : "\u7701\u76f4\u8f96",
					subItems : [ {
						id : "1803001",
						name : "\u4e94\u6307\u5c71\u5e02"
					}, {
						id : "1803004",
						name : "\u743c\u6d77\u5e02"
					}, {
						id : "1803007",
						name : "\u510b\u5dde\u5e02"
					}, {
						id : "1803010",
						name : "\u6587\u660c\u5e02"
					}, {
						id : "1803012",
						name : "\u4e07\u5b81\u5e02"
					}, {
						id : "1803014",
						name : "\u4e1c\u65b9\u5e02"
					}, {
						id : "1803002",
						name : "\u5c6f\u660c\u53bf"
					}, {
						id : "1803003",
						name : "\u4fdd\u4ead\u53bf"
					}, {
						id : "1803005",
						name : "\u6f84\u8fc8\u53bf"
					}, {
						id : "1803006",
						name : "\u743c\u4e2d\u53bf"
					}, {
						id : "1803008",
						name : "\u4e34\u9ad8\u53bf"
					}, {
						id : "1803009",
						name : "\u9675\u6c34\u53bf"
					}, {
						id : "1803011",
						name : "\u767d\u6c99\u53bf"
					}, {
						id : "1803013",
						name : "\u660c\u6c5f\u53bf"
					}, {
						id : "1803015",
						name : "\u4e50\u4e1c\u53bf"
					}, {
						id : "1803016",
						name : "\u5b9a\u5b89\u53bf"
					} ]
				} ]
			},
			{
				id : "1000",
				name : "\u6cb3\u5317\u7701",
				subItems : [ {
					id : "130",
					name : "\u77f3\u5bb6\u5e84\u5e02",
					subItems : [ {
						id : "13001",
						name : "\u957f\u5b89\u533a"
					}, {
						id : "13002",
						name : "\u6865\u4e1c\u533a"
					}, {
						id : "13005",
						name : "\u6865\u897f\u533a"
					}, {
						id : "13006",
						name : "\u65b0\u534e\u533a"
					}, {
						id : "13009",
						name : "\u4e95\u9649\u77ff\u533a"
					}, {
						id : "13024",
						name : "\u88d5\u534e\u533a"
					}, {
						id : "13004",
						name : "\u65b0\u4e50\u5e02"
					}, {
						id : "13008",
						name : "\u9e7f\u6cc9\u5e02"
					}, {
						id : "13017",
						name : "\u8f9b\u96c6\u5e02"
					}, {
						id : "13023",
						name : "\u664b\u5dde\u5e02"
					}, {
						id : "13025",
						name : "\u85c1\u57ce\u5e02"
					}, {
						id : "13007",
						name : "\u5e73\u5c71\u53bf"
					}, {
						id : "13003",
						name : "\u65e0\u6781\u53bf"
					}, {
						id : "13011",
						name : "\u5143\u6c0f\u53bf"
					}, {
						id : "13012",
						name : "\u4e95\u9649\u53bf"
					}, {
						id : "13013",
						name : "\u6b63\u5b9a\u53bf"
					}, {
						id : "13014",
						name : "\u8d75\u53bf"
					}, {
						id : "13015",
						name : "\u683e\u57ce\u53bf"
					}, {
						id : "13016",
						name : "\u884c\u5510\u53bf"
					}, {
						id : "13018",
						name : "\u7075\u5bff\u53bf"
					}, {
						id : "13019",
						name : "\u9ad8\u9091\u53bf"
					}, {
						id : "13021",
						name : "\u6df1\u6cfd\u53bf"
					}, {
						id : "13022",
						name : "\u8d5e\u7687\u53bf"
					} ]
				}, {
					id : "7",
					name : "\u4fdd\u5b9a\u5e02",
					subItems : [ {
						id : "701",
						name : "\u65b0\u5e02\u533a"
					}, {
						id : "705",
						name : "\u5317\u5e02\u533a"
					}, {
						id : "709",
						name : "\u5357\u5e02\u533a"
					}, {
						id : "704",
						name : "\u6dbf\u5dde\u5e02"
					}, {
						id : "708",
						name : "\u5b9a\u5dde\u5e02"
					}, {
						id : "716",
						name : "\u9ad8\u7891\u5e97\u5e02"
					}, {
						id : "712",
						name : "\u5b89\u56fd\u5e02"
					}, {
						id : "702",
						name : "\u5f90\u6c34\u53bf"
					}, {
						id : "703",
						name : "\u5b89\u65b0\u53bf"
					}, {
						id : "706",
						name : "\u5b9a\u5174\u53bf"
					}, {
						id : "707",
						name : "\u6613\u53bf"
					}, {
						id : "710",
						name : "\u5510\u53bf"
					}, {
						id : "711",
						name : "\u66f2\u9633\u53bf"
					}, {
						id : "713",
						name : "\u6ee1\u57ce\u53bf"
					}, {
						id : "714",
						name : "\u9ad8\u9633\u53bf"
					}, {
						id : "715",
						name : "\u8821\u53bf"
					}, {
						id : "717",
						name : "\u6e05\u82d1\u53bf"
					}, {
						id : "718",
						name : "\u5bb9\u57ce\u53bf"
					}, {
						id : "719",
						name : "\u987a\u5e73\u53bf"
					}, {
						id : "720",
						name : "\u6d9e\u6c34\u53bf"
					}, {
						id : "721",
						name : "\u6d9e\u6e90\u53bf"
					}, {
						id : "722",
						name : "\u535a\u91ce\u53bf"
					}, {
						id : "723",
						name : "\u961c\u5e73\u53bf"
					}, {
						id : "724",
						name : "\u671b\u90fd\u53bf"
					}, {
						id : "725",
						name : "\u96c4\u53bf"
					} ]
				}, {
					id : "1070",
					name : "\u627f\u5fb7\u5e02",
					subItems : [ {
						id : "107001",
						name : "\u53cc\u6865\u533a"
					}, {
						id : "107003",
						name : "\u53cc\u6ee6\u533a"
					}, {
						id : "107005",
						name : "\u9e70\u624b\u8425\u5b50\u77ff\u533a"
					}, {
						id : "107002",
						name : "\u9686\u5316\u53bf"
					}, {
						id : "107004",
						name : "\u4e30\u5b81\u53bf"
					}, {
						id : "107006",
						name : "\u5bbd\u57ce\u53bf"
					}, {
						id : "107007",
						name : "\u627f\u5fb7\u53bf"
					}, {
						id : "107008",
						name : "\u56f4\u573a\u53bf"
					}, {
						id : "107009",
						name : "\u5174\u9686\u53bf"
					}, {
						id : "107010",
						name : "\u5e73\u6cc9\u53bf"
					}, {
						id : "107011",
						name : "\u6ee6\u5e73\u53bf"
					} ]
				}, {
					id : "53",
					name : "\u90af\u90f8\u5e02",
					subItems : [ {
						id : "5301",
						name : "\u90af\u5c71\u533a"
					}, {
						id : "5304",
						name : "\u4e1b\u53f0\u533a"
					}, {
						id : "5306",
						name : "\u590d\u5174\u533a"
					}, {
						id : "5309",
						name : "\u5cf0\u5cf0\u77ff\u533a"
					}, {
						id : "5314",
						name : "\u6b66\u5b89\u5e02"
					}, {
						id : "5302",
						name : "\u5927\u540d\u53bf"
					}, {
						id : "5303",
						name : "\u5e7f\u5e73\u53bf"
					}, {
						id : "5305",
						name : "\u6d89\u53bf"
					}, {
						id : "5307",
						name : "\u78c1\u53bf"
					}, {
						id : "5308",
						name : "\u9b4f\u53bf"
					}, {
						id : "5310",
						name : "\u80a5\u4e61\u53bf"
					}, {
						id : "5311",
						name : "\u66f2\u5468\u53bf"
					}, {
						id : "5312",
						name : "\u90af\u90f8\u53bf"
					}, {
						id : "5313",
						name : "\u6c38\u5e74\u53bf"
					}, {
						id : "5315",
						name : "\u4e34\u6f33\u53bf"
					}, {
						id : "5316",
						name : "\u90b1\u53bf"
					}, {
						id : "5317",
						name : "\u6210\u5b89\u53bf"
					}, {
						id : "5318",
						name : "\u9e21\u6cfd\u53bf"
					}, {
						id : "5319",
						name : "\u9986\u9676\u53bf"
					} ]
				}, {
					id : "1080",
					name : "\u5eca\u574a\u5e02",
					subItems : [ {
						id : "108002",
						name : "\u5b89\u6b21\u533a"
					}, {
						id : "108004",
						name : "\u5e7f\u9633\u533a"
					}, {
						id : "108005",
						name : "\u9738\u5dde\u5e02"
					}, {
						id : "108007",
						name : "\u4e09\u6cb3\u5e02"
					}, {
						id : "108003",
						name : "\u5927\u5382\u53bf"
					}, {
						id : "108001",
						name : "\u6587\u5b89\u53bf"
					}, {
						id : "108006",
						name : "\u56fa\u5b89\u53bf"
					}, {
						id : "108008",
						name : "\u6c38\u6e05\u53bf"
					}, {
						id : "108009",
						name : "\u9999\u6cb3\u53bf"
					}, {
						id : "108010",
						name : "\u5927\u57ce\u53bf"
					} ]
				}, {
					id : "1030",
					name : "\u79e6\u7687\u5c9b\u5e02",
					subItems : [ {
						id : "103001",
						name : "\u6d77\u6e2f\u533a"
					}, {
						id : "103005",
						name : "\u5317\u6234\u6cb3\u533a"
					}, {
						id : "103003",
						name : "\u5c71\u6d77\u5173\u533a"
					}, {
						id : "103002",
						name : "\u660c\u9ece\u53bf"
					}, {
						id : "103004",
						name : "\u629a\u5b81\u53bf"
					}, {
						id : "103006",
						name : "\u5362\u9f99\u53bf"
					}, {
						id : "103007",
						name : "\u9752\u9f99\u53bf"
					} ]
				}, {
					id : "1020",
					name : "\u5510\u5c71\u5e02",
					subItems : [ {
						id : "102001",
						name : "\u8def\u5357\u533a"
					}, {
						id : "102002",
						name : "\u8def\u5317\u533a"
					}, {
						id : "102003",
						name : "\u53e4\u51b6\u533a"
					}, {
						id : "102004",
						name : "\u5f00\u5e73\u533a"
					}, {
						id : "102005",
						name : "\u4e30\u5357\u533a"
					}, {
						id : "102006",
						name : "\u4e30\u6da6\u533a"
					}, {
						id : "102013",
						name : "\u9075\u5316\u5e02"
					}, {
						id : "102014",
						name : "\u8fc1\u5b89\u5e02"
					}, {
						id : "102007",
						name : "\u6ee6\u53bf"
					}, {
						id : "102008",
						name : "\u6ee6\u5357\u53bf"
					}, {
						id : "102009",
						name : "\u4e50\u4ead\u53bf"
					}, {
						id : "102010",
						name : "\u8fc1\u897f\u53bf"
					}, {
						id : "102011",
						name : "\u7389\u7530\u53bf"
					}, {
						id : "102012",
						name : "\u5510\u6d77\u53bf"
					} ]
				}, {
					id : "1060",
					name : "\u5f20\u5bb6\u53e3\u5e02",
					subItems : [ {
						id : "106001",
						name : "\u6865\u4e1c\u533a"
					}, {
						id : "106004",
						name : "\u6865\u897f\u533a"
					}, {
						id : "106007",
						name : "\u5ba3\u5316\u533a"
					}, {
						id : "106010",
						name : "\u4e0b\u82b1\u56ed\u533a"
					}, {
						id : "106003",
						name : "\u6dbf\u9e7f\u53bf"
					}, {
						id : "106005",
						name : "\u5c1a\u4e49\u53bf"
					}, {
						id : "106002",
						name : "\u6cbd\u6e90\u53bf"
					}, {
						id : "106006",
						name : "\u8d64\u57ce\u53bf"
					}, {
						id : "106008",
						name : "\u851a\u53bf"
					}, {
						id : "106009",
						name : "\u5d07\u793c\u53bf"
					}, {
						id : "106011",
						name : "\u9633\u539f\u53bf"
					}, {
						id : "106012",
						name : "\u5ba3\u5316\u53bf"
					}, {
						id : "106013",
						name : "\u6000\u5b89\u53bf"
					}, {
						id : "106014",
						name : "\u5f20\u5317\u53bf"
					}, {
						id : "106015",
						name : "\u4e07\u5168\u53bf"
					}, {
						id : "106016",
						name : "\u5eb7\u4fdd\u53bf"
					}, {
						id : "106017",
						name : "\u6000\u6765\u53bf"
					} ]
				}, {
					id : "1090",
					name : "\u6ca7\u5dde\u5e02",
					subItems : [ {
						id : "109003",
						name : "\u65b0\u534e\u533a"
					}, {
						id : "109006",
						name : "\u8fd0\u6cb3\u533a"
					}, {
						id : "109008",
						name : "\u6cb3\u95f4\u5e02"
					}, {
						id : "109005",
						name : "\u9ec4\u9a85\u5e02"
					}, {
						id : "109002",
						name : "\u4efb\u4e18\u5e02"
					}, {
						id : "109016",
						name : "\u6cca\u5934\u5e02"
					}, {
						id : "109001",
						name : "\u76d0\u5c71\u53bf"
					}, {
						id : "109004",
						name : "\u8083\u5b81\u53bf"
					}, {
						id : "109007",
						name : "\u5357\u76ae\u53bf"
					}, {
						id : "109009",
						name : "\u6ca7\u53bf"
					}, {
						id : "109010",
						name : "\u5434\u6865\u53bf"
					}, {
						id : "109011",
						name : "\u9752\u53bf"
					}, {
						id : "109012",
						name : "\u732e\u53bf"
					}, {
						id : "109013",
						name : "\u4e1c\u5149\u53bf"
					}, {
						id : "109014",
						name : "\u5b5f\u6751\u53bf"
					}, {
						id : "109015",
						name : "\u6d77\u5174\u53bf"
					} ]
				}, {
					id : "1100",
					name : "\u90a2\u53f0\u5e02",
					subItems : [ {
						id : "110001",
						name : "\u6865\u4e1c\u533a"
					}, {
						id : "110004",
						name : "\u6865\u897f\u533a"
					}, {
						id : "110012",
						name : "\u5357\u5bab\u5e02"
					}, {
						id : "110015",
						name : "\u6c99\u6cb3\u5e02"
					}, {
						id : "110002",
						name : "\u4efb\u53bf"
					}, {
						id : "110003",
						name : "\u5a01\u53bf"
					}, {
						id : "110005",
						name : "\u5357\u548c\u53bf"
					}, {
						id : "110006",
						name : "\u6e05\u6cb3\u53bf"
					}, {
						id : "110007",
						name : "\u90a2\u53f0\u53bf"
					}, {
						id : "110008",
						name : "\u5b81\u664b\u53bf"
					}, {
						id : "110009",
						name : "\u4e34\u897f\u53bf"
					}, {
						id : "110010",
						name : "\u4e34\u57ce\u53bf"
					}, {
						id : "110011",
						name : "\u5de8\u9e7f\u53bf"
					}, {
						id : "110013",
						name : "\u5185\u4e18\u53bf"
					}, {
						id : "110014",
						name : "\u65b0\u6cb3\u53bf"
					}, {
						id : "110016",
						name : "\u67cf\u4e61\u53bf"
					}, {
						id : "110017",
						name : "\u5e7f\u5b97\u53bf"
					}, {
						id : "110018",
						name : "\u9686\u5c27\u53bf"
					}, {
						id : "110019",
						name : "\u5e73\u4e61\u53bf"
					} ]
				}, {
					id : "1110",
					name : "\u8861\u6c34\u5e02",
					subItems : [ {
						id : "111001",
						name : "\u6843\u57ce\u533a"
					}, {
						id : "111006",
						name : "\u5180\u5dde\u5e02"
					}, {
						id : "111008",
						name : "\u6df1\u5dde\u5e02"
					}, {
						id : "111002",
						name : "\u666f\u53bf"
					}, {
						id : "111003",
						name : "\u67a3\u5f3a\u53bf"
					}, {
						id : "111004",
						name : "\u961c\u57ce\u53bf"
					}, {
						id : "111005",
						name : "\u6b66\u9091\u53bf"
					}, {
						id : "111007",
						name : "\u6b66\u5f3a\u53bf"
					}, {
						id : "111009",
						name : "\u9976\u9633\u53bf"
					}, {
						id : "111010",
						name : "\u5b89\u5e73\u53bf"
					}, {
						id : "111011",
						name : "\u6545\u57ce\u53bf"
					} ]
				} ]
			},
			{
				id : "13000",
				name : "\u6cb3\u5357\u7701",
				subItems : [ {
					id : "175",
					name : "\u90d1\u5dde\u5e02",
					subItems : [ {
						id : "17501",
						name : "\u4e2d\u539f\u533a"
					}, {
						id : "17503",
						name : "\u91d1\u6c34\u533a"
					}, {
						id : "17505",
						name : "\u4e8c\u4e03\u533a"
					}, {
						id : "17507",
						name : "\u7ba1\u57ce\u56de\u65cf\u533a"
					}, {
						id : "17509",
						name : "\u4e0a\u8857\u533a"
					}, {
						id : "17511",
						name : "\u60e0\u6d4e\u533a"
					}, {
						id : "17502",
						name : "\u5de9\u4e49\u5e02"
					}, {
						id : "17504",
						name : "\u65b0\u90d1\u5e02"
					}, {
						id : "17506",
						name : "\u65b0\u5bc6\u5e02"
					}, {
						id : "17508",
						name : "\u767b\u5c01\u5e02"
					}, {
						id : "17510",
						name : "\u8365\u9633\u5e02"
					}, {
						id : "17512",
						name : "\u4e2d\u725f\u53bf"
					} ]
				}, {
					id : "78",
					name : "\u5f00\u5c01\u5e02",
					subItems : [ {
						id : "7801",
						name : "\u9f13\u697c\u533a"
					}, {
						id : "7803",
						name : "\u9f99\u4ead\u533a"
					}, {
						id : "7805",
						name : "\u987a\u6cb3\u56de\u65cf\u533a"
					}, {
						id : "7807",
						name : "\u79b9\u738b\u53f0\u533a"
					}, {
						id : "7808",
						name : "\u91d1\u660e\u533a"
					}, {
						id : "7804",
						name : "\u675e\u53bf"
					}, {
						id : "7806",
						name : "\u901a\u8bb8\u53bf"
					}, {
						id : "7802",
						name : "\u5170\u8003\u53bf"
					}, {
						id : "7809",
						name : "\u5f00\u5c01\u53bf"
					}, {
						id : "7810",
						name : "\u5c09\u6c0f\u53bf"
					} ]
				}, {
					id : "92",
					name : "\u6d1b\u9633\u5e02",
					subItems : [ {
						id : "9201",
						name : "\u897f\u5de5\u533a"
					}, {
						id : "9204",
						name : "\u8001\u57ce\u533a"
					}, {
						id : "9206",
						name : "\u6da7\u897f\u533a"
					}, {
						id : "9210",
						name : "\u6d1b\u9f99\u533a"
					}, {
						id : "9212",
						name : "\u5409\u5229\u533a"
					}, {
						id : "9208",
						name : "\u700d\u6cb3\u56de\u65cf\u533a"
					}, {
						id : "9214",
						name : "\u5043\u5e08\u5e02"
					}, {
						id : "9205",
						name : "\u6c5d\u9633\u53bf"
					}, {
						id : "9202",
						name : "\u5b5f\u6d25\u53bf"
					}, {
						id : "9203",
						name : "\u683e\u5ddd\u53bf"
					}, {
						id : "9207",
						name : "\u4f0a\u5ddd\u53bf"
					}, {
						id : "9209",
						name : "\u6d1b\u5b81\u53bf"
					}, {
						id : "9211",
						name : "\u5d69\u53bf"
					}, {
						id : "9213",
						name : "\u5b9c\u9633\u53bf"
					}, {
						id : "9215",
						name : "\u65b0\u5b89\u53bf"
					} ]
				}, {
					id : "13010",
					name : "\u5546\u4e18\u5e02",
					subItems : [ {
						id : "1301001",
						name : "\u6881\u56ed\u533a"
					}, {
						id : "1301003",
						name : "\u7762\u9633\u533a"
					}, {
						id : "1301005",
						name : "\u6c38\u57ce\u5e02"
					}, {
						id : "1301002",
						name : "\u67d8\u57ce\u53bf"
					}, {
						id : "1301004",
						name : "\u7762\u53bf"
					}, {
						id : "1301006",
						name : "\u5b81\u9675\u53bf"
					}, {
						id : "1301007",
						name : "\u865e\u57ce\u53bf"
					}, {
						id : "1301008",
						name : "\u6c11\u6743\u53bf"
					}, {
						id : "1301009",
						name : "\u590f\u9091\u53bf"
					} ]
				}, {
					id : "13020",
					name : "\u4e09\u95e8\u5ce1\u5e02",
					subItems : [ {
						id : "1302001",
						name : "\u6e56\u6ee8\u533a"
					}, {
						id : "1302002",
						name : "\u4e49\u9a6c\u5e02"
					}, {
						id : "1302003",
						name : "\u7075\u5b9d\u5e02"
					}, {
						id : "1302004",
						name : "\u6e11\u6c60\u53bf"
					}, {
						id : "1302005",
						name : "\u5362\u6c0f\u53bf"
					}, {
						id : "1302006",
						name : "\u9655\u53bf"
					} ]
				}, {
					id : "13030",
					name : "\u9a7b\u9a6c\u5e97\u5e02",
					subItems : [ {
						id : "1303001",
						name : "\u9a7f\u57ce\u533a"
					}, {
						id : "1303002",
						name : "\u6c5d\u5357\u53bf"
					}, {
						id : "1303003",
						name : "\u786e\u5c71\u53bf"
					}, {
						id : "1303004",
						name : "\u9042\u5e73\u53bf"
					}, {
						id : "1303005",
						name : "\u65b0\u8521\u53bf"
					}, {
						id : "1303006",
						name : "\u6b63\u9633\u53bf"
					}, {
						id : "1303007",
						name : "\u4e0a\u8521\u53bf"
					}, {
						id : "1303008",
						name : "\u897f\u5e73\u53bf"
					}, {
						id : "1303009",
						name : "\u6ccc\u9633\u53bf"
					}, {
						id : "1303010",
						name : "\u5e73\u8206\u53bf"
					} ]
				}, {
					id : "13040",
					name : "\u5e73\u9876\u5c71\u5e02",
					subItems : [ {
						id : "1304001",
						name : "\u65b0\u534e\u533a"
					}, {
						id : "1304003",
						name : "\u536b\u4e1c\u533a"
					}, {
						id : "1304005",
						name : "\u6e5b\u6cb3\u533a"
					}, {
						id : "1304007",
						name : "\u77f3\u9f99\u533a"
					}, {
						id : "1304008",
						name : "\u6c5d\u5dde\u5e02"
					}, {
						id : "1304009",
						name : "\u821e\u94a2\u5e02"
					}, {
						id : "1304002",
						name : "\u53f6\u53bf"
					}, {
						id : "1304004",
						name : "\u90cf\u53bf"
					}, {
						id : "1304006",
						name : "\u9c81\u5c71\u53bf"
					}, {
						id : "1304010",
						name : "\u5b9d\u4e30\u53bf"
					} ]
				}, {
					id : "13050",
					name : "\u5b89\u9633\u5e02",
					subItems : [ {
						id : "1305001",
						name : "\u5317\u5173\u533a"
					}, {
						id : "1305003",
						name : "\u6587\u5cf0\u533a"
					}, {
						id : "1305005",
						name : "\u6bb7\u90fd\u533a"
					}, {
						id : "1305006",
						name : "\u9f99\u5b89\u533a"
					}, {
						id : "1305007",
						name : "\u6797\u5dde\u5e02"
					}, {
						id : "1305002",
						name : "\u5185\u9ec4\u53bf"
					}, {
						id : "1305004",
						name : "\u6c64\u9634\u53bf"
					}, {
						id : "1305008",
						name : "\u5b89\u9633\u53bf"
					}, {
						id : "1305009",
						name : "\u6ed1\u53bf"
					} ]
				}, {
					id : "13060",
					name : "\u9e64\u58c1\u5e02",
					subItems : [ {
						id : "1306001",
						name : "\u6dc7\u6ee8\u533a"
					}, {
						id : "1306002",
						name : "\u5c71\u57ce\u533a"
					}, {
						id : "1306003",
						name : "\u9e64\u5c71\u533a"
					}, {
						id : "1306004",
						name : "\u6d5a\u53bf"
					}, {
						id : "1306005",
						name : "\u6dc7\u53bf"
					} ]
				}, {
					id : "13070",
					name : "\u65b0\u4e61\u5e02",
					subItems : [ {
						id : "1307001",
						name : "\u536b\u6ee8\u533a"
					}, {
						id : "1307003",
						name : "\u7ea2\u65d7\u533a"
					}, {
						id : "1307005",
						name : "\u51e4\u6cc9\u533a"
					}, {
						id : "1307007",
						name : "\u7267\u91ce\u533a"
					}, {
						id : "1307009",
						name : "\u536b\u8f89\u5e02"
					}, {
						id : "1307011",
						name : "\u8f89\u53bf\u5e02"
					}, {
						id : "1307002",
						name : "\u83b7\u5609\u53bf"
					}, {
						id : "1307004",
						name : "\u539f\u9633\u53bf"
					}, {
						id : "1307006",
						name : "\u957f\u57a3\u53bf"
					}, {
						id : "1307008",
						name : "\u5c01\u4e18\u53bf"
					}, {
						id : "1307010",
						name : "\u5ef6\u6d25\u53bf"
					}, {
						id : "1307012",
						name : "\u65b0\u4e61\u53bf"
					} ]
				}, {
					id : "13080",
					name : "\u7126\u4f5c\u5e02",
					subItems : [ {
						id : "1308001",
						name : "\u89e3\u653e\u533a"
					}, {
						id : "1308003",
						name : "\u4e2d\u7ad9\u533a"
					}, {
						id : "1308005",
						name : "\u9a6c\u6751\u533a"
					}, {
						id : "1308007",
						name : "\u5c71\u9633\u533a"
					}, {
						id : "1308008",
						name : "\u6c81\u9633\u5e02"
					}, {
						id : "1308009",
						name : "\u5b5f\u5dde\u5e02"
					}, {
						id : "1308006",
						name : "\u535a\u7231\u53bf"
					}, {
						id : "1308002",
						name : "\u6e29\u53bf"
					}, {
						id : "1308004",
						name : "\u6b66\u965f\u53bf"
					}, {
						id : "1308010",
						name : "\u4fee\u6b66\u53bf"
					} ]
				}, {
					id : "13090",
					name : "\u6fee\u9633\u5e02",
					subItems : [ {
						id : "1309001",
						name : "\u534e\u9f99\u533a"
					}, {
						id : "1309002",
						name : "\u6fee\u9633\u53bf"
					}, {
						id : "1309003",
						name : "\u5357\u4e50\u53bf"
					}, {
						id : "1309004",
						name : "\u53f0\u524d\u53bf"
					}, {
						id : "1309005",
						name : "\u6e05\u4e30\u53bf"
					}, {
						id : "1309006",
						name : "\u8303\u53bf"
					} ]
				}, {
					id : "13100",
					name : "\u8bb8\u660c\u5e02",
					subItems : [ {
						id : "1310001",
						name : "\u9b4f\u90fd\u533a"
					}, {
						id : "1310002",
						name : "\u79b9\u5dde\u5e02"
					}, {
						id : "1310003",
						name : "\u957f\u845b\u5e02"
					}, {
						id : "1310004",
						name : "\u8bb8\u660c\u53bf"
					}, {
						id : "1310005",
						name : "\u9122\u9675\u53bf"
					}, {
						id : "1310006",
						name : "\u8944\u57ce\u53bf"
					} ]
				}, {
					id : "13110",
					name : "\u6f2f\u6cb3\u5e02",
					subItems : [ {
						id : "1311001",
						name : "\u6e90\u6c47\u533a"
					}, {
						id : "1311002",
						name : "\u90fe\u57ce\u533a"
					}, {
						id : "1311003",
						name : "\u53ec\u9675\u533a"
					}, {
						id : "1311004",
						name : "\u4e34\u988d\u53bf"
					}, {
						id : "1311005",
						name : "\u821e\u9633\u53bf"
					} ]
				}, {
					id : "13120",
					name : "\u5357\u9633\u5e02",
					subItems : [ {
						id : "1312001",
						name : "\u5367\u9f99\u533a"
					}, {
						id : "1312003",
						name : "\u5b9b\u57ce\u533a"
					}, {
						id : "1312005",
						name : "\u9093\u5dde\u5e02"
					}, {
						id : "1312002",
						name : "\u5510\u6cb3\u53bf"
					}, {
						id : "1312004",
						name : "\u5357\u53ec\u53bf"
					}, {
						id : "1312006",
						name : "\u5185\u4e61\u53bf"
					}, {
						id : "1312007",
						name : "\u6850\u67cf\u53bf"
					}, {
						id : "1312008",
						name : "\u65b0\u91ce\u53bf"
					}, {
						id : "1312009",
						name : "\u65b9\u57ce\u53bf"
					}, {
						id : "1312010",
						name : "\u793e\u65d7\u53bf"
					}, {
						id : "1312011",
						name : "\u6dc5\u5ddd\u53bf"
					}, {
						id : "1312012",
						name : "\u897f\u5ce1\u53bf"
					}, {
						id : "1312013",
						name : "\u9547\u5e73\u53bf"
					} ]
				}, {
					id : "13130",
					name : "\u4fe1\u9633\u5e02",
					subItems : [ {
						id : "1313001",
						name : "\u6d49\u6cb3\u533a"
					}, {
						id : "1313002",
						name : "\u56fa\u59cb\u53bf"
					}, {
						id : "1313003",
						name : "\u5e73\u6865\u533a"
					}, {
						id : "1313004",
						name : "\u7f57\u5c71\u53bf"
					}, {
						id : "1313005",
						name : "\u6f62\u5ddd\u53bf"
					}, {
						id : "1313006",
						name : "\u5149\u5c71\u53bf"
					}, {
						id : "1313007",
						name : "\u6dee\u6ee8\u53bf"
					}, {
						id : "1313008",
						name : "\u606f\u53bf"
					}, {
						id : "1313009",
						name : "\u65b0\u53bf"
					}, {
						id : "1313010",
						name : "\u5546\u57ce\u53bf"
					} ]
				}, {
					id : "13140",
					name : "\u5468\u53e3\u5e02",
					subItems : [ {
						id : "1314001",
						name : "\u5ddd\u6c47\u533a"
					}, {
						id : "1314003",
						name : "\u9879\u57ce\u5e02"
					}, {
						id : "1314002",
						name : "\u6276\u6c9f\u53bf"
					}, {
						id : "1314004",
						name : "\u6c88\u4e18\u53bf"
					}, {
						id : "1314005",
						name : "\u5546\u6c34\u53bf"
					}, {
						id : "1314006",
						name : "\u90f8\u57ce\u53bf"
					}, {
						id : "1314007",
						name : "\u6dee\u9633\u53bf"
					}, {
						id : "1314008",
						name : "\u592a\u5eb7\u53bf"
					}, {
						id : "1314009",
						name : "\u9e7f\u9091\u53bf"
					}, {
						id : "1314010",
						name : "\u897f\u534e\u53bf"
					} ]
				}, {
					id : "13150",
					name : "\u7701\u76f4\u8f96",
					subItems : [ {
						id : "1315001",
						name : "\u6d4e\u6e90\u5e02"
					} ]
				} ]
			},
			{
				id : "6000",
				name : "\u9ed1\u9f99\u6c5f\u7701",
				subItems : [ {
					id : "60",
					name : "\u54c8\u5c14\u6ee8\u5e02",
					subItems : [ {
						id : "6001",
						name : "\u677e\u5317\u533a"
					}, {
						id : "6002",
						name : "\u963f\u57ce\u533a"
					}, {
						id : "6004",
						name : "\u9053\u91cc\u533a"
					}, {
						id : "6007",
						name : "\u5357\u5c97\u533a"
					}, {
						id : "6010",
						name : "\u9053\u5916\u533a"
					}, {
						id : "6013",
						name : "\u5e73\u623f\u533a"
					}, {
						id : "6015",
						name : "\u9999\u574a\u533a"
					}, {
						id : "6017",
						name : "\u547c\u5170\u533a"
					}, {
						id : "6005",
						name : "\u53cc\u57ce\u5e02"
					}, {
						id : "6008",
						name : "\u5c1a\u5fd7\u5e02"
					}, {
						id : "6011",
						name : "\u4e94\u5e38\u5e02"
					}, {
						id : "6003",
						name : "\u5df4\u5f66\u53bf"
					}, {
						id : "6006",
						name : "\u6728\u5170\u53bf"
					}, {
						id : "6009",
						name : "\u901a\u6cb3\u53bf"
					}, {
						id : "6012",
						name : "\u5ef6\u5bff\u53bf"
					}, {
						id : "6014",
						name : "\u4f9d\u5170\u53bf"
					}, {
						id : "6016",
						name : "\u65b9\u6b63\u53bf"
					}, {
						id : "6018",
						name : "\u5bbe\u53bf"
					} ]
				}, {
					id : "6030",
					name : "\u5927\u5e86\u5e02",
					subItems : [ {
						id : "603001",
						name : "\u8428\u5c14\u56fe\u533a"
					}, {
						id : "603003",
						name : "\u9f99\u51e4\u533a"
					}, {
						id : "603005",
						name : "\u8ba9\u80e1\u8def\u533a"
					}, {
						id : "603006",
						name : "\u7ea2\u5c97\u533a"
					}, {
						id : "603007",
						name : "\u5927\u540c\u533a"
					}, {
						id : "603002",
						name : "\u6797\u7538\u53bf"
					}, {
						id : "603004",
						name : "\u675c\u5c14\u4f2f\u7279\u53bf"
					}, {
						id : "603008",
						name : "\u8087\u5dde\u53bf"
					}, {
						id : "603009",
						name : "\u8087\u6e90\u53bf"
					} ]
				}, {
					id : "6040",
					name : "\u4f73\u6728\u65af\u5e02",
					subItems : [ {
						id : "604001",
						name : "\u524d\u8fdb\u533a"
					}, {
						id : "604003",
						name : "\u5411\u9633\u533a"
					}, {
						id : "604005",
						name : "\u4e1c\u98ce\u533a"
					}, {
						id : "604007",
						name : "\u4f73\u6728\u65af\u90ca\u533a"
					}, {
						id : "604008",
						name : "\u540c\u6c5f\u5e02"
					}, {
						id : "604009",
						name : "\u5bcc\u9526\u5e02"
					}, {
						id : "604002",
						name : "\u6866\u5ddd\u53bf"
					}, {
						id : "604004",
						name : "\u6c64\u539f\u53bf"
					}, {
						id : "604006",
						name : "\u629a\u8fdc\u53bf"
					}, {
						id : "604010",
						name : "\u6866\u5357\u53bf"
					} ]
				}, {
					id : "6050",
					name : "\u7261\u4e39\u6c5f\u5e02",
					subItems : [ {
						id : "605001",
						name : "\u7231\u6c11\u533a"
					}, {
						id : "605003",
						name : "\u4e1c\u5b89\u533a"
					}, {
						id : "605005",
						name : "\u9633\u660e\u533a"
					}, {
						id : "605007",
						name : "\u897f\u5b89\u533a"
					}, {
						id : "605002",
						name : "\u7a46\u68f1\u5e02"
					}, {
						id : "605009",
						name : "\u6d77\u6797\u5e02"
					}, {
						id : "605010",
						name : "\u5b81\u5b89\u5e02"
					}, {
						id : "605008",
						name : "\u7ee5\u82ac\u6cb3\u5e02"
					}, {
						id : "605004",
						name : "\u4e1c\u5b81\u53bf"
					}, {
						id : "605006",
						name : "\u6797\u53e3\u53bf"
					} ]
				}, {
					id : "6020",
					name : "\u9f50\u9f50\u54c8\u5c14\u5e02",
					subItems : [ {
						id : "602001",
						name : "\u9f99\u6c99\u533a"
					}, {
						id : "602004",
						name : "\u5efa\u534e\u533a"
					}, {
						id : "602007",
						name : "\u94c1\u950b\u533a"
					}, {
						id : "602009",
						name : "\u6602\u6602\u6eaa\u533a"
					}, {
						id : "602013",
						name : "\u78be\u5b50\u5c71\u533a"
					}, {
						id : "602015",
						name : "\u6885\u91cc\u65af"
					}, {
						id : "602011",
						name : "\u5bcc\u62c9\u5c14\u57fa\u533a"
					}, {
						id : "602002",
						name : "\u8bb7\u6cb3\u5e02"
					}, {
						id : "602005",
						name : "\u9f99\u6c5f\u53bf"
					}, {
						id : "602003",
						name : "\u514b\u4e1c\u53bf"
					}, {
						id : "602006",
						name : "\u62dc\u6cc9\u53bf"
					}, {
						id : "602008",
						name : "\u4f9d\u5b89\u53bf"
					}, {
						id : "602010",
						name : "\u6cf0\u6765\u53bf"
					}, {
						id : "602012",
						name : "\u7518\u5357\u53bf"
					}, {
						id : "602014",
						name : "\u5bcc\u88d5\u53bf"
					}, {
						id : "602016",
						name : "\u514b\u5c71\u53bf"
					} ]
				}, {
					id : "6060",
					name : "\u9e64\u5c97\u5e02",
					subItems : [ {
						id : "606001",
						name : "\u5174\u5c71\u533a"
					}, {
						id : "606003",
						name : "\u5411\u9633\u533a"
					}, {
						id : "606004",
						name : "\u5de5\u519c\u533a"
					}, {
						id : "606005",
						name : "\u5357\u5c71\u533a"
					}, {
						id : "606006",
						name : "\u5174\u5b89\u533a"
					}, {
						id : "606007",
						name : "\u4e1c\u5c71\u533a"
					}, {
						id : "606002",
						name : "\u7ee5\u6ee8\u53bf"
					}, {
						id : "606008",
						name : "\u841d\u5317\u53bf"
					} ]
				}, {
					id : "6070",
					name : "\u9e21\u897f\u5e02",
					subItems : [ {
						id : "607001",
						name : "\u9e21\u51a0\u533a"
					}, {
						id : "607003",
						name : "\u6052\u5c71\u533a"
					}, {
						id : "607005",
						name : "\u6ef4\u9053\u533a"
					}, {
						id : "607006",
						name : "\u68a8\u6811\u533a"
					}, {
						id : "607008",
						name : "\u9ebb\u5c71\u533a"
					}, {
						id : "607007",
						name : "\u57ce\u5b50\u6cb3\u533a"
					}, {
						id : "607002",
						name : "\u5bc6\u5c71\u5e02"
					}, {
						id : "607009",
						name : "\u864e\u6797\u5e02"
					}, {
						id : "607004",
						name : "\u9e21\u4e1c\u53bf"
					} ]
				}, {
					id : "6080",
					name : "\u7ee5\u5316\u5e02",
					subItems : [ {
						id : "608001",
						name : "\u5317\u6797\u533a"
					}, {
						id : "608003",
						name : "\u5b89\u8fbe\u5e02"
					}, {
						id : "608005",
						name : "\u8087\u4e1c\u5e02"
					}, {
						id : "608007",
						name : "\u6d77\u4f26\u5e02"
					}, {
						id : "608002",
						name : "\u5e86\u5b89\u53bf"
					}, {
						id : "608004",
						name : "\u660e\u6c34\u53bf"
					}, {
						id : "608006",
						name : "\u7ee5\u68f1\u53bf"
					}, {
						id : "608008",
						name : "\u671b\u594e\u53bf"
					}, {
						id : "608009",
						name : "\u5170\u897f\u53bf"
					}, {
						id : "608010",
						name : "\u9752\u5188\u53bf"
					} ]
				}, {
					id : "6090",
					name : "\u9ed1\u6cb3\u5e02",
					subItems : [ {
						id : "609001",
						name : "\u7231\u8f89\u533a"
					}, {
						id : "609002",
						name : "\u5317\u5b89\u5e02"
					}, {
						id : "609003",
						name : "\u4e94\u5927\u8fde\u6c60\u5e02"
					}, {
						id : "609004",
						name : "\u5ae9\u6c5f\u53bf"
					}, {
						id : "609005",
						name : "\u900a\u514b\u53bf"
					}, {
						id : "609006",
						name : "\u5b59\u5434\u53bf"
					} ]
				}, {
					id : "6100",
					name : "\u4f0a\u6625\u5e02",
					subItems : [ {
						id : "610001",
						name : "\u4f0a\u6625\u533a"
					}, {
						id : "610004",
						name : "\u5357\u5c94\u533a"
					}, {
						id : "610005",
						name : "\u4e94\u8425\u533a"
					}, {
						id : "610010",
						name : "\u897f\u6797\u533a"
					}, {
						id : "610007",
						name : "\u53cb\u597d\u533a"
					}, {
						id : "610013",
						name : "\u5e26\u5cad\u533a"
					}, {
						id : "610014",
						name : "\u65b0\u9752\u533a"
					}, {
						id : "610012",
						name : "\u7fe0\u5ce6\u533a"
					}, {
						id : "610016",
						name : "\u7f8e\u6eaa\u533a"
					}, {
						id : "610017",
						name : "\u7ea2\u661f\u533a"
					}, {
						id : "610002",
						name : "\u91d1\u5c71\u5c6f\u533a"
					}, {
						id : "610003",
						name : "\u4e0a\u7518\u5cad\u533a"
					}, {
						id : "610008",
						name : "\u4e4c\u9a6c\u6cb3\u533a"
					}, {
						id : "610011",
						name : "\u6c64\u65fa\u6cb3\u533a"
					}, {
						id : "610015",
						name : "\u4e4c\u4f0a\u5cad\u533a"
					}, {
						id : "610006",
						name : "\u94c1\u529b\u5e02"
					}, {
						id : "610009",
						name : "\u5609\u836b\u53bf"
					} ]
				}, {
					id : "6110",
					name : "\u53cc\u9e2d\u5c71\u5e02",
					subItems : [ {
						id : "611001",
						name : "\u5c16\u5c71\u533a"
					}, {
						id : "611003",
						name : "\u5cad\u4e1c\u533a"
					}, {
						id : "611005",
						name : "\u5b9d\u5c71\u533a"
					}, {
						id : "611004",
						name : "\u56db\u65b9\u53f0\u533a"
					}, {
						id : "611002",
						name : "\u9976\u6cb3\u53bf"
					}, {
						id : "611006",
						name : "\u96c6\u8d24\u53bf"
					}, {
						id : "611007",
						name : "\u53cb\u8c0a\u53bf"
					}, {
						id : "611008",
						name : "\u5b9d\u6e05\u53bf"
					} ]
				}, {
					id : "6120",
					name : "\u4e03\u53f0\u6cb3\u5e02",
					subItems : [ {
						id : "612001",
						name : "\u6843\u5c71\u533a"
					}, {
						id : "612002",
						name : "\u65b0\u5174\u533a"
					}, {
						id : "612003",
						name : "\u8304\u5b50\u6cb3\u533a"
					}, {
						id : "612004",
						name : "\u52c3\u5229\u53bf"
					} ]
				}, {
					id : "6130",
					name : "\u5927\u5174\u5b89\u5cad",
					subItems : [ {
						id : "613001",
						name : "\u547c\u739b\u53bf"
					}, {
						id : "613002",
						name : "\u5854\u6cb3\u53bf"
					}, {
						id : "613003",
						name : "\u6f20\u6cb3\u53bf"
					} ]
				} ]
			},
			{
				id : "14000",
				name : "\u6e56\u5317\u7701",
				subItems : [ {
					id : "150",
					name : "\u6b66\u6c49\u5e02",
					subItems : [ {
						id : "15001",
						name : "\u6c5f\u5cb8\u533a"
					}, {
						id : "15002",
						name : "\u4e1c\u897f\u6e56\u533a"
					}, {
						id : "15003",
						name : "\u6c5f\u6c49\u533a"
					}, {
						id : "15004",
						name : "\u6c49\u5357\u533a"
					}, {
						id : "15005",
						name : "\u785a\u53e3\u533a"
					}, {
						id : "15006",
						name : "\u8521\u7538\u533a"
					}, {
						id : "15007",
						name : "\u6c49\u9633\u533a"
					}, {
						id : "15008",
						name : "\u6c5f\u590f\u533a"
					}, {
						id : "15009",
						name : "\u6b66\u660c\u533a"
					}, {
						id : "15010",
						name : "\u9ec4\u9642\u533a"
					}, {
						id : "15011",
						name : "\u9752\u5c71\u533a"
					}, {
						id : "15012",
						name : "\u65b0\u6d32\u533a"
					}, {
						id : "15013",
						name : "\u6d2a\u5c71\u533a"
					} ]
				}, {
					id : "14020",
					name : "\u5341\u5830\u5e02",
					subItems : [ {
						id : "1402001",
						name : "\u8305\u7bad\u533a"
					}, {
						id : "1402003",
						name : "\u5f20\u6e7e\u533a"
					}, {
						id : "1402004",
						name : "\u4e39\u6c5f\u53e3\u5e02"
					}, {
						id : "1402002",
						name : "\u623f\u53bf"
					}, {
						id : "1402005",
						name : "\u90e7\u53bf"
					}, {
						id : "1402006",
						name : "\u90e7\u897f\u53bf"
					}, {
						id : "1402007",
						name : "\u7af9\u5c71\u53bf"
					}, {
						id : "1402008",
						name : "\u7af9\u6eaa\u53bf"
					} ]
				}, {
					id : "14040",
					name : "\u8944\u6a0a\u5e02",
					subItems : [ {
						id : "1404001",
						name : "\u8944\u57ce\u533a"
					}, {
						id : "1404003",
						name : "\u6a0a\u57ce\u533a"
					}, {
						id : "1404005",
						name : "\u8944\u9633\u533a"
					}, {
						id : "1404007",
						name : "\u67a3\u9633\u5e02"
					}, {
						id : "1404008",
						name : "\u5b9c\u57ce\u5e02"
					}, {
						id : "1404006",
						name : "\u8001\u6cb3\u53e3\u5e02"
					}, {
						id : "1404002",
						name : "\u8c37\u57ce\u53bf"
					}, {
						id : "1404004",
						name : "\u4fdd\u5eb7\u53bf"
					}, {
						id : "1404009",
						name : "\u5357\u6f33\u53bf"
					} ]
				}, {
					id : "14030",
					name : "\u5b9c\u660c\u5e02",
					subItems : [ {
						id : "1403001",
						name : "\u897f\u9675\u533a"
					}, {
						id : "1403005",
						name : "\u70b9\u519b\u533a"
					}, {
						id : "1403007",
						name : "\u7307\u4ead\u533a"
					}, {
						id : "1403009",
						name : "\u5937\u9675\u533a"
					}, {
						id : "1403003",
						name : "\u4f0d\u5bb6\u5c97\u533a"
					}, {
						id : "1403002",
						name : "\u679d\u6c5f\u5e02"
					}, {
						id : "1403011",
						name : "\u5b9c\u90fd\u5e02"
					}, {
						id : "1403013",
						name : "\u5f53\u9633\u5e02"
					}, {
						id : "1403004",
						name : "\u8fdc\u5b89\u53bf"
					}, {
						id : "1403006",
						name : "\u5174\u5c71\u53bf"
					}, {
						id : "1403008",
						name : "\u79ed\u5f52\u53bf"
					}, {
						id : "1403010",
						name : "\u957f\u9633\u53bf"
					}, {
						id : "1403012",
						name : "\u4e94\u5cf0\u53bf"
					} ]
				}, {
					id : "14060",
					name : "\u8346\u95e8\u5e02",
					subItems : [ {
						id : "1406001",
						name : "\u4e1c\u5b9d\u533a"
					}, {
						id : "1406002",
						name : "\u6387\u5200\u533a"
					}, {
						id : "1406003",
						name : "\u949f\u7965\u5e02"
					}, {
						id : "1406004",
						name : "\u4eac\u5c71\u53bf"
					}, {
						id : "1406005",
						name : "\u6c99\u6d0b\u53bf"
					} ]
				}, {
					id : "14070",
					name : "\u8346\u5dde\u5e02",
					subItems : [ {
						id : "1407003",
						name : "\u8346\u5dde\u533a"
					}, {
						id : "1407001",
						name : "\u6c99\u5e02\u533a"
					}, {
						id : "1407004",
						name : "\u77f3\u9996\u5e02"
					}, {
						id : "1407005",
						name : "\u6d2a\u6e56\u5e02"
					}, {
						id : "1407006",
						name : "\u677e\u6ecb\u5e02"
					}, {
						id : "1407002",
						name : "\u6c5f\u9675\u53bf"
					}, {
						id : "1407007",
						name : "\u516c\u5b89\u53bf"
					}, {
						id : "1407008",
						name : "\u76d1\u5229\u53bf"
					} ]
				}, {
					id : "14080",
					name : "\u9ec4\u77f3\u5e02",
					subItems : [ {
						id : "1408003",
						name : "\u4e0b\u9646\u533a"
					}, {
						id : "1408004",
						name : "\u94c1\u5c71\u533a"
					}, {
						id : "1408001",
						name : "\u9ec4\u77f3\u6e2f\u533a"
					}, {
						id : "1408002",
						name : "\u897f\u585e\u5c71\u533a"
					}, {
						id : "1408005",
						name : "\u5927\u51b6\u5e02"
					}, {
						id : "1408006",
						name : "\u9633\u65b0\u53bf"
					} ]
				}, {
					id : "14090",
					name : "\u9102\u5dde\u5e02",
					subItems : [ {
						id : "1409001",
						name : "\u9102\u57ce\u533a"
					}, {
						id : "1409002",
						name : "\u6881\u5b50\u6e56\u533a"
					}, {
						id : "1409003",
						name : "\u534e\u5bb9\u533a"
					} ]
				}, {
					id : "14100",
					name : "\u54b8\u5b81\u5e02",
					subItems : [ {
						id : "1410001",
						name : "\u54b8\u5b89\u533a"
					}, {
						id : "1410002",
						name : "\u8d64\u58c1\u5e02"
					}, {
						id : "1410003",
						name : "\u5609\u9c7c\u53bf"
					}, {
						id : "1410004",
						name : "\u901a\u57ce\u53bf"
					}, {
						id : "1410005",
						name : "\u5d07\u9633\u53bf"
					}, {
						id : "1410006",
						name : "\u901a\u5c71\u53bf"
					} ]
				}, {
					id : "14120",
					name : "\u5b5d\u611f\u5e02",
					subItems : [ {
						id : "1412001",
						name : "\u5b5d\u5357\u533a"
					}, {
						id : "1412002",
						name : "\u5e94\u57ce\u5e02"
					}, {
						id : "1412003",
						name : "\u5b89\u9646\u5e02"
					}, {
						id : "1412004",
						name : "\u6c49\u5ddd\u5e02"
					}, {
						id : "1412005",
						name : "\u5b5d\u660c\u53bf"
					}, {
						id : "1412006",
						name : "\u5927\u609f\u53bf"
					}, {
						id : "1412007",
						name : "\u4e91\u68a6\u53bf"
					} ]
				}, {
					id : "14130",
					name : "\u968f\u5dde\u5e02",
					subItems : [ {
						id : "1413001",
						name : "\u66fe\u90fd\u533a"
					}, {
						id : "1413002",
						name : "\u5e7f\u6c34\u5e02"
					}, {
						id : "1413003",
						name : "\u968f\u53bf"
					} ]
				}, {
					id : "14140",
					name : "\u6069\u65bd\u5dde",
					subItems : [ {
						id : "1414001",
						name : "\u6069\u65bd\u5e02"
					}, {
						id : "1414003",
						name : "\u5229\u5ddd\u5e02"
					}, {
						id : "1414004",
						name : "\u5efa\u59cb\u53bf"
					}, {
						id : "1414002",
						name : "\u9e64\u5cf0\u53bf"
					}, {
						id : "1414005",
						name : "\u5df4\u4e1c\u53bf"
					}, {
						id : "1414006",
						name : "\u5ba3\u6069\u53bf"
					}, {
						id : "1414007",
						name : "\u54b8\u4e30\u53bf"
					}, {
						id : "1414008",
						name : "\u6765\u51e4\u53bf"
					} ]
				}, {
					id : "14150",
					name : "\u9ec4\u5188\u5e02",
					subItems : [ {
						id : "1415001",
						name : "\u9ec4\u5dde\u533a"
					}, {
						id : "1415005",
						name : "\u6b66\u7a74\u5e02"
					}, {
						id : "14110",
						name : "\u9ebb\u57ce\u5e02"
					}, {
						id : "1415002",
						name : "\u6d60\u6c34\u53bf"
					}, {
						id : "1415003",
						name : "\u82f1\u5c71\u53bf"
					}, {
						id : "1415004",
						name : "\u8572\u6625\u53bf"
					}, {
						id : "1415006",
						name : "\u9ec4\u6885\u53bf"
					}, {
						id : "1415007",
						name : "\u56e2\u98ce\u53bf"
					}, {
						id : "1415008",
						name : "\u7ea2\u5b89\u53bf"
					}, {
						id : "1415009",
						name : "\u7f57\u7530\u53bf"
					} ]
				}, {
					id : "14190",
					name : "\u7701\u76f4\u8f96",
					subItems : [ {
						id : "14050",
						name : "\u6f5c\u6c5f\u5e02"
					}, {
						id : "14160",
						name : "\u4ed9\u6843\u5e02"
					}, {
						id : "14170",
						name : "\u5929\u95e8\u5e02"
					}, {
						id : "14180",
						name : "\u795e\u519c\u67b6"
					} ]
				} ]
			},
			{
				id : "15000",
				name : "\u6e56\u5357\u7701",
				subItems : [ {
					id : "15",
					name : "\u957f\u6c99\u5e02",
					subItems : [ {
						id : "1501",
						name : "\u8299\u84c9\u533a"
					}, {
						id : "1503",
						name : "\u5929\u5fc3\u533a"
					}, {
						id : "1505",
						name : "\u5cb3\u9e93\u533a"
					}, {
						id : "1506",
						name : "\u5f00\u798f\u533a"
					}, {
						id : "1507",
						name : "\u96e8\u82b1\u533a"
					}, {
						id : "1508",
						name : "\u6d4f\u9633\u5e02"
					}, {
						id : "1504",
						name : "\u5b81\u4e61\u53bf"
					}, {
						id : "1502",
						name : "\u671b\u57ce\u53bf"
					}, {
						id : "1509",
						name : "\u957f\u6c99\u53bf"
					} ]
				}, {
					id : "15030",
					name : "\u6e58\u6f6d\u5e02",
					subItems : [ {
						id : "1503001",
						name : "\u96e8\u6e56\u533a"
					}, {
						id : "1503002",
						name : "\u5cb3\u5858\u533a"
					}, {
						id : "1503003",
						name : "\u6e58\u4e61\u5e02"
					}, {
						id : "1503004",
						name : "\u97f6\u5c71\u5e02"
					}, {
						id : "1503005",
						name : "\u6e58\u6f6d\u53bf"
					} ]
				}, {
					id : "15020",
					name : "\u682a\u6d32\u5e02",
					subItems : [ {
						id : "1502001",
						name : "\u8377\u5858\u533a"
					}, {
						id : "1502003",
						name : "\u82a6\u6dde\u533a"
					}, {
						id : "1502005",
						name : "\u77f3\u5cf0\u533a"
					}, {
						id : "1502006",
						name : "\u5929\u5143\u533a"
					}, {
						id : "1502007",
						name : "\u91b4\u9675\u5e02"
					}, {
						id : "1502002",
						name : "\u8336\u9675\u53bf"
					}, {
						id : "1502004",
						name : "\u708e\u9675\u53bf"
					}, {
						id : "1502008",
						name : "\u682a\u6d32\u53bf"
					}, {
						id : "1502009",
						name : "\u6538\u53bf"
					} ]
				}, {
					id : "15040",
					name : "\u5e38\u5fb7\u5e02",
					subItems : [ {
						id : "1504001",
						name : "\u6b66\u9675\u533a"
					}, {
						id : "1504003",
						name : "\u9f0e\u57ce\u533a"
					}, {
						id : "1504005",
						name : "\u6d25\u5e02\u5e02"
					}, {
						id : "1504002",
						name : "\u6843\u6e90\u53bf"
					}, {
						id : "1504004",
						name : "\u77f3\u95e8\u53bf"
					}, {
						id : "1504006",
						name : "\u5b89\u4e61\u53bf"
					}, {
						id : "1504007",
						name : "\u6c49\u5bff\u53bf"
					}, {
						id : "1504009",
						name : "\u4e34\u6fa7\u53bf"
					}, {
						id : "1504008",
						name : "\u6fa7\u53bf"
					} ]
				}, {
					id : "15050",
					name : "\u8861\u9633\u5e02",
					subItems : [ {
						id : "1505001",
						name : "\u73e0\u6656\u533a"
					}, {
						id : "1505003",
						name : "\u96c1\u5cf0\u533a"
					}, {
						id : "1505005",
						name : "\u77f3\u9f13\u533a"
					}, {
						id : "1505007",
						name : "\u84b8\u6e58\u533a"
					}, {
						id : "1505009",
						name : "\u5357\u5cb3\u533a"
					}, {
						id : "1505011",
						name : "\u8012\u9633\u5e02"
					}, {
						id : "1505012",
						name : "\u5e38\u5b81\u5e02"
					}, {
						id : "1505002",
						name : "\u8861\u9633\u53bf"
					}, {
						id : "1505004",
						name : "\u8861\u5357\u53bf"
					}, {
						id : "1505006",
						name : "\u8861\u5c71\u53bf"
					}, {
						id : "1505008",
						name : "\u8861\u4e1c\u53bf"
					}, {
						id : "1505010",
						name : "\u7941\u4e1c\u53bf"
					} ]
				}, {
					id : "15060",
					name : "\u76ca\u9633\u5e02",
					subItems : [ {
						id : "1506001",
						name : "\u8d44\u9633\u533a"
					}, {
						id : "1506002",
						name : "\u8d6b\u5c71\u533a"
					}, {
						id : "1506003",
						name : "\u6c85\u6c5f\u5e02"
					}, {
						id : "1506004",
						name : "\u5357\u53bf"
					}, {
						id : "1506005",
						name : "\u6843\u6c5f\u53bf"
					}, {
						id : "1506006",
						name : "\u5b89\u5316\u53bf"
					} ]
				}, {
					id : "15070",
					name : "\u90f4\u5dde\u5e02",
					subItems : [ {
						id : "1507001",
						name : "\u5317\u6e56\u533a"
					}, {
						id : "1507003",
						name : "\u82cf\u4ed9\u533a"
					}, {
						id : "1507005",
						name : "\u8d44\u5174\u5e02"
					}, {
						id : "1507002",
						name : "\u4e34\u6b66\u53bf"
					}, {
						id : "1507004",
						name : "\u6c5d\u57ce\u53bf"
					}, {
						id : "1507006",
						name : "\u6842\u4e1c\u53bf"
					}, {
						id : "1507007",
						name : "\u6842\u9633\u53bf"
					}, {
						id : "1507008",
						name : "\u5b89\u4ec1\u53bf"
					}, {
						id : "1507009",
						name : "\u5b9c\u7ae0\u53bf"
					}, {
						id : "1507010",
						name : "\u6c38\u5174\u53bf"
					}, {
						id : "1507011",
						name : "\u5609\u79be\u53bf"
					} ]
				}, {
					id : "15080",
					name : "\u5cb3\u9633\u5e02",
					subItems : [ {
						id : "1508001",
						name : "\u5cb3\u9633\u697c\u533a"
					}, {
						id : "1508003",
						name : "\u4e91\u6eaa\u533a"
					}, {
						id : "1508005",
						name : "\u541b\u5c71\u533a"
					}, {
						id : "1508006",
						name : "\u6c68\u7f57\u5e02"
					}, {
						id : "1508007",
						name : "\u4e34\u6e58\u5e02"
					}, {
						id : "1508002",
						name : "\u6e58\u9634\u53bf"
					}, {
						id : "1508004",
						name : "\u5e73\u6c5f\u53bf"
					}, {
						id : "1508008",
						name : "\u5cb3\u9633\u53bf"
					}, {
						id : "1508009",
						name : "\u534e\u5bb9\u53bf"
					} ]
				}, {
					id : "15090",
					name : "\u5f20\u5bb6\u754c\u5e02",
					subItems : [ {
						id : "1509001",
						name : "\u6c38\u5b9a\u533a"
					}, {
						id : "1509002",
						name : "\u6b66\u9675\u6e90"
					}, {
						id : "1509003",
						name : "\u6148\u5229\u53bf"
					}, {
						id : "1509004",
						name : "\u6851\u690d\u53bf"
					} ]
				}, {
					id : "15100",
					name : "\u6c38\u5dde\u5e02",
					subItems : [ {
						id : "1510001",
						name : "\u96f6\u9675\u533a"
					}, {
						id : "1510003",
						name : "\u51b7\u6c34\u6ee9\u533a"
					}, {
						id : "1510002",
						name : "\u5b81\u8fdc\u53bf"
					}, {
						id : "1510004",
						name : "\u84dd\u5c71\u53bf"
					}, {
						id : "1510005",
						name : "\u7941\u9633\u53bf"
					}, {
						id : "1510006",
						name : "\u65b0\u7530\u53bf"
					}, {
						id : "1510007",
						name : "\u4e1c\u5b89\u53bf"
					}, {
						id : "1510008",
						name : "\u6c5f\u534e\u53bf"
					}, {
						id : "1510009",
						name : "\u53cc\u724c\u53bf"
					}, {
						id : "1510011",
						name : "\u6c5f\u6c38\u53bf"
					}, {
						id : "1510010",
						name : "\u9053\u53bf"
					} ]
				}, {
					id : "15120",
					name : "\u5a04\u5e95\u5e02",
					subItems : [ {
						id : "1512001",
						name : "\u5a04\u661f\u533a"
					}, {
						id : "1512003",
						name : "\u6d9f\u6e90\u5e02"
					}, {
						id : "1512002",
						name : "\u51b7\u6c34\u6c5f\u5e02"
					}, {
						id : "1512004",
						name : "\u53cc\u5cf0\u53bf"
					}, {
						id : "1512005",
						name : "\u65b0\u5316\u53bf"
					} ]
				}, {
					id : "15130",
					name : "\u6000\u5316\u5e02",
					subItems : [ {
						id : "1513001",
						name : "\u9e64\u57ce\u533a"
					}, {
						id : "1513003",
						name : "\u6d2a\u6c5f\u5e02"
					}, {
						id : "1513002",
						name : "\u9ebb\u9633\u53bf"
					}, {
						id : "1513004",
						name : "\u65b0\u6643\u53bf"
					}, {
						id : "1513005",
						name : "\u6c85\u9675\u53bf"
					}, {
						id : "1513006",
						name : "\u82b7\u6c5f\u53bf"
					}, {
						id : "1513007",
						name : "\u8fb0\u6eaa\u53bf"
					}, {
						id : "1513008",
						name : "\u9756\u5dde\u53bf"
					}, {
						id : "1513009",
						name : "\u6e86\u6d66\u53bf"
					}, {
						id : "1513010",
						name : "\u901a\u9053\u53bf"
					}, {
						id : "1513011",
						name : "\u4e2d\u65b9\u53bf"
					}, {
						id : "1513012",
						name : "\u4f1a\u540c\u53bf"
					} ]
				}, {
					id : "15140",
					name : "\u90b5\u9633\u5e02",
					subItems : [ {
						id : "1514001",
						name : "\u53cc\u6e05\u533a"
					}, {
						id : "1514003",
						name : "\u5927\u7965\u533a"
					}, {
						id : "1514005",
						name : "\u5317\u5854\u533a"
					}, {
						id : "1514007",
						name : "\u6b66\u5188\u5e02"
					}, {
						id : "1514002",
						name : "\u9686\u56de\u53bf"
					}, {
						id : "1514004",
						name : "\u6d1e\u53e3\u53bf"
					}, {
						id : "1514006",
						name : "\u7ee5\u5b81\u53bf"
					}, {
						id : "1514008",
						name : "\u65b0\u5b81\u53bf"
					}, {
						id : "1514009",
						name : "\u90b5\u4e1c\u53bf"
					}, {
						id : "1514010",
						name : "\u57ce\u6b65\u53bf"
					}, {
						id : "1514011",
						name : "\u65b0\u90b5\u53bf"
					}, {
						id : "1514012",
						name : "\u90b5\u9633\u53bf"
					} ]
				}, {
					id : "15150",
					name : "\u6e58\u897f\u5dde",
					subItems : [ {
						id : "15110",
						name : "\u5409\u9996\u5e02"
					}, {
						id : "1515001",
						name : "\u9f99\u5c71\u53bf"
					}, {
						id : "1515002",
						name : "\u6cf8\u6eaa\u53bf"
					}, {
						id : "1515003",
						name : "\u51e4\u51f0\u53bf"
					}, {
						id : "1515004",
						name : "\u82b1\u57a3\u53bf"
					}, {
						id : "1515005",
						name : "\u4fdd\u9756\u53bf"
					}, {
						id : "1515006",
						name : "\u53e4\u4e08\u53bf"
					}, {
						id : "1515007",
						name : "\u6c38\u987a\u53bf"
					} ]
				} ]
			},
			{
				id : "5000",
				name : "\u5409\u6797\u7701",
				subItems : [ {
					id : "10",
					name : "\u957f\u6625\u5e02",
					subItems : [ {
						id : "1001",
						name : "\u671d\u9633\u533a"
					}, {
						id : "1003",
						name : "\u5357\u5173\u533a"
					}, {
						id : "1005",
						name : "\u5bbd\u57ce\u533a"
					}, {
						id : "1007",
						name : "\u4e8c\u9053\u533a"
					}, {
						id : "1008",
						name : "\u7eff\u56ed\u533a"
					}, {
						id : "1009",
						name : "\u53cc\u9633\u533a"
					}, {
						id : "1010",
						name : "\u5fb7\u60e0\u5e02"
					}, {
						id : "1002",
						name : "\u4e5d\u53f0\u5e02"
					}, {
						id : "1004",
						name : "\u6986\u6811\u5e02"
					}, {
						id : "1006",
						name : "\u519c\u5b89\u53bf"
					} ]
				}, {
					id : "5020",
					name : "\u5409\u6797\u5e02",
					subItems : [ {
						id : "502002",
						name : "\u660c\u9091\u533a"
					}, {
						id : "502004",
						name : "\u9f99\u6f6d\u533a"
					}, {
						id : "502005",
						name : "\u4e30\u6ee1\u533a"
					}, {
						id : "502009",
						name : "\u8239\u8425\u533a"
					}, {
						id : "502001",
						name : "\u78d0\u77f3\u5e02"
					}, {
						id : "502006",
						name : "\u86df\u6cb3\u5e02"
					}, {
						id : "502007",
						name : "\u6866\u7538\u5e02"
					}, {
						id : "502008",
						name : "\u8212\u5170\u5e02"
					}, {
						id : "502003",
						name : "\u6c38\u5409\u53bf"
					} ]
				}, {
					id : "5030",
					name : "\u8fbd\u6e90\u5e02",
					subItems : [ {
						id : "503001",
						name : "\u9f99\u5c71\u533a"
					}, {
						id : "503002",
						name : "\u897f\u5b89\u533a"
					}, {
						id : "503003",
						name : "\u4e1c\u4e30\u53bf"
					}, {
						id : "503004",
						name : "\u4e1c\u8fbd\u53bf"
					} ]
				}, {
					id : "5040",
					name : "\u56db\u5e73\u5e02",
					subItems : [ {
						id : "504001",
						name : "\u94c1\u897f\u533a"
					}, {
						id : "504002",
						name : "\u94c1\u4e1c\u533a"
					}, {
						id : "504003",
						name : "\u516c\u4e3b\u5cad\u5e02"
					}, {
						id : "504004",
						name : "\u53cc\u8fbd\u5e02"
					}, {
						id : "504005",
						name : "\u68a8\u6811\u53bf"
					}, {
						id : "504006",
						name : "\u4f0a\u901a\u53bf"
					} ]
				}, {
					id : "5050",
					name : "\u901a\u5316\u5e02",
					subItems : [ {
						id : "505001",
						name : "\u4e1c\u660c\u533a"
					}, {
						id : "505002",
						name : "\u4e8c\u9053\u6c5f\u533a"
					}, {
						id : "505003",
						name : "\u6885\u6cb3\u53e3\u5e02"
					}, {
						id : "505004",
						name : "\u96c6\u5b89\u5e02"
					}, {
						id : "505005",
						name : "\u901a\u5316\u53bf"
					}, {
						id : "505006",
						name : "\u8f89\u5357\u53bf"
					}, {
						id : "505007",
						name : "\u67f3\u6cb3\u53bf"
					} ]
				}, {
					id : "5070",
					name : "\u677e\u539f\u5e02",
					subItems : [ {
						id : "507001",
						name : "\u5b81\u6c5f\u533a"
					}, {
						id : "507002",
						name : "\u957f\u5cad\u53bf"
					}, {
						id : "507003",
						name : "\u4e7e\u5b89\u53bf"
					}, {
						id : "507004",
						name : "\u6276\u4f59\u53bf"
					}, {
						id : "507005",
						name : "\u524d\u90ed\u5c14\u7f57\u65af\u53bf"
					} ]
				}, {
					id : "5080",
					name : "\u767d\u57ce\u5e02",
					subItems : [ {
						id : "508001",
						name : "\u6d2e\u5317\u533a"
					}, {
						id : "508002",
						name : "\u6d2e\u5357\u5e02"
					}, {
						id : "508003",
						name : "\u5927\u5b89\u5e02"
					}, {
						id : "508004",
						name : "\u9547\u8d49\u53bf"
					}, {
						id : "508005",
						name : "\u901a\u6986\u53bf"
					} ]
				}, {
					id : "5090",
					name : "\u767d\u5c71\u5e02",
					subItems : [ {
						id : "509001",
						name : "\u516b\u9053\u6c5f\u533a"
					}, {
						id : "509002",
						name : "\u6c5f\u6e90\u533a"
					}, {
						id : "509003",
						name : "\u4e34\u6c5f\u5e02"
					}, {
						id : "509004",
						name : "\u629a\u677e\u53bf"
					}, {
						id : "509005",
						name : "\u9756\u5b87\u53bf"
					}, {
						id : "509006",
						name : "\u957f\u767d\u53bf"
					} ]
				}, {
					id : "5100",
					name : "\u5ef6\u8fb9",
					subItems : [ {
						id : "5060",
						name : "\u5ef6\u5409\u5e02"
					}, {
						id : "510003",
						name : "\u56fe\u4eec\u5e02"
					}, {
						id : "510004",
						name : "\u6566\u5316\u5e02"
					}, {
						id : "510005",
						name : "\u73f2\u6625\u5e02"
					}, {
						id : "510006",
						name : "\u9f99\u4e95\u5e02"
					}, {
						id : "510007",
						name : "\u548c\u9f99\u5e02"
					}, {
						id : "510002",
						name : "\u5b89\u56fe\u53bf"
					}, {
						id : "510008",
						name : "\u6c6a\u6e05\u53bf"
					} ]
				} ]
			},
			{
				id : "11000",
				name : "\u6c5f\u897f\u7701",
				subItems : [ {
					id : "95",
					name : "\u5357\u660c\u5e02",
					subItems : [ {
						id : "9501",
						name : "\u4e1c\u6e56\u533a"
					}, {
						id : "9503",
						name : "\u897f\u6e56\u533a"
					}, {
						id : "9506",
						name : "\u6e7e\u91cc\u533a"
					}, {
						id : "9505",
						name : "\u9752\u4e91\u8c31\u533a"
					}, {
						id : "9507",
						name : "\u9752\u5c71\u6e56\u533a"
					}, {
						id : "9504",
						name : "\u8fdb\u8d24\u53bf"
					}, {
						id : "9502",
						name : "\u5b89\u4e49\u53bf"
					}, {
						id : "9508",
						name : "\u5357\u660c\u53bf"
					}, {
						id : "9509",
						name : "\u65b0\u5efa\u53bf"
					} ]
				}, {
					id : "11020",
					name : "\u4e5d\u6c5f\u5e02",
					subItems : [ {
						id : "1102001",
						name : "\u6d54\u9633\u533a"
					}, {
						id : "1102003",
						name : "\u5e90\u5c71\u533a"
					}, {
						id : "1102005",
						name : "\u745e\u660c\u5e02"
					}, {
						id : "1102002",
						name : "\u5fb7\u5b89\u53bf"
					}, {
						id : "1102004",
						name : "\u661f\u5b50\u53bf"
					}, {
						id : "1102006",
						name : "\u90fd\u660c\u53bf"
					}, {
						id : "1102007",
						name : "\u4e5d\u6c5f\u53bf"
					}, {
						id : "1102008",
						name : "\u6e56\u53e3\u53bf"
					}, {
						id : "1102009",
						name : "\u6b66\u5b81\u53bf"
					}, {
						id : "1102010",
						name : "\u5f6d\u6cfd\u53bf"
					}, {
						id : "1102011",
						name : "\u4fee\u6c34\u53bf"
					}, {
						id : "1102012",
						name : "\u6c38\u4fee\u53bf"
					} ]
				}, {
					id : "11030",
					name : "\u666f\u5fb7\u9547\u5e02",
					subItems : [ {
						id : "1103001",
						name : "\u73e0\u5c71\u533a"
					}, {
						id : "1103002",
						name : "\u660c\u6c5f\u533a"
					}, {
						id : "1103003",
						name : "\u4e50\u5e73\u5e02"
					}, {
						id : "1103004",
						name : "\u6d6e\u6881\u53bf"
					} ]
				}, {
					id : "11040",
					name : "\u65b0\u4f59\u5e02",
					subItems : [ {
						id : "1104001",
						name : "\u6e1d\u6c34\u533a"
					}, {
						id : "1104002",
						name : "\u5206\u5b9c\u53bf"
					} ]
				}, {
					id : "11050",
					name : "\u9e70\u6f6d\u5e02",
					subItems : [ {
						id : "1105001",
						name : "\u6708\u6e56\u533a"
					}, {
						id : "1105002",
						name : "\u8d35\u6eaa\u5e02"
					}, {
						id : "1105003",
						name : "\u4f59\u6c5f\u53bf"
					} ]
				}, {
					id : "11060",
					name : "\u4e0a\u9976\u5e02",
					subItems : [ {
						id : "1106001",
						name : "\u4fe1\u5dde\u533a"
					}, {
						id : "1106003",
						name : "\u5fb7\u5174\u5e02"
					}, {
						id : "1106002",
						name : "\u5f0b\u9633\u53bf"
					}, {
						id : "1106004",
						name : "\u4f59\u5e72\u53bf"
					}, {
						id : "1106005",
						name : "\u4e0a\u9976\u53bf"
					}, {
						id : "1106006",
						name : "\u9131\u9633\u53bf"
					}, {
						id : "1106007",
						name : "\u5e7f\u4e30\u53bf"
					}, {
						id : "1106008",
						name : "\u4e07\u5e74\u53bf"
					}, {
						id : "1106009",
						name : "\u7389\u5c71\u53bf"
					}, {
						id : "1106010",
						name : "\u5a7a\u6e90\u53bf"
					}, {
						id : "1106011",
						name : "\u94c5\u5c71\u53bf"
					}, {
						id : "1106012",
						name : "\u6a2a\u5cf0\u53bf"
					} ]
				}, {
					id : "11070",
					name : "\u840d\u4e61\u5e02",
					subItems : [ {
						id : "1107001",
						name : "\u5b89\u6e90\u533a"
					}, {
						id : "1107002",
						name : "\u6e58\u4e1c\u533a"
					}, {
						id : "1107003",
						name : "\u83b2\u82b1\u53bf"
					}, {
						id : "1107004",
						name : "\u82a6\u6eaa\u53bf"
					}, {
						id : "1107005",
						name : "\u4e0a\u6817\u53bf"
					} ]
				}, {
					id : "11080",
					name : "\u5409\u5b89\u5e02",
					subItems : [ {
						id : "1108001",
						name : "\u5409\u5dde\u533a"
					}, {
						id : "1108003",
						name : "\u9752\u539f\u533a"
					}, {
						id : "1108005",
						name : "\u4e95\u5188\u5c71\u5e02"
					}, {
						id : "1108002",
						name : "\u6c38\u4e30\u53bf"
					}, {
						id : "1108004",
						name : "\u6cf0\u548c\u53bf"
					}, {
						id : "1108006",
						name : "\u9042\u5ddd\u53bf"
					}, {
						id : "1108007",
						name : "\u5409\u5b89\u53bf"
					}, {
						id : "1108008",
						name : "\u4e07\u5b89\u53bf"
					}, {
						id : "1108009",
						name : "\u5409\u6c34\u53bf"
					}, {
						id : "1108010",
						name : "\u5b89\u798f\u53bf"
					}, {
						id : "1108011",
						name : "\u5ce1\u6c5f\u53bf"
					}, {
						id : "1108012",
						name : "\u6c38\u65b0\u53bf"
					}, {
						id : "1108013",
						name : "\u65b0\u5e72\u53bf"
					} ]
				}, {
					id : "11090",
					name : "\u8d63\u5dde\u5e02",
					subItems : [ {
						id : "1109001",
						name : "\u7ae0\u8d21\u533a"
					}, {
						id : "1109004",
						name : "\u745e\u91d1\u5e02"
					}, {
						id : "1109007",
						name : "\u5357\u5eb7\u5e02"
					}, {
						id : "1109010",
						name : "\u8d63\u53bf"
					}, {
						id : "1109003",
						name : "\u5174\u56fd\u53bf"
					}, {
						id : "1109002",
						name : "\u5d07\u4e49\u53bf"
					}, {
						id : "1109005",
						name : "\u5b89\u8fdc\u53bf"
					}, {
						id : "1109006",
						name : "\u4f1a\u660c\u53bf"
					}, {
						id : "1109008",
						name : "\u9f99\u5357\u53bf"
					}, {
						id : "1109009",
						name : "\u5bfb\u4e4c\u53bf"
					}, {
						id : "1109011",
						name : "\u5b9a\u5357\u53bf"
					}, {
						id : "1109012",
						name : "\u77f3\u57ce\u53bf"
					}, {
						id : "1109013",
						name : "\u4fe1\u4e30\u53bf"
					}, {
						id : "1109014",
						name : "\u5168\u5357\u53bf"
					}, {
						id : "1109015",
						name : "\u5927\u4f59\u53bf"
					}, {
						id : "1109016",
						name : "\u5b81\u90fd\u53bf"
					}, {
						id : "1109017",
						name : "\u4e0a\u72b9\u53bf"
					}, {
						id : "1109018",
						name : "\u4e8e\u90fd\u53bf"
					} ]
				}, {
					id : "11100",
					name : "\u5b9c\u6625\u5e02",
					subItems : [ {
						id : "1110001",
						name : "\u8881\u5dde\u533a"
					}, {
						id : "1110005",
						name : "\u6a1f\u6811\u5e02"
					}, {
						id : "1110003",
						name : "\u4e30\u57ce\u5e02"
					}, {
						id : "1110007",
						name : "\u9ad8\u5b89\u5e02"
					}, {
						id : "1110002",
						name : "\u5b9c\u4e30\u53bf"
					}, {
						id : "1110004",
						name : "\u9756\u5b89\u53bf"
					}, {
						id : "1110006",
						name : "\u94dc\u9f13\u53bf"
					}, {
						id : "1110008",
						name : "\u5949\u65b0\u53bf"
					}, {
						id : "1110009",
						name : "\u4e07\u8f7d\u53bf"
					}, {
						id : "1110010",
						name : "\u4e0a\u9ad8\u53bf"
					} ]
				}, {
					id : "11110",
					name : "\u629a\u5dde\u5e02",
					subItems : [ {
						id : "1111001",
						name : "\u4e34\u5ddd\u533a"
					}, {
						id : "1111002",
						name : "\u91d1\u6eaa\u53bf"
					}, {
						id : "1111003",
						name : "\u5357\u57ce\u53bf"
					}, {
						id : "1111004",
						name : "\u8d44\u6eaa\u53bf"
					}, {
						id : "1111005",
						name : "\u9ece\u5ddd\u53bf"
					}, {
						id : "1111006",
						name : "\u4e1c\u4e61\u53bf"
					}, {
						id : "1111007",
						name : "\u5357\u4e30\u53bf"
					}, {
						id : "1111008",
						name : "\u5e7f\u660c\u53bf"
					}, {
						id : "1111009",
						name : "\u5d07\u4ec1\u53bf"
					}, {
						id : "1111010",
						name : "\u4e50\u5b89\u53bf"
					}, {
						id : "1111011",
						name : "\u5b9c\u9ec4\u53bf"
					} ]
				} ]
			},
			{
				id : "4000",
				name : "\u8fbd\u5b81\u7701",
				subItems : [ {
					id : "120",
					name : "\u6c88\u9633\u5e02",
					subItems : [ {
						id : "12001",
						name : "\u6c88\u6cb3\u533a"
					}, {
						id : "12003",
						name : "\u548c\u5e73\u533a"
					}, {
						id : "12004",
						name : "\u4e8e\u6d2a\u533a"
					}, {
						id : "12005",
						name : "\u5927\u4e1c\u533a"
					}, {
						id : "12007",
						name : "\u7687\u59d1\u533a"
					}, {
						id : "12013",
						name : "\u4e1c\u9675\u533a"
					}, {
						id : "12009",
						name : "\u94c1\u897f\u533a"
					}, {
						id : "12002",
						name : "\u6c88\u5317\u65b0\u533a"
					}, {
						id : "12011",
						name : "\u82cf\u5bb6\u5c6f\u533a"
					}, {
						id : "12006",
						name : "\u65b0\u6c11\u5e02"
					}, {
						id : "12008",
						name : "\u8fbd\u4e2d\u53bf"
					}, {
						id : "12010",
						name : "\u5eb7\u5e73\u53bf"
					}, {
						id : "12012",
						name : "\u6cd5\u5e93\u53bf"
					} ]
				}, {
					id : "4030",
					name : "\u978d\u5c71\u5e02",
					subItems : [ {
						id : "403001",
						name : "\u94c1\u4e1c\u533a"
					}, {
						id : "403002",
						name : "\u94c1\u897f\u533a"
					}, {
						id : "403003",
						name : "\u7acb\u5c71\u533a"
					}, {
						id : "403004",
						name : "\u5343\u5c71\u533a"
					}, {
						id : "403005",
						name : "\u6d77\u57ce\u5e02"
					}, {
						id : "403006",
						name : "\u53f0\u5b89\u53bf"
					}, {
						id : "403007",
						name : "\u5cab\u5ca9\u53bf"
					} ]
				}, {
					id : "30",
					name : "\u5927\u8fde\u5e02",
					subItems : [ {
						id : "3001",
						name : "\u897f\u5c97\u533a"
					}, {
						id : "3003",
						name : "\u4e2d\u5c71\u533a"
					}, {
						id : "3004",
						name : "\u5e84\u6cb3\u5e02"
					}, {
						id : "3009",
						name : "\u91d1\u5dde\u533a"
					}, {
						id : "3005",
						name : "\u6c99\u6cb3\u53e3\u533a"
					}, {
						id : "3007",
						name : "\u7518\u4e95\u5b50\u533a"
					}, {
						id : "3008",
						name : "\u65c5\u987a\u53e3\u533a"
					}, {
						id : "3002",
						name : "\u666e\u5170\u5e97\u5e02"
					}, {
						id : "3010",
						name : "\u74e6\u623f\u5e97\u5e02"
					}, {
						id : "3006",
						name : "\u957f\u6d77\u53bf"
					} ]
				}, {
					id : "4040",
					name : "\u846b\u82a6\u5c9b\u5e02",
					subItems : [ {
						id : "404001",
						name : "\u9f99\u6e2f\u533a"
					}, {
						id : "404002",
						name : "\u8fde\u5c71\u533a"
					}, {
						id : "404003",
						name : "\u5357\u7968\u533a"
					}, {
						id : "404004",
						name : "\u5174\u57ce\u5e02"
					}, {
						id : "404005",
						name : "\u7ee5\u4e2d\u53bf"
					}, {
						id : "404006",
						name : "\u5efa\u660c\u53bf"
					} ]
				}, {
					id : "4050",
					name : "\u8425\u53e3\u5e02",
					subItems : [ {
						id : "405001",
						name : "\u7ad9\u524d\u533a"
					}, {
						id : "405002",
						name : "\u897f\u5e02\u533a"
					}, {
						id : "405004",
						name : "\u8001\u8fb9\u533a"
					}, {
						id : "405003",
						name : "\u9c85\u9c7c\u5708\u533a"
					}, {
						id : "405005",
						name : "\u76d6\u5dde\u5e02"
					}, {
						id : "405006",
						name : "\u5927\u77f3\u6865\u5e02"
					} ]
				}, {
					id : "4010",
					name : "\u629a\u987a\u5e02",
					subItems : [ {
						id : "401001",
						name : "\u987a\u57ce\u533a"
					}, {
						id : "401002",
						name : "\u65b0\u629a\u533a"
					}, {
						id : "401003",
						name : "\u4e1c\u6d32\u533a"
					}, {
						id : "401004",
						name : "\u671b\u82b1\u533a"
					}, {
						id : "401005",
						name : "\u629a\u987a\u53bf"
					}, {
						id : "401006",
						name : "\u65b0\u5bbe\u53bf"
					}, {
						id : "401007",
						name : "\u6e05\u539f\u53bf"
					} ]
				}, {
					id : "4020",
					name : "\u9526\u5dde\u5e02",
					subItems : [ {
						id : "402001",
						name : "\u592a\u548c\u533a"
					}, {
						id : "402002",
						name : "\u53e4\u5854\u533a"
					}, {
						id : "402003",
						name : "\u51cc\u6cb3\u533a"
					}, {
						id : "402004",
						name : "\u51cc\u6d77\u5e02"
					}, {
						id : "402005",
						name : "\u5317\u9547\u5e02"
					}, {
						id : "402006",
						name : "\u9ed1\u5c71\u53bf"
					}, {
						id : "402007",
						name : "\u4e49\u53bf"
					} ]
				}, {
					id : "4060",
					name : "\u4e39\u4e1c\u5e02",
					subItems : [ {
						id : "406001",
						name : "\u632f\u5174\u533a"
					}, {
						id : "406002",
						name : "\u5143\u5b9d\u533a"
					}, {
						id : "406003",
						name : "\u632f\u5b89\u533a"
					}, {
						id : "406004",
						name : "\u4e1c\u6e2f\u5e02"
					}, {
						id : "406005",
						name : "\u51e4\u57ce\u5e02"
					}, {
						id : "406006",
						name : "\u5bbd\u7538\u53bf"
					} ]
				}, {
					id : "4070",
					name : "\u672c\u6eaa\u5e02",
					subItems : [ {
						id : "407001",
						name : "\u5e73\u5c71\u533a"
					}, {
						id : "407002",
						name : "\u6eaa\u6e56\u533a"
					}, {
						id : "407003",
						name : "\u660e\u5c71\u533a"
					}, {
						id : "407004",
						name : "\u5357\u82ac\u533a"
					}, {
						id : "407005",
						name : "\u672c\u6eaa\u53bf"
					}, {
						id : "407006",
						name : "\u6853\u4ec1\u53bf"
					} ]
				}, {
					id : "4080",
					name : "\u8fbd\u9633\u5e02",
					subItems : [ {
						id : "408001",
						name : "\u767d\u5854\u533a"
					}, {
						id : "408002",
						name : "\u6587\u5723\u533a"
					}, {
						id : "408003",
						name : "\u5b8f\u4f1f\u533a"
					}, {
						id : "408004",
						name : "\u5f13\u957f\u5cad\u533a"
					}, {
						id : "408005",
						name : "\u592a\u5b50\u6cb3\u533a"
					}, {
						id : "408006",
						name : "\u706f\u5854\u5e02"
					}, {
						id : "408007",
						name : "\u8fbd\u9633\u53bf"
					} ]
				}, {
					id : "4090",
					name : "\u94c1\u5cad\u5e02",
					subItems : [ {
						id : "409001",
						name : "\u94f6\u5dde\u533a"
					}, {
						id : "409002",
						name : "\u6e05\u6cb3\u533a"
					}, {
						id : "409004",
						name : "\u5f00\u539f\u5e02"
					}, {
						id : "409003",
						name : "\u8c03\u5175\u5c71\u5e02"
					}, {
						id : "409005",
						name : "\u94c1\u5cad\u53bf"
					}, {
						id : "409006",
						name : "\u897f\u4e30\u53bf"
					}, {
						id : "409007",
						name : "\u660c\u56fe\u53bf"
					} ]
				}, {
					id : "4100",
					name : "\u76d8\u9526\u5e02",
					subItems : [ {
						id : "410001",
						name : "\u5174\u9686\u53f0\u533a"
					}, {
						id : "410002",
						name : "\u53cc\u53f0\u5b50\u533a"
					}, {
						id : "410003",
						name : "\u5927\u6d3c\u53bf"
					}, {
						id : "410004",
						name : "\u76d8\u5c71\u53bf"
					} ]
				}, {
					id : "4110",
					name : "\u961c\u65b0\u5e02",
					subItems : [ {
						id : "411001",
						name : "\u6d77\u5dde\u533a"
					}, {
						id : "411002",
						name : "\u65b0\u90b1\u533a"
					}, {
						id : "411003",
						name : "\u592a\u5e73\u533a"
					}, {
						id : "411005",
						name : "\u7ec6\u6cb3\u533a"
					}, {
						id : "411004",
						name : "\u6e05\u6cb3\u95e8\u533a"
					}, {
						id : "411006",
						name : "\u961c\u65b0\u53bf"
					}, {
						id : "411007",
						name : "\u5f70\u6b66\u53bf"
					} ]
				}, {
					id : "4120",
					name : "\u671d\u9633\u5e02",
					subItems : [ {
						id : "412001",
						name : "\u53cc\u5854\u533a"
					}, {
						id : "412002",
						name : "\u9f99\u57ce\u533a"
					}, {
						id : "412003",
						name : "\u5317\u7968\u5e02"
					}, {
						id : "412004",
						name : "\u51cc\u6e90\u5e02"
					}, {
						id : "412005",
						name : "\u671d\u9633\u53bf"
					}, {
						id : "412006",
						name : "\u5efa\u5e73\u53bf"
					}, {
						id : "412007",
						name : "\u5580\u5587\u6c81\u5de6\u7ffc\u53bf"
					} ]
				} ]
			},
			{
				id : "3000",
				name : "\u5185\u8499\u53e4",
				subItems : [
						{
							id : "70",
							name : "\u547c\u548c\u6d69\u7279\u5e02",
							subItems : [ {
								id : "7001",
								name : "\u56de\u6c11\u533a"
							}, {
								id : "7003",
								name : "\u7389\u6cc9\u533a"
							}, {
								id : "7005",
								name : "\u65b0\u57ce\u533a"
							}, {
								id : "7006",
								name : "\u8d5b\u7f55\u533a"
							}, {
								id : "7009",
								name : "\u6b66\u5ddd\u53bf"
							}, {
								id : "7007",
								name : "\u6258\u514b\u6258\u53bf"
							}, {
								id : "7008",
								name : "\u6e05\u6c34\u6cb3\u53bf"
							}, {
								id : "7002",
								name : "\u548c\u6797\u683c\u5c14\u53bf"
							}, {
								id : "7004",
								name : "\u571f\u9ed8\u7279\u5de6\u65d7"
							} ]
						},
						{
							id : "3020",
							name : "\u5305\u5934\u5e02",
							subItems : [
									{
										id : "302003",
										name : "\u9752\u5c71\u533a"
									},
									{
										id : "302005",
										name : "\u4e1c\u6cb3\u533a"
									},
									{
										id : "302006",
										name : "\u4e5d\u539f\u533a"
									},
									{
										id : "302007",
										name : "\u77f3\u62d0\u533a"
									},
									{
										id : "302001",
										name : "\u6606\u90fd\u4ed1\u533a"
									},
									{
										id : "302008",
										name : "\u767d\u4e91\u77ff\u533a"
									},
									{
										id : "302009",
										name : "\u56fa\u9633\u53bf"
									},
									{
										id : "302002",
										name : "\u571f\u9ed8\u7279\u53f3\u65d7"
									},
									{
										id : "302004",
										name : "\u8fbe\u5c14\u7f55\u8302\u660e\u5b89\u8054\u5408\u65d7"
									} ]
						},
						{
							id : "3030",
							name : "\u8d64\u5cf0\u5e02",
							subItems : [ {
								id : "303001",
								name : "\u7ea2\u5c71\u533a"
							}, {
								id : "303005",
								name : "\u677e\u5c71\u533a"
							}, {
								id : "303003",
								name : "\u5143\u5b9d\u5c71\u533a"
							}, {
								id : "303009",
								name : "\u6797\u897f\u53bf"
							}, {
								id : "303007",
								name : "\u5b81\u57ce\u53bf"
							}, {
								id : "303002",
								name : "\u6556\u6c49\u65d7"
							}, {
								id : "303006",
								name : "\u7fc1\u725b\u7279\u65d7"
							}, {
								id : "303010",
								name : "\u5df4\u6797\u53f3\u65d7"
							}, {
								id : "303008",
								name : "\u514b\u4ec0\u514b\u817e\u65d7"
							}, {
								id : "303011",
								name : "\u5580\u5587\u6c81\u65d7"
							}, {
								id : "303012",
								name : "\u5df4\u6797\u5de6\u65d7"
							}, {
								id : "303004",
								name : "\u963f\u9c81\u79d1\u5c14\u6c81\u65d7"
							} ]
						},
						{
							id : "3040",
							name : "\u901a\u8fbd\u5e02",
							subItems : [
									{
										id : "304001",
										name : "\u79d1\u5c14\u6c81\u533a"
									},
									{
										id : "304003",
										name : "\u970d\u6797\u90ed\u52d2\u5e02"
									},
									{
										id : "304004",
										name : "\u5f00\u9c81\u53bf"
									},
									{
										id : "304007",
										name : "\u5e93\u4f26\u65d7"
									},
									{
										id : "304008",
										name : "\u5948\u66fc\u65d7"
									},
									{
										id : "304002",
										name : "\u624e\u9c81\u7279\u65d7"
									},
									{
										id : "304005",
										name : "\u79d1\u5c14\u6c81\u5de6\u7ffc\u4e2d\u65d7"
									},
									{
										id : "304006",
										name : "\u79d1\u5c14\u6c81\u5de6\u7ffc\u540e\u65d7"
									} ]
						},
						{
							id : "3050",
							name : "\u4e4c\u5170\u5bdf\u5e03\u5e02",
							subItems : [
									{
										id : "305001",
										name : "\u96c6\u5b81\u533a"
									},
									{
										id : "305003",
										name : "\u4e30\u9547\u5e02"
									},
									{
										id : "305005",
										name : "\u5174\u548c\u53bf"
									},
									{
										id : "305007",
										name : "\u5353\u8d44\u53bf"
									},
									{
										id : "305009",
										name : "\u5546\u90fd\u53bf"
									},
									{
										id : "305010",
										name : "\u51c9\u57ce\u53bf"
									},
									{
										id : "305011",
										name : "\u5316\u5fb7\u53bf"
									},
									{
										id : "305002",
										name : "\u56db\u5b50\u738b\u65d7"
									},
									{
										id : "305004",
										name : "\u5bdf\u54c8\u5c14\u53f3\u7ffc\u524d\u65d7"
									},
									{
										id : "305006",
										name : "\u5bdf\u54c8\u5c14\u53f3\u7ffc\u4e2d\u65d7"
									},
									{
										id : "305008",
										name : "\u5bdf\u54c8\u5c14\u53f3\u7ffc\u540e\u65d7"
									} ]
						},
						{
							id : "3060",
							name : "\u4e4c\u6d77\u5e02",
							subItems : [ {
								id : "306001",
								name : "\u6d77\u52c3\u6e7e\u533a"
							}, {
								id : "306002",
								name : "\u4e4c\u8fbe\u533a"
							}, {
								id : "306003",
								name : "\u6d77\u5357\u533a"
							} ]
						},
						{
							id : "3070",
							name : "\u9102\u5c14\u591a\u65af\u5e02",
							subItems : [ {
								id : "307001",
								name : "\u4e1c\u80dc\u533a"
							}, {
								id : "307004",
								name : "\u4e4c\u5ba1\u65d7"
							}, {
								id : "307008",
								name : "\u676d\u9526\u65d7"
							}, {
								id : "307003",
								name : "\u51c6\u683c\u5c14\u65d7"
							}, {
								id : "307002",
								name : "\u8fbe\u62c9\u7279\u65d7"
							}, {
								id : "307006",
								name : "\u9102\u6258\u514b\u65d7"
							}, {
								id : "307005",
								name : "\u4f0a\u91d1\u970d\u6d1b\u65d7"
							}, {
								id : "307007",
								name : "\u9102\u6258\u514b\u524d\u65d7"
							} ]
						},
						{
							id : "3080",
							name : "\u547c\u4f26\u8d1d\u5c14\u5e02",
							subItems : [
									{
										id : "308001",
										name : "\u6d77\u62c9\u5c14\u533a"
									},
									{
										id : "308009",
										name : "\u6839\u6cb3\u5e02"
									},
									{
										id : "308003",
										name : "\u6ee1\u6d32\u91cc\u5e02"
									},
									{
										id : "308005",
										name : "\u7259\u514b\u77f3\u5e02"
									},
									{
										id : "308007",
										name : "\u624e\u5170\u5c6f\u5e02"
									},
									{
										id : "308011",
										name : "\u989d\u5c14\u53e4\u7eb3\u5e02"
									},
									{
										id : "308002",
										name : "\u963f\u8363\u65d7"
									},
									{
										id : "308013",
										name : "\u9648\u5df4\u5c14\u864e\u65d7"
									},
									{
										id : "308004",
										name : "\u65b0\u5df4\u5c14\u864e\u5de6\u65d7"
									},
									{
										id : "308006",
										name : "\u65b0\u5df4\u5c14\u864e\u53f3\u65d7"
									},
									{
										id : "308008",
										name : "\u9102\u4f26\u6625\u81ea\u6cbb\u65d7"
									},
									{
										id : "308012",
										name : "\u9102\u6e29\u514b\u65cf\u81ea\u6cbb\u65d7"
									},
									{
										id : "308010",
										name : "\u83ab\u529b\u8fbe\u74e6\u8fbe\u65a1\u5c14\u65cf\u81ea\u6cbb\u65d7"
									} ]
						},
						{
							id : "3090",
							name : "\u5df4\u5f66\u6dd6\u5c14\u5e02",
							subItems : [ {
								id : "309001",
								name : "\u4e34\u6cb3\u533a"
							}, {
								id : "309002",
								name : "\u4e94\u539f\u53bf"
							}, {
								id : "309003",
								name : "\u78f4\u53e3\u53bf"
							}, {
								id : "309004",
								name : "\u676d\u9526\u540e\u65d7"
							}, {
								id : "309005",
								name : "\u4e4c\u62c9\u7279\u4e2d\u65d7"
							}, {
								id : "309006",
								name : "\u4e4c\u62c9\u7279\u524d\u65d7"
							}, {
								id : "309007",
								name : "\u4e4c\u62c9\u7279\u540e\u65d7"
							} ]
						},
						{
							id : "3100",
							name : "\u9521\u6797\u90ed\u52d2\u76df",
							subItems : [ {
								id : "310001",
								name : "\u9521\u6797\u6d69\u7279\u5e02"
							}, {
								id : "310003",
								name : "\u4e8c\u8fde\u6d69\u7279\u5e02"
							}, {
								id : "310005",
								name : "\u591a\u4f26\u53bf"
							}, {
								id : "310008",
								name : "\u6b63\u84dd\u65d7"
							}, {
								id : "310010",
								name : "\u9576\u9ec4\u65d7"
							}, {
								id : "310006",
								name : "\u6b63\u9576\u767d\u65d7"
							}, {
								id : "310004",
								name : "\u592a\u4ec6\u5bfa\u65d7"
							}, {
								id : "310007",
								name : "\u963f\u5df4\u560e\u65d7"
							}, {
								id : "310012",
								name : "\u82cf\u5c3c\u7279\u5de6\u65d7"
							}, {
								id : "310002",
								name : "\u82cf\u5c3c\u7279\u53f3\u65d7"
							}, {
								id : "310011",
								name : "\u4e1c\u4e4c\u73e0\u7a46\u6c81\u65d7"
							}, {
								id : "310009",
								name : "\u897f\u4e4c\u73e0\u7a46\u6c81\u65d7"
							} ]
						},
						{
							id : "3110",
							name : "\u5174\u5b89\u76df",
							subItems : [
									{
										id : "311001",
										name : "\u4e4c\u5170\u6d69\u7279\u5e02"
									},
									{
										id : "311002",
										name : "\u963f\u5c14\u5c71\u5e02"
									},
									{
										id : "311003",
										name : "\u7a81\u6cc9\u53bf"
									},
									{
										id : "311004",
										name : "\u624e\u8d49\u7279\u65d7"
									},
									{
										id : "311005",
										name : "\u79d1\u5c14\u6c81\u53f3\u7ffc\u524d\u65d7"
									},
									{
										id : "311006",
										name : "\u79d1\u5c14\u6c81\u53f3\u7ffc\u4e2d\u65d7"
									} ]
						}, {
							id : "3120",
							name : "\u963f\u62c9\u5584\u76df",
							subItems : [ {
								id : "312001",
								name : "\u963f\u62c9\u5584\u5de6\u65d7"
							}, {
								id : "312002",
								name : "\u963f\u62c9\u5584\u53f3\u65d7"
							}, {
								id : "312003",
								name : "\u989d\u6d4e\u7eb3\u65d7"
							} ]
						} ]
			},
			{
				id : "26000",
				name : "\u5b81\u590f",
				subItems : [ {
					id : "170",
					name : "\u94f6\u5ddd\u5e02",
					subItems : [ {
						id : "17001",
						name : "\u5174\u5e86\u533a"
					}, {
						id : "17002",
						name : "\u91d1\u51e4\u533a"
					}, {
						id : "17003",
						name : "\u897f\u590f\u533a"
					}, {
						id : "17006",
						name : "\u7075\u6b66\u5e02"
					}, {
						id : "17004",
						name : "\u6c38\u5b81\u53bf"
					}, {
						id : "17005",
						name : "\u8d3a\u5170\u53bf"
					} ]
				}, {
					id : "26010",
					name : "\u77f3\u5634\u5c71\u5e02",
					subItems : [ {
						id : "2601001",
						name : "\u5927\u6b66\u53e3\u533a"
					}, {
						id : "2601002",
						name : "\u60e0\u519c\u533a"
					}, {
						id : "2601003",
						name : "\u5e73\u7f57\u53bf"
					} ]
				}, {
					id : "26020",
					name : "\u5434\u5fe0\u5e02",
					subItems : [ {
						id : "2602001",
						name : "\u5229\u901a\u533a"
					}, {
						id : "2602002",
						name : "\u7ea2\u5bfa\u5821\u533a"
					}, {
						id : "2602003",
						name : "\u76d0\u6c60\u53bf"
					}, {
						id : "2602004",
						name : "\u540c\u5fc3\u53bf"
					}, {
						id : "2602005",
						name : "\u9752\u94dc\u5ce1\u5e02"
					} ]
				}, {
					id : "26030",
					name : "\u56fa\u539f\u5e02",
					subItems : [ {
						id : "2603001",
						name : "\u539f\u5dde\u533a"
					}, {
						id : "2603002",
						name : "\u897f\u5409\u53bf"
					}, {
						id : "2603003",
						name : "\u9686\u5fb7\u53bf"
					}, {
						id : "2603004",
						name : "\u6cfe\u6e90\u53bf"
					}, {
						id : "2603005",
						name : "\u5f6d\u9633\u53bf"
					} ]
				}, {
					id : "26040",
					name : "\u4e2d\u536b\u5e02",
					subItems : [ {
						id : "2604001",
						name : "\u6c99\u5761\u5934\u533a"
					}, {
						id : "2604002",
						name : "\u4e2d\u5b81\u53bf"
					}, {
						id : "2604003",
						name : "\u6d77\u539f\u53bf"
					} ]
				} ]
			},
			{
				id : "25000",
				name : "\u9752\u6d77\u7701",
				subItems : [ {
					id : "165",
					name : "\u897f\u5b81\u5e02",
					subItems : [ {
						id : "16501",
						name : "\u57ce\u4e1c\u533a"
					}, {
						id : "16502",
						name : "\u57ce\u4e2d\u533a"
					}, {
						id : "16503",
						name : "\u57ce\u897f\u533a"
					}, {
						id : "16504",
						name : "\u57ce\u5317\u533a"
					}, {
						id : "16505",
						name : "\u6e5f\u4e2d\u53bf"
					}, {
						id : "16506",
						name : "\u6e5f\u6e90\u53bf"
					}, {
						id : "16507",
						name : "\u5927\u901a\u53bf"
					} ]
				}, {
					id : "25010",
					name : "\u6d77\u4e1c",
					subItems : [ {
						id : "2501001",
						name : "\u5e73\u5b89\u53bf"
					}, {
						id : "2501002",
						name : "\u4e50\u90fd\u53bf"
					}, {
						id : "2501003",
						name : "\u6c11\u548c\u53bf"
					}, {
						id : "2501004",
						name : "\u4e92\u52a9\u53bf"
					}, {
						id : "2501005",
						name : "\u5316\u9686\u53bf"
					}, {
						id : "2501006",
						name : "\u5faa\u5316\u53bf"
					} ]
				}, {
					id : "25020",
					name : "\u6d77\u5317\u5dde",
					subItems : [ {
						id : "2502001",
						name : "\u6d77\u664f\u53bf"
					}, {
						id : "2502002",
						name : "\u7941\u8fde\u53bf"
					}, {
						id : "2502003",
						name : "\u521a\u5bdf\u53bf"
					}, {
						id : "2502004",
						name : "\u95e8\u6e90\u53bf"
					} ]
				}, {
					id : "25030",
					name : "\u9ec4\u5357\u5dde",
					subItems : [ {
						id : "2503001",
						name : "\u540c\u4ec1\u53bf"
					}, {
						id : "2503002",
						name : "\u5c16\u624e\u53bf"
					}, {
						id : "2503003",
						name : "\u6cfd\u5e93\u53bf"
					}, {
						id : "2503004",
						name : "\u6cb3\u5357\u53bf"
					} ]
				}, {
					id : "25040",
					name : "\u6d77\u5357\u5dde",
					subItems : [ {
						id : "2504001",
						name : "\u5171\u548c\u53bf"
					}, {
						id : "2504002",
						name : "\u540c\u5fb7\u53bf"
					}, {
						id : "2504003",
						name : "\u8d35\u5fb7\u53bf"
					}, {
						id : "2504004",
						name : "\u5174\u6d77\u53bf"
					}, {
						id : "2504005",
						name : "\u8d35\u5357\u53bf"
					} ]
				}, {
					id : "25050",
					name : "\u679c\u6d1b\u5dde",
					subItems : [ {
						id : "2505001",
						name : "\u739b\u6c81\u53bf"
					}, {
						id : "2505002",
						name : "\u73ed\u739b\u53bf"
					}, {
						id : "2505003",
						name : "\u7518\u5fb7\u53bf"
					}, {
						id : "2505004",
						name : "\u8fbe\u65e5\u53bf"
					}, {
						id : "2505005",
						name : "\u4e45\u6cbb\u53bf"
					}, {
						id : "2505006",
						name : "\u739b\u591a\u53bf"
					} ]
				}, {
					id : "25060",
					name : "\u7389\u6811\u5dde",
					subItems : [ {
						id : "2506001",
						name : "\u7389\u6811\u53bf"
					}, {
						id : "2506002",
						name : "\u6742\u591a\u53bf"
					}, {
						id : "2506003",
						name : "\u79f0\u591a\u53bf"
					}, {
						id : "2506004",
						name : "\u6cbb\u591a\u53bf"
					}, {
						id : "2506005",
						name : "\u56ca\u8c26\u53bf"
					}, {
						id : "2506006",
						name : "\u66f2\u9ebb\u83b1\u53bf"
					} ]
				}, {
					id : "25070",
					name : "\u6d77\u897f\u5dde",
					subItems : [ {
						id : "2507001",
						name : "\u5fb7\u4ee4\u54c8\u5e02"
					}, {
						id : "2507002",
						name : "\u683c\u5c14\u6728\u5e02"
					}, {
						id : "2507003",
						name : "\u4e4c\u5170\u53bf"
					}, {
						id : "2507004",
						name : "\u90fd\u5170\u53bf"
					}, {
						id : "2507005",
						name : "\u5929\u5cfb\u53bf"
					} ]
				} ]
			},
			{
				id : "12000",
				name : "\u5c71\u4e1c\u7701",
				subItems : [ {
					id : "75",
					name : "\u6d4e\u5357\u5e02",
					subItems : [ {
						id : "7501",
						name : "\u5386\u4e0b\u533a"
					}, {
						id : "7503",
						name : "\u5e02\u4e2d\u533a"
					}, {
						id : "7505",
						name : "\u69d0\u836b\u533a"
					}, {
						id : "7507",
						name : "\u5929\u6865\u533a"
					}, {
						id : "7508",
						name : "\u5386\u57ce\u533a"
					}, {
						id : "7509",
						name : "\u957f\u6e05\u533a"
					}, {
						id : "7510",
						name : "\u7ae0\u4e18\u5e02"
					}, {
						id : "7502",
						name : "\u5e73\u9634\u53bf"
					}, {
						id : "7504",
						name : "\u6d4e\u9633\u53bf"
					}, {
						id : "7506",
						name : "\u5546\u6cb3\u53bf"
					} ]
				}, {
					id : "12090",
					name : "\u5fb7\u5dde\u5e02",
					subItems : [ {
						id : "1209001",
						name : "\u5fb7\u57ce\u533a"
					}, {
						id : "1209003",
						name : "\u4e50\u9675\u5e02"
					}, {
						id : "1209005",
						name : "\u79b9\u57ce\u5e02"
					}, {
						id : "1209007",
						name : "\u9675\u53bf"
					}, {
						id : "1209002",
						name : "\u9f50\u6cb3\u53bf"
					}, {
						id : "1209004",
						name : "\u5e73\u539f\u53bf"
					}, {
						id : "1209006",
						name : "\u590f\u6d25\u53bf"
					}, {
						id : "1209008",
						name : "\u6b66\u57ce\u53bf"
					}, {
						id : "1209009",
						name : "\u5b81\u6d25\u53bf"
					}, {
						id : "1209010",
						name : "\u5e86\u4e91\u53bf"
					}, {
						id : "1209011",
						name : "\u4e34\u9091\u53bf"
					} ]
				}, {
					id : "12040",
					name : "\u4e1c\u8425\u5e02",
					subItems : [ {
						id : "1204001",
						name : "\u4e1c\u8425\u533a"
					}, {
						id : "1204002",
						name : "\u6cb3\u53e3\u533a"
					}, {
						id : "1204003",
						name : "\u57a6\u5229\u53bf"
					}, {
						id : "1204004",
						name : "\u5229\u6d25\u53bf"
					}, {
						id : "1204005",
						name : "\u5e7f\u9976\u53bf"
					} ]
				}, {
					id : "12060",
					name : "\u6d4e\u5b81\u5e02",
					subItems : [ {
						id : "1206001",
						name : "\u5e02\u4e2d\u533a"
					}, {
						id : "1206003",
						name : "\u4efb\u57ce\u533a"
					}, {
						id : "1206005",
						name : "\u66f2\u961c\u5e02"
					}, {
						id : "1206007",
						name : "\u5156\u5dde\u5e02"
					}, {
						id : "1206009",
						name : "\u90b9\u57ce\u5e02"
					}, {
						id : "1206002",
						name : "\u91d1\u4e61\u53bf"
					}, {
						id : "1206004",
						name : "\u5609\u7965\u53bf"
					}, {
						id : "1206006",
						name : "\u6c76\u4e0a\u53bf"
					}, {
						id : "1206008",
						name : "\u6cd7\u6c34\u53bf"
					}, {
						id : "1206010",
						name : "\u6881\u5c71\u53bf"
					}, {
						id : "1206011",
						name : "\u5fae\u5c71\u53bf"
					}, {
						id : "1206012",
						name : "\u9c7c\u53f0\u53bf"
					} ]
				}, {
					id : "12100",
					name : "\u4e34\u6c82\u5e02",
					subItems : [ {
						id : "1210001",
						name : "\u5170\u5c71\u533a"
					}, {
						id : "1210003",
						name : "\u7f57\u5e84\u533a"
					}, {
						id : "1210005",
						name : "\u6cb3\u4e1c\u533a"
					}, {
						id : "1210002",
						name : "\u8d39\u53bf"
					}, {
						id : "1210004",
						name : "\u5e73\u9091\u53bf"
					}, {
						id : "1210006",
						name : "\u8392\u5357\u53bf"
					}, {
						id : "1210007",
						name : "\u6c82\u5357\u53bf"
					}, {
						id : "1210008",
						name : "\u8499\u9634\u53bf"
					}, {
						id : "1210009",
						name : "\u90ef\u57ce\u53bf"
					}, {
						id : "1210010",
						name : "\u4e34\u6cad\u53bf"
					}, {
						id : "1210011",
						name : "\u6c82\u6c34\u53bf"
					}, {
						id : "1210012",
						name : "\u82cd\u5c71\u53bf"
					} ]
				}, {
					id : "110",
					name : "\u9752\u5c9b\u5e02",
					subItems : [ {
						id : "11001",
						name : "\u5e02\u5357\u533a"
					}, {
						id : "11003",
						name : "\u5e02\u5317\u533a"
					}, {
						id : "11005",
						name : "\u56db\u65b9\u533a"
					}, {
						id : "11007",
						name : "\u9ec4\u5c9b\u533a"
					}, {
						id : "11009",
						name : "\u5d02\u5c71\u533a"
					}, {
						id : "11011",
						name : "\u674e\u6ca7\u533a"
					}, {
						id : "11012",
						name : "\u57ce\u9633\u533a"
					}, {
						id : "11004",
						name : "\u5373\u58a8\u5e02"
					}, {
						id : "11006",
						name : "\u5e73\u5ea6\u5e02"
					}, {
						id : "11002",
						name : "\u80f6\u5dde\u5e02"
					}, {
						id : "11008",
						name : "\u80f6\u5357\u5e02"
					}, {
						id : "11010",
						name : "\u83b1\u897f\u5e02"
					} ]
				}, {
					id : "12080",
					name : "\u65e5\u7167\u5e02",
					subItems : [ {
						id : "1208001",
						name : "\u4e1c\u6e2f\u533a"
					}, {
						id : "1208002",
						name : "\u5c9a\u5c71\u533a"
					}, {
						id : "1208004",
						name : "\u8392\u53bf"
					}, {
						id : "1208003",
						name : "\u4e94\u83b2\u53bf"
					} ]
				}, {
					id : "12070",
					name : "\u6cf0\u5b89\u5e02",
					subItems : [ {
						id : "1207001",
						name : "\u6cf0\u5c71\u533a"
					}, {
						id : "1207002",
						name : "\u5cb1\u5cb3\u533a"
					}, {
						id : "1207003",
						name : "\u65b0\u6cf0\u5e02"
					}, {
						id : "1207004",
						name : "\u80a5\u57ce\u5e02"
					}, {
						id : "1207005",
						name : "\u5b81\u9633\u53bf"
					}, {
						id : "1207006",
						name : "\u4e1c\u5e73\u53bf"
					} ]
				}, {
					id : "146",
					name : "\u5a01\u6d77\u5e02",
					subItems : [ {
						id : "14601",
						name : "\u73af\u7fe0\u533a"
					}, {
						id : "14602",
						name : "\u6587\u767b\u5e02"
					}, {
						id : "14603",
						name : "\u8363\u6210\u5e02"
					}, {
						id : "14604",
						name : "\u4e73\u5c71\u5e02"
					} ]
				}, {
					id : "12050",
					name : "\u6f4d\u574a\u5e02",
					subItems : [ {
						id : "1205001",
						name : "\u6f4d\u57ce\u533a"
					}, {
						id : "1205003",
						name : "\u5bd2\u4ead\u533a"
					}, {
						id : "1205005",
						name : "\u574a\u5b50\u533a"
					}, {
						id : "1205007",
						name : "\u594e\u6587\u533a"
					}, {
						id : "1205004",
						name : "\u9ad8\u5bc6\u5e02"
					}, {
						id : "1205002",
						name : "\u5b89\u4e18\u5e02"
					}, {
						id : "1205006",
						name : "\u660c\u9091\u5e02"
					}, {
						id : "1205009",
						name : "\u9752\u5dde\u5e02"
					}, {
						id : "1205011",
						name : "\u8bf8\u57ce\u5e02"
					}, {
						id : "1205012",
						name : "\u5bff\u5149\u5e02"
					}, {
						id : "1205010",
						name : "\u660c\u4e50\u53bf"
					}, {
						id : "1205008",
						name : "\u4e34\u6710\u53bf"
					} ]
				}, {
					id : "168",
					name : "\u70df\u53f0\u5e02",
					subItems : [ {
						id : "16801",
						name : "\u829d\u7f58\u533a"
					}, {
						id : "16803",
						name : "\u798f\u5c71\u533a"
					}, {
						id : "16805",
						name : "\u725f\u5e73\u533a"
					}, {
						id : "16807",
						name : "\u83b1\u5c71\u533a"
					}, {
						id : "16802",
						name : "\u84ec\u83b1\u5e02"
					}, {
						id : "16804",
						name : "\u62db\u8fdc\u5e02"
					}, {
						id : "16806",
						name : "\u6816\u971e\u5e02"
					}, {
						id : "16808",
						name : "\u6d77\u9633\u5e02"
					}, {
						id : "16809",
						name : "\u9f99\u53e3\u5e02"
					}, {
						id : "16811",
						name : "\u83b1\u9633\u5e02"
					}, {
						id : "16812",
						name : "\u83b1\u5dde\u5e02"
					}, {
						id : "16810",
						name : "\u957f\u5c9b\u53bf"
					} ]
				}, {
					id : "12030",
					name : "\u6dc4\u535a\u5e02",
					subItems : [ {
						id : "1203001",
						name : "\u5f20\u5e97\u533a"
					}, {
						id : "1203003",
						name : "\u6dc4\u5ddd\u533a"
					}, {
						id : "1203004",
						name : "\u535a\u5c71\u533a"
					}, {
						id : "1203005",
						name : "\u4e34\u6dc4\u533a"
					}, {
						id : "1203006",
						name : "\u5468\u6751\u533a"
					}, {
						id : "1203002",
						name : "\u6c82\u6e90\u53bf"
					}, {
						id : "1203007",
						name : "\u6853\u53f0\u53bf"
					}, {
						id : "1203008",
						name : "\u9ad8\u9752\u53bf"
					} ]
				}, {
					id : "12110",
					name : "\u83cf\u6cfd\u5e02",
					subItems : [ {
						id : "1211001",
						name : "\u7261\u4e39\u533a"
					}, {
						id : "1211003",
						name : "\u66f9\u53bf"
					}, {
						id : "1211005",
						name : "\u5355\u53bf"
					}, {
						id : "1211002",
						name : "\u5b9a\u9676\u53bf"
					}, {
						id : "1211004",
						name : "\u4e1c\u660e\u53bf"
					}, {
						id : "1211006",
						name : "\u6210\u6b66\u53bf"
					}, {
						id : "1211007",
						name : "\u5de8\u91ce\u53bf"
					}, {
						id : "1211008",
						name : "\u90d3\u57ce\u53bf"
					}, {
						id : "1211009",
						name : "\u9104\u57ce\u53bf"
					} ]
				}, {
					id : "12120",
					name : "\u6ee8\u5dde\u5e02",
					subItems : [ {
						id : "1212001",
						name : "\u6ee8\u57ce\u533a"
					}, {
						id : "1212002",
						name : "\u60e0\u6c11\u53bf"
					}, {
						id : "1212003",
						name : "\u9633\u4fe1\u53bf"
					}, {
						id : "1212004",
						name : "\u65e0\u68e3\u53bf"
					}, {
						id : "1212005",
						name : "\u6cbe\u5316\u53bf"
					}, {
						id : "1212006",
						name : "\u535a\u5174\u53bf"
					}, {
						id : "1212007",
						name : "\u90b9\u5e73\u53bf"
					} ]
				}, {
					id : "12130",
					name : "\u67a3\u5e84\u5e02",
					subItems : [ {
						id : "1213001",
						name : "\u5e02\u4e2d\u533a"
					}, {
						id : "1213002",
						name : "\u859b\u57ce\u533a"
					}, {
						id : "1213003",
						name : "\u5cc4\u57ce\u533a"
					}, {
						id : "1213004",
						name : "\u53f0\u513f\u5e84\u533a"
					}, {
						id : "1213005",
						name : "\u5c71\u4ead\u533a"
					}, {
						id : "1213006",
						name : "\u6ed5\u5dde\u5e02"
					} ]
				}, {
					id : "12140",
					name : "\u804a\u57ce\u5e02",
					subItems : [ {
						id : "1214001",
						name : "\u4e1c\u660c\u5e9c\u533a"
					}, {
						id : "1214003",
						name : "\u4e34\u6e05\u5e02"
					}, {
						id : "1214005",
						name : "\u8398\u53bf"
					}, {
						id : "1214008",
						name : "\u51a0\u53bf"
					}, {
						id : "1214002",
						name : "\u9ad8\u5510\u53bf"
					}, {
						id : "1214004",
						name : "\u9633\u8c37\u53bf"
					}, {
						id : "1214006",
						name : "\u830c\u5e73\u53bf"
					}, {
						id : "1214007",
						name : "\u4e1c\u963f\u53bf"
					} ]
				}, {
					id : "12150",
					name : "\u83b1\u829c\u5e02",
					subItems : [ {
						id : "1215001",
						name : "\u83b1\u57ce\u533a"
					}, {
						id : "1215002",
						name : "\u94a2\u57ce\u533a"
					} ]
				} ]
			},
			{
				id : "2000",
				name : "\u5c71\u897f\u7701",
				subItems : [ {
					id : "135",
					name : "\u592a\u539f\u5e02",
					subItems : [ {
						id : "13501",
						name : "\u5c0f\u5e97\u533a"
					}, {
						id : "13503",
						name : "\u8fce\u6cfd\u533a"
					}, {
						id : "13510",
						name : "\u664b\u6e90\u533a"
					}, {
						id : "13505",
						name : "\u674f\u82b1\u5cad\u533a"
					}, {
						id : "13507",
						name : "\u5c16\u8349\u576a\u533a"
					}, {
						id : "13509",
						name : "\u4e07\u67cf\u6797\u533a"
					}, {
						id : "13508",
						name : "\u53e4\u4ea4\u5e02"
					}, {
						id : "13502",
						name : "\u6e05\u5f90\u53bf"
					}, {
						id : "13504",
						name : "\u9633\u66f2\u53bf"
					}, {
						id : "13506",
						name : "\u5a04\u70e6\u53bf"
					} ]
				}, {
					id : "2010",
					name : "\u5927\u540c\u5e02",
					subItems : [ {
						id : "201001",
						name : "\u5927\u540c\u57ce\u533a"
					}, {
						id : "201003",
						name : "\u5927\u540c\u77ff\u533a"
					}, {
						id : "201005",
						name : "\u5357\u90ca\u533a"
					}, {
						id : "201007",
						name : "\u65b0\u8363\u533a"
					}, {
						id : "201011",
						name : "\u5927\u540c\u53bf"
					}, {
						id : "201002",
						name : "\u9633\u9ad8\u53bf"
					}, {
						id : "201004",
						name : "\u5929\u9547\u53bf"
					}, {
						id : "201006",
						name : "\u5e7f\u7075\u53bf"
					}, {
						id : "201008",
						name : "\u7075\u4e18\u53bf"
					}, {
						id : "201009",
						name : "\u6d51\u6e90\u53bf"
					}, {
						id : "201010",
						name : "\u5de6\u4e91\u53bf"
					} ]
				}, {
					id : "2020",
					name : "\u4e34\u6c7e\u5e02",
					subItems : [ {
						id : "202001",
						name : "\u5c27\u90fd\u533a"
					}, {
						id : "202006",
						name : "\u4faf\u9a6c\u5e02"
					}, {
						id : "202009",
						name : "\u970d\u5dde\u5e02"
					}, {
						id : "202005",
						name : "\u5409\u53bf"
					}, {
						id : "202013",
						name : "\u96b0\u53bf"
					}, {
						id : "202014",
						name : "\u53e4\u53bf"
					}, {
						id : "202017",
						name : "\u84b2\u53bf"
					}, {
						id : "202002",
						name : "\u6d6e\u5c71\u53bf"
					}, {
						id : "202004",
						name : "\u66f2\u6c83\u53bf"
					}, {
						id : "202003",
						name : "\u6c7e\u897f\u53bf"
					}, {
						id : "202007",
						name : "\u7ffc\u57ce\u53bf"
					}, {
						id : "202008",
						name : "\u4e61\u5b81\u53bf"
					}, {
						id : "202010",
						name : "\u8944\u6c7e\u53bf"
					}, {
						id : "202011",
						name : "\u5927\u5b81\u53bf"
					}, {
						id : "202012",
						name : "\u6d2a\u6d1e\u53bf"
					}, {
						id : "202015",
						name : "\u6c38\u548c\u53bf"
					}, {
						id : "202016",
						name : "\u5b89\u6cfd\u53bf"
					} ]
				}, {
					id : "2030",
					name : "\u8fd0\u57ce\u5e02",
					subItems : [ {
						id : "203001",
						name : "\u76d0\u6e56\u533a"
					}, {
						id : "203012",
						name : "\u6cb3\u6d25\u5e02"
					}, {
						id : "203010",
						name : "\u6c38\u6d4e\u5e02"
					}, {
						id : "203004",
						name : "\u590f\u53bf"
					}, {
						id : "203013",
						name : "\u7edb\u53bf"
					}, {
						id : "203002",
						name : "\u57a3\u66f2\u53bf"
					}, {
						id : "203003",
						name : "\u4e34\u7317\u53bf"
					}, {
						id : "203005",
						name : "\u4e07\u8363\u53bf"
					}, {
						id : "203006",
						name : "\u5e73\u9646\u53bf"
					}, {
						id : "203007",
						name : "\u95fb\u559c\u53bf"
					}, {
						id : "203008",
						name : "\u82ae\u57ce\u53bf"
					}, {
						id : "203009",
						name : "\u7a37\u5c71\u53bf"
					}, {
						id : "203011",
						name : "\u65b0\u7edb\u53bf"
					} ]
				}, {
					id : "2040",
					name : "\u9633\u6cc9\u5e02",
					subItems : [ {
						id : "204001",
						name : "\u9633\u6cc9\u57ce\u533a"
					}, {
						id : "204002",
						name : "\u9633\u6cc9\u77ff\u533a"
					}, {
						id : "204003",
						name : "\u9633\u6cc9\u90ca\u533a"
					}, {
						id : "204005",
						name : "\u76c2\u53bf"
					}, {
						id : "204004",
						name : "\u5e73\u5b9a\u53bf"
					} ]
				}, {
					id : "2050",
					name : "\u957f\u6cbb\u5e02",
					subItems : [ {
						id : "205001",
						name : "\u957f\u6cbb\u57ce\u533a"
					}, {
						id : "205003",
						name : "\u957f\u6cbb\u90ca\u533a"
					}, {
						id : "205012",
						name : "\u6f5e\u57ce\u5e02"
					}, {
						id : "205008",
						name : "\u6c81\u53bf"
					}, {
						id : "205002",
						name : "\u58f6\u5173\u53bf"
					}, {
						id : "205004",
						name : "\u957f\u5b50\u53bf"
					}, {
						id : "205005",
						name : "\u957f\u6cbb\u53bf"
					}, {
						id : "205006",
						name : "\u6b66\u4e61\u53bf"
					}, {
						id : "205007",
						name : "\u8944\u57a3\u53bf"
					}, {
						id : "205009",
						name : "\u5c6f\u7559\u53bf"
					}, {
						id : "205010",
						name : "\u6c81\u6e90\u53bf"
					}, {
						id : "205011",
						name : "\u5e73\u987a\u53bf"
					}, {
						id : "205013",
						name : "\u9ece\u57ce\u53bf"
					} ]
				}, {
					id : "2060",
					name : "\u664b\u57ce\u5e02",
					subItems : [ {
						id : "206001",
						name : "\u664b\u57ce\u57ce\u533a"
					}, {
						id : "206006",
						name : "\u9ad8\u5e73\u5e02"
					}, {
						id : "206002",
						name : "\u6c81\u6c34\u53bf"
					}, {
						id : "206003",
						name : "\u9633\u57ce\u53bf"
					}, {
						id : "206004",
						name : "\u9675\u5ddd\u53bf"
					}, {
						id : "206005",
						name : "\u6cfd\u5dde\u53bf"
					} ]
				}, {
					id : "2070",
					name : "\u6714\u5dde\u5e02",
					subItems : [ {
						id : "207001",
						name : "\u6714\u57ce\u533a"
					}, {
						id : "207002",
						name : "\u5e73\u9c81\u533a"
					}, {
						id : "207003",
						name : "\u5c71\u9634\u53bf"
					}, {
						id : "207004",
						name : "\u5e94\u53bf"
					}, {
						id : "207005",
						name : "\u53f3\u7389\u53bf"
					}, {
						id : "207006",
						name : "\u6000\u4ec1\u53bf"
					} ]
				}, {
					id : "2080",
					name : "\u664b\u4e2d\u5e02",
					subItems : [ {
						id : "208001",
						name : "\u6986\u6b21\u533a"
					}, {
						id : "208008",
						name : "\u4ecb\u4f11\u5e02"
					}, {
						id : "208002",
						name : "\u7941\u53bf"
					}, {
						id : "208003",
						name : "\u6986\u793e\u53bf"
					}, {
						id : "208004",
						name : "\u5e73\u9065\u53bf"
					}, {
						id : "208005",
						name : "\u5de6\u6743\u53bf"
					}, {
						id : "208006",
						name : "\u7075\u77f3\u53bf"
					}, {
						id : "208007",
						name : "\u548c\u987a\u53bf"
					}, {
						id : "208009",
						name : "\u6614\u9633\u53bf"
					}, {
						id : "208010",
						name : "\u5bff\u9633\u53bf"
					}, {
						id : "208011",
						name : "\u592a\u8c37\u53bf"
					} ]
				}, {
					id : "2090",
					name : "\u5ffb\u5dde\u5e02",
					subItems : [ {
						id : "209001",
						name : "\u5ffb\u5e9c\u533a"
					}, {
						id : "209014",
						name : "\u539f\u5e73\u5e02"
					}, {
						id : "209007",
						name : "\u4ee3\u53bf"
					}, {
						id : "209002",
						name : "\u795e\u6c60\u53bf"
					}, {
						id : "209003",
						name : "\u5b9a\u8944\u53bf"
					}, {
						id : "209004",
						name : "\u4e94\u5be8\u53bf"
					}, {
						id : "209005",
						name : "\u4e94\u53f0\u53bf"
					}, {
						id : "209006",
						name : "\u5ca2\u5c9a\u53bf"
					}, {
						id : "209008",
						name : "\u6cb3\u66f2\u53bf"
					}, {
						id : "209009",
						name : "\u7e41\u5cd9\u53bf"
					}, {
						id : "209010",
						name : "\u4fdd\u5fb7\u53bf"
					}, {
						id : "209011",
						name : "\u5b81\u6b66\u53bf"
					}, {
						id : "209012",
						name : "\u504f\u5173\u53bf"
					}, {
						id : "209013",
						name : "\u9759\u4e50\u53bf"
					} ]
				}, {
					id : "2100",
					name : "\u5415\u6881\u5e02",
					subItems : [ {
						id : "210001",
						name : "\u79bb\u77f3\u533a"
					}, {
						id : "210012",
						name : "\u6c7e\u9633\u5e02"
					}, {
						id : "210010",
						name : "\u5b5d\u4e49\u5e02"
					}, {
						id : "210002",
						name : "\u5c9a\u53bf"
					}, {
						id : "210007",
						name : "\u5174\u53bf"
					}, {
						id : "210009",
						name : "\u4e34\u53bf"
					}, {
						id : "210003",
						name : "\u6587\u6c34\u53bf"
					}, {
						id : "210004",
						name : "\u65b9\u5c71\u53bf"
					}, {
						id : "210005",
						name : "\u4ea4\u57ce\u53bf"
					}, {
						id : "210008",
						name : "\u4ea4\u53e3\u53bf"
					}, {
						id : "210006",
						name : "\u4e2d\u9633\u53bf"
					}, {
						id : "210011",
						name : "\u67f3\u6797\u53bf"
					}, {
						id : "210013",
						name : "\u77f3\u697c\u53bf"
					} ]
				} ]
			},
			{
				id : "23000",
				name : "\u9655\u897f\u7701",
				subItems : [ {
					id : "160",
					name : "\u897f\u5b89\u5e02",
					subItems : [ {
						id : "16001",
						name : "\u672a\u592e\u533a"
					}, {
						id : "16002",
						name : "\u4e34\u6f7c\u533a"
					}, {
						id : "16003",
						name : "\u65b0\u57ce\u533a"
					}, {
						id : "16004",
						name : "\u957f\u5b89\u533a"
					}, {
						id : "16005",
						name : "\u7891\u6797\u533a"
					}, {
						id : "16007",
						name : "\u83b2\u6e56\u533a"
					}, {
						id : "16009",
						name : "\u705e\u6865\u533a"
					}, {
						id : "16011",
						name : "\u96c1\u5854\u533a"
					}, {
						id : "16013",
						name : "\u960e\u826f\u533a"
					}, {
						id : "16014",
						name : "\u6237\u53bf"
					}, {
						id : "16006",
						name : "\u84dd\u7530\u53bf"
					}, {
						id : "16008",
						name : "\u5468\u81f3\u53bf"
					}, {
						id : "16012",
						name : "\u9ad8\u9675\u53bf"
					} ]
				}, {
					id : "23010",
					name : "\u5b9d\u9e21\u5e02",
					subItems : [ {
						id : "2301001",
						name : "\u6e2d\u6ee8\u533a"
					}, {
						id : "2301003",
						name : "\u91d1\u53f0\u533a"
					}, {
						id : "2301005",
						name : "\u9648\u4ed3\u533a"
					}, {
						id : "2301008",
						name : "\u51e4\u53bf"
					}, {
						id : "2301002",
						name : "\u9647\u53bf"
					}, {
						id : "2301012",
						name : "\u7709\u53bf"
					}, {
						id : "2301004",
						name : "\u5343\u9633\u53bf"
					}, {
						id : "2301006",
						name : "\u9e9f\u6e38\u53bf"
					}, {
						id : "2301007",
						name : "\u51e4\u7fd4\u53bf"
					}, {
						id : "2301009",
						name : "\u5c90\u5c71\u53bf"
					}, {
						id : "2301010",
						name : "\u592a\u767d\u53bf"
					}, {
						id : "2301011",
						name : "\u6276\u98ce\u53bf"
					} ]
				}, {
					id : "23020",
					name : "\u54b8\u9633\u5e02",
					subItems : [ {
						id : "2302001",
						name : "\u79e6\u90fd\u533a"
					}, {
						id : "2302003",
						name : "\u6768\u9675\u533a"
					}, {
						id : "2302005",
						name : "\u6e2d\u57ce\u533a"
					}, {
						id : "2302007",
						name : "\u5174\u5e73\u5e02"
					}, {
						id : "2302006",
						name : "\u5f6c\u53bf"
					}, {
						id : "2302013",
						name : "\u4e7e\u53bf"
					}, {
						id : "2302002",
						name : "\u793c\u6cc9\u53bf"
					}, {
						id : "2302004",
						name : "\u6c38\u5bff\u53bf"
					}, {
						id : "2302008",
						name : "\u957f\u6b66\u53bf"
					}, {
						id : "2302009",
						name : "\u4e09\u539f\u53bf"
					}, {
						id : "2302010",
						name : "\u65ec\u9091\u53bf"
					}, {
						id : "2302011",
						name : "\u6cfe\u9633\u53bf"
					}, {
						id : "2302012",
						name : "\u6df3\u5316\u53bf"
					}, {
						id : "2302014",
						name : "\u6b66\u529f\u53bf"
					} ]
				}, {
					id : "23030",
					name : "\u94dc\u5ddd\u5e02",
					subItems : [ {
						id : "2303001",
						name : "\u8000\u5dde\u533a"
					}, {
						id : "2303002",
						name : "\u738b\u76ca\u533a"
					}, {
						id : "2303003",
						name : "\u5370\u53f0\u533a"
					}, {
						id : "2303004",
						name : "\u5b9c\u541b\u53bf"
					} ]
				}, {
					id : "23040",
					name : "\u6e2d\u5357\u5e02",
					subItems : [ {
						id : "2304001",
						name : "\u4e34\u6e2d\u533a"
					}, {
						id : "2304003",
						name : "\u97e9\u57ce\u5e02"
					}, {
						id : "2304005",
						name : "\u534e\u9634\u5e02"
					}, {
						id : "2304007",
						name : "\u534e\u53bf"
					}, {
						id : "2304002",
						name : "\u6f84\u57ce\u53bf"
					}, {
						id : "2304004",
						name : "\u84b2\u57ce\u53bf"
					}, {
						id : "2304006",
						name : "\u767d\u6c34\u53bf"
					}, {
						id : "2304008",
						name : "\u5bcc\u5e73\u53bf"
					}, {
						id : "2304009",
						name : "\u6f7c\u5173\u53bf"
					}, {
						id : "2304010",
						name : "\u5927\u8354\u53bf"
					}, {
						id : "2304011",
						name : "\u5408\u9633\u53bf"
					} ]
				}, {
					id : "23050",
					name : "\u5ef6\u5b89\u5e02",
					subItems : [ {
						id : "2305001",
						name : "\u5b9d\u5854\u533a"
					}, {
						id : "2305004",
						name : "\u5bcc\u53bf"
					}, {
						id : "2305002",
						name : "\u7518\u6cc9\u53bf"
					}, {
						id : "2305003",
						name : "\u5ef6\u957f\u53bf"
					}, {
						id : "2305005",
						name : "\u5ef6\u5ddd\u53bf"
					}, {
						id : "2305006",
						name : "\u6d1b\u5ddd\u53bf"
					}, {
						id : "2305008",
						name : "\u5b9c\u5ddd\u53bf"
					}, {
						id : "2305007",
						name : "\u5b50\u957f\u53bf"
					}, {
						id : "2305009",
						name : "\u5b89\u585e\u53bf"
					}, {
						id : "2305010",
						name : "\u9ec4\u9f99\u53bf"
					}, {
						id : "2305012",
						name : "\u9ec4\u9675\u53bf"
					}, {
						id : "2305011",
						name : "\u5fd7\u4e39\u53bf"
					}, {
						id : "2305013",
						name : "\u5434\u8d77\u53bf"
					} ]
				}, {
					id : "23060",
					name : "\u6c49\u4e2d\u5e02",
					subItems : [ {
						id : "2306001",
						name : "\u6c49\u53f0\u533a"
					}, {
						id : "2306007",
						name : "\u6d0b\u53bf"
					}, {
						id : "2306010",
						name : "\u52c9\u53bf"
					}, {
						id : "2306002",
						name : "\u7565\u9633\u53bf"
					}, {
						id : "2306003",
						name : "\u5357\u90d1\u53bf"
					}, {
						id : "2306004",
						name : "\u9547\u5df4\u53bf"
					}, {
						id : "2306005",
						name : "\u57ce\u56fa\u53bf"
					}, {
						id : "2306006",
						name : "\u7559\u575d\u53bf"
					}, {
						id : "2306008",
						name : "\u4f5b\u576a\u53bf"
					}, {
						id : "2306009",
						name : "\u897f\u4e61\u53bf"
					}, {
						id : "2306011",
						name : "\u5b81\u5f3a\u53bf"
					} ]
				}, {
					id : "23070",
					name : "\u6986\u6797\u5e02",
					subItems : [ {
						id : "2307001",
						name : "\u6986\u9633\u533a"
					}, {
						id : "2307004",
						name : "\u4f73\u53bf"
					}, {
						id : "2307002",
						name : "\u7c73\u8102\u53bf"
					}, {
						id : "2307003",
						name : "\u795e\u6728\u53bf"
					}, {
						id : "2307005",
						name : "\u5e9c\u8c37\u53bf"
					}, {
						id : "2307006",
						name : "\u5434\u5821\u53bf"
					}, {
						id : "2307007",
						name : "\u6a2a\u5c71\u53bf"
					}, {
						id : "2307008",
						name : "\u6e05\u6da7\u53bf"
					}, {
						id : "2307009",
						name : "\u9756\u8fb9\u53bf"
					}, {
						id : "2307010",
						name : "\u5b50\u6d32\u53bf"
					}, {
						id : "2307011",
						name : "\u5b9a\u8fb9\u53bf"
					}, {
						id : "2307012",
						name : "\u7ee5\u5fb7\u53bf"
					} ]
				}, {
					id : "23080",
					name : "\u5b89\u5eb7\u5e02",
					subItems : [ {
						id : "2308001",
						name : "\u6c49\u6ee8\u533a"
					}, {
						id : "2308002",
						name : "\u9547\u576a\u53bf"
					}, {
						id : "2308003",
						name : "\u6c49\u9634\u53bf"
					}, {
						id : "2308004",
						name : "\u65ec\u9633\u53bf"
					}, {
						id : "2308005",
						name : "\u77f3\u6cc9\u53bf"
					}, {
						id : "2308006",
						name : "\u767d\u6cb3\u53bf"
					}, {
						id : "2308007",
						name : "\u5b81\u9655\u53bf"
					}, {
						id : "2308008",
						name : "\u7d2b\u9633\u53bf"
					}, {
						id : "2308009",
						name : "\u5c9a\u768b\u53bf"
					}, {
						id : "2308010",
						name : "\u5e73\u5229\u53bf"
					} ]
				}, {
					id : "23090",
					name : "\u5546\u6d1b\u5e02",
					subItems : [ {
						id : "2309001",
						name : "\u5546\u5dde\u533a"
					}, {
						id : "2309002",
						name : "\u6d1b\u5357\u53bf"
					}, {
						id : "2309003",
						name : "\u4e39\u51e4\u53bf"
					}, {
						id : "2309004",
						name : "\u5546\u5357\u53bf"
					}, {
						id : "2309005",
						name : "\u5c71\u9633\u53bf"
					}, {
						id : "2309006",
						name : "\u9547\u5b89\u53bf"
					}, {
						id : "2309007",
						name : "\u67de\u6c34\u53bf"
					} ]
				} ]
			},
			{
				id : "19000",
				name : "\u56db\u5ddd\u7701",
				subItems : [ {
					id : "20",
					name : "\u6210\u90fd\u5e02",
					subItems : [ {
						id : "2001",
						name : "\u9526\u6c5f\u533a"
					}, {
						id : "2002",
						name : "\u65b0\u90fd\u533a"
					}, {
						id : "2005",
						name : "\u6e29\u6c5f\u533a"
					}, {
						id : "2004",
						name : "\u9752\u7f8a\u533a"
					}, {
						id : "2007",
						name : "\u91d1\u725b\u533a"
					}, {
						id : "2013",
						name : "\u6210\u534e\u533a"
					}, {
						id : "2021",
						name : "\u6b66\u4faf\u533a"
					}, {
						id : "2016",
						name : "\u9f99\u6cc9\u9a7f\u533a"
					}, {
						id : "2018",
						name : "\u9752\u767d\u6c5f\u533a"
					}, {
						id : "2011",
						name : "\u5f6d\u5dde\u5e02"
					}, {
						id : "2014",
						name : "\u909b\u5d03\u5e02"
					}, {
						id : "2017",
						name : "\u5d07\u5dde\u5e02"
					}, {
						id : "2008",
						name : "\u90fd\u6c5f\u5830\u5e02"
					}, {
						id : "2006",
						name : "\u90eb\u53bf"
					}, {
						id : "2009",
						name : "\u5927\u9091\u53bf"
					}, {
						id : "2003",
						name : "\u53cc\u6d41\u53bf"
					}, {
						id : "2012",
						name : "\u84b2\u6c5f\u53bf"
					}, {
						id : "2015",
						name : "\u65b0\u6d25\u53bf"
					}, {
						id : "2019",
						name : "\u91d1\u5802\u53bf"
					} ]
				}, {
					id : "19060",
					name : "\u4e50\u5c71\u5e02",
					subItems : [ {
						id : "1906001",
						name : "\u5e02\u4e2d\u533a"
					}, {
						id : "1906003",
						name : "\u6c99\u6e7e\u533a"
					}, {
						id : "1906007",
						name : "\u91d1\u53e3\u6cb3\u533a"
					}, {
						id : "1906005",
						name : "\u4e94\u901a\u6865\u533a"
					}, {
						id : "1906009",
						name : "\u5ce8\u7709\u5c71\u5e02"
					}, {
						id : "1906002",
						name : "\u5939\u6c5f\u53bf"
					}, {
						id : "1906004",
						name : "\u6c90\u5ddd\u53bf"
					}, {
						id : "1906006",
						name : "\u5ce8\u8fb9\u53bf"
					}, {
						id : "1906008",
						name : "\u9a6c\u8fb9\u53bf"
					}, {
						id : "1906010",
						name : "\u728d\u4e3a\u53bf"
					}, {
						id : "1906011",
						name : "\u4e95\u7814\u53bf"
					} ]
				}, {
					id : "19030",
					name : "\u6cf8\u5dde\u5e02",
					subItems : [ {
						id : "1903001",
						name : "\u6c5f\u9633\u533a"
					}, {
						id : "1903002",
						name : "\u7eb3\u6eaa\u533a"
					}, {
						id : "1903003",
						name : "\u9f99\u9a6c\u6f6d\u533a"
					}, {
						id : "1903004",
						name : "\u6cf8\u53bf"
					}, {
						id : "1903005",
						name : "\u5408\u6c5f\u53bf"
					}, {
						id : "1903006",
						name : "\u53d9\u6c38\u53bf"
					}, {
						id : "1903007",
						name : "\u53e4\u853a\u53bf"
					} ]
				}, {
					id : "19040",
					name : "\u7ef5\u9633\u5e02",
					subItems : [ {
						id : "1904001",
						name : "\u6daa\u57ce\u533a"
					}, {
						id : "1904003",
						name : "\u6e38\u4ed9\u533a"
					}, {
						id : "1904005",
						name : "\u6c5f\u6cb9\u5e02"
					}, {
						id : "1904008",
						name : "\u5b89\u53bf"
					}, {
						id : "1904002",
						name : "\u5e73\u6b66\u53bf"
					}, {
						id : "1904004",
						name : "\u5317\u5ddd\u53bf"
					}, {
						id : "1904006",
						name : "\u4e09\u53f0\u53bf"
					}, {
						id : "1904007",
						name : "\u76d0\u4ead\u53bf"
					}, {
						id : "1904009",
						name : "\u6893\u6f7c\u53bf"
					} ]
				}, {
					id : "19050",
					name : "\u5185\u6c5f\u5e02",
					subItems : [ {
						id : "1905001",
						name : "\u5e02\u4e2d\u533a"
					}, {
						id : "1905002",
						name : "\u4e1c\u5174\u533a"
					}, {
						id : "1905003",
						name : "\u5a01\u8fdc\u53bf"
					}, {
						id : "1905004",
						name : "\u8d44\u4e2d\u53bf"
					}, {
						id : "1905005",
						name : "\u9686\u660c\u53bf"
					} ]
				}, {
					id : "19070",
					name : "\u5b9c\u5bbe\u5e02",
					subItems : [ {
						id : "1907001",
						name : "\u7fe0\u5c4f\u533a"
					}, {
						id : "1907010",
						name : "\u73d9\u53bf"
					}, {
						id : "1907009",
						name : "\u9ad8\u53bf"
					}, {
						id : "1907002",
						name : "\u7b60\u8fde\u53bf"
					}, {
						id : "1907003",
						name : "\u5b9c\u5bbe\u53bf"
					}, {
						id : "1907004",
						name : "\u5174\u6587\u53bf"
					}, {
						id : "1907005",
						name : "\u5357\u6eaa\u53bf"
					}, {
						id : "1907006",
						name : "\u5c4f\u5c71\u53bf"
					}, {
						id : "1907007",
						name : "\u6c5f\u5b89\u53bf"
					}, {
						id : "1907008",
						name : "\u957f\u5b81\u53bf"
					} ]
				}, {
					id : "19020",
					name : "\u81ea\u8d21\u5e02",
					subItems : [ {
						id : "1902001",
						name : "\u81ea\u6d41\u4e95\u533a"
					}, {
						id : "1902002",
						name : "\u8d21\u4e95\u533a"
					}, {
						id : "1902003",
						name : "\u5927\u5b89\u533a"
					}, {
						id : "1902004",
						name : "\u6cbf\u6ee9\u533a"
					}, {
						id : "1902005",
						name : "\u8363\u53bf"
					}, {
						id : "1902006",
						name : "\u5bcc\u987a\u53bf"
					} ]
				}, {
					id : "19080",
					name : "\u6500\u679d\u82b1\u5e02",
					subItems : [ {
						id : "1908001",
						name : "\u4e1c\u533a"
					}, {
						id : "1908002",
						name : "\u897f\u533a"
					}, {
						id : "1908003",
						name : "\u4ec1\u548c\u533a"
					}, {
						id : "1908004",
						name : "\u7c73\u6613\u53bf"
					}, {
						id : "1908005",
						name : "\u76d0\u8fb9\u53bf"
					} ]
				}, {
					id : "19090",
					name : "\u5fb7\u9633\u5e02",
					subItems : [ {
						id : "1909001",
						name : "\u65cc\u9633\u533a"
					}, {
						id : "1909002",
						name : "\u5e7f\u6c49\u5e02"
					}, {
						id : "1909003",
						name : "\u4ec0\u90a1\u5e02"
					}, {
						id : "1909004",
						name : "\u7ef5\u7af9\u5e02"
					}, {
						id : "1909005",
						name : "\u7f57\u6c5f\u53bf"
					}, {
						id : "1909006",
						name : "\u4e2d\u6c5f\u53bf"
					} ]
				}, {
					id : "19100",
					name : "\u5e7f\u5143\u5e02",
					subItems : [ {
						id : "1910001",
						name : "\u5229\u5dde\u533a"
					}, {
						id : "1910002",
						name : "\u5143\u575d\u533a"
					}, {
						id : "1910003",
						name : "\u671d\u5929\u533a"
					}, {
						id : "1910004",
						name : "\u65fa\u82cd\u53bf"
					}, {
						id : "1910005",
						name : "\u9752\u5ddd\u53bf"
					}, {
						id : "1910006",
						name : "\u5251\u9601\u53bf"
					}, {
						id : "1910007",
						name : "\u82cd\u6eaa\u53bf"
					} ]
				}, {
					id : "19110",
					name : "\u9042\u5b81\u5e02",
					subItems : [ {
						id : "1911001",
						name : "\u8239\u5c71\u533a"
					}, {
						id : "1911002",
						name : "\u5b89\u5c45\u533a"
					}, {
						id : "1911003",
						name : "\u84ec\u6eaa\u53bf"
					}, {
						id : "1911004",
						name : "\u5c04\u6d2a\u53bf"
					}, {
						id : "1911005",
						name : "\u5927\u82f1\u53bf"
					} ]
				}, {
					id : "19120",
					name : "\u5357\u5145\u5e02",
					subItems : [ {
						id : "1912001",
						name : "\u987a\u5e86\u533a"
					}, {
						id : "1912003",
						name : "\u9ad8\u576a\u533a"
					}, {
						id : "1912005",
						name : "\u5609\u9675\u533a"
					}, {
						id : "1912006",
						name : "\u9606\u4e2d\u5e02"
					}, {
						id : "1912002",
						name : "\u4eea\u9647\u53bf"
					}, {
						id : "1912004",
						name : "\u897f\u5145\u53bf"
					}, {
						id : "1912007",
						name : "\u5357\u90e8\u53bf"
					}, {
						id : "1912008",
						name : "\u8425\u5c71\u53bf"
					}, {
						id : "1912009",
						name : "\u84ec\u5b89\u53bf"
					} ]
				}, {
					id : "19130",
					name : "\u5e7f\u5b89\u5e02",
					subItems : [ {
						id : "1913001",
						name : "\u5e7f\u5b89\u533a"
					}, {
						id : "1913002",
						name : "\u534e\u84e5\u5e02"
					}, {
						id : "1913003",
						name : "\u5cb3\u6c60\u53bf"
					}, {
						id : "1913004",
						name : "\u6b66\u80dc\u53bf"
					}, {
						id : "1913005",
						name : "\u90bb\u6c34\u53bf"
					} ]
				}, {
					id : "19140",
					name : "\u8fbe\u5dde\u5e02",
					subItems : [ {
						id : "1914001",
						name : "\u901a\u5ddd\u533a"
					}, {
						id : "1914002",
						name : "\u4e07\u6e90\u5e02"
					}, {
						id : "1914003",
						name : "\u8fbe\u53bf"
					}, {
						id : "1914007",
						name : "\u6e20\u53bf"
					}, {
						id : "1914004",
						name : "\u5ba3\u6c49\u53bf"
					}, {
						id : "1914005",
						name : "\u5f00\u6c5f\u53bf"
					}, {
						id : "1914006",
						name : "\u5927\u7af9\u53bf"
					} ]
				}, {
					id : "19150",
					name : "\u5df4\u4e2d\u5e02",
					subItems : [ {
						id : "1915001",
						name : "\u5df4\u5dde\u533a"
					}, {
						id : "1915002",
						name : "\u901a\u6c5f\u53bf"
					}, {
						id : "1915003",
						name : "\u5357\u6c5f\u53bf"
					}, {
						id : "1915004",
						name : "\u5e73\u660c\u53bf"
					} ]
				}, {
					id : "19160",
					name : "\u96c5\u5b89\u5e02",
					subItems : [ {
						id : "1916001",
						name : "\u96e8\u57ce\u533a"
					}, {
						id : "1916002",
						name : "\u5b9d\u5174\u53bf"
					}, {
						id : "1916003",
						name : "\u540d\u5c71\u53bf"
					}, {
						id : "1916004",
						name : "\u8365\u7ecf\u53bf"
					}, {
						id : "1916005",
						name : "\u6c49\u6e90\u53bf"
					}, {
						id : "1916006",
						name : "\u77f3\u68c9\u53bf"
					}, {
						id : "1916007",
						name : "\u5929\u5168\u53bf"
					}, {
						id : "1916008",
						name : "\u82a6\u5c71\u53bf"
					} ]
				}, {
					id : "19170",
					name : "\u7709\u5c71\u5e02",
					subItems : [ {
						id : "1917001",
						name : "\u4e1c\u5761\u533a"
					}, {
						id : "1917002",
						name : "\u4ec1\u5bff\u53bf"
					}, {
						id : "1917003",
						name : "\u5f6d\u5c71\u53bf"
					}, {
						id : "1917004",
						name : "\u6d2a\u96c5\u53bf"
					}, {
						id : "1917005",
						name : "\u4e39\u68f1\u53bf"
					}, {
						id : "1917006",
						name : "\u9752\u795e\u53bf"
					} ]
				}, {
					id : "19180",
					name : "\u8d44\u9633\u5e02",
					subItems : [ {
						id : "1918001",
						name : "\u96c1\u6c5f\u533a"
					}, {
						id : "1918002",
						name : "\u7b80\u9633\u5e02"
					}, {
						id : "1918003",
						name : "\u5b89\u5cb3\u53bf"
					}, {
						id : "1918004",
						name : "\u4e50\u81f3\u53bf"
					} ]
				}, {
					id : "19190",
					name : "\u963f\u575d\u5dde",
					subItems : [ {
						id : "1919005",
						name : "\u7406\u53bf"
					}, {
						id : "1919007",
						name : "\u8302\u53bf"
					}, {
						id : "1919002",
						name : "\u5c0f\u91d1\u53bf"
					}, {
						id : "1919003",
						name : "\u6c76\u5ddd\u53bf"
					}, {
						id : "1919004",
						name : "\u9ed1\u6c34\u53bf"
					}, {
						id : "1919008",
						name : "\u963f\u575d\u53bf"
					}, {
						id : "1919006",
						name : "\u58e4\u5858\u53bf"
					}, {
						id : "1919009",
						name : "\u677e\u6f58\u53bf"
					}, {
						id : "1919013",
						name : "\u91d1\u5ddd\u53bf"
					}, {
						id : "1919012",
						name : "\u7ea2\u539f\u53bf"
					}, {
						id : "1919001",
						name : "\u9a6c\u5c14\u5eb7\u53bf"
					}, {
						id : "1919010",
						name : "\u82e5\u5c14\u76d6\u53bf"
					}, {
						id : "1919011",
						name : "\u4e5d\u5be8\u6c9f\u53bf"
					} ]
				}, {
					id : "19200",
					name : "\u7518\u5b5c\u5dde",
					subItems : [ {
						id : "1920001",
						name : "\u5eb7\u5b9a\u53bf"
					}, {
						id : "1920002",
						name : "\u7518\u5b5c\u53bf"
					}, {
						id : "1920003",
						name : "\u5df4\u5858\u53bf"
					}, {
						id : "1920004",
						name : "\u6cf8\u5b9a\u53bf"
					}, {
						id : "1920005",
						name : "\u65b0\u9f99\u53bf"
					}, {
						id : "1920006",
						name : "\u4e61\u57ce\u53bf"
					}, {
						id : "1920007",
						name : "\u4e39\u5df4\u53bf"
					}, {
						id : "1920008",
						name : "\u5fb7\u683c\u53bf"
					}, {
						id : "1920009",
						name : "\u7a3b\u57ce\u53bf"
					}, {
						id : "1920010",
						name : "\u4e5d\u9f99\u53bf"
					}, {
						id : "1920011",
						name : "\u767d\u7389\u53bf"
					}, {
						id : "1920012",
						name : "\u5f97\u8363\u53bf"
					}, {
						id : "1920013",
						name : "\u96c5\u6c5f\u53bf"
					}, {
						id : "1920014",
						name : "\u77f3\u6e20\u53bf"
					}, {
						id : "1920015",
						name : "\u9053\u5b5a\u53bf"
					}, {
						id : "1920016",
						name : "\u8272\u8fbe\u53bf"
					}, {
						id : "1920017",
						name : "\u7089\u970d\u53bf"
					}, {
						id : "1920018",
						name : "\u7406\u5858\u53bf"
					} ]
				}, {
					id : "19210",
					name : "\u51c9\u5c71\u5dde",
					subItems : [ {
						id : "1921001",
						name : "\u897f\u660c\u5e02"
					}, {
						id : "1921002",
						name : "\u5e03\u62d6\u53bf"
					}, {
						id : "1921003",
						name : "\u7f8e\u59d1\u53bf"
					}, {
						id : "1921004",
						name : "\u76d0\u6e90\u53bf"
					}, {
						id : "1921005",
						name : "\u91d1\u9633\u53bf"
					}, {
						id : "1921006",
						name : "\u96f7\u6ce2\u53bf"
					}, {
						id : "1921007",
						name : "\u5fb7\u660c\u53bf"
					}, {
						id : "1921008",
						name : "\u662d\u89c9\u53bf"
					}, {
						id : "1921009",
						name : "\u6728\u91cc\u53bf"
					}, {
						id : "1921010",
						name : "\u4f1a\u7406\u53bf"
					}, {
						id : "1921011",
						name : "\u559c\u5fb7\u53bf"
					}, {
						id : "1921012",
						name : "\u4f1a\u4e1c\u53bf"
					}, {
						id : "1921013",
						name : "\u5195\u5b81\u53bf"
					}, {
						id : "1921014",
						name : "\u5b81\u5357\u53bf"
					}, {
						id : "1921015",
						name : "\u8d8a\u897f\u53bf"
					}, {
						id : "1921016",
						name : "\u666e\u683c\u53bf"
					}, {
						id : "1921017",
						name : "\u7518\u6d1b\u53bf"
					} ]
				} ]
			},
			{
				id : "22000",
				name : "\u897f\u85cf",
				subItems : [ {
					id : "90",
					name : "\u62c9\u8428\u5e02",
					subItems : [ {
						id : "9001",
						name : "\u57ce\u5173\u533a"
					}, {
						id : "9003",
						name : "\u6797\u5468\u53bf"
					}, {
						id : "9004",
						name : "\u5f53\u96c4\u53bf"
					}, {
						id : "9005",
						name : "\u5c3c\u6728\u53bf"
					}, {
						id : "9006",
						name : "\u66f2\u6c34\u53bf"
					}, {
						id : "9008",
						name : "\u8fbe\u5b5c\u53bf"
					}, {
						id : "9002",
						name : "\u58a8\u7af9\u5de5\u5361\u53bf"
					}, {
						id : "9007",
						name : "\u5806\u9f99\u5fb7\u5e86\u53bf"
					} ]
				}, {
					id : "22020",
					name : "\u65e5\u5580\u5219",
					subItems : [ {
						id : "2202001",
						name : "\u65e5\u5580\u5219\u5e02"
					}, {
						id : "2202003",
						name : "\u4e9a\u4e1c\u53bf"
					}, {
						id : "2202005",
						name : "\u767d\u6717\u53bf"
					}, {
						id : "2202006",
						name : "\u5409\u9686\u53bf"
					}, {
						id : "2202007",
						name : "\u6c5f\u5b5c\u53bf"
					}, {
						id : "2202008",
						name : "\u4ec1\u5e03\u53bf"
					}, {
						id : "2202010",
						name : "\u5b9a\u65e5\u53bf"
					}, {
						id : "2202011",
						name : "\u5eb7\u9a6c\u53bf"
					}, {
						id : "2202012",
						name : "\u8428\u560e\u53bf"
					}, {
						id : "2202013",
						name : "\u8428\u8fe6\u53bf"
					}, {
						id : "2202014",
						name : "\u5b9a\u7ed3\u53bf"
					}, {
						id : "2202015",
						name : "\u5c97\u5df4\u53bf"
					}, {
						id : "2202016",
						name : "\u62c9\u5b5c\u53bf"
					}, {
						id : "2202017",
						name : "\u4ef2\u5df4\u53bf"
					}, {
						id : "2202018",
						name : "\u6602\u4ec1\u53bf"
					}, {
						id : "2202004",
						name : "\u5357\u6728\u6797\u53bf"
					}, {
						id : "2202002",
						name : "\u8c22\u901a\u95e8\u53bf"
					}, {
						id : "2202009",
						name : "\u8042\u62c9\u6728\u53bf"
					} ]
				}, {
					id : "22030",
					name : "\u660c\u90fd",
					subItems : [ {
						id : "2203001",
						name : "\u660c\u90fd\u53bf"
					}, {
						id : "2203002",
						name : "\u5de6\u8d21\u53bf"
					}, {
						id : "2203003",
						name : "\u6c5f\u8fbe\u53bf"
					}, {
						id : "2203004",
						name : "\u8292\u5eb7\u53bf"
					}, {
						id : "2203005",
						name : "\u8d21\u89c9\u53bf"
					}, {
						id : "2203006",
						name : "\u6d1b\u9686\u53bf"
					}, {
						id : "2203008",
						name : "\u8fb9\u575d\u53bf"
					}, {
						id : "2203009",
						name : "\u4e01\u9752\u53bf"
					}, {
						id : "2203010",
						name : "\u5bdf\u96c5\u53bf"
					}, {
						id : "2203011",
						name : "\u516b\u5bbf\u53bf"
					}, {
						id : "2203007",
						name : "\u7c7b\u4e4c\u9f50\u53bf"
					} ]
				}, {
					id : "22040",
					name : "\u5c71\u5357",
					subItems : [ {
						id : "2204001",
						name : "\u4e43\u4e1c\u53bf"
					}, {
						id : "2204002",
						name : "\u6d1b\u624e\u53bf"
					}, {
						id : "2204003",
						name : "\u624e\u56ca\u53bf"
					}, {
						id : "2204004",
						name : "\u52a0\u67e5\u53bf"
					}, {
						id : "2204005",
						name : "\u8d21\u560e\u53bf"
					}, {
						id : "2204006",
						name : "\u9686\u5b50\u53bf"
					}, {
						id : "2204007",
						name : "\u6851\u65e5\u53bf"
					}, {
						id : "2204008",
						name : "\u9519\u90a3\u53bf"
					}, {
						id : "2204009",
						name : "\u743c\u7ed3\u53bf"
					}, {
						id : "2204010",
						name : "\u6d6a\u5361\u5b50\u53bf"
					}, {
						id : "2204011",
						name : "\u66f2\u677e\u53bf"
					}, {
						id : "2204012",
						name : "\u63aa\u7f8e\u53bf"
					} ]
				}, {
					id : "22050",
					name : "\u90a3\u66f2",
					subItems : [ {
						id : "2205010",
						name : "\u7d22\u53bf"
					}, {
						id : "2205001",
						name : "\u90a3\u66f2\u53bf"
					}, {
						id : "2205002",
						name : "\u73ed\u6208\u53bf"
					}, {
						id : "2205003",
						name : "\u5609\u9ece\u53bf"
					}, {
						id : "2205004",
						name : "\u5df4\u9752\u53bf"
					}, {
						id : "2205005",
						name : "\u6bd4\u5982\u53bf"
					}, {
						id : "2205006",
						name : "\u5c3c\u739b\u53bf"
					}, {
						id : "2205007",
						name : "\u8042\u8363\u53bf"
					}, {
						id : "2205008",
						name : "\u5b89\u591a\u53bf"
					}, {
						id : "2205009",
						name : "\u7533\u624e\u53bf"
					} ]
				}, {
					id : "22060",
					name : "\u963f\u91cc",
					subItems : [ {
						id : "2206001",
						name : "\u5676\u5c14\u53bf"
					}, {
						id : "2206002",
						name : "\u666e\u5170\u53bf"
					}, {
						id : "2206003",
						name : "\u672d\u8fbe\u53bf"
					}, {
						id : "2206004",
						name : "\u65e5\u571f\u53bf"
					}, {
						id : "2206005",
						name : "\u9769\u5409\u53bf"
					}, {
						id : "2206006",
						name : "\u6539\u5219\u53bf"
					}, {
						id : "2206007",
						name : "\u63aa\u52e4\u53bf"
					} ]
				}, {
					id : "22070",
					name : "\u6797\u829d",
					subItems : [ {
						id : "2207007",
						name : "\u6717\u53bf"
					}, {
						id : "2207001",
						name : "\u6797\u829d\u53bf"
					}, {
						id : "2207003",
						name : "\u7c73\u6797\u53bf"
					}, {
						id : "2207004",
						name : "\u58a8\u8131\u53bf"
					}, {
						id : "2207005",
						name : "\u6ce2\u5bc6\u53bf"
					}, {
						id : "2207006",
						name : "\u5bdf\u9685\u53bf"
					}, {
						id : "2207002",
						name : "\u5de5\u5e03\u6c5f\u8fbe\u53bf"
					} ]
				} ]
			},
			{
				id : "27000",
				name : "\u65b0\u7586",
				subItems : [ {
					id : "145",
					name : "\u4e4c\u9c81\u6728\u9f50\u5e02",
					subItems : [ {
						id : "14501",
						name : "\u5929\u5c71\u533a"
					}, {
						id : "14504",
						name : "\u65b0\u5e02\u533a"
					}, {
						id : "14508",
						name : "\u7c73\u4e1c\u533a"
					}, {
						id : "14505",
						name : "\u6c34\u78e8\u6c9f\u533a"
					}, {
						id : "14506",
						name : "\u5934\u5c6f\u6cb3\u533a"
					}, {
						id : "14507",
						name : "\u8fbe\u5742\u57ce\u533a"
					}, {
						id : "14503",
						name : "\u6c99\u4f9d\u5df4\u514b\u533a"
					}, {
						id : "14502",
						name : "\u4e4c\u9c81\u6728\u9f50\u53bf"
					} ]
				}, {
					id : "27030",
					name : "\u5580\u4ec0",
					subItems : [ {
						id : "2703001",
						name : "\u5580\u4ec0\u5e02"
					}, {
						id : "2703006",
						name : "\u4f3d\u5e08"
					}, {
						id : "2703008",
						name : "\u5df4\u695a"
					}, {
						id : "2703009",
						name : "\u6cfd\u666e"
					}, {
						id : "2703011",
						name : "\u838e\u8f66"
					}, {
						id : "2703012",
						name : "\u53f6\u57ce"
					}, {
						id : "2703002",
						name : "\u9ea6\u76d6\u63d0"
					}, {
						id : "2703003",
						name : "\u758f\u9644\u53bf"
					}, {
						id : "2703004",
						name : "\u5cb3\u666e\u6e56"
					}, {
						id : "2703005",
						name : "\u758f\u52d2\u53bf"
					}, {
						id : "2703007",
						name : "\u82f1\u5409\u6c99\u53bf"
					}, {
						id : "2703010",
						name : "\u5854\u4ec0\u5e93\u5c14\u5e72\u53bf"
					} ]
				}, {
					id : "27020",
					name : "\u514b\u62c9\u739b\u4f9d\u5e02",
					subItems : [ {
						id : "2702001",
						name : "\u514b\u62c9\u739b\u4f9d\u533a"
					}, {
						id : "2702002",
						name : "\u72ec\u5c71\u5b50\u533a"
					}, {
						id : "2702003",
						name : "\u767d\u78b1\u6ee9\u533a"
					}, {
						id : "2702004",
						name : "\u4e4c\u5c14\u79be\u533a"
					} ]
				}, {
					id : "27040",
					name : "\u4f0a\u7281",
					subItems : [ {
						id : "27050",
						name : "\u594e\u5c6f\u5e02"
					}, {
						id : "27060",
						name : "\u4f0a\u5b81\u5e02"
					}, {
						id : "2704003",
						name : "\u4f0a\u5b81\u53bf"
					}, {
						id : "2704005",
						name : "\u970d\u57ce\u53bf"
					}, {
						id : "2704006",
						name : "\u5de9\u7559\u53bf"
					}, {
						id : "2704007",
						name : "\u65b0\u6e90\u53bf"
					}, {
						id : "2704008",
						name : "\u662d\u82cf\u53bf"
					}, {
						id : "2704002",
						name : "\u5c3c\u52d2\u514b\u53bf"
					}, {
						id : "2704001",
						name : "\u7279\u514b\u65af\u53bf"
					}, {
						id : "2704004",
						name : "\u5bdf\u5e03\u67e5\u5c14\u53bf"
					} ]
				}, {
					id : "27070",
					name : "\u548c\u7530",
					subItems : [ {
						id : "2707001",
						name : "\u548c\u7530\u5e02"
					}, {
						id : "2707002",
						name : "\u6c11\u4e30\u53bf"
					}, {
						id : "2707003",
						name : "\u548c\u7530\u53bf"
					}, {
						id : "2707004",
						name : "\u58a8\u7389\u53bf"
					}, {
						id : "2707005",
						name : "\u76ae\u5c71\u53bf"
					}, {
						id : "2707006",
						name : "\u6d1b\u6d66\u53bf"
					}, {
						id : "2707007",
						name : "\u7b56\u52d2\u53bf"
					}, {
						id : "2707008",
						name : "\u4e8e\u7530\u53bf"
					} ]
				}, {
					id : "27080",
					name : "\u963f\u52d2\u6cf0",
					subItems : [ {
						id : "2708001",
						name : "\u963f\u52d2\u6cf0\u5e02"
					}, {
						id : "2708003",
						name : "\u5bcc\u8574\u53bf"
					}, {
						id : "2708004",
						name : "\u798f\u6d77\u53bf"
					}, {
						id : "2708006",
						name : "\u9752\u6cb3\u53bf"
					}, {
						id : "2708002",
						name : "\u5e03\u5c14\u6d25\u53bf"
					}, {
						id : "2708005",
						name : "\u54c8\u5df4\u6cb3\u53bf"
					}, {
						id : "2708007",
						name : "\u5409\u6728\u4e43\u53bf"
					} ]
				}, {
					id : "27170",
					name : "\u5410\u9c81\u756a",
					subItems : [ {
						id : "2717001",
						name : "\u5410\u9c81\u756a\u5e02"
					}, {
						id : "2717002",
						name : "\u912f\u5584\u53bf"
					}, {
						id : "2717003",
						name : "\u6258\u514b\u900a\u53bf"
					} ]
				}, {
					id : "27100",
					name : "\u54c8\u5bc6",
					subItems : [ {
						id : "2710001",
						name : "\u54c8\u5bc6\u5e02"
					}, {
						id : "2710002",
						name : "\u4f0a\u543e\u53bf"
					}, {
						id : "2710003",
						name : "\u5df4\u91cc\u5764\u53bf"
					} ]
				}, {
					id : "27110",
					name : "\u963f\u514b\u82cf",
					subItems : [ {
						id : "2711001",
						name : "\u963f\u514b\u82cf\u5e02"
					}, {
						id : "2711003",
						name : "\u6e29\u5bbf\u53bf"
					}, {
						id : "2711004",
						name : "\u67ef\u576a\u53bf"
					}, {
						id : "2711005",
						name : "\u5e93\u8f66\u53bf"
					}, {
						id : "2711006",
						name : "\u6c99\u96c5\u53bf"
					}, {
						id : "2711007",
						name : "\u65b0\u548c\u53bf"
					}, {
						id : "2711008",
						name : "\u62dc\u57ce\u53bf"
					}, {
						id : "2711009",
						name : "\u4e4c\u4ec0\u53bf"
					}, {
						id : "2711002",
						name : "\u963f\u74e6\u63d0\u53bf"
					} ]
				}, {
					id : "27120",
					name : "\u514b\u5b5c\u52d2\u82cf\u67ef\u5c14\u514b\u5b5c",
					subItems : [ {
						id : "2712001",
						name : "\u963f\u56fe\u4ec0\u5e02"
					}, {
						id : "2712002",
						name : "\u963f\u514b\u9676\u53bf"
					}, {
						id : "2712003",
						name : "\u963f\u5408\u5947\u53bf"
					}, {
						id : "2712004",
						name : "\u4e4c\u6070\u53bf"
					} ]
				}, {
					id : "27130",
					name : "\u5df4\u97f3\u90ed\u695e",
					subItems : [ {
						id : "27090",
						name : "\u5e93\u5c14\u52d2\u5e02"
					}, {
						id : "2713001",
						name : "\u548c\u7855\u53bf"
					}, {
						id : "2713002",
						name : "\u8f6e\u53f0\u53bf"
					}, {
						id : "2713003",
						name : "\u535a\u6e56\u53bf"
					}, {
						id : "2713004",
						name : "\u5c09\u7281\u53bf"
					}, {
						id : "2713005",
						name : "\u82e5\u7f8c\u53bf"
					}, {
						id : "2713006",
						name : "\u4e14\u672b\u53bf"
					}, {
						id : "2713007",
						name : "\u7109\u8006\u53bf"
					}, {
						id : "2713008",
						name : "\u548c\u9759\u53bf"
					} ]
				}, {
					id : "27140",
					name : "\u660c\u5409",
					subItems : [ {
						id : "2714001",
						name : "\u660c\u5409\u5e02"
					}, {
						id : "2714002",
						name : "\u961c\u5eb7\u5e02"
					}, {
						id : "2714007",
						name : "\u6728\u5792\u53bf"
					}, {
						id : "2714005",
						name : "\u5947\u53f0\u53bf"
					}, {
						id : "2714003",
						name : "\u547c\u56fe\u58c1\u53bf"
					}, {
						id : "2714004",
						name : "\u739b\u7eb3\u65af\u53bf"
					}, {
						id : "2714006",
						name : "\u5409\u6728\u8428\u5c14\u53bf"
					} ]
				}, {
					id : "27150",
					name : "\u535a\u5c14\u5854\u62c9",
					subItems : [ {
						id : "2715001",
						name : "\u535a\u4e50\u5e02"
					}, {
						id : "2715002",
						name : "\u7cbe\u6cb3\u53bf"
					}, {
						id : "2715003",
						name : "\u6e29\u6cc9\u53bf"
					} ]
				}, {
					id : "27160",
					name : "\u5854\u57ce",
					subItems : [ {
						id : "2716001",
						name : "\u5854\u57ce\u5e02"
					}, {
						id : "2716002",
						name : "\u4e4c\u82cf\u5e02"
					}, {
						id : "2716003",
						name : "\u989d\u654f\u53bf"
					}, {
						id : "2716004",
						name : "\u6c99\u6e7e\u53bf"
					}, {
						id : "2716005",
						name : "\u6258\u91cc\u53bf"
					}, {
						id : "2716006",
						name : "\u88d5\u6c11\u53bf"
					}, {
						id : "2716007",
						name : "\u548c\u5e03\u514b\u8d5b\u5c14\u53bf"
					} ]
				}, {
					id : "27180",
					name : "\u81ea\u6cbb\u533a\u76f4\u8f96",
					subItems : [ {
						id : "2718001",
						name : "\u77f3\u6cb3\u5b50\u5e02"
					}, {
						id : "2718002",
						name : "\u963f\u62c9\u5c14\u5e02"
					}, {
						id : "2718004",
						name : "\u4e94\u5bb6\u6e20\u5e02"
					}, {
						id : "2718003",
						name : "\u56fe\u6728\u8212\u514b\u5e02"
					} ]
				} ]
			},
			{
				id : "21000",
				name : "\u4e91\u5357\u7701",
				subItems : [ {
					id : "80",
					name : "\u6606\u660e\u5e02",
					subItems : [ {
						id : "8001",
						name : "\u4e94\u534e\u533a"
					}, {
						id : "8003",
						name : "\u76d8\u9f99\u533a"
					}, {
						id : "8005",
						name : "\u5b98\u6e21\u533a"
					}, {
						id : "8007",
						name : "\u897f\u5c71\u533a"
					}, {
						id : "8009",
						name : "\u4e1c\u5ddd\u533a"
					}, {
						id : "8011",
						name : "\u5b89\u5b81\u5e02"
					}, {
						id : "8002",
						name : "\u664b\u5b81\u53bf"
					}, {
						id : "8008",
						name : "\u5d69\u660e\u53bf"
					}, {
						id : "8006",
						name : "\u5b9c\u826f\u53bf"
					}, {
						id : "8004",
						name : "\u5bcc\u6c11\u53bf"
					}, {
						id : "8010",
						name : "\u77f3\u6797\u53bf"
					}, {
						id : "8012",
						name : "\u7984\u529d\u53bf"
					}, {
						id : "8013",
						name : "\u5448\u8d21\u53bf"
					}, {
						id : "8014",
						name : "\u5bfb\u7538\u53bf"
					} ]
				}, {
					id : "21030",
					name : "\u5927\u7406\u5dde",
					subItems : [ {
						id : "2103001",
						name : "\u5927\u7406\u5e02"
					}, {
						id : "2103002",
						name : "\u5251\u5ddd\u53bf"
					}, {
						id : "2103003",
						name : "\u7965\u4e91\u53bf"
					}, {
						id : "2103004",
						name : "\u9e64\u5e86\u53bf"
					}, {
						id : "2103005",
						name : "\u5bbe\u5ddd\u53bf"
					}, {
						id : "2103006",
						name : "\u6f3e\u6fde\u53bf"
					}, {
						id : "2103007",
						name : "\u5f25\u6e21\u53bf"
					}, {
						id : "2103008",
						name : "\u5357\u6da7\u53bf"
					}, {
						id : "2103009",
						name : "\u6c38\u5e73\u53bf"
					}, {
						id : "2103010",
						name : "\u5dcd\u5c71\u53bf"
					}, {
						id : "2103011",
						name : "\u4e91\u9f99\u53bf"
					}, {
						id : "2103012",
						name : "\u6d31\u6e90\u53bf"
					} ]
				}, {
					id : "21040",
					name : "\u4e3d\u6c5f\u5e02",
					subItems : [ {
						id : "2104001",
						name : "\u53e4\u57ce\u533a"
					}, {
						id : "2104002",
						name : "\u6c38\u80dc\u53bf"
					}, {
						id : "2104003",
						name : "\u534e\u576a\u53bf"
					}, {
						id : "2104004",
						name : "\u7389\u9f99\u53bf"
					}, {
						id : "2104005",
						name : "\u5b81\u8497\u53bf"
					} ]
				}, {
					id : "21020",
					name : "\u7389\u6eaa\u5e02",
					subItems : [ {
						id : "2102001",
						name : "\u7ea2\u5854\u533a"
					}, {
						id : "2102002",
						name : "\u65b0\u5e73\u53bf"
					}, {
						id : "2102003",
						name : "\u6c5f\u5ddd\u53bf"
					}, {
						id : "2102004",
						name : "\u5143\u6c5f\u53bf"
					}, {
						id : "2102005",
						name : "\u6f84\u6c5f\u53bf"
					}, {
						id : "2102006",
						name : "\u901a\u6d77\u53bf"
					}, {
						id : "2102007",
						name : "\u534e\u5b81\u53bf"
					}, {
						id : "2102008",
						name : "\u6613\u95e8\u53bf"
					}, {
						id : "2102009",
						name : "\u5ce8\u5c71\u53bf"
					} ]
				}, {
					id : "21050",
					name : "\u66f2\u9756\u5e02",
					subItems : [ {
						id : "2105001",
						name : "\u9e92\u9e9f\u533a"
					}, {
						id : "2105002",
						name : "\u4f1a\u6cfd\u53bf"
					}, {
						id : "2105003",
						name : "\u5ba3\u5a01\u5e02"
					}, {
						id : "2105004",
						name : "\u6cbe\u76ca\u53bf"
					}, {
						id : "2105005",
						name : "\u9a6c\u9f99\u53bf"
					}, {
						id : "2105006",
						name : "\u9646\u826f\u53bf"
					}, {
						id : "2105007",
						name : "\u5e08\u5b97\u53bf"
					}, {
						id : "2105008",
						name : "\u7f57\u5e73\u53bf"
					}, {
						id : "2105009",
						name : "\u5bcc\u6e90\u53bf"
					} ]
				}, {
					id : "21060",
					name : "\u4fdd\u5c71\u5e02",
					subItems : [ {
						id : "2106001",
						name : "\u9686\u9633\u533a"
					}, {
						id : "2106002",
						name : "\u65bd\u7538\u53bf"
					}, {
						id : "2106003",
						name : "\u817e\u51b2\u53bf"
					}, {
						id : "2106004",
						name : "\u9f99\u9675\u53bf"
					}, {
						id : "2106005",
						name : "\u660c\u5b81\u53bf"
					} ]
				}, {
					id : "21070",
					name : "\u662d\u901a\u5e02",
					subItems : [ {
						id : "2107001",
						name : "\u662d\u9633\u533a"
					}, {
						id : "2107002",
						name : "\u9547\u96c4\u53bf"
					}, {
						id : "2107003",
						name : "\u9c81\u7538\u53bf"
					}, {
						id : "2107004",
						name : "\u5f5d\u826f\u53bf"
					}, {
						id : "2107005",
						name : "\u5de7\u5bb6\u53bf"
					}, {
						id : "2107006",
						name : "\u5a01\u4fe1\u53bf"
					}, {
						id : "2107007",
						name : "\u76d0\u6d25\u53bf"
					}, {
						id : "2107008",
						name : "\u6c34\u5bcc\u53bf"
					}, {
						id : "2107009",
						name : "\u5927\u5173\u53bf"
					}, {
						id : "2107010",
						name : "\u6c38\u5584\u53bf"
					}, {
						id : "2107011",
						name : "\u7ee5\u6c5f\u53bf"
					} ]
				}, {
					id : "21080",
					name : "\u666e\u6d31\u5e02",
					subItems : [ {
						id : "2108001",
						name : "\u601d\u8305\u533a"
					}, {
						id : "2108002",
						name : "\u5b5f\u8fde\u53bf"
					}, {
						id : "2108003",
						name : "\u5b81\u6d31\u53bf"
					}, {
						id : "2108004",
						name : "\u6f9c\u6ca7\u53bf"
					}, {
						id : "2108005",
						name : "\u58a8\u6c5f\u53bf"
					}, {
						id : "2108006",
						name : "\u897f\u76df\u53bf"
					}, {
						id : "2108007",
						name : "\u666f\u4e1c\u53bf"
					}, {
						id : "2108008",
						name : "\u666f\u8c37\u53bf"
					}, {
						id : "2108009",
						name : "\u9547\u6c85\u53bf"
					}, {
						id : "2108010",
						name : "\u6c5f\u57ce\u53bf"
					} ]
				}, {
					id : "21090",
					name : "\u4e34\u6ca7\u5e02",
					subItems : [ {
						id : "2109001",
						name : "\u4e34\u7fd4\u533a"
					}, {
						id : "2109002",
						name : "\u6ca7\u6e90\u53bf"
					}, {
						id : "2109003",
						name : "\u51e4\u5e86\u53bf"
					}, {
						id : "2109004",
						name : "\u4e91\u53bf"
					}, {
						id : "2109005",
						name : "\u6c38\u5fb7\u53bf"
					}, {
						id : "2109006",
						name : "\u9547\u5eb7\u53bf"
					}, {
						id : "2109007",
						name : "\u53cc\u6c5f\u53bf"
					}, {
						id : "2109008",
						name : "\u803f\u9a6c\u53bf"
					} ]
				}, {
					id : "21100",
					name : "\u8fea\u5e86\u5dde",
					subItems : [ {
						id : "2110001",
						name : "\u9999\u683c\u91cc\u62c9\u53bf"
					}, {
						id : "2110002",
						name : "\u5fb7\u94a6\u53bf"
					}, {
						id : "2110003",
						name : "\u7ef4\u897f\u53bf"
					} ]
				}, {
					id : "21110",
					name : "\u6587\u5c71\u5dde",
					subItems : [ {
						id : "2111001",
						name : "\u6587\u5c71\u53bf"
					}, {
						id : "2111002",
						name : "\u5bcc\u5b81\u53bf"
					}, {
						id : "2111003",
						name : "\u781a\u5c71\u53bf"
					}, {
						id : "2111004",
						name : "\u897f\u7574\u53bf"
					}, {
						id : "2111005",
						name : "\u9ebb\u6817\u5761"
					}, {
						id : "2111006",
						name : "\u9a6c\u5173\u53bf"
					}, {
						id : "2111007",
						name : "\u4e18\u5317\u53bf"
					}, {
						id : "2111008",
						name : "\u5e7f\u5357\u53bf"
					} ]
				}, {
					id : "21120",
					name : "\u7ea2\u6cb3\u5dde",
					subItems : [ {
						id : "2112001",
						name : "\u8499\u81ea\u53bf"
					}, {
						id : "2112002",
						name : "\u6cf8\u897f\u53bf"
					}, {
						id : "2112003",
						name : "\u4e2a\u65e7\u5e02"
					}, {
						id : "2112004",
						name : "\u5143\u9633\u53bf"
					}, {
						id : "2112005",
						name : "\u5f00\u8fdc\u5e02"
					}, {
						id : "2112006",
						name : "\u7ea2\u6cb3\u53bf"
					}, {
						id : "2112007",
						name : "\u7eff\u6625\u53bf"
					}, {
						id : "2112008",
						name : "\u91d1\u5e73\u53bf"
					}, {
						id : "2112009",
						name : "\u5efa\u6c34\u53bf"
					}, {
						id : "2112010",
						name : "\u6cb3\u53e3\u53bf"
					}, {
						id : "2112011",
						name : "\u77f3\u5c4f\u53bf"
					}, {
						id : "2112012",
						name : "\u5c4f\u8fb9\u53bf"
					}, {
						id : "2112013",
						name : "\u5f25\u52d2\u53bf"
					} ]
				}, {
					id : "21130",
					name : "\u897f\u53cc\u7248\u7eb3\u5dde",
					subItems : [ {
						id : "2113001",
						name : "\u666f\u6d2a\u5e02"
					}, {
						id : "2113002",
						name : "\u52d0\u6d77\u53bf"
					}, {
						id : "2113003",
						name : "\u52d0\u814a\u53bf"
					} ]
				}, {
					id : "21140",
					name : "\u695a\u96c4\u5dde",
					subItems : [ {
						id : "2114001",
						name : "\u695a\u96c4\u5e02"
					}, {
						id : "2114002",
						name : "\u5143\u8c0b\u53bf"
					}, {
						id : "2114003",
						name : "\u53cc\u67cf\u53bf"
					}, {
						id : "2114004",
						name : "\u6b66\u5b9a\u53bf"
					}, {
						id : "2114005",
						name : "\u725f\u5b9a\u53bf"
					}, {
						id : "2114006",
						name : "\u7984\u4e30\u53bf"
					}, {
						id : "2114007",
						name : "\u5357\u534e\u53bf"
					}, {
						id : "2114008",
						name : "\u59da\u5b89\u53bf"
					}, {
						id : "2114009",
						name : "\u5927\u59da\u53bf"
					}, {
						id : "2114010",
						name : "\u6c38\u4ec1\u53bf"
					} ]
				}, {
					id : "21150",
					name : "\u5fb7\u5b8f\u5dde",
					subItems : [ {
						id : "2115001",
						name : "\u6f5e\u897f\u5e02"
					}, {
						id : "2115002",
						name : "\u745e\u4e3d\u5e02"
					}, {
						id : "2115003",
						name : "\u6881\u6cb3\u53bf"
					}, {
						id : "2115004",
						name : "\u76c8\u6c5f\u53bf"
					}, {
						id : "2115005",
						name : "\u9647\u5ddd\u53bf"
					} ]
				}, {
					id : "21160",
					name : "\u6012\u6c5f\u5dde",
					subItems : [ {
						id : "2116001",
						name : "\u6cf8\u6c34\u53bf"
					}, {
						id : "2116002",
						name : "\u798f\u8d21\u53bf"
					}, {
						id : "2116003",
						name : "\u8d21\u5c71\u53bf"
					}, {
						id : "2116004",
						name : "\u5170\u576a\u53bf"
					} ]
				} ]
			},
			{
				id : "34000",
				name : "\u9999\u6e2f",
				subItems : [ {
					id : "340001",
					name : "\u4e2d\u897f\u533a"
				}, {
					id : "340002",
					name : "\u6cb9\u5c16\u65fa\u533a"
				}, {
					id : "340003",
					name : "\u8343\u6e7e\u533a"
				}, {
					id : "340004",
					name : "\u4e1c\u533a"
				}, {
					id : "340005",
					name : "\u79bb\u5c9b\u533a"
				}, {
					id : "340006",
					name : "\u5143\u6717\u533a"
				}, {
					id : "340007",
					name : "\u4e5d\u9f99\u57ce\u533a"
				}, {
					id : "340008",
					name : "\u8475\u9752\u533a"
				}, {
					id : "340009",
					name : "\u89c2\u5858\u533a"
				}, {
					id : "340010",
					name : "\u5317\u533a"
				}, {
					id : "340011",
					name : "\u5357\u533a"
				}, {
					id : "340012",
					name : "\u897f\u8d21\u533a"
				}, {
					id : "340013",
					name : "\u6df1\u6c34\u57d7\u533a"
				}, {
					id : "340014",
					name : "\u6c99\u7530\u533a"
				}, {
					id : "340015",
					name : "\u9ec4\u5927\u4ed9\u533a"
				}, {
					id : "340016",
					name : "\u5c6f\u95e8\u533a"
				}, {
					id : "340017",
					name : "\u6e7e\u4ed4\u533a"
				}, {
					id : "340018",
					name : "\u5927\u57d4\u533a"
				} ]
			},
			{
				id : "35000",
				name : "\u6fb3\u95e8",
				subItems : [ {
					id : "190",
					name : "\u6fb3\u95e8"
				} ]
			},
			{
				id : "36000",
				name : "\u53f0\u6e7e",
				subItems : [ {
					id : "195",
					name : "\u53f0\u6e7e"
				} ]
			},
			{
				id : "37000",
				name : "\u5176\u4ed6\u4e9a\u6d32\u56fd\u5bb6\u548c\u5730\u533a",
				subItems : [ {
					id : "200",
					name : "\u5176\u4ed6\u4e9a\u6d32\u56fd\u5bb6\u548c\u5730\u533a"
				} ]
			}, {
				id : "38000",
				name : "\u5317\u7f8e\u6d32",
				subItems : [ {
					id : "205",
					name : "\u5317\u7f8e\u6d32"
				} ]
			}, {
				id : "41000",
				name : "\u5357\u7f8e\u6d32",
				subItems : [ {
					id : "230",
					name : "\u5357\u7f8e\u6d32"
				} ]
			}, {
				id : "39000",
				name : "\u5927\u6d0b\u6d32",
				subItems : [ {
					id : "210",
					name : "\u5927\u6d0b\u6d32"
				} ]
			}, {
				id : "40000",
				name : "\u6b27\u6d32",
				subItems : [ {
					id : "215",
					name : "\u6b27\u6d32"
				} ]
			}, {
				id : "42000",
				name : "\u975e\u6d32",
				subItems : [ {
					id : "235",
					name : "\u975e\u6d32"
				} ]
			} ]
}
function getInd() {
	return [
			{
				id : "100",
				name : "\u8ba1\u7b97\u673a",
				enname : "Computer"
			},
			{
				id : "3600",
				name : "\u4e92\u8054\u7f51\u2022\u7535\u5b50\u5546\u52a1",
				enname : "Internet"
			},
			{
				id : "1123000",
				name : "\u7f51\u7edc\u6e38\u620f",
				enname : "Online Game"
			},
			{
				id : "300",
				name : "\u7535\u5b50\u2022\u5fae\u7535\u5b50",
				enname : "Electronics"
			},
			{
				id : "400",
				name : "\u901a\u4fe1(\u8bbe\u5907\u2022\u8fd0\u8425\u2022\u589e\u503c\u670d\u52a1)",
				enname : "Telecommunication"
			},
			{
				id : "800",
				name : "\u5e7f\u544a\u2022\u4f1a\u5c55\u2022\u516c\u5173",
				enname : "advertising"
			},
			{
				id : "1100",
				name : "\u623f\u5730\u4ea7\u5f00\u53d1\u2022\u5efa\u7b51\u4e0e\u5de5\u7a0b",
				enname : "Real Estate construction"
			},
			{
				id : "3700",
				name : "\u7269\u4e1a\u7ba1\u7406\u2022\u5546\u4e1a\u4e2d\u5fc3",
				enname : "Property Management"
			},
			{
				id : "1500",
				name : "\u5bb6\u5c45\u2022\u5ba4\u5185\u8bbe\u8ba1\u2022\u88c5\u6f62",
				enname : "fitment"
			},
			{
				id : "2800",
				name : "\u4e2d\u4ecb\u670d\u52a1",
				enname : "Service Agency"
			},
			{
				id : "900",
				name : "\u4e13\u4e1a\u670d\u52a1(\u54a8\u8be2\u2022\u8d22\u4f1a\u2022\u6cd5\u5f8b\u7b49)",
				enname : "Professional Service"
			},
			{
				id : "1124000",
				name : "\u68c0\u9a8c\u2022\u68c0\u6d4b\u2022\u8ba4\u8bc1",
				enname : "testing Certification"
			},
			{
				id : "1000",
				name : "\u91d1\u878d\u4e1a(\u6295\u8d44\u2022\u4fdd\u9669\u2022\u8bc1\u5238\u2022\u94f6\u884c\u2022\u57fa\u91d1)",
				enname : "Finance"
			},
			{
				id : "700",
				name : "\u8d38\u6613\u2022\u8fdb\u51fa\u53e3",
				enname : "trading"
			},
			{
				id : "1118000",
				name : "\u5a92\u4f53\u2022\u51fa\u7248\u2022\u6587\u5316\u4f20\u64ad",
				enname : "Media Publishing"
			},
			{
				id : "2600",
				name : "\u5370\u5237\u2022\u5305\u88c5\u2022\u9020\u7eb8",
				enname : "Printing packaging"
			},
			{
				id : "1300",
				name : "\u5feb\u901f\u6d88\u8d39\u54c1(\u98df\u54c1\u2022\u996e\u6599\u2022\u65e5\u5316\u2022\u70df\u9152\u7b49)",
				enname : "fast Consumer"
			},
			{
				id : "1113000",
				name : "\u8010\u7528\u6d88\u8d39\u54c1(\u670d\u9970\u2022\u7eba\u7ec7\u2022\u76ae\u9769\u2022\u5bb6\u5177)",
				enname : "Consumer Durables"
			},
			{
				id : "1125000",
				name : "\u73a9\u5177\u2022\u5de5\u827a\u54c1\u2022\u6536\u85cf\u54c1\u2022\u5962\u4f88\u54c1",
				enname : "Toys Artworks Collections"
			},
			{
				id : "1400",
				name : "\u5bb6\u7535\u4e1a",
				enname : "Domestic Appliances"
			},
			{
				id : "1800",
				name : "\u529e\u516c\u8bbe\u5907\u2022\u7528\u54c1",
				enname : "Office euqipment"
			},
			{
				id : "1600",
				name : "\u65c5\u6e38\u2022\u9152\u5e97\u2022\u9910\u996e\u670d\u52a1",
				enname : "catering"
			},
			{
				id : "600",
				name : "\u6279\u53d1\u2022\u96f6\u552e",
				enname : "Wholesale Retail"
			},
			{
				id : "1700",
				name : "\u4ea4\u901a\u2022\u8fd0\u8f93\u2022\u7269\u6d41",
				enname : "Logistics"
			},
			{
				id : "1200",
				name : "\u5a31\u4e50\u2022\u8fd0\u52a8\u2022\u4f11\u95f2",
				enname : "Entertainment"
			},
			{
				id : "2300",
				name : "\u5236\u836f\u2022\u751f\u7269\u5de5\u7a0b",
				enname : "Pharmaceuticals Biotechnology"
			},
			{
				id : "3200",
				name : "\u533b\u7597\u2022\u4fdd\u5065\u2022\u7f8e\u5bb9\u2022\u536b\u751f\u670d\u52a1",
				enname : "Medical service"
			},
			{
				id : "2000",
				name : "\u533b\u7597\u8bbe\u5907\u2022\u5668\u68b0",
				enname : "Medical Equipment"
			},
			{
				id : "2400",
				name : "\u73af\u4fdd",
				enname : "Environment protection"
			},
			{
				id : "2200",
				name : "\u77f3\u6cb9\u2022\u5316\u5de5\u2022\u77ff\u4ea7\u2022\u91c7\u6398\u2022\u51b6\u70bc\u2022\u539f\u6750\u6599",
				enname : "Petroleum Mining Raw Meterial"
			},
			{
				id : "1122000",
				name : "\u80fd\u6e90(\u7535\u529b\u2022\u77f3\u6cb9)\u2022\u6c34\u5229",
				enname : "Energy"
			},
			{
				id : "3900",
				name : "\u4eea\u5668\u2022\u4eea\u8868\u2022\u5de5\u4e1a\u81ea\u52a8\u5316\u2022\u7535\u6c14",
				enname : "Instrument Automation Electicity"
			},
			{
				id : "2100",
				name : "\u6c7d\u8f66\u2022\u6469\u6258\u8f66(\u5236\u9020\u2022\u7ef4\u62a4\u2022\u914d\u4ef6\u2022\u9500\u552e\u2022\u670d\u52a1)",
				enname : "Automobile"
			},
			{
				id : "1114000",
				name : "\u673a\u68b0\u5236\u9020\u2022\u673a\u7535\u2022\u91cd\u5de5",
				enname : "Heavy industry"
			},
			{
				id : "1115000",
				name : "\u539f\u6750\u6599\u53ca\u52a0\u5de5(\u91d1\u5c5e\u2022\u6728\u6750\u2022\u6a61\u80f6\u2022\u5851\u6599\u2022\u73bb\u7483\u2022\u9676\u74f7\u2022\u5efa\u6750)",
				enname : "Raw Material and processing"
			},
			{
				id : "2900",
				name : "\u519c\u2022\u6797\u2022\u7267\u2022\u6e14",
				enname : "Agriculture"
			},
			{
				id : "1105000",
				name : "\u822a\u7a7a\u2022\u822a\u5929\u7814\u7a76\u4e0e\u5236\u9020",
				enname : "Aeronautics"
			},
			{
				id : "1126000",
				name : "\u8239\u8236\u5236\u9020",
				enname : "Shipbuilding"
			},
			{
				id : "3100",
				name : "\u6559\u80b2\u2022\u57f9\u8bad\u2022\u79d1\u7814\u2022\u9662\u6821",
				enname : "Education Training"
			}, {
				id : "1116000",
				name : "\u653f\u5e9c\u2022\u975e\u8425\u5229\u673a\u6784",
				enname : "Government Nonprofit Organization"
			}, {
				id : "1121000",
				name : "\u5176\u4ed6",
				enname : "Others"
			} ]
}jQuery.extend(Array.prototype,{remove:function(c){var b=this.clone();this.length=0;for(var a=0;a<b.length;a++)b[a].id!=c&&this.push(b[a]);return this},has:function(b){for(var a=0;a<this.length;a++){if(this[a]==b)return true;if(typeof b=="object"&&this[a].id==b.id)return true}return false},GetFather:function(b){for(var a=0;a<this.length;a++){if(this[a].parObj==null)continue;if(this[a].parObj.id==b.id)return true;if(typeof b=="object"&&this[a].id==b.id)return true}return false},parItems:getLoc(),GetFaFather:function(c){for(var a=0;a<this.length;a++){if(this[a].parObj==null)continue;if(this[a].parObj.parObj==null)continue;var b=this.parItems[parseInt(this[a].parObj.parObj)];if(b!=null){if(b.id==c.id)return true}else if(typeof this[a].parObj.parObj=="object"&&this[a].parObj.parObj.id==c.id)return true}return false},findById:function(d){for(var a=0;a<this.length;a++){if(this[a].id==d){this[a].parObj=null;return this[a]}if(this[a].subItems!=null&&this[a].subItems!="undefined")for(var b=0;b<this[a].subItems.length;b++){if(this[a].subItems[b].id==d){this[a].subItems[b].parObj=this[a];return this[a].subItems[b]}if(this[a].subItems[b].subItems!=null&&this[a].subItems[b].subItems!="undefined")for(var c=0;c<this[a].subItems[b].subItems.length;c++)if(this[a].subItems[b].subItems[c].id==d){this[a].subItems[b].subItems[c].parObj=this[a].subItems[b];this[a].subItems[b].subItems[c].parObj.parObj=this[a];return this[a].subItems[b].subItems[c]}}}return null},findById:function(d,e){for(var a=0;a<this.length;a++){if(this[a].id==d){this[a].parObj=null;return this[a]}if(e)continue;if(this[a].subItems!=null&&this[a].subItems!="undefined")for(var b=0;b<this[a].subItems.length;b++){if(this[a].subItems[b].id==d){this[a].subItems[b].parObj=this[a];return this[a].subItems[b]}if(this[a].subItems[b].subItems!=null&&this[a].subItems[b].subItems!="undefined")for(var c=0;c<this[a].subItems[b].subItems.length;c++)if(this[a].subItems[b].subItems[c].id==d){this[a].subItems[b].subItems[c].parObj=this[a].subItems[b];this[a].subItems[b].subItems[c].parObj.parObj=this[a];return this[a].subItems[b].subItems[c]}}}return null},clear:function(){this.length=0;return this},clone:function(){return[].concat(this)}});var requestFromStr={QueryString:function(a,c){var b=a.substring(0,1)=="?"?a:"?"+a,d=new RegExp("[&?]"+c+"=([^&?]*)","ig");return b.match(d)?b.match(d)[0].substr(c.length+2):""}};function locConverter(d){for(var b=d.split(","),c=[],a=0;a<b.length;a++)c.push(ConverterLoc(b[a]));return c}function traversal(e,c){var a=c.findById(e);if(a!=null)return{id:a.id,name:a.name,parObj:null};else for(var f,b,d=0;d<c.length;d++){a=c[d];b=a.subItems.findById(e);if(b!=null)return{id:b.id,name:b.name,parObj:{id:a.id,name:a.name,parObj:null}}}return null}function dbcToSbc(a){return a.replace(/\uff08/g,"(").replace(/\uff09/g,")")}function addSpan(a){a=dbcToSbc(a);if(a.length>9)a="<span>"+a+"</span>";return a}var PopupSelector={loadSelected:function(k){var b=[],f=requestFromStr.QueryString(k,"occParentIDList");if(f!="")if(f.indexOf(",")>0)f=f.split(",");else{f=f.replace(/%2C/g,"%2c");f=f.split("%2c")}var d=requestFromStr.QueryString(k,"occIDList");if(d.indexOf(",")>0)d=d.split(",");else{d=d.replace(/%2C/g,"%2c");d=d.split("%2c")}var p=requestFromStr.QueryString(k,"addOccIDList");if(p!=""){d.clear();d[0]=p}this._selItems.cat.clear();var t=getCat();if(p!="")for(var l,a=0;a<d.length;a++){l=traversal(d[a],t);if(l!=null){this._selItems.cat.push(l);b.push(l.name)}}if(p==""&&f==""){for(var x=[],a=0;a<d.length;a++){var s=t.findById(d[a]);if(s!=null)if(s.parObj==null)x.push(s.id);else x.push(s.parObj.id)}f=x}if(d.length>0&&d[0]!=""&&f!=""&&p=="")for(var a=0;a<f.length;a++)if(f[a]==d[a]){if(d[a]!=255){var l=t.findById(d[a]);this._selItems.cat.push({id:l.id,name:l.name,parObj:null});b.push(l.name)}}else{var y=t.findById(f[a]),z=y.subItems.findById(d[a]);this._selItems.cat.push({id:z.id,name:z.name,parObj:{id:y.id,name:y.name,parObj:null}});b.push(z.name)}jQuery("#txtCat").val(b.join("+"));this.InitClass(b,"cat");b.length=0;var c=requestFromStr.QueryString(k,"myLocIDList");if(c.indexOf(",")>0)c=c.split(",");else{c=c.replace(/%2C/g,"%2c");c=c.split("%2c")}var e=requestFromStr.QueryString(k,"myLocParentIDList");if(e!="")if(e.indexOf(",")>0)e=e.split(",");else{e=e.replace(/%2C/g,"%2c");e=e.split("%2c")}var r=requestFromStr.QueryString(k,"addLocIDList");if(r!=""){c.clear();c[0]=r}if(e==""&&c!=""){for(var w=[],a=0;a<c.length;a++){var B=this.allItems.loc().findById(c[a]);if(B.parObj==null)w.push(B.id);else w.push(B.parObj.id)}e=w}this._selItems.loc.clear();var u=getLoc();if(r!=""||e=="")for(var g,a=0;a<c.length;a++){g=u.findById(c[a]);if(g!=null){this._selItems.loc.push(g);if(g.parObj!=null)if(g.parObj.parObj!=null)b.push(g.parObj.parObj.name+"-"+g.parObj.name+"-"+g.name);else b.push(g.parObj.name+"-"+g.name);else b.push(g.name)}}if(c.length>0&&c[0]!=""&&e!=""&&r=="")for(var a=0;a<e.length;a++)if(e[a]==c[a]||e[a]==-1){if(c[a]!=255){var g=u.findById(c[a]);this._selItems.loc.push({id:g.id,name:g.name,parObj:null});b.push(g.name)}}else{var i=u.findById(e[a]);if(i!=null){var n=i.subItems.findById(c[a]),F=this.allItems.loc(),A="",i=u.findById(e[a]);if(i!=null&&i.parObj!=null){var m=F[parseInt(i.parObj)];if(m=="undefined"||m==null)m=i.parObj;if(m.name==i.parObj.name){A=m.name+"-"+i.name+"-"+n.name;this._selItems.loc.push({id:n.id,name:n.name,parObj:{id:i.id,name:i.name,parObj:{id:m.id,name:m.name,parObj:null}}})}}else{A=i.name+"-"+n.name;this._selItems.loc.push({id:n.id,name:n.name,parObj:{id:i.id,name:i.name,parObj:null}})}b.push(A)}}jQuery("#txtLoc").val(b.join("+"));this.InitClass(b,"loc");b.length=0;var j=requestFromStr.QueryString(k,"indIDList");if(j.indexOf(",")>0)j=j.split(",");else{j=j.replace(/%2C/g,"%2c");j=j.split("%2c")}var E=requestFromStr.QueryString(k,"addIndIDList"),C=getInd(),o=C.findById(E);this._selItems.ind.clear();if(o!=null){this._selItems.ind.push({id:o.id,name:o.name,parObj:null});b.push(o.name)}if(j.length>0&&j[0]!=""&&o==null)for(var a=0;a<j.length;a++)if(j[a]!=255){var v=C.findById(j[a]);if(v==null)continue;this._selItems.ind.push({id:v.id,name:v.name,parObj:null});b.push(v.name)}jQuery("#txtInd").val(b.join("+"));this.InitClass(b,"ind");b.length=0;if(jQuery("#txtAdvanceCompType").length>0){var h=requestFromStr.QueryString(k,"companyTypeIDList");if(h!="")if(h.indexOf(",")>0)h=h.split(",");else{h=h.replace(/%2C/g,"%2c");h=h.split("%2c")}var D=getCompanyType();if(h.length>0&&h[0]!="")for(var a=0;a<h.length;a++)if(h[a]!=255){var q=D.findById(h[a]);if(q==null)continue;this._selItems.comptype.push({id:q.id,name:q.name,parObj:null});b.push(q.name)}jQuery("#txtAdvanceCompType").val(b.join("+"));b.length=0}},RenderSelectedHTML:function(){var b=""},InitClass:function(b,a){if(b.length!=0){if(a=="cat"){$("#txtCat").removeClass("inp_txt inp_txtsel inp_wl inp_cue gray");$("#txtCat").addClass("inp_txt inp_txtsel inp_wl")}if(a=="loc"){$("#txtLoc").removeClass("inp_txt inp_txtsel inp_wm inp_cue gray");$("#txtLoc").addClass("inp_txt inp_txtsel inp_wm")}if(a=="ind"){$("#txtInd").removeClass("inp_txt inp_txtsel inp_wm inp_cue gray");$("#txtInd").addClass("inp_txt inp_txtsel inp_wm")}}else{if(a=="cat"){$("#txtCat").removeClass("inp_txt inp_txtsel inp_wl");$("#txtCat").addClass("inp_txt inp_txtsel inp_wl inp_cue gray");$("#txtCat").val("\u8bf7\u9009\u62e9\u804c\u4e1a\u7c7b\u522b")}if(a=="loc"){$("#txtLoc").removeClass("inp_txt inp_txtsel inp_wm");$("#txtLoc").addClass("inp_txt inp_txtsel inp_wm inp_cue gray");$("#txtLoc").val("\u8bf7\u9009\u62e9\u5de5\u4f5c\u5730\u70b9")}if(a=="ind"){$("#txtInd").removeClass("inp_txt inp_txtsel inp_wm");$("#txtInd").addClass("inp_txt inp_txtsel inp_wm inp_cue gray");$("#txtInd").val("\u8bf7\u9009\u62e9\u884c\u4e1a\u7c7b\u522b")}}},popup:function(g,a,c,f,d){typeof Mask!="undefined"&&Mask!=null&&Mask.Display();this.AarearenderSelItemEleId=c;this.AareatagPrefix=f;this.AarearenderSelItemDiv=a;var e;if(this._box==null)this._box=jQuery("#popupSelector");if(this._subBox==null)this._subBox=document.getElementById("subItems");this._ref=a;this._type=g.toString().toLowerCase();if(this.AarearenderSelItemEleId!=null)e=document.getElementById(this.AarearenderSelItemEleId).value.split(",");var h=getLoc();this._curItems.length=0;this._curItems=this._selItems[this._type].clone();if(arguments.length==3)this._checkType=d;var b=jQuery("#"+a.id).offset();this._style.left=b.left;this._style.top=b.top;this.render()},render:function(){var e={top:this._style.top+this._style.offset.levelOne.Y,left:this._style.left+this._style.offset.levelOne.X};if(this._type!="cat"&&this._type!="comptype")if(this._type=="ind")e.left=jQuery(jQuery("#txtCat")[0]).offset().left;else if(this._type=="loc")if(this.InSalaryPage)e.left=this._style.left;else if(this.InAdvanceSearchPage)e.left=this._style.left;else e.left=this._style.left-this._style.offset.levelOne[this._type].X;var g;if(jQuery.browser.msie&&jQuery.browser.version=="6.0")g=document.body.scrollTop;else g=document.documentElement.scrollTop;var l=jQuery(window).height()-e.top+g;if(l<this._style.height[this._type])e.top=e.top-(this._style.height[this._type]-l);if(this._style.height[this._type]>jQuery(window).height())e.top=g;for(var m=jQuery("#pslayer"),b=0;b<this._types.length;b++)m.removeClass(this._style.className.levelOne[this._types[b]]);m.addClass(this._style.className.levelOne[this._type]);if(this.InSalaryPage)this._gtYourSelected.ind="\u8bf7\u9009\u62e9\u4f60\u7684\u884c\u4e1a\u7c7b\u522b\uff1a";jQuery("#selectingHeader").html(this._gtYourSelected[this._type]);jQuery("#psHeader").html(this._gtPopupSelectorHeader[this._type]);var a=[],o,d=this.allItems[this._type](),n=0,i=d.length;if(this._type=="loc"){n=0;i=34;jQuery("#subHeader1").html("<span>\u6240\u6709\u7701\u5e02\uff1a</span>");jQuery("#subHeader2").html("<span>\u5176\u5b83\u56fd\u5bb6\u548c\u5730\u533a\uff1a</span>").show();for(var b=i;b<d.length;b++){if(this._curItems.has({id:d[b].id,name:d[b].name,parObj:null}))a.push("<li id=jQuery"+b+" name="+d[b].id+' class="layon"><a href="javascript:void(0);">');else a.push("<li id=jQuery"+b+" name="+d[b].id+' class="nonelay"><a href="javascript:void(0);">');a.push('<label for="pcbx');a.push(d[b].id);a.push('">');a.push('<input id="pcbx');a.push(d[b].id);a.push('" type="'+PopupSelector._checkType+'" value="');a.push(d[b].id);a.push("@");a.push(d[b].name);a.push('"');this._curItems.has({id:d[b].id,name:d[b].name,parObj:null})&&a.push(" checked");a.push(' onclick="PopupSelector.click('+b+",this, null,"+d[b].id+')" />');a.push(d[b].name);a.push("</label>");a.push("</a></li>")}jQuery("#subHeader1").show();jQuery("#subHeader2").show();jQuery("#allItems2").html(a.join("")).show()}else{jQuery("#subHeader1").hide();jQuery("#subHeader2").hide();jQuery("#allItems2").hide()}a=[];for(var h=false,j=0;j<this._curItems.length;j++)if(this._curItems[j].id=="999999")h=true;for(var b=n;b<i;b++){var c=d[b];if(c.subItems&&c.subItems.length>1){if(this._curItems.has({id:c.id,name:c.name,parObj:null})||this._curItems.GetFather({id:c.id,name:c.name,parObj:null})||this._curItems.GetFaFather({id:c.id,name:c.name,parObj:null}))a.push("<li id=jQuery"+b+" name="+c.id+' class="layicon" onmouseover="PopupSelector.showSubItems(');else a.push("<li id=jQuery"+b+" name="+c.id+' onmouseover="PopupSelector.showSubItems(');a.push(b);a.push(","+c.id+', this, true)" onmouseout="PopupSelector.hideSubItems(this)">');a.push('<a href="javascript:void(0);">');a.push('<input id="pcbx');a.push(c.id);a.push('" type="'+PopupSelector._checkType+'" value="');a.push(c.id);a.push("@");a.push(c.name);a.push('"');if(h){c.id!="999999"&&a.push(' disabled="true"');a.push(" checked")}else this._curItems.has({id:c.id,name:c.name,parObj:null})&&a.push(" checked");a.push(' onclick="PopupSelector.click(');a.push(b+",this, null,"+c.id+')" />');a.push(dbcToSbc(c.name));a.push("</a></li>")}else{if(this._curItems.has({id:c.id,name:c.name,parObj:null}))a.push("<li id=jQuery"+b+" name="+c.id+' class="layon">');else a.push("<li id=jQuery"+b+" name="+c.id+' class="nonelay">');a.push('<a href="javascript:void(0);">');a.push('<label for="pcbx');a.push(d[b].id);a.push('">');a.push('<input id="pcbx');a.push(c.id);a.push('" type="'+PopupSelector._checkType+'" value="');a.push(c.id);a.push("@");a.push(c.name);a.push('"');if(h){c.id!="999999"&&a.push(' disabled="true"');a.push(" checked")}else this._curItems.has({id:c.id,name:c.name,parObj:null})&&a.push(" checked");a.push(' onclick="PopupSelector.click(');a.push(b+",this, null,"+c.id+')" />');a.push(dbcToSbc(c.name));a.push("</label>");a.push("</a></li>")}}jQuery("#allItems1").html(a.join("").toString());a.length=0;jQuery("#divSelecting").css("display",this._curItems.length==0?"none":"block");var k=this._curItems.length==0?"block":"none";if(this._type=="cat"){jQuery("#noSelectedCat").css("display",k);jQuery("#noSelectedLoc").hide()}else if(this._type=="loc"){jQuery("#noSelectedLoc").css("display",k);jQuery("#noSelectedCat").hide()}else{jQuery("#noSelectedCat").hide();jQuery("#noSelectedLoc").hide()}for(var b=0;b<this._curItems.length;b++){a.push('<li id="');a.push(this._curItems[b].id);a.push('"><a href="###" onclick="PopupSelector.remove(');a.push(this._curItems[b].id);if(this._curItems[b].parObj!=null)if(this._curItems[b].parObj.parObj!=null)a.push(","+this._curItems[b].parObj.parObj.id+')">');else a.push(","+this._curItems[b].parObj.id+')">');else a.push(')">');var f;if(this._curItems[b].parObj&&this._type=="loc")if(this._curItems[b].parObj.parObj!=null){c=d[parseInt(this._curItems[b].parObj.parObj)];if(c=="undefined"||c==null)c=this._curItems[b].parObj.parObj;if(c.name==this._curItems[b].parObj.name)f=this._curItems[b].parObj.name+"-"+this._curItems[b].name;else f=c.name+"-"+this._curItems[b].parObj.name+"-"+this._curItems[b].name}else f=this._curItems[b].parObj.name+"-"+this._curItems[b].name;else f=this._curItems[b].name;a.push(dbcToSbc(f));a.push("</a></li>")}jQuery("#selecting").html(a.join(""));if(this._type=="ind"||this._type=="comptype"){jQuery("#divSelecting").show();jQuery("#selecting").hide()}else jQuery("#selecting").show();jQuery("#shield").width(this._style.width[this._type]).height(this._style.height[this._type]);jQuery(this._box).css(e).show();jQuery("#mask").height(jQuery(document).height()).show();$("#mask iframe").height(jQuery(document).height());$("#mask iframe").width(jQuery(document).width());jQuery("#pcbx30000").focus()},close:function(){jQuery("#mask").hide();this._ref=null;jQuery(this._box).hide();typeof Mask!="undefined"&&Mask!=null&&Mask.Hide()},OK:function(){var a=[],h=popupLayer.selectedItems,d=this.AarearenderSelItemDiv,g=this.AarearenderSelItemEleId,e=this.AareatagPrefix,f=[],c=[],b=this;jQuery.each(this._curItems,function(){var h;if(this.parObj&&b._type=="loc"){var i=b.allItems[b._type]();if(this.parObj.parObj!=null){parItem=i[parseInt(this.parObj.parObj)];if(parItem=="undefined"||parItem==null)parItem=this.parObj.parObj;if(parItem.name==this.parObj.name)h=this.parObj.name+"-"+this.name;else h=parItem.name+"-"+this.parObj.name+"-"+this.name}else h=this.parObj.name+"-"+this.name}else h=this.name;c.push(h);a.push('<div class="list_m" id="'+e+"_divSelItem_"+this.id+'">');a.push('<div class="list_c">');a.push(h);a.push("</div>");a.push('<input type="button" value="\u5220\u9664" title="\u5220\u9664"');a.push("onclick=\"pLayer.remove('"+e+"_divSelItem_"+this.id+"','"+g+"','"+d+"')\" />");a.push("</div>");f.push(this.id)});$(d).innerHTML=a.join("");$("#renderSelIds").val(f.join(","));if(this.InSalaryPage)if(this._curItems){this._type=="loc"&&this._curItems[0]!=null&&jQuery("#hidLoc").val(this._curItems[0].id);this._type=="ind"&&this._curItems[0]!=null&&jQuery("#hidInd").val(this._curItems[0].id);this._type=="cat"&&this._curItems[0]!=null&&jQuery("#hidCat").val(this._curItems[0].id)}jQuery(this._ref).val(dbcToSbc(c.join("+")));this.InitClass(c,this._type);this._ref=null;this._selItems[this._type]=this._curItems.clone();this._curItems.clear();jQuery("#selecting").html("");jQuery("#mask").hide();jQuery(this._box).hide();typeof Mask!="undefined"&&Mask!=null&&Mask.Hide()},empty:function(){jQuery("#selecting").html("");jQuery("#allItems1 input").each(function(){this.checked=false;this.disabled=false});jQuery("#allItems2 input").each(function(){this.checked=false});jQuery("#allItems1 li").each(function(){if(this.className=="layicon")this.className="";if(this.className=="layon")this.className="nonelay"});jQuery("#allItems2 li").each(function(){if(this.className=="layicon")this.className="";if(this.className=="layon")this.className="nonelay"});this._curItems.clear();PopupSelector.showtips()},InSalaryPage:false,InAdvanceSearchPage:false,click:function(c,e,b,o,j){if(e.checked&&this._curItems.length==this._maxSize){alert(this._gtMaxLimit);e.checked=false;return}var d={id:e.value.split("@")[0],name:e.value.split("@")[1],parObj:b},i=document.getElementById("selecting"),f=null;if(this.InSalaryPage){jQuery(".sech_layb input[type='radio']").each(function(){this.checked=false});jQuery("#subBox input[type='radio']").each(function(){this.checked=false});if(this._type!="ind"){var u=this._type;jQuery(".layon").each(function(){if(u=="loc")if(this.id=="jQuery1"||this.id=="jQuery2"||this.id=="jQuery3"||this.id=="jQuery4"||this.id=="jQuery32"||this.id=="jQuery33"||this.id=="jQuery34"||this.id=="jQuery35"||this.id=="jQuery36"||this.id=="jQuery37"||this.id=="jQuery38"||this.id=="jQuery39"||this.id=="jQuery40")this.className="nonelay";else this.className="";else if(this.id=="jQuery29"||this.id=="jQuery31")this.className="nonelay";else this.className=""})}else jQuery(".layon").each(function(){this.className="nonelay"});jQuery(".layicon").each(function(){this.className=""});e.checked=true;f='<a href="javascript:void(0);" onclick="PopupSelector.remove('+d.id+',null);">'+dbcToSbc(d.name)+"</a>";if(b!=null)f='<a href="javascript:void(0);" onclick="PopupSelector.remove('+d.id+","+b.id+');">'+dbcToSbc(d.name)+"</a>";jQuery(i).html("<li id='"+o+"'>"+f+"</li>");if(b!=null){document.getElementById("^"+c).className="layon";var v=b.id;jQuery("li[@name="+v+"]").addClass("layicon")}else document.getElementById("jQuery"+c).className="layon";this._curItems.clear();this._curItems.push(d);this.showHideSelecting(this);PopupSelector.showtips();return}if(e.checked){if(o!="999999"){f=document.createElement("li");f.id=d.id;if(b==null){var a=this.allItems[this._type]()[c],g=this;jQuery(g._curItems).each(function(){var d=this.id;itemSubstring=document.getElementById(d);if(itemSubstring!=null){var c=false;if(a.subItems&&a.subItems.length>0)for(var b=0;b<a.subItems.length;b++){if(a.subItems[b].id==d)c=true;a.subItems[b].subItems&&a.subItems[b].subItems.length>0&&jQuery(a.subItems[b].subItems).each(function(){if(this.id==d)c=true})}if(c==true){i.removeChild(itemSubstring);g._curItems.remove(this.id)}}});f.innerHTML='<a href="javascript:void(0);" onclick="PopupSelector.remove('+d.id+',null);">'+dbcToSbc(d.name)+"</a>"}else{var l;if(j==3){var r=this.allItems[this._type](),s=parseInt(b.parObj);a=r[s];l=a.name+"-"+b.name+"-"+d.name;f.innerHTML='<a href="javascript:void(0);" onclick="PopupSelector.removelv3('+d.id+","+b.id+","+a.id+","+b.parObj+');">'+dbcToSbc(l)+"</a>"}else{var a=this.allItems[this._type]()[b.parObj].subItems[c],g=this;jQuery(g._curItems).each(function(){var c=this.id;itemSubstring=document.getElementById(c);if(itemSubstring!=null){var b=false;a.subItems&&a.subItems.length>0&&jQuery(a.subItems).each(function(){if(this.id==c)b=true});if(b==true){i.removeChild(itemSubstring);g._curItems.remove(this.id)}}});if(this._type=="loc")l=b.name+"-"+d.name;else l=d.name;f.innerHTML='<a href="javascript:void(0);" onclick="PopupSelector.remove('+d.id+","+b.id+","+b.parObj+');">'+dbcToSbc(l)+"</a>"}}i.appendChild(f);this._curItems.push(d)}else{var g=this;g._curItems.length>0&&jQuery(g._curItems).each(function(){var b=this.id;itemSubstring=document.getElementById(b);if(itemSubstring!=null){var a=false;jQuery("#allItems2 input").each(function(){if(this.value.split("@")[0]==b)a=true});if(a!=true){i.removeChild(itemSubstring);g._curItems.remove(this.id)}}});PopupSelector.showtips();jQuery("#allItems1 input").each(function(){if(this.id!="pcbx999999"){var a=null,b=null;a={id:this.value.split("@")[0],name:this.value.split("@")[1],parObj:null};b=document.createElement("li");b.id=a.id;b.innerHTML='<a href="javascript:void(0);" onclick="PopupSelector.remove('+a.id+',null);">'+dbcToSbc(a.name)+"</a>";i.appendChild(b);g._curItems.push(a)}})}this.showHideSelecting(this)}else{if(d.id=="999999"){var g=this;jQuery("#allItems1 input").each(function(){if(this.id!="pcbx999999"){var a=null;a=document.getElementById(this.value.split("@")[0]);if(a!=null){i.removeChild(a);g._curItems.remove(this.value.split("@")[0])}}})}else{f=document.getElementById(d.id);i.removeChild(f);this._curItems.remove(d.id)}PopupSelector.showtips()}if(b==null){var m=this.allItems[this._type]();a=m[c];if(e.checked)if(a.subItems&&a.subItems.length>1)document.getElementById("jQuery"+c).className="layicon";else document.getElementById("jQuery"+c).className="layon";else if(a.subItems&&a.subItems.length>1)document.getElementById("jQuery"+c).className="";else document.getElementById("jQuery"+c).className="nonelay";if(o=="999999")jQuery("#allItems1 input").each(function(a){if(this.id!="pcbx999999")this.checked=this.disabled=e.checked;var b=m[a];if(e.checked)if(b.subItems&&b.subItems.length>1)document.getElementById("jQuery"+a).className="layicon";else document.getElementById("jQuery"+a).className="layon";else if(b.subItems&&b.subItems.length>1)document.getElementById("jQuery"+a).className="";else document.getElementById("jQuery"+a).className="nonelay"});else try{if(a.subItems&&a.subItems.length>1)var t=this._type;jQuery("#subItems input").each(function(d){this.checked=this.disabled=e.checked;var c=a.subItems[d],f="^"+d;if(t!="loc")f="^"+(d+1);var b=document.getElementById(f);if(b!=null)if(e.checked)if(c.subItems&&c.subItems.length>1)b.className="layicon";else b.className="layon";else if(c.subItems&&c.subItems.length>1)b.className="";else b.className="nonelay"})}catch(w){}}else{for(var m=this.allItems[this._type](),n,h=0;h<m.length;h++)if(m[h].id==b.id){a=m[h];n=h;break}if(e.checked){if(a.subItems[c].subItems&&a.subItems[c].subItems.length>1)document.getElementById("^"+c).className="layicon";else document.getElementById("^"+c).className="layon";if(j==2)document.getElementById("jQuery"+n).className="layicon";else if(j==3){document.getElementById("jQuery"+b.parObj).className="layicon";document.getElementById(b.tid).className="layon"}}else if(j==2){if(!this._curItems.GetFather({id:b.id,name:null,parObj:null}))document.getElementById("jQuery"+n).className="layshow";if(a.subItems[c].subItems&&a.subItems[c].subItems.length>1)document.getElementById("^"+c).className="";else document.getElementById("^"+c).className="nonelay"}else if(j==3){for(var q=false,p=this.allItems[this._type]()[b.parObj].subItems,h=0;h<p.length;h++)for(var k=0;k<this._curItems.length;k++){if(this._curItems[k].id!=null&&this._curItems[k].id==p[h].id)q=true;if(this._curItems[k].parObj!=null&&this._curItems[k].parObj.id==p[h].id)q=true}if(q==false)document.getElementById("jQuery"+b.parObj).className="layshow";if(!this._curItems.GetFather({id:b.id,name:null,parObj:null}))document.getElementById("^"+c).className="layshow";document.getElementById(b.tid).className="nonelay"}if(j=="2"){jQuery("#thirdItems input").each(function(){this.checked=this.disabled=e.checked});if(e.checked)jQuery("#thirdItems li").each(function(){this.className="layon"});else jQuery("#thirdItems li").each(function(){this.className="nonelay"})}}},showtips:function(){if(this._curItems.length==0){this._type!="ind"&&jQuery("#divSelecting").css("display","none");if(this._type=="cat"){jQuery("#noSelectedLoc").hide();this.InSalaryPage&&jQuery("#noSelectedCat p").html(jQuery("#noSelectedCat p").html().replace("\u60a8\u6700\u591a\u53ef\u4ee5\u9009\u62e95\u4e2a\u804c\u4f4d\u7c7b\u522b","&nbsp;"));jQuery("#noSelectedCat").show()}if(this._type=="loc"){jQuery("#noSelectedCat").hide();this.InSalaryPage&&jQuery("#noSelectedLoc p").html(jQuery("#noSelectedLoc p").html().replace("\u60a8\u6700\u591a\u53ef\u4ee5\u9009\u62e95\u4e2a\u5de5\u4f5c\u5730\u70b9",""));jQuery("#noSelectedLoc").show()}}},remove:function(b,d,i){document.getElementById("selecting").removeChild(document.getElementById(b));var j=document.getElementById("pcbx"+b);if(j)j.checked=false;var g=this._curItems.clone();this._curItems.clear();for(var a=0;a<g.length;a++)g[a].id!=b&&this._curItems.push(g[a]);if(d==null){jQuery("li[@name="+b+"]").removeClass("layicon");jQuery("li[@name="+b+"]").removeClass("layon");for(var a=32;a<this.allItems[this._type]().length;a++)if(this.allItems[this._type]()[a].id==b&&this.allItems[this._type]()[a].subItems.length<=1){jQuery("li[@name="+b+"]").addClass("nonelay");break}}else{var h=false;if(i!=undefined)for(var f=this.allItems[this._type]()[i].subItems,a=0;a<f.length;a++)for(var c=0;c<this._curItems.length;c++){if(this._curItems[c].id!=null&&this._curItems[c].id==f[a].id)h=true;if(this._curItems[c].parObj!=null&&this._curItems[c].parObj.id==f[a].id)h=true}if(h==false)!this._curItems.GetFather({id:d,name:null,parObj:null})&&!this._curItems.GetFaFather({id:d,name:null,parObj:null})&&jQuery("li[@name="+d+"]").removeClass("layicon")}var k=jQuery("#allItems1 li[@name="+b+"]");if(k.length>0){var e=document.getElementById("pcbx999999");if(e!=null&&e.checked){jQuery("#allItems1 input").each(function(){this.disabled=false});e.checked=false;jQuery("li[@name=999999]").attr("class","nonelay")}}PopupSelector.showtips()},removelv3:function(c,h,i,j){document.getElementById("selecting").removeChild(document.getElementById(c));var g=document.getElementById("pcbx"+c);if(g)g.checked=false;var e=this._curItems.clone();this._curItems.clear();for(var a=0;a<e.length;a++)e[a].id!=c&&this._curItems.push(e[a]);if(h==null){jQuery("li[@name="+c+"]").removeClass("layicon");jQuery("li[@name="+c+"]").removeClass("layon")}else{for(var f=false,d=this.allItems[this._type]()[j].subItems,a=0;a<d.length;a++)for(var b=0;b<this._curItems.length;b++){if(this._curItems[b].id!=null&&this._curItems[b].id==d[a].id)f=true;if(this._curItems[b].parObj!=null&&this._curItems[b].parObj.id==d[a].id)f=true}f==false&&jQuery("li[@name="+i+"]").removeClass("layicon")}PopupSelector.showtips()},showHideSelecting:function(a){jQuery("#noSelectedCat").hide();jQuery("#noSelectedLoc").hide();a._curItems.length>0&&jQuery("#divSelecting").show()},showSubItems:function(b,e,c,f){var a=jQuery("#subItems");this._hideTimer&&clearTimeout(this._hideTimer);this._showTimer&&clearTimeout(this._showTimer);if(b==this._lastPopupIndex&&a.css("display")=="block"){jQuery(c).addClass("layshow");return}!f&&d(b,c,this);var g=this;this._showTimer=setTimeout(function(){d(b,c,g)},this._delay);function d(h,k,c){var f=c.allItems[c._type]()[h],o=jQuery(k).offset(),i={top:o.top+c._style.offset.levelTwo[c._type].Y,left:o.left+c._style.offset.levelTwo[c._type].X};if(document.body.clientWidth<i.left+296)i.left=o.left-296;var j=f.subItems.length-1,p=j%2==0?j/2:j/2+1;if(j<=c._oneColumnLimit[c._type])p=j;var n=c._style.lineHeight*parseInt(p)+c._style.topBottomMargin,s=jQuery(window).height(),m;if(jQuery.browser.msie||jQuery.browser.mozilla)m=document.body.scrollTop;else m=document.documentElement.scrollTop;var q=s-i.top+m;if(q<n)i.top=i.top-(n-q);if(n>s)i.top=m;var l=k.getElementsByTagName("input")[0].checked==true?" checked disabled ":"",v={id:f.id,name:f.name,parObj:null},b=[];b.push("<ol >");var r=1;if(c._type=="loc")r=0;for(var g=r;g<f.subItems.length;g++){var d=f.subItems[g];d.parObj=v;if(k.getElementsByTagName("input")[0].checked==true||c._curItems.has(d)||c._curItems.GetFather(d))if(f.subItems[g].subItems&&f.subItems[g].subItems.length>0){b.push("<li id=^"+g+" name="+f.id+' class="layicon" onmouseover="PopupSelector.showThirdItems(');b.push(h);b.push(",");b.push(g);b.push(', this, true)" onmouseout="PopupSelector.hideThirdItems(this)">');b.push('<a href="javascript:void(0);">');b.push('<input id="scbx');b.push(d.id);b.push('" type="'+PopupSelector._checkType+'" value="');b.push(d.id);b.push("@");b.push(d.name);b.push('"');b.push(l);c._curItems.has(d)&&b.push(" checked");b.push(' onclick="PopupSelector.click('+g+",this, { id: ");b.push(f.id);b.push(", name: '");b.push(f.name);b.push("', parObj: "+h+" }");b.push(",'','2')\" />");b.push(dbcToSbc(d.name));b.push("</a></li>")}else{b.push("<li id=^"+g+" name="+f.id+" class=\"layon\" onmouseover='PopupSelector.clearThirdHidden()' onmouseover='PopupSelector.hideThirdItems(this)'>");b.push('<a href="javascript:void(0);"><label for="scbx');b.push(d.id);b.push('">');b.push('<input id="scbx');b.push(d.id);b.push('" type="'+PopupSelector._checkType+'" value="');b.push(d.id);b.push("@");b.push(d.name);b.push('"');b.push(l);c._curItems.has(d)&&b.push(" checked");b.push(' onclick="PopupSelector.click('+g+",this, { id: ");b.push(f.id);b.push(", name: '");b.push(f.name);b.push("', parObj: "+h+" }");b.push(",'','2')\" />");b.push(dbcToSbc(d.name));b.push("</label></a></li>")}else if(f.subItems[g].subItems&&f.subItems[g].subItems.length>0){b.push("<li id=^"+g+" name="+f.id+' onmouseover="PopupSelector.showThirdItems(');b.push(h);b.push(",");b.push(g);b.push(', this, true)" onmouseout="PopupSelector.hideThirdItems(this)">');b.push('<a href="javascript:void(0);">');b.push('<input id="scbx');b.push(d.id);b.push('" type="'+PopupSelector._checkType+'" value="');b.push(d.id);b.push("@");b.push(d.name);b.push('"');b.push(l);c._curItems.has(d)&&b.push(" checked");b.push(' onclick="PopupSelector.click('+g+",this, { id: ");b.push(f.id);b.push(", name: '");b.push(f.name);b.push("', parObj: "+h+" }");b.push(",'','2')\" />");b.push(dbcToSbc(d.name));b.push("</a></li>")}else{b.push("<li id=^"+g+" name="+f.id+" class=\"nonelay\" onmouseover='PopupSelector.clearThirdHidden()' onmouseover='PopupSelector.hideThirdItems(this)'>");b.push('<a href="javascript:void(0);"><label for="scbx');b.push(d.id);b.push('">');b.push('<input id="scbx');b.push(d.id);b.push('" type="'+PopupSelector._checkType+'" value="');b.push(d.id);b.push("@");b.push(d.name);b.push('"');b.push(l);c._curItems.has(d)&&b.push(" checked");b.push(' onclick="PopupSelector.click('+g+",this, { id: ");b.push(f.id);b.push(", name: '");b.push(f.name);b.push("', parObj: "+h+" }");b.push(",'','2')\" />");b.push(dbcToSbc(d.name));b.push("</label></a></li>")}}b.push("</ol>");for(var u=jQuery("#subBox"),g=0;g<c._types.length;g++){a.removeClass(c._style.className.levelTwo1[c._types[g]]);a.removeClass(c._style.className.levelTwo2[c._types[g]])}var t=j>c._oneColumnLimit[c._type]?c._style.className.levelTwo2[c._type]:c._style.className.levelTwo1[c._type];if(c._type=="loc")if(f.id=="8000"||f.id=="15000")t=c._style.className.levelTwo1[c._type];c._lastPopupIndex=h;jQuery("#subItems").hover(function(a){c.showSubItems(h,e,k,true,a)},function(){c.hideSubItems(k)});u.html(b.join(""));a.addClass(t).css(i).show()}},hideSubItems:function(a){jQuery(a).removeClass("layshow");this._showTimer&&clearTimeout(this._showTimer);this._hideTimer&&clearTimeout(this._hideTimer);this._hideTimer=setTimeout(function(){jQuery("#subItems").hide()},100)},clearThirdHidden:function(){this._showThirdTimer&&clearTimeout(this._showThirdTimer);this._hideThirdTimer&&clearTimeout(this._hideThirdTimer);jQuery("#thirdItems").hide()},showThirdItems:function(c,b,d,f){var a=jQuery("#thirdItems");this._showThirdTimer&&clearTimeout(this._showThirdTimer);this._hideThirdTimer&&clearTimeout(this._hideThirdTimer);if(b+50==this._lastPopupIndex&&a.css("display")=="block"){jQuery("#jQuery"+c).addClass("layshow");jQuery(d).addClass("layshow");return}!f&&e(c,b,d,this);var g=this;this._showThirdTimer=setTimeout(function(){e(c,b,d,g)},this._delay);function e(n,i,k,b){var e=b.allItems[b._type]()[n].subItems[i],m=jQuery(k).offset(),g={top:m.top+b._style.offset.levelTwo[b._type].Y,left:m.left+b._style.offset.levelTwo[b._type].X};if(document.body.clientWidth<g.left+296)g.left=m.left-296;var h=e.subItems.length-1,o=h%2==0?h/2:h/2+1;if(h<=b._oneColumnLimit[b._type])o=h;var l=b._style.lineHeight*parseInt(o)+b._style.topBottomMargin,q=jQuery(window).height(),j;if(jQuery.browser.msie||jQuery.browser.mozilla)j=document.body.scrollTop;else j=document.documentElement.scrollTop;var p=q-g.top+j;if(p<l)g.top=g.top-(l-p);if(l>q)g.top=j;var s=k.getElementsByTagName("input")[0].checked==true?" checked disabled ":"",u={id:e.id,name:e.name,parObj:null},c=[];c.push("<ol >");for(var d=0;d<e.subItems.length;d++){var f=e.subItems[d];f.parObj=u;if(k.getElementsByTagName("input")[0].checked==true||b._curItems.has(f))c.push("<li id=Q"+d+' class="layon" >');else c.push("<li id=Q"+d+' class="nonelay" >');c.push('<a href="javascript:void(0);"><label for="tcbx');c.push(f.id);c.push('"><input id="tcbx');c.push(f.id);c.push('" type="checkbox" value="');c.push(f.id);c.push("@");c.push(f.name);c.push('"');c.push(s);b._curItems.has(f)&&c.push(" checked");c.push(' onclick="PopupSelector.click('+i+",this, { id: ");c.push(e.id);c.push(", name: '");c.push(e.name);c.push("', parObj: "+n+",tid:'Q"+d+"'}");c.push(",'','3')\" />");c.push(addSpan(f.name));c.push("</label></a></li>")}c.push("</ol>");for(var t=jQuery("#thirdBox"),d=0;d<b._types.length;d++){a.removeClass(b._style.className.levelThree1[b._types[d]]);a.removeClass(b._style.className.levelThree2[b._types[d]])}var r=h>b._oneColumnLimit[b._type]?b._style.className.levelThree2[b._type]:b._style.className.levelThree1[b._type];if(b._type=="loc")if(e.id=="8000"||e.id=="15000")r=b._style.className.levelThree1[b._type];b._lastPopupIndex=i+50;jQuery("#thirdItems").hover(function(a){b._hideTimer&&clearTimeout(b._hideTimer);b._hideThirdTimer&&clearTimeout(b._hideThirdTimer);b.showThirdItems(n,i,k,true,a)},function(){b.hideThirdItems()});t.html(c.join(""));a.css("display","block");a.addClass(r).css(g).show()}},hideThirdItems:function(a){jQuery(a).removeClass("layshow");this._showTimer&&clearTimeout(this._showTimer);this._hideTimer&&clearTimeout(this._hideTimer);this._showThirdTimer&&clearTimeout(this._showThirdTimer);this._hideThirdTimer&&clearTimeout(this._hideThirdTimer);this._hideThirdTimer=setTimeout(function(){jQuery("#thirdItems").hide();jQuery("#subItems").hide()},100)},_showTimer:null,_hideTimer:null,_showThirdTimer:null,_hideThirdTimer:null,_lastPopupIndex:null,_box:null,_subbox:null,_ref:null,_type:null,_types:["cat","ind","loc","comptype"],_maxSize:5,_checkType:"checkbox",_curItems:[],_selItems:{cat:[],loc:[],ind:[],comptype:[]},_gtMaxLimit:"\u5bf9\u4e0d\u8d77,\u60a8\u7684\u5df2\u9009\u9879\u5df2\u7ecf\u8fbe\u5230\u4e865\u9879.\u8bf7\u51cf\u5c11\u5df2\u9009\u9879,\u518d\u7ee7\u7eed\u9009\u62e9",_gtYourSelected:{cat:"\u60a8\u9009\u62e9\u7684\u804c\u4f4d\u7c7b\u522b\u662f\uff1a",ind:"\u60a8\u6700\u591a\u53ef\u4ee5\u9009\u62e9\uff15\u4e2a\u884c\u4e1a\u7c7b\u522b",loc:"\u60a8\u9009\u62e9\u7684\u5730\u533a\u662f",comptype:"\u60a8\u9009\u62e9\u7684\u516c\u53f8\u6027\u8d28\u662f\uff1a"},_gtPopupSelectorHeader:{cat:"\u804c\u4f4d\u7c7b\u522b",ind:"\u884c\u4e1a\u7c7b\u522b",loc:"\u8bf7\u9009\u62e9\u5730\u533a",comptype:"\u516c\u53f8\u6027\u8d28"},_oneColumnLimit:{cat:12,loc:11},_delay:500,_style:{className:{levelOne:{cat:"lay_wl",loc:"lay_wls",ind:"lay_wll",comptype:"lay_wms"},levelTwo1:{cat:"lay_wm",loc:"lay_ws lm",ind:"",comptype:""},levelTwo2:{cat:"lay_wl2",loc:"lay_ws lm",ind:"",comptype:""},levelThree1:{cat:"lay_wm",loc:"lay_ws",ind:"",comptype:""},levelThree2:{cat:"lay_wl2",loc:"lay_ws",ind:"",comptype:""}},left:0,top:0,width:{cat:594,loc:466,ind:746,comptype:360},height:{cat:517,loc:419,ind:510,comptype:173},lineHeight:20,topBottomMargin:17,offset:{levelOne:{X:0,Y:20,cat:{X:0,Y:20},loc:{X:250,Y:20}},levelTwo:{cat:{X:304,Y:0},loc:{X:130,Y:0}}}},allItems:{cat:function(){return getCat()},ind:function(){return getInd()},loc:function(){return getLoc()},comptype:function(){return getCompanyType()}}};jQuery(document).ready(function(){if(typeof PopCheckType_Cat!="undefined"&&PopCheckType_Cat!=null){jQuery("#txtCat").click(function(){PopupSelector.popup("cat",this,PopCheckType_Cat)});jQuery("#txtInd").click(function(){PopupSelector.popup("ind",this,PopCheckType_Ind)});jQuery("#txtLoc").click(function(){PopupSelector.popup("loc",this,PopCheckType_Loc)});jQuery("#txtCatDrop").click(function(){PopupSelector.popup("cat",jQuery("#txtCat")[0],PopCheckType_Cat)});jQuery("#txtIndDrop").click(function(){PopupSelector.popup("ind",jQuery("#txtInd")[0],PopCheckType_Ind)});jQuery("#txtLocDrop").click(function(){PopupSelector.popup("loc",jQuery("#txtLoc")[0],PopCheckType_Loc)})}else{jQuery("#txtCat").click(function(){PopupSelector.popup("cat",this)});jQuery("#txtInd").click(function(){PopupSelector.popup("ind",this)});jQuery("#txtLoc").click(function(){PopupSelector.popup("loc",this)});jQuery("#txtCatDrop").click(function(){PopupSelector.popup("cat",jQuery("#txtCat")[0])});jQuery("#txtIndDrop").click(function(){PopupSelector.popup("ind",jQuery("#txtInd")[0])});jQuery("#txtLocDrop").click(function(){PopupSelector.popup("loc",jQuery("#txtLoc")[0])})}popupLayer=$(".sech_layb");jQuery("#lnkEmpty").click(function(){PopupSelector.empty()});jQuery("#lnkCancel").click(function(){PopupSelector.cancel()});jQuery("#lnkOK").click(function(){PopupSelector.OK()});jQuery("#btnOk").click(function(){PopupSelector.OK()});jQuery("#btnOkLoc").click(function(){PopupSelector.OK()});jQuery("#imgClose").click(function(){PopupSelector.close()})});function getJobType(){return[{id:"1",name:"\u5168\u804c"},{id:"2",name:"\u517c\u804c"},{id:"4",name:"\u4e34\u65f6"},{id:"8",name:"\u5b9e\u4e60"}]}function getPostDate(){return[{id:"1",name:"\u5f53\u5929"},{id:"3",name:"3\u5929"},{id:"7",name:"7\u5929"},{id:"15",name:"15\u5929"},{id:"30",name:"30\u5929"},{id:"60",name:"60\u5929"},{id:"90",name:"90\u5929"}]}function getWorkExp(){return[{id:"0",name:"\u4e00\u5e74\u4ee5\u4e0b"},{id:"1",name:"1-2\u5e74"},{id:"4",name:"3-5\u5e74"},{id:"6",name:"6-7\u5e74"},{id:"8",name:"8-10\u5e74\u53ca\u4ee5\u4e0a"}]}function getCompanyType(){return[{id:"10",name:"\u5916\u5546\u72ec\u8d44\u00b7\u5916\u4f01\u529e\u4e8b\u5904"},{id:"20",name:"\u4e2d\u5916\u5408\u8425(\u5408\u8d44\u00b7\u5408\u4f5c)"},{id:"21",name:"\u80a1\u4efd\u5236\u4f01\u4e1a"},{id:"50",name:"\u56fd\u5185\u4e0a\u5e02\u516c\u53f8"},{id:"30",name:"\u79c1\u8425\u00b7\u6c11\u8425\u4f01\u4e1a"},{id:"40",name:"\u56fd\u6709\u4f01\u4e1a"},{id:"60",name:"\u653f\u5e9c\u673a\u5173/\u975e\u8425\u5229\u673a\u6784"},{id:"70",name:"\u4e8b\u4e1a\u5355\u4f4d"}]}function getCompanySize(){return[{id:"1",name:"1-49\u4eba"},{id:"50",name:"50-99\u4eba"},{id:"100",name:"100-499\u4eba"},{id:"500",name:"500-999\u4eba"},{id:"1000",name:"1000\u4eba\u4ee5\u4e0a"}]}function getDegree(){return[{id:"6",name:"\u535a\u58eb"},{id:"5",name:"MBA"},{id:"4",name:"\u7855\u58eb"},{id:"3",name:"\u672c\u79d1"},{id:"2",name:"\u5927\u4e13"},{id:"1",name:"\u4e2d\u4e13"},{id:"-5",name:"\u4e2d\u6280"},{id:"-10",name:"\u9ad8\u4e2d"},{id:"-15",name:"\u521d\u4e2d"}]}function getSalary(){return[{id:"600",name:"1000\u4ee5\u4e0b"},{id:"700",name:"1000\uff5e1999"},{id:"800",name:"2000\uff5e2999"},{id:"900",name:"3000\uff5e3999"},{id:"1000",name:"4000\uff5e5999"},{id:"1100",name:"6000\uff5e7999"},{id:"1200",name:"8000\uff5e9999"},{id:"1300",name:"10000\uff5e14999"},{id:"1400",name:"15000\uff5e19999"},{id:"1500",name:"20000\uff5e29999"},{id:"1600",name:"30000\uff5e49999"},{id:"1700",name:"50000+"}]}function getCat(){return[{id:"600",name:"\u8ba1\u7b97\u673a\u2022\u7f51\u7edc\u2022\u6280\u672f\u7c7b",subItems:[{id:"600",name:"\u8ba1\u7b97\u673a\u2022\u7f51\u7edc\u2022\u6280\u672f\u7c7b"},{id:"1001015",name:"\u9996\u5e2d\u6280\u672f\u5b98CTO\u2022\u9996\u5e2d\u4fe1\u606f\u5b98CIO"},{id:"1001028",name:"\u6280\u672f\u603b\u76d1\u2022\u6280\u672f\u7ecf\u7406"},{id:"1001029",name:"\u4fe1\u606f\u6280\u672f\u7ecf\u7406\u2022\u4fe1\u606f\u6280\u672f\u4e3b\u7ba1"},{id:"1001026",name:"\u4fe1\u606f\u6280\u672f\u4e13\u5458"},{id:"1001003",name:"\u4ea7\u54c1\u7ecf\u7406"},{id:"1001016",name:"\u9879\u76ee\u7ecf\u7406\u2022\u9879\u76ee\u4e3b\u7ba1"},{id:"1001017",name:"\u9879\u76ee\u6267\u884c\u2022\u534f\u8c03\u4eba\u5458"},{id:"1001129",name:"\u67b6\u6784\u5e08"},{id:"608",name:"\u7cfb\u7edf\u5206\u6790\u5458"},{id:"1001014",name:"\u7814\u53d1\u5de5\u7a0b\u5e08"},{id:"1001018",name:"\u9ad8\u7ea7\u8f6f\u4ef6\u5de5\u7a0b\u5e08"},{id:"1001035",name:"\u8f6f\u4ef6\u5de5\u7a0b\u5e08"},{id:"1001019",name:"\u4e92\u8054\u7f51\u8f6f\u4ef6\u5f00\u53d1\u5de5\u7a0b\u5e08"},{id:"1001020",name:"\u9ad8\u7ea7\u786c\u4ef6\u5de5\u7a0b\u5e08"},{id:"603",name:"\u7cfb\u7edf\u96c6\u6210\u5de5\u7a0b\u5e08"},{id:"1001034",name:"\u786c\u4ef6\u5de5\u7a0b\u5e08"},{id:"1001021",name:"\u901a\u4fe1\u6280\u672f\u5de5\u7a0b\u5e08"},{id:"1001128",name:"\u5de5\u7a0b\u4e0e\u9879\u76ee\u5b9e\u65bd"},{id:"1001009",name:"ERP\u6280\u672f\u5e94\u7528\u987e\u95ee\u2022ERP\u5b9e\u65bd\u5de5\u7a0b\u5e08"},{id:"610",name:"\u6570\u636e\u5e93\u7ba1\u7406\u5458\u2022\u6570\u636e\u5e93\u5f00\u53d1\u5de5\u7a0b\u5e08"},{id:"1001022",name:"\u6280\u672f\u652f\u6301\u2022\u7ef4\u62a4\u7ecf\u7406"},{id:"1001030",name:"\u6280\u672f\u652f\u6301\u2022\u7ef4\u62a4\u5de5\u7a0b\u5e08"},{id:"1001023",name:"\u54c1\u8d28\u7ecf\u7406\u2022\u8d28\u91cf\u5de5\u7a0b\u5e08"},{id:"1001126",name:"\u7f51\u7edc\u4e0e\u4fe1\u606f\u5b89\u5168\u5de5\u7a0b\u5e08"},{id:"1001004",name:"\u8f6f\u4ef6\u6d4b\u8bd5"},{id:"1001005",name:"\u786c\u4ef6\u6d4b\u8bd5"},{id:"1001013",name:"\u6587\u6863\u5de5\u7a0b\u5e08"},{id:"1001031",name:"\u7cfb\u7edf\u7ba1\u7406\u5458\u2022\u7f51\u7ba1"},{id:"605",name:" \u8bed\u97f3\u2022\u89c6\u9891\u2022\u591a\u5a92\u4f53\u5f00\u53d1"},{id:"1001008",name:"\u6e38\u620f\u8bbe\u8ba1\u4e0e\u5f00\u53d1"},{id:"1001024",name:"\u7f51\u7ad9\u8425\u8fd0\u7ba1\u7406"},{id:"1001011",name:"\u7f51\u7ad9\u7f16\u8f91"},{id:"1001010",name:"\u7f51\u7ad9\u7b56\u5212"},{id:"1001033",name:"\u7f51\u7edc\u5de5\u7a0b\u5e08"},{id:"1001032",name:"UI\u8bbe\u8ba1\u2022\u7f51\u9875\u8bbe\u8ba1\u4e0e\u5236\u4f5c"},{id:"1001127",name:"\u8ba1\u7b97\u673a\u8f85\u52a9\u8bbe\u8ba1\u5de5\u7a0b\u5e08"},{id:"1001999",name:"\u5176\u4ed6\u804c\u4f4d(\u8ba1\u7b97\u673a\u2022\u7f51\u7edc\u2022\u6280\u672f\u7c7b)"}]},{id:"1035000",name:"\u884c\u653f\u2022\u540e\u52e4\u7c7b",subItems:[{id:"1035000",name:"\u884c\u653f\u2022\u540e\u52e4\u7c7b"},{id:"1035001",name:"\u884c\u653f\u603b\u76d1"},{id:"1035002",name:"\u884c\u653f\u7ecf\u7406\u2022\u884c\u653f\u4e3b\u7ba1\u2022\u529e\u516c\u5ba4\u4e3b\u4efb"},{id:"1035003",name:"\u884c\u653f\u4e13\u5458\u2022\u52a9\u7406"},{id:"1035008",name:"\u540e\u52e4"},{id:"1035004",name:"\u7ecf\u7406\u52a9\u7406\u2022\u79d8\u4e66\u2022\u6587\u5458"},{id:"1035005",name:"\u524d\u53f0\u2022\u603b\u673a\u2022\u63a5\u5f85"},{id:"1035006",name:"\u56fe\u4e66\u60c5\u62a5\u2022\u8d44\u6599\u2022\u6587\u6863\u7ba1\u7406"},{id:"1035007",name:"\u7535\u8111\u64cd\u4f5c\u5458\u2022\u6253\u5b57\u5458"},{id:"1035999",name:"\u5176\u4ed6\u804c\u4f4d(\u884c\u653f\u2022\u540e\u52e4\u7c7b)"}]},{id:"700",name:"\u7535\u5b50\u2022\u7535\u5668\u2022\u901a\u4fe1\u6280\u672f\u7c7b",subItems:[{id:"700",name:"\u7535\u5b50\u2022\u7535\u5668\u2022\u901a\u4fe1\u6280\u672f\u7c7b"},{id:"1002001",name:"\u7535\u5b50\u5de5\u7a0b\u5e08\u2022\u7535\u8def\u5de5\u7a0b\u5e08"},{id:"1002002",name:"\u7535\u5668\u5de5\u7a0b\u5e08"},{id:"1002003",name:"\u7535\u4fe1\u5de5\u7a0b\u5e08\u2022\u901a\u8baf\u5de5\u7a0b\u5e08"},{id:"1002026",name:"\u79fb\u52a8\u901a\u4fe1\u5de5\u7a0b\u5e08\u2022\u65e0\u7ebf\u901a\u4fe1\u5de5\u7a0b\u5e08"},{id:"1002027",name:"\u6709\u7ebf\u4f20\u8f93\u5de5\u7a0b\u5e08"},{id:"1002028",name:"\u589e\u503c\u4ea7\u54c1\u7814\u53d1"},{id:"1002029",name:"\u901a\u4fe1\u7535\u6e90\u5de5\u7a0b\u5e08"},{id:"1002030",name:"\u6570\u636e\u901a\u4fe1\u5de5\u7a0b\u5e08"},{id:"1002015",name:"\u5de5\u827a\u5de5\u7a0b\u5e08"},{id:"1002004",name:"\u7535\u58f0\u5de5\u7a0b\u5e08"},{id:"1002005",name:"\u6570\u7801\u4ea7\u54c1\u5f00\u53d1\u5de5\u7a0b\u5e08"},{id:"1002024",name:"\u5d4c\u5165\u5f0f\u7cfb\u7edf\u8f6f\u4ef6\u5f00\u53d1"},{id:"1002007",name:"\u65e0\u7ebf\u7535\u5de5\u7a0b\u5e08"},{id:"1002008",name:"\u534a\u5bfc\u4f53\u5de5\u7a0b\u5e08"},{id:"1002009",name:"\u7535\u5b50\u5143\u5668\u4ef6\u5de5\u7a0b\u5e08"},{id:"1002010",name:"\u7535\u5b50\u2022\u7535\u5668\u7ef4\u4fee"},{id:"1002012",name:"\u5149\u6e90\u4e0e\u7167\u660e\u5de5\u7a0b\u5e08"},{id:"1002031",name:"\u6fc0\u5149\u2022\u5149\u7535\u5b50\u6280\u672f"},{id:"1002016",name:"\u96c6\u6210\u7535\u8def(IC)\u82af\u7247\u5f00\u53d1"},{id:"1002032",name:"\u7248\u56fe\u8bbe\u8ba1\u5de5\u7a0b\u5e08"},{id:"1002014",name:"\u6280\u672f\u6587\u6863\u5de5\u7a0b\u5e08"},{id:"1002013",name:"\u6d4b\u8bd5\u5de5\u7a0b\u5e08"},{id:"1002021",name:"\u673a\u68b0\u5de5\u7a0b\u5e08"},{id:"1002022",name:"\u7535\u6c14\u5de5\u7a0b\u5e08"},{id:"1002033",name:"\u7535\u6c60\u2022\u7535\u6e90\u5f00\u53d1"},{id:"1002011",name:"\u7814\u53d1\u5de5\u7a0b\u5e08"},{id:"1002025",name:"\u5de5\u7a0b\u4e0e\u9879\u76ee\u5b9e\u65bd"},{id:"1002034",name:"\u73b0\u573a\u5e94\u7528\u5de5\u7a0b\u5e08\u2022FAE"},{id:"1002999",name:"\u5176\u4ed6\u804c\u4f4d(\u7535\u5b50\u2022\u7535\u5668\u2022\u901a\u4fe1\u6280\u672f\u7c7b)"}]},{id:"1016001",name:"\u7ffb\u8bd1\u7c7b",subItems:[{id:"1016001",name:"\u7ffb\u8bd1\u7c7b"},{id:"1801",name:"\u82f1\u8bed"},{id:"1802",name:"\u65e5\u8bed"},{id:"1803",name:"\u6cd5\u8bed"},{id:"1804",name:"\u5fb7\u8bed"},{id:"1805",name:"\u4fc4\u8bed"},{id:"1806",name:"\u897f\u73ed\u7259\u8bed"},{id:"1807",name:"\u97e9\u8bed"},{id:"1808",name:"\u963f\u62c9\u4f2f\u8bed\u7ffb\u8bd1"},{id:"1016999",name:"\u5176\u4ed6\u8bed\u79cd\u7ffb\u8bd1(\u7ffb\u8bd1\u7c7b)"}]},{id:"100",name:"\u9500\u552e\u7c7b",subItems:[{id:"100",name:"\u9500\u552e\u7c7b"},{id:"1005017",name:"\u9500\u552e\u603b\u76d1"},{id:"1005009",name:"\u9500\u552e\u7ecf\u7406\u2022\u9500\u552e\u4e3b\u7ba1"},{id:"1005018",name:"\u9500\u552e\u4ee3\u8868\u2022\u5ba2\u6237\u7ecf\u7406"},{id:"1005010",name:"\u6e20\u9053\u603b\u76d1\u2022\u5206\u9500\u603b\u76d1"},{id:"1005019",name:"\u6e20\u9053\u5206\u9500\u7ecf\u7406\u2022\u6e20\u9053\u5206\u9500\u4e3b\u7ba1"},{id:"1005011",name:"\u6e20\u9053\u4ee3\u8868"},{id:"1005023",name:"\u533a\u57df\u9500\u552e\u7ba1\u7406"},{id:"1005012",name:"\u552e\u524d\u652f\u6301\u7ecf\u7406\u2022\u552e\u524d\u652f\u6301\u4e3b\u7ba1"},{id:"1005020",name:"\u552e\u524d\u652f\u6301\u5de5\u7a0b\u5e08"},{id:"1005006",name:"\u9500\u552e\u52a9\u7406"},{id:"1005013",name:"\u5546\u52a1\u7ecf\u7406\u2022\u5546\u52a1\u4e3b\u7ba1"},{id:"1005014",name:"\u5546\u52a1\u4e13\u5458\u2022\u5546\u52a1\u52a9\u7406"},{id:"1005015",name:"\u4e1a\u52a1\u62d3\u5c55(BD)\u7ecf\u7406"},{id:"1005016",name:"\u7535\u8bdd\u9500\u552e"},{id:"1005022",name:"\u533b\u836f\u4ee3\u8868"},{id:"1005024",name:"\u533b\u7597\u5668\u68b0\u9500\u552e"},{id:"1005021",name:"\u4fdd\u9669\u4ee3\u7406\u4eba\u2022\u7ecf\u7eaa\u4eba\u2022\u5ba2\u6237\u7ecf\u7406"},{id:"1005999",name:"\u5176\u4ed6\u804c\u4f4d(\u9500\u552e\u7c7b)"}]},{id:"900",name:"\u5ba2\u6237\u670d\u52a1\u7c7b",subItems:[{id:"900",name:"\u5ba2\u6237\u670d\u52a1\u7c7b"},{id:"1007008",name:"\u5ba2\u6237\u670d\u52a1\u603b\u76d1"},{id:"1007010",name:"\u5ba2\u6237\u670d\u52a1\u7ecf\u7406\u2022\u5ba2\u6237\u670d\u52a1\u4e3b\u7ba1"},{id:"1007002",name:"\u5ba2\u6237\u670d\u52a1\u4e13\u5458\u2022\u5ba2\u6237\u670d\u52a1\u52a9\u7406"},{id:"1007003",name:"\u5ba2\u6237\u5173\u7cfb\u7ba1\u7406"},{id:"1007011",name:"\u5ba2\u6237\u54a8\u8be2\u70ed\u7ebf\u2022\u547c\u53eb\u4e2d\u5fc3\u4eba\u5458"},{id:"1007009",name:"\u552e\u540e\u652f\u6301\u7ecf\u7406\u2022\u552e\u540e\u652f\u6301\u4e3b\u7ba1"},{id:"1007006",name:"\u552e\u540e\u652f\u6301\u5de5\u7a0b\u5e08"},{id:"1007999",name:"\u5176\u4ed6\u804c\u4f4d(\u5ba2\u6237\u670d\u52a1\u7c7b)"}]},{id:"3600",name:"\u5e02\u573a\u2022\u516c\u5173\u2022\u5a92\u4ecb\u7c7b",subItems:[{id:"3600",name:"\u5e02\u573a\u2022\u516c\u5173\u2022\u5a92\u4ecb\u7c7b"},{id:"1008032",name:"\u5e02\u573a\u603b\u76d1"},{id:"1008033",name:"\u5e02\u573a\u7ecf\u7406\u2022\u5e02\u573a\u4e3b\u7ba1"},{id:"1008007",name:"\u5e02\u573a\u4e13\u5458\u2022\u5e02\u573a\u52a9\u7406"},{id:"1008029",name:"\u516c\u5173\u5a92\u4ecb\u7ecf\u7406\u2022\u516c\u5173\u5a92\u4ecb\u4e3b\u7ba1"},{id:"1008030",name:"\u516c\u5173\u5a92\u4ecb\u4e13\u5458"},{id:"3630",name:"\u5e02\u573a\u8c03\u7814\u4e0e\u5206\u6790"},{id:"1008011",name:"\u4f1a\u52a1\u7ecf\u7406\u2022\u4f1a\u52a1\u4e3b\u7ba1"},{id:"1008012",name:"\u4f1a\u52a1\u4e13\u5458"},{id:"1008008",name:"\u4ea7\u54c1\u7ecf\u7406\u2022\u54c1\u724c\u7ecf\u7406\u2022\u4e3b\u7ba1"},{id:"1008005",name:"\u4ea7\u54c1\u4e13\u5458\u2022\u54c1\u724c\u4e13\u5458"},{id:"1008026",name:"\u4fc3\u9500\u7ecf\u7406\u2022\u4fc3\u9500\u4e3b\u7ba1"},{id:"1008027",name:"\u4fc3\u9500\u5458"},{id:"1008022",name:"\u5e02\u573a\u4f01\u5212\u7ecf\u7406\u2022\u5e02\u573a\u4f01\u5212\u4e3b\u7ba1"},{id:"1008023",name:"\u5e02\u573a\u4f01\u5212\u4e13\u5458"},{id:"1500",name:"\u5e7f\u544a\u521b\u610f\u2022\u7b56\u5212\u2022\u8bbe\u8ba1\u6216\u6587\u6848"},{id:"1008024",name:"\u5e7f\u544a\u5ba2\u6237\u7ecf\u7406"},{id:"1008025",name:"\u5e7f\u544a\u5ba2\u6237\u4e3b\u7ba1\u2022\u5e7f\u544a\u5ba2\u6237\u4e13\u5458"},{id:"1008031",name:"\u5e02\u573a\u8425\u9500\u7ecf\u7406\u2022\u5e02\u573a\u8425\u9500\u4e3b\u7ba1"},{id:"1008028",name:"\u5e02\u573a\u8425\u9500\u4e13\u5458"},{id:"1008999",name:"\u5176\u4ed6\u804c\u4f4d(\u5e02\u573a\u2022\u516c\u5173\u2022\u5a92\u4ecb\u7c7b)"}]},{id:"2100",name:"\u54a8\u8be2\u2022\u987e\u95ee\u7c7b",subItems:[{id:"2100",name:"\u54a8\u8be2\u2022\u987e\u95ee\u7c7b"},{id:"1010004",name:"\u4f01\u7ba1\u987e\u95ee\u2022\u4e13\u4e1a\u987e\u95ee\u2022\u7b56\u5212\u5e08"},{id:"1010009",name:"\u54a8\u8be2\u603b\u76d1"},{id:"1010010",name:"\u54a8\u8be2\u7ecf\u7406"},{id:"1010011",name:"\u54a8\u8be2\u5458"},{id:"1010006",name:"\u8c03\u7814\u5458"},{id:"1010007",name:"\u57f9\u8bad\u5e08"},{id:"1010008",name:"\u6d89\u5916\u54a8\u8be2\u5e08"},{id:"1010999",name:"\u5176\u4ed6\u804c\u4f4d(\u54a8\u8be2\u2022\u987e\u95ee\u7c7b)"}]},{id:"400",name:"\u8d22\u52a1\u2022\u5ba1\u8ba1\u2022\u7edf\u8ba1\u7c7b",subItems:[{id:"400",name:"\u8d22\u52a1\u2022\u5ba1\u8ba1\u2022\u7edf\u8ba1\u7c7b"},{id:"1012024",name:"\u9996\u5e2d\u8d22\u52a1\u5b98CFO"},{id:"1012011",name:"\u8d22\u52a1\u603b\u76d1"},{id:"1012012",name:"\u8d22\u52a1\u7ecf\u7406"},{id:"1012002",name:"\u8d22\u52a1\u4e3b\u7ba1\u2022\u603b\u5e10\u4e3b\u7ba1"},{id:"1012013",name:"\u4f1a\u8ba1\u7ecf\u7406\u2022\u4f1a\u8ba1\u4e3b\u7ba1"},{id:"1012014",name:"\u4f1a\u8ba1"},{id:"1012004",name:"\u51fa\u7eb3"},{id:"1012015",name:"\u8d22\u52a1\u5206\u6790\u7ecf\u7406\u2022\u8d22\u52a1\u5206\u6790\u4e3b\u7ba1"},{id:"1012016",name:"\u8d22\u52a1\u5206\u6790\u5458"},{id:"1012017",name:"\u6210\u672c\u7ecf\u7406\u2022\u6210\u672c\u4e3b\u7ba1"},{id:"1012018",name:"\u6210\u672c\u7ba1\u7406\u5458"},{id:"1012019",name:"\u5ba1\u8ba1\u7ecf\u7406\u2022\u5ba1\u8ba1\u4e3b\u7ba1"},{id:"1012020",name:"\u5ba1\u8ba1\u4e13\u5458\u2022\u5ba1\u8ba1\u52a9\u7406"},{id:"1012008",name:"\u8d22\u52a1\u52a9\u7406\u2022\u4f1a\u8ba1\u52a9\u7406"},{id:"1012009",name:"\u7edf\u8ba1"},{id:"1012021",name:"\u7a0e\u52a1\u7ecf\u7406\u2022\u7a0e\u52a1\u4e3b\u7ba1"},{id:"1012022",name:"\u7a0e\u52a1\u4e13\u5458\u2022\u7a0e\u52a1\u52a9\u7406"},{id:"1012023",name:"\u6295\u878d\u8d44\u7ecf\u7406\u2022\u6295\u878d\u8d44\u4e3b\u7ba1"},{id:"1012999",name:"\u5176\u4ed6\u804c\u4f4d(\u8d22\u52a1\u2022\u5ba1\u8ba1\u2022\u7edf\u8ba1\u7c7b)"}]},{id:"604",name:"\u9879\u76ee\u7ba1\u7406\u7c7b",subItems:[{id:"604",name:"\u9879\u76ee\u7ba1\u7406\u7c7b"},{id:"1006001",name:"\u9879\u76ee\u603b\u76d1"},{id:"1006002",name:"\u9879\u76ee\u7ecf\u7406"},{id:"1006999",name:"\u5176\u4ed6\u804c\u4f4d(\u9879\u76ee\u7ba1\u7406\u7c7b)"}]},{id:"300",name:"\u4eba\u529b\u8d44\u6e90\u7c7b",subItems:[{id:"300",name:"\u4eba\u529b\u8d44\u6e90\u7c7b"},{id:"1011020",name:"\u4eba\u529b\u8d44\u6e90\u603b\u76d1"},{id:"1011002",name:"\u4eba\u529b\u8d44\u6e90\u7ecf\u7406\u2022\u4eba\u529b\u8d44\u6e90\u4e3b\u7ba1"},{id:"1011003",name:"\u4eba\u529b\u8d44\u6e90\u4e13\u5458\u2022\u4eba\u529b\u8d44\u6e90\u52a9\u7406"},{id:"1011019",name:"\u4eba\u4e8b\u4e3b\u7ba1\u2022\u4eba\u4e8b\u4e13\u5458"},{id:"1011004",name:"\u62db\u8058\u7ecf\u7406\u2022\u62db\u8058\u4e3b\u7ba1"},{id:"1011005",name:"\u62db\u8058\u4e13\u5458\u2022\u62db\u8058\u52a9\u7406"},{id:"1011006",name:"\u57f9\u8bad\u7ecf\u7406\u2022\u57f9\u8bad\u4e3b\u7ba1"},{id:"1011007",name:"\u57f9\u8bad\u4e13\u5458\u2022\u57f9\u8bad\u52a9\u7406"},{id:"1011008",name:"\u57f9\u8bad\u5e08"},{id:"1011009",name:"\u85aa\u8d44\u798f\u5229\u7ecf\u7406\u2022\u4e3b\u7ba1"},{id:"1011010",name:"\u85aa\u8d44\u798f\u5229\u4e13\u5458\u2022\u85aa\u8d44\u798f\u5229\u52a9\u7406"},{id:"1011011",name:"\u7ee9\u6548\u8003\u6838\u7ecf\u7406\u2022\u7ee9\u6548\u8003\u6838\u4e3b\u7ba1"},{id:"1011012",name:"\u7ee9\u6548\u8003\u6838\u4e13\u5458\u2022\u7ee9\u6548\u8003\u6838\u52a9\u7406"},{id:"1011013",name:"\u5458\u5de5\u5173\u7cfb\u7ecf\u7406\u2022\u5458\u5de5\u5173\u7cfb\u4e3b\u7ba1"},{id:"1011014",name:"\u5458\u5de5\u5173\u7cfb\u4e13\u5458\u2022\u5458\u5de5\u5173\u7cfb\u52a9\u7406"},{id:"1011021",name:"\u730e\u5934\u987e\u95ee"},{id:"1011999",name:"\u5176\u4ed6\u804c\u4f4d(\u4eba\u529b\u8d44\u6e90\u7c7b)"}]},{id:"1900",name:"\u6559\u80b2\u2022\u57f9\u8bad\u7c7b",subItems:[{id:"1900",name:"\u6559\u80b2\u2022\u57f9\u8bad\u7c7b"},{id:"1023001",name:"\u6559\u5b66\u2022\u6559\u52a1\u7ba1\u7406\u4eba\u5458"},{id:"1023002",name:"\u5e7c\u513f\u6559\u80b2"},{id:"1023003",name:"\u6559\u5e08"},{id:"1023004",name:"\u8bb2\u5e08"},{id:"1023005",name:"\u52a9\u6559"},{id:"1023006",name:"\u5bb6\u6559"},{id:"1023007",name:"\u5065\u8eab\u987e\u95ee/\u6559\u7ec3"},{id:"1023008",name:"\u6559\u80b2\u4ea7\u54c1\u5f00\u53d1"},{id:"1023999",name:"\u5176\u4ed6\u804c\u4f4d(\u6559\u80b2\u2022\u57f9\u8bad\u7c7b)"}]},{id:"1200",name:"\u7ecf\u8425\u7ba1\u7406\u7c7b",subItems:[{id:"1200",name:"\u7ecf\u8425\u7ba1\u7406\u7c7b"},{id:"1009001",name:"\u9996\u5e2d\u6267\u884c\u5b98CEO\u2022\u603b\u88c1\u2022\u603b\u7ecf\u7406"},{id:"1009004",name:"\u9996\u5e2d\u8fd0\u8425\u5b98COO"},{id:"1009005",name:"\u9996\u5e2d\u6280\u672f\u5b98CTO\u2022\u9996\u5e2d\u4fe1\u606f\u5b98CIO"},{id:"1009006",name:"\u9996\u5e2d\u8d22\u52a1\u5b98CFO"},{id:"1009013",name:"\u526f\u603b\u88c1\u2022\u526f\u603b\u7ecf\u7406"},{id:"1009007",name:"\u5408\u4f19\u4eba"},{id:"1009008",name:"\u603b\u76d1\u2022\u4e8b\u4e1a\u90e8\u603b\u7ecf\u7406"},{id:"1009003",name:"\u603b\u88c1\u52a9\u7406\u2022\u603b\u7ecf\u7406\u52a9\u7406"},{id:"1009009",name:"\u5206\u516c\u53f8\u7ecf\u7406\u2022\u5206\u652f\u673a\u6784\u7ecf\u7406\u2022\u529e\u4e8b\u5904\u7ecf\u7406"},{id:"1009010",name:"\u90e8\u95e8\u7ecf\u7406"},{id:"1009011",name:"\u5382\u957f\u2022\u526f\u5382\u957f"},{id:"1009012",name:"\u5e97\u957f"},{id:"1009999",name:"\u5176\u4ed6\u804c\u4f4d(\u7ecf\u8425\u7ba1\u7406\u7c7b)"}]},{id:"1034000",name:"\u8d28\u91cf\u7ba1\u7406\u7c7b",subItems:[{id:"1034000",name:"\u8d28\u91cf\u7ba1\u7406\u7c7b"},{id:"1034001",name:"\u8d28\u91cf\u4fdd\u8bc1(QA)\u2022\u8d28\u91cf\u7ba1\u7406\u2022\u8d28\u91cf\u7763\u5bfc"},{id:"1034002",name:"\u8d28\u91cf\u63a7\u5236(QC)\u2022\u8d28\u91cf\u68c0\u9a8c"},{id:"1034003",name:"\u65b0\u4ea7\u54c1\u5f00\u53d1\u6d4b\u8bd5"},{id:"1034004",name:"\u4f9b\u5e94\u5546\u7ba1\u7406\u2022\u91c7\u8d2d\u8bbe\u5907\u4e0e\u6750\u6599\u8d28\u91cf\u7ba1\u7406"},{id:"1034005",name:"\u8d28\u91cf\u4f53\u7cfb\u8ba4\u8bc1"},{id:"1034999",name:"\u5176\u4ed6\u804c\u4f4d(\u8d28\u91cf\u7ba1\u7406\u7c7b)"}]},{id:"3800",name:"\u7f8e\u672f\u2022\u8bbe\u8ba1\u2022\u521b\u610f\u7c7b",subItems:[{id:"3800",name:"\u7f8e\u672f\u2022\u8bbe\u8ba1\u2022\u521b\u610f\u7c7b"},{id:"1021001",name:"\u8bbe\u8ba1\u7ba1\u7406\u4eba\u5458"},{id:"1021002",name:"\u7f8e\u672f\u2022\u56fe\u5f62\u8bbe\u8ba1"},{id:"1021003",name:"\u5de5\u4e1a\u2022\u4ea7\u54c1\u8bbe\u8ba1"},{id:"1021018",name:"\u670d\u88c5\u2022\u7eba\u7ec7\u54c1\u8bbe\u8ba1\u5e08"},{id:"1021015",name:"\u670d\u88c5\u6253\u6837\u2022\u670d\u88c5\u5236\u677f"},{id:"1021005",name:"\u5de5\u827a\u54c1\u2022\u73e0\u5b9d\u8bbe\u8ba1"},{id:"1021006",name:"\u5bb6\u5177\u8bbe\u8ba1"},{id:"1021007",name:"\u5e73\u9762\u8bbe\u8ba1"},{id:"1021008",name:"\u5a92\u4f53\u5e7f\u544a\u8bbe\u8ba1"},{id:"1021009",name:"\u9020\u578b\u8bbe\u8ba1"},{id:"1021011",name:"\u591a\u5a92\u4f53\u8bbe\u8ba1"},{id:"1021012",name:"\u52a8\u753b\u8bbe\u8ba1"},{id:"1021013",name:"\u5c55\u793a\u2022\u88c5\u6f62\u8bbe\u8ba1"},{id:"1021014",name:"\u521b\u610f\u2022\u7b56\u5212\u2022\u6587\u6848"},{id:"1021999",name:"\u5176\u4ed6\u804c\u4f4d(\u7f8e\u672f\u2022\u8bbe\u8ba1\u2022\u521b\u610f\u7c7b)"}]},{id:"3300",name:"\u6280\u5de5\u7c7b",subItems:[{id:"3300",name:"\u6280\u5de5\u7c7b"},{id:"1028001",name:"\u7535\u5de5\u2022\u94c6\u710a\u5de5\u2022\u94b3\u5de5"},{id:"1028002",name:"\u7a7a\u8c03\u5de5\u2022\u7535\u68af\u5de5\u2022\u9505\u7089\u5de5"},{id:"3313",name:"\u6cb9\u6f06\u2022\u94a3\u91d1"},{id:"3302",name:"\u952f\u5e8a\u2022\u8f66\u5e8a\u2022\u78e8\u5e8a\u2022\u94e3\u5e8a\u2022\u51b2\u5e8a\u2022\u9523\u5e8a"},{id:"3303",name:"\u94f2\u8f66\u2022\u53c9\u8f66\u5de5"},{id:"3304",name:"\u673a\u4fee\u5de5"},{id:"1028011",name:"\u5207\u5272\u6280\u5de5"},{id:"1028012",name:"\u4ea7\u54c1\u6216\u5de5\u827a\u5de5\u7a0b\u5e08"},{id:"1028004",name:"\u666e\u5de5"},{id:"1028006",name:"\u6c34\u5de5\u2022\u6728\u5de5\u2022\u6cb9\u6f06\u5de5"},{id:"1028013",name:"\u6a21\u5177\u5de5\u7a0b\u5e08"},{id:"1028999",name:"\u5176\u4ed6\u804c\u4f4d(\u6280\u5de5\u7c7b)"}]},{id:"1013000",name:"\u91d1\u878d\u7c7b\uff08\u94f6\u884c\u2022\u57fa\u91d1\u2022\u8bc1\u5238\u2022\u671f\u8d27\u2022\u6295\u8d44\u2022\u5178\u5f53\uff09",subItems:[{id:"1013000",name:"\u91d1\u878d\u7c7b\uff08\u94f6\u884c\u2022\u57fa\u91d1\u2022\u8bc1\u5238\u2022\u671f\u8d27\u2022\u6295\u8d44\u2022\u5178\u5f53\uff09"},{id:"1013003",name:"\u8bc1\u5238\u2022\u5916\u6c47\u2022\u671f\u8d27\u7ecf\u7eaa\u4eba"},{id:"1013016",name:"\u8bc1\u5238\u5206\u6790\u5e08"},{id:"1013018",name:"\u80a1\u7968\u2022\u671f\u8d27\u64cd\u76d8\u624b"},{id:"1013001",name:"\u884c\u957f\u2022\u526f\u884c\u957f\u2022\u9ad8\u7ea7\u7ba1\u7406"},{id:"2300",name:"\u6295\u8d44\u7ba1\u7406\u2022\u7814\u7a76\u5206\u6790\u2022\u987e\u95ee"},{id:"1013013",name:"\u6295\u8d44\u94f6\u884c\u4e1a\u52a1"},{id:"1013004",name:"\u878d\u8d44\u9879\u76ee\u7ba1\u7406"},{id:"1013006",name:"\u5ba2\u6237\u7ecf\u7406\u2022\u91d1\u878d\u4ea7\u54c1\u8425\u9500\u7ba1\u7406"},{id:"1013007",name:"\u8d44\u4ea7\u7ba1\u7406\u2022\u8d44\u4ea7\u8bc4\u4f30\u2022\u4ea4\u6613\u7ba1\u7406"},{id:"1013005",name:"\u98ce\u9669\u7ba1\u7406\u2022\u7a3d\u6838\u2022\u6cd5\u5f8b"},{id:"1013008",name:"\u4fe1\u8d37\u7ba1\u7406\u2022\u4fe1\u7528\u7ba1\u7406"},{id:"1013009",name:"\u8d44\u91d1\u7ba1\u7406\u2022\u8d22\u52a1\u7ba1\u7406\u2022\u6e05\u7b97\u2022\u7ed3\u7b97"},{id:"1013010",name:"\u67dc\u5458\u2022\u7406\u8d22\u54a8\u8be2\u2022\u5ba2\u6237\u670d\u52a1\u2022\u94f6\u884c\u4f1a\u8ba1"},{id:"1013014",name:"\u94f6\u884c\u5361\u2022\u7535\u5b50\u94f6\u884c\u2022\u65b0\u4e1a\u52a1\u5f00\u62d3"},{id:"1013015",name:"\u56fd\u9645\u7ed3\u7b97\u2022\u5916\u6c47\u4ea4\u6613"},{id:"1013019",name:"\u62cd\u5356\u5e08"},{id:"1013020",name:"\u9274\u5b9a\u2022\u4f30\u4ef7\u5e08"},{id:"1013999",name:"\u5176\u4ed6\u804c\u4f4d(\u91d1\u878d\u7c7b\uff08\u94f6\u884c\u2022\u57fa\u91d1\u2022\u8bc1\u5238\u2022\u671f\u8d27\u2022\u6295\u8d44\u2022\u5178\u5f53\uff09)"}]},{id:"1036000",name:"\u4fdd\u9669\u7c7b",subItems:[{id:"1036000",name:"\u4fdd\u9669\u7c7b"},{id:"1036001",name:"\u7cbe\u7b97\u2022\u4ea7\u54c1\u7814\u53d1\u2022\u6295\u8d44\u2022\u7a3d\u6838\u2022\u6cd5\u5f8b"},{id:"1036002",name:"\u6838\u4fdd\u2022\u7406\u8d54\u2022\u5951\u7ea6\u7ba1\u7406\u2022\u53d7\u7406\u53f0"},{id:"1036003",name:"\u7ec4\u8bad\u2022\u57f9\u8bad\u2022\u4eba\u5458\u7ba1\u7406\u2022\u4e1a\u52a1\u63a8\u52a8"},{id:"1036004",name:"\u4fdd\u9669\u4ee3\u7406\u4eba\u2022\u7ecf\u7eaa\u4eba\u2022\u5ba2\u6237\u7ecf\u7406"},{id:"1036005",name:"\u5ba2\u6237\u670d\u52a1\u2022\u7eed\u671f\u7ba1\u7406"},{id:"1036006",name:"\u4fdd\u9669\u5185\u52e4"},{id:"1036999",name:"\u5176\u4ed6\u804c\u4f4d(\u4fdd\u9669\u7c7b)"}]},{id:"1014000",name:"\u8d38\u6613\u2022\u7269\u6d41\u2022\u91c7\u8d2d\u2022\u8fd0\u8f93\u7c7b",subItems:[{id:"1014000",name:"\u8d38\u6613\u2022\u7269\u6d41\u2022\u91c7\u8d2d\u2022\u8fd0\u8f93\u7c7b"},{id:"1014001",name:"\u5916\u8d38\u7ecf\u7406\u2022\u4e3b\u7ba1\u2022\u4e13\u5458\u2022\u52a9\u7406"},{id:"1014002",name:"\u56fd\u5185\u8d38\u6613\u7ecf\u7406\u2022\u4e3b\u7ba1\u2022\u4e13\u5458\u2022\u52a9\u7406"},{id:"1014003",name:"\u4e1a\u52a1\u8ddf\u5355"},{id:"1014020",name:"\u5355\u8bc1\u5458\u2022\u62a5\u5173\u5458"},{id:"1014014",name:"\u9646\u8fd0\u2022\u6d77\u8fd0\u2022\u7a7a\u8fd0\u4eba\u5458"},{id:"1014005",name:"\u5546\u52a1\u7ecf\u7406\u2022\u4e3b\u7ba1\u2022\u4e13\u5458\u2022\u52a9\u7406"},{id:"1014021",name:"\u91c7\u8d2d\u7ecf\u7406\u2022\u4e3b\u7ba1"},{id:"1014015",name:"\u91c7\u8d2d\u5458\u2022\u52a9\u7406"},{id:"1014022",name:"\u7269\u6d41\u7ecf\u7406\u2022\u7269\u6d41\u4e3b\u7ba1"},{id:"1014016",name:"\u7269\u6d41\u4e13\u5458\u2022\u7269\u6d41\u52a9\u7406"},{id:"1014017",name:"\u7269\u6599\u7ba1\u7406"},{id:"1014008",name:"\u4ed3\u5e93\u7ba1\u7406"},{id:"1014009",name:"\u8fd0\u8f93\u7ecf\u7406\u2022\u4e3b\u7ba1"},{id:"1014010",name:"\u8d27\u8fd0\u4ee3\u7406"},{id:"1014011",name:"\u6d77\u9646\u7a7a\u4ea4\u901a\u8fd0\u8f93"},{id:"1014023",name:"\u53f8\u673a"},{id:"1014012",name:"\u8c03\u5ea6\u5458"},{id:"1014013",name:"\u5feb\u9012\u5458\u2022\u901f\u9012\u5458"},{id:"1014024",name:"\u4f9b\u5e94\u94fe\u7ba1\u7406"},{id:"1014025",name:"\u4e70\u624b"},{id:"1014026",name:"\u6d77\u5173\u4e8b\u52a1\u7ba1\u7406"},{id:"1024027",name:"\u96c6\u88c5\u7bb1\u4e1a\u52a1"},{id:"1014999",name:"\u5176\u4ed6\u804c\u4f4d(\u8d38\u6613\u2022\u7269\u6d41\u2022\u91c7\u8d2d\u2022\u8fd0\u8f93\u7c7b)"}]},{id:"1020000",name:"\u5546\u4e1a\u96f6\u552e\u7c7b",subItems:[{id:"1020000",name:"\u5546\u4e1a\u96f6\u552e\u7c7b"},{id:"1020001",name:"\u5e97\u957f"},{id:"1020002",name:"\u8425\u8fd0"},{id:"1020003",name:"\u751f\u9c9c\u2022\u9632\u635f\u6280\u672f\u4eba\u5458"},{id:"1020004",name:"\u7406\u8d27\u5458"},{id:"1020005",name:"\u8425\u4e1a\u5458\u2022\u670d\u52a1\u5458\u2022\u5e97\u5458\u2022\u5bfc\u8d2d\u5458"},{id:"3307",name:"\u6536\u94f6\u5458"},{id:"1020999",name:"\u5176\u4ed6\u804c\u4f4d(\u5546\u4e1a\u96f6\u552e\u7c7b)"}]},{id:"702",name:"\u5efa\u7b51\u2022\u623f\u5730\u4ea7\u2022\u88c5\u9970\u88c5\u4fee\u2022\u7269\u4e1a\u7ba1\u7406\u7c7b",subItems:[{id:"702",name:"\u5efa\u7b51\u2022\u623f\u5730\u4ea7\u2022\u88c5\u9970\u88c5\u4fee\u2022\u7269\u4e1a\u7ba1\u7406\u7c7b"},{id:"1015021",name:"\u9ad8\u7ea7\u5efa\u7b51\u5de5\u7a0b\u5e08\u2022\u603b\u5de5"},{id:"3103",name:"\u5efa\u7b51\u5e08"},{id:"1015001",name:"\u7ed3\u6784\u5de5\u7a0b\u5e08\u2022\u571f\u6728\u571f\u5efa\u5de5\u7a0b\u5e08"},{id:"1015002",name:"\u5efa\u7b51\u8bbe\u8ba1\u2022\u5efa\u7b51\u5236\u56fe"},{id:"1015011",name:"\u5ca9\u571f\u5de5\u7a0b\u5e08"},{id:"1015012",name:"\u5e55\u5899\u5de5\u7a0b\u5e08"},{id:"1015003",name:"\u5efa\u7b51\u5de5\u7a0b\u7ba1\u7406"},{id:"1015004",name:"\u5de5\u7a0b\u76d1\u7406"},{id:"1015013",name:"\u5efa\u7b51\u5de5\u7a0b\u9a8c\u6536"},{id:"1015014",name:"\u6d4b\u7ed8\u2022\u6d4b\u91cf"},{id:"3107",name:"\u7ed9\u6392\u6c34\u2022\u5f3a\u7535\u2022\u5f31\u7535\u2022\u5236\u51b7\u6696\u901a"},{id:"3101",name:"\u623f\u5730\u4ea7\u5f00\u53d1\u2022\u7b56\u5212"},{id:"1015020",name:"\u623f\u5730\u4ea7\u9879\u76ee\u62db\u6295\u6807\u4e13\u5458"},{id:"3102",name:"\u623f\u5730\u4ea7\u8bc4\u4f30"},{id:"1015015",name:"\u623f\u5730\u4ea7\u9500\u552e"},{id:"1015005",name:"\u8bbe\u5907\u5de5\u7a0b\u5e08"},{id:"1015006",name:"\u9020\u4ef7\u2022\u6982\u9884\u7b97"},{id:"3108",name:"\u8def\u6865\u2022\u96a7\u9053\u2022\u6e2f\u53e3\u2022\u822a\u9053\u5de5\u7a0b"},{id:"3109",name:"\u56ed\u827a\u8bbe\u8ba1\u2022\u56ed\u6797\u8bbe\u8ba1\u2022\u666f\u89c2\u8bbe\u8ba1"},{id:"3110",name:"\u5ba4\u5185\u5916\u88c5\u6f62\u8bbe\u8ba1"},{id:"2200",name:"\u7269\u4e1a\u7ba1\u7406"},{id:"1015016",name:"\u7269\u4e1a\u987e\u95ee"},{id:"1015017",name:"\u7269\u4e1a\u62db\u5546\u2022\u79df\u8d41\u2022\u79df\u552e"},{id:"1015018",name:"\u7269\u4e1a\u8bbe\u65bd\u7ba1\u7406"},{id:"1015019",name:"\u7269\u4e1a\u7ef4\u4fee"},{id:"1015007",name:"\u57ce\u5e02\u89c4\u5212\u4e0e\u8bbe\u8ba1"},{id:"1015008",name:"\u623f\u5730\u4ea7\u4e2d\u4ecb\u2022\u4ea4\u6613"},{id:"1015009",name:"\u516c\u8def\u6865\u6881\u8bbe\u8ba1\u2022\u516c\u8def\u6865\u6881\u9884\u7b97\u5e08"},{id:"1015010",name:"\u65bd\u5de5\u5458"},{id:"1015999",name:"\u5176\u4ed6\u804c\u4f4d(\u5efa\u7b51\u2022\u623f\u5730\u4ea7\u2022\u88c5\u9970\u88c5\u4fee\u2022\u7269\u4e1a\u7ba1\u7406\u7c7b)"}]},{id:"1700",name:"\u6cd5\u5f8b\u7c7b",subItems:[{id:"1700",name:"\u6cd5\u5f8b\u7c7b"},{id:"1024001",name:"\u5f8b\u5e08"},{id:"1024002",name:"\u6cd5\u5f8b\u987e\u95ee"},{id:"1024003",name:"\u5f8b\u5e08\u52a9\u7406"},{id:"1024004",name:"\u6cd5\u52a1\u4eba\u5458"},{id:"1024005",name:"\u77e5\u8bc6\u4ea7\u6743\u2022\u4e13\u5229\u987e\u95ee"},{id:"1024999",name:"\u5176\u4ed6\u804c\u4f4d(\u6cd5\u5f8b\u7c7b)"}]},{id:"1017000",name:"\u9152\u5e97\u2022\u9910\u996e\u2022\u65c5\u6e38\u2022\u670d\u52a1\u7c7b",subItems:[{id:"1017000",name:"\u9152\u5e97\u2022\u9910\u996e\u2022\u65c5\u6e38\u2022\u670d\u52a1\u7c7b"},{id:"1017007",name:"\u5bbe\u9986\u6216\u9152\u5e97\u7ba1\u7406"},{id:"1017001",name:"\u5a31\u4e50\u6216\u9910\u996e\u7ba1\u7406"},{id:"1017002",name:"\u5927\u5802\u7ecf\u7406\u2022\u526f\u7406"},{id:"1017003",name:"\u697c\u9762\u7ecf\u7406"},{id:"1017004",name:"\u53a8\u5e08\u2022\u8c03\u9152\u5e08"},{id:"1017008",name:"\u8425\u517b\u5e08"},{id:"1017009",name:"\u670d\u52a1\u5458"},{id:"3309",name:"\u793c\u4eea\u2022\u5546\u52a1\u2022\u63a5\u7ebf\u751f"},{id:"1017010",name:"\u8425\u4e1a\u5458\u2022\u6536\u94f6\u5458\u2022\u7406\u8d27\u5458"},{id:"1017005",name:"\u5bfc\u6e38\u2022\u8ba1\u8c03"},{id:"1017017",name:"\u7968\u52a1"},{id:"1017006",name:"\u5065\u8eab\u6559\u7ec3"},{id:"1017011",name:"\u7f8e\u5bb9\u7f8e\u53d1"},{id:"1017012",name:"\u53f8\u673a"},{id:"1017013",name:"\u4fdd\u5b89"},{id:"1017014",name:"\u5bfb\u547c\u5458\u2022\u8bdd\u52a1\u5458"},{id:"1017015",name:"\u793e\u533a\u6216\u5bb6\u653f\u670d\u52a1"},{id:"1017018",name:"\u4fdd\u6d01"},{id:"1017999",name:"\u5176\u4ed6\u804c\u4f4d(\u9152\u5e97\u2022\u9910\u996e\u2022\u65c5\u6e38\u2022\u670d\u52a1\u7c7b)"}]},{id:"1026000",name:"\u751f\u7269\u2022\u5236\u836f\u2022\u5316\u5de5\u2022\u73af\u4fdd\u7c7b",subItems:[{id:"1026000",name:"\u751f\u7269\u2022\u5236\u836f\u2022\u5316\u5de5\u2022\u73af\u4fdd\u7c7b"},{id:"1026001",name:"\u751f\u7269\u5de5\u7a0b\u2022\u751f\u7269\u5236\u836f"},{id:"1026002",name:"\u4e34\u5e8a\u8bd5\u9a8c\u2022\u836f\u54c1\u6ce8\u518c"},{id:"1026003",name:"\u533b\u836f\u7814\u53d1\u2022\u5316\u5b66\u5236\u5242\u7814\u53d1"},{id:"1026015",name:"\u836f\u54c1\u751f\u4ea7\u2022\u8d28\u91cf\u7ba1\u7406"},{id:"1026014",name:"\u836f\u54c1\u9500\u552e\u2022\u63a8\u5e7f\u2022\u4e1a\u52a1\u54a8\u8be2"},{id:"1026017",name:"\u533b\u836f\u62db\u5546"},{id:"1026016",name:"\u5316\u5de5\u6280\u672f"},{id:"1026018",name:"\u6d82\u6599\u5f00\u53d1\u5de5\u7a0b\u5e08"},{id:"1026019",name:"\u5851\u6599\u5236\u54c1\u7814\u53d1"},{id:"1026020",name:"\u65e5\u7528\u5316\u5de5\u4ea7\u54c1\u7814\u53d1"},{id:"1026013",name:"\u73af\u4fdd\u6280\u672f"},{id:"1026999",name:"\u5176\u4ed6\u804c\u4f4d(\u751f\u7269\u2022\u5236\u836f\u2022\u5316\u5de5\u2022\u73af\u4fdd\u7c7b)"}]},{id:"3500",name:"\u6587\u4f53\u2022\u5f71\u89c6\u2022\u5199\u4f5c\u2022\u5a92\u4f53\u7c7b",subItems:[{id:"3500",name:"\u6587\u4f53\u2022\u5f71\u89c6\u2022\u5199\u4f5c\u2022\u5a92\u4f53\u7c7b"},{id:"1022001",name:"\u4f5c\u5bb6\u2022\u64b0\u7a3f\u4eba"},{id:"1022002",name:"\u603b\u7f16\u2022\u526f\u603b\u7f16"},{id:"1600",name:"\u7f16\u8f91\u2022\u8bb0\u8005"},{id:"1022003",name:"\u7f8e\u672f\u7f16\u8f91"},{id:"1022004",name:"\u53d1\u884c\u603b\u76d1\u2022\u7ecf\u7406\u2022\u4e3b\u7ba1"},{id:"1022005",name:"\u51fa\u7248"},{id:"1022006",name:"\u6821\u5bf9\u2022\u5f55\u5165"},{id:"1022007",name:"\u6392\u7248\u8bbe\u8ba1"},{id:"1022008",name:"\u827a\u672f\u603b\u76d1\u2022\u8bbe\u8ba1\u603b\u76d1"},{id:"1022009",name:"\u5f71\u89c6\u7b56\u5212\u2022\u5f71\u89c6\u5236\u4f5c"},{id:"1022010",name:"\u5bfc\u6f14\u2022\u7f16\u5bfc"},{id:"1022011",name:"\u6444\u5f71\u2022\u6444\u50cf"},{id:"1022012",name:"\u5f55\u97f3\u2022\u97f3\u6548\u5e08"},{id:"1022013",name:"\u5316\u5986\u5e08\u2022\u9020\u578b\u5e08"},{id:"1022014",name:"\u6f14\u5458\u2022\u914d\u97f3\u2022\u6a21\u7279"},{id:"1022015",name:"\u4e3b\u6301\u4eba\u2022\u64ad\u97f3\u5458\u2022DJ"},{id:"1022016",name:"\u6f14\u827a\u6216\u4f53\u80b2\u7ecf\u7eaa\u4eba"},{id:"1022999",name:"\u5176\u4ed6\u804c\u4f4d(\u6587\u4f53\u2022\u5f71\u89c6\u2022\u5199\u4f5c\u2022\u5a92\u4f53\u7c7b)"}]},{id:"2500",name:"\u5728\u6821\u5b66\u751f\u7c7b",subItems:[{id:"2500",name:"\u5728\u6821\u5b66\u751f\u7c7b"},{id:"1032001",name:"\u5e94\u5c4a\u6bd5\u4e1a\u751f"},{id:"1032002",name:"\u975e\u5e94\u5c4a\u6bd5\u4e1a\u751f"},{id:"1032003",name:"\u5b9e\u4e60\u751f"},{id:"1032999",name:"\u5176\u4ed6(\u5728\u6821\u5b66\u751f\u7c7b)"}]},{id:"3420",name:"\u673a\u68b0\u2022\u4eea\u5668\u4eea\u8868\u7c7b",subItems:[{id:"3420",name:"\u673a\u68b0\u2022\u4eea\u5668\u4eea\u8868\u7c7b"},{id:"1004001",name:"\u673a\u68b0\u5de5\u7a0b\u5e08"},{id:"1004002",name:"\u6a21\u5177\u5de5\u7a0b\u5e08"},{id:"1004003",name:"\u673a\u68b0\u8bbe\u8ba1\u5e08"},{id:"1004004",name:"\u673a\u68b0\u5236\u56fe\u5458"},{id:"2800",name:"\u673a\u7535\u5de5\u7a0b\u5e08"},{id:"2900",name:"\u7cbe\u5bc6\u673a\u68b0\u2022\u4eea\u5668\u4eea\u8868\u5de5\u7a0b\u5e08\u2022\u6280\u672f\u5458"},{id:"1004005",name:"\u94f8\u9020\u2022\u953b\u9020\u5de5\u7a0b\u5e08"},{id:"1004006",name:"\u6ce8\u5851\u5de5\u7a0b\u5e08"},{id:"1004007",name:"CNC\u5de5\u7a0b\u5e08"},{id:"1004008",name:"\u51b2\u538b\u5de5\u7a0b\u5e08"},{id:"1004009",name:"\u5939\u5177\u5de5\u7a0b\u5e08"},{id:"1004010",name:"\u9505\u7089\u5de5\u7a0b\u5e08"},{id:"1004011",name:"\u710a\u63a5\u5de5\u7a0b\u5e08"},{id:"1004012",name:"\u6c7d\u8f66\u2022\u6469\u6258\u8f66\u5de5\u7a0b\u5e08"},{id:"1004013",name:"\u8239\u8236\u5de5\u7a0b\u5e08"},{id:"1004014",name:"\u98de\u884c\u5668\u8bbe\u8ba1\u4e0e\u5236\u9020"},{id:"1004015",name:"\u673a\u68b0\u7ef4\u4fee\u5de5\u7a0b\u5e08"},{id:"1004016",name:"\u5305\u88c5\u2022\u5370\u5237\u673a\u68b0"},{id:"1004017",name:"\u98df\u54c1\u673a\u68b0"},{id:"1004018",name:"\u7eba\u7ec7\u673a\u68b0"},{id:"1004019",name:"\u8bbe\u5907\u4fee\u7406"},{id:"1004999",name:"\u5176\u4ed6\u804c\u4f4d(\u673a\u68b0\u2022\u4eea\u5668\u4eea\u8868\u7c7b)"}]},{id:"800",name:"\u79d1\u7814\u7c7b",subItems:[{id:"800",name:"\u79d1\u7814\u7c7b"},{id:"1027001",name:"\u79d1\u7814\u7ba1\u7406\u4eba\u5458"},{id:"1027002",name:"\u79d1\u7814\u4eba\u5458"},{id:"1027999",name:"\u5176\u4ed6\u804c\u4f4d(\u79d1\u7814\u7c7b)"}]},{id:"3400",name:"\u5de5\u5382\u751f\u4ea7\u7c7b",subItems:[{id:"3400",name:"\u5de5\u5382\u751f\u4ea7\u7c7b"},{id:"1018001",name:"\u5382\u957f\u2022\u526f\u5382\u957f"},{id:"1018002",name:"\u603b\u5de5\u7a0b\u5e08\u2022\u526f\u603b\u5de5\u7a0b\u5e08"},{id:"1018010",name:"\u6280\u672f\u5de5\u7a0b\u5e08"},{id:"1018003",name:"\u91c7\u8d2d\u7ba1\u7406"},{id:"1018004",name:"\u7269\u6599\u6216\u7269\u6d41\u7ba1\u7406"},{id:"1018005",name:"\u5de5\u7a0b\u6216\u8bbe\u5907\u7ba1\u7406"},{id:"1018006",name:"\u5b89\u5168\u2022\u5065\u5eb7\u2022\u73af\u5883\u7ba1\u7406"},{id:"1018007",name:"\u4ea7\u54c1\u5f00\u53d1"},{id:"1018011",name:"\u6280\u672f\u6216\u5de5\u827a\u8bbe\u8ba1\u7ecf\u7406"},{id:"1018012",name:"\u8d28\u91cf\u7ba1\u7406(QA\u2022QC)"},{id:"1018013",name:"\u5316\u9a8c\u2022\u68c0\u9a8c"},{id:"3430",name:"\u4ed3\u5e93\u7ba1\u7406"},{id:"1018009",name:"\u7ef4\u4fee\u5de5\u7a0b\u5e08"},{id:"1018014",name:"\u751f\u4ea7\u7ecf\u7406\u2022\u8f66\u95f4\u4e3b\u4efb"},{id:"1018015",name:"\u7ec4\u957f\u2022\u62c9\u957f"},{id:"1018016",name:"\u8ba1\u5212\u2022\u8c03\u5ea6"},{id:"1018017",name:"\u4ea7\u54c1\u6216\u751f\u4ea7\u5de5\u827a\u5de5\u7a0b\u5e08(PE\u2022ME)"},{id:"1018018",name:"\u5de5\u4e1a\u5de5\u7a0b\u5e08\uff08IE\uff09"},{id:"1018019",name:"\u5236\u9020\u5de5\u7a0b\u5e08"},{id:"1018020",name:"\u751f\u4ea7\u6587\u5458"},{id:"1018999",name:"\u5176\u4ed6\u804c\u4f4d(\u5de5\u5382\u751f\u4ea7\u7c7b)"}]},{id:"2600",name:"\u516c\u52a1\u5458\u7c7b",subItems:[{id:"2600",name:"\u516c\u52a1\u5458\u7c7b"}]},{id:"2000",name:"\u533b\u7597\u536b\u751f\u2022\u7f8e\u5bb9\u4fdd\u5065\u7c7b",subItems:[{id:"2000",name:"\u533b\u7597\u536b\u751f\u2022\u7f8e\u5bb9\u4fdd\u5065\u7c7b"},{id:"1025001",name:"\u533b\u7597\u7ba1\u7406\u4eba\u5458"},{id:"1025002",name:"\u533b\u7597\u6280\u672f\u4eba\u5458"},{id:"1025003",name:"\u533b\u751f\u2022\u533b\u5e08"},{id:"1025018",name:"\u4e2d\u533b"},{id:"1025004",name:"\u5fc3\u7406\u533b\u751f"},{id:"1025005",name:"\u533b\u836f\u68c0\u9a8c"},{id:"1025006",name:"\u62a4\u58eb\u2022\u62a4\u7406\u4eba\u5458"},{id:"1025019",name:"\u8425\u517b\u5e08"},{id:"1025017",name:"\u836f\u5b66\u6280\u672f\u4e0e\u7ba1\u7406\u4eba\u5458"},{id:"1025008",name:"\u75be\u75c5\u63a7\u5236\u2022\u516c\u5171\u536b\u751f"},{id:"1025009",name:"\u7f8e\u5bb9\u2022\u6574\u5f62\u5e08"},{id:"1025010",name:"\u517d\u533b\u2022\u5ba0\u7269\u533b\u751f"},{id:"1025011",name:"\u836f\u5e93\u4e3b\u4efb\u2022\u836f\u5242\u5e08"},{id:"1025013",name:"\u9488\u7078\u63a8\u62ff"},{id:"1025014",name:"\u836f\u54c1\u6ce8\u518c"},{id:"1025015",name:"\u5065\u8eab\u987e\u95ee\u2022\u6559\u7ec3"},{id:"1025016",name:"\u533b\u836f\u4ee3\u8868"},{id:"1025020",name:"\u533b\u7597\u5668\u68b0\u9500\u552e"},{id:"1025999",name:"\u5176\u4ed6\u804c\u4f4d(\u533b\u7597\u536b\u751f\u2022\u7f8e\u5bb9\u4fdd\u5065\u7c7b)"}]},{id:"3700",name:"\u57f9\u8bad\u751f\u7c7b",subItems:[{id:"3700",name:"\u57f9\u8bad\u751f\u7c7b"}]},{id:"2700",name:"\u7535\u6c14\u2022\u80fd\u6e90\u2022\u52a8\u529b\u7c7b",subItems:[{id:"2700",name:"\u7535\u6c14\u2022\u80fd\u6e90\u2022\u52a8\u529b\u7c7b"},{id:"1003001",name:"\u7535\u6c14\u5de5\u7a0b\u5e08"},{id:"1003002",name:"\u5149\u6e90\u4e0e\u7167\u660e\u5de5\u7a0b"},{id:"1003003",name:"\u53d8\u538b\u5668\u4e0e\u78c1\u7535\u5de5\u7a0b\u5e08"},{id:"1003004",name:"\u7535\u8def\u5de5\u7a0b\u5e08"},{id:"2720",name:"\u667a\u80fd\u5927\u53a6\u2022\u7efc\u5408\u5e03\u7ebf\u2022\u5f31\u7535"},{id:"1003005",name:"\u7535\u529b\u5de5\u7a0b\u5e08"},{id:"1003006",name:"\u7535\u6c14\u7ef4\u4fee\u6280\u672f\u5458"},{id:"1003007",name:"\u6c34\u5229\u2022\u6c34\u7535\u5de5\u7a0b\u5e08"},{id:"1003008",name:"\u6838\u529b\u2022\u706b\u529b\u5de5\u7a0b\u5e08"},{id:"1003013",name:"\u71c3\u6c14\u8f6e\u673a\u5de5\u7a0b\u5e08"},{id:"1003009",name:"\u7a7a\u8c03\u2022\u70ed\u80fd\u5de5\u7a0b\u5e08"},{id:"1003010",name:"\u77f3\u6cb9\u5929\u7136\u6c14\u6280\u672f\u4eba\u5458"},{id:"1003011",name:"\u81ea\u52a8\u63a7\u5236"},{id:"1003012",name:"\u5236\u51b7\u2022\u6696\u901a"},{id:"1003999",name:"\u5176\u4ed6\u804c\u4f4d(\u7535\u6c14\u2022\u80fd\u6e90\u2022\u52a8\u529b\u7c7b)"}]},{id:"1033000",name:"\u5176\u4ed6\u7c7b",subItems:[{id:"1033000",name:"\u5176\u4ed6\u7c7b"},{id:"1033001",name:"\u822a\u7a7a\u822a\u5929"},{id:"1033002",name:"\u5b89\u5168\u6d88\u9632"},{id:"1033003",name:"\u58f0\u5149\u5b66\u6280\u672f\u2022\u6fc0\u5149\u6280\u672f"},{id:"1033004",name:"\u6d4b\u7ed8\u6280\u672f"},{id:"1033005",name:"\u5730\u8d28\u77ff\u4ea7\u51b6\u91d1"},{id:"1033006",name:"\u6c14\u8c61"},{id:"1033007",name:"\u519c\u6797\u7267\u6e14"},{id:"1033999",name:"\u5176\u4ed6\u7c7b\u522b\u804c\u4f4d(\u5176\u4ed6\u7c7b)"}]}]}function getLoc(){return[{id:"30000",name:"\u5317\u4eac\u5e02",subItems:[{id:"300001",name:"\u4e1c\u57ce\u533a"},{id:"300002",name:"\u897f\u57ce\u533a"},{id:"300003",name:"\u5d07\u6587\u533a"},{id:"300004",name:"\u5ba3\u6b66\u533a"},{id:"300005",name:"\u671d\u9633\u533a"},{id:"300006",name:"\u4e30\u53f0\u533a"},{id:"300007",name:"\u77f3\u666f\u5c71\u533a"},{id:"300008",name:"\u6d77\u6dc0\u533a"},{id:"300009",name:"\u95e8\u5934\u6c9f\u533a"},{id:"300010",name:"\u623f\u5c71\u533a"},{id:"300011",name:"\u901a\u5dde\u533a"},{id:"300012",name:"\u987a\u4e49\u533a"},{id:"300013",name:"\u660c\u5e73\u533a"},{id:"300014",name:"\u5927\u5174\u533a"},{id:"300015",name:"\u6000\u67d4\u533a"},{id:"300016",name:"\u5e73\u8c37\u533a"},{id:"300017",name:"\u5bc6\u4e91\u53bf"},{id:"300018",name:"\u5ef6\u5e86\u53bf"}]},{id:"31000",name:"\u4e0a\u6d77\u5e02",subItems:[{id:"3100001",name:"\u9ec4\u6d66\u533a"},{id:"3100002",name:"\u5362\u6e7e\u533a"},{id:"3100003",name:"\u5f90\u6c47\u533a"},{id:"3100004",name:"\u957f\u5b81\u533a"},{id:"3100005",name:"\u9759\u5b89\u533a"},{id:"3100006",name:"\u666e\u9640\u533a"},{id:"3100007",name:"\u95f8\u5317\u533a"},{id:"3100008",name:"\u8679\u53e3\u533a"},{id:"3100009",name:"\u6768\u6d66\u533a"},{id:"3100010",name:"\u95f5\u884c\u533a"},{id:"3100011",name:"\u5b9d\u5c71\u533a"},{id:"3100012",name:"\u5609\u5b9a\u533a"},{id:"3100013",name:"\u6d66\u4e1c\u65b0\u533a"},{id:"3100014",name:"\u91d1\u5c71\u533a"},{id:"3100015",name:"\u677e\u6c5f\u533a"},{id:"3100016",name:"\u9752\u6d66\u533a"},{id:"3100017",name:"\u5949\u8d24\u533a"},{id:"3100018",name:"\u5d07\u660e\u53bf"}]},{id:"32000",name:"\u5929\u6d25\u5e02",subItems:[{id:"14001",name:"\u548c\u5e73\u533a"},{id:"14002",name:"\u6cb3\u4e1c\u533a"},{id:"14003",name:"\u6cb3\u897f\u533a"},{id:"14004",name:"\u5357\u5f00\u533a"},{id:"14005",name:"\u6cb3\u5317\u533a"},{id:"14006",name:"\u7ea2\u6865\u533a"},{id:"14007",name:"\u4e1c\u4e3d\u533a"},{id:"14008",name:"\u897f\u9752\u533a"},{id:"14009",name:"\u6d25\u5357\u533a"},{id:"14010",name:"\u5317\u8fb0\u533a"},{id:"14011",name:"\u6b66\u6e05\u533a"},{id:"14012",name:"\u5b9d\u577b\u533a"},{id:"14013",name:"\u6ee8\u6d77\u65b0\u533a"},{id:"14014",name:"\u5b81\u6cb3\u53bf"},{id:"14015",name:"\u9759\u6d77\u53bf"},{id:"14016",name:"\u84df\u53bf"}]},{id:"33000",name:"\u91cd\u5e86\u5e02",subItems:[{id:"330001",name:"\u6e1d\u4e2d\u533a"},{id:"330005",name:"\u5927\u6e21\u53e3\u533a"},{id:"330009",name:"\u6c5f\u5317\u533a"},{id:"330013",name:"\u6c99\u576a\u575d\u533a"},{id:"330002",name:"\u53cc\u6865\u533a"},{id:"330003",name:"\u5408\u5ddd\u533a"},{id:"330006",name:"\u6e1d\u5317\u533a"},{id:"330007",name:"\u6c38\u5ddd\u533a"},{id:"330010",name:"\u5df4\u5357\u533a"},{id:"330011",name:"\u5357\u5ddd\u533a"},{id:"330016",name:"\u4e5d\u9f99\u5761\u533a"},{id:"330014",name:"\u4e07\u5dde\u533a"},{id:"330017",name:"\u6daa\u9675\u533a"},{id:"330020",name:"\u9ed4\u6c5f\u533a"},{id:"330019",name:"\u5357\u5cb8\u533a"},{id:"330022",name:"\u5317\u789a\u533a"},{id:"330023",name:"\u957f\u5bff\u533a"},{id:"330037",name:"\u4e07\u76db\u533a"},{id:"330038",name:"\u6c5f\u6d25\u533a"},{id:"330004",name:"\u7da6\u6c5f\u53bf"},{id:"330008",name:"\u6f7c\u5357\u53bf"},{id:"330012",name:"\u94dc\u6881\u53bf"},{id:"330015",name:"\u5927\u8db3\u53bf"},{id:"330018",name:"\u8363\u660c\u53bf"},{id:"330021",name:"\u74a7\u5c71\u53bf"},{id:"330024",name:"\u57ab\u6c5f\u53bf"},{id:"330025",name:"\u4e30\u90fd\u53bf"},{id:"330026",name:"\u5fe0\u53bf"},{id:"330027",name:"\u77f3\u67f1\u53bf"},{id:"330028",name:"\u57ce\u53e3\u53bf"},{id:"330029",name:"\u5f6d\u6c34\u53bf"},{id:"330030",name:"\u6881\u5e73\u53bf"},{id:"330031",name:"\u9149\u9633\u53bf"},{id:"330032",name:"\u5f00\u53bf"},{id:"330033",name:"\u79c0\u5c71\u53bf"},{id:"330034",name:"\u5deb\u6eaa\u53bf"},{id:"330035",name:"\u5deb\u5c71\u53bf"},{id:"330036",name:"\u5949\u8282\u53bf"},{id:"330039",name:"\u6b66\u9686\u53bf"},{id:"330040",name:"\u4e91\u9633\u53bf"}]},{id:"16000",name:"\u5e7f\u4e1c\u7701",subItems:[{id:"40",name:"\u5e7f\u5dde\u5e02",subItems:[{id:"4001",name:"\u8d8a\u79c0\u533a"},{id:"4002",name:"\u82b1\u90fd\u533a"},{id:"4003",name:"\u8354\u6e7e\u533a"},{id:"4004",name:"\u5357\u6c99\u533a"},{id:"4005",name:"\u6d77\u73e0\u533a"},{id:"4006",name:"\u841d\u5c97\u533a"},{id:"4007",name:"\u5929\u6cb3\u533a"},{id:"4009",name:"\u767d\u4e91\u533a"},{id:"4011",name:"\u9ec4\u57d4\u533a"},{id:"4012",name:"\u756a\u79ba\u533a"},{id:"4008",name:"\u589e\u57ce\u5e02"},{id:"4013",name:"\u4ece\u5316\u5e02"}]},{id:"16010",name:"\u6f6e\u5dde\u5e02",subItems:[{id:"1601001",name:"\u6e58\u6865\u533a"},{id:"1601002",name:"\u6f6e\u5b89\u53bf"},{id:"1601003",name:"\u9976\u5e73\u53bf"}]},{id:"225",name:"\u4e1c\u839e\u5e02"},{id:"16050",name:"\u4f5b\u5c71\u5e02",subItems:[{id:"1605001",name:"\u7985\u57ce\u533a"},{id:"1605002",name:"\u5357\u6d77\u533a"},{id:"1605003",name:"\u4e09\u6c34\u533a"},{id:"1605004",name:"\u9ad8\u660e\u533a"},{id:"16040",name:"\u987a\u5fb7\u533a"}]},{id:"16020",name:"\u60e0\u5dde\u5e02",subItems:[{id:"1602001",name:"\u60e0\u57ce\u533a"},{id:"1602002",name:"\u60e0\u9633\u533a"},{id:"1602003",name:"\u535a\u7f57\u53bf"},{id:"1602004",name:"\u60e0\u4e1c\u53bf"},{id:"1602005",name:"\u9f99\u95e8\u53bf"}]},{id:"16030",name:"\u6e05\u8fdc\u5e02",subItems:[{id:"1603001",name:"\u6e05\u57ce\u533a"},{id:"1603003",name:"\u82f1\u5fb7\u5e02"},{id:"1603004",name:"\u8fde\u5dde\u5e02"},{id:"1603002",name:"\u8fde\u5357\u53bf"},{id:"1603005",name:"\u4f5b\u5188\u53bf"},{id:"1603006",name:"\u9633\u5c71\u53bf"},{id:"1603007",name:"\u6e05\u65b0\u53bf"},{id:"1603008",name:"\u8fde\u5c71\u53bf"}]},{id:"117",name:"\u6c55\u5934\u5e02",subItems:[{id:"11701",name:"\u91d1\u5e73\u533a"},{id:"11702",name:"\u9f99\u6e56\u533a"},{id:"11703",name:"\u6fe0\u6c5f\u533a"},{id:"11704",name:"\u6f6e\u9633\u533a"},{id:"11705",name:"\u6f6e\u5357\u533a"},{id:"11706",name:"\u6f84\u6d77\u533a"},{id:"11707",name:"\u5357\u6fb3\u53bf"}]},{id:"125",name:"\u6df1\u5733\u5e02",subItems:[{id:"12501",name:"\u798f\u7530\u533a"},{id:"12502",name:"\u7f57\u6e56\u533a"},{id:"12503",name:"\u5357\u5c71\u533a"},{id:"12504",name:"\u5b9d\u5b89\u533a"},{id:"12505",name:"\u9f99\u5c97\u533a"},{id:"12506",name:"\u76d0\u7530\u533a"}]},{id:"16060",name:"\u6e5b\u6c5f\u5e02",subItems:[{id:"1606001",name:"\u8d64\u574e\u533a"},{id:"1606003",name:"\u971e\u5c71\u533a"},{id:"1606005",name:"\u5761\u5934\u533a"},{id:"1606006",name:"\u9ebb\u7ae0\u533a"},{id:"1606007",name:"\u5ec9\u6c5f\u5e02"},{id:"1606008",name:"\u96f7\u5dde\u5e02"},{id:"1606009",name:"\u5434\u5ddd\u5e02"},{id:"1606002",name:"\u9042\u6eaa\u53bf"},{id:"1606004",name:"\u5f90\u95fb\u53bf"}]},{id:"16080",name:"\u8087\u5e86\u5e02",subItems:[{id:"1608001",name:"\u7aef\u5dde\u533a"},{id:"1608003",name:"\u9f0e\u6e56\u533a"},{id:"1608004",name:"\u9ad8\u8981\u5e02"},{id:"1608005",name:"\u56db\u4f1a\u5e02"},{id:"1608002",name:"\u5fb7\u5e86\u53bf"},{id:"1608006",name:"\u5e7f\u5b81\u53bf"},{id:"1608007",name:"\u6000\u96c6\u53bf"},{id:"1608008",name:"\u5c01\u5f00\u53bf"}]},{id:"16070",name:"\u4e2d\u5c71\u5e02"},{id:"180",name:"\u73e0\u6d77\u5e02",subItems:[{id:"18001",name:"\u9999\u6d32\u533a"},{id:"18002",name:"\u6597\u95e8\u533a"},{id:"18003",name:"\u91d1\u6e7e\u533a"}]},{id:"16090",name:"\u6c5f\u95e8\u5e02",subItems:[{id:"1609001",name:"\u6c5f\u6d77\u533a"},{id:"1609002",name:"\u84ec\u6c5f\u533a"},{id:"1609003",name:"\u65b0\u4f1a\u533a"},{id:"1609004",name:"\u53f0\u5c71\u5e02"},{id:"1609005",name:"\u9e64\u5c71\u5e02"},{id:"1609006",name:"\u6069\u5e73\u5e02"},{id:"16150",name:"\u5f00\u5e73\u5e02"}]},{id:"16100",name:"\u6c55\u5c3e\u5e02",subItems:[{id:"1610001",name:"\u6c55\u5c3e\u57ce\u533a"},{id:"1610002",name:"\u9646\u4e30\u5e02"},{id:"1610003",name:"\u6d77\u4e30\u53bf"},{id:"1610004",name:"\u9646\u6cb3\u53bf"}]},{id:"16110",name:"\u97f6\u5173\u5e02",subItems:[{id:"1611001",name:"\u6d48\u6c5f\u533a"},{id:"1611003",name:"\u6b66\u6c5f\u533a"},{id:"1611005",name:"\u66f2\u6c5f\u533a"},{id:"1611007",name:"\u4e50\u660c\u5e02"},{id:"1611008",name:"\u5357\u96c4\u5e02"},{id:"1611002",name:"\u7fc1\u6e90\u53bf"},{id:"1611004",name:"\u65b0\u4e30\u53bf"},{id:"1611006",name:"\u4e73\u6e90\u53bf"},{id:"1611009",name:"\u59cb\u5174\u53bf"},{id:"1611010",name:"\u4ec1\u5316\u53bf"}]},{id:"16120",name:"\u8302\u540d\u5e02",subItems:[{id:"1612001",name:"\u8302\u5357\u533a"},{id:"1612002",name:"\u8302\u6e2f\u533a"},{id:"1612003",name:"\u9ad8\u5dde\u5e02"},{id:"1612004",name:"\u5316\u5dde\u5e02"},{id:"1612005",name:"\u4fe1\u5b9c\u5e02"},{id:"1612006",name:"\u7535\u767d\u53bf"}]},{id:"16130",name:"\u6cb3\u6e90\u5e02",subItems:[{id:"1613001",name:"\u6e90\u57ce\u533a"},{id:"1613002",name:"\u7d2b\u91d1\u53bf"},{id:"1613003",name:"\u9f99\u5ddd\u53bf"},{id:"1613004",name:"\u8fde\u5e73\u53bf"},{id:"1613005",name:"\u548c\u5e73\u53bf"},{id:"1613006",name:"\u4e1c\u6e90\u53bf"}]},{id:"16140",name:"\u6885\u5dde\u5e02",subItems:[{id:"1614001",name:"\u6885\u6c5f\u533a"},{id:"1614003",name:"\u5174\u5b81\u5e02"},{id:"1614002",name:"\u8549\u5cad\u53bf"},{id:"1614004",name:"\u6885\u53bf"},{id:"1614005",name:"\u5927\u57d4\u53bf"},{id:"1614006",name:"\u4e30\u987a\u53bf"},{id:"1614007",name:"\u4e94\u534e\u53bf"},{id:"1614008",name:"\u5e73\u8fdc\u53bf"}]},{id:"16160",name:"\u9633\u6c5f\u5e02",subItems:[{id:"1616001",name:"\u6c5f\u57ce\u533a"},{id:"1616002",name:"\u9633\u6625\u5e02"},{id:"1616003",name:"\u9633\u897f\u53bf"},{id:"1616004",name:"\u9633\u4e1c\u53bf"}]},{id:"16170",name:"\u63ed\u9633\u5e02",subItems:[{id:"1617001",name:"\u6995\u57ce\u533a"},{id:"1617002",name:"\u666e\u5b81\u5e02"},{id:"1617003",name:"\u63ed\u4e1c\u53bf"},{id:"1617004",name:"\u63ed\u897f\u53bf"},{id:"1617005",name:"\u60e0\u6765\u53bf"}]},{id:"16180",name:"\u4e91\u6d6e\u5e02",subItems:[{id:"1618001",name:"\u4e91\u57ce\u533a"},{id:"1618002",name:"\u7f57\u5b9a\u5e02"},{id:"1618003",name:"\u65b0\u5174\u53bf"},{id:"1618004",name:"\u90c1\u5357\u53bf"},{id:"1618005",name:"\u4e91\u5b89\u53bf"}]}]},{id:"7000",name:"\u6c5f\u82cf\u7701",subItems:[{id:"100",name:"\u5357\u4eac\u5e02",subItems:[{id:"10001",name:"\u7384\u6b66\u533a"},{id:"10002",name:"\u6816\u971e\u533a"},{id:"10003",name:"\u767d\u4e0b\u533a"},{id:"10004",name:"\u96e8\u82b1\u53f0\u533a"},{id:"10005",name:"\u79e6\u6dee\u533a"},{id:"10006",name:"\u6c5f\u5b81\u533a"},{id:"10007",name:"\u5efa\u90ba\u533a"},{id:"10008",name:"\u516d\u5408\u533a"},{id:"10009",name:"\u9f13\u697c\u533a"},{id:"10011",name:"\u4e0b\u5173\u533a"},{id:"10012",name:"\u9ad8\u6df3\u53bf"},{id:"10013",name:"\u6d66\u53e3\u533a"},{id:"10014",name:"\u6ea7\u6c34\u53bf"}]},{id:"18",name:"\u5e38\u5dde\u5e02",subItems:[{id:"1801",name:"\u5929\u5b81\u533a"},{id:"1802",name:"\u949f\u697c\u533a"},{id:"1803",name:"\u621a\u5885\u5830\u533a"},{id:"1804",name:"\u65b0\u5317\u533a"},{id:"1805",name:"\u6b66\u8fdb\u533a"},{id:"7180",name:"\u6ea7\u9633"},{id:"1806",name:"\u91d1\u575b\u5e02"}]},{id:"7070",name:"\u8fde\u4e91\u6e2f\u5e02",subItems:[{id:"707001",name:"\u65b0\u6d66\u533a"},{id:"707002",name:"\u8fde\u4e91\u533a"},{id:"707003",name:"\u6d77\u5dde\u533a"},{id:"707004",name:"\u8d63\u6986\u53bf"},{id:"707005",name:"\u4e1c\u6d77\u53bf"},{id:"707006",name:"\u704c\u4e91\u53bf"},{id:"707007",name:"\u704c\u5357\u53bf"}]},{id:"7060",name:"\u5357\u901a\u5e02",subItems:[{id:"706001",name:"\u5d07\u5ddd\u533a"},{id:"706003",name:"\u6e2f\u95f8\u533a"},{id:"706002",name:"\u6d77\u95e8\u5e02"},{id:"706006",name:"\u542f\u4e1c\u5e02"},{id:"706007",name:"\u5982\u768b\u5e02"},{id:"706008",name:"\u901a\u5dde\u5e02"},{id:"706004",name:"\u6d77\u5b89\u53bf"},{id:"706005",name:"\u5982\u4e1c\u53bf"}]},{id:"220",name:"\u82cf\u5dde\u5e02",subItems:[{id:"22001",name:"\u6ca7\u6d6a\u533a"},{id:"22002",name:"\u5e73\u6c5f\u533a"},{id:"22003",name:"\u91d1\u960a\u533a"},{id:"22004",name:"\u864e\u4e18\u533a"},{id:"22005",name:"\u5434\u4e2d\u533a"},{id:"22006",name:"\u76f8\u57ce\u533a"},{id:"7010",name:"\u5e38\u719f\u5e02"},{id:"7110",name:"\u5f20\u5bb6\u6e2f\u5e02"},{id:"7100",name:"\u5434\u6c5f\u5e02"},{id:"7020",name:"\u6606\u5c71\u5e02"},{id:"7040",name:"\u592a\u4ed3\u5e02"}]},{id:"152",name:"\u65e0\u9521\u5e02",subItems:[{id:"15201",name:"\u5d07\u5b89\u533a"},{id:"15202",name:"\u5357\u957f\u533a"},{id:"15203",name:"\u5317\u5858\u533a"},{id:"15204",name:"\u6ee8\u6e56\u533a"},{id:"15205",name:"\u9521\u5c71\u533a"},{id:"15206",name:"\u60e0\u5c71\u533a"},{id:"7190",name:"\u5b9c\u5174\u5e02"},{id:"7130",name:"\u6c5f\u9634\u5e02"}]},{id:"7030",name:"\u5f90\u5dde\u5e02",subItems:[{id:"703001",name:"\u9f13\u697c\u533a"},{id:"703003",name:"\u4e91\u9f99\u533a"},{id:"703005",name:"\u4e5d\u91cc\u533a"},{id:"703009",name:"\u6cc9\u5c71\u533a"},{id:"703007",name:"\u8d3e\u6c6a\u533a"},{id:"703006",name:"\u65b0\u6c82\u5e02"},{id:"703008",name:"\u90b3\u5dde\u5e02"},{id:"703004",name:"\u7762\u5b81\u53bf"},{id:"703002",name:"\u94dc\u5c71\u53bf"},{id:"703010",name:"\u4e30\u53bf"},{id:"703011",name:"\u6c9b\u53bf"}]},{id:"167",name:"\u626c\u5dde\u5e02",subItems:[{id:"16701",name:"\u5e7f\u9675\u533a"},{id:"16702",name:"\u9097\u6c5f\u533a"},{id:"16703",name:"\u7ef4\u626c\u533a"},{id:"16705",name:"\u4eea\u5f81\u5e02"},{id:"16706",name:"\u9ad8\u90ae\u5e02"},{id:"16707",name:"\u6c5f\u90fd\u5e02"},{id:"16704",name:"\u5b9d\u5e94\u53bf"}]},{id:"7080",name:"\u9547\u6c5f\u5e02",subItems:[{id:"708001",name:"\u4eac\u53e3\u533a"},{id:"708002",name:"\u6da6\u5dde\u533a"},{id:"708003",name:"\u4e39\u5f92\u533a"},{id:"7140",name:"\u4e39\u9633\u5e02"},{id:"708004",name:"\u626c\u4e2d\u5e02"},{id:"708005",name:"\u53e5\u5bb9\u5e02"}]},{id:"7090",name:"\u76d0\u57ce\u5e02",subItems:[{id:"709001",name:"\u4ead\u6e56\u533a"},{id:"709003",name:"\u76d0\u90fd\u533a"},{id:"709002",name:"\u4e1c\u53f0\u5e02"},{id:"709004",name:"\u5927\u4e30\u5e02"},{id:"709005",name:"\u54cd\u6c34\u53bf"},{id:"709006",name:"\u6ee8\u6d77\u53bf"},{id:"709007",name:"\u961c\u5b81\u53bf"},{id:"709008",name:"\u5c04\u9633\u53bf"},{id:"709009",name:"\u5efa\u6e56\u53bf"}]},{id:"7120",name:"\u6cf0\u5dde\u5e02",subItems:[{id:"712001",name:"\u6d77\u9675\u533a"},{id:"712002",name:"\u9ad8\u6e2f\u533a"},{id:"712003",name:"\u5174\u5316\u5e02"},{id:"712004",name:"\u9756\u6c5f\u5e02"},{id:"712005",name:"\u59dc\u5830\u5e02"},{id:"7150",name:"\u6cf0\u5174\u5e02"}]},{id:"7160",name:"\u6dee\u5b89\u5e02",subItems:[{id:"716001",name:"\u6e05\u6cb3\u533a"},{id:"716003",name:"\u6e05\u6d66\u533a"},{id:"716004",name:"\u695a\u5dde\u533a"},{id:"716005",name:"\u6dee\u9634\u533a"},{id:"716002",name:"\u91d1\u6e56\u53bf"},{id:"716006",name:"\u6d9f\u6c34\u53bf"},{id:"716007",name:"\u6d2a\u6cfd\u53bf"},{id:"716008",name:"\u76f1\u7719\u53bf"}]},{id:"7170",name:"\u5bbf\u8fc1\u5e02",subItems:[{id:"717001",name:"\u5bbf\u57ce\u533a"},{id:"717002",name:"\u5bbf\u8c6b\u533a"},{id:"717003",name:"\u6cad\u9633\u53bf"},{id:"717004",name:"\u6cd7\u9633\u53bf"},{id:"717005",name:"\u6cd7\u6d2a\u53bf"}]}]},{id:"8000",name:"\u6d59\u6c5f\u7701",subItems:[{id:"55",name:"\u676d\u5dde\u5e02",subItems:[{id:"5501",name:"\u62f1\u5885\u533a"},{id:"5502",name:"\u4f59\u676d\u533a"},{id:"5503",name:"\u4e0a\u57ce\u533a"},{id:"5505",name:"\u4e0b\u57ce\u533a"},{id:"5507",name:"\u6c5f\u5e72\u533a"},{id:"5509",name:"\u897f\u6e56\u533a"},{id:"8130",name:"\u8427\u5c71\u533a"},{id:"5511",name:"\u6ee8\u6c5f\u533a"},{id:"5504",name:"\u5efa\u5fb7\u5e02"},{id:"5506",name:"\u5bcc\u9633\u5e02"},{id:"5508",name:"\u4e34\u5b89\u5e02"},{id:"5510",name:"\u6850\u5e90\u53bf"},{id:"5512",name:"\u6df3\u5b89\u53bf"}]},{id:"107",name:"\u5b81\u6ce2\u5e02",subItems:[{id:"10701",name:"\u6d77\u66d9\u533a"},{id:"10702",name:"\u6c5f\u4e1c\u533a"},{id:"10704",name:"\u6c5f\u5317\u533a"},{id:"10706",name:"\u5317\u4ed1\u533a"},{id:"10708",name:"\u9547\u6d77\u533a"},{id:"10709",name:"\u911e\u5dde\u533a"},{id:"10703",name:"\u5949\u5316\u5e02"},{id:"10710",name:"\u4f59\u59da\u5e02"},{id:"8120",name:"\u6148\u6eaa\u5e02"},{id:"10705",name:"\u8c61\u5c71\u53bf"},{id:"10707",name:"\u5b81\u6d77\u53bf"}]},{id:"147",name:"\u6e29\u5dde\u5e02",subItems:[{id:"14701",name:"\u9e7f\u57ce\u533a"},{id:"14703",name:"\u9f99\u6e7e\u533a"},{id:"14705",name:"\u74ef\u6d77\u533a"},{id:"14709",name:"\u4e50\u6e05\u5e02"},{id:"14707",name:"\u745e\u5b89\u5e02"},{id:"14704",name:"\u82cd\u5357\u53bf"},{id:"14702",name:"\u5e73\u9633\u53bf"},{id:"14706",name:"\u6587\u6210\u53bf"},{id:"14708",name:"\u6cf0\u987a\u53bf"},{id:"14710",name:"\u6d1e\u5934\u53bf"},{id:"14711",name:"\u6c38\u5609\u53bf"}]},{id:"8050",name:"\u7ecd\u5174\u5e02",subItems:[{id:"805001",name:"\u8d8a\u57ce\u533a"},{id:"805002",name:"\u8bf8\u66a8\u5e02"},{id:"805003",name:"\u4e0a\u865e\u5e02"},{id:"805004",name:"\u5d4a\u5dde\u5e02"},{id:"805005",name:"\u7ecd\u5174\u53bf"},{id:"805006",name:"\u65b0\u660c\u53bf"}]},{id:"8060",name:"\u91d1\u534e\u5e02",subItems:[{id:"806001",name:"\u5a7a\u57ce\u533a"},{id:"806003",name:"\u91d1\u4e1c\u533a"},{id:"806005",name:"\u5170\u6eaa\u5e02"},{id:"806006",name:"\u4e49\u4e4c\u5e02"},{id:"806007",name:"\u4e1c\u9633\u5e02"},{id:"806008",name:"\u6c38\u5eb7\u5e02"},{id:"806004",name:"\u78d0\u5b89\u53bf"},{id:"806002",name:"\u6d66\u6c5f\u53bf"},{id:"806009",name:"\u6b66\u4e49\u53bf"}]},{id:"8080",name:"\u53f0\u5dde\u5e02",subItems:[{id:"808001",name:"\u6912\u6c5f\u533a"},{id:"808003",name:"\u9ec4\u5ca9\u533a"},{id:"808005",name:"\u8def\u6865\u533a"},{id:"808006",name:"\u6e29\u5cad\u5e02"},{id:"808007",name:"\u4e34\u6d77\u5e02"},{id:"808002",name:"\u5929\u53f0\u53bf"},{id:"808004",name:"\u4ed9\u5c45\u53bf"},{id:"808008",name:"\u7389\u73af\u53bf"},{id:"808009",name:"\u4e09\u95e8\u53bf"}]},{id:"8090",name:"\u6e56\u5dde\u5e02",subItems:[{id:"809001",name:"\u5434\u5174\u533a"},{id:"809002",name:"\u5357\u6d54\u533a"},{id:"809003",name:"\u5fb7\u6e05\u53bf"},{id:"809004",name:"\u957f\u5174\u53bf"},{id:"809005",name:"\u5b89\u5409\u53bf"}]},{id:"73",name:"\u5609\u5174\u5e02",subItems:[{id:"7301",name:"\u5357\u6e56\u533a"},{id:"7302",name:"\u79c0\u6d32\u533a"},{id:"7303",name:"\u6d77\u5b81\u5e02"},{id:"7304",name:"\u5e73\u6e56\u5e02"},{id:"7305",name:"\u6850\u4e61\u5e02"},{id:"7306",name:"\u5609\u5584\u53bf"},{id:"7307",name:"\u6d77\u76d0\u53bf"}]},{id:"8110",name:"\u8862\u5dde\u5e02",subItems:[{id:"811001",name:"\u67ef\u57ce\u533a"},{id:"811002",name:"\u8862\u6c5f\u533a"},{id:"811003",name:"\u6c5f\u5c71\u5e02"},{id:"811004",name:"\u5e38\u5c71\u53bf"},{id:"811005",name:"\u5f00\u5316\u53bf"},{id:"811006",name:"\u9f99\u6e38\u53bf"}]},{id:"8100",name:"\u4e3d\u6c34\u5e02",subItems:[{id:"810001",name:"\u83b2\u90fd\u533a"},{id:"810003",name:"\u9f99\u6cc9\u5e02"},{id:"810002",name:"\u5e86\u5143\u53bf"},{id:"810004",name:"\u666f\u5b81\u53bf"},{id:"810005",name:"\u9752\u7530\u53bf"},{id:"810006",name:"\u7f19\u4e91\u53bf"},{id:"810007",name:"\u9042\u660c\u53bf"},{id:"810008",name:"\u677e\u9633\u53bf"},{id:"810009",name:"\u4e91\u548c\u53bf"}]},{id:"8070",name:"\u821f\u5c71\u5e02",subItems:[{id:"807001",name:"\u5b9a\u6d77\u533a"},{id:"807002",name:"\u666e\u9640\u533a"},{id:"807003",name:"\u5cb1\u5c71\u53bf"},{id:"807004",name:"\u5d4a\u6cd7\u53bf"}]}]},{id:"9000",name:"\u5b89\u5fbd\u7701",subItems:[{id:"65",name:"\u5408\u80a5\u5e02",subItems:[{id:"6501",name:"\u5e90\u9633\u533a"},{id:"6502",name:"\u7476\u6d77\u533a"},{id:"6503",name:"\u8700\u5c71\u533a"},{id:"6504",name:"\u5305\u6cb3\u533a"},{id:"6505",name:"\u957f\u4e30\u53bf"},{id:"6506",name:"\u80a5\u4e1c\u53bf"},{id:"6507",name:"\u80a5\u897f\u53bf"}]},{id:"9040",name:"\u5b89\u5e86\u5e02",subItems:[{id:"904001",name:"\u8fce\u6c5f\u533a"},{id:"904003",name:"\u5927\u89c2\u533a"},{id:"904005",name:"\u5b9c\u79c0\u533a"},{id:"904007",name:"\u6850\u57ce\u5e02"},{id:"904002",name:"\u6000\u5b81\u53bf"},{id:"904004",name:"\u5cb3\u897f\u53bf"},{id:"904006",name:"\u671b\u6c5f\u53bf"},{id:"904008",name:"\u6f5c\u5c71\u53bf"},{id:"904009",name:"\u5bbf\u677e\u53bf"},{id:"904010",name:"\u679e\u9633\u53bf"},{id:"904011",name:"\u592a\u6e56\u53bf"}]},{id:"9030",name:"\u868c\u57e0\u5e02",subItems:[{id:"903001",name:"\u868c\u5c71\u533a"},{id:"903002",name:"\u9f99\u5b50\u6e56\u533a"},{id:"903003",name:"\u79b9\u4f1a\u533a"},{id:"903004",name:"\u6dee\u4e0a\u533a"},{id:"903005",name:"\u6000\u8fdc\u53bf"},{id:"903006",name:"\u56fa\u9547\u53bf"},{id:"903007",name:"\u4e94\u6cb3\u53bf"}]},{id:"9020",name:"\u829c\u6e56\u5e02",subItems:[{id:"902001",name:"\u955c\u6e56\u533a"},{id:"902002",name:"\u5f0b\u6c5f\u533a"},{id:"902003",name:"\u9e20\u6c5f\u533a"},{id:"902004",name:"\u4e09\u5c71\u533a"},{id:"902005",name:"\u829c\u6e56\u53bf"},{id:"902006",name:"\u5357\u9675\u53bf"},{id:"902007",name:"\u7e41\u660c\u53bf"}]},{id:"9060",name:"\u6dee\u5357\u5e02",subItems:[{id:"906001",name:"\u7530\u5bb6\u5eb5\u533a"},{id:"906002",name:"\u5927\u901a\u533a"},{id:"906003",name:"\u8c22\u5bb6\u96c6\u533a"},{id:"906004",name:"\u516b\u516c\u5c71\u533a"},{id:"906005",name:"\u6f58\u96c6\u533a"},{id:"906006",name:"\u51e4\u53f0\u53bf"}]},{id:"9070",name:"\u9a6c\u978d\u5c71\u5e02",subItems:[{id:"907001",name:"\u96e8\u5c71\u533a"},{id:"907002",name:"\u82b1\u5c71\u533a"},{id:"907003",name:"\u91d1\u5bb6\u5e84\u533a"},{id:"907004",name:"\u5f53\u6d82\u53bf"}]},{id:"9080",name:"\u6dee\u5317\u5e02",subItems:[{id:"908001",name:"\u76f8\u5c71\u533a"},{id:"908002",name:"\u675c\u96c6\u533a"},{id:"908003",name:"\u70c8\u5c71\u533a"},{id:"908004",name:"\u6fc9\u6eaa\u53bf"}]},{id:"9090",name:"\u9ec4\u5c71\u5e02",subItems:[{id:"909001",name:"\u5c6f\u6eaa\u533a"},{id:"909002",name:"\u9ec4\u5c71\u533a"},{id:"909003",name:"\u5fbd\u5dde\u533a"},{id:"909004",name:"\u4f11\u5b81\u53bf"},{id:"909005",name:"\u6b59\u53bf"},{id:"909006",name:"\u7941\u95e8\u53bf"},{id:"909007",name:"\u9edf\u53bf"}]},{id:"9100",name:"\u6ec1\u5dde\u5e02",subItems:[{id:"910001",name:"\u7405\u740a\u533a"},{id:"910003",name:"\u5357\u8c2f\u533a"},{id:"910004",name:"\u5929\u957f\u5e02"},{id:"910005",name:"\u660e\u5149\u5e02"},{id:"910002",name:"\u51e4\u9633\u53bf"},{id:"910006",name:"\u5168\u6912\u53bf"},{id:"910007",name:"\u6765\u5b89\u53bf"},{id:"910008",name:"\u5b9a\u8fdc\u53bf"}]},{id:"9110",name:"\u961c\u9633\u5e02",subItems:[{id:"911001",name:"\u988d\u5dde\u533a"},{id:"911003",name:"\u988d\u4e1c\u533a"},{id:"911004",name:"\u988d\u6cc9\u533a"},{id:"911005",name:"\u754c\u9996\u5e02"},{id:"911002",name:"\u592a\u548c\u53bf"},{id:"911006",name:"\u4e34\u6cc9\u53bf"},{id:"911007",name:"\u988d\u4e0a\u53bf"},{id:"911008",name:"\u961c\u5357\u53bf"}]},{id:"9120",name:"\u5bbf\u5dde\u5e02",subItems:[{id:"912001",name:"\u57c7\u6865\u533a"},{id:"912002",name:"\u8427\u53bf"},{id:"912003",name:"\u6cd7\u53bf"},{id:"912004",name:"\u7800\u5c71\u53bf"},{id:"912005",name:"\u7075\u74a7\u53bf"}]},{id:"9130",name:"\u5de2\u6e56\u5e02",subItems:[{id:"913001",name:"\u5c45\u5de2\u533a"},{id:"913002",name:"\u542b\u5c71\u53bf"},{id:"913003",name:"\u65e0\u4e3a\u53bf"},{id:"913004",name:"\u5e90\u6c5f\u53bf"},{id:"913005",name:"\u548c\u53bf"}]},{id:"9140",name:"\u516d\u5b89\u5e02",subItems:[{id:"914001",name:"\u91d1\u5b89\u533a"},{id:"914002",name:"\u88d5\u5b89\u533a"},{id:"914003",name:"\u5bff\u53bf"},{id:"914004",name:"\u970d\u5c71\u53bf"},{id:"914005",name:"\u970d\u90b1\u53bf"},{id:"914006",name:"\u8212\u57ce\u53bf"},{id:"914007",name:"\u91d1\u5be8\u53bf"}]},{id:"9150",name:"\u4eb3\u5dde\u5e02",subItems:[{id:"915001",name:"\u8c2f\u57ce\u533a"},{id:"915002",name:"\u5229\u8f9b\u53bf"},{id:"915003",name:"\u6da1\u9633\u53bf"},{id:"915004",name:"\u8499\u57ce\u53bf"}]},{id:"9160",name:"\u6c60\u5dde\u5e02",subItems:[{id:"916001",name:"\u8d35\u6c60\u533a"},{id:"916002",name:"\u4e1c\u81f3\u53bf"},{id:"916003",name:"\u77f3\u53f0\u53bf"},{id:"916004",name:"\u9752\u9633\u53bf"}]},{id:"9170",name:"\u5ba3\u57ce\u5e02",subItems:[{id:"917001",name:"\u5ba3\u5dde\u533a"},{id:"9050",name:"\u5b81\u56fd\u5e02"},{id:"917002",name:"\u5e7f\u5fb7\u53bf"},{id:"917003",name:"\u90ce\u6eaa\u53bf"},{id:"917004",name:"\u6cfe\u53bf"},{id:"917005",name:"\u65cc\u5fb7\u53bf"},{id:"917006",name:"\u7ee9\u6eaa\u53bf"}]},{id:"9180",name:"\u94dc\u9675\u5e02",subItems:[{id:"918001",name:"\u94dc\u5b98\u5c71\u533a"},{id:"918002",name:"\u72ee\u5b50\u5c71\u533a"},{id:"918003",name:"\u94dc\u9675\u90ca\u533a"},{id:"918004",name:"\u94dc\u9675\u53bf"}]}]},{id:"10000",name:"\u798f\u5efa\u7701",subItems:[{id:"35",name:"\u798f\u5dde\u5e02",subItems:[{id:"3501",name:"\u9f13\u697c\u533a"},{id:"3503",name:"\u53f0\u6c5f\u533a"},{id:"3507",name:"\u9a6c\u5c3e\u533a"},{id:"3505",name:"\u4ed3\u5c71\u533a"},{id:"3509",name:"\u664b\u5b89\u533a"},{id:"3512",name:"\u957f\u4e50\u5e02"},{id:"3510",name:"\u798f\u6e05\u5e02"},{id:"3502",name:"\u7f57\u6e90\u53bf"},{id:"3506",name:"\u6c38\u6cf0\u53bf"},{id:"3504",name:"\u95fd\u6e05\u53bf"},{id:"3508",name:"\u5e73\u6f6d\u53bf"},{id:"3511",name:"\u95fd\u4faf\u53bf"},{id:"3513",name:"\u8fde\u6c5f\u53bf"}]},{id:"10030",name:"\u6cc9\u5dde\u5e02",subItems:[{id:"1003001",name:"\u9ca4\u57ce\u533a"},{id:"1003003",name:"\u4e30\u6cfd\u533a"},{id:"1003005",name:"\u6d1b\u6c5f\u533a"},{id:"1003007",name:"\u6cc9\u6e2f\u533a"},{id:"1003006",name:"\u77f3\u72ee\u5e02"},{id:"1003008",name:"\u664b\u6c5f\u5e02"},{id:"1003010",name:"\u5357\u5b89\u5e02"},{id:"1003004",name:"\u91d1\u95e8\u53bf"},{id:"1003002",name:"\u5fb7\u5316\u53bf"},{id:"1003012",name:"\u6c38\u6625\u53bf"},{id:"1003009",name:"\u60e0\u5b89\u53bf"},{id:"1003011",name:"\u5b89\u6eaa\u53bf"}]},{id:"155",name:"\u53a6\u95e8\u5e02",subItems:[{id:"15501",name:"\u601d\u660e\u533a"},{id:"15502",name:"\u6d77\u6ca7\u533a"},{id:"15503",name:"\u6e56\u91cc\u533a"},{id:"15504",name:"\u96c6\u7f8e\u533a"},{id:"15505",name:"\u540c\u5b89\u533a"},{id:"15506",name:"\u7fd4\u5b89\u533a"}]},{id:"10040",name:"\u6f33\u5dde\u5e02",subItems:[{id:"1004001",name:"\u8297\u57ce\u533a"},{id:"1004003",name:"\u9f99\u6587\u533a"},{id:"1004008",name:"\u9f99\u6d77\u5e02"},{id:"1004004",name:"\u5e73\u548c\u53bf"},{id:"1004002",name:"\u5357\u9756\u53bf"},{id:"1004005",name:"\u4e91\u9704\u53bf"},{id:"1004006",name:"\u534e\u5b89\u53bf"},{id:"1004007",name:"\u6f33\u6d66\u53bf"},{id:"1004009",name:"\u8bcf\u5b89\u53bf"},{id:"1004010",name:"\u957f\u6cf0\u53bf"},{id:"1004011",name:"\u4e1c\u5c71\u53bf"}]},{id:"10050",name:"\u8386\u7530\u5e02",subItems:[{id:"1005001",name:"\u57ce\u53a2\u533a"},{id:"1005002",name:"\u6db5\u6c5f\u533a"},{id:"1005003",name:"\u8354\u57ce\u533a"},{id:"1005004",name:"\u79c0\u5c7f\u533a"},{id:"1005005",name:"\u4ed9\u6e38\u53bf"}]},{id:"10020",name:"\u9f99\u5ca9\u5e02",subItems:[{id:"1002001",name:"\u65b0\u7f57\u533a"},{id:"1002007",name:"\u6f33\u5e73\u5e02"},{id:"1002002",name:"\u957f\u6c40\u53bf"},{id:"1002003",name:"\u6c38\u5b9a\u53bf"},{id:"1002004",name:"\u4e0a\u676d\u53bf"},{id:"1002005",name:"\u6b66\u5e73\u53bf"},{id:"1002006",name:"\u8fde\u57ce\u53bf"}]},{id:"10060",name:"\u5b81\u5fb7\u5e02",subItems:[{id:"1006001",name:"\u8549\u57ce\u533a"},{id:"1006003",name:"\u798f\u9f0e\u5e02"},{id:"10010",name:"\u798f\u5b89\u5e02"},{id:"1006004",name:"\u53e4\u7530\u53bf"},{id:"1006002",name:"\u971e\u6d66\u53bf"},{id:"1006005",name:"\u5c4f\u5357\u53bf"},{id:"1006006",name:"\u5bff\u5b81\u53bf"},{id:"1006007",name:"\u5468\u5b81\u53bf"},{id:"1006008",name:"\u67d8\u8363\u53bf"}]},{id:"10070",name:"\u5357\u5e73\u5e02",subItems:[{id:"1007001",name:"\u5ef6\u5e73\u533a"},{id:"1007002",name:"\u6b66\u5937\u5c71\u5e02"},{id:"1007004",name:"\u5efa\u74ef\u5e02"},{id:"1007006",name:"\u5efa\u9633\u5e02"},{id:"1007010",name:"\u90b5\u6b66\u5e02"},{id:"1007003",name:"\u987a\u660c\u53bf"},{id:"1007005",name:"\u6d66\u57ce\u53bf"},{id:"1007007",name:"\u5149\u6cfd\u53bf"},{id:"1007008",name:"\u677e\u6eaa\u53bf"},{id:"1007009",name:"\u653f\u548c\u53bf"}]},{id:"10080",name:"\u4e09\u660e\u5e02",subItems:[{id:"1008001",name:"\u6885\u5217\u533a"},{id:"1008003",name:"\u4e09\u5143\u533a"},{id:"1008010",name:"\u6c38\u5b89\u5e02"},{id:"1008002",name:"\u6c99\u53bf"},{id:"1008004",name:"\u5c06\u4e50\u53bf"},{id:"1008005",name:"\u660e\u6eaa\u53bf"},{id:"1008006",name:"\u6cf0\u5b81\u53bf"},{id:"1008007",name:"\u6e05\u6d41\u53bf"},{id:"1008008",name:"\u5efa\u5b81\u53bf"},{id:"1008009",name:"\u5b81\u5316\u53bf"},{id:"1008011",name:"\u5927\u7530\u53bf"},{id:"1008012",name:"\u5c24\u6eaa\u53bf"}]}]},{id:"24000",name:"\u7518\u8083\u7701",subItems:[{id:"85",name:"\u5170\u5dde\u5e02",subItems:[{id:"8501",name:"\u57ce\u5173\u533a"},{id:"8503",name:"\u4e03\u91cc\u6cb3\u533a"},{id:"8504",name:"\u897f\u56fa\u533a"},{id:"8505",name:"\u5b89\u5b81\u533a"},{id:"8506",name:"\u7ea2\u53e4\u533a"},{id:"8502",name:"\u6986\u4e2d\u53bf"},{id:"8507",name:"\u6c38\u767b\u53bf"},{id:"8508",name:"\u768b\u5170\u53bf"}]},{id:"24020",name:"\u5609\u5cea\u5173\u5e02"},{id:"24030",name:"\u9152\u6cc9\u5e02",subItems:[{id:"2403001",name:"\u8083\u5dde\u533a"},{id:"2403002",name:"\u7389\u95e8\u5e02"},{id:"2403003",name:"\u6566\u714c\u5e02"},{id:"2403004",name:"\u91d1\u5854\u53bf"},{id:"2403005",name:"\u74dc\u5dde\u53bf"},{id:"2403006",name:"\u8083\u5317\u53bf"},{id:"2403007",name:"\u963f\u514b\u585e\u53bf"}]},{id:"24040",name:"\u91d1\u660c\u5e02",subItems:[{id:"2404001",name:"\u91d1\u5ddd\u533a"},{id:"2404002",name:"\u6c38\u660c\u53bf"}]},{id:"24050",name:"\u767d\u94f6\u5e02",subItems:[{id:"2405001",name:"\u767d\u94f6\u533a"},{id:"2405002",name:"\u5e73\u5ddd\u533a"},{id:"2405003",name:"\u9756\u8fdc\u53bf"},{id:"2405004",name:"\u4f1a\u5b81\u53bf"},{id:"2405005",name:"\u666f\u6cf0\u53bf"}]},{id:"24060",name:"\u5929\u6c34\u5e02",subItems:[{id:"2406001",name:"\u79e6\u5dde\u533a"},{id:"2406002",name:"\u9ea6\u79ef\u533a"},{id:"2406003",name:"\u6e05\u6c34\u53bf"},{id:"2406004",name:"\u79e6\u5b89\u53bf"},{id:"2406005",name:"\u7518\u8c37\u53bf"},{id:"2406006",name:"\u6b66\u5c71\u53bf"},{id:"2406007",name:"\u5f20\u5bb6\u5ddd\u53bf"}]},{id:"24070",name:"\u6b66\u5a01\u5e02",subItems:[{id:"2407001",name:"\u51c9\u5dde\u533a"},{id:"2407002",name:"\u6c11\u52e4\u53bf"},{id:"2407003",name:"\u53e4\u6d6a\u53bf"},{id:"2407004",name:"\u5929\u795d\u53bf"}]},{id:"24080",name:"\u5f20\u6396\u5e02",subItems:[{id:"2408001",name:"\u7518\u5dde\u533a"},{id:"2408002",name:"\u6c11\u4e50\u53bf"},{id:"2408003",name:"\u4e34\u6cfd\u53bf"},{id:"2408004",name:"\u9ad8\u53f0\u53bf"},{id:"2408005",name:"\u5c71\u4e39\u53bf"},{id:"2408006",name:"\u8083\u5357\u53bf"}]},{id:"24090",name:"\u5e73\u51c9\u5e02",subItems:[{id:"2409001",name:"\u5d06\u5cd2\u533a"},{id:"2409002",name:"\u6cfe\u5ddd\u53bf"},{id:"2409003",name:"\u7075\u53f0\u53bf"},{id:"2409004",name:"\u5d07\u4fe1\u53bf"},{id:"2409005",name:"\u534e\u4ead\u53bf"},{id:"2409006",name:"\u5e84\u6d6a\u53bf"},{id:"2409007",name:"\u9759\u5b81\u53bf"}]},{id:"24100",name:"\u5e86\u9633\u5e02",subItems:[{id:"2410001",name:"\u897f\u5cf0\u533a"},{id:"2410002",name:"\u9547\u539f\u53bf"},{id:"2410003",name:"\u5e86\u57ce\u53bf"},{id:"2410004",name:"\u73af\u53bf"},{id:"2410005",name:"\u534e\u6c60\u53bf"},{id:"2410006",name:"\u5408\u6c34\u53bf"},{id:"2410007",name:"\u6b63\u5b81\u53bf"},{id:"2410008",name:"\u5b81\u53bf"}]},{id:"24110",name:"\u5b9a\u897f\u5e02",subItems:[{id:"2411001",name:"\u5b89\u5b9a\u533a"},{id:"2411002",name:"\u901a\u6e2d\u53bf"},{id:"2411003",name:"\u9647\u897f\u53bf"},{id:"2411004",name:"\u6e2d\u6e90\u53bf"},{id:"2411005",name:"\u4e34\u6d2e\u53bf"},{id:"2411006",name:"\u6f33\u53bf"},{id:"2411007",name:"\u5cb7\u53bf"}]},{id:"24120",name:"\u9647\u5357\u5e02",subItems:[{id:"2412001",name:"\u6b66\u90fd\u533a"},{id:"2412002",name:"\u5fbd\u53bf"},{id:"2412003",name:"\u6210\u53bf"},{id:"2412004",name:"\u4e24\u5f53\u53bf"},{id:"2412005",name:"\u6587\u53bf"},{id:"2412006",name:"\u5b95\u660c\u53bf"},{id:"2412007",name:"\u5eb7\u53bf"},{id:"2412008",name:"\u897f\u548c\u53bf"},{id:"2412009",name:"\u793c\u53bf"}]},{id:"24130",name:"\u4e34\u590f\u5dde",subItems:[{id:"2413001",name:"\u4e34\u590f\u5e02"},{id:"2413002",name:"\u79ef\u77f3\u5c71\u53bf"},{id:"2413003",name:"\u4e34\u590f\u53bf"},{id:"2413004",name:"\u5eb7\u4e50\u53bf"},{id:"2413005",name:"\u6c38\u9756\u53bf"},{id:"2413006",name:"\u5e7f\u6cb3\u53bf"},{id:"2413007",name:"\u548c\u653f\u53bf"},{id:"2413008",name:"\u4e1c\u4e61\u65cf\u81ea\u6cbb\u53bf"}]},{id:"24140",name:"\u7518\u5357\u5dde",subItems:[{id:"2414001",name:"\u5408\u4f5c\u5e02"},{id:"2414002",name:"\u590f\u6cb3\u53bf"},{id:"2414003",name:"\u4e34\u6f6d\u53bf"},{id:"2414004",name:"\u5353\u5c3c\u53bf"},{id:"2414005",name:"\u821f\u66f2\u53bf"},{id:"2414006",name:"\u8fed\u90e8\u53bf"},{id:"2414007",name:"\u739b\u66f2\u53bf"},{id:"2414008",name:"\u788c\u66f2\u53bf"}]}]},{id:"17000",name:"\u5e7f\u897f",subItems:[{id:"105",name:"\u5357\u5b81\u5e02",subItems:[{id:"10501",name:"\u5174\u5b81\u533a"},{id:"10503",name:"\u9752\u79c0\u533a"},{id:"10507",name:"\u897f\u4e61\u5858\u533a"},{id:"10505",name:"\u6c5f\u5357\u533a"},{id:"10509",name:"\u826f\u5e86\u533a"},{id:"10511",name:"\u9095\u5b81\u533a"},{id:"10502",name:"\u9686\u5b89\u53bf"},{id:"10504",name:"\u9a6c\u5c71\u53bf"},{id:"10508",name:"\u5bbe\u9633\u53bf"},{id:"10506",name:"\u4e0a\u6797\u53bf"},{id:"10510",name:"\u6a2a\u53bf"},{id:"10512",name:"\u6b66\u9e23\u53bf"}]},{id:"17040",name:"\u5317\u6d77\u5e02",subItems:[{id:"1704001",name:"\u6d77\u57ce\u533a"},{id:"1704002",name:"\u94f6\u6d77\u533a"},{id:"1704003",name:"\u94c1\u5c71\u6e2f\u533a"},{id:"1704004",name:"\u5408\u6d66\u53bf"}]},{id:"42",name:"\u6842\u6797\u5e02",subItems:[{id:"4201",name:"\u79c0\u5cf0\u533a"},{id:"4204",name:"\u53e0\u5f69\u533a"},{id:"4207",name:"\u8c61\u5c71\u533a"},{id:"4210",name:"\u4e03\u661f\u533a"},{id:"4212",name:"\u96c1\u5c71\u533a"},{id:"4202",name:"\u7075\u5ddd\u53bf"},{id:"4203",name:"\u8354\u6d66\u53bf"},{id:"4205",name:"\u5168\u5dde\u53bf"},{id:"4206",name:"\u9f99\u80dc\u53bf"},{id:"4208",name:"\u5174\u5b89\u53bf"},{id:"4209",name:"\u606d\u57ce\u53bf"},{id:"4211",name:"\u6c38\u798f\u53bf"},{id:"4213",name:"\u704c\u9633\u53bf"},{id:"4214",name:"\u9633\u6714\u53bf"},{id:"4215",name:"\u8d44\u6e90\u53bf"},{id:"4216",name:"\u4e34\u6842\u53bf"},{id:"4217",name:"\u5e73\u4e50\u53bf"}]},{id:"17020",name:"\u67f3\u5dde\u5e02",subItems:[{id:"1702001",name:"\u57ce\u4e2d\u533a"},{id:"1702003",name:"\u9c7c\u5cf0\u533a"},{id:"1702007",name:"\u67f3\u5317\u533a"},{id:"1702005",name:"\u67f3\u5357\u533a"},{id:"1702004",name:"\u878d\u6c34\u53bf"},{id:"1702002",name:"\u878d\u5b89\u53bf"},{id:"1702006",name:"\u4e09\u6c5f\u53bf"},{id:"1702008",name:"\u67f3\u6c5f\u53bf"},{id:"1702009",name:"\u67f3\u57ce\u53bf"},{id:"1702010",name:"\u9e7f\u5be8\u53bf"}]},{id:"17050",name:"\u7389\u6797\u5e02",subItems:[{id:"1705001",name:"\u7389\u5dde\u533a"},{id:"1705002",name:"\u5317\u6d41\u5e02"},{id:"1705003",name:"\u5bb9\u53bf"},{id:"1705004",name:"\u9646\u5ddd\u53bf"},{id:"1705005",name:"\u535a\u767d\u53bf"},{id:"1705006",name:"\u5174\u4e1a\u53bf"}]},{id:"17060",name:"\u767e\u8272\u5e02",subItems:[{id:"1706001",name:"\u53f3\u6c5f\u533a"},{id:"1706002",name:"\u51cc\u4e91\u53bf"},{id:"1706003",name:"\u7530\u9633\u53bf"},{id:"1706004",name:"\u4e50\u4e1a\u53bf"},{id:"1706005",name:"\u7530\u4e1c\u53bf"},{id:"1706006",name:"\u7530\u6797\u53bf"},{id:"1706007",name:"\u5e73\u679c\u53bf"},{id:"1706008",name:"\u897f\u6797\u53bf"},{id:"1706009",name:"\u5fb7\u4fdd\u53bf"},{id:"1706010",name:"\u9686\u6797\u53bf"},{id:"1706011",name:"\u9756\u897f\u53bf"},{id:"1706012",name:"\u90a3\u5761\u53bf"}]},{id:"17070",name:"\u8d35\u6e2f\u5e02",subItems:[{id:"1707001",name:"\u6e2f\u5317\u533a"},{id:"1707002",name:"\u6e2f\u5357\u533a"},{id:"1707003",name:"\u8983\u5858\u533a"},{id:"1707004",name:"\u6842\u5e73\u5e02"},{id:"1707005",name:"\u5e73\u5357\u53bf"}]},{id:"17080",name:"\u68a7\u5dde\u5e02",subItems:[{id:"1708001",name:"\u4e07\u79c0\u533a"},{id:"1708002",name:"\u8776\u5c71\u533a"},{id:"1708003",name:"\u957f\u6d32\u533a"},{id:"1708004",name:"\u5c91\u6eaa\u5e02"},{id:"1708005",name:"\u82cd\u68a7\u53bf"},{id:"1708006",name:"\u85e4\u53bf"},{id:"1708007",name:"\u8499\u5c71\u53bf"}]},{id:"17090",name:"\u9632\u57ce\u6e2f\u5e02",subItems:[{id:"1709001",name:"\u6e2f\u53e3\u533a"},{id:"1709002",name:"\u9632\u57ce\u533a"},{id:"1709003",name:"\u4e1c\u5174\u5e02"},{id:"1709004",name:"\u4e0a\u601d\u53bf"}]},{id:"17100",name:"\u94a6\u5dde\u5e02",subItems:[{id:"1710001",name:"\u94a6\u5357\u533a"},{id:"1710002",name:"\u94a6\u5317\u533a"},{id:"1710003",name:"\u7075\u5c71\u53bf"},{id:"1710004",name:"\u6d66\u5317\u53bf"}]},{id:"17110",name:"\u8d3a\u5dde\u5e02",subItems:[{id:"1711001",name:"\u516b\u6b65\u533a"},{id:"1711002",name:"\u662d\u5e73\u53bf"},{id:"1711003",name:"\u949f\u5c71\u53bf"},{id:"1711004",name:"\u5bcc\u5ddd\u53bf"}]},{id:"17120",name:"\u6cb3\u6c60\u5e02",subItems:[{id:"1712001",name:"\u91d1\u57ce\u6c5f\u533a"},{id:"1712003",name:"\u5b9c\u5dde\u5e02"},{id:"1712002",name:"\u7f57\u57ce\u53bf"},{id:"1712004",name:"\u73af\u6c5f\u53bf"},{id:"1712005",name:"\u5357\u4e39\u53bf"},{id:"1712006",name:"\u5df4\u9a6c\u53bf"},{id:"1712007",name:"\u5929\u5ce8\u53bf"},{id:"1712008",name:"\u90fd\u5b89\u53bf"},{id:"1712009",name:"\u51e4\u5c71\u53bf"},{id:"1712010",name:"\u5927\u5316\u53bf"},{id:"1712011",name:"\u4e1c\u5170\u53bf"}]},{id:"17130",name:"\u6765\u5bbe\u5e02",subItems:[{id:"1713001",name:"\u5174\u5bbe\u533a"},{id:"1713002",name:"\u5408\u5c71\u5e02"},{id:"1713003",name:"\u5ffb\u57ce\u53bf"},{id:"1713004",name:"\u8c61\u5dde\u53bf"},{id:"1713005",name:"\u6b66\u5ba3\u53bf"},{id:"1713006",name:"\u91d1\u79c0\u53bf"}]},{id:"17140",name:"\u5d07\u5de6\u5e02",subItems:[{id:"1714001",name:"\u6c5f\u5dde\u533a"},{id:"1714002",name:"\u51ed\u7965\u5e02"},{id:"1714003",name:"\u6276\u7ee5\u53bf"},{id:"1714004",name:"\u5b81\u660e\u53bf"},{id:"1714005",name:"\u9f99\u5dde\u53bf"},{id:"1714006",name:"\u5927\u65b0\u53bf"},{id:"1714007",name:"\u5929\u7b49\u53bf"}]}]},{id:"20000",name:"\u8d35\u5dde\u7701",subItems:[{id:"45",name:"\u8d35\u9633\u5e02",subItems:[{id:"4501",name:"\u4e4c\u5f53\u533a"},{id:"4503",name:"\u5357\u660e\u533a"},{id:"4505",name:"\u4e91\u5ca9\u533a"},{id:"4507",name:"\u82b1\u6eaa\u533a"},{id:"4508",name:"\u767d\u4e91\u533a"},{id:"4509",name:"\u5c0f\u6cb3\u533a"},{id:"4510",name:"\u6e05\u9547\u5e02"},{id:"4502",name:"\u5f00\u9633\u53bf"},{id:"4504",name:"\u606f\u70fd\u53bf"},{id:"4506",name:"\u4fee\u6587\u53bf"}]},{id:"20020",name:"\u9075\u4e49\u5e02",subItems:[{id:"2002001",name:"\u7ea2\u82b1\u5c97\u533a"},{id:"2002003",name:"\u6c47\u5ddd\u533a"},{id:"2002005",name:"\u8d64\u6c34\u5e02"},{id:"2002007",name:"\u4ec1\u6000\u5e02"},{id:"2002002",name:"\u6b63\u5b89\u53bf"},{id:"2002004",name:"\u51e4\u5188\u53bf"},{id:"2002006",name:"\u6e44\u6f6d\u53bf"},{id:"2002008",name:"\u4f59\u5e86\u53bf"},{id:"2002009",name:"\u9075\u4e49\u53bf"},{id:"2002010",name:"\u4e60\u6c34\u53bf"},{id:"2002011",name:"\u6850\u6893\u53bf"},{id:"2002012",name:"\u9053\u771f\u53bf"},{id:"2002013",name:"\u7ee5\u9633\u53bf"},{id:"2002014",name:"\u52a1\u5ddd\u53bf"}]},{id:"20030",name:"\u516d\u76d8\u6c34\u5e02",subItems:[{id:"2003001",name:"\u949f\u5c71\u533a"},{id:"2003002",name:"\u516d\u679d\u7279\u533a"},{id:"2003003",name:"\u6c34\u57ce\u53bf"},{id:"2003004",name:"\u76d8\u53bf"}]},{id:"20040",name:"\u5b89\u987a\u5e02",subItems:[{id:"2004001",name:"\u897f\u79c0\u533a"},{id:"2004002",name:"\u5e73\u575d\u53bf"},{id:"2004003",name:"\u666e\u5b9a\u53bf"},{id:"2004004",name:"\u9547\u5b81\u53bf"},{id:"2004005",name:"\u5173\u5cad\u53bf"},{id:"2004006",name:"\u7d2b\u4e91\u53bf"}]},{id:"20050",name:"\u94dc\u4ec1",subItems:[{id:"2005006",name:"\u4e07\u5c71\u7279\u533a"},{id:"2005001",name:"\u94dc\u4ec1\u5e02"},{id:"2005002",name:"\u6cbf\u6cb3\u53bf"},{id:"2005003",name:"\u6c5f\u53e3\u53bf"},{id:"2005004",name:"\u677e\u6843\u53bf"},{id:"2005005",name:"\u77f3\u9621\u53bf"},{id:"2005007",name:"\u601d\u5357\u53bf"},{id:"2005008",name:"\u5fb7\u6c5f\u53bf"},{id:"2005009",name:"\u7389\u5c4f\u53bf"},{id:"2005010",name:"\u5370\u6c5f\u53bf"}]},{id:"20060",name:"\u6bd5\u8282",subItems:[{id:"2006001",name:"\u6bd5\u8282\u5e02"},{id:"2006002",name:"\u5a01\u5b81\u53bf"},{id:"2006003",name:"\u5927\u65b9\u53bf"},{id:"2006004",name:"\u9ed4\u897f\u53bf"},{id:"2006005",name:"\u91d1\u6c99\u53bf"},{id:"2006006",name:"\u7ec7\u91d1\u53bf"},{id:"2006007",name:"\u7eb3\u96cd\u53bf"},{id:"2006008",name:"\u8d6b\u7ae0\u53bf"}]},{id:"20070",name:"\u9ed4\u897f\u5357\u5dde",subItems:[{id:"2007001",name:"\u5174\u4e49\u5e02"},{id:"2007002",name:"\u5b89\u9f99\u53bf"},{id:"2007003",name:"\u5174\u4ec1\u53bf"},{id:"2007004",name:"\u666e\u5b89\u53bf"},{id:"2007005",name:"\u6674\u9686\u53bf"},{id:"2007006",name:"\u8d1e\u4e30\u53bf"},{id:"2007007",name:"\u671b\u8c1f\u53bf"},{id:"2007008",name:"\u518c\u4ea8\u53bf"}]},{id:"20080",name:"\u9ed4\u4e1c\u5357\u5dde",subItems:[{id:"2008001",name:"\u51ef\u91cc\u5e02"},{id:"2008002",name:"\u9526\u5c4f\u53bf"},{id:"2008003",name:"\u9ebb\u6c5f\u53bf"},{id:"2008004",name:"\u9ec4\u5e73\u53bf"},{id:"2008005",name:"\u5251\u6cb3\u53bf"},{id:"2008006",name:"\u4e39\u5be8\u53bf"},{id:"2008007",name:"\u65bd\u79c9\u53bf"},{id:"2008008",name:"\u53f0\u6c5f\u53bf"},{id:"2008009",name:"\u4e09\u7a57\u53bf"},{id:"2008010",name:"\u9ece\u5e73\u53bf"},{id:"2008011",name:"\u9547\u8fdc\u53bf"},{id:"2008012",name:"\u6995\u6c5f\u53bf"},{id:"2008013",name:"\u5c91\u5de9\u53bf"},{id:"2008014",name:"\u4ece\u6c5f\u53bf"},{id:"2008015",name:"\u5929\u67f1\u53bf"},{id:"2008016",name:"\u96f7\u5c71\u53bf"}]},{id:"20090",name:"\u9ed4\u5357\u5dde",subItems:[{id:"2009001",name:"\u90fd\u5300\u5e02"},{id:"2009003",name:"\u798f\u6cc9\u5e02"},{id:"2009002",name:"\u7f57\u7538\u53bf"},{id:"2009004",name:"\u957f\u987a\u53bf"},{id:"2009005",name:"\u8354\u6ce2\u53bf"},{id:"2009006",name:"\u9f99\u91cc\u53bf"},{id:"2009007",name:"\u8d35\u5b9a\u53bf"},{id:"2009008",name:"\u60e0\u6c34\u53bf"},{id:"2009009",name:"\u74ee\u5b89\u53bf"},{id:"2009010",name:"\u4e09\u90fd\u53bf"},{id:"2009011",name:"\u72ec\u5c71\u53bf"},{id:"2009012",name:"\u5e73\u5858\u53bf"}]}]},{id:"18000",name:"\u6d77\u5357\u7701",subItems:[{id:"50",name:"\u6d77\u53e3\u5e02",subItems:[{id:"5001",name:"\u9f99\u534e\u533a"},{id:"5002",name:"\u79c0\u82f1\u533a"},{id:"5003",name:"\u743c\u5c71\u533a"},{id:"5004",name:"\u7f8e\u5170\u533a"}]},{id:"18020",name:"\u4e09\u4e9a\u5e02"},{id:"18030",name:"\u7701\u76f4\u8f96",subItems:[{id:"1803001",name:"\u4e94\u6307\u5c71\u5e02"},{id:"1803004",name:"\u743c\u6d77\u5e02"},{id:"1803007",name:"\u510b\u5dde\u5e02"},{id:"1803010",name:"\u6587\u660c\u5e02"},{id:"1803012",name:"\u4e07\u5b81\u5e02"},{id:"1803014",name:"\u4e1c\u65b9\u5e02"},{id:"1803002",name:"\u5c6f\u660c\u53bf"},{id:"1803003",name:"\u4fdd\u4ead\u53bf"},{id:"1803005",name:"\u6f84\u8fc8\u53bf"},{id:"1803006",name:"\u743c\u4e2d\u53bf"},{id:"1803008",name:"\u4e34\u9ad8\u53bf"},{id:"1803009",name:"\u9675\u6c34\u53bf"},{id:"1803011",name:"\u767d\u6c99\u53bf"},{id:"1803013",name:"\u660c\u6c5f\u53bf"},{id:"1803015",name:"\u4e50\u4e1c\u53bf"},{id:"1803016",name:"\u5b9a\u5b89\u53bf"}]}]},{id:"1000",name:"\u6cb3\u5317\u7701",subItems:[{id:"130",name:"\u77f3\u5bb6\u5e84\u5e02",subItems:[{id:"13001",name:"\u957f\u5b89\u533a"},{id:"13002",name:"\u6865\u4e1c\u533a"},{id:"13005",name:"\u6865\u897f\u533a"},{id:"13006",name:"\u65b0\u534e\u533a"},{id:"13009",name:"\u4e95\u9649\u77ff\u533a"},{id:"13024",name:"\u88d5\u534e\u533a"},{id:"13004",name:"\u65b0\u4e50\u5e02"},{id:"13008",name:"\u9e7f\u6cc9\u5e02"},{id:"13017",name:"\u8f9b\u96c6\u5e02"},{id:"13023",name:"\u664b\u5dde\u5e02"},{id:"13025",name:"\u85c1\u57ce\u5e02"},{id:"13007",name:"\u5e73\u5c71\u53bf"},{id:"13003",name:"\u65e0\u6781\u53bf"},{id:"13011",name:"\u5143\u6c0f\u53bf"},{id:"13012",name:"\u4e95\u9649\u53bf"},{id:"13013",name:"\u6b63\u5b9a\u53bf"},{id:"13014",name:"\u8d75\u53bf"},{id:"13015",name:"\u683e\u57ce\u53bf"},{id:"13016",name:"\u884c\u5510\u53bf"},{id:"13018",name:"\u7075\u5bff\u53bf"},{id:"13019",name:"\u9ad8\u9091\u53bf"},{id:"13021",name:"\u6df1\u6cfd\u53bf"},{id:"13022",name:"\u8d5e\u7687\u53bf"}]},{id:"7",name:"\u4fdd\u5b9a\u5e02",subItems:[{id:"701",name:"\u65b0\u5e02\u533a"},{id:"705",name:"\u5317\u5e02\u533a"},{id:"709",name:"\u5357\u5e02\u533a"},{id:"704",name:"\u6dbf\u5dde\u5e02"},{id:"708",name:"\u5b9a\u5dde\u5e02"},{id:"716",name:"\u9ad8\u7891\u5e97\u5e02"},{id:"712",name:"\u5b89\u56fd\u5e02"},{id:"702",name:"\u5f90\u6c34\u53bf"},{id:"703",name:"\u5b89\u65b0\u53bf"},{id:"706",name:"\u5b9a\u5174\u53bf"},{id:"707",name:"\u6613\u53bf"},{id:"710",name:"\u5510\u53bf"},{id:"711",name:"\u66f2\u9633\u53bf"},{id:"713",name:"\u6ee1\u57ce\u53bf"},{id:"714",name:"\u9ad8\u9633\u53bf"},{id:"715",name:"\u8821\u53bf"},{id:"717",name:"\u6e05\u82d1\u53bf"},{id:"718",name:"\u5bb9\u57ce\u53bf"},{id:"719",name:"\u987a\u5e73\u53bf"},{id:"720",name:"\u6d9e\u6c34\u53bf"},{id:"721",name:"\u6d9e\u6e90\u53bf"},{id:"722",name:"\u535a\u91ce\u53bf"},{id:"723",name:"\u961c\u5e73\u53bf"},{id:"724",name:"\u671b\u90fd\u53bf"},{id:"725",name:"\u96c4\u53bf"}]},{id:"1070",name:"\u627f\u5fb7\u5e02",subItems:[{id:"107001",name:"\u53cc\u6865\u533a"},{id:"107003",name:"\u53cc\u6ee6\u533a"},{id:"107005",name:"\u9e70\u624b\u8425\u5b50\u77ff\u533a"},{id:"107002",name:"\u9686\u5316\u53bf"},{id:"107004",name:"\u4e30\u5b81\u53bf"},{id:"107006",name:"\u5bbd\u57ce\u53bf"},{id:"107007",name:"\u627f\u5fb7\u53bf"},{id:"107008",name:"\u56f4\u573a\u53bf"},{id:"107009",name:"\u5174\u9686\u53bf"},{id:"107010",name:"\u5e73\u6cc9\u53bf"},{id:"107011",name:"\u6ee6\u5e73\u53bf"}]},{id:"53",name:"\u90af\u90f8\u5e02",subItems:[{id:"5301",name:"\u90af\u5c71\u533a"},{id:"5304",name:"\u4e1b\u53f0\u533a"},{id:"5306",name:"\u590d\u5174\u533a"},{id:"5309",name:"\u5cf0\u5cf0\u77ff\u533a"},{id:"5314",name:"\u6b66\u5b89\u5e02"},{id:"5302",name:"\u5927\u540d\u53bf"},{id:"5303",name:"\u5e7f\u5e73\u53bf"},{id:"5305",name:"\u6d89\u53bf"},{id:"5307",name:"\u78c1\u53bf"},{id:"5308",name:"\u9b4f\u53bf"},{id:"5310",name:"\u80a5\u4e61\u53bf"},{id:"5311",name:"\u66f2\u5468\u53bf"},{id:"5312",name:"\u90af\u90f8\u53bf"},{id:"5313",name:"\u6c38\u5e74\u53bf"},{id:"5315",name:"\u4e34\u6f33\u53bf"},{id:"5316",name:"\u90b1\u53bf"},{id:"5317",name:"\u6210\u5b89\u53bf"},{id:"5318",name:"\u9e21\u6cfd\u53bf"},{id:"5319",name:"\u9986\u9676\u53bf"}]},{id:"1080",name:"\u5eca\u574a\u5e02",subItems:[{id:"108002",name:"\u5b89\u6b21\u533a"},{id:"108004",name:"\u5e7f\u9633\u533a"},{id:"108005",name:"\u9738\u5dde\u5e02"},{id:"108007",name:"\u4e09\u6cb3\u5e02"},{id:"108003",name:"\u5927\u5382\u53bf"},{id:"108001",name:"\u6587\u5b89\u53bf"},{id:"108006",name:"\u56fa\u5b89\u53bf"},{id:"108008",name:"\u6c38\u6e05\u53bf"},{id:"108009",name:"\u9999\u6cb3\u53bf"},{id:"108010",name:"\u5927\u57ce\u53bf"}]},{id:"1030",name:"\u79e6\u7687\u5c9b\u5e02",subItems:[{id:"103001",name:"\u6d77\u6e2f\u533a"},{id:"103005",name:"\u5317\u6234\u6cb3\u533a"},{id:"103003",name:"\u5c71\u6d77\u5173\u533a"},{id:"103002",name:"\u660c\u9ece\u53bf"},{id:"103004",name:"\u629a\u5b81\u53bf"},{id:"103006",name:"\u5362\u9f99\u53bf"},{id:"103007",name:"\u9752\u9f99\u53bf"}]},{id:"1020",name:"\u5510\u5c71\u5e02",subItems:[{id:"102001",name:"\u8def\u5357\u533a"},{id:"102002",name:"\u8def\u5317\u533a"},{id:"102003",name:"\u53e4\u51b6\u533a"},{id:"102004",name:"\u5f00\u5e73\u533a"},{id:"102005",name:"\u4e30\u5357\u533a"},{id:"102006",name:"\u4e30\u6da6\u533a"},{id:"102013",name:"\u9075\u5316\u5e02"},{id:"102014",name:"\u8fc1\u5b89\u5e02"},{id:"102007",name:"\u6ee6\u53bf"},{id:"102008",name:"\u6ee6\u5357\u53bf"},{id:"102009",name:"\u4e50\u4ead\u53bf"},{id:"102010",name:"\u8fc1\u897f\u53bf"},{id:"102011",name:"\u7389\u7530\u53bf"},{id:"102012",name:"\u5510\u6d77\u53bf"}]},{id:"1060",name:"\u5f20\u5bb6\u53e3\u5e02",subItems:[{id:"106001",name:"\u6865\u4e1c\u533a"},{id:"106004",name:"\u6865\u897f\u533a"},{id:"106007",name:"\u5ba3\u5316\u533a"},{id:"106010",name:"\u4e0b\u82b1\u56ed\u533a"},{id:"106003",name:"\u6dbf\u9e7f\u53bf"},{id:"106005",name:"\u5c1a\u4e49\u53bf"},{id:"106002",name:"\u6cbd\u6e90\u53bf"},{id:"106006",name:"\u8d64\u57ce\u53bf"},{id:"106008",name:"\u851a\u53bf"},{id:"106009",name:"\u5d07\u793c\u53bf"},{id:"106011",name:"\u9633\u539f\u53bf"},{id:"106012",name:"\u5ba3\u5316\u53bf"},{id:"106013",name:"\u6000\u5b89\u53bf"},{id:"106014",name:"\u5f20\u5317\u53bf"},{id:"106015",name:"\u4e07\u5168\u53bf"},{id:"106016",name:"\u5eb7\u4fdd\u53bf"},{id:"106017",name:"\u6000\u6765\u53bf"}]},{id:"1090",name:"\u6ca7\u5dde\u5e02",subItems:[{id:"109003",name:"\u65b0\u534e\u533a"},{id:"109006",name:"\u8fd0\u6cb3\u533a"},{id:"109008",name:"\u6cb3\u95f4\u5e02"},{id:"109005",name:"\u9ec4\u9a85\u5e02"},{id:"109002",name:"\u4efb\u4e18\u5e02"},{id:"109016",name:"\u6cca\u5934\u5e02"},{id:"109001",name:"\u76d0\u5c71\u53bf"},{id:"109004",name:"\u8083\u5b81\u53bf"},{id:"109007",name:"\u5357\u76ae\u53bf"},{id:"109009",name:"\u6ca7\u53bf"},{id:"109010",name:"\u5434\u6865\u53bf"},{id:"109011",name:"\u9752\u53bf"},{id:"109012",name:"\u732e\u53bf"},{id:"109013",name:"\u4e1c\u5149\u53bf"},{id:"109014",name:"\u5b5f\u6751\u53bf"},{id:"109015",name:"\u6d77\u5174\u53bf"}]},{id:"1100",name:"\u90a2\u53f0\u5e02",subItems:[{id:"110001",name:"\u6865\u4e1c\u533a"},{id:"110004",name:"\u6865\u897f\u533a"},{id:"110012",name:"\u5357\u5bab\u5e02"},{id:"110015",name:"\u6c99\u6cb3\u5e02"},{id:"110002",name:"\u4efb\u53bf"},{id:"110003",name:"\u5a01\u53bf"},{id:"110005",name:"\u5357\u548c\u53bf"},{id:"110006",name:"\u6e05\u6cb3\u53bf"},{id:"110007",name:"\u90a2\u53f0\u53bf"},{id:"110008",name:"\u5b81\u664b\u53bf"},{id:"110009",name:"\u4e34\u897f\u53bf"},{id:"110010",name:"\u4e34\u57ce\u53bf"},{id:"110011",name:"\u5de8\u9e7f\u53bf"},{id:"110013",name:"\u5185\u4e18\u53bf"},{id:"110014",name:"\u65b0\u6cb3\u53bf"},{id:"110016",name:"\u67cf\u4e61\u53bf"},{id:"110017",name:"\u5e7f\u5b97\u53bf"},{id:"110018",name:"\u9686\u5c27\u53bf"},{id:"110019",name:"\u5e73\u4e61\u53bf"}]},{id:"1110",name:"\u8861\u6c34\u5e02",subItems:[{id:"111001",name:"\u6843\u57ce\u533a"},{id:"111006",name:"\u5180\u5dde\u5e02"},{id:"111008",name:"\u6df1\u5dde\u5e02"},{id:"111002",name:"\u666f\u53bf"},{id:"111003",name:"\u67a3\u5f3a\u53bf"},{id:"111004",name:"\u961c\u57ce\u53bf"},{id:"111005",name:"\u6b66\u9091\u53bf"},{id:"111007",name:"\u6b66\u5f3a\u53bf"},{id:"111009",name:"\u9976\u9633\u53bf"},{id:"111010",name:"\u5b89\u5e73\u53bf"},{id:"111011",name:"\u6545\u57ce\u53bf"}]}]},{id:"13000",name:"\u6cb3\u5357\u7701",subItems:[{id:"175",name:"\u90d1\u5dde\u5e02",subItems:[{id:"17501",name:"\u4e2d\u539f\u533a"},{id:"17503",name:"\u91d1\u6c34\u533a"},{id:"17505",name:"\u4e8c\u4e03\u533a"},{id:"17507",name:"\u7ba1\u57ce\u56de\u65cf\u533a"},{id:"17509",name:"\u4e0a\u8857\u533a"},{id:"17511",name:"\u60e0\u6d4e\u533a"},{id:"17502",name:"\u5de9\u4e49\u5e02"},{id:"17504",name:"\u65b0\u90d1\u5e02"},{id:"17506",name:"\u65b0\u5bc6\u5e02"},{id:"17508",name:"\u767b\u5c01\u5e02"},{id:"17510",name:"\u8365\u9633\u5e02"},{id:"17512",name:"\u4e2d\u725f\u53bf"}]},{id:"78",name:"\u5f00\u5c01\u5e02",subItems:[{id:"7801",name:"\u9f13\u697c\u533a"},{id:"7803",name:"\u9f99\u4ead\u533a"},{id:"7805",name:"\u987a\u6cb3\u56de\u65cf\u533a"},{id:"7807",name:"\u79b9\u738b\u53f0\u533a"},{id:"7808",name:"\u91d1\u660e\u533a"},{id:"7804",name:"\u675e\u53bf"},{id:"7806",name:"\u901a\u8bb8\u53bf"},{id:"7802",name:"\u5170\u8003\u53bf"},{id:"7809",name:"\u5f00\u5c01\u53bf"},{id:"7810",name:"\u5c09\u6c0f\u53bf"}]},{id:"92",name:"\u6d1b\u9633\u5e02",subItems:[{id:"9201",name:"\u897f\u5de5\u533a"},{id:"9204",name:"\u8001\u57ce\u533a"},{id:"9206",name:"\u6da7\u897f\u533a"},{id:"9210",name:"\u6d1b\u9f99\u533a"},{id:"9212",name:"\u5409\u5229\u533a"},{id:"9208",name:"\u700d\u6cb3\u56de\u65cf\u533a"},{id:"9214",name:"\u5043\u5e08\u5e02"},{id:"9205",name:"\u6c5d\u9633\u53bf"},{id:"9202",name:"\u5b5f\u6d25\u53bf"},{id:"9203",name:"\u683e\u5ddd\u53bf"},{id:"9207",name:"\u4f0a\u5ddd\u53bf"},{id:"9209",name:"\u6d1b\u5b81\u53bf"},{id:"9211",name:"\u5d69\u53bf"},{id:"9213",name:"\u5b9c\u9633\u53bf"},{id:"9215",name:"\u65b0\u5b89\u53bf"}]},{id:"13010",name:"\u5546\u4e18\u5e02",subItems:[{id:"1301001",name:"\u6881\u56ed\u533a"},{id:"1301003",name:"\u7762\u9633\u533a"},{id:"1301005",name:"\u6c38\u57ce\u5e02"},{id:"1301002",name:"\u67d8\u57ce\u53bf"},{id:"1301004",name:"\u7762\u53bf"},{id:"1301006",name:"\u5b81\u9675\u53bf"},{id:"1301007",name:"\u865e\u57ce\u53bf"},{id:"1301008",name:"\u6c11\u6743\u53bf"},{id:"1301009",name:"\u590f\u9091\u53bf"}]},{id:"13020",name:"\u4e09\u95e8\u5ce1\u5e02",subItems:[{id:"1302001",name:"\u6e56\u6ee8\u533a"},{id:"1302002",name:"\u4e49\u9a6c\u5e02"},{id:"1302003",name:"\u7075\u5b9d\u5e02"},{id:"1302004",name:"\u6e11\u6c60\u53bf"},{id:"1302005",name:"\u5362\u6c0f\u53bf"},{id:"1302006",name:"\u9655\u53bf"}]},{id:"13030",name:"\u9a7b\u9a6c\u5e97\u5e02",subItems:[{id:"1303001",name:"\u9a7f\u57ce\u533a"},{id:"1303002",name:"\u6c5d\u5357\u53bf"},{id:"1303003",name:"\u786e\u5c71\u53bf"},{id:"1303004",name:"\u9042\u5e73\u53bf"},{id:"1303005",name:"\u65b0\u8521\u53bf"},{id:"1303006",name:"\u6b63\u9633\u53bf"},{id:"1303007",name:"\u4e0a\u8521\u53bf"},{id:"1303008",name:"\u897f\u5e73\u53bf"},{id:"1303009",name:"\u6ccc\u9633\u53bf"},{id:"1303010",name:"\u5e73\u8206\u53bf"}]},{id:"13040",name:"\u5e73\u9876\u5c71\u5e02",subItems:[{id:"1304001",name:"\u65b0\u534e\u533a"},{id:"1304003",name:"\u536b\u4e1c\u533a"},{id:"1304005",name:"\u6e5b\u6cb3\u533a"},{id:"1304007",name:"\u77f3\u9f99\u533a"},{id:"1304008",name:"\u6c5d\u5dde\u5e02"},{id:"1304009",name:"\u821e\u94a2\u5e02"},{id:"1304002",name:"\u53f6\u53bf"},{id:"1304004",name:"\u90cf\u53bf"},{id:"1304006",name:"\u9c81\u5c71\u53bf"},{id:"1304010",name:"\u5b9d\u4e30\u53bf"}]},{id:"13050",name:"\u5b89\u9633\u5e02",subItems:[{id:"1305001",name:"\u5317\u5173\u533a"},{id:"1305003",name:"\u6587\u5cf0\u533a"},{id:"1305005",name:"\u6bb7\u90fd\u533a"},{id:"1305006",name:"\u9f99\u5b89\u533a"},{id:"1305007",name:"\u6797\u5dde\u5e02"},{id:"1305002",name:"\u5185\u9ec4\u53bf"},{id:"1305004",name:"\u6c64\u9634\u53bf"},{id:"1305008",name:"\u5b89\u9633\u53bf"},{id:"1305009",name:"\u6ed1\u53bf"}]},{id:"13060",name:"\u9e64\u58c1\u5e02",subItems:[{id:"1306001",name:"\u6dc7\u6ee8\u533a"},{id:"1306002",name:"\u5c71\u57ce\u533a"},{id:"1306003",name:"\u9e64\u5c71\u533a"},{id:"1306004",name:"\u6d5a\u53bf"},{id:"1306005",name:"\u6dc7\u53bf"}]},{id:"13070",name:"\u65b0\u4e61\u5e02",subItems:[{id:"1307001",name:"\u536b\u6ee8\u533a"},{id:"1307003",name:"\u7ea2\u65d7\u533a"},{id:"1307005",name:"\u51e4\u6cc9\u533a"},{id:"1307007",name:"\u7267\u91ce\u533a"},{id:"1307009",name:"\u536b\u8f89\u5e02"},{id:"1307011",name:"\u8f89\u53bf\u5e02"},{id:"1307002",name:"\u83b7\u5609\u53bf"},{id:"1307004",name:"\u539f\u9633\u53bf"},{id:"1307006",name:"\u957f\u57a3\u53bf"},{id:"1307008",name:"\u5c01\u4e18\u53bf"},{id:"1307010",name:"\u5ef6\u6d25\u53bf"},{id:"1307012",name:"\u65b0\u4e61\u53bf"}]},{id:"13080",name:"\u7126\u4f5c\u5e02",subItems:[{id:"1308001",name:"\u89e3\u653e\u533a"},{id:"1308003",name:"\u4e2d\u7ad9\u533a"},{id:"1308005",name:"\u9a6c\u6751\u533a"},{id:"1308007",name:"\u5c71\u9633\u533a"},{id:"1308008",name:"\u6c81\u9633\u5e02"},{id:"1308009",name:"\u5b5f\u5dde\u5e02"},{id:"1308006",name:"\u535a\u7231\u53bf"},{id:"1308002",name:"\u6e29\u53bf"},{id:"1308004",name:"\u6b66\u965f\u53bf"},{id:"1308010",name:"\u4fee\u6b66\u53bf"}]},{id:"13090",name:"\u6fee\u9633\u5e02",subItems:[{id:"1309001",name:"\u534e\u9f99\u533a"},{id:"1309002",name:"\u6fee\u9633\u53bf"},{id:"1309003",name:"\u5357\u4e50\u53bf"},{id:"1309004",name:"\u53f0\u524d\u53bf"},{id:"1309005",name:"\u6e05\u4e30\u53bf"},{id:"1309006",name:"\u8303\u53bf"}]},{id:"13100",name:"\u8bb8\u660c\u5e02",subItems:[{id:"1310001",name:"\u9b4f\u90fd\u533a"},{id:"1310002",name:"\u79b9\u5dde\u5e02"},{id:"1310003",name:"\u957f\u845b\u5e02"},{id:"1310004",name:"\u8bb8\u660c\u53bf"},{id:"1310005",name:"\u9122\u9675\u53bf"},{id:"1310006",name:"\u8944\u57ce\u53bf"}]},{id:"13110",name:"\u6f2f\u6cb3\u5e02",subItems:[{id:"1311001",name:"\u6e90\u6c47\u533a"},{id:"1311002",name:"\u90fe\u57ce\u533a"},{id:"1311003",name:"\u53ec\u9675\u533a"},{id:"1311004",name:"\u4e34\u988d\u53bf"},{id:"1311005",name:"\u821e\u9633\u53bf"}]},{id:"13120",name:"\u5357\u9633\u5e02",subItems:[{id:"1312001",name:"\u5367\u9f99\u533a"},{id:"1312003",name:"\u5b9b\u57ce\u533a"},{id:"1312005",name:"\u9093\u5dde\u5e02"},{id:"1312002",name:"\u5510\u6cb3\u53bf"},{id:"1312004",name:"\u5357\u53ec\u53bf"},{id:"1312006",name:"\u5185\u4e61\u53bf"},{id:"1312007",name:"\u6850\u67cf\u53bf"},{id:"1312008",name:"\u65b0\u91ce\u53bf"},{id:"1312009",name:"\u65b9\u57ce\u53bf"},{id:"1312010",name:"\u793e\u65d7\u53bf"},{id:"1312011",name:"\u6dc5\u5ddd\u53bf"},{id:"1312012",name:"\u897f\u5ce1\u53bf"},{id:"1312013",name:"\u9547\u5e73\u53bf"}]},{id:"13130",name:"\u4fe1\u9633\u5e02",subItems:[{id:"1313001",name:"\u6d49\u6cb3\u533a"},{id:"1313002",name:"\u56fa\u59cb\u53bf"},{id:"1313003",name:"\u5e73\u6865\u533a"},{id:"1313004",name:"\u7f57\u5c71\u53bf"},{id:"1313005",name:"\u6f62\u5ddd\u53bf"},{id:"1313006",name:"\u5149\u5c71\u53bf"},{id:"1313007",name:"\u6dee\u6ee8\u53bf"},{id:"1313008",name:"\u606f\u53bf"},{id:"1313009",name:"\u65b0\u53bf"},{id:"1313010",name:"\u5546\u57ce\u53bf"}]},{id:"13140",name:"\u5468\u53e3\u5e02",subItems:[{id:"1314001",name:"\u5ddd\u6c47\u533a"},{id:"1314003",name:"\u9879\u57ce\u5e02"},{id:"1314002",name:"\u6276\u6c9f\u53bf"},{id:"1314004",name:"\u6c88\u4e18\u53bf"},{id:"1314005",name:"\u5546\u6c34\u53bf"},{id:"1314006",name:"\u90f8\u57ce\u53bf"},{id:"1314007",name:"\u6dee\u9633\u53bf"},{id:"1314008",name:"\u592a\u5eb7\u53bf"},{id:"1314009",name:"\u9e7f\u9091\u53bf"},{id:"1314010",name:"\u897f\u534e\u53bf"}]},{id:"13150",name:"\u7701\u76f4\u8f96",subItems:[{id:"1315001",name:"\u6d4e\u6e90\u5e02"}]}]},{id:"6000",name:"\u9ed1\u9f99\u6c5f\u7701",subItems:[{id:"60",name:"\u54c8\u5c14\u6ee8\u5e02",subItems:[{id:"6001",name:"\u677e\u5317\u533a"},{id:"6002",name:"\u963f\u57ce\u533a"},{id:"6004",name:"\u9053\u91cc\u533a"},{id:"6007",name:"\u5357\u5c97\u533a"},{id:"6010",name:"\u9053\u5916\u533a"},{id:"6013",name:"\u5e73\u623f\u533a"},{id:"6015",name:"\u9999\u574a\u533a"},{id:"6017",name:"\u547c\u5170\u533a"},{id:"6005",name:"\u53cc\u57ce\u5e02"},{id:"6008",name:"\u5c1a\u5fd7\u5e02"},{id:"6011",name:"\u4e94\u5e38\u5e02"},{id:"6003",name:"\u5df4\u5f66\u53bf"},{id:"6006",name:"\u6728\u5170\u53bf"},{id:"6009",name:"\u901a\u6cb3\u53bf"},{id:"6012",name:"\u5ef6\u5bff\u53bf"},{id:"6014",name:"\u4f9d\u5170\u53bf"},{id:"6016",name:"\u65b9\u6b63\u53bf"},{id:"6018",name:"\u5bbe\u53bf"}]},{id:"6030",name:"\u5927\u5e86\u5e02",subItems:[{id:"603001",name:"\u8428\u5c14\u56fe\u533a"},{id:"603003",name:"\u9f99\u51e4\u533a"},{id:"603005",name:"\u8ba9\u80e1\u8def\u533a"},{id:"603006",name:"\u7ea2\u5c97\u533a"},{id:"603007",name:"\u5927\u540c\u533a"},{id:"603002",name:"\u6797\u7538\u53bf"},{id:"603004",name:"\u675c\u5c14\u4f2f\u7279\u53bf"},{id:"603008",name:"\u8087\u5dde\u53bf"},{id:"603009",name:"\u8087\u6e90\u53bf"}]},{id:"6040",name:"\u4f73\u6728\u65af\u5e02",subItems:[{id:"604001",name:"\u524d\u8fdb\u533a"},{id:"604003",name:"\u5411\u9633\u533a"},{id:"604005",name:"\u4e1c\u98ce\u533a"},{id:"604007",name:"\u4f73\u6728\u65af\u90ca\u533a"},{id:"604008",name:"\u540c\u6c5f\u5e02"},{id:"604009",name:"\u5bcc\u9526\u5e02"},{id:"604002",name:"\u6866\u5ddd\u53bf"},{id:"604004",name:"\u6c64\u539f\u53bf"},{id:"604006",name:"\u629a\u8fdc\u53bf"},{id:"604010",name:"\u6866\u5357\u53bf"}]},{id:"6050",name:"\u7261\u4e39\u6c5f\u5e02",subItems:[{id:"605001",name:"\u7231\u6c11\u533a"},{id:"605003",name:"\u4e1c\u5b89\u533a"},{id:"605005",name:"\u9633\u660e\u533a"},{id:"605007",name:"\u897f\u5b89\u533a"},{id:"605002",name:"\u7a46\u68f1\u5e02"},{id:"605009",name:"\u6d77\u6797\u5e02"},{id:"605010",name:"\u5b81\u5b89\u5e02"},{id:"605008",name:"\u7ee5\u82ac\u6cb3\u5e02"},{id:"605004",name:"\u4e1c\u5b81\u53bf"},{id:"605006",name:"\u6797\u53e3\u53bf"}]},{id:"6020",name:"\u9f50\u9f50\u54c8\u5c14\u5e02",subItems:[{id:"602001",name:"\u9f99\u6c99\u533a"},{id:"602004",name:"\u5efa\u534e\u533a"},{id:"602007",name:"\u94c1\u950b\u533a"},{id:"602009",name:"\u6602\u6602\u6eaa\u533a"},{id:"602013",name:"\u78be\u5b50\u5c71\u533a"},{id:"602015",name:"\u6885\u91cc\u65af"},{id:"602011",name:"\u5bcc\u62c9\u5c14\u57fa\u533a"},{id:"602002",name:"\u8bb7\u6cb3\u5e02"},{id:"602005",name:"\u9f99\u6c5f\u53bf"},{id:"602003",name:"\u514b\u4e1c\u53bf"},{id:"602006",name:"\u62dc\u6cc9\u53bf"},{id:"602008",name:"\u4f9d\u5b89\u53bf"},{id:"602010",name:"\u6cf0\u6765\u53bf"},{id:"602012",name:"\u7518\u5357\u53bf"},{id:"602014",name:"\u5bcc\u88d5\u53bf"},{id:"602016",name:"\u514b\u5c71\u53bf"}]},{id:"6060",name:"\u9e64\u5c97\u5e02",subItems:[{id:"606001",name:"\u5174\u5c71\u533a"},{id:"606003",name:"\u5411\u9633\u533a"},{id:"606004",name:"\u5de5\u519c\u533a"},{id:"606005",name:"\u5357\u5c71\u533a"},{id:"606006",name:"\u5174\u5b89\u533a"},{id:"606007",name:"\u4e1c\u5c71\u533a"},{id:"606002",name:"\u7ee5\u6ee8\u53bf"},{id:"606008",name:"\u841d\u5317\u53bf"}]},{id:"6070",name:"\u9e21\u897f\u5e02",subItems:[{id:"607001",name:"\u9e21\u51a0\u533a"},{id:"607003",name:"\u6052\u5c71\u533a"},{id:"607005",name:"\u6ef4\u9053\u533a"},{id:"607006",name:"\u68a8\u6811\u533a"},{id:"607008",name:"\u9ebb\u5c71\u533a"},{id:"607007",name:"\u57ce\u5b50\u6cb3\u533a"},{id:"607002",name:"\u5bc6\u5c71\u5e02"},{id:"607009",name:"\u864e\u6797\u5e02"},{id:"607004",name:"\u9e21\u4e1c\u53bf"}]},{id:"6080",name:"\u7ee5\u5316\u5e02",subItems:[{id:"608001",name:"\u5317\u6797\u533a"},{id:"608003",name:"\u5b89\u8fbe\u5e02"},{id:"608005",name:"\u8087\u4e1c\u5e02"},{id:"608007",name:"\u6d77\u4f26\u5e02"},{id:"608002",name:"\u5e86\u5b89\u53bf"},{id:"608004",name:"\u660e\u6c34\u53bf"},{id:"608006",name:"\u7ee5\u68f1\u53bf"},{id:"608008",name:"\u671b\u594e\u53bf"},{id:"608009",name:"\u5170\u897f\u53bf"},{id:"608010",name:"\u9752\u5188\u53bf"}]},{id:"6090",name:"\u9ed1\u6cb3\u5e02",subItems:[{id:"609001",name:"\u7231\u8f89\u533a"},{id:"609002",name:"\u5317\u5b89\u5e02"},{id:"609003",name:"\u4e94\u5927\u8fde\u6c60\u5e02"},{id:"609004",name:"\u5ae9\u6c5f\u53bf"},{id:"609005",name:"\u900a\u514b\u53bf"},{id:"609006",name:"\u5b59\u5434\u53bf"}]},{id:"6100",name:"\u4f0a\u6625\u5e02",subItems:[{id:"610001",name:"\u4f0a\u6625\u533a"},{id:"610004",name:"\u5357\u5c94\u533a"},{id:"610005",name:"\u4e94\u8425\u533a"},{id:"610010",name:"\u897f\u6797\u533a"},{id:"610007",name:"\u53cb\u597d\u533a"},{id:"610013",name:"\u5e26\u5cad\u533a"},{id:"610014",name:"\u65b0\u9752\u533a"},{id:"610012",name:"\u7fe0\u5ce6\u533a"},{id:"610016",name:"\u7f8e\u6eaa\u533a"},{id:"610017",name:"\u7ea2\u661f\u533a"},{id:"610002",name:"\u91d1\u5c71\u5c6f\u533a"},{id:"610003",name:"\u4e0a\u7518\u5cad\u533a"},{id:"610008",name:"\u4e4c\u9a6c\u6cb3\u533a"},{id:"610011",name:"\u6c64\u65fa\u6cb3\u533a"},{id:"610015",name:"\u4e4c\u4f0a\u5cad\u533a"},{id:"610006",name:"\u94c1\u529b\u5e02"},{id:"610009",name:"\u5609\u836b\u53bf"}]},{id:"6110",name:"\u53cc\u9e2d\u5c71\u5e02",subItems:[{id:"611001",name:"\u5c16\u5c71\u533a"},{id:"611003",name:"\u5cad\u4e1c\u533a"},{id:"611005",name:"\u5b9d\u5c71\u533a"},{id:"611004",name:"\u56db\u65b9\u53f0\u533a"},{id:"611002",name:"\u9976\u6cb3\u53bf"},{id:"611006",name:"\u96c6\u8d24\u53bf"},{id:"611007",name:"\u53cb\u8c0a\u53bf"},{id:"611008",name:"\u5b9d\u6e05\u53bf"}]},{id:"6120",name:"\u4e03\u53f0\u6cb3\u5e02",subItems:[{id:"612001",name:"\u6843\u5c71\u533a"},{id:"612002",name:"\u65b0\u5174\u533a"},{id:"612003",name:"\u8304\u5b50\u6cb3\u533a"},{id:"612004",name:"\u52c3\u5229\u53bf"}]},{id:"6130",name:"\u5927\u5174\u5b89\u5cad",subItems:[{id:"613001",name:"\u547c\u739b\u53bf"},{id:"613002",name:"\u5854\u6cb3\u53bf"},{id:"613003",name:"\u6f20\u6cb3\u53bf"}]}]},{id:"14000",name:"\u6e56\u5317\u7701",subItems:[{id:"150",name:"\u6b66\u6c49\u5e02",subItems:[{id:"15001",name:"\u6c5f\u5cb8\u533a"},{id:"15002",name:"\u4e1c\u897f\u6e56\u533a"},{id:"15003",name:"\u6c5f\u6c49\u533a"},{id:"15004",name:"\u6c49\u5357\u533a"},{id:"15005",name:"\u785a\u53e3\u533a"},{id:"15006",name:"\u8521\u7538\u533a"},{id:"15007",name:"\u6c49\u9633\u533a"},{id:"15008",name:"\u6c5f\u590f\u533a"},{id:"15009",name:"\u6b66\u660c\u533a"},{id:"15010",name:"\u9ec4\u9642\u533a"},{id:"15011",name:"\u9752\u5c71\u533a"},{id:"15012",name:"\u65b0\u6d32\u533a"},{id:"15013",name:"\u6d2a\u5c71\u533a"}]},{id:"14020",name:"\u5341\u5830\u5e02",subItems:[{id:"1402001",name:"\u8305\u7bad\u533a"},{id:"1402003",name:"\u5f20\u6e7e\u533a"},{id:"1402004",name:"\u4e39\u6c5f\u53e3\u5e02"},{id:"1402002",name:"\u623f\u53bf"},{id:"1402005",name:"\u90e7\u53bf"},{id:"1402006",name:"\u90e7\u897f\u53bf"},{id:"1402007",name:"\u7af9\u5c71\u53bf"},{id:"1402008",name:"\u7af9\u6eaa\u53bf"}]},{id:"14040",name:"\u8944\u6a0a\u5e02",subItems:[{id:"1404001",name:"\u8944\u57ce\u533a"},{id:"1404003",name:"\u6a0a\u57ce\u533a"},{id:"1404005",name:"\u8944\u9633\u533a"},{id:"1404007",name:"\u67a3\u9633\u5e02"},{id:"1404008",name:"\u5b9c\u57ce\u5e02"},{id:"1404006",name:"\u8001\u6cb3\u53e3\u5e02"},{id:"1404002",name:"\u8c37\u57ce\u53bf"},{id:"1404004",name:"\u4fdd\u5eb7\u53bf"},{id:"1404009",name:"\u5357\u6f33\u53bf"}]},{id:"14030",name:"\u5b9c\u660c\u5e02",subItems:[{id:"1403001",name:"\u897f\u9675\u533a"},{id:"1403005",name:"\u70b9\u519b\u533a"},{id:"1403007",name:"\u7307\u4ead\u533a"},{id:"1403009",name:"\u5937\u9675\u533a"},{id:"1403003",name:"\u4f0d\u5bb6\u5c97\u533a"},{id:"1403002",name:"\u679d\u6c5f\u5e02"},{id:"1403011",name:"\u5b9c\u90fd\u5e02"},{id:"1403013",name:"\u5f53\u9633\u5e02"},{id:"1403004",name:"\u8fdc\u5b89\u53bf"},{id:"1403006",name:"\u5174\u5c71\u53bf"},{id:"1403008",name:"\u79ed\u5f52\u53bf"},{id:"1403010",name:"\u957f\u9633\u53bf"},{id:"1403012",name:"\u4e94\u5cf0\u53bf"}]},{id:"14060",name:"\u8346\u95e8\u5e02",subItems:[{id:"1406001",name:"\u4e1c\u5b9d\u533a"},{id:"1406002",name:"\u6387\u5200\u533a"},{id:"1406003",name:"\u949f\u7965\u5e02"},{id:"1406004",name:"\u4eac\u5c71\u53bf"},{id:"1406005",name:"\u6c99\u6d0b\u53bf"}]},{id:"14070",name:"\u8346\u5dde\u5e02",subItems:[{id:"1407003",name:"\u8346\u5dde\u533a"},{id:"1407001",name:"\u6c99\u5e02\u533a"},{id:"1407004",name:"\u77f3\u9996\u5e02"},{id:"1407005",name:"\u6d2a\u6e56\u5e02"},{id:"1407006",name:"\u677e\u6ecb\u5e02"},{id:"1407002",name:"\u6c5f\u9675\u53bf"},{id:"1407007",name:"\u516c\u5b89\u53bf"},{id:"1407008",name:"\u76d1\u5229\u53bf"}]},{id:"14080",name:"\u9ec4\u77f3\u5e02",subItems:[{id:"1408003",name:"\u4e0b\u9646\u533a"},{id:"1408004",name:"\u94c1\u5c71\u533a"},{id:"1408001",name:"\u9ec4\u77f3\u6e2f\u533a"},{id:"1408002",name:"\u897f\u585e\u5c71\u533a"},{id:"1408005",name:"\u5927\u51b6\u5e02"},{id:"1408006",name:"\u9633\u65b0\u53bf"}]},{id:"14090",name:"\u9102\u5dde\u5e02",subItems:[{id:"1409001",name:"\u9102\u57ce\u533a"},{id:"1409002",name:"\u6881\u5b50\u6e56\u533a"},{id:"1409003",name:"\u534e\u5bb9\u533a"}]},{id:"14100",name:"\u54b8\u5b81\u5e02",subItems:[{id:"1410001",name:"\u54b8\u5b89\u533a"},{id:"1410002",name:"\u8d64\u58c1\u5e02"},{id:"1410003",name:"\u5609\u9c7c\u53bf"},{id:"1410004",name:"\u901a\u57ce\u53bf"},{id:"1410005",name:"\u5d07\u9633\u53bf"},{id:"1410006",name:"\u901a\u5c71\u53bf"}]},{id:"14120",name:"\u5b5d\u611f\u5e02",subItems:[{id:"1412001",name:"\u5b5d\u5357\u533a"},{id:"1412002",name:"\u5e94\u57ce\u5e02"},{id:"1412003",name:"\u5b89\u9646\u5e02"},{id:"1412004",name:"\u6c49\u5ddd\u5e02"},{id:"1412005",name:"\u5b5d\u660c\u53bf"},{id:"1412006",name:"\u5927\u609f\u53bf"},{id:"1412007",name:"\u4e91\u68a6\u53bf"}]},{id:"14130",name:"\u968f\u5dde\u5e02",subItems:[{id:"1413001",name:"\u66fe\u90fd\u533a"},{id:"1413002",name:"\u5e7f\u6c34\u5e02"},{id:"1413003",name:"\u968f\u53bf"}]},{id:"14140",name:"\u6069\u65bd\u5dde",subItems:[{id:"1414001",name:"\u6069\u65bd\u5e02"},{id:"1414003",name:"\u5229\u5ddd\u5e02"},{id:"1414004",name:"\u5efa\u59cb\u53bf"},{id:"1414002",name:"\u9e64\u5cf0\u53bf"},{id:"1414005",name:"\u5df4\u4e1c\u53bf"},{id:"1414006",name:"\u5ba3\u6069\u53bf"},{id:"1414007",name:"\u54b8\u4e30\u53bf"},{id:"1414008",name:"\u6765\u51e4\u53bf"}]},{id:"14150",name:"\u9ec4\u5188\u5e02",subItems:[{id:"1415001",name:"\u9ec4\u5dde\u533a"},{id:"1415005",name:"\u6b66\u7a74\u5e02"},{id:"14110",name:"\u9ebb\u57ce\u5e02"},{id:"1415002",name:"\u6d60\u6c34\u53bf"},{id:"1415003",name:"\u82f1\u5c71\u53bf"},{id:"1415004",name:"\u8572\u6625\u53bf"},{id:"1415006",name:"\u9ec4\u6885\u53bf"},{id:"1415007",name:"\u56e2\u98ce\u53bf"},{id:"1415008",name:"\u7ea2\u5b89\u53bf"},{id:"1415009",name:"\u7f57\u7530\u53bf"}]},{id:"14190",name:"\u7701\u76f4\u8f96",subItems:[{id:"14050",name:"\u6f5c\u6c5f\u5e02"},{id:"14160",name:"\u4ed9\u6843\u5e02"},{id:"14170",name:"\u5929\u95e8\u5e02"},{id:"14180",name:"\u795e\u519c\u67b6"}]}]},{id:"15000",name:"\u6e56\u5357\u7701",subItems:[{id:"15",name:"\u957f\u6c99\u5e02",subItems:[{id:"1501",name:"\u8299\u84c9\u533a"},{id:"1503",name:"\u5929\u5fc3\u533a"},{id:"1505",name:"\u5cb3\u9e93\u533a"},{id:"1506",name:"\u5f00\u798f\u533a"},{id:"1507",name:"\u96e8\u82b1\u533a"},{id:"1508",name:"\u6d4f\u9633\u5e02"},{id:"1504",name:"\u5b81\u4e61\u53bf"},{id:"1502",name:"\u671b\u57ce\u53bf"},{id:"1509",name:"\u957f\u6c99\u53bf"}]},{id:"15030",name:"\u6e58\u6f6d\u5e02",subItems:[{id:"1503001",name:"\u96e8\u6e56\u533a"},{id:"1503002",name:"\u5cb3\u5858\u533a"},{id:"1503003",name:"\u6e58\u4e61\u5e02"},{id:"1503004",name:"\u97f6\u5c71\u5e02"},{id:"1503005",name:"\u6e58\u6f6d\u53bf"}]},{id:"15020",name:"\u682a\u6d32\u5e02",subItems:[{id:"1502001",name:"\u8377\u5858\u533a"},{id:"1502003",name:"\u82a6\u6dde\u533a"},{id:"1502005",name:"\u77f3\u5cf0\u533a"},{id:"1502006",name:"\u5929\u5143\u533a"},{id:"1502007",name:"\u91b4\u9675\u5e02"},{id:"1502002",name:"\u8336\u9675\u53bf"},{id:"1502004",name:"\u708e\u9675\u53bf"},{id:"1502008",name:"\u682a\u6d32\u53bf"},{id:"1502009",name:"\u6538\u53bf"}]},{id:"15040",name:"\u5e38\u5fb7\u5e02",subItems:[{id:"1504001",name:"\u6b66\u9675\u533a"},{id:"1504003",name:"\u9f0e\u57ce\u533a"},{id:"1504005",name:"\u6d25\u5e02\u5e02"},{id:"1504002",name:"\u6843\u6e90\u53bf"},{id:"1504004",name:"\u77f3\u95e8\u53bf"},{id:"1504006",name:"\u5b89\u4e61\u53bf"},{id:"1504007",name:"\u6c49\u5bff\u53bf"},{id:"1504009",name:"\u4e34\u6fa7\u53bf"},{id:"1504008",name:"\u6fa7\u53bf"}]},{id:"15050",name:"\u8861\u9633\u5e02",subItems:[{id:"1505001",name:"\u73e0\u6656\u533a"},{id:"1505003",name:"\u96c1\u5cf0\u533a"},{id:"1505005",name:"\u77f3\u9f13\u533a"},{id:"1505007",name:"\u84b8\u6e58\u533a"},{id:"1505009",name:"\u5357\u5cb3\u533a"},{id:"1505011",name:"\u8012\u9633\u5e02"},{id:"1505012",name:"\u5e38\u5b81\u5e02"},{id:"1505002",name:"\u8861\u9633\u53bf"},{id:"1505004",name:"\u8861\u5357\u53bf"},{id:"1505006",name:"\u8861\u5c71\u53bf"},{id:"1505008",name:"\u8861\u4e1c\u53bf"},{id:"1505010",name:"\u7941\u4e1c\u53bf"}]},{id:"15060",name:"\u76ca\u9633\u5e02",subItems:[{id:"1506001",name:"\u8d44\u9633\u533a"},{id:"1506002",name:"\u8d6b\u5c71\u533a"},{id:"1506003",name:"\u6c85\u6c5f\u5e02"},{id:"1506004",name:"\u5357\u53bf"},{id:"1506005",name:"\u6843\u6c5f\u53bf"},{id:"1506006",name:"\u5b89\u5316\u53bf"}]},{id:"15070",name:"\u90f4\u5dde\u5e02",subItems:[{id:"1507001",name:"\u5317\u6e56\u533a"},{id:"1507003",name:"\u82cf\u4ed9\u533a"},{id:"1507005",name:"\u8d44\u5174\u5e02"},{id:"1507002",name:"\u4e34\u6b66\u53bf"},{id:"1507004",name:"\u6c5d\u57ce\u53bf"},{id:"1507006",name:"\u6842\u4e1c\u53bf"},{id:"1507007",name:"\u6842\u9633\u53bf"},{id:"1507008",name:"\u5b89\u4ec1\u53bf"},{id:"1507009",name:"\u5b9c\u7ae0\u53bf"},{id:"1507010",name:"\u6c38\u5174\u53bf"},{id:"1507011",name:"\u5609\u79be\u53bf"}]},{id:"15080",name:"\u5cb3\u9633\u5e02",subItems:[{id:"1508001",name:"\u5cb3\u9633\u697c\u533a"},{id:"1508003",name:"\u4e91\u6eaa\u533a"},{id:"1508005",name:"\u541b\u5c71\u533a"},{id:"1508006",name:"\u6c68\u7f57\u5e02"},{id:"1508007",name:"\u4e34\u6e58\u5e02"},{id:"1508002",name:"\u6e58\u9634\u53bf"},{id:"1508004",name:"\u5e73\u6c5f\u53bf"},{id:"1508008",name:"\u5cb3\u9633\u53bf"},{id:"1508009",name:"\u534e\u5bb9\u53bf"}]},{id:"15090",name:"\u5f20\u5bb6\u754c\u5e02",subItems:[{id:"1509001",name:"\u6c38\u5b9a\u533a"},{id:"1509002",name:"\u6b66\u9675\u6e90"},{id:"1509003",name:"\u6148\u5229\u53bf"},{id:"1509004",name:"\u6851\u690d\u53bf"}]},{id:"15100",name:"\u6c38\u5dde\u5e02",subItems:[{id:"1510001",name:"\u96f6\u9675\u533a"},{id:"1510003",name:"\u51b7\u6c34\u6ee9\u533a"},{id:"1510002",name:"\u5b81\u8fdc\u53bf"},{id:"1510004",name:"\u84dd\u5c71\u53bf"},{id:"1510005",name:"\u7941\u9633\u53bf"},{id:"1510006",name:"\u65b0\u7530\u53bf"},{id:"1510007",name:"\u4e1c\u5b89\u53bf"},{id:"1510008",name:"\u6c5f\u534e\u53bf"},{id:"1510009",name:"\u53cc\u724c\u53bf"},{id:"1510011",name:"\u6c5f\u6c38\u53bf"},{id:"1510010",name:"\u9053\u53bf"}]},{id:"15120",name:"\u5a04\u5e95\u5e02",subItems:[{id:"1512001",name:"\u5a04\u661f\u533a"},{id:"1512003",name:"\u6d9f\u6e90\u5e02"},{id:"1512002",name:"\u51b7\u6c34\u6c5f\u5e02"},{id:"1512004",name:"\u53cc\u5cf0\u53bf"},{id:"1512005",name:"\u65b0\u5316\u53bf"}]},{id:"15130",name:"\u6000\u5316\u5e02",subItems:[{id:"1513001",name:"\u9e64\u57ce\u533a"},{id:"1513003",name:"\u6d2a\u6c5f\u5e02"},{id:"1513002",name:"\u9ebb\u9633\u53bf"},{id:"1513004",name:"\u65b0\u6643\u53bf"},{id:"1513005",name:"\u6c85\u9675\u53bf"},{id:"1513006",name:"\u82b7\u6c5f\u53bf"},{id:"1513007",name:"\u8fb0\u6eaa\u53bf"},{id:"1513008",name:"\u9756\u5dde\u53bf"},{id:"1513009",name:"\u6e86\u6d66\u53bf"},{id:"1513010",name:"\u901a\u9053\u53bf"},{id:"1513011",name:"\u4e2d\u65b9\u53bf"},{id:"1513012",name:"\u4f1a\u540c\u53bf"}]},{id:"15140",name:"\u90b5\u9633\u5e02",subItems:[{id:"1514001",name:"\u53cc\u6e05\u533a"},{id:"1514003",name:"\u5927\u7965\u533a"},{id:"1514005",name:"\u5317\u5854\u533a"},{id:"1514007",name:"\u6b66\u5188\u5e02"},{id:"1514002",name:"\u9686\u56de\u53bf"},{id:"1514004",name:"\u6d1e\u53e3\u53bf"},{id:"1514006",name:"\u7ee5\u5b81\u53bf"},{id:"1514008",name:"\u65b0\u5b81\u53bf"},{id:"1514009",name:"\u90b5\u4e1c\u53bf"},{id:"1514010",name:"\u57ce\u6b65\u53bf"},{id:"1514011",name:"\u65b0\u90b5\u53bf"},{id:"1514012",name:"\u90b5\u9633\u53bf"}]},{id:"15150",name:"\u6e58\u897f\u5dde",subItems:[{id:"15110",name:"\u5409\u9996\u5e02"},{id:"1515001",name:"\u9f99\u5c71\u53bf"},{id:"1515002",name:"\u6cf8\u6eaa\u53bf"},{id:"1515003",name:"\u51e4\u51f0\u53bf"},{id:"1515004",name:"\u82b1\u57a3\u53bf"},{id:"1515005",name:"\u4fdd\u9756\u53bf"},{id:"1515006",name:"\u53e4\u4e08\u53bf"},{id:"1515007",name:"\u6c38\u987a\u53bf"}]}]},{id:"5000",name:"\u5409\u6797\u7701",subItems:[{id:"10",name:"\u957f\u6625\u5e02",subItems:[{id:"1001",name:"\u671d\u9633\u533a"},{id:"1003",name:"\u5357\u5173\u533a"},{id:"1005",name:"\u5bbd\u57ce\u533a"},{id:"1007",name:"\u4e8c\u9053\u533a"},{id:"1008",name:"\u7eff\u56ed\u533a"},{id:"1009",name:"\u53cc\u9633\u533a"},{id:"1010",name:"\u5fb7\u60e0\u5e02"},{id:"1002",name:"\u4e5d\u53f0\u5e02"},{id:"1004",name:"\u6986\u6811\u5e02"},{id:"1006",name:"\u519c\u5b89\u53bf"}]},{id:"5020",name:"\u5409\u6797\u5e02",subItems:[{id:"502002",name:"\u660c\u9091\u533a"},{id:"502004",name:"\u9f99\u6f6d\u533a"},{id:"502005",name:"\u4e30\u6ee1\u533a"},{id:"502009",name:"\u8239\u8425\u533a"},{id:"502001",name:"\u78d0\u77f3\u5e02"},{id:"502006",name:"\u86df\u6cb3\u5e02"},{id:"502007",name:"\u6866\u7538\u5e02"},{id:"502008",name:"\u8212\u5170\u5e02"},{id:"502003",name:"\u6c38\u5409\u53bf"}]},{id:"5030",name:"\u8fbd\u6e90\u5e02",subItems:[{id:"503001",name:"\u9f99\u5c71\u533a"},{id:"503002",name:"\u897f\u5b89\u533a"},{id:"503003",name:"\u4e1c\u4e30\u53bf"},{id:"503004",name:"\u4e1c\u8fbd\u53bf"}]},{id:"5040",name:"\u56db\u5e73\u5e02",subItems:[{id:"504001",name:"\u94c1\u897f\u533a"},{id:"504002",name:"\u94c1\u4e1c\u533a"},{id:"504003",name:"\u516c\u4e3b\u5cad\u5e02"},{id:"504004",name:"\u53cc\u8fbd\u5e02"},{id:"504005",name:"\u68a8\u6811\u53bf"},{id:"504006",name:"\u4f0a\u901a\u53bf"}]},{id:"5050",name:"\u901a\u5316\u5e02",subItems:[{id:"505001",name:"\u4e1c\u660c\u533a"},{id:"505002",name:"\u4e8c\u9053\u6c5f\u533a"},{id:"505003",name:"\u6885\u6cb3\u53e3\u5e02"},{id:"505004",name:"\u96c6\u5b89\u5e02"},{id:"505005",name:"\u901a\u5316\u53bf"},{id:"505006",name:"\u8f89\u5357\u53bf"},{id:"505007",name:"\u67f3\u6cb3\u53bf"}]},{id:"5070",name:"\u677e\u539f\u5e02",subItems:[{id:"507001",name:"\u5b81\u6c5f\u533a"},{id:"507002",name:"\u957f\u5cad\u53bf"},{id:"507003",name:"\u4e7e\u5b89\u53bf"},{id:"507004",name:"\u6276\u4f59\u53bf"},{id:"507005",name:"\u524d\u90ed\u5c14\u7f57\u65af\u53bf"}]},{id:"5080",name:"\u767d\u57ce\u5e02",subItems:[{id:"508001",name:"\u6d2e\u5317\u533a"},{id:"508002",name:"\u6d2e\u5357\u5e02"},{id:"508003",name:"\u5927\u5b89\u5e02"},{id:"508004",name:"\u9547\u8d49\u53bf"},{id:"508005",name:"\u901a\u6986\u53bf"}]},{id:"5090",name:"\u767d\u5c71\u5e02",subItems:[{id:"509001",name:"\u516b\u9053\u6c5f\u533a"},{id:"509002",name:"\u6c5f\u6e90\u533a"},{id:"509003",name:"\u4e34\u6c5f\u5e02"},{id:"509004",name:"\u629a\u677e\u53bf"},{id:"509005",name:"\u9756\u5b87\u53bf"},{id:"509006",name:"\u957f\u767d\u53bf"}]},{id:"5100",name:"\u5ef6\u8fb9",subItems:[{id:"5060",name:"\u5ef6\u5409\u5e02"},{id:"510003",name:"\u56fe\u4eec\u5e02"},{id:"510004",name:"\u6566\u5316\u5e02"},{id:"510005",name:"\u73f2\u6625\u5e02"},{id:"510006",name:"\u9f99\u4e95\u5e02"},{id:"510007",name:"\u548c\u9f99\u5e02"},{id:"510002",name:"\u5b89\u56fe\u53bf"},{id:"510008",name:"\u6c6a\u6e05\u53bf"}]}]},{id:"11000",name:"\u6c5f\u897f\u7701",subItems:[{id:"95",name:"\u5357\u660c\u5e02",subItems:[{id:"9501",name:"\u4e1c\u6e56\u533a"},{id:"9503",name:"\u897f\u6e56\u533a"},{id:"9506",name:"\u6e7e\u91cc\u533a"},{id:"9505",name:"\u9752\u4e91\u8c31\u533a"},{id:"9507",name:"\u9752\u5c71\u6e56\u533a"},{id:"9504",name:"\u8fdb\u8d24\u53bf"},{id:"9502",name:"\u5b89\u4e49\u53bf"},{id:"9508",name:"\u5357\u660c\u53bf"},{id:"9509",name:"\u65b0\u5efa\u53bf"}]},{id:"11020",name:"\u4e5d\u6c5f\u5e02",subItems:[{id:"1102001",name:"\u6d54\u9633\u533a"},{id:"1102003",name:"\u5e90\u5c71\u533a"},{id:"1102005",name:"\u745e\u660c\u5e02"},{id:"1102002",name:"\u5fb7\u5b89\u53bf"},{id:"1102004",name:"\u661f\u5b50\u53bf"},{id:"1102006",name:"\u90fd\u660c\u53bf"},{id:"1102007",name:"\u4e5d\u6c5f\u53bf"},{id:"1102008",name:"\u6e56\u53e3\u53bf"},{id:"1102009",name:"\u6b66\u5b81\u53bf"},{id:"1102010",name:"\u5f6d\u6cfd\u53bf"},{id:"1102011",name:"\u4fee\u6c34\u53bf"},{id:"1102012",name:"\u6c38\u4fee\u53bf"}]},{id:"11030",name:"\u666f\u5fb7\u9547\u5e02",subItems:[{id:"1103001",name:"\u73e0\u5c71\u533a"},{id:"1103002",name:"\u660c\u6c5f\u533a"},{id:"1103003",name:"\u4e50\u5e73\u5e02"},{id:"1103004",name:"\u6d6e\u6881\u53bf"}]},{id:"11040",name:"\u65b0\u4f59\u5e02",subItems:[{id:"1104001",name:"\u6e1d\u6c34\u533a"},{id:"1104002",name:"\u5206\u5b9c\u53bf"}]},{id:"11050",name:"\u9e70\u6f6d\u5e02",subItems:[{id:"1105001",name:"\u6708\u6e56\u533a"},{id:"1105002",name:"\u8d35\u6eaa\u5e02"},{id:"1105003",name:"\u4f59\u6c5f\u53bf"}]},{id:"11060",name:"\u4e0a\u9976\u5e02",subItems:[{id:"1106001",name:"\u4fe1\u5dde\u533a"},{id:"1106003",name:"\u5fb7\u5174\u5e02"},{id:"1106002",name:"\u5f0b\u9633\u53bf"},{id:"1106004",name:"\u4f59\u5e72\u53bf"},{id:"1106005",name:"\u4e0a\u9976\u53bf"},{id:"1106006",name:"\u9131\u9633\u53bf"},{id:"1106007",name:"\u5e7f\u4e30\u53bf"},{id:"1106008",name:"\u4e07\u5e74\u53bf"},{id:"1106009",name:"\u7389\u5c71\u53bf"},{id:"1106010",name:"\u5a7a\u6e90\u53bf"},{id:"1106011",name:"\u94c5\u5c71\u53bf"},{id:"1106012",name:"\u6a2a\u5cf0\u53bf"}]},{id:"11070",name:"\u840d\u4e61\u5e02",subItems:[{id:"1107001",name:"\u5b89\u6e90\u533a"},{id:"1107002",name:"\u6e58\u4e1c\u533a"},{id:"1107003",name:"\u83b2\u82b1\u53bf"},{id:"1107004",name:"\u82a6\u6eaa\u53bf"},{id:"1107005",name:"\u4e0a\u6817\u53bf"}]},{id:"11080",name:"\u5409\u5b89\u5e02",subItems:[{id:"1108001",name:"\u5409\u5dde\u533a"},{id:"1108003",name:"\u9752\u539f\u533a"},{id:"1108005",name:"\u4e95\u5188\u5c71\u5e02"},{id:"1108002",name:"\u6c38\u4e30\u53bf"},{id:"1108004",name:"\u6cf0\u548c\u53bf"},{id:"1108006",name:"\u9042\u5ddd\u53bf"},{id:"1108007",name:"\u5409\u5b89\u53bf"},{id:"1108008",name:"\u4e07\u5b89\u53bf"},{id:"1108009",name:"\u5409\u6c34\u53bf"},{id:"1108010",name:"\u5b89\u798f\u53bf"},{id:"1108011",name:"\u5ce1\u6c5f\u53bf"},{id:"1108012",name:"\u6c38\u65b0\u53bf"},{id:"1108013",name:"\u65b0\u5e72\u53bf"}]},{id:"11090",name:"\u8d63\u5dde\u5e02",subItems:[{id:"1109001",name:"\u7ae0\u8d21\u533a"},{id:"1109004",name:"\u745e\u91d1\u5e02"},{id:"1109007",name:"\u5357\u5eb7\u5e02"},{id:"1109010",name:"\u8d63\u53bf"},{id:"1109003",name:"\u5174\u56fd\u53bf"},{id:"1109002",name:"\u5d07\u4e49\u53bf"},{id:"1109005",name:"\u5b89\u8fdc\u53bf"},{id:"1109006",name:"\u4f1a\u660c\u53bf"},{id:"1109008",name:"\u9f99\u5357\u53bf"},{id:"1109009",name:"\u5bfb\u4e4c\u53bf"},{id:"1109011",name:"\u5b9a\u5357\u53bf"},{id:"1109012",name:"\u77f3\u57ce\u53bf"},{id:"1109013",name:"\u4fe1\u4e30\u53bf"},{id:"1109014",name:"\u5168\u5357\u53bf"},{id:"1109015",name:"\u5927\u4f59\u53bf"},{id:"1109016",name:"\u5b81\u90fd\u53bf"},{id:"1109017",name:"\u4e0a\u72b9\u53bf"},{id:"1109018",name:"\u4e8e\u90fd\u53bf"}]},{id:"11100",name:"\u5b9c\u6625\u5e02",subItems:[{id:"1110001",name:"\u8881\u5dde\u533a"},{id:"1110005",name:"\u6a1f\u6811\u5e02"},{id:"1110003",name:"\u4e30\u57ce\u5e02"},{id:"1110007",name:"\u9ad8\u5b89\u5e02"},{id:"1110002",name:"\u5b9c\u4e30\u53bf"},{id:"1110004",name:"\u9756\u5b89\u53bf"},{id:"1110006",name:"\u94dc\u9f13\u53bf"},{id:"1110008",name:"\u5949\u65b0\u53bf"},{id:"1110009",name:"\u4e07\u8f7d\u53bf"},{id:"1110010",name:"\u4e0a\u9ad8\u53bf"}]},{id:"11110",name:"\u629a\u5dde\u5e02",subItems:[{id:"1111001",name:"\u4e34\u5ddd\u533a"},{id:"1111002",name:"\u91d1\u6eaa\u53bf"},{id:"1111003",name:"\u5357\u57ce\u53bf"},{id:"1111004",name:"\u8d44\u6eaa\u53bf"},{id:"1111005",name:"\u9ece\u5ddd\u53bf"},{id:"1111006",name:"\u4e1c\u4e61\u53bf"},{id:"1111007",name:"\u5357\u4e30\u53bf"},{id:"1111008",name:"\u5e7f\u660c\u53bf"},{id:"1111009",name:"\u5d07\u4ec1\u53bf"},{id:"1111010",name:"\u4e50\u5b89\u53bf"},{id:"1111011",name:"\u5b9c\u9ec4\u53bf"}]}]},{id:"4000",name:"\u8fbd\u5b81\u7701",subItems:[{id:"120",name:"\u6c88\u9633\u5e02",subItems:[{id:"12001",name:"\u6c88\u6cb3\u533a"},{id:"12003",name:"\u548c\u5e73\u533a"},{id:"12004",name:"\u4e8e\u6d2a\u533a"},{id:"12005",name:"\u5927\u4e1c\u533a"},{id:"12007",name:"\u7687\u59d1\u533a"},{id:"12013",name:"\u4e1c\u9675\u533a"},{id:"12009",name:"\u94c1\u897f\u533a"},{id:"12002",name:"\u6c88\u5317\u65b0\u533a"},{id:"12011",name:"\u82cf\u5bb6\u5c6f\u533a"},{id:"12006",name:"\u65b0\u6c11\u5e02"},{id:"12008",name:"\u8fbd\u4e2d\u53bf"},{id:"12010",name:"\u5eb7\u5e73\u53bf"},{id:"12012",name:"\u6cd5\u5e93\u53bf"}]},{id:"4030",name:"\u978d\u5c71\u5e02",subItems:[{id:"403001",name:"\u94c1\u4e1c\u533a"},{id:"403002",name:"\u94c1\u897f\u533a"},{id:"403003",name:"\u7acb\u5c71\u533a"},{id:"403004",name:"\u5343\u5c71\u533a"},{id:"403005",name:"\u6d77\u57ce\u5e02"},{id:"403006",name:"\u53f0\u5b89\u53bf"},{id:"403007",name:"\u5cab\u5ca9\u53bf"}]},{id:"30",name:"\u5927\u8fde\u5e02",subItems:[{id:"3001",name:"\u897f\u5c97\u533a"},{id:"3003",name:"\u4e2d\u5c71\u533a"},{id:"3004",name:"\u5e84\u6cb3\u5e02"},{id:"3009",name:"\u91d1\u5dde\u533a"},{id:"3005",name:"\u6c99\u6cb3\u53e3\u533a"},{id:"3007",name:"\u7518\u4e95\u5b50\u533a"},{id:"3008",name:"\u65c5\u987a\u53e3\u533a"},{id:"3002",name:"\u666e\u5170\u5e97\u5e02"},{id:"3010",name:"\u74e6\u623f\u5e97\u5e02"},{id:"3006",name:"\u957f\u6d77\u53bf"}]},{id:"4040",name:"\u846b\u82a6\u5c9b\u5e02",subItems:[{id:"404001",name:"\u9f99\u6e2f\u533a"},{id:"404002",name:"\u8fde\u5c71\u533a"},{id:"404003",name:"\u5357\u7968\u533a"},{id:"404004",name:"\u5174\u57ce\u5e02"},{id:"404005",name:"\u7ee5\u4e2d\u53bf"},{id:"404006",name:"\u5efa\u660c\u53bf"}]},{id:"4050",name:"\u8425\u53e3\u5e02",subItems:[{id:"405001",name:"\u7ad9\u524d\u533a"},{id:"405002",name:"\u897f\u5e02\u533a"},{id:"405004",name:"\u8001\u8fb9\u533a"},{id:"405003",name:"\u9c85\u9c7c\u5708\u533a"},{id:"405005",name:"\u76d6\u5dde\u5e02"},{id:"405006",name:"\u5927\u77f3\u6865\u5e02"}]},{id:"4010",name:"\u629a\u987a\u5e02",subItems:[{id:"401001",name:"\u987a\u57ce\u533a"},{id:"401002",name:"\u65b0\u629a\u533a"},{id:"401003",name:"\u4e1c\u6d32\u533a"},{id:"401004",name:"\u671b\u82b1\u533a"},{id:"401005",name:"\u629a\u987a\u53bf"},{id:"401006",name:"\u65b0\u5bbe\u53bf"},{id:"401007",name:"\u6e05\u539f\u53bf"}]},{id:"4020",name:"\u9526\u5dde\u5e02",subItems:[{id:"402001",name:"\u592a\u548c\u533a"},{id:"402002",name:"\u53e4\u5854\u533a"},{id:"402003",name:"\u51cc\u6cb3\u533a"},{id:"402004",name:"\u51cc\u6d77\u5e02"},{id:"402005",name:"\u5317\u9547\u5e02"},{id:"402006",name:"\u9ed1\u5c71\u53bf"},{id:"402007",name:"\u4e49\u53bf"}]},{id:"4060",name:"\u4e39\u4e1c\u5e02",subItems:[{id:"406001",name:"\u632f\u5174\u533a"},{id:"406002",name:"\u5143\u5b9d\u533a"},{id:"406003",name:"\u632f\u5b89\u533a"},{id:"406004",name:"\u4e1c\u6e2f\u5e02"},{id:"406005",name:"\u51e4\u57ce\u5e02"},{id:"406006",name:"\u5bbd\u7538\u53bf"}]},{id:"4070",name:"\u672c\u6eaa\u5e02",subItems:[{id:"407001",name:"\u5e73\u5c71\u533a"},{id:"407002",name:"\u6eaa\u6e56\u533a"},{id:"407003",name:"\u660e\u5c71\u533a"},{id:"407004",name:"\u5357\u82ac\u533a"},{id:"407005",name:"\u672c\u6eaa\u53bf"},{id:"407006",name:"\u6853\u4ec1\u53bf"}]},{id:"4080",name:"\u8fbd\u9633\u5e02",subItems:[{id:"408001",name:"\u767d\u5854\u533a"},{id:"408002",name:"\u6587\u5723\u533a"},{id:"408003",name:"\u5b8f\u4f1f\u533a"},{id:"408004",name:"\u5f13\u957f\u5cad\u533a"},{id:"408005",name:"\u592a\u5b50\u6cb3\u533a"},{id:"408006",name:"\u706f\u5854\u5e02"},{id:"408007",name:"\u8fbd\u9633\u53bf"}]},{id:"4090",name:"\u94c1\u5cad\u5e02",subItems:[{id:"409001",name:"\u94f6\u5dde\u533a"},{id:"409002",name:"\u6e05\u6cb3\u533a"},{id:"409004",name:"\u5f00\u539f\u5e02"},{id:"409003",name:"\u8c03\u5175\u5c71\u5e02"},{id:"409005",name:"\u94c1\u5cad\u53bf"},{id:"409006",name:"\u897f\u4e30\u53bf"},{id:"409007",name:"\u660c\u56fe\u53bf"}]},{id:"4100",name:"\u76d8\u9526\u5e02",subItems:[{id:"410001",name:"\u5174\u9686\u53f0\u533a"},{id:"410002",name:"\u53cc\u53f0\u5b50\u533a"},{id:"410003",name:"\u5927\u6d3c\u53bf"},{id:"410004",name:"\u76d8\u5c71\u53bf"}]},{id:"4110",name:"\u961c\u65b0\u5e02",subItems:[{id:"411001",name:"\u6d77\u5dde\u533a"},{id:"411002",name:"\u65b0\u90b1\u533a"},{id:"411003",name:"\u592a\u5e73\u533a"},{id:"411005",name:"\u7ec6\u6cb3\u533a"},{id:"411004",name:"\u6e05\u6cb3\u95e8\u533a"},{id:"411006",name:"\u961c\u65b0\u53bf"},{id:"411007",name:"\u5f70\u6b66\u53bf"}]},{id:"4120",name:"\u671d\u9633\u5e02",subItems:[{id:"412001",name:"\u53cc\u5854\u533a"},{id:"412002",name:"\u9f99\u57ce\u533a"},{id:"412003",name:"\u5317\u7968\u5e02"},{id:"412004",name:"\u51cc\u6e90\u5e02"},{id:"412005",name:"\u671d\u9633\u53bf"},{id:"412006",name:"\u5efa\u5e73\u53bf"},{id:"412007",name:"\u5580\u5587\u6c81\u5de6\u7ffc\u53bf"}]}]},{id:"3000",name:"\u5185\u8499\u53e4",subItems:[{id:"70",name:"\u547c\u548c\u6d69\u7279\u5e02",subItems:[{id:"7001",name:"\u56de\u6c11\u533a"},{id:"7003",name:"\u7389\u6cc9\u533a"},{id:"7005",name:"\u65b0\u57ce\u533a"},{id:"7006",name:"\u8d5b\u7f55\u533a"},{id:"7009",name:"\u6b66\u5ddd\u53bf"},{id:"7007",name:"\u6258\u514b\u6258\u53bf"},{id:"7008",name:"\u6e05\u6c34\u6cb3\u53bf"},{id:"7002",name:"\u548c\u6797\u683c\u5c14\u53bf"},{id:"7004",name:"\u571f\u9ed8\u7279\u5de6\u65d7"}]},{id:"3020",name:"\u5305\u5934\u5e02",subItems:[{id:"302003",name:"\u9752\u5c71\u533a"},{id:"302005",name:"\u4e1c\u6cb3\u533a"},{id:"302006",name:"\u4e5d\u539f\u533a"},{id:"302007",name:"\u77f3\u62d0\u533a"},{id:"302001",name:"\u6606\u90fd\u4ed1\u533a"},{id:"302008",name:"\u767d\u4e91\u77ff\u533a"},{id:"302009",name:"\u56fa\u9633\u53bf"},{id:"302002",name:"\u571f\u9ed8\u7279\u53f3\u65d7"},{id:"302004",name:"\u8fbe\u5c14\u7f55\u8302\u660e\u5b89\u8054\u5408\u65d7"}]},{id:"3030",name:"\u8d64\u5cf0\u5e02",subItems:[{id:"303001",name:"\u7ea2\u5c71\u533a"},{id:"303005",name:"\u677e\u5c71\u533a"},{id:"303003",name:"\u5143\u5b9d\u5c71\u533a"},{id:"303009",name:"\u6797\u897f\u53bf"},{id:"303007",name:"\u5b81\u57ce\u53bf"},{id:"303002",name:"\u6556\u6c49\u65d7"},{id:"303006",name:"\u7fc1\u725b\u7279\u65d7"},{id:"303010",name:"\u5df4\u6797\u53f3\u65d7"},{id:"303008",name:"\u514b\u4ec0\u514b\u817e\u65d7"},{id:"303011",name:"\u5580\u5587\u6c81\u65d7"},{id:"303012",name:"\u5df4\u6797\u5de6\u65d7"},{id:"303004",name:"\u963f\u9c81\u79d1\u5c14\u6c81\u65d7"}]},{id:"3040",name:"\u901a\u8fbd\u5e02",subItems:[{id:"304001",name:"\u79d1\u5c14\u6c81\u533a"},{id:"304003",name:"\u970d\u6797\u90ed\u52d2\u5e02"},{id:"304004",name:"\u5f00\u9c81\u53bf"},{id:"304007",name:"\u5e93\u4f26\u65d7"},{id:"304008",name:"\u5948\u66fc\u65d7"},{id:"304002",name:"\u624e\u9c81\u7279\u65d7"},{id:"304005",name:"\u79d1\u5c14\u6c81\u5de6\u7ffc\u4e2d\u65d7"},{id:"304006",name:"\u79d1\u5c14\u6c81\u5de6\u7ffc\u540e\u65d7"}]},{id:"3050",name:"\u4e4c\u5170\u5bdf\u5e03\u5e02",subItems:[{id:"305001",name:"\u96c6\u5b81\u533a"},{id:"305003",name:"\u4e30\u9547\u5e02"},{id:"305005",name:"\u5174\u548c\u53bf"},{id:"305007",name:"\u5353\u8d44\u53bf"},{id:"305009",name:"\u5546\u90fd\u53bf"},{id:"305010",name:"\u51c9\u57ce\u53bf"},{id:"305011",name:"\u5316\u5fb7\u53bf"},{id:"305002",name:"\u56db\u5b50\u738b\u65d7"},{id:"305004",name:"\u5bdf\u54c8\u5c14\u53f3\u7ffc\u524d\u65d7"},{id:"305006",name:"\u5bdf\u54c8\u5c14\u53f3\u7ffc\u4e2d\u65d7"},{id:"305008",name:"\u5bdf\u54c8\u5c14\u53f3\u7ffc\u540e\u65d7"}]},{id:"3060",name:"\u4e4c\u6d77\u5e02",subItems:[{id:"306001",name:"\u6d77\u52c3\u6e7e\u533a"},{id:"306002",name:"\u4e4c\u8fbe\u533a"},{id:"306003",name:"\u6d77\u5357\u533a"}]},{id:"3070",name:"\u9102\u5c14\u591a\u65af\u5e02",subItems:[{id:"307001",name:"\u4e1c\u80dc\u533a"},{id:"307004",name:"\u4e4c\u5ba1\u65d7"},{id:"307008",name:"\u676d\u9526\u65d7"},{id:"307003",name:"\u51c6\u683c\u5c14\u65d7"},{id:"307002",name:"\u8fbe\u62c9\u7279\u65d7"},{id:"307006",name:"\u9102\u6258\u514b\u65d7"},{id:"307005",name:"\u4f0a\u91d1\u970d\u6d1b\u65d7"},{id:"307007",name:"\u9102\u6258\u514b\u524d\u65d7"}]},{id:"3080",name:"\u547c\u4f26\u8d1d\u5c14\u5e02",subItems:[{id:"308001",name:"\u6d77\u62c9\u5c14\u533a"},{id:"308009",name:"\u6839\u6cb3\u5e02"},{id:"308003",name:"\u6ee1\u6d32\u91cc\u5e02"},{id:"308005",name:"\u7259\u514b\u77f3\u5e02"},{id:"308007",name:"\u624e\u5170\u5c6f\u5e02"},{id:"308011",name:"\u989d\u5c14\u53e4\u7eb3\u5e02"},{id:"308002",name:"\u963f\u8363\u65d7"},{id:"308013",name:"\u9648\u5df4\u5c14\u864e\u65d7"},{id:"308004",name:"\u65b0\u5df4\u5c14\u864e\u5de6\u65d7"},{id:"308006",name:"\u65b0\u5df4\u5c14\u864e\u53f3\u65d7"},{id:"308008",name:"\u9102\u4f26\u6625\u81ea\u6cbb\u65d7"},{id:"308012",name:"\u9102\u6e29\u514b\u65cf\u81ea\u6cbb\u65d7"},{id:"308010",name:"\u83ab\u529b\u8fbe\u74e6\u8fbe\u65a1\u5c14\u65cf\u81ea\u6cbb\u65d7"}]},{id:"3090",name:"\u5df4\u5f66\u6dd6\u5c14\u5e02",subItems:[{id:"309001",name:"\u4e34\u6cb3\u533a"},{id:"309002",name:"\u4e94\u539f\u53bf"},{id:"309003",name:"\u78f4\u53e3\u53bf"},{id:"309004",name:"\u676d\u9526\u540e\u65d7"},{id:"309005",name:"\u4e4c\u62c9\u7279\u4e2d\u65d7"},{id:"309006",name:"\u4e4c\u62c9\u7279\u524d\u65d7"},{id:"309007",name:"\u4e4c\u62c9\u7279\u540e\u65d7"}]},{id:"3100",name:"\u9521\u6797\u90ed\u52d2\u76df",subItems:[{id:"310001",name:"\u9521\u6797\u6d69\u7279\u5e02"},{id:"310003",name:"\u4e8c\u8fde\u6d69\u7279\u5e02"},{id:"310005",name:"\u591a\u4f26\u53bf"},{id:"310008",name:"\u6b63\u84dd\u65d7"},{id:"310010",name:"\u9576\u9ec4\u65d7"},{id:"310006",name:"\u6b63\u9576\u767d\u65d7"},{id:"310004",name:"\u592a\u4ec6\u5bfa\u65d7"},{id:"310007",name:"\u963f\u5df4\u560e\u65d7"},{id:"310012",name:"\u82cf\u5c3c\u7279\u5de6\u65d7"},{id:"310002",name:"\u82cf\u5c3c\u7279\u53f3\u65d7"},{id:"310011",name:"\u4e1c\u4e4c\u73e0\u7a46\u6c81\u65d7"},{id:"310009",name:"\u897f\u4e4c\u73e0\u7a46\u6c81\u65d7"}]},{id:"3110",name:"\u5174\u5b89\u76df",subItems:[{id:"311001",name:"\u4e4c\u5170\u6d69\u7279\u5e02"},{id:"311002",name:"\u963f\u5c14\u5c71\u5e02"},{id:"311003",name:"\u7a81\u6cc9\u53bf"},{id:"311004",name:"\u624e\u8d49\u7279\u65d7"},{id:"311005",name:"\u79d1\u5c14\u6c81\u53f3\u7ffc\u524d\u65d7"},{id:"311006",name:"\u79d1\u5c14\u6c81\u53f3\u7ffc\u4e2d\u65d7"}]},{id:"3120",name:"\u963f\u62c9\u5584\u76df",subItems:[{id:"312001",name:"\u963f\u62c9\u5584\u5de6\u65d7"},{id:"312002",name:"\u963f\u62c9\u5584\u53f3\u65d7"},{id:"312003",name:"\u989d\u6d4e\u7eb3\u65d7"}]}]},{id:"26000",name:"\u5b81\u590f",subItems:[{id:"170",name:"\u94f6\u5ddd\u5e02",subItems:[{id:"17001",name:"\u5174\u5e86\u533a"},{id:"17002",name:"\u91d1\u51e4\u533a"},{id:"17003",name:"\u897f\u590f\u533a"},{id:"17006",name:"\u7075\u6b66\u5e02"},{id:"17004",name:"\u6c38\u5b81\u53bf"},{id:"17005",name:"\u8d3a\u5170\u53bf"}]},{id:"26010",name:"\u77f3\u5634\u5c71\u5e02",subItems:[{id:"2601001",name:"\u5927\u6b66\u53e3\u533a"},{id:"2601002",name:"\u60e0\u519c\u533a"},{id:"2601003",name:"\u5e73\u7f57\u53bf"}]},{id:"26020",name:"\u5434\u5fe0\u5e02",subItems:[{id:"2602001",name:"\u5229\u901a\u533a"},{id:"2602002",name:"\u7ea2\u5bfa\u5821\u533a"},{id:"2602003",name:"\u76d0\u6c60\u53bf"},{id:"2602004",name:"\u540c\u5fc3\u53bf"},{id:"2602005",name:"\u9752\u94dc\u5ce1\u5e02"}]},{id:"26030",name:"\u56fa\u539f\u5e02",subItems:[{id:"2603001",name:"\u539f\u5dde\u533a"},{id:"2603002",name:"\u897f\u5409\u53bf"},{id:"2603003",name:"\u9686\u5fb7\u53bf"},{id:"2603004",name:"\u6cfe\u6e90\u53bf"},{id:"2603005",name:"\u5f6d\u9633\u53bf"}]},{id:"26040",name:"\u4e2d\u536b\u5e02",subItems:[{id:"2604001",name:"\u6c99\u5761\u5934\u533a"},{id:"2604002",name:"\u4e2d\u5b81\u53bf"},{id:"2604003",name:"\u6d77\u539f\u53bf"}]}]},{id:"25000",name:"\u9752\u6d77\u7701",subItems:[{id:"165",name:"\u897f\u5b81\u5e02",subItems:[{id:"16501",name:"\u57ce\u4e1c\u533a"},{id:"16502",name:"\u57ce\u4e2d\u533a"},{id:"16503",name:"\u57ce\u897f\u533a"},{id:"16504",name:"\u57ce\u5317\u533a"},{id:"16505",name:"\u6e5f\u4e2d\u53bf"},{id:"16506",name:"\u6e5f\u6e90\u53bf"},{id:"16507",name:"\u5927\u901a\u53bf"}]},{id:"25010",name:"\u6d77\u4e1c",subItems:[{id:"2501001",name:"\u5e73\u5b89\u53bf"},{id:"2501002",name:"\u4e50\u90fd\u53bf"},{id:"2501003",name:"\u6c11\u548c\u53bf"},{id:"2501004",name:"\u4e92\u52a9\u53bf"},{id:"2501005",name:"\u5316\u9686\u53bf"},{id:"2501006",name:"\u5faa\u5316\u53bf"}]},{id:"25020",name:"\u6d77\u5317\u5dde",subItems:[{id:"2502001",name:"\u6d77\u664f\u53bf"},{id:"2502002",name:"\u7941\u8fde\u53bf"},{id:"2502003",name:"\u521a\u5bdf\u53bf"},{id:"2502004",name:"\u95e8\u6e90\u53bf"}]},{id:"25030",name:"\u9ec4\u5357\u5dde",subItems:[{id:"2503001",name:"\u540c\u4ec1\u53bf"},{id:"2503002",name:"\u5c16\u624e\u53bf"},{id:"2503003",name:"\u6cfd\u5e93\u53bf"},{id:"2503004",name:"\u6cb3\u5357\u53bf"}]},{id:"25040",name:"\u6d77\u5357\u5dde",subItems:[{id:"2504001",name:"\u5171\u548c\u53bf"},{id:"2504002",name:"\u540c\u5fb7\u53bf"},{id:"2504003",name:"\u8d35\u5fb7\u53bf"},{id:"2504004",name:"\u5174\u6d77\u53bf"},{id:"2504005",name:"\u8d35\u5357\u53bf"}]},{id:"25050",name:"\u679c\u6d1b\u5dde",subItems:[{id:"2505001",name:"\u739b\u6c81\u53bf"},{id:"2505002",name:"\u73ed\u739b\u53bf"},{id:"2505003",name:"\u7518\u5fb7\u53bf"},{id:"2505004",name:"\u8fbe\u65e5\u53bf"},{id:"2505005",name:"\u4e45\u6cbb\u53bf"},{id:"2505006",name:"\u739b\u591a\u53bf"}]},{id:"25060",name:"\u7389\u6811\u5dde",subItems:[{id:"2506001",name:"\u7389\u6811\u53bf"},{id:"2506002",name:"\u6742\u591a\u53bf"},{id:"2506003",name:"\u79f0\u591a\u53bf"},{id:"2506004",name:"\u6cbb\u591a\u53bf"},{id:"2506005",name:"\u56ca\u8c26\u53bf"},{id:"2506006",name:"\u66f2\u9ebb\u83b1\u53bf"}]},{id:"25070",name:"\u6d77\u897f\u5dde",subItems:[{id:"2507001",name:"\u5fb7\u4ee4\u54c8\u5e02"},{id:"2507002",name:"\u683c\u5c14\u6728\u5e02"},{id:"2507003",name:"\u4e4c\u5170\u53bf"},{id:"2507004",name:"\u90fd\u5170\u53bf"},{id:"2507005",name:"\u5929\u5cfb\u53bf"}]}]},{id:"12000",name:"\u5c71\u4e1c\u7701",subItems:[{id:"75",name:"\u6d4e\u5357\u5e02",subItems:[{id:"7501",name:"\u5386\u4e0b\u533a"},{id:"7503",name:"\u5e02\u4e2d\u533a"},{id:"7505",name:"\u69d0\u836b\u533a"},{id:"7507",name:"\u5929\u6865\u533a"},{id:"7508",name:"\u5386\u57ce\u533a"},{id:"7509",name:"\u957f\u6e05\u533a"},{id:"7510",name:"\u7ae0\u4e18\u5e02"},{id:"7502",name:"\u5e73\u9634\u53bf"},{id:"7504",name:"\u6d4e\u9633\u53bf"},{id:"7506",name:"\u5546\u6cb3\u53bf"}]},{id:"12090",name:"\u5fb7\u5dde\u5e02",subItems:[{id:"1209001",name:"\u5fb7\u57ce\u533a"},{id:"1209003",name:"\u4e50\u9675\u5e02"},{id:"1209005",name:"\u79b9\u57ce\u5e02"},{id:"1209007",name:"\u9675\u53bf"},{id:"1209002",name:"\u9f50\u6cb3\u53bf"},{id:"1209004",name:"\u5e73\u539f\u53bf"},{id:"1209006",name:"\u590f\u6d25\u53bf"},{id:"1209008",name:"\u6b66\u57ce\u53bf"},{id:"1209009",name:"\u5b81\u6d25\u53bf"},{id:"1209010",name:"\u5e86\u4e91\u53bf"},{id:"1209011",name:"\u4e34\u9091\u53bf"}]},{id:"12040",name:"\u4e1c\u8425\u5e02",subItems:[{id:"1204001",name:"\u4e1c\u8425\u533a"},{id:"1204002",name:"\u6cb3\u53e3\u533a"},{id:"1204003",name:"\u57a6\u5229\u53bf"},{id:"1204004",name:"\u5229\u6d25\u53bf"},{id:"1204005",name:"\u5e7f\u9976\u53bf"}]},{id:"12060",name:"\u6d4e\u5b81\u5e02",subItems:[{id:"1206001",name:"\u5e02\u4e2d\u533a"},{id:"1206003",name:"\u4efb\u57ce\u533a"},{id:"1206005",name:"\u66f2\u961c\u5e02"},{id:"1206007",name:"\u5156\u5dde\u5e02"},{id:"1206009",name:"\u90b9\u57ce\u5e02"},{id:"1206002",name:"\u91d1\u4e61\u53bf"},{id:"1206004",name:"\u5609\u7965\u53bf"},{id:"1206006",name:"\u6c76\u4e0a\u53bf"},{id:"1206008",name:"\u6cd7\u6c34\u53bf"},{id:"1206010",name:"\u6881\u5c71\u53bf"},{id:"1206011",name:"\u5fae\u5c71\u53bf"},{id:"1206012",name:"\u9c7c\u53f0\u53bf"}]},{id:"12100",name:"\u4e34\u6c82\u5e02",subItems:[{id:"1210001",name:"\u5170\u5c71\u533a"},{id:"1210003",name:"\u7f57\u5e84\u533a"},{id:"1210005",name:"\u6cb3\u4e1c\u533a"},{id:"1210002",name:"\u8d39\u53bf"},{id:"1210004",name:"\u5e73\u9091\u53bf"},{id:"1210006",name:"\u8392\u5357\u53bf"},{id:"1210007",name:"\u6c82\u5357\u53bf"},{id:"1210008",name:"\u8499\u9634\u53bf"},{id:"1210009",name:"\u90ef\u57ce\u53bf"},{id:"1210010",name:"\u4e34\u6cad\u53bf"},{id:"1210011",name:"\u6c82\u6c34\u53bf"},{id:"1210012",name:"\u82cd\u5c71\u53bf"}]},{id:"110",name:"\u9752\u5c9b\u5e02",subItems:[{id:"11001",name:"\u5e02\u5357\u533a"},{id:"11003",name:"\u5e02\u5317\u533a"},{id:"11005",name:"\u56db\u65b9\u533a"},{id:"11007",name:"\u9ec4\u5c9b\u533a"},{id:"11009",name:"\u5d02\u5c71\u533a"},{id:"11011",name:"\u674e\u6ca7\u533a"},{id:"11012",name:"\u57ce\u9633\u533a"},{id:"11004",name:"\u5373\u58a8\u5e02"},{id:"11006",name:"\u5e73\u5ea6\u5e02"},{id:"11002",name:"\u80f6\u5dde\u5e02"},{id:"11008",name:"\u80f6\u5357\u5e02"},{id:"11010",name:"\u83b1\u897f\u5e02"}]},{id:"12080",name:"\u65e5\u7167\u5e02",subItems:[{id:"1208001",name:"\u4e1c\u6e2f\u533a"},{id:"1208002",name:"\u5c9a\u5c71\u533a"},{id:"1208004",name:"\u8392\u53bf"},{id:"1208003",name:"\u4e94\u83b2\u53bf"}]},{id:"12070",name:"\u6cf0\u5b89\u5e02",subItems:[{id:"1207001",name:"\u6cf0\u5c71\u533a"},{id:"1207002",name:"\u5cb1\u5cb3\u533a"},{id:"1207003",name:"\u65b0\u6cf0\u5e02"},{id:"1207004",name:"\u80a5\u57ce\u5e02"},{id:"1207005",name:"\u5b81\u9633\u53bf"},{id:"1207006",name:"\u4e1c\u5e73\u53bf"}]},{id:"146",name:"\u5a01\u6d77\u5e02",subItems:[{id:"14601",name:"\u73af\u7fe0\u533a"},{id:"14602",name:"\u6587\u767b\u5e02"},{id:"14603",name:"\u8363\u6210\u5e02"},{id:"14604",name:"\u4e73\u5c71\u5e02"}]},{id:"12050",name:"\u6f4d\u574a\u5e02",subItems:[{id:"1205001",name:"\u6f4d\u57ce\u533a"},{id:"1205003",name:"\u5bd2\u4ead\u533a"},{id:"1205005",name:"\u574a\u5b50\u533a"},{id:"1205007",name:"\u594e\u6587\u533a"},{id:"1205004",name:"\u9ad8\u5bc6\u5e02"},{id:"1205002",name:"\u5b89\u4e18\u5e02"},{id:"1205006",name:"\u660c\u9091\u5e02"},{id:"1205009",name:"\u9752\u5dde\u5e02"},{id:"1205011",name:"\u8bf8\u57ce\u5e02"},{id:"1205012",name:"\u5bff\u5149\u5e02"},{id:"1205010",name:"\u660c\u4e50\u53bf"},{id:"1205008",name:"\u4e34\u6710\u53bf"}]},{id:"168",name:"\u70df\u53f0\u5e02",subItems:[{id:"16801",name:"\u829d\u7f58\u533a"},{id:"16803",name:"\u798f\u5c71\u533a"},{id:"16805",name:"\u725f\u5e73\u533a"},{id:"16807",name:"\u83b1\u5c71\u533a"},{id:"16802",name:"\u84ec\u83b1\u5e02"},{id:"16804",name:"\u62db\u8fdc\u5e02"},{id:"16806",name:"\u6816\u971e\u5e02"},{id:"16808",name:"\u6d77\u9633\u5e02"},{id:"16809",name:"\u9f99\u53e3\u5e02"},{id:"16811",name:"\u83b1\u9633\u5e02"},{id:"16812",name:"\u83b1\u5dde\u5e02"},{id:"16810",name:"\u957f\u5c9b\u53bf"}]},{id:"12030",name:"\u6dc4\u535a\u5e02",subItems:[{id:"1203001",name:"\u5f20\u5e97\u533a"},{id:"1203003",name:"\u6dc4\u5ddd\u533a"},{id:"1203004",name:"\u535a\u5c71\u533a"},{id:"1203005",name:"\u4e34\u6dc4\u533a"},{id:"1203006",name:"\u5468\u6751\u533a"},{id:"1203002",name:"\u6c82\u6e90\u53bf"},{id:"1203007",name:"\u6853\u53f0\u53bf"},{id:"1203008",name:"\u9ad8\u9752\u53bf"}]},{id:"12110",name:"\u83cf\u6cfd\u5e02",subItems:[{id:"1211001",name:"\u7261\u4e39\u533a"},{id:"1211003",name:"\u66f9\u53bf"},{id:"1211005",name:"\u5355\u53bf"},{id:"1211002",name:"\u5b9a\u9676\u53bf"},{id:"1211004",name:"\u4e1c\u660e\u53bf"},{id:"1211006",name:"\u6210\u6b66\u53bf"},{id:"1211007",name:"\u5de8\u91ce\u53bf"},{id:"1211008",name:"\u90d3\u57ce\u53bf"},{id:"1211009",name:"\u9104\u57ce\u53bf"}]},{id:"12120",name:"\u6ee8\u5dde\u5e02",subItems:[{id:"1212001",name:"\u6ee8\u57ce\u533a"},{id:"1212002",name:"\u60e0\u6c11\u53bf"},{id:"1212003",name:"\u9633\u4fe1\u53bf"},{id:"1212004",name:"\u65e0\u68e3\u53bf"},{id:"1212005",name:"\u6cbe\u5316\u53bf"},{id:"1212006",name:"\u535a\u5174\u53bf"},{id:"1212007",name:"\u90b9\u5e73\u53bf"}]},{id:"12130",name:"\u67a3\u5e84\u5e02",subItems:[{id:"1213001",name:"\u5e02\u4e2d\u533a"},{id:"1213002",name:"\u859b\u57ce\u533a"},{id:"1213003",name:"\u5cc4\u57ce\u533a"},{id:"1213004",name:"\u53f0\u513f\u5e84\u533a"},{id:"1213005",name:"\u5c71\u4ead\u533a"},{id:"1213006",name:"\u6ed5\u5dde\u5e02"}]},{id:"12140",name:"\u804a\u57ce\u5e02",subItems:[{id:"1214001",name:"\u4e1c\u660c\u5e9c\u533a"},{id:"1214003",name:"\u4e34\u6e05\u5e02"},{id:"1214005",name:"\u8398\u53bf"},{id:"1214008",name:"\u51a0\u53bf"},{id:"1214002",name:"\u9ad8\u5510\u53bf"},{id:"1214004",name:"\u9633\u8c37\u53bf"},{id:"1214006",name:"\u830c\u5e73\u53bf"},{id:"1214007",name:"\u4e1c\u963f\u53bf"}]},{id:"12150",name:"\u83b1\u829c\u5e02",subItems:[{id:"1215001",name:"\u83b1\u57ce\u533a"},{id:"1215002",name:"\u94a2\u57ce\u533a"}]}]},{id:"2000",name:"\u5c71\u897f\u7701",subItems:[{id:"135",name:"\u592a\u539f\u5e02",subItems:[{id:"13501",name:"\u5c0f\u5e97\u533a"},{id:"13503",name:"\u8fce\u6cfd\u533a"},{id:"13510",name:"\u664b\u6e90\u533a"},{id:"13505",name:"\u674f\u82b1\u5cad\u533a"},{id:"13507",name:"\u5c16\u8349\u576a\u533a"},{id:"13509",name:"\u4e07\u67cf\u6797\u533a"},{id:"13508",name:"\u53e4\u4ea4\u5e02"},{id:"13502",name:"\u6e05\u5f90\u53bf"},{id:"13504",name:"\u9633\u66f2\u53bf"},{id:"13506",name:"\u5a04\u70e6\u53bf"}]},{id:"2010",name:"\u5927\u540c\u5e02",subItems:[{id:"201001",name:"\u5927\u540c\u57ce\u533a"},{id:"201003",name:"\u5927\u540c\u77ff\u533a"},{id:"201005",name:"\u5357\u90ca\u533a"},{id:"201007",name:"\u65b0\u8363\u533a"},{id:"201011",name:"\u5927\u540c\u53bf"},{id:"201002",name:"\u9633\u9ad8\u53bf"},{id:"201004",name:"\u5929\u9547\u53bf"},{id:"201006",name:"\u5e7f\u7075\u53bf"},{id:"201008",name:"\u7075\u4e18\u53bf"},{id:"201009",name:"\u6d51\u6e90\u53bf"},{id:"201010",name:"\u5de6\u4e91\u53bf"}]},{id:"2020",name:"\u4e34\u6c7e\u5e02",subItems:[{id:"202001",name:"\u5c27\u90fd\u533a"},{id:"202006",name:"\u4faf\u9a6c\u5e02"},{id:"202009",name:"\u970d\u5dde\u5e02"},{id:"202005",name:"\u5409\u53bf"},{id:"202013",name:"\u96b0\u53bf"},{id:"202014",name:"\u53e4\u53bf"},{id:"202017",name:"\u84b2\u53bf"},{id:"202002",name:"\u6d6e\u5c71\u53bf"},{id:"202004",name:"\u66f2\u6c83\u53bf"},{id:"202003",name:"\u6c7e\u897f\u53bf"},{id:"202007",name:"\u7ffc\u57ce\u53bf"},{id:"202008",name:"\u4e61\u5b81\u53bf"},{id:"202010",name:"\u8944\u6c7e\u53bf"},{id:"202011",name:"\u5927\u5b81\u53bf"},{id:"202012",name:"\u6d2a\u6d1e\u53bf"},{id:"202015",name:"\u6c38\u548c\u53bf"},{id:"202016",name:"\u5b89\u6cfd\u53bf"}]},{id:"2030",name:"\u8fd0\u57ce\u5e02",subItems:[{id:"203001",name:"\u76d0\u6e56\u533a"},{id:"203012",name:"\u6cb3\u6d25\u5e02"},{id:"203010",name:"\u6c38\u6d4e\u5e02"},{id:"203004",name:"\u590f\u53bf"},{id:"203013",name:"\u7edb\u53bf"},{id:"203002",name:"\u57a3\u66f2\u53bf"},{id:"203003",name:"\u4e34\u7317\u53bf"},{id:"203005",name:"\u4e07\u8363\u53bf"},{id:"203006",name:"\u5e73\u9646\u53bf"},{id:"203007",name:"\u95fb\u559c\u53bf"},{id:"203008",name:"\u82ae\u57ce\u53bf"},{id:"203009",name:"\u7a37\u5c71\u53bf"},{id:"203011",name:"\u65b0\u7edb\u53bf"}]},{id:"2040",name:"\u9633\u6cc9\u5e02",subItems:[{id:"204001",name:"\u9633\u6cc9\u57ce\u533a"},{id:"204002",name:"\u9633\u6cc9\u77ff\u533a"},{id:"204003",name:"\u9633\u6cc9\u90ca\u533a"},{id:"204005",name:"\u76c2\u53bf"},{id:"204004",name:"\u5e73\u5b9a\u53bf"}]},{id:"2050",name:"\u957f\u6cbb\u5e02",subItems:[{id:"205001",name:"\u957f\u6cbb\u57ce\u533a"},{id:"205003",name:"\u957f\u6cbb\u90ca\u533a"},{id:"205012",name:"\u6f5e\u57ce\u5e02"},{id:"205008",name:"\u6c81\u53bf"},{id:"205002",name:"\u58f6\u5173\u53bf"},{id:"205004",name:"\u957f\u5b50\u53bf"},{id:"205005",name:"\u957f\u6cbb\u53bf"},{id:"205006",name:"\u6b66\u4e61\u53bf"},{id:"205007",name:"\u8944\u57a3\u53bf"},{id:"205009",name:"\u5c6f\u7559\u53bf"},{id:"205010",name:"\u6c81\u6e90\u53bf"},{id:"205011",name:"\u5e73\u987a\u53bf"},{id:"205013",name:"\u9ece\u57ce\u53bf"}]},{id:"2060",name:"\u664b\u57ce\u5e02",subItems:[{id:"206001",name:"\u664b\u57ce\u57ce\u533a"},{id:"206006",name:"\u9ad8\u5e73\u5e02"},{id:"206002",name:"\u6c81\u6c34\u53bf"},{id:"206003",name:"\u9633\u57ce\u53bf"},{id:"206004",name:"\u9675\u5ddd\u53bf"},{id:"206005",name:"\u6cfd\u5dde\u53bf"}]},{id:"2070",name:"\u6714\u5dde\u5e02",subItems:[{id:"207001",name:"\u6714\u57ce\u533a"},{id:"207002",name:"\u5e73\u9c81\u533a"},{id:"207003",name:"\u5c71\u9634\u53bf"},{id:"207004",name:"\u5e94\u53bf"},{id:"207005",name:"\u53f3\u7389\u53bf"},{id:"207006",name:"\u6000\u4ec1\u53bf"}]},{id:"2080",name:"\u664b\u4e2d\u5e02",subItems:[{id:"208001",name:"\u6986\u6b21\u533a"},{id:"208008",name:"\u4ecb\u4f11\u5e02"},{id:"208002",name:"\u7941\u53bf"},{id:"208003",name:"\u6986\u793e\u53bf"},{id:"208004",name:"\u5e73\u9065\u53bf"},{id:"208005",name:"\u5de6\u6743\u53bf"},{id:"208006",name:"\u7075\u77f3\u53bf"},{id:"208007",name:"\u548c\u987a\u53bf"},{id:"208009",name:"\u6614\u9633\u53bf"},{id:"208010",name:"\u5bff\u9633\u53bf"},{id:"208011",name:"\u592a\u8c37\u53bf"}]},{id:"2090",name:"\u5ffb\u5dde\u5e02",subItems:[{id:"209001",name:"\u5ffb\u5e9c\u533a"},{id:"209014",name:"\u539f\u5e73\u5e02"},{id:"209007",name:"\u4ee3\u53bf"},{id:"209002",name:"\u795e\u6c60\u53bf"},{id:"209003",name:"\u5b9a\u8944\u53bf"},{id:"209004",name:"\u4e94\u5be8\u53bf"},{id:"209005",name:"\u4e94\u53f0\u53bf"},{id:"209006",name:"\u5ca2\u5c9a\u53bf"},{id:"209008",name:"\u6cb3\u66f2\u53bf"},{id:"209009",name:"\u7e41\u5cd9\u53bf"},{id:"209010",name:"\u4fdd\u5fb7\u53bf"},{id:"209011",name:"\u5b81\u6b66\u53bf"},{id:"209012",name:"\u504f\u5173\u53bf"},{id:"209013",name:"\u9759\u4e50\u53bf"}]},{id:"2100",name:"\u5415\u6881\u5e02",subItems:[{id:"210001",name:"\u79bb\u77f3\u533a"},{id:"210012",name:"\u6c7e\u9633\u5e02"},{id:"210010",name:"\u5b5d\u4e49\u5e02"},{id:"210002",name:"\u5c9a\u53bf"},{id:"210007",name:"\u5174\u53bf"},{id:"210009",name:"\u4e34\u53bf"},{id:"210003",name:"\u6587\u6c34\u53bf"},{id:"210004",name:"\u65b9\u5c71\u53bf"},{id:"210005",name:"\u4ea4\u57ce\u53bf"},{id:"210008",name:"\u4ea4\u53e3\u53bf"},{id:"210006",name:"\u4e2d\u9633\u53bf"},{id:"210011",name:"\u67f3\u6797\u53bf"},{id:"210013",name:"\u77f3\u697c\u53bf"}]}]},{id:"23000",name:"\u9655\u897f\u7701",subItems:[{id:"160",name:"\u897f\u5b89\u5e02",subItems:[{id:"16001",name:"\u672a\u592e\u533a"},{id:"16002",name:"\u4e34\u6f7c\u533a"},{id:"16003",name:"\u65b0\u57ce\u533a"},{id:"16004",name:"\u957f\u5b89\u533a"},{id:"16005",name:"\u7891\u6797\u533a"},{id:"16007",name:"\u83b2\u6e56\u533a"},{id:"16009",name:"\u705e\u6865\u533a"},{id:"16011",name:"\u96c1\u5854\u533a"},{id:"16013",name:"\u960e\u826f\u533a"},{id:"16014",name:"\u6237\u53bf"},{id:"16006",name:"\u84dd\u7530\u53bf"},{id:"16008",name:"\u5468\u81f3\u53bf"},{id:"16012",name:"\u9ad8\u9675\u53bf"}]},{id:"23010",name:"\u5b9d\u9e21\u5e02",subItems:[{id:"2301001",name:"\u6e2d\u6ee8\u533a"},{id:"2301003",name:"\u91d1\u53f0\u533a"},{id:"2301005",name:"\u9648\u4ed3\u533a"},{id:"2301008",name:"\u51e4\u53bf"},{id:"2301002",name:"\u9647\u53bf"},{id:"2301012",name:"\u7709\u53bf"},{id:"2301004",name:"\u5343\u9633\u53bf"},{id:"2301006",name:"\u9e9f\u6e38\u53bf"},{id:"2301007",name:"\u51e4\u7fd4\u53bf"},{id:"2301009",name:"\u5c90\u5c71\u53bf"},{id:"2301010",name:"\u592a\u767d\u53bf"},{id:"2301011",name:"\u6276\u98ce\u53bf"}]},{id:"23020",name:"\u54b8\u9633\u5e02",subItems:[{id:"2302001",name:"\u79e6\u90fd\u533a"},{id:"2302003",name:"\u6768\u9675\u533a"},{id:"2302005",name:"\u6e2d\u57ce\u533a"},{id:"2302007",name:"\u5174\u5e73\u5e02"},{id:"2302006",name:"\u5f6c\u53bf"},{id:"2302013",name:"\u4e7e\u53bf"},{id:"2302002",name:"\u793c\u6cc9\u53bf"},{id:"2302004",name:"\u6c38\u5bff\u53bf"},{id:"2302008",name:"\u957f\u6b66\u53bf"},{id:"2302009",name:"\u4e09\u539f\u53bf"},{id:"2302010",name:"\u65ec\u9091\u53bf"},{id:"2302011",name:"\u6cfe\u9633\u53bf"},{id:"2302012",name:"\u6df3\u5316\u53bf"},{id:"2302014",name:"\u6b66\u529f\u53bf"}]},{id:"23030",name:"\u94dc\u5ddd\u5e02",subItems:[{id:"2303001",name:"\u8000\u5dde\u533a"},{id:"2303002",name:"\u738b\u76ca\u533a"},{id:"2303003",name:"\u5370\u53f0\u533a"},{id:"2303004",name:"\u5b9c\u541b\u53bf"}]},{id:"23040",name:"\u6e2d\u5357\u5e02",subItems:[{id:"2304001",name:"\u4e34\u6e2d\u533a"},{id:"2304003",name:"\u97e9\u57ce\u5e02"},{id:"2304005",name:"\u534e\u9634\u5e02"},{id:"2304007",name:"\u534e\u53bf"},{id:"2304002",name:"\u6f84\u57ce\u53bf"},{id:"2304004",name:"\u84b2\u57ce\u53bf"},{id:"2304006",name:"\u767d\u6c34\u53bf"},{id:"2304008",name:"\u5bcc\u5e73\u53bf"},{id:"2304009",name:"\u6f7c\u5173\u53bf"},{id:"2304010",name:"\u5927\u8354\u53bf"},{id:"2304011",name:"\u5408\u9633\u53bf"}]},{id:"23050",name:"\u5ef6\u5b89\u5e02",subItems:[{id:"2305001",name:"\u5b9d\u5854\u533a"},{id:"2305004",name:"\u5bcc\u53bf"},{id:"2305002",name:"\u7518\u6cc9\u53bf"},{id:"2305003",name:"\u5ef6\u957f\u53bf"},{id:"2305005",name:"\u5ef6\u5ddd\u53bf"},{id:"2305006",name:"\u6d1b\u5ddd\u53bf"},{id:"2305008",name:"\u5b9c\u5ddd\u53bf"},{id:"2305007",name:"\u5b50\u957f\u53bf"},{id:"2305009",name:"\u5b89\u585e\u53bf"},{id:"2305010",name:"\u9ec4\u9f99\u53bf"},{id:"2305012",name:"\u9ec4\u9675\u53bf"},{id:"2305011",name:"\u5fd7\u4e39\u53bf"},{id:"2305013",name:"\u5434\u8d77\u53bf"}]},{id:"23060",name:"\u6c49\u4e2d\u5e02",subItems:[{id:"2306001",name:"\u6c49\u53f0\u533a"},{id:"2306007",name:"\u6d0b\u53bf"},{id:"2306010",name:"\u52c9\u53bf"},{id:"2306002",name:"\u7565\u9633\u53bf"},{id:"2306003",name:"\u5357\u90d1\u53bf"},{id:"2306004",name:"\u9547\u5df4\u53bf"},{id:"2306005",name:"\u57ce\u56fa\u53bf"},{id:"2306006",name:"\u7559\u575d\u53bf"},{id:"2306008",name:"\u4f5b\u576a\u53bf"},{id:"2306009",name:"\u897f\u4e61\u53bf"},{id:"2306011",name:"\u5b81\u5f3a\u53bf"}]},{id:"23070",name:"\u6986\u6797\u5e02",subItems:[{id:"2307001",name:"\u6986\u9633\u533a"},{id:"2307004",name:"\u4f73\u53bf"},{id:"2307002",name:"\u7c73\u8102\u53bf"},{id:"2307003",name:"\u795e\u6728\u53bf"},{id:"2307005",name:"\u5e9c\u8c37\u53bf"},{id:"2307006",name:"\u5434\u5821\u53bf"},{id:"2307007",name:"\u6a2a\u5c71\u53bf"},{id:"2307008",name:"\u6e05\u6da7\u53bf"},{id:"2307009",name:"\u9756\u8fb9\u53bf"},{id:"2307010",name:"\u5b50\u6d32\u53bf"},{id:"2307011",name:"\u5b9a\u8fb9\u53bf"},{id:"2307012",name:"\u7ee5\u5fb7\u53bf"}]},{id:"23080",name:"\u5b89\u5eb7\u5e02",subItems:[{id:"2308001",name:"\u6c49\u6ee8\u533a"},{id:"2308002",name:"\u9547\u576a\u53bf"},{id:"2308003",name:"\u6c49\u9634\u53bf"},{id:"2308004",name:"\u65ec\u9633\u53bf"},{id:"2308005",name:"\u77f3\u6cc9\u53bf"},{id:"2308006",name:"\u767d\u6cb3\u53bf"},{id:"2308007",name:"\u5b81\u9655\u53bf"},{id:"2308008",name:"\u7d2b\u9633\u53bf"},{id:"2308009",name:"\u5c9a\u768b\u53bf"},{id:"2308010",name:"\u5e73\u5229\u53bf"}]},{id:"23090",name:"\u5546\u6d1b\u5e02",subItems:[{id:"2309001",name:"\u5546\u5dde\u533a"},{id:"2309002",name:"\u6d1b\u5357\u53bf"},{id:"2309003",name:"\u4e39\u51e4\u53bf"},{id:"2309004",name:"\u5546\u5357\u53bf"},{id:"2309005",name:"\u5c71\u9633\u53bf"},{id:"2309006",name:"\u9547\u5b89\u53bf"},{id:"2309007",name:"\u67de\u6c34\u53bf"}]}]},{id:"19000",name:"\u56db\u5ddd\u7701",subItems:[{id:"20",name:"\u6210\u90fd\u5e02",subItems:[{id:"2001",name:"\u9526\u6c5f\u533a"},{id:"2002",name:"\u65b0\u90fd\u533a"},{id:"2005",name:"\u6e29\u6c5f\u533a"},{id:"2004",name:"\u9752\u7f8a\u533a"},{id:"2007",name:"\u91d1\u725b\u533a"},{id:"2013",name:"\u6210\u534e\u533a"},{id:"2021",name:"\u6b66\u4faf\u533a"},{id:"2016",name:"\u9f99\u6cc9\u9a7f\u533a"},{id:"2018",name:"\u9752\u767d\u6c5f\u533a"},{id:"2011",name:"\u5f6d\u5dde\u5e02"},{id:"2014",name:"\u909b\u5d03\u5e02"},{id:"2017",name:"\u5d07\u5dde\u5e02"},{id:"2008",name:"\u90fd\u6c5f\u5830\u5e02"},{id:"2006",name:"\u90eb\u53bf"},{id:"2009",name:"\u5927\u9091\u53bf"},{id:"2003",name:"\u53cc\u6d41\u53bf"},{id:"2012",name:"\u84b2\u6c5f\u53bf"},{id:"2015",name:"\u65b0\u6d25\u53bf"},{id:"2019",name:"\u91d1\u5802\u53bf"}]},{id:"19060",name:"\u4e50\u5c71\u5e02",subItems:[{id:"1906001",name:"\u5e02\u4e2d\u533a"},{id:"1906003",name:"\u6c99\u6e7e\u533a"},{id:"1906007",name:"\u91d1\u53e3\u6cb3\u533a"},{id:"1906005",name:"\u4e94\u901a\u6865\u533a"},{id:"1906009",name:"\u5ce8\u7709\u5c71\u5e02"},{id:"1906002",name:"\u5939\u6c5f\u53bf"},{id:"1906004",name:"\u6c90\u5ddd\u53bf"},{id:"1906006",name:"\u5ce8\u8fb9\u53bf"},{id:"1906008",name:"\u9a6c\u8fb9\u53bf"},{id:"1906010",name:"\u728d\u4e3a\u53bf"},{id:"1906011",name:"\u4e95\u7814\u53bf"}]},{id:"19030",name:"\u6cf8\u5dde\u5e02",subItems:[{id:"1903001",name:"\u6c5f\u9633\u533a"},{id:"1903002",name:"\u7eb3\u6eaa\u533a"},{id:"1903003",name:"\u9f99\u9a6c\u6f6d\u533a"},{id:"1903004",name:"\u6cf8\u53bf"},{id:"1903005",name:"\u5408\u6c5f\u53bf"},{id:"1903006",name:"\u53d9\u6c38\u53bf"},{id:"1903007",name:"\u53e4\u853a\u53bf"}]},{id:"19040",name:"\u7ef5\u9633\u5e02",subItems:[{id:"1904001",name:"\u6daa\u57ce\u533a"},{id:"1904003",name:"\u6e38\u4ed9\u533a"},{id:"1904005",name:"\u6c5f\u6cb9\u5e02"},{id:"1904008",name:"\u5b89\u53bf"},{id:"1904002",name:"\u5e73\u6b66\u53bf"},{id:"1904004",name:"\u5317\u5ddd\u53bf"},{id:"1904006",name:"\u4e09\u53f0\u53bf"},{id:"1904007",name:"\u76d0\u4ead\u53bf"},{id:"1904009",name:"\u6893\u6f7c\u53bf"}]},{id:"19050",name:"\u5185\u6c5f\u5e02",subItems:[{id:"1905001",name:"\u5e02\u4e2d\u533a"},{id:"1905002",name:"\u4e1c\u5174\u533a"},{id:"1905003",name:"\u5a01\u8fdc\u53bf"},{id:"1905004",name:"\u8d44\u4e2d\u53bf"},{id:"1905005",name:"\u9686\u660c\u53bf"}]},{id:"19070",name:"\u5b9c\u5bbe\u5e02",subItems:[{id:"1907001",name:"\u7fe0\u5c4f\u533a"},{id:"1907010",name:"\u73d9\u53bf"},{id:"1907009",name:"\u9ad8\u53bf"},{id:"1907002",name:"\u7b60\u8fde\u53bf"},{id:"1907003",name:"\u5b9c\u5bbe\u53bf"},{id:"1907004",name:"\u5174\u6587\u53bf"},{id:"1907005",name:"\u5357\u6eaa\u53bf"},{id:"1907006",name:"\u5c4f\u5c71\u53bf"},{id:"1907007",name:"\u6c5f\u5b89\u53bf"},{id:"1907008",name:"\u957f\u5b81\u53bf"}]},{id:"19020",name:"\u81ea\u8d21\u5e02",subItems:[{id:"1902001",name:"\u81ea\u6d41\u4e95\u533a"},{id:"1902002",name:"\u8d21\u4e95\u533a"},{id:"1902003",name:"\u5927\u5b89\u533a"},{id:"1902004",name:"\u6cbf\u6ee9\u533a"},{id:"1902005",name:"\u8363\u53bf"},{id:"1902006",name:"\u5bcc\u987a\u53bf"}]},{id:"19080",name:"\u6500\u679d\u82b1\u5e02",subItems:[{id:"1908001",name:"\u4e1c\u533a"},{id:"1908002",name:"\u897f\u533a"},{id:"1908003",name:"\u4ec1\u548c\u533a"},{id:"1908004",name:"\u7c73\u6613\u53bf"},{id:"1908005",name:"\u76d0\u8fb9\u53bf"}]},{id:"19090",name:"\u5fb7\u9633\u5e02",subItems:[{id:"1909001",name:"\u65cc\u9633\u533a"},{id:"1909002",name:"\u5e7f\u6c49\u5e02"},{id:"1909003",name:"\u4ec0\u90a1\u5e02"},{id:"1909004",name:"\u7ef5\u7af9\u5e02"},{id:"1909005",name:"\u7f57\u6c5f\u53bf"},{id:"1909006",name:"\u4e2d\u6c5f\u53bf"}]},{id:"19100",name:"\u5e7f\u5143\u5e02",subItems:[{id:"1910001",name:"\u5229\u5dde\u533a"},{id:"1910002",name:"\u5143\u575d\u533a"},{id:"1910003",name:"\u671d\u5929\u533a"},{id:"1910004",name:"\u65fa\u82cd\u53bf"},{id:"1910005",name:"\u9752\u5ddd\u53bf"},{id:"1910006",name:"\u5251\u9601\u53bf"},{id:"1910007",name:"\u82cd\u6eaa\u53bf"}]},{id:"19110",name:"\u9042\u5b81\u5e02",subItems:[{id:"1911001",name:"\u8239\u5c71\u533a"},{id:"1911002",name:"\u5b89\u5c45\u533a"},{id:"1911003",name:"\u84ec\u6eaa\u53bf"},{id:"1911004",name:"\u5c04\u6d2a\u53bf"},{id:"1911005",name:"\u5927\u82f1\u53bf"}]},{id:"19120",name:"\u5357\u5145\u5e02",subItems:[{id:"1912001",name:"\u987a\u5e86\u533a"},{id:"1912003",name:"\u9ad8\u576a\u533a"},{id:"1912005",name:"\u5609\u9675\u533a"},{id:"1912006",name:"\u9606\u4e2d\u5e02"},{id:"1912002",name:"\u4eea\u9647\u53bf"},{id:"1912004",name:"\u897f\u5145\u53bf"},{id:"1912007",name:"\u5357\u90e8\u53bf"},{id:"1912008",name:"\u8425\u5c71\u53bf"},{id:"1912009",name:"\u84ec\u5b89\u53bf"}]},{id:"19130",name:"\u5e7f\u5b89\u5e02",subItems:[{id:"1913001",name:"\u5e7f\u5b89\u533a"},{id:"1913002",name:"\u534e\u84e5\u5e02"},{id:"1913003",name:"\u5cb3\u6c60\u53bf"},{id:"1913004",name:"\u6b66\u80dc\u53bf"},{id:"1913005",name:"\u90bb\u6c34\u53bf"}]},{id:"19140",name:"\u8fbe\u5dde\u5e02",subItems:[{id:"1914001",name:"\u901a\u5ddd\u533a"},{id:"1914002",name:"\u4e07\u6e90\u5e02"},{id:"1914003",name:"\u8fbe\u53bf"},{id:"1914007",name:"\u6e20\u53bf"},{id:"1914004",name:"\u5ba3\u6c49\u53bf"},{id:"1914005",name:"\u5f00\u6c5f\u53bf"},{id:"1914006",name:"\u5927\u7af9\u53bf"}]},{id:"19150",name:"\u5df4\u4e2d\u5e02",subItems:[{id:"1915001",name:"\u5df4\u5dde\u533a"},{id:"1915002",name:"\u901a\u6c5f\u53bf"},{id:"1915003",name:"\u5357\u6c5f\u53bf"},{id:"1915004",name:"\u5e73\u660c\u53bf"}]},{id:"19160",name:"\u96c5\u5b89\u5e02",subItems:[{id:"1916001",name:"\u96e8\u57ce\u533a"},{id:"1916002",name:"\u5b9d\u5174\u53bf"},{id:"1916003",name:"\u540d\u5c71\u53bf"},{id:"1916004",name:"\u8365\u7ecf\u53bf"},{id:"1916005",name:"\u6c49\u6e90\u53bf"},{id:"1916006",name:"\u77f3\u68c9\u53bf"},{id:"1916007",name:"\u5929\u5168\u53bf"},{id:"1916008",name:"\u82a6\u5c71\u53bf"}]},{id:"19170",name:"\u7709\u5c71\u5e02",subItems:[{id:"1917001",name:"\u4e1c\u5761\u533a"},{id:"1917002",name:"\u4ec1\u5bff\u53bf"},{id:"1917003",name:"\u5f6d\u5c71\u53bf"},{id:"1917004",name:"\u6d2a\u96c5\u53bf"},{id:"1917005",name:"\u4e39\u68f1\u53bf"},{id:"1917006",name:"\u9752\u795e\u53bf"}]},{id:"19180",name:"\u8d44\u9633\u5e02",subItems:[{id:"1918001",name:"\u96c1\u6c5f\u533a"},{id:"1918002",name:"\u7b80\u9633\u5e02"},{id:"1918003",name:"\u5b89\u5cb3\u53bf"},{id:"1918004",name:"\u4e50\u81f3\u53bf"}]},{id:"19190",name:"\u963f\u575d\u5dde",subItems:[{id:"1919005",name:"\u7406\u53bf"},{id:"1919007",name:"\u8302\u53bf"},{id:"1919002",name:"\u5c0f\u91d1\u53bf"},{id:"1919003",name:"\u6c76\u5ddd\u53bf"},{id:"1919004",name:"\u9ed1\u6c34\u53bf"},{id:"1919008",name:"\u963f\u575d\u53bf"},{id:"1919006",name:"\u58e4\u5858\u53bf"},{id:"1919009",name:"\u677e\u6f58\u53bf"},{id:"1919013",name:"\u91d1\u5ddd\u53bf"},{id:"1919012",name:"\u7ea2\u539f\u53bf"},{id:"1919001",name:"\u9a6c\u5c14\u5eb7\u53bf"},{id:"1919010",name:"\u82e5\u5c14\u76d6\u53bf"},{id:"1919011",name:"\u4e5d\u5be8\u6c9f\u53bf"}]},{id:"19200",name:"\u7518\u5b5c\u5dde",subItems:[{id:"1920001",name:"\u5eb7\u5b9a\u53bf"},{id:"1920002",name:"\u7518\u5b5c\u53bf"},{id:"1920003",name:"\u5df4\u5858\u53bf"},{id:"1920004",name:"\u6cf8\u5b9a\u53bf"},{id:"1920005",name:"\u65b0\u9f99\u53bf"},{id:"1920006",name:"\u4e61\u57ce\u53bf"},{id:"1920007",name:"\u4e39\u5df4\u53bf"},{id:"1920008",name:"\u5fb7\u683c\u53bf"},{id:"1920009",name:"\u7a3b\u57ce\u53bf"},{id:"1920010",name:"\u4e5d\u9f99\u53bf"},{id:"1920011",name:"\u767d\u7389\u53bf"},{id:"1920012",name:"\u5f97\u8363\u53bf"},{id:"1920013",name:"\u96c5\u6c5f\u53bf"},{id:"1920014",name:"\u77f3\u6e20\u53bf"},{id:"1920015",name:"\u9053\u5b5a\u53bf"},{id:"1920016",name:"\u8272\u8fbe\u53bf"},{id:"1920017",name:"\u7089\u970d\u53bf"},{id:"1920018",name:"\u7406\u5858\u53bf"}]},{id:"19210",name:"\u51c9\u5c71\u5dde",subItems:[{id:"1921001",name:"\u897f\u660c\u5e02"},{id:"1921002",name:"\u5e03\u62d6\u53bf"},{id:"1921003",name:"\u7f8e\u59d1\u53bf"},{id:"1921004",name:"\u76d0\u6e90\u53bf"},{id:"1921005",name:"\u91d1\u9633\u53bf"},{id:"1921006",name:"\u96f7\u6ce2\u53bf"},{id:"1921007",name:"\u5fb7\u660c\u53bf"},{id:"1921008",name:"\u662d\u89c9\u53bf"},{id:"1921009",name:"\u6728\u91cc\u53bf"},{id:"1921010",name:"\u4f1a\u7406\u53bf"},{id:"1921011",name:"\u559c\u5fb7\u53bf"},{id:"1921012",name:"\u4f1a\u4e1c\u53bf"},{id:"1921013",name:"\u5195\u5b81\u53bf"},{id:"1921014",name:"\u5b81\u5357\u53bf"},{id:"1921015",name:"\u8d8a\u897f\u53bf"},{id:"1921016",name:"\u666e\u683c\u53bf"},{id:"1921017",name:"\u7518\u6d1b\u53bf"}]}]},{id:"22000",name:"\u897f\u85cf",subItems:[{id:"90",name:"\u62c9\u8428\u5e02",subItems:[{id:"9001",name:"\u57ce\u5173\u533a"},{id:"9003",name:"\u6797\u5468\u53bf"},{id:"9004",name:"\u5f53\u96c4\u53bf"},{id:"9005",name:"\u5c3c\u6728\u53bf"},{id:"9006",name:"\u66f2\u6c34\u53bf"},{id:"9008",name:"\u8fbe\u5b5c\u53bf"},{id:"9002",name:"\u58a8\u7af9\u5de5\u5361\u53bf"},{id:"9007",name:"\u5806\u9f99\u5fb7\u5e86\u53bf"}]},{id:"22020",name:"\u65e5\u5580\u5219",subItems:[{id:"2202001",name:"\u65e5\u5580\u5219\u5e02"},{id:"2202003",name:"\u4e9a\u4e1c\u53bf"},{id:"2202005",name:"\u767d\u6717\u53bf"},{id:"2202006",name:"\u5409\u9686\u53bf"},{id:"2202007",name:"\u6c5f\u5b5c\u53bf"},{id:"2202008",name:"\u4ec1\u5e03\u53bf"},{id:"2202010",name:"\u5b9a\u65e5\u53bf"},{id:"2202011",name:"\u5eb7\u9a6c\u53bf"},{id:"2202012",name:"\u8428\u560e\u53bf"},{id:"2202013",name:"\u8428\u8fe6\u53bf"},{id:"2202014",name:"\u5b9a\u7ed3\u53bf"},{id:"2202015",name:"\u5c97\u5df4\u53bf"},{id:"2202016",name:"\u62c9\u5b5c\u53bf"},{id:"2202017",name:"\u4ef2\u5df4\u53bf"},{id:"2202018",name:"\u6602\u4ec1\u53bf"},{id:"2202004",name:"\u5357\u6728\u6797\u53bf"},{id:"2202002",name:"\u8c22\u901a\u95e8\u53bf"},{id:"2202009",name:"\u8042\u62c9\u6728\u53bf"}]},{id:"22030",name:"\u660c\u90fd",subItems:[{id:"2203001",name:"\u660c\u90fd\u53bf"},{id:"2203002",name:"\u5de6\u8d21\u53bf"},{id:"2203003",name:"\u6c5f\u8fbe\u53bf"},{id:"2203004",name:"\u8292\u5eb7\u53bf"},{id:"2203005",name:"\u8d21\u89c9\u53bf"},{id:"2203006",name:"\u6d1b\u9686\u53bf"},{id:"2203008",name:"\u8fb9\u575d\u53bf"},{id:"2203009",name:"\u4e01\u9752\u53bf"},{id:"2203010",name:"\u5bdf\u96c5\u53bf"},{id:"2203011",name:"\u516b\u5bbf\u53bf"},{id:"2203007",name:"\u7c7b\u4e4c\u9f50\u53bf"}]},{id:"22040",name:"\u5c71\u5357",subItems:[{id:"2204001",name:"\u4e43\u4e1c\u53bf"},{id:"2204002",name:"\u6d1b\u624e\u53bf"},{id:"2204003",name:"\u624e\u56ca\u53bf"},{id:"2204004",name:"\u52a0\u67e5\u53bf"},{id:"2204005",name:"\u8d21\u560e\u53bf"},{id:"2204006",name:"\u9686\u5b50\u53bf"},{id:"2204007",name:"\u6851\u65e5\u53bf"},{id:"2204008",name:"\u9519\u90a3\u53bf"},{id:"2204009",name:"\u743c\u7ed3\u53bf"},{id:"2204010",name:"\u6d6a\u5361\u5b50\u53bf"},{id:"2204011",name:"\u66f2\u677e\u53bf"},{id:"2204012",name:"\u63aa\u7f8e\u53bf"}]},{id:"22050",name:"\u90a3\u66f2",subItems:[{id:"2205010",name:"\u7d22\u53bf"},{id:"2205001",name:"\u90a3\u66f2\u53bf"},{id:"2205002",name:"\u73ed\u6208\u53bf"},{id:"2205003",name:"\u5609\u9ece\u53bf"},{id:"2205004",name:"\u5df4\u9752\u53bf"},{id:"2205005",name:"\u6bd4\u5982\u53bf"},{id:"2205006",name:"\u5c3c\u739b\u53bf"},{id:"2205007",name:"\u8042\u8363\u53bf"},{id:"2205008",name:"\u5b89\u591a\u53bf"},{id:"2205009",name:"\u7533\u624e\u53bf"}]},{id:"22060",name:"\u963f\u91cc",subItems:[{id:"2206001",name:"\u5676\u5c14\u53bf"},{id:"2206002",name:"\u666e\u5170\u53bf"},{id:"2206003",name:"\u672d\u8fbe\u53bf"},{id:"2206004",name:"\u65e5\u571f\u53bf"},{id:"2206005",name:"\u9769\u5409\u53bf"},{id:"2206006",name:"\u6539\u5219\u53bf"},{id:"2206007",name:"\u63aa\u52e4\u53bf"}]},{id:"22070",name:"\u6797\u829d",subItems:[{id:"2207007",name:"\u6717\u53bf"},{id:"2207001",name:"\u6797\u829d\u53bf"},{id:"2207003",name:"\u7c73\u6797\u53bf"},{id:"2207004",name:"\u58a8\u8131\u53bf"},{id:"2207005",name:"\u6ce2\u5bc6\u53bf"},{id:"2207006",name:"\u5bdf\u9685\u53bf"},{id:"2207002",name:"\u5de5\u5e03\u6c5f\u8fbe\u53bf"}]}]},{id:"27000",name:"\u65b0\u7586",subItems:[{id:"145",name:"\u4e4c\u9c81\u6728\u9f50\u5e02",subItems:[{id:"14501",name:"\u5929\u5c71\u533a"},{id:"14504",name:"\u65b0\u5e02\u533a"},{id:"14508",name:"\u7c73\u4e1c\u533a"},{id:"14505",name:"\u6c34\u78e8\u6c9f\u533a"},{id:"14506",name:"\u5934\u5c6f\u6cb3\u533a"},{id:"14507",name:"\u8fbe\u5742\u57ce\u533a"},{id:"14503",name:"\u6c99\u4f9d\u5df4\u514b\u533a"},{id:"14502",name:"\u4e4c\u9c81\u6728\u9f50\u53bf"}]},{id:"27030",name:"\u5580\u4ec0",subItems:[{id:"2703001",name:"\u5580\u4ec0\u5e02"},{id:"2703006",name:"\u4f3d\u5e08"},{id:"2703008",name:"\u5df4\u695a"},{id:"2703009",name:"\u6cfd\u666e"},{id:"2703011",name:"\u838e\u8f66"},{id:"2703012",name:"\u53f6\u57ce"},{id:"2703002",name:"\u9ea6\u76d6\u63d0"},{id:"2703003",name:"\u758f\u9644\u53bf"},{id:"2703004",name:"\u5cb3\u666e\u6e56"},{id:"2703005",name:"\u758f\u52d2\u53bf"},{id:"2703007",name:"\u82f1\u5409\u6c99\u53bf"},{id:"2703010",name:"\u5854\u4ec0\u5e93\u5c14\u5e72\u53bf"}]},{id:"27020",name:"\u514b\u62c9\u739b\u4f9d\u5e02",subItems:[{id:"2702001",name:"\u514b\u62c9\u739b\u4f9d\u533a"},{id:"2702002",name:"\u72ec\u5c71\u5b50\u533a"},{id:"2702003",name:"\u767d\u78b1\u6ee9\u533a"},{id:"2702004",name:"\u4e4c\u5c14\u79be\u533a"}]},{id:"27040",name:"\u4f0a\u7281",subItems:[{id:"27050",name:"\u594e\u5c6f\u5e02"},{id:"27060",name:"\u4f0a\u5b81\u5e02"},{id:"2704003",name:"\u4f0a\u5b81\u53bf"},{id:"2704005",name:"\u970d\u57ce\u53bf"},{id:"2704006",name:"\u5de9\u7559\u53bf"},{id:"2704007",name:"\u65b0\u6e90\u53bf"},{id:"2704008",name:"\u662d\u82cf\u53bf"},{id:"2704002",name:"\u5c3c\u52d2\u514b\u53bf"},{id:"2704001",name:"\u7279\u514b\u65af\u53bf"},{id:"2704004",name:"\u5bdf\u5e03\u67e5\u5c14\u53bf"}]},{id:"27070",name:"\u548c\u7530",subItems:[{id:"2707001",name:"\u548c\u7530\u5e02"},{id:"2707002",name:"\u6c11\u4e30\u53bf"},{id:"2707003",name:"\u548c\u7530\u53bf"},{id:"2707004",name:"\u58a8\u7389\u53bf"},{id:"2707005",name:"\u76ae\u5c71\u53bf"},{id:"2707006",name:"\u6d1b\u6d66\u53bf"},{id:"2707007",name:"\u7b56\u52d2\u53bf"},{id:"2707008",name:"\u4e8e\u7530\u53bf"}]},{id:"27080",name:"\u963f\u52d2\u6cf0",subItems:[{id:"2708001",name:"\u963f\u52d2\u6cf0\u5e02"},{id:"2708003",name:"\u5bcc\u8574\u53bf"},{id:"2708004",name:"\u798f\u6d77\u53bf"},{id:"2708006",name:"\u9752\u6cb3\u53bf"},{id:"2708002",name:"\u5e03\u5c14\u6d25\u53bf"},{id:"2708005",name:"\u54c8\u5df4\u6cb3\u53bf"},{id:"2708007",name:"\u5409\u6728\u4e43\u53bf"}]},{id:"27170",name:"\u5410\u9c81\u756a",subItems:[{id:"2717001",name:"\u5410\u9c81\u756a\u5e02"},{id:"2717002",name:"\u912f\u5584\u53bf"},{id:"2717003",name:"\u6258\u514b\u900a\u53bf"}]},{id:"27100",name:"\u54c8\u5bc6",subItems:[{id:"2710001",name:"\u54c8\u5bc6\u5e02"},{id:"2710002",name:"\u4f0a\u543e\u53bf"},{id:"2710003",name:"\u5df4\u91cc\u5764\u53bf"}]},{id:"27110",name:"\u963f\u514b\u82cf",subItems:[{id:"2711001",name:"\u963f\u514b\u82cf\u5e02"},{id:"2711003",name:"\u6e29\u5bbf\u53bf"},{id:"2711004",name:"\u67ef\u576a\u53bf"},{id:"2711005",name:"\u5e93\u8f66\u53bf"},{id:"2711006",name:"\u6c99\u96c5\u53bf"},{id:"2711007",name:"\u65b0\u548c\u53bf"},{id:"2711008",name:"\u62dc\u57ce\u53bf"},{id:"2711009",name:"\u4e4c\u4ec0\u53bf"},{id:"2711002",name:"\u963f\u74e6\u63d0\u53bf"}]},{id:"27120",name:"\u514b\u5b5c\u52d2\u82cf\u67ef\u5c14\u514b\u5b5c",subItems:[{id:"2712001",name:"\u963f\u56fe\u4ec0\u5e02"},{id:"2712002",name:"\u963f\u514b\u9676\u53bf"},{id:"2712003",name:"\u963f\u5408\u5947\u53bf"},{id:"2712004",name:"\u4e4c\u6070\u53bf"}]},{id:"27130",name:"\u5df4\u97f3\u90ed\u695e",subItems:[{id:"27090",name:"\u5e93\u5c14\u52d2\u5e02"},{id:"2713001",name:"\u548c\u7855\u53bf"},{id:"2713002",name:"\u8f6e\u53f0\u53bf"},{id:"2713003",name:"\u535a\u6e56\u53bf"},{id:"2713004",name:"\u5c09\u7281\u53bf"},{id:"2713005",name:"\u82e5\u7f8c\u53bf"},{id:"2713006",name:"\u4e14\u672b\u53bf"},{id:"2713007",name:"\u7109\u8006\u53bf"},{id:"2713008",name:"\u548c\u9759\u53bf"}]},{id:"27140",name:"\u660c\u5409",subItems:[{id:"2714001",name:"\u660c\u5409\u5e02"},{id:"2714002",name:"\u961c\u5eb7\u5e02"},{id:"2714007",name:"\u6728\u5792\u53bf"},{id:"2714005",name:"\u5947\u53f0\u53bf"},{id:"2714003",name:"\u547c\u56fe\u58c1\u53bf"},{id:"2714004",name:"\u739b\u7eb3\u65af\u53bf"},{id:"2714006",name:"\u5409\u6728\u8428\u5c14\u53bf"}]},{id:"27150",name:"\u535a\u5c14\u5854\u62c9",subItems:[{id:"2715001",name:"\u535a\u4e50\u5e02"},{id:"2715002",name:"\u7cbe\u6cb3\u53bf"},{id:"2715003",name:"\u6e29\u6cc9\u53bf"}]},{id:"27160",name:"\u5854\u57ce",subItems:[{id:"2716001",name:"\u5854\u57ce\u5e02"},{id:"2716002",name:"\u4e4c\u82cf\u5e02"},{id:"2716003",name:"\u989d\u654f\u53bf"},{id:"2716004",name:"\u6c99\u6e7e\u53bf"},{id:"2716005",name:"\u6258\u91cc\u53bf"},{id:"2716006",name:"\u88d5\u6c11\u53bf"},{id:"2716007",name:"\u548c\u5e03\u514b\u8d5b\u5c14\u53bf"}]},{id:"27180",name:"\u81ea\u6cbb\u533a\u76f4\u8f96",subItems:[{id:"2718001",name:"\u77f3\u6cb3\u5b50\u5e02"},{id:"2718002",name:"\u963f\u62c9\u5c14\u5e02"},{id:"2718004",name:"\u4e94\u5bb6\u6e20\u5e02"},{id:"2718003",name:"\u56fe\u6728\u8212\u514b\u5e02"}]}]},{id:"21000",name:"\u4e91\u5357\u7701",subItems:[{id:"80",name:"\u6606\u660e\u5e02",subItems:[{id:"8001",name:"\u4e94\u534e\u533a"},{id:"8003",name:"\u76d8\u9f99\u533a"},{id:"8005",name:"\u5b98\u6e21\u533a"},{id:"8007",name:"\u897f\u5c71\u533a"},{id:"8009",name:"\u4e1c\u5ddd\u533a"},{id:"8011",name:"\u5b89\u5b81\u5e02"},{id:"8002",name:"\u664b\u5b81\u53bf"},{id:"8008",name:"\u5d69\u660e\u53bf"},{id:"8006",name:"\u5b9c\u826f\u53bf"},{id:"8004",name:"\u5bcc\u6c11\u53bf"},{id:"8010",name:"\u77f3\u6797\u53bf"},{id:"8012",name:"\u7984\u529d\u53bf"},{id:"8013",name:"\u5448\u8d21\u53bf"},{id:"8014",name:"\u5bfb\u7538\u53bf"}]},{id:"21030",name:"\u5927\u7406\u5dde",subItems:[{id:"2103001",name:"\u5927\u7406\u5e02"},{id:"2103002",name:"\u5251\u5ddd\u53bf"},{id:"2103003",name:"\u7965\u4e91\u53bf"},{id:"2103004",name:"\u9e64\u5e86\u53bf"},{id:"2103005",name:"\u5bbe\u5ddd\u53bf"},{id:"2103006",name:"\u6f3e\u6fde\u53bf"},{id:"2103007",name:"\u5f25\u6e21\u53bf"},{id:"2103008",name:"\u5357\u6da7\u53bf"},{id:"2103009",name:"\u6c38\u5e73\u53bf"},{id:"2103010",name:"\u5dcd\u5c71\u53bf"},{id:"2103011",name:"\u4e91\u9f99\u53bf"},{id:"2103012",name:"\u6d31\u6e90\u53bf"}]},{id:"21040",name:"\u4e3d\u6c5f\u5e02",subItems:[{id:"2104001",name:"\u53e4\u57ce\u533a"},{id:"2104002",name:"\u6c38\u80dc\u53bf"},{id:"2104003",name:"\u534e\u576a\u53bf"},{id:"2104004",name:"\u7389\u9f99\u53bf"},{id:"2104005",name:"\u5b81\u8497\u53bf"}]},{id:"21020",name:"\u7389\u6eaa\u5e02",subItems:[{id:"2102001",name:"\u7ea2\u5854\u533a"},{id:"2102002",name:"\u65b0\u5e73\u53bf"},{id:"2102003",name:"\u6c5f\u5ddd\u53bf"},{id:"2102004",name:"\u5143\u6c5f\u53bf"},{id:"2102005",name:"\u6f84\u6c5f\u53bf"},{id:"2102006",name:"\u901a\u6d77\u53bf"},{id:"2102007",name:"\u534e\u5b81\u53bf"},{id:"2102008",name:"\u6613\u95e8\u53bf"},{id:"2102009",name:"\u5ce8\u5c71\u53bf"}]},{id:"21050",name:"\u66f2\u9756\u5e02",subItems:[{id:"2105001",name:"\u9e92\u9e9f\u533a"},{id:"2105002",name:"\u4f1a\u6cfd\u53bf"},{id:"2105003",name:"\u5ba3\u5a01\u5e02"},{id:"2105004",name:"\u6cbe\u76ca\u53bf"},{id:"2105005",name:"\u9a6c\u9f99\u53bf"},{id:"2105006",name:"\u9646\u826f\u53bf"},{id:"2105007",name:"\u5e08\u5b97\u53bf"},{id:"2105008",name:"\u7f57\u5e73\u53bf"},{id:"2105009",name:"\u5bcc\u6e90\u53bf"}]},{id:"21060",name:"\u4fdd\u5c71\u5e02",subItems:[{id:"2106001",name:"\u9686\u9633\u533a"},{id:"2106002",name:"\u65bd\u7538\u53bf"},{id:"2106003",name:"\u817e\u51b2\u53bf"},{id:"2106004",name:"\u9f99\u9675\u53bf"},{id:"2106005",name:"\u660c\u5b81\u53bf"}]},{id:"21070",name:"\u662d\u901a\u5e02",subItems:[{id:"2107001",name:"\u662d\u9633\u533a"},{id:"2107002",name:"\u9547\u96c4\u53bf"},{id:"2107003",name:"\u9c81\u7538\u53bf"},{id:"2107004",name:"\u5f5d\u826f\u53bf"},{id:"2107005",name:"\u5de7\u5bb6\u53bf"},{id:"2107006",name:"\u5a01\u4fe1\u53bf"},{id:"2107007",name:"\u76d0\u6d25\u53bf"},{id:"2107008",name:"\u6c34\u5bcc\u53bf"},{id:"2107009",name:"\u5927\u5173\u53bf"},{id:"2107010",name:"\u6c38\u5584\u53bf"},{id:"2107011",name:"\u7ee5\u6c5f\u53bf"}]},{id:"21080",name:"\u666e\u6d31\u5e02",subItems:[{id:"2108001",name:"\u601d\u8305\u533a"},{id:"2108002",name:"\u5b5f\u8fde\u53bf"},{id:"2108003",name:"\u5b81\u6d31\u53bf"},{id:"2108004",name:"\u6f9c\u6ca7\u53bf"},{id:"2108005",name:"\u58a8\u6c5f\u53bf"},{id:"2108006",name:"\u897f\u76df\u53bf"},{id:"2108007",name:"\u666f\u4e1c\u53bf"},{id:"2108008",name:"\u666f\u8c37\u53bf"},{id:"2108009",name:"\u9547\u6c85\u53bf"},{id:"2108010",name:"\u6c5f\u57ce\u53bf"}]},{id:"21090",name:"\u4e34\u6ca7\u5e02",subItems:[{id:"2109001",name:"\u4e34\u7fd4\u533a"},{id:"2109002",name:"\u6ca7\u6e90\u53bf"},{id:"2109003",name:"\u51e4\u5e86\u53bf"},{id:"2109004",name:"\u4e91\u53bf"},{id:"2109005",name:"\u6c38\u5fb7\u53bf"},{id:"2109006",name:"\u9547\u5eb7\u53bf"},{id:"2109007",name:"\u53cc\u6c5f\u53bf"},{id:"2109008",name:"\u803f\u9a6c\u53bf"}]},{id:"21100",name:"\u8fea\u5e86\u5dde",subItems:[{id:"2110001",name:"\u9999\u683c\u91cc\u62c9\u53bf"},{id:"2110002",name:"\u5fb7\u94a6\u53bf"},{id:"2110003",name:"\u7ef4\u897f\u53bf"}]},{id:"21110",name:"\u6587\u5c71\u5dde",subItems:[{id:"2111001",name:"\u6587\u5c71\u53bf"},{id:"2111002",name:"\u5bcc\u5b81\u53bf"},{id:"2111003",name:"\u781a\u5c71\u53bf"},{id:"2111004",name:"\u897f\u7574\u53bf"},{id:"2111005",name:"\u9ebb\u6817\u5761"},{id:"2111006",name:"\u9a6c\u5173\u53bf"},{id:"2111007",name:"\u4e18\u5317\u53bf"},{id:"2111008",name:"\u5e7f\u5357\u53bf"}]},{id:"21120",name:"\u7ea2\u6cb3\u5dde",subItems:[{id:"2112001",name:"\u8499\u81ea\u53bf"},{id:"2112002",name:"\u6cf8\u897f\u53bf"},{id:"2112003",name:"\u4e2a\u65e7\u5e02"},{id:"2112004",name:"\u5143\u9633\u53bf"},{id:"2112005",name:"\u5f00\u8fdc\u5e02"},{id:"2112006",name:"\u7ea2\u6cb3\u53bf"},{id:"2112007",name:"\u7eff\u6625\u53bf"},{id:"2112008",name:"\u91d1\u5e73\u53bf"},{id:"2112009",name:"\u5efa\u6c34\u53bf"},{id:"2112010",name:"\u6cb3\u53e3\u53bf"},{id:"2112011",name:"\u77f3\u5c4f\u53bf"},{id:"2112012",name:"\u5c4f\u8fb9\u53bf"},{id:"2112013",name:"\u5f25\u52d2\u53bf"}]},{id:"21130",name:"\u897f\u53cc\u7248\u7eb3\u5dde",subItems:[{id:"2113001",name:"\u666f\u6d2a\u5e02"},{id:"2113002",name:"\u52d0\u6d77\u53bf"},{id:"2113003",name:"\u52d0\u814a\u53bf"}]},{id:"21140",name:"\u695a\u96c4\u5dde",subItems:[{id:"2114001",name:"\u695a\u96c4\u5e02"},{id:"2114002",name:"\u5143\u8c0b\u53bf"},{id:"2114003",name:"\u53cc\u67cf\u53bf"},{id:"2114004",name:"\u6b66\u5b9a\u53bf"},{id:"2114005",name:"\u725f\u5b9a\u53bf"},{id:"2114006",name:"\u7984\u4e30\u53bf"},{id:"2114007",name:"\u5357\u534e\u53bf"},{id:"2114008",name:"\u59da\u5b89\u53bf"},{id:"2114009",name:"\u5927\u59da\u53bf"},{id:"2114010",name:"\u6c38\u4ec1\u53bf"}]},{id:"21150",name:"\u5fb7\u5b8f\u5dde",subItems:[{id:"2115001",name:"\u6f5e\u897f\u5e02"},{id:"2115002",name:"\u745e\u4e3d\u5e02"},{id:"2115003",name:"\u6881\u6cb3\u53bf"},{id:"2115004",name:"\u76c8\u6c5f\u53bf"},{id:"2115005",name:"\u9647\u5ddd\u53bf"}]},{id:"21160",name:"\u6012\u6c5f\u5dde",subItems:[{id:"2116001",name:"\u6cf8\u6c34\u53bf"},{id:"2116002",name:"\u798f\u8d21\u53bf"},{id:"2116003",name:"\u8d21\u5c71\u53bf"},{id:"2116004",name:"\u5170\u576a\u53bf"}]}]},{id:"34000",name:"\u9999\u6e2f",subItems:[{id:"340001",name:"\u4e2d\u897f\u533a"},{id:"340002",name:"\u6cb9\u5c16\u65fa\u533a"},{id:"340003",name:"\u8343\u6e7e\u533a"},{id:"340004",name:"\u4e1c\u533a"},{id:"340005",name:"\u79bb\u5c9b\u533a"},{id:"340006",name:"\u5143\u6717\u533a"},{id:"340007",name:"\u4e5d\u9f99\u57ce\u533a"},{id:"340008",name:"\u8475\u9752\u533a"},{id:"340009",name:"\u89c2\u5858\u533a"},{id:"340010",name:"\u5317\u533a"},{id:"340011",name:"\u5357\u533a"},{id:"340012",name:"\u897f\u8d21\u533a"},{id:"340013",name:"\u6df1\u6c34\u57d7\u533a"},{id:"340014",name:"\u6c99\u7530\u533a"},{id:"340015",name:"\u9ec4\u5927\u4ed9\u533a"},{id:"340016",name:"\u5c6f\u95e8\u533a"},{id:"340017",name:"\u6e7e\u4ed4\u533a"},{id:"340018",name:"\u5927\u57d4\u533a"}]},{id:"35000",name:"\u6fb3\u95e8",subItems:[{id:"190",name:"\u6fb3\u95e8"}]},{id:"36000",name:"\u53f0\u6e7e",subItems:[{id:"195",name:"\u53f0\u6e7e"}]},{id:"37000",name:"\u5176\u4ed6\u4e9a\u6d32\u56fd\u5bb6\u548c\u5730\u533a",subItems:[{id:"200",name:"\u5176\u4ed6\u4e9a\u6d32\u56fd\u5bb6\u548c\u5730\u533a"}]},{id:"38000",name:"\u5317\u7f8e\u6d32",subItems:[{id:"205",name:"\u5317\u7f8e\u6d32"}]},{id:"41000",name:"\u5357\u7f8e\u6d32",subItems:[{id:"230",name:"\u5357\u7f8e\u6d32"}]},{id:"39000",name:"\u5927\u6d0b\u6d32",subItems:[{id:"210",name:"\u5927\u6d0b\u6d32"}]},{id:"40000",name:"\u6b27\u6d32",subItems:[{id:"215",name:"\u6b27\u6d32"}]},{id:"42000",name:"\u975e\u6d32",subItems:[{id:"235",name:"\u975e\u6d32"}]}]}function getInd(){return[{id:"100",name:"\u8ba1\u7b97\u673a",enname:"Computer"},{id:"3600",name:"\u4e92\u8054\u7f51\u2022\u7535\u5b50\u5546\u52a1",enname:"Internet"},{id:"1123000",name:"\u7f51\u7edc\u6e38\u620f",enname:"Online Game"},{id:"300",name:"\u7535\u5b50\u2022\u5fae\u7535\u5b50",enname:"Electronics"},{id:"400",name:"\u901a\u4fe1(\u8bbe\u5907\u2022\u8fd0\u8425\u2022\u589e\u503c\u670d\u52a1)",enname:"Telecommunication"},{id:"800",name:"\u5e7f\u544a\u2022\u4f1a\u5c55\u2022\u516c\u5173",enname:"advertising"},{id:"1100",name:"\u623f\u5730\u4ea7\u5f00\u53d1\u2022\u5efa\u7b51\u4e0e\u5de5\u7a0b",enname:"Real Estate construction"},{id:"3700",name:"\u7269\u4e1a\u7ba1\u7406\u2022\u5546\u4e1a\u4e2d\u5fc3",enname:"Property Management"},{id:"1500",name:"\u5bb6\u5c45\u2022\u5ba4\u5185\u8bbe\u8ba1\u2022\u88c5\u6f62",enname:"fitment"},{id:"2800",name:"\u4e2d\u4ecb\u670d\u52a1",enname:"Service Agency"},{id:"900",name:"\u4e13\u4e1a\u670d\u52a1(\u54a8\u8be2\u2022\u8d22\u4f1a\u2022\u6cd5\u5f8b\u7b49)",enname:"Professional Service"},{id:"1124000",name:"\u68c0\u9a8c\u2022\u68c0\u6d4b\u2022\u8ba4\u8bc1",enname:"testing Certification"},{id:"1000",name:"\u91d1\u878d\u4e1a(\u6295\u8d44\u2022\u4fdd\u9669\u2022\u8bc1\u5238\u2022\u94f6\u884c\u2022\u57fa\u91d1)",enname:"Finance"},{id:"700",name:"\u8d38\u6613\u2022\u8fdb\u51fa\u53e3",enname:"trading"},{id:"1118000",name:"\u5a92\u4f53\u2022\u51fa\u7248\u2022\u6587\u5316\u4f20\u64ad",enname:"Media Publishing"},{id:"2600",name:"\u5370\u5237\u2022\u5305\u88c5\u2022\u9020\u7eb8",enname:"Printing packaging"},{id:"1300",name:"\u5feb\u901f\u6d88\u8d39\u54c1(\u98df\u54c1\u2022\u996e\u6599\u2022\u65e5\u5316\u2022\u70df\u9152\u7b49)",enname:"fast Consumer"},{id:"1113000",name:"\u8010\u7528\u6d88\u8d39\u54c1(\u670d\u9970\u2022\u7eba\u7ec7\u2022\u76ae\u9769\u2022\u5bb6\u5177)",enname:"Consumer Durables"},{id:"1125000",name:"\u73a9\u5177\u2022\u5de5\u827a\u54c1\u2022\u6536\u85cf\u54c1\u2022\u5962\u4f88\u54c1",enname:"Toys Artworks Collections"},{id:"1400",name:"\u5bb6\u7535\u4e1a",enname:"Domestic Appliances"},{id:"1800",name:"\u529e\u516c\u8bbe\u5907\u2022\u7528\u54c1",enname:"Office euqipment"},{id:"1600",name:"\u65c5\u6e38\u2022\u9152\u5e97\u2022\u9910\u996e\u670d\u52a1",enname:"catering"},{id:"600",name:"\u6279\u53d1\u2022\u96f6\u552e",enname:"Wholesale Retail"},{id:"1700",name:"\u4ea4\u901a\u2022\u8fd0\u8f93\u2022\u7269\u6d41",enname:"Logistics"},{id:"1200",name:"\u5a31\u4e50\u2022\u8fd0\u52a8\u2022\u4f11\u95f2",enname:"Entertainment"},{id:"2300",name:"\u5236\u836f\u2022\u751f\u7269\u5de5\u7a0b",enname:"Pharmaceuticals Biotechnology"},{id:"3200",name:"\u533b\u7597\u2022\u4fdd\u5065\u2022\u7f8e\u5bb9\u2022\u536b\u751f\u670d\u52a1",enname:"Medical service"},{id:"2000",name:"\u533b\u7597\u8bbe\u5907\u2022\u5668\u68b0",enname:"Medical Equipment"},{id:"2400",name:"\u73af\u4fdd",enname:"Environment protection"},{id:"2200",name:"\u77f3\u6cb9\u2022\u5316\u5de5\u2022\u77ff\u4ea7\u2022\u91c7\u6398\u2022\u51b6\u70bc\u2022\u539f\u6750\u6599",enname:"Petroleum Mining Raw Meterial"},{id:"1122000",name:"\u80fd\u6e90(\u7535\u529b\u2022\u77f3\u6cb9)\u2022\u6c34\u5229",enname:"Energy"},{id:"3900",name:"\u4eea\u5668\u2022\u4eea\u8868\u2022\u5de5\u4e1a\u81ea\u52a8\u5316\u2022\u7535\u6c14",enname:"Instrument Automation Electicity"},{id:"2100",name:"\u6c7d\u8f66\u2022\u6469\u6258\u8f66(\u5236\u9020\u2022\u7ef4\u62a4\u2022\u914d\u4ef6\u2022\u9500\u552e\u2022\u670d\u52a1)",enname:"Automobile"},{id:"1114000",name:"\u673a\u68b0\u5236\u9020\u2022\u673a\u7535\u2022\u91cd\u5de5",enname:"Heavy industry"},{id:"1115000",name:"\u539f\u6750\u6599\u53ca\u52a0\u5de5(\u91d1\u5c5e\u2022\u6728\u6750\u2022\u6a61\u80f6\u2022\u5851\u6599\u2022\u73bb\u7483\u2022\u9676\u74f7\u2022\u5efa\u6750)",enname:"Raw Material and processing"},{id:"2900",name:"\u519c\u2022\u6797\u2022\u7267\u2022\u6e14",enname:"Agriculture"},{id:"1105000",name:"\u822a\u7a7a\u2022\u822a\u5929\u7814\u7a76\u4e0e\u5236\u9020",enname:"Aeronautics"},{id:"1126000",name:"\u8239\u8236\u5236\u9020",enname:"Shipbuilding"},{id:"3100",name:"\u6559\u80b2\u2022\u57f9\u8bad\u2022\u79d1\u7814\u2022\u9662\u6821",enname:"Education Training"},{id:"1116000",name:"\u653f\u5e9c\u2022\u975e\u8425\u5229\u673a\u6784",enname:"Government Nonprofit Organization"},{id:"1121000",name:"\u5176\u4ed6",enname:"Others"}]}