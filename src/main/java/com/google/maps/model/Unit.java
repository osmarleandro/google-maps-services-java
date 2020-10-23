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

/** Units of measurement. */
public enum Unit implements UrlValue {
  METRIC,
  IMPERIAL;

  @Override
  public String toString() {
    return toUrlValue();
  }

  @Override
  public String toUrlValue() {
    return name().toLowerCase(Locale.ENGLISH);
  }

/**
   * Specifies the unit system to use when expressing distance as text. Distance Matrix results
   * contain text within distance fields to indicate the distance of the calculated route.
   *
   * @param distanceMatrixApiRequest TODO
 * @see <a
   *     href="https://developers.google.com/maps/documentation/distance-matrix/intro#unit_systems">
   *     Unit systems in the Distance Matrix API</a>
   * @return Returns this {@code DistanceMatrixApiRequest} for call chaining.
   */
  public DistanceMatrixApiRequest units(DistanceMatrixApiRequest distanceMatrixApiRequest) {
    return distanceMatrixApiRequest.param("units", this);
  }
}
