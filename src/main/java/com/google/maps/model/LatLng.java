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
import com.google.maps.FindPlaceFromTextRequest.LocationBiasIP;
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
  public void testFindPlaceFromText(PlacesApiTest placesApiTest) throws Exception {
    try (LocalTestServerContext sc =
        new LocalTestServerContext(placesApiTest.findPlaceFromTextMuseumOfContemporaryArt)) {

      String input = "Museum of Contemporary Art Australia";

      FindPlaceFromText response =
          PlacesApi.findPlaceFromText(sc.context, input, InputType.TEXT_QUERY)
              .fields(
                  FindPlaceFromTextRequest.FieldMask.BUSINESS_STATUS,
                  FindPlaceFromTextRequest.FieldMask.PHOTOS,
                  FindPlaceFromTextRequest.FieldMask.FORMATTED_ADDRESS,
                  FindPlaceFromTextRequest.FieldMask.NAME,
                  FindPlaceFromTextRequest.FieldMask.RATING,
                  FindPlaceFromTextRequest.FieldMask.OPENING_HOURS,
                  FindPlaceFromTextRequest.FieldMask.GEOMETRY)
              .locationBias(new LocationBiasIP())
              .await();

      sc.assertParamValue(input, "input");
      sc.assertParamValue("textquery", "inputtype");
      sc.assertParamValue(
          "business_status,photos,formatted_address,name,rating,opening_hours,geometry", "fields");
      sc.assertParamValue("ipbias", "locationbias");

      assertNotNull(response);
      PlacesSearchResult candidate = response.candidates[0];
      assertNotNull(candidate);
      assertEquals("140 George St, The Rocks NSW 2000, Australia", candidate.formattedAddress);
      LatLng location = candidate.geometry.location;
      assertEquals(-33.8599358, location.lat, 0.00001);
      assertEquals(151.2090295, location.lng, 0.00001);
      assertEquals("Museum of Contemporary Art Australia", candidate.name);
      assertEquals(true, candidate.openingHours.openNow);
      Photo photo = candidate.photos[0];
      assertEquals(
          "CmRaAAAAXBZe3QrziBst5oTCPUzL4LSgSuWYMctBNRu8bOP4TfwD0aU80YemnnbhjWdFfMX-kkh5h9NhFJky6fW5Ivk_G9fc11GekI0HOCDASZH3qRJmUBsdw0MWoCDZmwQAg-dVEhBb0aLoJXzoZ8cXWEceB9omGhRrX24jI3VnSEQUmInfYoAwSX4OPw",
          photo.photoReference);
      assertEquals(2268, photo.height);
      assertEquals(4032, photo.width);
      assertEquals(4.4, candidate.rating, 0.01);
    }
  }
}
