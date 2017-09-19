package com.codevicious.prenotazionionline.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.Places;
import com.codevicious.prenotazionionline.views.AvailabilityView;
import com.codevicious.prenotazionionline.views.DashboardView;


@Produces(MediaType.TEXT_HTML)
@Path("/dashboard")
public class Dashboard {
	private final Client client;
	public Dashboard( Client client ) {
		this.client = client;
	}
	
	@GET	
	public DashboardView showDaschboard() {

	WebTarget availResource =	client.target("http://localhost:8080/disponibilita/places");
	Invocation.Builder invocationBuilder = availResource.request(MediaType.APPLICATION_JSON_TYPE);
	
	Response response = invocationBuilder.get();			
	
	@SuppressWarnings("unchecked")
	List<Places> places = response.readEntity(List.class);
	
	return new DashboardView(places);
	}
	
	@GET
	@Path("/showAvailabiliy")
	public AvailabilityView showAvailabiliy(@QueryParam("id") int id) {
	WebTarget availResource =	client.target("http://localhost:8080/disponibilita/"+id);
	Invocation.Builder invocationBuilder = availResource.request(MediaType.APPLICATION_JSON_TYPE);
	
	Response response = invocationBuilder.get();			
	Availability avail = response.readEntity(Availability.class);
	
	return new AvailabilityView(avail);
	}
	
}
