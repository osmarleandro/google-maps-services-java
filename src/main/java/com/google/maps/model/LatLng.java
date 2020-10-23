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
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.PlacesApiTest;
import com.google.maps.internal.StringJoin.UrlValue;
import com.google.maps.model.OpeningHours.Period;
import com.google.maps.model.OpeningHours.Period.OpenClose.DayOfWeek;
import com.google.maps.model.PlaceDetails.Review;
import com.google.maps.model.PlaceDetails.Review.AspectRating;
import com.google.maps.model.PlaceDetails.Review.AspectRating.RatingType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.net.URI;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
  public void testPlaceDetailsLookupGoogleSydney(PlacesApiTest placesApiTest) throws Exception {
    try (LocalTestServerContext sc = new LocalTestServerContext(placesApiTest.placeDetailResponseBody)) {
      PlaceDetails placeDetails =
          PlacesApi.placeDetails(sc.context, PlacesApiTest.GOOGLE_SYDNEY)
              .fields(
                  PlaceDetailsRequest.FieldMask.PLACE_ID,
                  PlaceDetailsRequest.FieldMask.NAME,
                  PlaceDetailsRequest.FieldMask.TYPES)
              .await();

      sc.assertParamValue(PlacesApiTest.GOOGLE_SYDNEY, "placeid");
      sc.assertParamValue("place_id,name,types", "fields");

      assertNotNull(placeDetails);
      assertNotNull(placeDetails.toString());

      // Address
      assertNotNull(placeDetails.addressComponents);
      assertEquals(placeDetails.addressComponents[0].longName, "5");
      assertEquals(placeDetails.addressComponents[0].types.length, 0);
      assertEquals(placeDetails.addressComponents[1].longName, "48");
      assertEquals(placeDetails.addressComponents[1].types[0], AddressComponentType.STREET_NUMBER);
      assertEquals(placeDetails.addressComponents[2].longName, "Pirrama Road");
      assertEquals(placeDetails.addressComponents[2].shortName, "Pirrama Rd");
      assertEquals(placeDetails.addressComponents[2].types[0], AddressComponentType.ROUTE);
      assertEquals(placeDetails.addressComponents[3].shortName, "Pyrmont");
      assertEquals(placeDetails.addressComponents[3].types[0], AddressComponentType.LOCALITY);
      assertEquals(placeDetails.addressComponents[3].types[1], AddressComponentType.POLITICAL);
      assertEquals(placeDetails.addressComponents[4].longName, "New South Wales");
      assertEquals(placeDetails.addressComponents[4].shortName, "NSW");
      assertEquals(
          placeDetails.addressComponents[4].types[0],
          AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1);
      assertEquals(placeDetails.addressComponents[4].types[1], AddressComponentType.POLITICAL);
      assertEquals(placeDetails.addressComponents[5].longName, "Australia");
      assertEquals(placeDetails.addressComponents[5].shortName, "AU");
      assertEquals(placeDetails.addressComponents[5].types[0], AddressComponentType.COUNTRY);
      assertEquals(placeDetails.addressComponents[5].types[1], AddressComponentType.POLITICAL);
      assertEquals(placeDetails.addressComponents[6].shortName, "2009");
      assertEquals(placeDetails.addressComponents[6].types[0], AddressComponentType.POSTAL_CODE);
      assertNotNull(placeDetails.formattedAddress);
      assertEquals(placeDetails.formattedAddress, "5, 48 Pirrama Rd, Pyrmont NSW 2009, Australia");
      assertNotNull(placeDetails.vicinity);
      assertEquals(placeDetails.vicinity, "5 48 Pirrama Road, Pyrmont");

      // Phone numbers
      assertNotNull(placeDetails.formattedPhoneNumber);
      assertEquals(placeDetails.formattedPhoneNumber, "(02) 9374 4000");
      assertNotNull(placeDetails.internationalPhoneNumber);
      assertEquals(placeDetails.internationalPhoneNumber, "+61 2 9374 4000");

      // Geometry
      assertNotNull(placeDetails.geometry);
      assertNotNull(placeDetails.geometry.location);
      assertEquals(placeDetails.geometry.location.lat, -33.866611, 0.001);
      assertEquals(placeDetails.geometry.location.lng, 151.195832, 0.001);

      // URLs
      assertNotNull(placeDetails.icon);
      assertEquals(
          placeDetails.icon.toURI(),
          new URI("https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png"));
      assertNotNull(placeDetails.url);
      assertEquals(
          placeDetails.url.toURI(),
          new URI("https://plus.google.com/111337342022929067349/about?hl=en-US"));
      assertNotNull(placeDetails.website);
      assertEquals(
          placeDetails.website.toURI(),
          new URI("https://www.google.com.au/about/careers/locations/sydney/"));

      // Name
      assertNotNull(placeDetails.name);
      assertEquals(placeDetails.name, "Google");

      // Opening Hours
      assertNotNull(placeDetails.openingHours);
      assertNotNull(placeDetails.openingHours.openNow);
      assertTrue(placeDetails.openingHours.openNow);
      assertNotNull(placeDetails.openingHours.periods);
      assertEquals(placeDetails.openingHours.periods.length, 5);

      {
        Period monday = placeDetails.openingHours.periods[0];
        Period tuesday = placeDetails.openingHours.periods[1];
        Period wednesday = placeDetails.openingHours.periods[2];
        Period thursday = placeDetails.openingHours.periods[3];
        Period friday = placeDetails.openingHours.periods[4];

        assertEquals(DayOfWeek.MONDAY, monday.open.day);
        LocalTime opening = LocalTime.of(8, 30);
        LocalTime closing5pm = LocalTime.of(17, 0);
        LocalTime closing530pm = LocalTime.of(17, 30);

        assertEquals(opening, monday.open.time);
        assertEquals(DayOfWeek.MONDAY, monday.close.day);
        assertEquals(closing530pm, monday.close.time);

        assertEquals(DayOfWeek.TUESDAY, tuesday.open.day);
        assertEquals(opening, tuesday.open.time);
        assertEquals(DayOfWeek.TUESDAY, tuesday.close.day);
        assertEquals(closing530pm, tuesday.close.time);

        assertEquals(DayOfWeek.WEDNESDAY, wednesday.open.day);
        assertEquals(opening, wednesday.open.time);
        assertEquals(DayOfWeek.WEDNESDAY, wednesday.close.day);
        assertEquals(closing530pm, wednesday.close.time);

        assertEquals(DayOfWeek.THURSDAY, thursday.open.day);
        assertEquals(opening, thursday.open.time);
        assertEquals(DayOfWeek.THURSDAY, thursday.close.day);
        assertEquals(closing530pm, thursday.close.time);

        assertEquals(DayOfWeek.FRIDAY, friday.open.day);
        assertEquals(opening, friday.open.time);
        assertEquals(DayOfWeek.FRIDAY, friday.close.day);
        assertEquals(closing5pm, friday.close.time);
      }

      assertNotNull(placeDetails.openingHours.weekdayText);
      assertEquals(placeDetails.openingHours.weekdayText[0], "Monday: 8:30 am – 5:30 pm");
      assertEquals(placeDetails.openingHours.weekdayText[1], "Tuesday: 8:30 am – 5:30 pm");
      assertEquals(placeDetails.openingHours.weekdayText[2], "Wednesday: 8:30 am – 5:30 pm");
      assertEquals(placeDetails.openingHours.weekdayText[3], "Thursday: 8:30 am – 5:30 pm");
      assertEquals(placeDetails.openingHours.weekdayText[4], "Friday: 8:30 am – 5:00 pm");
      assertEquals(placeDetails.openingHours.weekdayText[5], "Saturday: Closed");
      assertEquals(placeDetails.openingHours.weekdayText[6], "Sunday: Closed");
      assertEquals(placeDetails.utcOffset, 600);

      // Photos
      assertNotNull(placeDetails.photos);
      Photo photo = placeDetails.photos[0];
      assertNotNull(photo);
      assertNotNull(photo.photoReference);
      assertNotNull(photo.htmlAttributions);
      assertNotNull(photo.htmlAttributions[0]);

      // Reviews
      assertNotNull(placeDetails.reviews);
      Review review = placeDetails.reviews[0];
      assertNotNull(review);
      assertNotNull(review.authorName);
      assertEquals("Danielle Lonnon", review.authorName);
      assertNotNull(review.authorUrl);
      assertEquals(
          new URI("https://plus.google.com/118257578392162991040"), review.authorUrl.toURI());
      assertNotNull(review.profilePhotoUrl);
      assertEquals("https://lh5.googleusercontent.com/photo.jpg", review.profilePhotoUrl);
      assertNotNull(review.language);
      assertEquals("en", review.language);
      assertNotNull(review.relativeTimeDescription);
      assertEquals("a month ago", review.relativeTimeDescription);
      assertEquals(5, review.rating);
      assertNotNull(review.text);
      assertTrue(review.text.startsWith("As someone who works in the theatre,"));
      assertNotNull(review.aspects);
      AspectRating aspect = review.aspects[0];
      assertNotNull(aspect);
      assertEquals(3, aspect.rating);
      assertNotNull(aspect.type);
      assertEquals(RatingType.OVERALL, aspect.type);
      assertEquals(1425790392, review.time.toEpochMilli() / 1000);
      assertEquals(
          "2015-03-08 04:53 am",
          DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm a")
              .withZone(ZoneOffset.UTC)
              .format(review.time)
              .toLowerCase());

      // Place ID
      assertNotNull(placeDetails.placeId);
      assertEquals(placeDetails.placeId, PlacesApiTest.GOOGLE_SYDNEY);
      assertNotNull(placeDetails.types);
      assertEquals(placeDetails.types[0], AddressType.ESTABLISHMENT);
      assertEquals(placeDetails.rating, 4.4, 0.1);

      // Permanently closed:
      assertFalse(placeDetails.permanentlyClosed);
    }
  }
}
