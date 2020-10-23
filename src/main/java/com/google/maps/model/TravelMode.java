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

import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.internal.StringJoin.UrlValue;
import java.util.Locale;

/**
 * You may specify the transportation mode to use for calulating directions. Directions are
 * calculating as driving directions by default.
 *
 * @see <a href="https://developers.google.com/maps/documentation/directions/intro#TravelModes">
 *     Directions API travel modes</a>
 * @see <a href="https://developers.google.com/maps/documentation/distance-matrix/intro">Distance
 *     Matrix API Intro</a>
 */
public enum TravelMode implements UrlValue {
  DRIVING,
  WALKING,
  BICYCLING,
  TRANSIT,

  /**
   * Indicates an unknown travel mode returned by the server. The Java Client for Google Maps
   * Services should be updated to support the new value.
   */
  UNKNOWN;

  @Override
  public String toString() {
    return name().toLowerCase(Locale.ENGLISH);
  }

  @Override
  public String toUrlValue() {
    if (this == UNKNOWN) {
      throw new UnsupportedOperationException("Shouldn't use TravelMode.UNKNOWN in a request.");
    }
    return name().toLowerCase(Locale.ENGLISH);
  }

/**
   * Specifies the mode of transport to use when calculating directions.
   *
   * <p>Note that Distance Matrix requests only support {@link TravelMode#DRIVING}, {@link
   * TravelMode#WALKING}, {@link TravelMode#BICYCLING} and {@link TravelMode#TRANSIT}.
   *
   * @param distanceMatrixApiRequest TODO
 * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
   */
  public DistanceMatrixApiRequest mode(DistanceMatrixApiRequest distanceMatrixApiRequest) {
    if (TravelMode.DRIVING.equals(this)
        || TravelMode.WALKING.equals(this)
        || TravelMode.BICYCLING.equals(this)
        || TravelMode.TRANSIT.equals(this)) {
      return distanceMatrixApiRequest.param("mode", this);
    }
    throw new IllegalArgumentException(
        "Distance Matrix API travel modes must be Driving, Transit, Walking or Bicycling");
  }
}
