package com.google.maps;

import org.junit.Test;

public interface IDistanceMatrixApiTest {

	void testLatLngOriginDestinations() throws Exception;

	void testGetDistanceMatrixWithBasicStringParams() throws Exception;

	void testNewRequestWithAllPossibleParams() throws Exception;

	/**
	   * Test the language parameter.
	   *
	   * <p>Sample request: <a
	   * href="http://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC&mode=bicycling&language=fr-FR">
	   * origins: Vancouver BC|Seattle, destinations: San Francisco|Victoria BC, mode: bicycling,
	   * language: french</a>.
	   */
	void testLanguageParameter() throws Exception;

	/** Test transit without arrival or departure times specified. */
	void testTransitWithoutSpecifyingTime() throws Exception;

	/** Test duration in traffic with traffic model set. */
	void testDurationInTrafficWithTrafficModel() throws Exception;

}