package com.codevicious.prenotazionionline.views;

import com.codevicious.prenotazionionline.representations.Availability;

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
