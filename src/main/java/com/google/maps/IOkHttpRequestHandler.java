package com.google.maps;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.ExceptionsAllowedToRetry;
import com.google.maps.metrics.RequestMetrics;

public interface IOkHttpRequestHandler {

	<T, R extends ApiResponse<T>> PendingResult<T> handle(String hostName, String url, String userAgent,
			String experienceIdHeaderValue, Class<R> clazz, FieldNamingPolicy fieldNamingPolicy, long errorTimeout,
			Integer maxRetries, ExceptionsAllowedToRetry exceptionsAllowedToRetry, RequestMetrics metrics);

	<T, R extends ApiResponse<T>> PendingResult<T> handlePost(String hostName, String url, String payload,
			String userAgent, String experienceIdHeaderValue, Class<R> clazz, FieldNamingPolicy fieldNamingPolicy,
			long errorTimeout, Integer maxRetries, ExceptionsAllowedToRetry exceptionsAllowedToRetry,
			RequestMetrics metrics);

	void shutdown();

}