package com.google.maps.metrics;

interface IOpenCensusRequestMetrics {

	void startNetwork();

	void endNetwork();

	void endRequest(Exception exception, int httpStatusCode, long retryCount);

}