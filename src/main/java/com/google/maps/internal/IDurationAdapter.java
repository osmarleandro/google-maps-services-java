package com.google.maps.internal;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;

public interface IDurationAdapter {

	/**
	   * Read a distance object from a Directions API result and convert it to a {@link Distance}.
	   *
	   * <p>We are expecting to receive something akin to the following:
	   *
	   * <pre>
	   * {
	   *   "value": 207,
	   *   "text": "0.1 mi"
	   * }
	   * </pre>
	   */
	Duration read(JsonReader reader) throws IOException;

	/** This method is not implemented. */
	void write(JsonWriter writer, Duration value) throws IOException;

}