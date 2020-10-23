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

import com.google.gson.FieldNamingPolicy;
import com.google.maps.NearbySearchRequest;
import com.google.maps.model.LatLng;

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

/**
   * Specifies the distance (in meters) within which to return place results. The maximum allowed
   * radius is 50,000 meters. Note that radius must not be included if {@code rankby=DISTANCE} is
   * specified.
   *
   * @param nearbySearchRequest TODO
 * @param distance The distance in meters around the {@link #location(LatLng)} to search.
 * @return Returns this {@code NearbyApiRequest} for call chaining.
   */
  public NearbySearchRequest radius(NearbySearchRequest nearbySearchRequest, int distance) {
    if (distance > 50000) {
      throw new IllegalArgumentException("The maximum allowed radius is 50,000 meters.");
    }
    return nearbySearchRequest.param("radius", String.valueOf(distance));
  }
}
