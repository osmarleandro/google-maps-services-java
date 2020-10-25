package com.google.maps.metrics;

public interface INoOpRequestMetricsReporter {

	RequestMetrics newRequest(String requestName);

}