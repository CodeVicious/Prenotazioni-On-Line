package com.codevicious.prenotazionionline.representations;

import java.util.List;

public class DataTableResult {

	private long iTotalRecords;
	private long iTotalDisplayRecords;
	private List<Reservation> aaData;

	public DataTableResult(long iTotalRecords, long iTotalDisplayRecords, List<Reservation> aaData) {
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
	}
	
	public DataTableResult() {
		this.iTotalDisplayRecords = 0;
		this.iTotalRecords = 0;
		this.aaData = null;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public List<Reservation> getAaData() {
		return aaData;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public void setAaData(List<Reservation> aaData) {
		this.aaData = aaData;
	}

	
}
