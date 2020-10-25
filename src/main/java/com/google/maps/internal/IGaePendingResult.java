package com.google.maps.internal;

import java.io.IOException;

import com.google.maps.PendingResult.Callback;
import com.google.maps.errors.ApiException;

public interface IGaePendingResult<T, R extends ApiResponse<T>> {

	void setCallback(Callback<T> callback);

	T await() throws ApiException, IOException, InterruptedException;

	T awaitIgnoreError();

	void cancel();

}