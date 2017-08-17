package com.tower.entity;

import java.util.List;

public class Result {
	private List<StationInfo> rows;
	private int total;

	public List<StationInfo> getRows() {
		return rows;
	}

	public void setRows(List<StationInfo> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
