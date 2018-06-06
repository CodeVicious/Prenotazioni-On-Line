package com.codevicious.prenotazionionline.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.codevicious.prenotazionionline.views.ErrorView;

@Path("/error")
public class ErrorResource {
	public ErrorResource() {
	}

	@Path("/401")
	@GET
	@Timed
	@Produces(MediaType.TEXT_HTML)
	public Response error401() {
		return Response.status(401).entity(new ErrorView(401)).build();
	}
	
		
	@Path("/404")
	@GET
	@Timed
	@Produces(MediaType.TEXT_HTML)
	public Response error404() {
		return Response.status(404).entity(new ErrorView(404)).build();
	}
	
	@Path("/405")
	@GET
	@Timed
	@Produces(MediaType.TEXT_HTML)
	public Response error405() {
		return Response.status(405).entity(new ErrorView(405)).build();
	}

}
