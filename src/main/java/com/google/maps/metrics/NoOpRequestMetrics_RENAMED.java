package com.google.maps.metrics;

/** A no-op implementation that does nothing */
final class NoOpRequestMetrics_RENAMED implements RequestMetrics {

  NoOpRequestMetrics_RENAMED(String requestName) {}

  public void startNetwork() {}

  public void endNetwork() {}

  public void endRequest(Exception exception, int httpStatusCode, long retryCount) {}
}
