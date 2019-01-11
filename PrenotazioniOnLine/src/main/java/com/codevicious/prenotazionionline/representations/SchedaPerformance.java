package com.codevicious.prenotazionionline.representations;

public class SchedaPerformance {

	long id;
	String descrizione_obiettivo_1;
	String descrizione_obiettivo_2;
	String descrizione_obiettivo_3;
	String indicatore_desc_1;
	String indicatore_desc_2;
	String indicatore_desc_3;
	int peso_1;
	int peso_2;
	int peso_3;
	float raggiungimento_1;
	float raggiungimento_2;
	float raggiungimento_3;
	int comp_1;
	int comp_2;
	int comp_3;
	int comp_4;
	int relaz_1;
	int relaz_2;
	int relaz_3;
	int relaz_4;
	int del_saper_1;
	int del_saper_2;
	int del_saper_3;
	int del_saper_4;

	public SchedaPerformance(long id, String descrizione_obiettivo_1, String descrizione_obiettivo_2,
			String descrizione_obiettivo_3, String indicatore_desc_1, String indicatore_desc_2,
			String indicatore_desc_3, int peso_1, int peso_2, int peso_3, float raggiungimento_1,
			float raggiungimento_2, float raggiungimento_3, int comp_1, int comp_2, int comp_3, int comp_4, int relaz_1,
			int relaz_2, int relaz_3, int relaz_4, int del_saper_1, int del_saper_2, int del_saper_3, int del_saper_4) {
		this.id = id;
		this.descrizione_obiettivo_1 = descrizione_obiettivo_1;
		this.descrizione_obiettivo_2 = descrizione_obiettivo_2;
		this.descrizione_obiettivo_3 = descrizione_obiettivo_3;
		this.indicatore_desc_1 = indicatore_desc_1;
		this.indicatore_desc_2 = indicatore_desc_2;
		this.indicatore_desc_3 = indicatore_desc_3;
		this.peso_1 = peso_1;
		this.peso_2 = peso_2;
		this.peso_3 = peso_3;
		this.raggiungimento_1 = raggiungimento_1;
		this.raggiungimento_2 = raggiungimento_2;
		this.raggiungimento_3 = raggiungimento_3;
		this.comp_1 = comp_1;
		this.comp_2 = comp_2;
		this.comp_3 = comp_3;
		this.comp_4 = comp_4;
		this.relaz_1 = relaz_1;
		this.relaz_2 = relaz_2;
		this.relaz_3 = relaz_3;
		this.relaz_4 = relaz_4;
		this.del_saper_1 = del_saper_1;
		this.del_saper_2 = del_saper_2;
		this.del_saper_3 = del_saper_3;
		this.del_saper_4 = del_saper_4;
	}

	public long getId() {
		return id;
	}

	public String getDescrizione_obiettivo_1() {
		return descrizione_obiettivo_1;
	}

	public String getDescrizione_obiettivo_2() {
		return descrizione_obiettivo_2;
	}

	public String getDescrizione_obiettivo_3() {
		return descrizione_obiettivo_3;
	}

	public String getIndicatore_desc_1() {
		return indicatore_desc_1;
	}

	public String getIndicatore_desc_2() {
		return indicatore_desc_2;
	}

	public String getIndicatore_desc_3() {
		return indicatore_desc_3;
	}

	public int getPeso_1() {
		return peso_1;
	}

	public int getPeso_2() {
		return peso_2;
	}

	public int getPeso_3() {
		return peso_3;
	}

	public float getRaggiungimento_1() {
		return raggiungimento_1;
	}

	public float getRaggiungimento_2() {
		return raggiungimento_2;
	}

	public float getRaggiungimento_3() {
		return raggiungimento_3;
	}

	public int getComp_1() {
		return comp_1;
	}

	public int getComp_2() {
		return comp_2;
	}

	public int getComp_3() {
		return comp_3;
	}

	public int getComp_4() {
		return comp_4;
	}

	public int getRelaz_1() {
		return relaz_1;
	}

	public int getRelaz_2() {
		return relaz_2;
	}

	public int getRelaz_3() {
		return relaz_3;
	}

	public int getRelaz_4() {
		return relaz_4;
	}

	public int getDel_saper_1() {
		return del_saper_1;
	}

	public int getDel_saper_2() {
		return del_saper_2;
	}

	public int getDel_saper_3() {
		return del_saper_3;
	}

	public int getDel_saper_4() {
		return del_saper_4;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescrizione_obiettivo_1(String descrizione_obiettivo_1) {
		this.descrizione_obiettivo_1 = descrizione_obiettivo_1;
	}

	public void setDescrizione_obiettivo_2(String descrizione_obiettivo_2) {
		this.descrizione_obiettivo_2 = descrizione_obiettivo_2;
	}

	public void setDescrizione_obiettivo_3(String descrizione_obiettivo_3) {
		this.descrizione_obiettivo_3 = descrizione_obiettivo_3;
	}

	public void setIndicatore_desc_1(String indicatore_desc_1) {
		this.indicatore_desc_1 = indicatore_desc_1;
	}

	public void setIndicatore_desc_2(String indicatore_desc_2) {
		this.indicatore_desc_2 = indicatore_desc_2;
	}

	public void setIndicatore_desc_3(String indicatore_desc_3) {
		this.indicatore_desc_3 = indicatore_desc_3;
	}

	public void setPeso_1(int peso_1) {
		this.peso_1 = peso_1;
	}

	public void setPeso_2(int peso_2) {
		this.peso_2 = peso_2;
	}

	public void setPeso_3(int peso_3) {
		this.peso_3 = peso_3;
	}

	public void setRaggiungimento_1(float raggiungimento_1) {
		this.raggiungimento_1 = raggiungimento_1;
	}

	public void setRaggiungimento_2(float raggiungimento_2) {
		this.raggiungimento_2 = raggiungimento_2;
	}

	public void setRaggiungimento_3(float raggiungimento_3) {
		this.raggiungimento_3 = raggiungimento_3;
	}

	public void setComp_1(int comp_1) {
		this.comp_1 = comp_1;
	}

	public void setComp_2(int comp_2) {
		this.comp_2 = comp_2;
	}

	public void setComp_3(int comp_3) {
		this.comp_3 = comp_3;
	}

	public void setComp_4(int comp_4) {
		this.comp_4 = comp_4;
	}

	public void setRelaz_1(int relaz_1) {
		this.relaz_1 = relaz_1;
	}

	public void setRelaz_2(int relaz_2) {
		this.relaz_2 = relaz_2;
	}

	public void setRelaz_3(int relaz_3) {
		this.relaz_3 = relaz_3;
	}

	public void setRelaz_4(int relaz_4) {
		this.relaz_4 = relaz_4;
	}

	public void setDel_saper_1(int del_saper_1) {
		this.del_saper_1 = del_saper_1;
	}

	public void setDel_saper_2(int del_saper_2) {
		this.del_saper_2 = del_saper_2;
	}

	public void setDel_saper_3(int del_saper_3) {
		this.del_saper_3 = del_saper_3;
	}

	public void setDel_saper_4(int del_saper_4) {
		this.del_saper_4 = del_saper_4;
	}

}
