package com.google.maps.metrics;

import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;

/** An OpenCensus logger that generates success and latency metrics. */
final class OpenCensusRequestMetrics implements RequestMetrics {
  private final String requestName;
  private final Tagger tagger;
  private final StatsRecorder statsRecorder;

  private long requestStart;
  private long networkStart;
  private long networkTime;
  private boolean finished;

  OpenCensusRequestMetrics(String requestName, Tagger tagger, StatsRecorder statsRecorder) {
    this.requestName = requestName;
    this.tagger = tagger;
    this.statsRecorder = statsRecorder;
    this.requestStart = milliTime();
    this.networkStart = milliTime();
    this.networkTime = 0;
    this.finished = false;
  }

  @Override
  public void startNetwork() {
    this.networkStart = milliTime();
  }

  @Override
  public void endNetwork() {
    this.networkTime += milliTime() - this.networkStart;
  }

  private String exceptionName(Exception exception) {
    if (exception == null) {
      return "";
    } else {
      return exception.getClass().getName();
    }
  }

  private long milliTime() {
    return System.currentTimeMillis();
  }
}
