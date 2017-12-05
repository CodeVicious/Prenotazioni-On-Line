package com.codevicious.prenotazionionline;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.codevicious.prenotazionionline.factories.EmailServiceFactory;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class PrenotazioniOnLineConfiguration extends Configuration {
	
	@Valid 
	@NotNull
    private EmailServiceFactory smtpParams = new EmailServiceFactory();

	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

	
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

	@JsonProperty("smtpParams")
	public EmailServiceFactory getSmtpparams() {
		return smtpParams;
	}

	@JsonProperty("smtpparams")
	public void setSmtpparams(EmailServiceFactory smtpParams) {
		this.smtpParams = smtpParams;
	}


	
	

}
