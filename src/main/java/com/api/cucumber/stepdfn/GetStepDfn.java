package com.api.cucumber.stepdfn;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.*;

public class GetStepDfn {

	private RequestSpecification data;
	private Response res, resWithId;
	// private String id = Helper.genaratedUniqueId(1000);

	@Given("^accept json format$")
	public void acceptJsonFormat() throws Throwable {
		data = given().accept(ContentType.JSON);
	}

	@Given("^accept xml format$")
	public void acceptXmlFormat() throws Throwable {
		data = given().accept(ContentType.XML);

	}

	@When("^I do Get request$")
	public void iDoGetRequest() throws Throwable {
		res = data.get("/all");
		System.out.println("response  ==>" + res.getBody().asString());
	}

	@When("^I do Get request with id \"([^\"]*)\"$")
	public void iDoGetRequestWithIdSomething(String id) throws Throwable {
		resWithId = data.get("/find/" + id);
		System.out.println("response with id ==>" + resWithId.getBody().asString());
	}

	@Then("^response status code should be \"([^\"]*)\"$")
	public void responseStatusCodeShouldBeSomething(String strArg1) throws Throwable {
		res.then().assertThat().statusCode(HttpStatus.SC_OK);
	}

	@Then("^response status code should be \"([^\"]*)\" and response json body Id should be \"([^\"]*)\"$")
	public void responseStatusCodeShouldBeSomethingAndResponseJsonBodyIdShouldBeSomething(int code, String id)
			throws Throwable {
		resWithId.then().assertThat().statusCode(HttpStatus.SC_OK);
		resWithId.then().assertThat().statusCode(code);
		resWithId.then().assertThat().body("Id", equalTo(Integer.parseInt(id)));

	}

	@Then("^response status code should be \"([^\"]*)\" and response xml body Id should be \"([^\"]*)\"$")
	public void responseStatusCodeShouldBeSomethingAndResponseXmlBodyIdShouldBeSomething(int code, String id)
			throws Throwable {
		resWithId.then().assertThat().statusCode(HttpStatus.SC_OK);
		resWithId.then().assertThat().statusCode(code);
		resWithId.then().assertThat().body("Laptop.Id", equalTo(id));
	}

	@And("^response body should be json format as body start with \"([^\"]*)\" and end with \"([^\"]*)\"$")
	public void responseBodyShouldBeJsonFormatAsBodyStartWithSomethingAndEndWithSomething(String start, String end)
			throws Throwable {
		assertTrue(
				resWithId.andReturn().asString().startsWith(start) && resWithId.andReturn().asString().endsWith(end));
	}

	@And("^response body should be xml format as body contains \"([^\"]*)\"$")
	public void responseBodyShouldBeXmlFormatAsBodyContainsSomething(String str) throws Throwable {
		System.err.println("string is " + str);
		assertTrue(resWithId.andReturn().asString().contains(str));
	}

}
