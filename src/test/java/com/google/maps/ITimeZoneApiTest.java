package com.google.maps;

import org.junit.Test;

import com.google.maps.errors.ZeroResultsException;

public interface ITimeZoneApiTest {

	void testGetTimeZone() throws Exception;

	void testNoResult() throws Exception;

}