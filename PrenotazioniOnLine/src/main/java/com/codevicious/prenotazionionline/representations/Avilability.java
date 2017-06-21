package com.codevicious.prenotazionionline.representations;

import java.sql.Date;
import java.sql.Time;

public class Avilability {
	private final int id;
	private final int PK;
	private final Date data;
	private final Time ora;
	private final int FK_places;

	public Avilability() {
		this.id = 0;
		this.PK = 0;
		this.data = null;
		this.ora = null;
		this.FK_places = 0;
	}

	public Avilability(int id, int PK, Date data, Time ora, int FK_places) {
		this.id = id;
		this.PK = PK;
		this.data = data;
		this.ora = ora;
		this.FK_places = FK_places;
	}

	public int getId() {
		return id;
	}

	public int getPK() {
		return PK;
	}

	public Date getData() {
		return data;
	}

	public Time getOra() {
		return ora;
	}

	public int getFK_places() {
		return FK_places;
	}

}
