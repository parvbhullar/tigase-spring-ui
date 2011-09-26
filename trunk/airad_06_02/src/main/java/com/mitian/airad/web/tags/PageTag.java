/*
 * Created on 2005-4-27 To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package com.mitian.airad.web.tags;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author baojun
 */
public class PageTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 5979549104527858982L;
    //
    private StringBuffer buffer = new StringBuffer(0);
    //
    private static final String PREVIOUS_PAGE_CN = "上一页";
    private static final String PREVIOUS_PAGE_TITLE_CN = "翻至上一页";
    private static final String NEXT_PAGE_CN = "下一页";
    private static final String NEXT_PAGE_TITLE_CN = "翻至下一页";
    private static final String FIRST_CN =
            "<img src=\"/images/page_first_b.gif\" alt=\"翻至第一页\" border=\"0\" align=\"texttop\" />";
    private static final String LAST_CN =
            "<img src=\"/images/page_last_b.gif\" alt=\"翻至最后一页\" border=\"0\" align=\"texttop\" />";
    // //////
    private String previousPage = "";
    private String nextPage = "";
    private String previousPageTitle = "";
    private String nextPageTitle = "";
    private String first = "";
    private String last = "";
    //
    private static final String PAGENUM = "PAGENUM";
    private static final Pattern PAGE = Pattern.compile("[0-9]+");
    // /////////页面传入的值////////////////////////////////
    private String href = "";
    private int currentPage = 1;
    private String totalRecord = "";
    private int pageSize;
    private String sumInfo = "";
    private String lanCode = "";
    private String firstPageUrl = "";

    @Override
    public int doEndTag() throws JspException {
        setHtmlIntoStringBuffer();
        write(buffer);
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            initTag();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    /**
     * @return
     */
    public StringBuffer getBuffer() {
        return buffer;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return
     */
    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    /**
     * @return
     */
    public String getHref() {
        return href;
    }

    /**
     * @return
     */
    public String getLanCode() {
        return lanCode;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSumInfo() {
        return sumInfo;
    }

    public void setSumInfo(String sumInfo) {
        this.sumInfo = sumInfo;
    }

    /**
     * @return
     */
    public String getTotalRecord() {
        return totalRecord;
    }

    public void initTag() throws Exception {
        buffer = new StringBuffer(0);
        previousPage = PREVIOUS_PAGE_CN;
        previousPageTitle = PREVIOUS_PAGE_TITLE_CN;
        nextPage = NEXT_PAGE_CN;
        nextPageTitle = NEXT_PAGE_TITLE_CN;
        first = FIRST_CN;
        last = LAST_CN;
    }

    /**
     * @param buffer
     */
    public void setBuffer(StringBuffer buffer) {
        this.buffer = buffer;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @param string
     */
    public void setFirstPageUrl(String string) {
        firstPageUrl = string;
    }

    /**
     * @param string
     */
    public void setHref(String string) {
        href = string;
    }

    public void setHtmlIntoStringBuffer() {
        boolean needPrevious = false;
        boolean needNext = false;
        boolean needFirst = false;
        boolean needLast = false;
        int iNext = 0;
        int iPrevious = 0;
        int iCurrent = 0;
        int iTotal = 0;
        int iTotalRecord = 0;
        int iPageSize = 0;
        int iStart = 0;
        int iEnd = 0;
        String[] pageFace = new String[11];
        if (isNum(totalRecord)) {
            iCurrent = currentPage;
            iTotalRecord = Integer.parseInt(totalRecord);
            iPageSize = pageSize;
            // 一页大小为0则退出
            if (iPageSize <= 0) {
                return;
            }
            // 计算总页数
            iTotal = (iTotalRecord + iPageSize - 1) / iPageSize;
            if (iTotal > 1) {
                for (int j = 1; j <= 10; j++) {
                    pageFace[j] = Integer.toString(j);
                }
                if (iTotal > 10) {
                    if (iCurrent <= 5) {
                        for (int m = 1; m <= 7; m++) {
                            pageFace[m] = Integer.toString(m);
                        }
                        pageFace[8] = "p";
                        pageFace[9] = Integer.toString(iTotal - 1);
                        pageFace[10] = Integer.toString(iTotal);
                    }
                    else {
                        if (iCurrent + 6 - 1 < iTotal) {
                            for (int n = 4; n <= 7; n++) {
                                pageFace[n] = Integer.toString(iCurrent - 1 + n - 4);
                            }
                            // pageFace[3] = "p";
                            pageFace[8] = "p";
                            pageFace[9] = Integer.toString(iTotal - 1);
                            pageFace[10] = Integer.toString(iTotal);
                        }
                        else {
                            for (int k = 10; k >= 4; k--) {
                                pageFace[k] = Integer.toString(iTotal - (10 - k));
                            }
                        }
                        pageFace[3] = "p";
                    }
                }
                else {
                    for (int i = 1; i <= 10; i++) {
                        if (i <= iTotal) {
                            pageFace[i] = Integer.toString(i);
                        }
                        else {
                            pageFace[i] = "n";
                        }
                    }
                }
                if (iCurrent > 1) {
                    needPrevious = true;
                    needFirst = false;
                    iPrevious = iCurrent - 1;
                }
                if (iCurrent < iTotal) {
                    needNext = true;
                    needLast = false;
                    iNext = iCurrent + 1;
                }
            }
            else {
                buffer.append("<div class=\"pager\">");
                buffer.append("共<strong>" + iTotalRecord + "</strong>条记录");
                buffer.append("</div>");
                return;
            }

            buffer.append("<div class=\"pager\">");
            // buffer.append("<div>");
            if ((sumInfo.equals("") || sumInfo.equals("left")) && !sumInfo.equals("false")) {
                buffer.append("<span>共<strong>" + iTotalRecord + "</strong>条记录</span>");
            }
            if (needFirst) {
                // 第一页
                buffer.append("<a href=\"" + getReplacedHref("1") + "\">" + first + "</a>");
            }
            if (needPrevious) {
                // 前一页
                buffer.append("<a href=\"" + getReplacedHref(String.valueOf(iPrevious)) + "\">&lt;" + previousPage
                        + "</a>");
            }
            for (int i = 1; i < pageFace.length; i++) {
                if (pageFace[i] == "p") {
                    buffer.append("...");
                }
                else if (pageFace[i] == "n") {
                    // 此处不能删除
                }
                else {
                    if (pageFace[i].equals(Integer.toString(iCurrent))) {
                        buffer.append("<span>" + iCurrent + "</span>");
                    }
                    else {
                        buffer.append("<a href=\"" + getReplacedHref(pageFace[i]) + "\">" + pageFace[i] + "</a>");
                    }
                }
            }
            if (needNext) {
                // 后一页
                buffer.append("<a href=\"" + getReplacedHref(String.valueOf(iNext)) + "\">" + nextPage + "&gt;</a>");
            }
            if (needLast) {
                // 最后一页
                buffer.append("<a href=\"" + getReplacedHref(String.valueOf(iTotal)) + "\">" + last + "</a>");
            }
            if (sumInfo.equals("right") && !sumInfo.equals("false")) {
                buffer.append("共<strong>" + iTotalRecord + "</strong>条记录");
            }
            // buffer.append("</div>");
            buffer.append("</div>");
        }
    }

    /**
     * @param string
     */
    public void setLanCode(String string) {
        lanCode = string;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @param string
     */
    public void setTotalRecord(String string) {
        totalRecord = string;
    }

    public void write(String string) {
        this.write(string, pageContext.getOut());
    }

    public void write(String string, JspWriter out) {
        if (string == null) {
            return;
        }
        try {
            out.write(string);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method. Write a string to the default out
     * 
     * @param buffer StringBuffer
     */
    public void write(StringBuffer buffer) {
        this.write(buffer.toString());
    }

    private String getReplacedHref(String num) {
        if ((firstPageUrl != null) && !"".equals(firstPageUrl) && "1".equals(num)) {
            return firstPageUrl.replaceAll(PAGENUM, num);
        }
        else {
            return href.replaceAll(PAGENUM, num);
        }
    }

    private boolean isNum(String pageInt) {
        Matcher m = PAGE.matcher(pageInt);
        boolean isNUM = m.matches();
        return isNUM;
    }

    /**
     * @return the previousPage
     */
    public String getPreviousPage() {
        return previousPage;
    }

    /**
     * @param previousPage the previousPage to set
     */
    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    /**
     * @return the nextPage
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * @param nextPage the nextPage to set
     */
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * @return the previousPageTitle
     */
    public String getPreviousPageTitle() {
        return previousPageTitle;
    }

    /**
     * @param previousPageTitle the previousPageTitle to set
     */
    public void setPreviousPageTitle(String previousPageTitle) {
        this.previousPageTitle = previousPageTitle;
    }

    /**
     * @return the nextPageTitle
     */
    public String getNextPageTitle() {
        return nextPageTitle;
    }

    /**
     * @param nextPageTitle the nextPageTitle to set
     */
    public void setNextPageTitle(String nextPageTitle) {
        this.nextPageTitle = nextPageTitle;
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }
}
