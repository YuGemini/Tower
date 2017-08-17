package com.tower.bean;

public class SearchParam {
	private int pageSize;
	private int curPage;
	private String id;
	private String ttmc;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTtmc() {
		return ttmc;
	}

	public void setTtmc(String ttmc) {
		this.ttmc = ttmc;
	}

}
