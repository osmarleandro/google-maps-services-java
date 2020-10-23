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

import static com.google.maps.internal.StringJoin.join;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.ComponentFilter;

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
   * Sets the component filters. Each component filter consists of a component:value pair and will
   * fully restrict the results from the geocoder.
   *
   * <p>For more information see <a
   * href="https://developers.google.com/maps/documentation/geocoding/intro?hl=pl#ComponentFiltering">
   * Component Filtering</a>.
   *
   * @param geocodingApiRequest TODO
 * @param filters Component filters to apply to the request.
 * @return Returns this {@code GeocodingApiRequest} for call chaining.
   */
  public GeocodingApiRequest components(GeocodingApiRequest geocodingApiRequest, ComponentFilter... filters) {
    return geocodingApiRequest.param("components", join('|', filters));
  }
}
