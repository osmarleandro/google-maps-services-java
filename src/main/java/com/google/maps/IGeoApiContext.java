package com.google.maps;

import com.google.maps.internal.HttpHeaders;

public interface IGeoApiContext {

	/**
	   * Sets the value for the HTTP header field name {@link HttpHeaders#X_GOOG_MAPS_EXPERIENCE_ID} to
	   * be used on subsequent API calls. Calling this method with {@code null} is equivalent to calling
	   * {@link #clearExperienceId()}.
	   *
	   * @param experienceId The experience ID if set, otherwise null
	   */
	void setExperienceId(String... experienceId);

	/** @return Returns the experience ID if set, otherwise, null */
	String getExperienceId();

	/**
	   * Clears the experience ID if set the HTTP header field {@link
	   * HttpHeaders#X_GOOG_MAPS_EXPERIENCE_ID} will be omitted from subsequent calls.
	   */
	void clearExperienceId();

	/**
	   * Shut down this GeoApiContext instance, reclaiming resources. After shutdown() has been called,
	   * no further queries may be done against this instance.
	   */
	void shutdown();

}