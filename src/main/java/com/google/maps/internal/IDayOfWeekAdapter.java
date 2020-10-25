package com.google.maps.internal;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.OpeningHours.Period.OpenClose.DayOfWeek;

public interface IDayOfWeekAdapter {

	DayOfWeek read(JsonReader reader) throws IOException;

	/** This method is not implemented. */
	void write(JsonWriter writer, DayOfWeek value) throws IOException;

}