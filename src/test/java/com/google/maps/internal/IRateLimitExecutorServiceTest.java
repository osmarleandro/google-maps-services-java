package com.google.maps.internal;

import org.junit.Test;

public interface IRateLimitExecutorServiceTest {

	void testRateLimitDoesNotExceedSuppliedQps() throws Exception;

	void testDelayThreadIsStoppedAfterShutdownIsCalled() throws InterruptedException;

}