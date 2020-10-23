/*
 * Copyright 2016 Google Inc. All rights reserved.
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

package com.google.maps.internal;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.OkHttpRequestHandler;
import com.google.maps.PendingResult;
import com.google.maps.errors.ApiException;
import com.google.maps.metrics.RequestMetrics;

import okhttp3.Request;
import okhttp3.Request.Builder;

import java.util.HashSet;

public final class ExceptionsAllowedToRetry extends HashSet<Class<? extends ApiException>> {

  private static final long serialVersionUID = 5283992240187266422L;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append("ExceptionsAllowedToRetry[");

    Object[] array = toArray();
    for (int i = 0; i < array.length; i++) {
      sb.append(array[i]);
      if (i < array.length - 1) {
        sb.append(", ");
      }
    }

    sb.append(']');
    return sb.toString();
  }

public <T, R extends ApiResponse<T>> PendingResult<T> handle(
      String hostName, String url, String userAgent, String experienceIdHeaderValue, Class<R> clazz, FieldNamingPolicy fieldNamingPolicy, long errorTimeout, Integer maxRetries, OkHttpRequestHandler okHttpRequestHandler, RequestMetrics metrics) {
    Builder builder = new Builder().get().header("User-Agent", userAgent);
    if (experienceIdHeaderValue != null) {
      builder = builder.header(HttpHeaders.X_GOOG_MAPS_EXPERIENCE_ID, experienceIdHeaderValue);
    }
    Request req = builder.url(hostName + url).build();

    return new OkHttpPendingResult<>(
        req,
        okHttpRequestHandler.client,
        clazz,
        fieldNamingPolicy,
        errorTimeout,
        maxRetries,
        this,
        metrics);
  }
}
