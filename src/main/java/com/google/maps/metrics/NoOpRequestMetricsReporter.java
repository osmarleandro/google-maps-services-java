package com.google.maps.metrics;

/** A no-op implementation that does nothing */
public final class NoOpRequestMetricsReporter implements RequestMetricsReporter_RENAMED {

  public NoOpRequestMetricsReporter() {}

  public RequestMetrics newRequest(String requestName) {
    return new NoOpRequestMetrics(requestName);
  }
}
