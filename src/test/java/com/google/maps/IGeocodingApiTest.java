package com.google.maps;

import org.junit.Test;

public interface IGeocodingApiTest {

	void testGeocodeLibraryType() throws Exception;

	void testSimpleGeocode() throws Exception;

	void testPlaceGeocode() throws Exception;

	void testAsync() throws Exception;

	void testReverseGeocode() throws Exception;

	/**
	   * Simple geocode sample: <a
	   * href="https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA">
	   * Address Geocode for "1600 Amphitheatre Parkway, Mountain View, CA"</a>.
	   */
	void testGeocodeTheGoogleplex() throws Exception;

	/**
	   * Address geocode with bounds: <a
	   * href="https://maps.googleapis.com/maps/api/geocode/json?address=Winnetka&bounds=34.172684,-118.604794|34.236144,-118.500938">
	   * Winnetka within (34.172684,-118.604794) - (34.236144,-118.500938)</a>.
	   */
	void testGeocodeWithBounds() throws Exception;

	/**
	   * Geocode with region biasing: <a
	   * href="https://maps.googleapis.com/maps/api/geocode/json?address=Toledo&region=es">Geocode for
	   * Toledo in Spain</a>.
	   */
	void testGeocodeWithRegionBiasing() throws Exception;

	/**
	   * Geocode with component filtering: <a
	   * href="https://maps.googleapis.com/maps/api/geocode/json?address=santa+cruz&components=country:ES">
	   * Geocoding "santa cruz" with country set to ES</a>.
	   */
	void testGeocodeWithComponentFilter() throws Exception;

	/**
	   * Geocode with multiple component filters: <a
	   * href="https://maps.googleapis.com/maps/api/geocode/json?address=Torun&components=administrative_area:TX|country:US">
	   * Geocoding Torun, with administrative area of "TX" and country of "US"</a>.
	   */
	void testGeocodeWithMultipleComponentFilters() throws Exception;

	/**
	   * Making a request using just components filter: <a
	   * href="https://maps.googleapis.com/maps/api/geocode/json?components=route:Annegatan|administrative_area:Helsinki|country:Finland">
	   * Searching for a route of Annegatan, in the administrative area of Helsinki, and the country of
	   * Finland </a>.
	   */
	void testGeocodeWithJustComponents() throws Exception;

	/**
	   * Simple reverse geocoding. <a
	   * href="https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452">Reverse
	   * geocode (40.714224,-73.961452)</a>.
	   */
	void testSimpleReverseGeocode() throws Exception;

	/**
	   * Reverse geocode restricted by type: <a
	   * href="https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&location_type=ROOFTOP&result_type=street_address">
	   * Reverse Geocode (40.714224,-73.961452) with location type of ROOFTOP and result type of
	   * street_address</a>.
	   */
	void testReverseGeocodeRestrictedByType() throws Exception;

	/** Testing UTF8 result parsing. */
	void testUtfResult() throws Exception;

	/**
	   * Testing custom parameter pass through.
	   *
	   * <p>See <a
	   * href="https://googlegeodevelopers.blogspot.com.au/2016/11/address-geocoding-in-google-maps-apis.html">
	   * Address Geocoding in the Google Maps APIs</a> for the reasoning behind this usage.
	   */
	void testCustomParameterPassThrough() throws Exception;

	/** Testing Kita Ward reverse geocode. */
	void testReverseGeocodeWithKitaWard() throws Exception;

	/** Testing supported Address Types for Geocoding. */
	void testSupportedAddressTypesFood() throws Exception;

	/** Testing supported Address Types for Geocoding - Synagogue. */
	void testSupportedAddressTypesSynagogue() throws Exception;

}