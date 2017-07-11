package com.codevicious.prenotazionionline;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.resources.AvailabilityResource;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Hello world!
 *
 */
public class PrenotazioniOnLineApplication extends Application<PrenotazioniOnLineConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrenotazioniOnLineApplication.class);

	public static void main(String[] args) throws Exception {
		new PrenotazioniOnLineApplication().run(args);
	}

	public String GetName() {
		return "Prenotazioni On Line";
	}

	public void initialize(Bootstrap<PrenotazioniOnLineConfiguration> bootstrap) {
		bootstrap.addBundle(new ViewBundle<PrenotazioniOnLineConfiguration>());

	}

	@Override
	public void run(PrenotazioniOnLineConfiguration configuration, Environment environment) throws Exception {

		LOGGER.info("Method App#run() called");

		for (int i = 0; i < configuration.getMessageRepetitions(); i++) {
			System.out.println(configuration.getMessage());
		}

		System.out.println(configuration.getAdditionalMessage());

		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory
		.build(environment, configuration.getDataSourceFactory(), "mysql");
		environment.healthChecks().register("template", healthCheck);		
		environment.jersey().register(new AvailabilityResource(jdbi));
		

	}
}
