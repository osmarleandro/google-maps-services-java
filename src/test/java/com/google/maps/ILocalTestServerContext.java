package com.google.maps;

import org.json.JSONObject;

public interface ILocalTestServerContext {

	JSONObject requestBody() throws InterruptedException;

	String path() throws InterruptedException;

	void close();

}