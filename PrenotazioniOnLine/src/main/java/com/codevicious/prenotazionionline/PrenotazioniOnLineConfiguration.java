package com.codevicious.prenotazionionline;


import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class PrenotazioniOnLineConfiguration extends Configuration {
	
	
    private Map<String, String> smtpparams;

	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

	@JsonProperty("smtpparams")
	public Map<String, String> getSmtpparams() {
		return smtpparams;
	}

	@JsonProperty("smtpparams")
	public void setSmtpparams(Map<String, String> smtpparams) {
		this.smtpparams = smtpparams;
	}
	
	@JsonIgnore
	public Optional<String> getSMTPHost(){
		return Optional.ofNullable(smtpparams.get("host"));
	}

	@JsonIgnore
	public Optional<String> getSMTPPort(){
		return Optional.ofNullable(smtpparams.get("port"));
	}
	
	@JsonIgnore
	public Optional<String> getSMTPUname(){
		return Optional.ofNullable(smtpparams.get("username"));
	}
	
	@JsonIgnore
	public Optional<String> getSMTPPw(){
		return Optional.ofNullable(smtpparams.get("pw"));
	}
	

}
