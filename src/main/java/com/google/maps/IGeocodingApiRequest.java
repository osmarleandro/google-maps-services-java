package com.google.maps;

import com.google.maps.model.AddressType;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;

public interface IGeocodingApiRequest {

	/**
	   * Creates a forward geocode for {@code address}.
	   *
	   * @param address The address to geocode.
	   * @return Returns this {@code GeocodingApiRequest} for call chaining.
	   */
	IGeocodingApiRequest address(String address);

	/**
	   * Creates a forward geocode for {@code placeId}.
	   *
	   * @param placeId The Place ID to geocode.
	   * @return Returns this {@code GeocodingApiRequest} for call chaining.
	   */
	IGeocodingApiRequest place(String placeId);

	/**
	   * Creates a reverse geocode for {@code latlng}.
	   *
	   * @param latlng The location to reverse geocode.
	   * @return Returns this {@code GeocodingApiRequest} for call chaining.
	   */
	IGeocodingApiRequest latlng(LatLng latlng);

	/**
	   * Sets the bounding box of the viewport within which to bias geocode results more prominently.
	   * This parameter will only influence, not fully restrict, results from the geocoder.
	   *
	   * <p>For more information see <a
	   * href="https://developers.google.com/maps/documentation/geocoding/intro?hl=pl#Viewports">
	   * Viewport Biasing</a>.
	   *
	   * @param southWestBound The South West bound of the bounding box.
	   * @param northEastBound The North East bound of the bounding box.
	   * @return Returns this {@code GeocodingApiRequest} for call chaining.
	   */
	IGeocodingApiRequest bounds(LatLng southWestBound, LatLng northEastBound);

	/**
	   * Sets the region code, specified as a ccTLD ("top-level domain") two-character value. This
	   * parameter will only influence, not fully restrict, results from the geocoder.
	   *
	   * <p>For more information see <a
	   * href="https://developers.google.com/maps/documentation/geocoding/intro?hl=pl#RegionCodes">Region
	   * Biasing</a>.
	   *
	   * @param region The region code to influence results.
	   * @return Returns this {@code GeocodingApiRequest} for call chaining.
	   */
	IGeocodingApiRequest region(String region);

	/**
	   * Sets the component filters. Each component filter consists of a component:value pair and will
	   * fully restrict the results from the geocoder.
	   *
	   * <p>For more information see <a
	   * href="https://developers.google.com/maps/documentation/geocoding/intro?hl=pl#ComponentFiltering">
	   * Component Filtering</a>.
	   *
	   * @param filters Component filters to apply to the request.
	   * @return Returns this {@code GeocodingApiRequest} for call chaining.
	   */
	IGeocodingApiRequest components(ComponentFilter... filters);

	/**
	   * Sets the result type. Specifying a type will restrict the results to this type. If multiple
	   * types are specified, the API will return all addresses that match any of the types.
	   *
	   * @param resultTypes The result types to restrict to.
	   * @return Returns this {@code GeocodingApiRequest} for call chaining.
	   */
	IGeocodingApiRequest resultType(AddressType... resultTypes);

	/**
	   * Sets the location type. Specifying a type will restrict the results to this type. If multiple
	   * types are specified, the API will return all addresses that match any of the types.
	   *
	   * @param locationTypes The location types to restrict to.
	   * @return Returns this {@code GeocodingApiRequest} for call chaining.
	   */
	IGeocodingApiRequest locationType(LocationType... locationTypes);

}