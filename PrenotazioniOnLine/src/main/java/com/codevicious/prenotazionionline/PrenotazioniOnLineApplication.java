package com.codevicious.prenotazionionline;

import com.codevicious.prenotazionionline.resources.AvailabilityResource;
import com.codevicious.prenotazionionline.resources.PrenotazioniOnLineResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class PrenotazioniOnLineApplication extends Application<PrenotazioniOnLineConfiguration> {
	public static void main(String[] args) throws Exception {
		new PrenotazioniOnLineApplication().run(args);
	}

	public String GetName() {
		return "Prenotazioni On Line";
	}

	public void initialize(Bootstrap<PrenotazioniOnLineConfiguration> bootstrap) {

	}

	@Override
	public void run(PrenotazioniOnLineConfiguration configuration, Environment environment) throws Exception {

		final PrenotazioniOnLineResource resource = new PrenotazioniOnLineResource(configuration.getTemplate(),
				configuration.getDefaultName()); 
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
		environment.jersey().register(new AvailabilityResource());

	}
}
