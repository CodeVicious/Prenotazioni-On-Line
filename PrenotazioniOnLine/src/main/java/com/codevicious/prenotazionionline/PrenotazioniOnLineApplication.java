package com.codevicious.prenotazionionline;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.resources.AvailabilityResource;
import com.codevicious.prenotazionionline.resources.Dashboard;
import com.codevicious.prenotazionionline.resources.ReservationResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
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
		bootstrap.addBundle(new MultiPartBundle());
		bootstrap.addBundle(new AssetsBundle());

	}

	@Override
	public void run(PrenotazioniOnLineConfiguration configuration, Environment environment) throws Exception {

		LOGGER.info("Method App#run() called");
		LOGGER.info(configuration.getSMTPHost().get());
		LOGGER.info(configuration.getSMTPPort().get());
		LOGGER.info(configuration.getSMTPUname().get());
		LOGGER.info(configuration.getSMTPPw().get());

		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory
		.build(environment, configuration.getDataSourceFactory(), "mysql");
		final Client client = new 
				JerseyClientBuilder().build();
		
		environment.jersey().register(new Dashboard(client));		
		environment.jersey().register(new AvailabilityResource(jdbi));
		environment.jersey().register(new ReservationResource(jdbi));
		
		
	}
}
