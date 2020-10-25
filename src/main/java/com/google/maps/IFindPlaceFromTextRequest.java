package com.google.maps;

import com.google.maps.FindPlaceFromTextRequest.FieldMask;
import com.google.maps.FindPlaceFromTextRequest.InputType;
import com.google.maps.FindPlaceFromTextRequest.LocationBias;

public interface IFindPlaceFromTextRequest {

	/**
	   * The text input specifying which place to search for (for example, a name, address, or phone
	   * number).
	   *
	   * @param input The text input.
	   * @return Returns {@code FindPlaceFromTextRequest} for call chaining.
	   */
	IFindPlaceFromTextRequest input(String input);

	/**
	   * The type of input.
	   *
	   * @param inputType The input type.
	   * @return Returns {@code FindPlaceFromTextRequest} for call chaining.
	   */
	IFindPlaceFromTextRequest inputType(InputType inputType);

	/**
	   * The fields specifying the types of place data to return.
	   *
	   * @param fields The fields to return.
	   * @return Returns {@code FindPlaceFromTextRequest} for call chaining.
	   */
	IFindPlaceFromTextRequest fields(FieldMask... fields);

	/**
	   * Prefer results in a specified area, by specifying either a radius plus lat/lng, or two lat/lng
	   * pairs representing the points of a rectangle.
	   *
	   * @param locationBias The location bias for this request.
	   * @return Returns {@code FindPlaceFromTextRequest} for call chaining.
	   */
	IFindPlaceFromTextRequest locationBias(LocationBias locationBias);

}