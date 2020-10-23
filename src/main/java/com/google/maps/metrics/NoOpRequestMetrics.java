package com.google.maps.metrics;

/** A no-op implementation that does nothing */
final class NoOpRequestMetrics implements RequestMetrics_RENAMED {

  NoOpRequestMetrics(String requestName) {}

  public void startNetwork() {}

  public void endNetwork() {}

  public void endRequest(Exception exception, int httpStatusCode, long retryCount) {}
}
