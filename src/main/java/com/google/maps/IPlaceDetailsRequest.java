package com.google.maps;

import com.google.maps.PlaceDetailsRequest.FieldMask;

public interface IPlaceDetailsRequest {

	/**
	   * Specifies the Place ID to get Place Details for. Required.
	   *
	   * @param placeId The Place ID to retrieve details for.
	   * @return Returns this {@code PlaceDetailsRequest} for call chaining.
	   */
	IPlaceDetailsRequest placeId(String placeId);

	/**
	   * Sets the SessionToken for this request. Use this for Place Details requests that are called
	   * following an autocomplete request in the same user session. Optional.
	   *
	   * @param sessionToken Session Token is the session identifier.
	   * @return Returns this {@code PlaceDetailsRequest} for call chaining.
	   */
	IPlaceDetailsRequest sessionToken(PlaceAutocompleteRequest.SessionToken sessionToken);

	/**
	   * Sets the Region for this request. The region code, specified as a ccTLD (country code top-level
	   * domain) two-character value. Most ccTLD codes are identical to ISO 3166-1 codes, with some
	   * exceptions. This parameter will only influence, not fully restrict, results.
	   *
	   * @param region The region code.
	   * @return Returns this {@code PlaceDetailsRequest} for call chaining.
	   */
	IPlaceDetailsRequest region(String region);

	/**
	   * Specifies the field masks of the details to be returned by PlaceDetails.
	   *
	   * @param fields The Field Masks of the fields to return.
	   * @return Returns this {@code PlaceDetailsRequest} for call chaining.
	   */
	IPlaceDetailsRequest fields(FieldMask... fields);

}