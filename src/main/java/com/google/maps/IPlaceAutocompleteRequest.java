package com.google.maps;

import com.google.maps.PlaceAutocompleteRequest.SessionToken;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceAutocompleteType;

public interface IPlaceAutocompleteRequest {

	/**
	   * Sets the SessionToken for this request. Using session token makes sure the autocomplete is
	   * priced per session, instead of per keystroke.
	   *
	   * @param sessionToken Session Token is the session identifier.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest sessionToken(SessionToken sessionToken);

	/**
	   * Sets the text string on which to search. The Places service will return candidate matches based
	   * on this string and order results based on their perceived relevance.
	   *
	   * @param input The input text to autocomplete.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest input(String input);

	/**
	   * The character position in the input term at which the service uses text for predictions. For
	   * example, if the input is 'Googl' and the completion point is 3, the service will match on
	   * 'Goo'. The offset should generally be set to the position of the text caret. If no offset is
	   * supplied, the service will use the entire term.
	   *
	   * @param offset The character offset position of the user's cursor.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest offset(int offset);

	/**
	   * The origin point from which to calculate straight-line distance to the destination (returned as
	   * {@link AutocompletePrediction#distanceMeters}). If this value is omitted, straight-line
	   * distance will not be returned.
	   *
	   * @param origin The {@link LatLng} origin point from which to calculate distance.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest origin(LatLng origin);

	/**
	   * The point around which you wish to retrieve place information.
	   *
	   * @param location The {@link LatLng} location to center this autocomplete search.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest location(LatLng location);

	/**
	   * The distance (in meters) within which to return place results. Note that setting a radius
	   * biases results to the indicated area, but may not fully restrict results to the specified area.
	   *
	   * @param radius The radius over which to bias results.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest radius(int radius);

	/**
	   * Restricts the results to places matching the specified type.
	   *
	   * @param type The type to restrict results to.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   * @deprecated Please use {@code types} instead.
	   */
	IPlaceAutocompleteRequest type(PlaceAutocompleteType type);

	/**
	   * Restricts the results to places matching the specified type.
	   *
	   * @param types The type to restrict results to.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest types(PlaceAutocompleteType types);

	/**
	   * A grouping of places to which you would like to restrict your results. Currently, you can use
	   * components to filter by country.
	   *
	   * @param filters The component filter to restrict results with.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest components(ComponentFilter... filters);

	/**
	   * StrictBounds returns only those places that are strictly within the region defined by location
	   * and radius. This is a restriction, rather than a bias, meaning that results outside this region
	   * will not be returned even if they match the user input.
	   *
	   * @param strictBounds Whether to strictly bound results.
	   * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
	   */
	IPlaceAutocompleteRequest strictBounds(boolean strictBounds);

}