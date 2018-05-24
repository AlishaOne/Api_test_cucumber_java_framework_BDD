package com.api.cucumber.stepdfn;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.apache.http.entity.FileEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import com.api.cucumber.model.LaptopBag;
import com.api.cucumber.util.Helper;
import com.api.cucumber.util.RestForCucumberDJ;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import org.apache.http.entity.*;

public class PutStepDfn {
	// private String id = Helper.genaratedUniqueId(1000);
	// private String url = "http://localhost:8088/laptop-bag/webapi/api/add";
	private String id = Helper.genaratedUniqueId(1000);;
	private JSONObject jsonString;

	// use Dependency Injection to solve data dependency
	private RestForCucumberDJ dataDj;

	public PutStepDfn(RestForCucumberDJ restCuDj) {
		this.dataDj = restCuDj;
	}

	@Given("^latop json data for update BrandName as \"([^\"]*)\" feature as \"([^\"]*)\" LaptopName as \"([^\"]*)\" and header accept json$")
	public void latopJsonDataForUpdateBrandNameAsSomethingFeatureAsSomethingAndHeaderAcceptJson(String brandName,
			List<String> list, String laptopName) throws Throwable {
		id = dataDj.resp.thenReturn().jsonPath().getString("Id");
		System.out.println("id is :" + id);
		dataDj.data = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and()
				.body(Helper.getJsonBody(brandName, id, list, laptopName));
	}

	@Given("^latop xml updte data BrandName as \"([^\"]*)\" and header accept xml$")
	public void latopXmlUpdteDataBrandNameAsSomethingAndHeaderAcceptXml(String xmlStr) throws Throwable {
		id = dataDj.resp.thenReturn().xmlPath().getString("Laptop.Id");
		dataDj.data = given().accept(ContentType.XML).and().contentType(ContentType.XML).and()
				.body(Helper.xmlBodyUpdate(id));

	}

	@When("^I do put request$")
	public void iDoPutRequest() throws Throwable {
		dataDj.resp = dataDj.data.put("/update");
		System.err.println("this is when response json update data==>" + dataDj.resp.getBody().asString());
	}

	@Then("^response update body whould be correct as BrandName is\"([^\"]*)\" feature as \"([^\"]*)\" LaptopName as \"([^\"]*)\"$")
	public void responseUpdateBodyWhouldBeCorrectAsBrandNameIssomethingFeatureAsSomething(String brandName,
			List<String> feature, String laptopName) throws Throwable {
		dataDj.resp.then().assertThat().body("BrandName", equalTo(brandName), "LaptopName", equalTo(laptopName),
				"Features.Feature", hasItem(feature.get(0)));
	}

	@Then("^response update body should be correct as xml \"([^\"]*)\"$")
	public void responseUpdateBodyShouldBeCorrectAsXmlSomething(String xmlStr) throws Throwable {
		dataDj.resp.then().body("Laptop.BrandName", containsString(xmlStr), "Laptop.Id", equalTo(id));
	}

	@And("^response post status code should be right as \"([^\"]*)\"$")
	public void responsePostStatusCodeShouldBeRightAsSomething(String strArg1) throws Throwable {
		dataDj.resp.then().assertThat().statusCode(HttpStatus.SC_OK);
	}

	@And("^response update status code should be right as \"([^\"]*)\"$")
	public void responseUpdateStatusCodeShouldBeRightAsSomething(String strArg1) throws Throwable {
		dataDj.resp.then().assertThat().statusCode(HttpStatus.SC_OK);
	}

	@Given("^latop json data detail as in file \"([^\"]*)\"$")
	public void latopJsonDataDetailAsInFileSomething(String path) throws Throwable {

		String oldId = Helper.readDynamicDataFromFile(path).get("Id").toString();
		Helper.writeDynamicDataToFile(path, oldId, id);
		jsonString = Helper.readDynamicDataFromFile(path);

		dataDj.data = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and()
				.body(jsonString.toString());
		System.out.println("------new  file of json: ------" + jsonString.toString());

	}

	@Given("^latop json data for update as in file \"([^\"]*)\"$")
	public void latopJsonDataForUpdateAsInFileSomething(String path) throws Throwable {
		String oldId = Helper.readDynamicDataFromFile(path).get("Id").toString();
		Helper.writeDynamicDataToFile(path, oldId, id);
		jsonString = Helper.readDynamicDataFromFile(path);
		dataDj.data = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and()
				.body(jsonString.toString());
		System.out.println("file of update json: " + jsonString.toString());
	}

	@Then("^response body whould be correct as in file \"([^\"]*)\"$")
	public void responseBodyWhouldBeCorrectAsInFileSomething(String path) throws Throwable {
		// id=Helper.readDynamicDataFromFileWithKey(path, "Id");
		// id = Helper.readDynamicDataFromFile(path).get("Id").toString();
		jsonString = Helper.readDynamicDataFromFile(path);
		System.out.println("---file of post json:---from--put-- " + jsonString);
		System.out.println("---file of post json BrandName:--- " + jsonString.get("BrandName"));

		dataDj.resp.then().body("BrandName", containsString(jsonString.get("BrandName").toString()), "Id",
				equalTo(Integer.parseInt(id)));
	}

	@Then("^response body whould be correct \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" as in file \"([^\"]*)\"$")
	public void responseBodyWhouldBeCorrectSomethingSomethingSomethingSomethingAsInFileSomething(String brandName,
			String featuresFeature, String passId, String laptopName, String path) throws Throwable {
		// id = Helper.readDynamicDataFromFile(path).get("Id").toString();
		jsonString = Helper.readDynamicDataFromFile(path);
		System.out.println("---file of post json:--- " + jsonString);
		System.out.println("---file of post json BrandName:--- " + jsonString.get("BrandName"));

		dataDj.resp.then().body(brandName, containsString(jsonString.get(brandName).toString()), passId,
				equalTo(Integer.parseInt(id)));

	}

	@Then("^response update body whould be correct as in file \"([^\"]*)\"$")
	public void responseUpdateBodyWhouldBeCorrectAsInFileSomething(String path) throws Throwable {
		// id=Helper.readDynamicDataFromFileWithKey(path, "Id");
		// String feats=Helper.readDynamicDataFromFileWithKey(path, "Features");
		// id = Helper.readDynamicDataFromFile(path).get("Id").toString();
		String feats = Helper.readDynamicDataFromFile(path).get("Features").toString();
		JSONObject featsObj = new JSONObject(feats);
		JSONArray featArray = new JSONArray(featsObj.get("Feature").toString());
		System.out.println("---feat:--- " + featArray.get(0));

		jsonString = Helper.readDynamicDataFromFile(path);
		dataDj.resp.then().body("BrandName", containsString(jsonString.get("BrandName").toString()), "Features.Feature",
				hasItems(featArray.get(0), featArray.get(featArray.length() - 1)), "Id", equalTo(Integer.parseInt(id)));
	}

}
