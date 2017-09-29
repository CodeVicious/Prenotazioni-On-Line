package com.codevicious.prenotazionionline;

import javax.ws.rs.client.Client;

import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.auth.PrenotazioniOnLineAuthenticator;
import com.codevicious.prenotazionionline.auth.User;
import com.codevicious.prenotazionionline.resources.AvailabilityResource;
import com.codevicious.prenotazionionline.resources.Dashboard;
import com.codevicious.prenotazionionline.resources.ErrorResource;
import com.codevicious.prenotazionionline.resources.ReservationResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
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

		// init Error pages
		final ErrorPageErrorHandler epeh = new ErrorPageErrorHandler();
		// 400 - Bad Request, leave alone
		epeh.addErrorPage(401, "/error/general-error");
		epeh.addErrorPage(402, "/error/general-error");
		epeh.addErrorPage(403, "/error/403");
		epeh.addErrorPage(404, "/error/404");
		epeh.addErrorPage(405, 499, "/error/general-error");
		epeh.addErrorPage(500, 599, "/error/general-error");
		environment.getApplicationContext().setErrorHandler(epeh);
		environment.getAdminContext().setErrorHandler(epeh);

		ErrorResource errorResource = new ErrorResource();
		environment.jersey().register(errorResource);

		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
		final Client client = new JerseyClientBuilder().build();

		environment.jersey().register(new Dashboard(client));
		environment.jersey().register(new AvailabilityResource(jdbi));
		environment.jersey().register(new ReservationResource(jdbi, configuration));
		environment.jersey()
				.register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
						.setAuthenticator(new PrenotazioniOnLineAuthenticator(jdbi)).buildAuthFilter()));
		environment.jersey().register(new AuthValueFactoryProvider.Binder<User>(User.class));

	}
}
