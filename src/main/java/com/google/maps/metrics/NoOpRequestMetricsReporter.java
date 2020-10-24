package com.google.maps.metrics;

/** A no-op implementation that does nothing */
public final class NoOpRequestMetricsReporter implements RequestMetricsReporter {

  public NoOpRequestMetricsReporter() {}

  @Override
public RequestMetrics newRequest(String requestName) {
    return new NoOpRequestMetrics(requestName);
  }
}
