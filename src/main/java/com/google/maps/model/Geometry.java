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

import java.io.Serializable;
import java.util.Arrays;

/** The geometry of a Geocoding result. */
public class Geometry implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * The bounding box which can fully contain the returned result (optionally returned). Note that
   * these bounds may not match the recommended viewport. (For example, San Francisco includes the
   * Farallon islands, which are technically part of the city, but probably should not be returned
   * in the viewport.)
   */
  public Bounds bounds;

  /**
   * The geocoded latitude/longitude value. For normal address lookups, this field is typically the
   * most important.
   */
  public LatLng location;

  /** The level of certainty of this geocoding result. */
  public LocationType locationType;

  /**
   * The recommended viewport for displaying the returned result. Generally the viewport is used to
   * frame a result when displaying it to a user.
   */
  public Bounds viewport;

  @Override
  public String toString() {
    return String.format(
        "[Geometry: %s (%s) bounds=%s, viewport=%s]", location, locationType, bounds, viewport);
  }

public String toString(PlaceDetails placeDetails) {
    StringBuilder sb = new StringBuilder("[PlaceDetails: ");
    sb.append("\"").append(placeDetails.name).append("\"");
    sb.append(" ").append(placeDetails.placeId).append(" (").append(placeDetails.scope).append(")");
    sb.append(" address=\"").append(placeDetails.formattedAddress).append("\"");
    sb.append(" geometry=").append(this);
    if (placeDetails.vicinity != null) {
      sb.append(", vicinity=").append(placeDetails.vicinity);
    }
    if (placeDetails.types != null && placeDetails.types.length > 0) {
      sb.append(", types=").append(Arrays.toString(placeDetails.types));
    }
    if (placeDetails.altIds != null && placeDetails.altIds.length > 0) {
      sb.append(", altIds=").append(Arrays.toString(placeDetails.altIds));
    }
    if (placeDetails.formattedPhoneNumber != null) {
      sb.append(", phone=").append(placeDetails.formattedPhoneNumber);
    }
    if (placeDetails.internationalPhoneNumber != null) {
      sb.append(", internationalPhoneNumber=").append(placeDetails.internationalPhoneNumber);
    }
    if (placeDetails.url != null) {
      sb.append(", url=").append(placeDetails.url);
    }
    if (placeDetails.website != null) {
      sb.append(", website=").append(placeDetails.website);
    }
    if (placeDetails.icon != null) {
      sb.append(", icon");
    }
    if (placeDetails.openingHours != null) {
      sb.append(", openingHours");
      sb.append(", utcOffset=").append(placeDetails.utcOffset);
    }
    if (placeDetails.priceLevel != null) {
      sb.append(", priceLevel=").append(placeDetails.priceLevel);
    }
    sb.append(", rating=").append(placeDetails.rating);
    if (placeDetails.permanentlyClosed) {
      sb.append(", permanentlyClosed");
    }
    if (placeDetails.userRatingsTotal > 0) {
      sb.append(", userRatingsTotal=").append(placeDetails.userRatingsTotal);
    }
    if (placeDetails.photos != null && placeDetails.photos.length > 0) {
      sb.append(", ").append(placeDetails.photos.length).append(" photos");
    }
    if (placeDetails.reviews != null && placeDetails.reviews.length > 0) {
      sb.append(", ").append(placeDetails.reviews.length).append(" reviews");
    }
    if (placeDetails.htmlAttributions != null && placeDetails.htmlAttributions.length > 0) {
      sb.append(", ").append(placeDetails.htmlAttributions.length).append(" htmlAttributions");
    }
    if (placeDetails.businessStatus != null) {
      sb.append(", businessStatus=").append(placeDetails.businessStatus);
    }
    sb.append("]");
    return sb.toString();
  }
}
