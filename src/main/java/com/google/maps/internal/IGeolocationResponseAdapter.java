package com.google.maps.internal;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.maps.GeolocationApi;

public interface IGeolocationResponseAdapter {

	/**
	   * Reads in a JSON object to create a Geolocation Response. See:
	   * https://developers.google.com/maps/documentation/geolocation/intro#responses
	   *
	   * <p>Success Case:
	   *
	   * <pre>
	   *   {
	   *     "location": {
	   *       "lat": 51.0,
	   *       "lng": -0.1
	   *     },
	   *     "accuracy": 1200.4
	   *   }
	   * </pre>
	   *
	   * Error Case: The response contains an object with a single error object with the following keys:
	   *
	   * <p>code: This is the same as the HTTP status of the response. {@code message}: A short
	   * description of the error. {@code errors}: A list of errors which occurred. Each error contains
	   * an identifier for the type of error (the reason) and a short description (the message). For
	   * example, sending invalid JSON will return the following error:
	   *
	   * <pre>
	   *   {
	   *     "error": {
	   *       "errors": [ {
	   *           "domain": "geolocation",
	   *           "reason": "notFound",
	   *           "message": "Not Found",
	   *           "debugInfo": "status: ZERO_RESULTS\ncom.google.api.server.core.Fault: Immu...
	   *       }],
	   *       "code": 404,
	   *       "message": "Not Found"
	   *     }
	   *   }
	   * </pre>
	   */
	GeolocationApi.Response read(JsonReader reader) throws IOException;

	/** Not supported. */
	void write(JsonWriter out, GeolocationApi.Response value) throws IOException;

}