/*
 * Copyright 2015 Google Inc. All rights reserved.
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
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.RankBy;

/**
 * A <a href="https://developers.google.com/places/web-service/search#TextSearchRequests">Text
 * Search</a> request.
 */
public class TextSearchRequest
    extends PendingResultBase<PlacesSearchResponse, TextSearchRequest, TextSearchRequest.Response> {

  static final ApiConfig API_CONFIG =
      new ApiConfig("/maps/api/place/textsearch/json")
          .fieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

  public TextSearchRequest(GeoApiContext context) {
    super(context, API_CONFIG, Response.class);
  }

  /**
   * Specifies the text string on which to search, for example: {@code "restaurant"}.
   *
   * @param query The query string to search for.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest query(String query) {
    return param("query", query);
  }

  /**
   * Specifies the latitude/longitude around which to retrieve place information.
   *
   * @param location The location of the center of the search.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest location(LatLng location) {
    return param("location", location);
  }

  /**
   * Region used to influence search results. This parameter will only influence, not fully
   * restrict, search results. If more relevant results exist outside of the specified region, they
   * may be included. When this parameter is used, the country name is omitted from the resulting
   * formatted_address for results in the specified region.
   *
   * @param region The ccTLD two-letter code of the region.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest region(String region) {
    return param("region", region);
  }

  /**
   * Specifies the distance (in meters) within which to bias place results.
   *
   * @param radius The radius of the search bias.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest radius(int radius) {
    if (radius > 50000) {
      throw new IllegalArgumentException("The maximum allowed radius is 50,000 meters.");
    }
    return param("radius", String.valueOf(radius));
  }

  /**
   * Restricts to places that are at least this price level.
   *
   * @param priceLevel The minimum price level to restrict results with.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest minPrice(PriceLevel priceLevel) {
    return param("minprice", priceLevel);
  }

  /**
   * Restricts to places that are at most this price level.
   *
   * @param priceLevel The maximum price leve to restrict results with.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest maxPrice(PriceLevel priceLevel) {
    return param("maxprice", priceLevel);
  }

  /**
   * Specifies one or more terms to be matched against the names of places, separated with space
   * characters.
   *
   * @param name The name to search for.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest name(String name) {
    return param("name", name);
  }

  /**
   * Restricts to only those places that are open for business at the time the query is sent.
   *
   * @param openNow Whether to restrict this search to open places.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest openNow(boolean openNow) {
    return param("opennow", String.valueOf(openNow));
  }

  /**
   * Returns the next 20 results from a previously run search. Setting pageToken will execute a
   * search with the same parameters used previously — all parameters other than pageToken will be
   * ignored.
   *
   * @param nextPageToken A {@code pageToken} from a prior result.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest pageToken(String nextPageToken) {
    return param("pagetoken", nextPageToken);
  }

  /**
   * Specifies the order in which results are listed.
   *
   * @param ranking The rank by method.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest rankby(RankBy ranking) {
    return param("rankby", ranking);
  }

  /**
   * Restricts the results to places matching the specified type.
   *
   * @param type The type of place to restrict the results with.
   * @return Returns this {@code TextSearchRequest} for call chaining.
   */
  public TextSearchRequest type(PlaceType type) {
    return param("type", type);
  }

  @Override
  protected void validateRequest() {

    // All other parameters are ignored if pagetoken is specified.
    if (params().containsKey("pagetoken")) {
      return;
    }

    if (!params().containsKey("query") && !params().containsKey("type")) {
      throw new IllegalArgumentException(
          "Request must contain 'query' or a 'pageToken'. If a 'type' is specified 'query' becomes optional.");
    }

    if (params().containsKey("location") && !params().containsKey("radius")) {
      throw new IllegalArgumentException(
          "Request must contain 'radius' parameter when it contains a 'location' parameter.");
    }
  }

  public static class Response implements ApiResponse<PlacesSearchResponse> {

    public String status;
    public String htmlAttributions[];
    public PlacesSearchResult results[];
    public String nextPageToken;
    public String errorMessage;

    @Override
    public boolean successful() {
      return "OK".equals(status) || "ZERO_RESULTS".equals(status);
    }

    @Override
    public PlacesSearchResponse getResult() {
      PlacesSearchResponse result = new PlacesSearchResponse();
      result.htmlAttributions = htmlAttributions;
      result.results = results;
      result.nextPageToken = nextPageToken;
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
}
