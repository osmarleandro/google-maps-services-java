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

import java.util.List;
import java.util.Map;

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

public <T, R extends ApiResponse<T>> PendingResult<T> post(
      GeoApiContext geoApiContext, Class<? extends R> clazz, Map<String, List<String>> params) {

    geoApiContext.checkContext(supportsClientId);

    StringBuilder url = new StringBuilder(path);
    if (supportsClientId && geoApiContext.clientId != null) {
      url.append("?client=").append(geoApiContext.clientId);
    } else {
      url.append("?key=").append(geoApiContext.apiKey);
    }

    if (supportsClientId && geoApiContext.urlSigner != null) {
      String signature = geoApiContext.urlSigner.getSignature(url.toString());
      url.append("&signature=").append(signature);
    }

    String hostName = hostName;
    if (geoApiContext.baseUrlOverride != null) {
      hostName = geoApiContext.baseUrlOverride;
    }

    return geoApiContext.requestHandler.handlePost(
        hostName,
        url.toString(),
        params.get("_payload").get(0),
        GeoApiContext.USER_AGENT,
        geoApiContext.experienceIdHeaderValue,
        clazz,
        fieldNamingPolicy,
        geoApiContext.errorTimeout,
        geoApiContext.maxRetries,
        geoApiContext.exceptionsAllowedToRetry,
        geoApiContext.requestMetricsReporter.newRequest(path));
  }
}
