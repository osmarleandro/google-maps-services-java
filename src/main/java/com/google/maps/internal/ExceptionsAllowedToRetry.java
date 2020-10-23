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

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.FetchOptions.Builder;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.gson.FieldNamingPolicy;
import com.google.maps.GaeRequestHandler;
import com.google.maps.PendingResult;
import com.google.maps.errors.ApiException;
import com.google.maps.metrics.RequestMetrics;

import java.net.MalformedURLException;
import java.net.URL;
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
      String hostName, String url, String userAgent, String experienceIdHeaderValue, Class<R> clazz, FieldNamingPolicy fieldNamingPolicy, long errorTimeout, Integer maxRetries, GaeRequestHandler gaeRequestHandler, RequestMetrics metrics) {
    FetchOptions fetchOptions = Builder.withDeadline(10);
    HTTPRequest req;
    try {
      req = new HTTPRequest(new URL(hostName + url), HTTPMethod.POST, fetchOptions);
      if (experienceIdHeaderValue != null) {
        req.setHeader(
            new HTTPHeader(HttpHeaders.X_GOOG_MAPS_EXPERIENCE_ID, experienceIdHeaderValue));
      }
    } catch (MalformedURLException e) {
      GaeRequestHandler.LOG.error("Request: {}{}", hostName, url, e);
      throw (new RuntimeException(e));
    }

    return new GaePendingResult<>(
        req,
        gaeRequestHandler.client,
        clazz,
        fieldNamingPolicy,
        errorTimeout,
        maxRetries,
        this,
        metrics);
  }
}
