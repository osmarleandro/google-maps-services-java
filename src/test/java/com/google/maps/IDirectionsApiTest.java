package com.google.maps;

import org.junit.Test;

import com.google.maps.errors.NotFoundException;

public interface IDirectionsApiTest {

	void testGetDirections() throws Exception;

	void testBuilder() throws Exception;

	void testResponseTimesArePopulatedCorrectly() throws Exception;

	/**
	   * A simple query from Toronto to Montreal.
	   *
	   * <p>{@code
	   * http://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal}
	   */
	void testTorontoToMontreal() throws Exception;

	/**
	   * Going from Toronto to Montreal by bicycle, avoiding highways.
	   *
	   * <p>{@code
	   * http://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&avoid=highways&mode=bicycling}
	   */
	void testTorontoToMontrealByBicycleAvoidingHighways() throws Exception;

	void testSanFranciscoToSeattleByBicycleAvoidingIndoor() throws Exception;

	/**
	   * Brooklyn to Queens by public transport.
	   *
	   * <p>{@code
	   * http://maps.googleapis.com/maps/api/directions/json?origin=Brooklyn&destination=Queens&departure_time=1343641500&mode=transit}
	   */
	void testBrooklynToQueensByTransit() throws Exception;

	/**
	   * Boston to Concord, via Charlestown and Lexington.
	   *
	   * <p>{@code
	   * http://maps.googleapis.com/maps/api/directions/json?origin=Boston,MA&destination=Concord,MA&waypoints=Charlestown,MA|Lexington,MA}
	   */
	void testBostonToConcordViaCharlestownAndLexington() throws Exception;

	/**
	   * Boston to Concord, via Charlestown and Lexington, using non-stopover waypoints.
	   *
	   * <p>{@code
	   * http://maps.googleapis.com/maps/api/directions/json?origin=Boston,MA&destination=Concord,MA&waypoints=via:Charlestown,MA|via:Lexington,MA}
	   */
	void testBostonToConcordViaCharlestownAndLexingtonNonStopover() throws Exception;

	/**
	   * Boston to Concord, via Charlestown and Lexington, but using exact latitude and longitude
	   * coordinates for the waypoints.
	   *
	   * <p>{@code
	   * http://maps.googleapis.com/maps/api/directions/json?origin=Boston,MA&destination=Concord,MA&waypoints=42.379322,-71.063384|42.444303,-71.229087}
	   */
	void testBostonToConcordViaCharlestownAndLexingtonLatLng() throws Exception;

	/**
	   * Boston to Concord, via Charlestown and Lexington, but using exact latitude and longitude
	   * coordinates for the waypoints, using non-stopover waypoints.
	   *
	   * <p>{@code
	   * http://maps.googleapis.com/maps/api/directions/json?origin=Boston,MA&destination=Concord,MA&waypoints=via:42.379322,-71.063384|via:42.444303,-71.229087}
	   */
	void testBostonToConcordViaCharlestownAndLexingtonLatLngNonStopoever() throws Exception;

	/**
	   * Toledo to Madrid, in Spain. This showcases region biasing results.
	   *
	   * <p>{@code
	   * http://maps.googleapis.com/maps/api/directions/json?origin=Toledo&destination=Madrid&region=es}
	   */
	void testToledoToMadridInSpain() throws Exception;

	/** Test the language parameter. */
	void testLanguageParameter() throws Exception;

	/** Tests the {@code traffic_model} and {@code duration_in_traffic} parameters. */
	void testTrafficModel() throws Exception;

	/** Test transit without arrival or departure times specified. */
	void testTransitWithoutSpecifyingTime() throws Exception;

	/** Test the extended transit parameters: mode and routing preference. */
	void testTransitParams() throws Exception;

	void testTravelModeWalking() throws Exception;

	void testNotFound() throws Exception;

	/** Test GeocodedWaypoints results. */
	void testGeocodedWaypoints() throws Exception;

	/** Tests that calling {@code optimizeWaypoints(true)} works in either order. */
	void testOptimizeWaypointsBeforeWaypoints() throws Exception;

	/** Tests that calling {@code optimizeWaypoints(true)} works in either order. */
	void testOptimizeWaypointsAfterWaypoints() throws Exception;

}