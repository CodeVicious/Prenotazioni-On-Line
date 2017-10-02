package com.codevicious.prenotazionionline.representations;

import java.util.List;

public class DataTableResult {

	private long draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<Reservation> data;

	public DataTableResult(long draw, List<Reservation> data, long recordsTotal, long recordsFiltered) {
		this.draw = draw;
		this.data = data;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;

	}

	public DataTableResult() {
		this.recordsFiltered = 0;
		this.recordsTotal = 0;
		this.data = null;
	}

	public long getDraw() {
		return draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public List<Reservation> getData() {
		return data;
	}

	public void setDraw(long draw) {
		this.draw = draw;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public void setData(List<Reservation> data) {
		this.data = data;
	}
	
}
