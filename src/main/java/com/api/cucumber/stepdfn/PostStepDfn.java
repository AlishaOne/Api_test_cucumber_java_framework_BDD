package com.api.cucumber.stepdfn;

import cucumber.api.PendingException;
import cucumber.api.Transform;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpStatus;

import com.api.cucumber.transform.TransformDataList;
import com.api.cucumber.util.Helper;
import com.api.cucumber.util.RestForCucumberDJ;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PostStepDfn {

	// private BaseClass object=new BaseClass();

	// private RequestSpecification data;
	// private Response res;
	private String id = Helper.genaratedUniqueId(1000);
	// private String url = "http://localhost:8088/laptop-bag/webapi/api/add";

	// use Dependency Injection to solve data dependency
	private RestForCucumberDJ dataDj;

	public PostStepDfn(RestForCucumberDJ restCuDj) {
		this.dataDj = restCuDj;
	}

	@Given("^latop json data BrandName as \"([^\"]*)\" feature as \"([^\"]*)\" LaptopName as \"([^\"]*)\" and header accept json$")
	public void latopJsonDataBrandNameAsSomethingFeatureAsSomethingAndHeaderAcceptJson(String brandName, String feature,
			String laptopName) {
		String body = Helper.getJsonBody(brandName, id, Arrays.asList(feature.split(",")), laptopName);
		dataDj.data = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(body);
		System.err.println("this is given latop json data from getJsonBody().");
		// access feature,scenario info
		// System.out.println("****constructor injection*****"+object.getFeatureName());

	}

	@Given("^latop json data as empty body \"([^\"]*)\" and header accept json$")
	public void latopJsonDataAsEmptyBodySomethingAndHeaderAcceptJson(String body) {
		body = "{}";
		dataDj.data = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(body);
		System.err.println("this is given latop json data==>" + dataDj.data);
	}

	@Given("^latop xml data as \"([^\"]*)\" and header accept xml$")
	public void latopXmlDataAsSomethingAndHeaderAcceptXml(String body) {
		body = Helper.xmlBody(id);
		dataDj.data = given().accept(ContentType.XML).and().contentType(ContentType.XML).and().body(body);
		System.err.println("this is given latop json data ==>" + dataDj.data);
	}

	@Given("^latop json data as \"([^\"]*)\" and header accept json$")
	public void latopJsonDataAsSomethingAndHeaderAcceptJson(@Transform(TransformDataList.class) List<String> list) {
		// for debugging
		for (String str : list) {
			System.out.println("*********** " + str + " ***********");
		}

		String body = Helper.getJsonBody(list.get(0), id, list.subList(1, 4), list.get(5));
		dataDj.data = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(body);
		System.err.println("this is given latop json transformer data from getJsonBody()." + dataDj.data.toString());

	}

	@When("^I do post request$")
	public void iDoPostRequest() {
		dataDj.resp = dataDj.data.post("/add");
		System.err.println("this is when response json==>" + dataDj.resp.getBody().asString());
	}

	@Then("^response body whould be correct as BrandName is\"([^\"]*)\" feature as \"([^\"]*)\" LaptopName as \"([^\"]*)\"$")
	public void responseBodyWhouldBeCorrectAsBrandNameIssomethingFeatureAsSomething(String brandName, List<String> list,
			String laptopName) {
		dataDj.resp.then().body("BrandName", containsString(brandName), "Id", equalTo(Integer.parseInt(id)),
				"Features.Feature", hasItem(list.get(0)), "LaptopName", containsString(laptopName));
	}

	@Then("^response body whould be correct as \"([^\"]*)\"$")
	public void responseBodyWhouldBeCorrectAsSomething(List<String> list) {
		// for (String str : list) {
		// System.out.println("*********** "+str+" ***********");
		// System.out.println("*********** " + list.get(5) + " ***********");
		// }
		//
		dataDj.resp.then().body("BrandName", containsString(list.get(0)), "Id", equalTo(Integer.parseInt(id)),
				"Features.Feature", hasItem(list.get(1)), "LaptopName", equalTo(list.get(5)));

	}

	@Then("^response body whould be correct as xml \"([^\"]*)\"$")
	public void responseBodyWhouldBeCorrectAsXmlSomething(String str) {
		dataDj.resp.then().body("Laptop.BrandName", containsString(str), "Laptop.Id", equalTo(id));

	}

	@Then("^response status code should be bad request \"([^\"]*)\"$")
	public void responseStatusCodeShouldBeBadRequestSomething(String strArg1) {
		dataDj.resp.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	@And("^response status code should be right as \"([^\"]*)\"$")
	public void responseStatusCodeShouldBeRightAsSomething(String strArg1) throws Throwable {
		dataDj.resp.then().assertThat().statusCode(HttpStatus.SC_OK);
		System.err.println(strArg1);
	}

}
