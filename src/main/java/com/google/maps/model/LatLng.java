/*
 * Copyright 2014 Google Inc. All rights reserved.
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

package com.google.maps.model;

import com.google.maps.LocalTestServerContext;
import com.google.maps.PlacesApi;
import com.google.maps.PlacesApiTest;
import com.google.maps.internal.StringJoin.UrlValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

import org.junit.Test;

/** A place on Earth, represented by a latitude/longitude pair. */
public class LatLng implements UrlValue, Serializable {

  private static final long serialVersionUID = 1L;

  /** The latitude of this location. */
  public double lat;

  /** The longitude of this location. */
  public double lng;

  /**
   * Constructs a location with a latitude/longitude pair.
   *
   * @param lat The latitude of this location.
   * @param lng The longitude of this location.
   */
  public LatLng(double lat, double lng) {
    this.lat = lat;
    this.lng = lng;
  }

  /** Serialisation constructor. */
  public LatLng() {}

  @Override
  public String toString() {
    return toUrlValue();
  }

  @Override
  public String toUrlValue() {
    // Enforce Locale to English for double to string conversion
    return String.format(Locale.ENGLISH, "%.8f,%.8f", lat, lng);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LatLng latLng = (LatLng) o;
    return Double.compare(latLng.lat, lat) == 0 && Double.compare(latLng.lng, lng) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(lat, lng);
  }

@Test
  public void testPizzaInNewYorkPagination(PlacesApiTest placesApiTest) throws Exception {
    try (LocalTestServerContext sc = new LocalTestServerContext(placesApiTest.placesApiPizzaInNewYork)) {
      PlacesSearchResponse response =
          PlacesApi.textSearchQuery(sc.context, "Pizza in New York").await();

      sc.assertParamValue("Pizza in New York", "query");

      assertNotNull(response.toString());
      assertEquals(20, response.results.length);
      assertEquals(
          "CvQB6AAAAPQLwX6KjvGbOw81Y7aYVhXRlHR8M60aCRXFDM9eyflac4BjE5MaNxTj_1T429x3H2kzBd-ztTFXCSu1CPh3kY44Gu0gmL-xfnArnPE9-BgfqXTpgzGPZNeCltB7m341y4LnU-NE2omFPoDWIrOPIyHnyi05Qol9eP2wKW7XPUhMlHvyl9MeVgZ8COBZKvCdENHbhBD1MN1lWlada6A9GPFj06cCp1aqRGW6v98-IHcIcM9RcfMcS4dLAFm6TsgLq4tpeU6E1kSzhrvDiLMBXdJYFlI0qJmytd2wS3vD0t3zKgU6Im_mY-IJL7AwAqhugBIQ8k0X_n6TnacL9BExELBaixoUo8nPOwWm0Nx02haufF2dY0VL-tg",
          response.nextPageToken);
    }
  }
}
