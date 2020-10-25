package com.google.maps.metrics;

interface INoOpRequestMetrics {

	void startNetwork();

	void endNetwork();

	void endRequest(Exception exception, int httpStatusCode, long retryCount);

}