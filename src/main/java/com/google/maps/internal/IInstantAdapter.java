package com.google.maps.internal;

import java.io.IOException;
import java.time.Instant;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface IInstantAdapter {

	/** Read a time from the Places API and convert to a {@link Instant} */
	Instant read(JsonReader reader) throws IOException;

	/** This method is not implemented. */
	void write(JsonWriter out, Instant value) throws IOException;

}