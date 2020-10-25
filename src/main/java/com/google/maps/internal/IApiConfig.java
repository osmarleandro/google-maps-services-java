package com.google.maps.internal;

import com.google.gson.FieldNamingPolicy;

public interface IApiConfig {

	IApiConfig fieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy);

	IApiConfig hostName(String hostName);

	IApiConfig supportsClientId(boolean supportsClientId);

	IApiConfig requestVerb(String requestVerb);

}