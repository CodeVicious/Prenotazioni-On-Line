package com.codevicious.prenotazionionline;

import javax.ws.rs.client.Client;

import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codevicious.prenotazionionline.auth.IntranetAuthorizer;
import com.codevicious.prenotazionionline.auth.IntranetOnLineAuthenticator;
import com.codevicious.prenotazionionline.representations.User;
import com.codevicious.prenotazionionline.resources.AdminDashboard;
import com.codevicious.prenotazionionline.resources.AuthResource;
import com.codevicious.prenotazionionline.resources.AvailabilityResource;
import com.codevicious.prenotazionionline.resources.Dashboard;
import com.codevicious.prenotazionionline.resources.ReservationResource;
import com.codevicious.prenotazionionline.resources.UserProfileResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
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
		bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));

	}

	@Override
	public void run(PrenotazioniOnLineConfiguration configuration, Environment environment) throws Exception {

		/*
		 * init Error pages final ErrorPageErrorHandler epeh = new
		 * ErrorPageErrorHandler(); 400 - Bad Request, leave alone
		 * epeh.addErrorPage(401, "/error/401"); epeh.addErrorPage(402, "/error/402");
		 * epeh.addErrorPage(403, "/error/403"); epeh.addErrorPage(404, "/error/404");
		 * epeh.addErrorPage(405, 499, "/error/general-error"); epeh.addErrorPage(500,
		 * 599, "/error/general-error");
		 * 
		 * environment.getApplicationContext().setErrorHandler(epeh);
		 * environment.getAdminContext().setErrorHandler(epeh); ErrorResource
		 * errorResource = new ErrorResource();
		 * environment.jersey().register(errorResource);
		 */
		environment.getApplicationContext().setSessionHandler(new SessionHandler());

		environment.jersey().setUrlPattern("/api/*");

		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
		final Client client = new JerseyClientBuilder().build();

		environment.jersey().register(new Dashboard(client));
		environment.jersey().register(new AvailabilityResource(jdbi));
		environment.jersey().register(new AdminDashboard(client));
		environment.jersey().register(new ReservationResource(jdbi, configuration));
		environment.jersey().register(new AuthResource(jdbi));
		environment.jersey().register(new UserProfileResource(jdbi, configuration));

		environment.jersey().register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<User>()
				.setAuthenticator(new IntranetOnLineAuthenticator(jdbi)).setPrefix("Bearer").buildAuthFilter()));
		
		environment.jersey().register(RolesAllowedDynamicFeature.class);
		environment.jersey().register(new AuthValueFactoryProvider.Binder<User>(User.class));

	}
}
