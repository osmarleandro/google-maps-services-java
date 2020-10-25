package com.google.maps;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.RankBy;

public interface ITextSearchRequest {

	/**
	   * Specifies the text string on which to search, for example: {@code "restaurant"}.
	   *
	   * @param query The query string to search for.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest query(String query);

	/**
	   * Specifies the latitude/longitude around which to retrieve place information.
	   *
	   * @param location The location of the center of the search.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest location(LatLng location);

	/**
	   * Region used to influence search results. This parameter will only influence, not fully
	   * restrict, search results. If more relevant results exist outside of the specified region, they
	   * may be included. When this parameter is used, the country name is omitted from the resulting
	   * formatted_address for results in the specified region.
	   *
	   * @param region The ccTLD two-letter code of the region.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest region(String region);

	/**
	   * Specifies the distance (in meters) within which to bias place results.
	   *
	   * @param radius The radius of the search bias.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest radius(int radius);

	/**
	   * Restricts to places that are at least this price level.
	   *
	   * @param priceLevel The minimum price level to restrict results with.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest minPrice(PriceLevel priceLevel);

	/**
	   * Restricts to places that are at most this price level.
	   *
	   * @param priceLevel The maximum price leve to restrict results with.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest maxPrice(PriceLevel priceLevel);

	/**
	   * Specifies one or more terms to be matched against the names of places, separated with space
	   * characters.
	   *
	   * @param name The name to search for.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest name(String name);

	/**
	   * Restricts to only those places that are open for business at the time the query is sent.
	   *
	   * @param openNow Whether to restrict this search to open places.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest openNow(boolean openNow);

	/**
	   * Returns the next 20 results from a previously run search. Setting pageToken will execute a
	   * search with the same parameters used previously — all parameters other than pageToken will be
	   * ignored.
	   *
	   * @param nextPageToken A {@code pageToken} from a prior result.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest pageToken(String nextPageToken);

	/**
	   * Specifies the order in which results are listed.
	   *
	   * @param ranking The rank by method.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest rankby(RankBy ranking);

	/**
	   * Restricts the results to places matching the specified type.
	   *
	   * @param type The type of place to restrict the results with.
	   * @return Returns this {@code TextSearchRequest} for call chaining.
	   */
	ITextSearchRequest type(PlaceType type);

}