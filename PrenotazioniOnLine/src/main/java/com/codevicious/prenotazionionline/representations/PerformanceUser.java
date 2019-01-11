package com.codevicious.prenotazionionline.representations;



import org.joda.time.DateTime;


public class PerformanceUser { 

	long id;
	int Anno;
	DateTime inizio_incarico;
	DateTime fine_incarico;
	int giorni_lavorati;
	boolean CP;
	String Responsabilita_speciali;
	String nome;
	String cognome;
	String DO;
	String Note_Informative_1;
	String Note_Informative_2;
	double percentuale_comando_effettivo;
	double percentuale_do;
	double presenza_giuridica;
	String capitolo_standard;
	String capitolo_oneri_standard;
	String capitolo_irap_standard;
	long fk_sectors;
	long fk_profilo_professionale;
	long fk_categoria_giuridica;
	long fk_user;
	
	public PerformanceUser(long id, int anno, DateTime inizio_incarico, DateTime fine_incarico, int giorni_lavorati,
			boolean cP, String responsabilita_speciali, String nome, String cognome, String dO,
			String note_Informative_1, String note_Informative_2, double percentuale_comando_effettivo,
			double percentuale_do, double presenza_giuridica, String capitolo_standard, String capitolo_oneri_standard,
			String capitolo_irap_standard, long fk_sectors, long fk_profilo_professionale, long fk_categoria_giuridica,
			long fk_user) {
		
		this.id = id;
		Anno = anno;
		this.inizio_incarico = inizio_incarico;
		this.fine_incarico = fine_incarico;
		this.giorni_lavorati = giorni_lavorati;
		CP = cP;
		Responsabilita_speciali = responsabilita_speciali;
		this.nome = nome;
		this.cognome = cognome;
		DO = dO;
		Note_Informative_1 = note_Informative_1;
		Note_Informative_2 = note_Informative_2;
		this.percentuale_comando_effettivo = percentuale_comando_effettivo;
		this.percentuale_do = percentuale_do;
		this.presenza_giuridica = presenza_giuridica;
		this.capitolo_standard = capitolo_standard;
		this.capitolo_oneri_standard = capitolo_oneri_standard;
		this.capitolo_irap_standard = capitolo_irap_standard;
		this.fk_sectors = fk_sectors;
		this.fk_profilo_professionale = fk_profilo_professionale;
		this.fk_categoria_giuridica = fk_categoria_giuridica;
		this.fk_user = fk_user;
	}

	public long getId() {
		return id;
	}

	public int getAnno() {
		return Anno;
	}

	public DateTime getInizio_incarico() {
		return inizio_incarico;
	}

	public DateTime getFine_incarico() {
		return fine_incarico;
	}

	public int getGiorni_lavorati() {
		return giorni_lavorati;
	}

	public boolean isCP() {
		return CP;
	}

	public String getResponsabilita_speciali() {
		return Responsabilita_speciali;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getDO() {
		return DO;
	}

	public String getNote_Informative_1() {
		return Note_Informative_1;
	}

	public String getNote_Informative_2() {
		return Note_Informative_2;
	}

	public double getPercentuale_comando_effettivo() {
		return percentuale_comando_effettivo;
	}

	public double getPercentuale_do() {
		return percentuale_do;
	}

	public double getPresenza_giuridica() {
		return presenza_giuridica;
	}

	public String getCapitolo_standard() {
		return capitolo_standard;
	}

	public String getCapitolo_oneri_standard() {
		return capitolo_oneri_standard;
	}

	public String getCapitolo_irap_standard() {
		return capitolo_irap_standard;
	}

	public long getFk_sectors() {
		return fk_sectors;
	}

	public long getFk_profilo_professionale() {
		return fk_profilo_professionale;
	}

	public long getFk_categoria_giuridica() {
		return fk_categoria_giuridica;
	}

	public long getFk_user() {
		return fk_user;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAnno(int anno) {
		Anno = anno;
	}

	public void setInizio_incarico(DateTime inizio_incarico) {
		this.inizio_incarico = inizio_incarico;
	}

	public void setFine_incarico(DateTime fine_incarico) {
		this.fine_incarico = fine_incarico;
	}

	public void setGiorni_lavorati(int giorni_lavorati) {
		this.giorni_lavorati = giorni_lavorati;
	}

	public void setCP(boolean cP) {
		CP = cP;
	}

	public void setResponsabilita_speciali(String responsabilita_speciali) {
		Responsabilita_speciali = responsabilita_speciali;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setDO(String dO) {
		DO = dO;
	}

	public void setNote_Informative_1(String note_Informative_1) {
		Note_Informative_1 = note_Informative_1;
	}

	public void setNote_Informative_2(String note_Informative_2) {
		Note_Informative_2 = note_Informative_2;
	}

	public void setPercentuale_comando_effettivo(double percentuale_comando_effettivo) {
		this.percentuale_comando_effettivo = percentuale_comando_effettivo;
	}

	public void setPercentuale_do(double percentuale_do) {
		this.percentuale_do = percentuale_do;
	}

	public void setPresenza_giuridica(double presenza_giuridica) {
		this.presenza_giuridica = presenza_giuridica;
	}

	public void setCapitolo_standard(String capitolo_standard) {
		this.capitolo_standard = capitolo_standard;
	}

	public void setCapitolo_oneri_standard(String capitolo_oneri_standard) {
		this.capitolo_oneri_standard = capitolo_oneri_standard;
	}

	public void setCapitolo_irap_standard(String capitolo_irap_standard) {
		this.capitolo_irap_standard = capitolo_irap_standard;
	}

	public void setFk_sectors(long fk_sectors) {
		this.fk_sectors = fk_sectors;
	}

	public void setFk_profilo_professionale(long fk_profilo_professionale) {
		this.fk_profilo_professionale = fk_profilo_professionale;
	}

	public void setFk_categoria_giuridica(long fk_categoria_giuridica) {
		this.fk_categoria_giuridica = fk_categoria_giuridica;
	}

	public void setFk_user(long fk_user) {
		this.fk_user = fk_user;
	}
	
	
	
}