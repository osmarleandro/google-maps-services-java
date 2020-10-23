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
import com.google.maps.PlaceAutocompleteRequest;

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
   * The character position in the input term at which the service uses text for predictions. For
   * example, if the input is 'Googl' and the completion point is 3, the service will match on
   * 'Goo'. The offset should generally be set to the position of the text caret. If no offset is
   * supplied, the service will use the entire term.
   *
   * @param placeAutocompleteRequest TODO
 * @param offset The character offset position of the user's cursor.
 * @return Returns this {@code PlaceAutocompleteRequest} for call chaining.
   */
  public PlaceAutocompleteRequest offset(PlaceAutocompleteRequest placeAutocompleteRequest, int offset) {
    return placeAutocompleteRequest.param("offset", String.valueOf(offset));
  }
}
