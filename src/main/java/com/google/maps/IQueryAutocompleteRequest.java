package com.google.maps;

import com.google.maps.model.LatLng;

public interface IQueryAutocompleteRequest {

	/**
	   * The text string on which to search. The Places service will return candidate matches based on
	   * this string and order results based on their perceived relevance.
	   *
	   * @param input The input text to autocomplete.
	   * @return Returns this {@code QueryAutocompleteRequest} for call chaining.
	   */
	IQueryAutocompleteRequest input(String input);

	/**
	   * The character position in the input term at which the service uses text for predictions. For
	   * example, if the input is 'Googl' and the completion point is 3, the service will match on
	   * 'Goo'. The offset should generally be set to the position of the text caret. If no offset is
	   * supplied, the service will use the entire term.
	   *
	   * @param offset The character offset to search from.
	   * @return Returns this {@code QueryAutocompleteRequest} for call chaining.
	   */
	IQueryAutocompleteRequest offset(int offset);

	/**
	   * The point around which you wish to retrieve place information.
	   *
	   * @param location The location point around which to search.
	   * @return Returns this {@code QueryAutocompleteRequest} for call chaining.
	   */
	IQueryAutocompleteRequest location(LatLng location);

	/**
	   * The distance (in meters) within which to return place results. Note that setting a radius
	   * biases results to the indicated area, but may not fully restrict results to the specified area.
	   *
	   * @param radius The radius around which to bias results.
	   * @return Returns this {@code QueryAutocompleteRequest} for call chaining.
	   */
	IQueryAutocompleteRequest radius(int radius);

}