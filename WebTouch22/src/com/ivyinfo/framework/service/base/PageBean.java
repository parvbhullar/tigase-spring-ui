package com.ivyinfo.framework.service.base;

public class PageBean extends BaseBean{
	//翻页
	private final int maxEntry = 3;
	private int currentPage = 0;
	

	public int getMaxEntry() {
		return maxEntry;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageUpIndex(int qty){
		int total = totalPage(qty);
		currentPage++;
		if(currentPage >= total){
			currentPage = total-1;
		}
			
		return currentPage * maxEntry;
	}
	
	public int getPageDownIndex(){ 
		currentPage--;
		if(currentPage <= 0){
			currentPage = 0;
			return 0;
		}
		
		return currentPage * maxEntry;
	}
	
	public int totalPage(int qty){
		
		int pages = qty / maxEntry;
		if(qty % maxEntry > 0)
			pages++;
		
		return pages;
	}
	
	public int rear(int qty){
		return qty - ( totalPage(qty)-1) * maxEntry;
	}
}
