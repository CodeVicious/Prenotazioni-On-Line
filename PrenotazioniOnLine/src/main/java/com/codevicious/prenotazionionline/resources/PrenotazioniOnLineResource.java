package com.codevicious.prenotazionionline.resources;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.codevicious.prenotazionionline.Saying;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)

public class PrenotazioniOnLineResource {

	private String template;
	private String defaultName;
	private AtomicLong counter;

	public PrenotazioniOnLineResource(String template, String defaultName) {
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public Saying sayHallo(@QueryParam("name") Optional<String> name) {
		final String value = String.format(template, name.orElse(defaultName));
		return new Saying(counter.incrementAndGet(), value);
	}

}
