package com.google.maps.metrics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public interface IOpenCensusTest {

	void Setup();

	void Teardown();

	void testSuccess() throws Exception;

}