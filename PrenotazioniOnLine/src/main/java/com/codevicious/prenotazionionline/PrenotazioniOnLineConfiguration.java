package com.codevicious.prenotazionionline;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class PrenotazioniOnLineConfiguration extends Configuration {
	@JsonProperty
	@NotEmpty
	private String message;

	@JsonProperty
	@Max(10)
	private int messageRepetitions;

	@JsonProperty
	private String additionalMessage = "This is optional";

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	@NotEmpty
	private String template;

	@NotEmpty
	private String defaultName = "Stranger";

	@JsonProperty
	public String getTemplate() {
		return template;
	}

	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonProperty
	public String getDefaultName() {
		return defaultName;
	}

	@JsonProperty
	public void setDefaultName(String name) {
		this.defaultName = name;
	}

	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

	public String getMessage() {
		return message;
	}

	public int getMessageRepetitions() {
		return messageRepetitions;
	}

}
