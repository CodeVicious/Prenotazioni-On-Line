package com.codevicious.prenotazionionline.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import com.codevicious.prenotazionionline.views.AdminView;

@Produces(MediaType.TEXT_HTML)
@Path("/admin")
public class AdminDashboard {
	private final Client client;

	public AdminDashboard(Client client) {
		this.client = client;
	}

	@GET
	public AdminView adminReservations() {

		return new AdminView();

	}

}
