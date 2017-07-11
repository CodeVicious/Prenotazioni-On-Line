package com.codevicious.prenotazionionline.views;

import org.eclipse.jetty.server.handler.ContextHandler.Availability;

import io.dropwizard.views.View;

public class AvailabilityView extends View {
	public final Availability availability;

	public AvailabilityView(Availability availability) {
		super("/views/availability.mustache");
		this.availability = availability;
	}

	public Availability getAvailability() {
		return availability;
	}

}
