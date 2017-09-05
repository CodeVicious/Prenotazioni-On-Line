package com.codevicious.prenotazionionline;





import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class PrenotazioniOnLineConfiguration extends Configuration {

    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

	public DataSourceFactory getDataSourceFactory() {
		
		return database;
	}


}
