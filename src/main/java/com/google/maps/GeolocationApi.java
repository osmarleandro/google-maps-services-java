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

package com.google.maps;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.errors.AccessNotConfiguredException;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.errors.MaxElementsExceededException;
import com.google.maps.errors.MaxRouteLengthExceededException;
import com.google.maps.errors.MaxWaypointsExceededException;
import com.google.maps.errors.NotFoundException;
import com.google.maps.errors.OverDailyLimitException;
import com.google.maps.errors.OverQueryLimitException;
import com.google.maps.errors.RequestDeniedException;
import com.google.maps.errors.UnknownErrorException;
import com.google.maps.errors.ZeroResultsException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.GeolocationPayload;
import com.google.maps.model.GeolocationResult;
import com.google.maps.model.LatLng;

/*
 *  The Google Maps Geolocation API returns a location and accuracy radius based on information
 *  about cell towers and WiFi nodes that the mobile client can detect.
 *
 * <p>Please see the<a href="https://developers.google.com/maps/documentation/geolocation/intro#top_of_page">
 *   Geolocation API</a> for more detail.
 *
 *
 */
public class GeolocationApi {
  private static final String API_BASE_URL = "https://www.googleapis.com";

  static final ApiConfig GEOLOCATION_API_CONFIG =
      new ApiConfig("/geolocation/v1/geolocate")
          .hostName(API_BASE_URL)
          .supportsClientId(false)
          .fieldNamingPolicy(FieldNamingPolicy.IDENTITY)
          .requestVerb("POST");

  private GeolocationApi() {}

  public static PendingResult<GeolocationResult> geolocate(
      GeoApiContext context, GeolocationPayload payload) {
    return new GeolocationApiRequest(context).Payload(payload).CreatePayload();
  }

  public static GeolocationApiRequest newRequest(GeoApiContext context) {
    return new GeolocationApiRequest(context);
  }

  public static class Response implements ApiResponse<GeolocationResult> {
    public int code = 200;
    public String message = "OK";
    public double accuracy = -1.0;
    public LatLng location = null;
    public String domain = null;
    public String reason = null;
    public String debugInfo = null;

    @Override
    public boolean successful() {
      return code == 200;
    }

    @Override
    public GeolocationResult getResult() {
      GeolocationResult result = new GeolocationResult();
      result.accuracy = accuracy;
      result.location = location;
      return result;
    }

    @Override
    public ApiException getError() {
      if (successful()) {
        return null;
      }
      // Classic Geo API error formats
	if ("OK".equals(reason)) {
	  return null;
	} else if ("INVALID_REQUEST".equals(reason)) {
	  return new InvalidRequestException(message);
	} else if ("MAX_ELEMENTS_EXCEEDED".equals(reason)) {
	  return new MaxElementsExceededException(message);
	} else if ("MAX_ROUTE_LENGTH_EXCEEDED".equals(reason)) {
	  return new MaxRouteLengthExceededException(message);
	} else if ("MAX_WAYPOINTS_EXCEEDED".equals(reason)) {
	  return new MaxWaypointsExceededException(message);
	} else if ("NOT_FOUND".equals(reason)) {
	  return new NotFoundException(message);
	} else if ("OVER_QUERY_LIMIT".equals(reason)) {
	  if ("You have exceeded your daily request quota for this API."
	      .equalsIgnoreCase(message)) {
	    return new OverDailyLimitException(message);
	  }
	  return new OverQueryLimitException(message);
	} else if ("REQUEST_DENIED".equals(reason)) {
	  return new RequestDeniedException(message);
	} else if ("UNKNOWN_ERROR".equals(reason)) {
	  return new UnknownErrorException(message);
	} else if ("ZERO_RESULTS".equals(reason)) {
	  return new ZeroResultsException(message);
	}
	
	// New-style Geo API error formats
	if ("ACCESS_NOT_CONFIGURED".equals(reason)) {
	  return new AccessNotConfiguredException(message);
	} else if ("INVALID_ARGUMENT".equals(reason)) {
	  return new InvalidRequestException(message);
	} else if ("RESOURCE_EXHAUSTED".equals(reason)) {
	  return new OverQueryLimitException(message);
	} else if ("PERMISSION_DENIED".equals(reason)) {
	  return new RequestDeniedException(message);
	}
	
	// Geolocation Errors
	if ("keyInvalid".equals(reason)) {
	  return new AccessNotConfiguredException(message);
	} else if ("dailyLimitExceeded".equals(reason)) {
	  return new OverDailyLimitException(message);
	} else if ("userRateLimitExceeded".equals(reason)) {
	  return new OverQueryLimitException(message);
	} else if ("notFound".equals(reason)) {
	  return new NotFoundException(message);
	} else if ("parseError".equals(reason)) {
	  return new InvalidRequestException(message);
	} else if ("invalid".equals(reason)) {
	  return new InvalidRequestException(message);
	}
	
	// We've hit an unknown error. This is not a state we should hit,
	// but we don't want to crash a user's application if we introduce a new error.
	return new UnknownErrorException(
	    "An unexpected error occurred. Status: " + reason + ", Message: " + message);
    }
  }
}
