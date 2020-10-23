/*
 * Copyright 2015 Google Inc. All rights reserved.
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;

/** API configuration builder. Defines fields that are variable per-API. */
public class ApiConfig {
  public String path;
  public FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
  public String hostName = "https://maps.googleapis.com";
  public boolean supportsClientId = true;
  public String requestVerb = "GET";

  public ApiConfig(String path) {
    this.path = path;
  }

  public ApiConfig fieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
    this.fieldNamingPolicy = fieldNamingPolicy;
    return this;
  }

  public ApiConfig hostName(String hostName) {
    this.hostName = hostName;
    return this;
  }

  public ApiConfig supportsClientId(boolean supportsClientId) {
    this.supportsClientId = supportsClientId;
    return this;
  }

  public ApiConfig requestVerb(String requestVerb) {
    this.requestVerb = requestVerb;
    return this;
  }

public <T, R extends ApiResponse<T>> PendingResult<T> get(
      GeoApiContext geoApiContext, Class<? extends R> clazz, String... params) {
    if (params.length % 2 != 0) {
      throw new IllegalArgumentException("Params must be matching key/value pairs.");
    }

    StringBuilder query = new StringBuilder();

    boolean channelSet = false;
    for (int i = 0; i < params.length; i += 2) {
      if (params[i].equals("channel")) {
        channelSet = true;
      }
      query.append('&').append(params[i]).append('=');

      // URL-encode the parameter.
      try {
        query.append(URLEncoder.encode(params[i + 1], "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        // This should never happen. UTF-8 support is required for every Java implementation.
        throw new IllegalStateException(e);
      }
    }

    // Channel can be supplied per-request or per-context. We prioritize it from the request,
    // so if it's not provided there, provide it here
    if (!channelSet && geoApiContext.channel != null && !geoApiContext.channel.isEmpty()) {
      query.append("&channel=").append(geoApiContext.channel);
    }

    return geoApiContext.getWithPath(
        clazz,
        fieldNamingPolicy,
        hostName,
        path,
        supportsClientId,
        query.toString(),
        geoApiContext.requestMetricsReporter.newRequest(path));
  }
}
