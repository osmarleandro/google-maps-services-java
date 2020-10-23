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
  public void testNearbySearchRequest(PlacesApiTest placesApiTest) throws Exception {
    try (LocalTestServerContext sc = new LocalTestServerContext("{\"status\" : \"OK\"}")) {
      LatLng location = new LatLng(10, 20);
      PlacesApi.nearbySearchQuery(sc.context, location)
          .radius(5000)
          .rankby(RankBy.PROMINENCE)
          .keyword("keyword")
          .language("en")
          .minPrice(PriceLevel.INEXPENSIVE)
          .maxPrice(PriceLevel.EXPENSIVE)
          .name("name")
          .openNow(true)
          .type(PlaceType.AIRPORT)
          .pageToken("next-page-token")
          .await();

      sc.assertParamValue(location.toUrlValue(), "location");
      sc.assertParamValue("5000", "radius");
      sc.assertParamValue(RankBy.PROMINENCE.toString(), "rankby");
      sc.assertParamValue("keyword", "keyword");
      sc.assertParamValue("en", "language");
      sc.assertParamValue(PriceLevel.INEXPENSIVE.toString(), "minprice");
      sc.assertParamValue(PriceLevel.EXPENSIVE.toString(), "maxprice");
      sc.assertParamValue("name", "name");
      sc.assertParamValue("true", "opennow");
      sc.assertParamValue(PlaceType.AIRPORT.toString(), "type");
      sc.assertParamValue("next-page-token", "pagetoken");
    }
  }
}
