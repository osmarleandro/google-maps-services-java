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

package com.google.maps;

import static com.google.maps.internal.StringJoin.join;

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
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;

/**
 * The Google Elevation API provides a simple interface to query locations on the earth for
 * elevation data. Additionally, you may request sampled elevation data along paths, allowing you to
 * calculate elevation changes along routes.
 *
 * <p>See <a href="https://developers.google.com/maps/documentation/elevation/start">the Google Maps
 * Elevation API documentation</a>.
 */
public class ElevationApi {
  private static final ApiConfig API_CONFIG = new ApiConfig("/maps/api/elevation/json");

  private ElevationApi() {}

  /**
   * Gets a list of elevations for a list of points.
   *
   * @param context The {@link GeoApiContext} to make requests through.
   * @param points The points to retrieve elevations for.
   * @return The elevations as a {@link PendingResult}.
   */
  public static PendingResult<ElevationResult[]> getByPoints(
      GeoApiContext context, LatLng... points) {
    return context.get(API_CONFIG, MultiResponse.class, "locations", shortestParam(points));
  }

  /**
   * See <a href="https://developers.google.com/maps/documentation/elevation/intro#Paths">
   * documentation</a>.
   *
   * @param context The {@link GeoApiContext} to make requests through.
   * @param samples The number of samples to retrieve heights along {@code path}.
   * @param path The path to sample.
   * @return The elevations as a {@link PendingResult}.
   */
  public static PendingResult<ElevationResult[]> getByPath(
      GeoApiContext context, int samples, LatLng... path) {
    return context.get(
        API_CONFIG,
        MultiResponse.class,
        "samples",
        String.valueOf(samples),
        "path",
        shortestParam(path));
  }

  /**
   * See <a href="https://developers.google.com/maps/documentation/elevation/intro#Paths">
   * documentation</a>.
   *
   * @param context The {@link GeoApiContext} to make requests through.
   * @param samples The number of samples to retrieve heights along {@code encodedPolyline}.
   * @param encodedPolyline The path to sample as an encoded polyline.
   * @return The elevations as a {@link PendingResult}.
   */
  public static PendingResult<ElevationResult[]> getByPath(
      GeoApiContext context, int samples, EncodedPolyline encodedPolyline) {
    return context.get(
        API_CONFIG,
        MultiResponse.class,
        "samples",
        String.valueOf(samples),
        "path",
        "enc:" + encodedPolyline.getEncodedPath());
  }

  /**
   * Chooses the shortest param (only a guess, since the length is different after URL encoding).
   */
  private static String shortestParam(LatLng[] points) {
    String joined = join('|', points);
    String encoded = "enc:" + PolylineEncoding.encode(points);
    return joined.length() < encoded.length() ? joined : encoded;
  }

  /**
   * Retrieves the elevation of a single location.
   *
   * @param context The {@link GeoApiContext} to make requests through.
   * @param location The location to retrieve the elevation for.
   * @return The elevation as a {@link PendingResult}.
   */
  public static PendingResult<ElevationResult> getByPoint(GeoApiContext context, LatLng location) {
    return context.get(API_CONFIG, SingularResponse.class, "locations", location.toString());
  }

  private static class SingularResponse implements ApiResponse<ElevationResult> {
    public String status;
    public String errorMessage;
    public ElevationResult[] results;

    @Override
    public boolean successful() {
      return "OK".equals(status);
    }

    @Override
    public ElevationResult getResult() {
      return results[0];
    }

    @Override
    public ApiException getError() {
      if (successful()) {
        return null;
      }
      // Classic Geo API error formats
	if ("OK".equals(status)) {
	  return null;
	} else if ("INVALID_REQUEST".equals(status)) {
	  return new InvalidRequestException(errorMessage);
	} else if ("MAX_ELEMENTS_EXCEEDED".equals(status)) {
	  return new MaxElementsExceededException(errorMessage);
	} else if ("MAX_ROUTE_LENGTH_EXCEEDED".equals(status)) {
	  return new MaxRouteLengthExceededException(errorMessage);
	} else if ("MAX_WAYPOINTS_EXCEEDED".equals(status)) {
	  return new MaxWaypointsExceededException(errorMessage);
	} else if ("NOT_FOUND".equals(status)) {
	  return new NotFoundException(errorMessage);
	} else if ("OVER_QUERY_LIMIT".equals(status)) {
	  if ("You have exceeded your daily request quota for this API."
	      .equalsIgnoreCase(errorMessage)) {
	    return new OverDailyLimitException(errorMessage);
	  }
	  return new OverQueryLimitException(errorMessage);
	} else if ("REQUEST_DENIED".equals(status)) {
	  return new RequestDeniedException(errorMessage);
	} else if ("UNKNOWN_ERROR".equals(status)) {
	  return new UnknownErrorException(errorMessage);
	} else if ("ZERO_RESULTS".equals(status)) {
	  return new ZeroResultsException(errorMessage);
	}
	
	// New-style Geo API error formats
	if ("ACCESS_NOT_CONFIGURED".equals(status)) {
	  return new AccessNotConfiguredException(errorMessage);
	} else if ("INVALID_ARGUMENT".equals(status)) {
	  return new InvalidRequestException(errorMessage);
	} else if ("RESOURCE_EXHAUSTED".equals(status)) {
	  return new OverQueryLimitException(errorMessage);
	} else if ("PERMISSION_DENIED".equals(status)) {
	  return new RequestDeniedException(errorMessage);
	}
	
	// Geolocation Errors
	if ("keyInvalid".equals(status)) {
	  return new AccessNotConfiguredException(errorMessage);
	} else if ("dailyLimitExceeded".equals(status)) {
	  return new OverDailyLimitException(errorMessage);
	} else if ("userRateLimitExceeded".equals(status)) {
	  return new OverQueryLimitException(errorMessage);
	} else if ("notFound".equals(status)) {
	  return new NotFoundException(errorMessage);
	} else if ("parseError".equals(status)) {
	  return new InvalidRequestException(errorMessage);
	} else if ("invalid".equals(status)) {
	  return new InvalidRequestException(errorMessage);
	}
	
	// We've hit an unknown error. This is not a state we should hit,
	// but we don't want to crash a user's application if we introduce a new error.
	return new UnknownErrorException(
	    "An unexpected error occurred. Status: " + status + ", Message: " + errorMessage);
    }
  }

  /**
   * Retrieves the elevations of an encoded polyline path.
   *
   * @param context The {@link GeoApiContext} to make requests through.
   * @param encodedPolyline The encoded polyline to retrieve elevations for.
   * @return The elevations as a {@link PendingResult}.
   */
  public static PendingResult<ElevationResult[]> getByPoints(
      GeoApiContext context, EncodedPolyline encodedPolyline) {
    return context.get(
        API_CONFIG, MultiResponse.class, "locations", "enc:" + encodedPolyline.getEncodedPath());
  }

  private static class MultiResponse implements ApiResponse<ElevationResult[]> {
    public String status;
    public String errorMessage;
    public ElevationResult[] results;

    @Override
    public boolean successful() {
      return "OK".equals(status);
    }

    @Override
    public ElevationResult[] getResult() {
      return results;
    }

    @Override
    public ApiException getError() {
      if (successful()) {
        return null;
      }
      // Classic Geo API error formats
	if ("OK".equals(status)) {
	  return null;
	} else if ("INVALID_REQUEST".equals(status)) {
	  return new InvalidRequestException(errorMessage);
	} else if ("MAX_ELEMENTS_EXCEEDED".equals(status)) {
	  return new MaxElementsExceededException(errorMessage);
	} else if ("MAX_ROUTE_LENGTH_EXCEEDED".equals(status)) {
	  return new MaxRouteLengthExceededException(errorMessage);
	} else if ("MAX_WAYPOINTS_EXCEEDED".equals(status)) {
	  return new MaxWaypointsExceededException(errorMessage);
	} else if ("NOT_FOUND".equals(status)) {
	  return new NotFoundException(errorMessage);
	} else if ("OVER_QUERY_LIMIT".equals(status)) {
	  if ("You have exceeded your daily request quota for this API."
	      .equalsIgnoreCase(errorMessage)) {
	    return new OverDailyLimitException(errorMessage);
	  }
	  return new OverQueryLimitException(errorMessage);
	} else if ("REQUEST_DENIED".equals(status)) {
	  return new RequestDeniedException(errorMessage);
	} else if ("UNKNOWN_ERROR".equals(status)) {
	  return new UnknownErrorException(errorMessage);
	} else if ("ZERO_RESULTS".equals(status)) {
	  return new ZeroResultsException(errorMessage);
	}
	
	// New-style Geo API error formats
	if ("ACCESS_NOT_CONFIGURED".equals(status)) {
	  return new AccessNotConfiguredException(errorMessage);
	} else if ("INVALID_ARGUMENT".equals(status)) {
	  return new InvalidRequestException(errorMessage);
	} else if ("RESOURCE_EXHAUSTED".equals(status)) {
	  return new OverQueryLimitException(errorMessage);
	} else if ("PERMISSION_DENIED".equals(status)) {
	  return new RequestDeniedException(errorMessage);
	}
	
	// Geolocation Errors
	if ("keyInvalid".equals(status)) {
	  return new AccessNotConfiguredException(errorMessage);
	} else if ("dailyLimitExceeded".equals(status)) {
	  return new OverDailyLimitException(errorMessage);
	} else if ("userRateLimitExceeded".equals(status)) {
	  return new OverQueryLimitException(errorMessage);
	} else if ("notFound".equals(status)) {
	  return new NotFoundException(errorMessage);
	} else if ("parseError".equals(status)) {
	  return new InvalidRequestException(errorMessage);
	} else if ("invalid".equals(status)) {
	  return new InvalidRequestException(errorMessage);
	}
	
	// We've hit an unknown error. This is not a state we should hit,
	// but we don't want to crash a user's application if we introduce a new error.
	return new UnknownErrorException(
	    "An unexpected error occurred. Status: " + status + ", Message: " + errorMessage);
    }
  }
}
