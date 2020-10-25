package com.google.maps.internal;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.Fare;

public interface IFareAdapter {

	/**
	   * Read a Fare object from the Directions API and convert to a {@link com.google.maps.model.Fare}
	   *
	   * <pre>{
	   *   "currency": "USD",
	   *   "value": 6
	   * }</pre>
	   */
	Fare read(JsonReader reader) throws IOException;

	/** This method is not implemented. */
	void write(JsonWriter out, Fare value) throws IOException;

}