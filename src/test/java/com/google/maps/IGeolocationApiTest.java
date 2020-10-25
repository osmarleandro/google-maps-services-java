package com.google.maps;

import org.junit.Test;

import com.google.maps.errors.InvalidRequestException;
import com.google.maps.errors.NotFoundException;

public interface IGeolocationApiTest {

	void testDocSampleGeolocation() throws Exception;

	void testMinimumWifiGeolocation() throws Exception;

	void testBasicGeolocation() throws Exception;

	void testAlternateWifiSetterGeolocation() throws Exception;

	void testMaximumWifiGeolocation() throws Exception;

	void testMinimumCellTowerGeolocation() throws Exception;

	void testAlternatePayloadBuilderGeolocation() throws Exception;

	void testMaximumCellTowerGeolocation() throws Exception;

	void testNoPayloadGeolocation0() throws Exception;

	void testNoPayloadGeolocation1() throws Exception;

	void testNotFoundGeolocation() throws Exception;

	void testInvalidArgumentGeolocation() throws Exception;

}