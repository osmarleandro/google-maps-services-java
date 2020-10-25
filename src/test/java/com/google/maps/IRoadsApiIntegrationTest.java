package com.google.maps;

import org.junit.Test;

public interface IRoadsApiIntegrationTest {

	void testSnapToRoad() throws Exception;

	void testSpeedLimitsWithLatLngs() throws Exception;

	void testSpeedLimitsWithUsaLatLngs() throws Exception;

	void testSpeedLimitsWithPlaceIds() throws Exception;

	void testSnappedSpeedLimitRequest() throws Exception;

	void testNearestRoads() throws Exception;

}