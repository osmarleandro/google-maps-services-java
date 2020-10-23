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
import com.google.maps.PlaceDetailsRequest;

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
   * Sets the Region for this request. The region code, specified as a ccTLD (country code top-level
   * domain) two-character value. Most ccTLD codes are identical to ISO 3166-1 codes, with some
   * exceptions. This parameter will only influence, not fully restrict, results.
   *
   * @param placeDetailsRequest TODO
 * @param region The region code.
 * @return Returns this {@code PlaceDetailsRequest} for call chaining.
   */
  public PlaceDetailsRequest region(PlaceDetailsRequest placeDetailsRequest, String region) {
    return placeDetailsRequest.param("region", region);
  }
}
