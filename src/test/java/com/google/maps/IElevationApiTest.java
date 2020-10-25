package com.google.maps;

import org.junit.Test;

import com.google.maps.errors.InvalidRequestException;
import com.google.maps.errors.RequestDeniedException;

public interface IElevationApiTest {

	void testGetByPointThrowsInvalidRequestExceptionFromResponse() throws Exception;

	void testGetByPointsThrowsRequestDeniedExceptionFromResponse() throws Exception;

	void testGetPoint() throws Exception;

	void testGetPoints() throws Exception;

	void testGetPath() throws Exception;

	void testDirectionsAlongPath() throws Exception;

}