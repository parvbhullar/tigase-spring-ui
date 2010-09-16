package com.linkage.framework.page;
/**
 * 
 *	重写分页方法，优惠券列表用
 *
 * @author jiale.wang
 *
 * @create on 2010-6-13 上午11:18:59
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see PageUtil
 */
public class CouponPageUtil extends PageUtil{
	/**
	  * 自定义分页方法 
	  */
	 public String getToolsMenu() {
	        StringBuffer str = new StringBuffer("");
	        int next, prev;
	        prev = curPage - 1;
	        next = curPage + 1;

	        if (curPage > 1) {
	            str.append(
	                "<a href=\"javascript:goPage(" +prev + ");\" onclick=''>上一页</a>&nbsp;");
	        } else {
	            str.append("<a title='已经是第一页'>上一页</a>&nbsp;&nbsp;");
	        }
	        
	        if (curPage < totalPage) {
	            str.append(
	                "<a href=\"javascript:goPage(" +next + ");\" class=\"page-next\" onclick=''>下一页</a>&nbsp;");
	        } else {
	            str.append("<a title='已经是最后一页' class=\"page-next\">下一页</a>&nbsp;");
	        }
	       
	        str.append(" 共" + totalRow + "条记录");
//	        
	        str.append("  转到");
	        str.append("<SELECT style='width: 50px;' size=1 name=Pagelist onchange='goPage(this.value)'>");
	        for (int i = 1; i < totalPage + 1; i++) {
	            if (i == curPage) {
	                str.append("<OPTION value=" + i + " selected>" + i +
	                           "</OPTION>");
	            } else {
	                str.append("<OPTION value=" + i + ">" + i + "</OPTION>");
	            }
	        }
	        str.append("</SELECT>页");
	      //  str.append("<input type='text' size=1 onblur='goPage(this.value);'/>页");
	        str.append("<INPUT type=hidden  value=" + curPage + " name=\"pages\" > ");
	        str.append("<INPUT type=hidden  value=" + pageSize +
	                   " name=\"pageSize\"> ");
	        return str.toString();
	    }
}
