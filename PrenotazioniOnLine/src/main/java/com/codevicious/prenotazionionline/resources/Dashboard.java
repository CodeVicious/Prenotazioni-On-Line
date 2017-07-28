package com.codevicious.prenotazionionline.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.TEXT_HTML)
@Path("/dashboard/")
public class Dashboard {
	private Client client;
	public Dashboard(Client client) {
		this.client = client;
	}

}
