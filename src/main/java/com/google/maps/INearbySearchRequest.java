package com.google.maps;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.RankBy;

public interface INearbySearchRequest {

	/**
	   * Specifies the latitude/longitude around which to retrieve place information.
	   *
	   * @param location The location to use as the center of the Nearby Search.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest location(LatLng location);

	/**
	   * Specifies the distance (in meters) within which to return place results. The maximum allowed
	   * radius is 50,000 meters. Note that radius must not be included if {@code rankby=DISTANCE} is
	   * specified.
	   *
	   * @param distance The distance in meters around the {@link #location(LatLng)} to search.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest radius(int distance);

	/**
	   * Specifies the order in which results are listed.
	   *
	   * @param ranking The rank by method.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest rankby(RankBy ranking);

	/**
	   * Specifies a term to be matched against all content that Google has indexed for this place. This
	   * includes but is not limited to name, type, and address, as well as customer reviews and other
	   * third-party content.
	   *
	   * @param keyword The keyword to search for.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest keyword(String keyword);

	/**
	   * Restricts to places that are at least this price level.
	   *
	   * @param priceLevel The price level to set as minimum.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest minPrice(PriceLevel priceLevel);

	/**
	   * Restricts to places that are at most this price level.
	   *
	   * @param priceLevel The price level to set as maximum.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest maxPrice(PriceLevel priceLevel);

	/**
	   * Specifies one or more terms to be matched against the names of places, separated by spaces.
	   *
	   * @param name Search for Places with this name.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest name(String name);

	/**
	   * Restricts to only those places that are open for business at the time the query is sent.
	   *
	   * @param openNow Whether to restrict to places that are open.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest openNow(boolean openNow);

	/**
	   * Returns the next 20 results from a previously run search. Setting {@code pageToken} will
	   * execute a search with the same parameters used previously — all parameters other than {@code
	   * pageToken} will be ignored.
	   *
	   * @param nextPageToken The page token from a previous result.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest pageToken(String nextPageToken);

	/**
	   * Restricts the results to places matching the specified type.
	   *
	   * @param type The {@link PlaceType} to restrict results to.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest type(PlaceType type);

	/**
	   * Restricts the results to places matching the specified type. Provides support for multiple
	   * types.
	   *
	   * @deprecated Multiple search types are ignored by the Places API.
	   * @param types The {@link PlaceType}s to restrict results to.
	   * @return Returns this {@code NearbyApiRequest} for call chaining.
	   */
	INearbySearchRequest type(PlaceType... types);

}