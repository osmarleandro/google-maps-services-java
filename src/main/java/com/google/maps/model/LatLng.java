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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.net.URI;
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
  public void testTextSearchResponse(PlacesApiTest placesApiTest) throws Exception {
    try (LocalTestServerContext sc = new LocalTestServerContext(placesApiTest.textSearchResponseBody)) {
      PlacesSearchResponse results = PlacesApi.textSearchQuery(sc.context, "Google Sydney").await();

      assertNotNull(results);
      assertNotNull(results.results);
      assertEquals(1, results.results.length);
      assertNotNull(results.toString());

      PlacesSearchResult result = results.results[0];
      assertNotNull(result.formattedAddress);
      assertEquals("5, 48 Pirrama Rd, Pyrmont NSW 2009, Australia", result.formattedAddress);
      assertNotNull(result.geometry);
      assertNotNull(result.geometry.location);
      assertEquals(-33.866611, result.geometry.location.lat, 0.0001);
      assertEquals(151.195832, result.geometry.location.lng, 0.0001);
      assertNotNull(result.icon);
      assertEquals(
          new URI("https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png"),
          result.icon.toURI());
      assertNotNull(result.name);
      assertEquals("Google", result.name);
      assertNotNull(result.openingHours);
      assertFalse(result.openingHours.openNow);
      assertNotNull(result.photos);

      assertEquals(1, result.photos.length);
      Photo photo = result.photos[0];
      assertNotNull(photo);
      assertEquals(2322, photo.height);
      assertEquals(4128, photo.width);
      assertNotNull(photo.htmlAttributions);
      assertEquals(1, photo.htmlAttributions.length);
      assertEquals(
          "<a href=\"https://maps.google.com/maps/contrib/107252953636064841537\">William Stewart</a>",
          photo.htmlAttributions[0]);
      assertEquals(
          "CmRdAAAAa43ZeiQvF4n-Yv5UnEGcIe0KjdTzzTH4g-g1GuKgWas0g8W7793eFDGxkrG4Z5i_Jua0Z-"
              + "Ib88IuYe2iVAZ0W3Q7wUrp4A2mux4BjZmakLFkTkPj_OZ7ek3vSGnrzqExEhBqB3AIn82lmf38RnVSFH1CGhSWrvzN30A_"
              + "ABGNScuiYEU70wau3w",
          photo.photoReference);

      assertNotNull(result.placeId);
      assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", result.placeId);
      assertEquals(4.4, result.rating, 0.0001);
      assertNotNull(result.types);
      assertNotNull(result.types[0]);
      assertEquals("establishment", result.types[0]);
    }
  }
}
