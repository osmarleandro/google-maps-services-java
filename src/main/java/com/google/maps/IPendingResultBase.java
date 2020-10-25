package com.google.maps;

import java.io.IOException;

import com.google.maps.PendingResult.Callback;
import com.google.maps.errors.ApiException;

interface IPendingResultBase<T, A extends IPendingResultBase<T, A, R>, R extends ApiResponse<T>> {

	void setCallback(Callback<T> callback);

	T await() throws ApiException, InterruptedException, IOException;

	T awaitIgnoreError();

	void cancel();

	/**
	   * The language in which to return results. Note that we often update supported languages so this
	   * list may not be exhaustive.
	   *
	   * @param language The language code, e.g. "en-AU" or "es".
	   * @see <a href="https://developers.google.com/maps/faq#languagesupport">List of supported domain
	   *     languages</a>
	   * @return Returns the request for call chaining.
	   */
	A language(String language);

	/**
	   * A channel to pass with the request. channel is used by Google Maps API for Work users to be
	   * able to track usage across different applications with the same clientID. See <a
	   * href="https://developers.google.com/maps/documentation/business/clientside/quota">Premium Plan
	   * Usage Rates and Limits</a>.
	   *
	   * @param channel String to pass with the request for analytics.
	   * @return Returns the request for call chaining.
	   */
	A channel(String channel);

	/**
	   * Custom parameter. For advanced usage only.
	   *
	   * @param parameter The name of the custom parameter.
	   * @param value The value of the custom parameter.
	   * @return Returns the request for call chaining.
	   */
	A custom(String parameter, String value);

}