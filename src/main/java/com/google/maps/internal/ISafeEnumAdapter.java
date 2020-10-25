package com.google.maps.internal;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface ISafeEnumAdapter<E extends Enum<E>> {

	void write(JsonWriter out, E value) throws IOException;

	E read(JsonReader reader) throws IOException;

}