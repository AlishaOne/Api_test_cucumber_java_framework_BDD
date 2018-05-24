package com.api.cucumber.hooks;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class GeneralHooks {
	// *Create the public method
	// *Use the @Before and @After annotation with the methods
	// *Specify the package in the runner
	// *Inject the Scenario Object in the hook method to get runtime Info of
	// Scenario
	@Before
	public void Setup(Scenario scenario) {
		System.out.println("============Before Hook================");
		System.out.println("Scenario name: " + scenario.getName());
		System.out.println("Scenario status: " + scenario.getStatus());
		//initialize basePath
		baseURI="http://localhost";
		port=8088;
		basePath="/laptop-bag/webapi/api";
		System.err.println("this is constructor...");


	}

	@After
	public void tearDown(Scenario scenario) {
		//clean up resource...
		System.out.println("============After Hook================");
		System.out.println("Scenario name: " + scenario.getName());
		if (!scenario.getStatus().contains("passed")) {
			System.err.println("Scenario status: " + scenario.getStatus());
		} else {
			System.out.println("Scenario status: " + scenario.getStatus());
		}
	}

}
