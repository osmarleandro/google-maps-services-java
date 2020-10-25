package com.google.maps.internal;

import java.io.IOException;

import com.google.maps.PendingResult.Callback;
import com.google.maps.errors.ApiException;

import okhttp3.Call;
import okhttp3.Response;

public interface IOkHttpPendingResult<T, R extends ApiResponse<T>> {

	void setCallback(Callback<T> callback);

	T await() throws ApiException, IOException, InterruptedException;

	T awaitIgnoreError();

	void cancel();

	void onFailure(Call call, IOException ioe);

	void onResponse(Call call, Response response) throws IOException;

}