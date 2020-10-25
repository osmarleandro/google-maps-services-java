package com.google.maps.internal;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.LatLng;

public interface ILatLngAdapter {

	/**
	   * Reads in a JSON object and try to create a LatLng in one of the following formats.
	   *
	   * <pre>{
	   *   "lat" : -33.8353684,
	   *   "lng" : 140.8527069
	   * }
	   *
	   * {
	   *   "latitude": -33.865257570508334,
	   *   "longitude": 151.19287000481452
	   * }</pre>
	   */
	LatLng read(JsonReader reader) throws IOException;

	/** Not supported. */
	void write(JsonWriter out, LatLng value) throws IOException;

}