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

public void checkContext(GeoApiContext geoApiContext, boolean canUseClientId) {
    if (geoApiContext.urlSigner == null && geoApiContext.apiKey == null) {
      throw new IllegalStateException("Must provide either API key or Maps for Work credentials.");
    } else if (!canUseClientId && geoApiContext.apiKey == null) {
      throw new IllegalStateException(
          "API does not support client ID & secret - you must provide a key");
    }
    if (geoApiContext.urlSigner == null && !geoApiContext.apiKey.startsWith("AIza")) {
      throw new IllegalStateException("Invalid API key.");
    }
  }
}
