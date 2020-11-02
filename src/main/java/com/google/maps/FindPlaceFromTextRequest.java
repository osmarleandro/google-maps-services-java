/*
 * Copyright 2018 Google Inc. All rights reserved.
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
import com.google.maps.internal.StringJoin;
import com.google.maps.internal.StringJoin.UrlValue;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;

public class FindPlaceFromTextRequest
    extends PendingResultBase<
        FindPlaceFromText, FindPlaceFromTextRequest, FindPlaceFromTextRequest.Response> {

  static final ApiConfig API_CONFIG =
      new ApiConfig("/maps/api/place/findplacefromtext/json")
          .fieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
          .supportsClientId(false);

  public FindPlaceFromTextRequest(GeoApiContext context) {
    super(context, API_CONFIG, Response.class);
  }

  public enum InputType implements UrlValue {
    TEXT_QUERY("textquery"),
    PHONE_NUMBER("phonenumber");

    private final String inputType;

    InputType(final String inputType) {
      this.inputType = inputType;
    }

    @Override
    public String toUrlValue() {
      return this.inputType;
    }
  }

  /**
   * The text input specifying which place to search for (for example, a name, address, or phone
   * number).
   *
   * @param input The text input.
   * @return Returns {@code FindPlaceFromTextRequest} for call chaining.
   */
  public FindPlaceFromTextRequest input(String input) {
    return param("input", input);
  }

  /**
   * The type of input.
   *
   * @param inputType The input type.
   * @return Returns {@code FindPlaceFromTextRequest} for call chaining.
   */
  public FindPlaceFromTextRequest inputType(InputType inputType) {
    return param("inputtype", inputType);
  }

  /**
   * The fields specifying the types of place data to return.
   *
   * @param fields The fields to return.
   * @return Returns {@code FindPlaceFromTextRequest} for call chaining.
   */
  public FindPlaceFromTextRequest fields(FieldMask... fields) {
    return param("fields", StringJoin.join(',', fields));
  }

  /**
   * Prefer results in a specified area, by specifying either a radius plus lat/lng, or two lat/lng
   * pairs representing the points of a rectangle.
   *
   * @param locationBias The location bias for this request.
   * @return Returns {@code FindPlaceFromTextRequest} for call chaining.
   */
  public FindPlaceFromTextRequest locationBias(LocationBias locationBias) {
    return param("locationbias", locationBias);
  }

  @Override
  protected void validateRequest() {
    if (!params().containsKey("input")) {
      throw new IllegalArgumentException("Request must contain 'input'.");
    }
    if (!params().containsKey("inputtype")) {
      throw new IllegalArgumentException("Request must contain 'inputType'.");
    }
  }

  public static class Response implements ApiResponse<FindPlaceFromText> {

    public String status;
    public PlacesSearchResult candidates[];
    public String errorMessage;

    @Override
    public boolean successful() {
      return "OK".equals(status) || "ZERO_RESULTS".equals(status);
    }

    @Override
    public FindPlaceFromText getResult() {
      FindPlaceFromText result = new FindPlaceFromText();
      result.candidates = candidates;
      return result;
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

  public enum FieldMask implements UrlValue {
    BUSINESS_STATUS("business_status"),
    FORMATTED_ADDRESS("formatted_address"),
    GEOMETRY("geometry"),
    ICON("icon"),
    ID("id"),
    NAME("name"),
    OPENING_HOURS("opening_hours"),
    @Deprecated
    PERMANENTLY_CLOSED("permanently_closed"),
    PHOTOS("photos"),
    PLACE_ID("place_id"),
    PRICE_LEVEL("price_level"),
    RATING("rating"),
    TYPES("types");

    private final String field;

    FieldMask(final String field) {
      this.field = field;
    }

    @Override
    public String toUrlValue() {
      return field;
    }
  }

  public interface LocationBias extends UrlValue {}

  public static class LocationBiasIP implements LocationBias {
    @Override
    public String toUrlValue() {
      return "ipbias";
    }
  }

  public static class LocationBiasPoint implements LocationBias {
    private final LatLng point;

    public LocationBiasPoint(LatLng point) {
      this.point = point;
    }

    @Override
    public String toUrlValue() {
      return "point:" + point.toUrlValue();
    }
  }

  public static class LocationBiasCircular implements LocationBias {
    private final LatLng center;
    private final int radius;

    public LocationBiasCircular(LatLng center, int radius) {
      this.center = center;
      this.radius = radius;
    }

    @Override
    public String toUrlValue() {
      return "circle:" + radius + "@" + center.toUrlValue();
    }
  }

  public static class LocationBiasRectangular implements LocationBias {
    private final LatLng southWest;
    private final LatLng northEast;

    public LocationBiasRectangular(LatLng southWest, LatLng northEast) {
      this.southWest = southWest;
      this.northEast = northEast;
    }

    @Override
    public String toUrlValue() {
      return "rectangle:" + southWest.toUrlValue() + "|" + northEast.toUrlValue();
    }
  }
}
