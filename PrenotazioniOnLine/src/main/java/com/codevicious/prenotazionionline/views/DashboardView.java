package com.codevicious.prenotazionionline.views;

import java.util.List;

import com.codevicious.prenotazionionline.representations.Places;

import io.dropwizard.views.View;

public class DashboardView extends View {
	public final List<Places> places;

	public DashboardView(List<Places> places) {
		super("/views/dashboard.mustache");
		this.places = places;
	}

	public List<Places> getPlaces() {
		return places;
	}

}
