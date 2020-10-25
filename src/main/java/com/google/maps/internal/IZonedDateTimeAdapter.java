package com.google.maps.internal;

import java.io.IOException;
import java.time.ZonedDateTime;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface IZonedDateTimeAdapter {

	/**
	   * Read a Time object from a Directions API result and convert it to a {@link ZonedDateTime}.
	   *
	   * <p>We are expecting to receive something akin to the following:
	   *
	   * <pre>
	   * {
	   *   "text" : "4:27pm",
	   *   "time_zone" : "Australia/Sydney",
	   *   "value" : 1406528829
	   * }
	   * </pre>
	   */
	ZonedDateTime read(JsonReader reader) throws IOException;

	/** This method is not implemented. */
	void write(JsonWriter writer, ZonedDateTime value) throws IOException;

}