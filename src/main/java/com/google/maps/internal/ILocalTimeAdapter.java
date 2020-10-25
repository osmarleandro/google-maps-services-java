package com.google.maps.internal;

import java.io.IOException;
import java.time.LocalTime;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface ILocalTimeAdapter {

	/** Read a time from the Places API and convert to a {@link LocalTime} */
	LocalTime read(JsonReader reader) throws IOException;

	/** This method is not implemented. */
	void write(JsonWriter out, LocalTime value) throws IOException;

}