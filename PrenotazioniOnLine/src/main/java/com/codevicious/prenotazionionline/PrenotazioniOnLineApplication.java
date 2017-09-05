package com.codevicious.prenotazionionline;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.dao.AvailabilityDAO;

import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.Place;
import com.codevicious.prenotazionionline.resources.AvailabilityResource;
import com.codevicious.prenotazionionline.resources.Dashboard;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Hello world!
 *
 */
public class PrenotazioniOnLineApplication extends Application<PrenotazioniOnLineConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrenotazioniOnLineApplication.class);
	
	
	private final HibernateBundle<PrenotazioniOnLineConfiguration> hibernateBundle = new HibernateBundle<PrenotazioniOnLineConfiguration>(
			Availability.class, Place.class) {

		public PooledDataSourceFactory getDataSourceFactory(PrenotazioniOnLineConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	
	public static void main(String[] args) throws Exception {
		new PrenotazioniOnLineApplication().run(args);
	}

	public String GetName() {
		return "Prenotazioni On Line";
	}

	public void initialize(Bootstrap<PrenotazioniOnLineConfiguration> bootstrap) {
		bootstrap.addBundle(new ViewBundle<PrenotazioniOnLineConfiguration>());
		bootstrap.addBundle(new AssetsBundle());
		
		bootstrap.addBundle(hibernateBundle);
	}

	@Override
	public void run(PrenotazioniOnLineConfiguration configuration, Environment environment) throws Exception {

		LOGGER.info("Method App#run() called");

		final AvailabilityDAO dao = new AvailabilityDAO(hibernateBundle.getSessionFactory());

		final Client client = new JerseyClientBuilder().build();

		environment.jersey().register(new Dashboard(client));
		environment.jersey().register(new AvailabilityResource(dao));

	}
}
