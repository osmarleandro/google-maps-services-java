/*
 * Copyright (C) 2011 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * Copyright 2017 Google Inc. All rights reserved.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.google.maps.internal.ratelimiter;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.util.concurrent.TimeUnit;

/**
 * A time source; returns a time value representing the number of nanoseconds elapsed since some
 * fixed but arbitrary point in time. Note that most users should use {@link Stopwatch} instead of
 * interacting with this class directly.
 *
 * <p><b>Warning:</b> this interface can only be used to measure elapsed time, not wall time.
 *
 * @author Kevin Bourrillion
 */
public abstract class Ticker {
  /** Constructor for use by subclasses. */
  protected Ticker() {}

  /** Returns the number of nanoseconds elapsed since this ticker's fixed point of reference. */
  public abstract long read();

  /** Returns a string representation of the current elapsed time. 
 * @param stopwatch TODO*/
  public String toString(Stopwatch stopwatch) {
    long nanos = stopwatch.elapsedNanos();

    TimeUnit unit = Stopwatch.chooseUnit(nanos);
    double value = (double) nanos / NANOSECONDS.convert(1, unit);

    // Too bad this functionality is not exposed as a regular method call
    return Platform.formatCompact4Digits(value) + " " + Stopwatch.abbreviate(unit);
  }

/** A ticker that reads the current time using {@link System#nanoTime}. */
  public static Ticker systemTicker() {
    return SYSTEM_TICKER;
  }

  private static final Ticker SYSTEM_TICKER =
      new Ticker() {
        @Override
        public long read() {
          return Platform.systemNanoTime();
        }
      };
}
