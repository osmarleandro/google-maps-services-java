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

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.FindPlaceFromTextRequest.InputType;
import com.google.maps.FindPlaceFromTextRequest.LocationBiasRectangular;
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
  public void testFindPlaceFromTextRectangular(PlacesApiTest placesApiTest) throws Exception {
    try (LocalTestServerContext sc =
        new LocalTestServerContext(placesApiTest.findPlaceFromTextMuseumOfContemporaryArt)) {

      String input = "Museum of Contemporary Art Australia";

      PlacesApi.findPlaceFromText(sc.context, input, InputType.TEXT_QUERY)
          .fields(
              FindPlaceFromTextRequest.FieldMask.PHOTOS,
              FindPlaceFromTextRequest.FieldMask.FORMATTED_ADDRESS,
              FindPlaceFromTextRequest.FieldMask.NAME,
              FindPlaceFromTextRequest.FieldMask.RATING,
              FindPlaceFromTextRequest.FieldMask.OPENING_HOURS,
              FindPlaceFromTextRequest.FieldMask.GEOMETRY)
          .locationBias(new LocationBiasRectangular(new LatLng(1, 2), new LatLng(3, 4)))
          .await();

      sc.assertParamValue(input, "input");
      sc.assertParamValue("textquery", "inputtype");
      sc.assertParamValue("photos,formatted_address,name,rating,opening_hours,geometry", "fields");
      sc.assertParamValue("rectangle:1.00000000,2.00000000|3.00000000,4.00000000", "locationbias");
    }
  }
}
