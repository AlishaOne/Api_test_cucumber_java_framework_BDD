package com.api.cucumber.util;

import org.junit.BeforeClass;

import cucumber.api.java.Before;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

/*
 * create url-->http://localhost:8088/laptop-bag/webapi/api
*/
public class BaseClass {
	// for running by junit
	@BeforeClass
	public static void setUp() {
		baseURI = "http://localhost";
		port = 8088;
		basePath = "/laptop-bag/webapi/api";

	}

	private String featureName;
	private String scenarioName;

	public String getFeatureName() {
		return featureName;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	// create constructor for base configuring
	public BaseClass() {
		baseURI = "http://localhost";
		port = 8088;
		basePath = "/laptop-bag/webapi/api";
		System.err.println("this is constructor...");
		
		this.featureName="BDD";
		this.scenarioName="Scenario";
	}

}
