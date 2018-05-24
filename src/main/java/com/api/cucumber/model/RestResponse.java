package com.api.cucumber.model;

import java.io.StringWriter;

import org.json.JSONException;
import org.json.JSONObject;


public class RestResponse {

	private int statusCode;
	private String responseBody;
	public int getStatusCode() {
		return statusCode;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public RestResponse(int statusCode,String responseBody) {
		this.statusCode=statusCode;
		this.responseBody=responseBody;
	}
	@Override
	public String toString() {
		return "RestResponse [statusCode=" + statusCode + ", responseBody=" + responseBody + "]";
	}

	


}
