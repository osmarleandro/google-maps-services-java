package com.google.maps.internal;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.PriceLevel;

public interface IPriceLevelAdapter {

	PriceLevel read(JsonReader reader) throws IOException;

	/** This method is not implemented. */
	void write(JsonWriter writer, PriceLevel value) throws IOException;

}