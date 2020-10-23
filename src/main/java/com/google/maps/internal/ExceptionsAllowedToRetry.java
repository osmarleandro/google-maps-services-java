/*
 * Copyright 2016 Google Inc. All rights reserved.
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

package com.google.maps.internal;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import java.util.HashSet;

public final class ExceptionsAllowedToRetry extends HashSet<Class<? extends ApiException>> {

  private static final long serialVersionUID = 5283992240187266422L;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append("ExceptionsAllowedToRetry[");

    Object[] array = toArray();
    for (int i = 0; i < array.length; i++) {
      sb.append(array[i]);
      if (i < array.length - 1) {
        sb.append(", ");
      }
    }

    sb.append(']');
    return sb.toString();
  }

/**
   * Sets the value for the HTTP header field name {@link HttpHeaders#X_GOOG_MAPS_EXPERIENCE_ID} to
   * be used on subsequent API calls. Calling this method with {@code null} is equivalent to calling
   * {@link #clearExperienceId()}.
   *
   * @param geoApiContext TODO
 * @param experienceId The experience ID if set, otherwise null
   */
  public void setExperienceId(GeoApiContext geoApiContext, String... experienceId) {
    if (experienceId == null || experienceId.length == 0) {
      geoApiContext.experienceIdHeaderValue = null;
      return;
    }
    geoApiContext.experienceIdHeaderValue = StringJoin.join(",", experienceId);
  }
}
