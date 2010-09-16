package com.linkage.framework.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页函数BEAN
 * Date: 2009-7-6
 * Time: 14:42:59
 */
public class PaginationSupport {
    public static int DEFAULT_COUNT_ON_EACH_PAGE = 5;
    private int totalCount;
    private int startIndex;
    private int countOnEachPage;
    @SuppressWarnings("unchecked")
	private List items;
    private String urlParams;
    public PaginationSupport() {
        this(DEFAULT_COUNT_ON_EACH_PAGE);
    }
                                                                         
    public PaginationSupport(int countOnEachPage) {
        startIndex = 0;
        if (countOnEachPage < 1) {
            throw new IllegalArgumentException("Count should be greater than zero!");
        } else {
            this.countOnEachPage = countOnEachPage;
        }
    }

    public PaginationSupport(int startIndex, int totalCount, int countOnEachPage) {
        this.startIndex = startIndex;
        this.totalCount = totalCount;
        this.countOnEachPage = countOnEachPage;
        if(this.countOnEachPage <= 0) {
            this.countOnEachPage = DEFAULT_COUNT_ON_EACH_PAGE;
        }

    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setCountOnEachPage(int countOnEachPage) {
        this.countOnEachPage = countOnEachPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getEndIndex() {
        int endIndex = getStartIndex() + countOnEachPage;
        if (endIndex > totalCount) {
            return totalCount;
        } else {
            return endIndex;
        }
    }

    public int getStartIndex() {
        if (startIndex > totalCount) {
            return totalCount;
        } else if (startIndex < 0) {
            return 0;
        } else {
            return startIndex;
        }
    }

    public int getNextIndex() {
        int[] nextStartIndexes = getNextStartIndexes();
        if (nextStartIndexes == null) {
            return getTotalCount();
        } else {
            return nextStartIndexes[0];
        }
    }

    public int getPreviousIndex() {
        int[] previousIndexes = getPreviousStartIndexes();
        if (previousIndexes == null) {
            return getStartIndex();
        } else {
            return previousIndexes[previousIndexes.length - 1];
        }
    }

    public int[] getNextStartIndexes() {
        int index = getEndIndex();
        if (index == totalCount) {
            return null;
        }
        int count = (totalCount - index) / countOnEachPage;
        if ((totalCount - index) % countOnEachPage > 0) {
            count++;
        }
        int result[] = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = index;
            index += countOnEachPage;
        }

        return result;
    }

    public int[] getPreviousStartIndexes() {
        int index = getStartIndex();
        if (index == 0) {
            return null;
        }
        int count = index / countOnEachPage;
        if (index % countOnEachPage > 0) {
            count++;
        }
        int result[] = new int[count];
        for (int i = count - 1; i > 0; i--) {
            index -= countOnEachPage;
            result[i] = index;
        }

        return result;
    }

    public int getCountOnEachPage() {
        return countOnEachPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        validate();
    }

    private void validate() {
        if (startIndex > totalCount) {
            int i = getTotalCount() % countOnEachPage;
            startIndex = totalCount - i;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
    }

    /**
     * Return the number of pages for the current query.
     */
    public int getPageCount() {
        int pages = getTotalCount() / countOnEachPage;
        int i = getTotalCount() % countOnEachPage;
        if (i > 0) {
            pages++;
        }
        if (getTotalCount() == 0) {
            pages = 1;
        }
        return pages;
    }

    /**
     * Return the current page number.
     * Page numbering starts with 1.
     */
    public int getPage() {
        int page = startIndex / countOnEachPage;
        return page + 1;
    }

    public void setPage(int page) {
        if (page < 1) {
            startIndex = 0;
        } else {
            startIndex = (page - 1) * countOnEachPage;
        }
    }

    public String toString() {
        return "PaginationSupport["
            + "totalCount=" + totalCount
            + ", startIndex="+ startIndex
            + ", pageCount=" + getPageCount()
            + ", dao=" + getPage()
            + "]";
    }

    @SuppressWarnings("unchecked")
	public void setItems(List items) {
        this.items = items;
    }

    @SuppressWarnings("unchecked")
	public List getItems() {
        return items;
    }

    
    public String getUrlParams() {
        return urlParams;
    }

    public void setUrlParamsByJava(String urlParams) {
        this.urlParams = urlParams;
    }

    

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        PaginationSupport ps = new PaginationSupport();
        ps.setTotalCount(1000);
        ps.setCountOnEachPage(50);
        List items = new ArrayList();
        for(int i = 0;i < 50; i++) {
            items.add(new TestBean(i));
        }
        ps.setItems(items);
        long tick = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - tick);
    }

    public static class TestBean {
        private long id;
        private TestBean testBean;

        public TestBean getTestBean() {
            return testBean;
        }

        public void setTestBean(TestBean testBean) {
            this.testBean = testBean;
        }

        public TestBean(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

}
