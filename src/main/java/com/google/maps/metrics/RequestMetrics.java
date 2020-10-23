package com.google.maps.metrics;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.internal.ApiResponse;

/**
 * A type to report common metrics shared among all request types.
 *
 * <p>If a request retries, there will be multiple calls to all methods below. Ignore any endRequest
 * after the first one. For example:
 *
 * <ol>
 *   <li>constructor - request starts
 *   <li>startNetwork / endNetwork - original request
 *   <li>startNetwork / endNetwork - retried request
 *   <li>endRequest - request finished (retry)
 *   <li>endRequest - request finished (original)
 * </ol>
 *
 * <p>The following metrics can be computed: Total queries, successful queries, total latency,
 * network latency
 */
public interface RequestMetrics {

  void startNetwork();

  void endNetwork();

  void endRequest(Exception exception, int httpStatusCode, long retryCount);

public default <T, R extends ApiResponse<T>> PendingResult<T> getWithPath(
      Class<R> clazz, FieldNamingPolicy fieldNamingPolicy, String hostName, String path, boolean canUseClientId, String encodedPath, GeoApiContext geoApiContext) {
    geoApiContext.checkContext(canUseClientId);
    if (!encodedPath.startsWith("&")) {
      throw new IllegalArgumentException("encodedPath must start with &");
    }

    StringBuilder url = new StringBuilder(path);
    if (canUseClientId && geoApiContext.clientId != null) {
      url.append("?client=").append(geoApiContext.clientId);
    } else {
      url.append("?key=").append(geoApiContext.apiKey);
    }
    url.append(encodedPath);

    if (canUseClientId && geoApiContext.urlSigner != null) {
      String signature = geoApiContext.urlSigner.getSignature(url.toString());
      url.append("&signature=").append(signature);
    }

    if (geoApiContext.baseUrlOverride != null) {
      hostName = geoApiContext.baseUrlOverride;
    }

    return geoApiContext.requestHandler.handle(
        hostName,
        url.toString(),
        GeoApiContext.USER_AGENT,
        geoApiContext.experienceIdHeaderValue,
        clazz,
        fieldNamingPolicy,
        geoApiContext.errorTimeout,
        geoApiContext.maxRetries,
        geoApiContext.exceptionsAllowedToRetry,
        this);
  }
}
