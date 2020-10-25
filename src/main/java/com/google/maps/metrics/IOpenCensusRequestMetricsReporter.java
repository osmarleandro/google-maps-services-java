package com.google.maps.metrics;

public interface IOpenCensusRequestMetricsReporter {

	RequestMetrics newRequest(String requestName);

}