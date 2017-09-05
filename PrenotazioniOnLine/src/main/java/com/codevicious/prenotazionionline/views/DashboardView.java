package com.codevicious.prenotazionionline.views;

import java.util.List;

import com.codevicious.prenotazionionline.representations.Place;

import io.dropwizard.views.View;

public class DashboardView extends View {
	public final List<Place> places;

	public DashboardView(List<Place> places) {
		super("/views/dashboard.mustache");
		this.places = places;
	}

	public List<Place> getPlaces() {
		return places;
	}

}
