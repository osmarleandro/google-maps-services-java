package com.google.maps;

public interface IPhotoRequest {

	/**
	   * Sets the photoReference for this request.
	   *
	   * @param photoReference A string identifier that uniquely identifies a photo. Photo references
	   *     are returned from either a Place Search or Place Details request.
	   * @return Returns the configured PhotoRequest.
	   */
	IPhotoRequest photoReference(String photoReference);

	/**
	   * Sets the maxHeight for this request.
	   *
	   * @param maxHeight The maximum desired height, in pixels, of the image returned by the Place
	   *     Photos service.
	   * @return Returns the configured PhotoRequest.
	   */
	IPhotoRequest maxHeight(int maxHeight);

	/**
	   * Sets the maxWidth for this request.
	   *
	   * @param maxWidth The maximum desired width, in pixels, of the image returned by the Place Photos
	   *     service.
	   * @return Returns the configured PhotoRequest.
	   */
	IPhotoRequest maxWidth(int maxWidth);

}