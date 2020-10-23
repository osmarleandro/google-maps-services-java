package com.google.maps.metrics;

/** A no-op implementation that does nothing */
public final class NoOpRequestMetricsReporter_RENAMED implements RequestMetricsReporter {

  public NoOpRequestMetricsReporter_RENAMED() {}

  public RequestMetrics newRequest(String requestName) {
    return new NoOpRequestMetrics(requestName);
  }
}
