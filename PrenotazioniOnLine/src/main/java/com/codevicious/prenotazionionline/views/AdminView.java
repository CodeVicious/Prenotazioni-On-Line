package com.codevicious.prenotazionionline.views;

import java.util.List;

import com.codevicious.prenotazionionline.representations.Places;

import io.dropwizard.views.View;

public class AdminView extends View {
	public final List<Places> places;

	public AdminView(List<Places> places) {
		super("/views/admin.mustache");
		this.places = places;
	}

	public List<Places> getPlaces() {
		return places;
	}

}
