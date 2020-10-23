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
import com.google.maps.PlaceAutocompleteRequest.SessionToken;
import com.google.maps.PlacesApi;
import com.google.maps.PlacesApiTest;
import com.google.maps.internal.StringJoin.UrlValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.Arrays;
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
  public void testPlaceAutocompleteWithType(PlacesApiTest placesApiTest) throws Exception {
    try (LocalTestServerContext sc =
        new LocalTestServerContext(placesApiTest.placesApiPlaceAutocompleteWithType)) {
      SessionToken session = new SessionToken();
      AutocompletePrediction[] predictions =
          PlacesApi.placeAutocomplete(sc.context, "po", session)
              .components(ComponentFilter.country("nz"))
              .types(PlaceAutocompleteType.REGIONS)
              .await();

      sc.assertParamValue("po", "input");
      sc.assertParamValue("country:nz", "components");
      sc.assertParamValue("(regions)", "types");
      sc.assertParamValue(session.toUrlValue(), "sessiontoken");

      assertNotNull(Arrays.toString(predictions));
      assertEquals(5, predictions.length);
      for (AutocompletePrediction prediction : predictions) {
        for (int j = 0; j < prediction.types.length; j++) {
          assertFalse(prediction.types[j].equals("route"));
          assertFalse(prediction.types[j].equals("zoo"));
        }
      }
    }
  }
}
