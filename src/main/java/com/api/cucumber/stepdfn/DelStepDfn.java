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

public class DelStepDfn {
	private String id = Helper.genaratedUniqueId(1000);;
	private JSONObject jsonString;

	// use Dependency Injection to solve data dependency
	private RestForCucumberDJ dataDj;

	public DelStepDfn(RestForCucumberDJ restCuDj) {
		this.dataDj = restCuDj;
	}

	@Given("^latop data with detail as in file \"([^\"]*)\"$")
	public void latopDataWithDetailAsInFile(String request_file) throws Throwable {
		System.out.println("-------------" + request_file + "--------------------");
		String oldId = Helper.readDynamicDataFromFile(request_file).get("Id").toString();
		Helper.writeDynamicDataToFile(request_file, oldId, id);
		jsonString = Helper.readDynamicDataFromFile(request_file);

		dataDj.data = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and()
				.body(jsonString.toString());
		System.out.println("------new  file of json: ------" + jsonString.toString());

	}

	@When("^I do post request for creating data$")
	public void iDoPostRequestForCreatingData() {
		dataDj.resp = dataDj.data.post("/add");
		System.err.println("this is when response json==>" + dataDj.resp.getBody().asString());
	}

	@Then("^response body should be correct as in file \"([^\"]*)\"$")
	public void responseBodyShouldBeCorrectAsInFileSomething(String postresponsefile) throws Throwable {
		jsonString = Helper.readDynamicDataFromFile(postresponsefile);
		System.out.println("---file of post json for deleteing:--- " + jsonString);
		System.out.println("---file of post json BrandName:--- " + jsonString.get("BrandName"));

		dataDj.resp.then().body("BrandName", containsString(jsonString.get("BrandName").toString()), "Id",
				equalTo(Integer.parseInt(id)));

	}

	@And("^response post status code should be right as in file \"([^\"]*)\"$")
	public void responsePostStatusCodeShouldBeRightAsInFileSomething(String postresponsestatus) throws Throwable {
		dataDj.resp.then().assertThat().statusCode(equalTo(Integer.parseInt(postresponsestatus)));

	}

	@Given("^latop json data for delete as in file \"([^\"]*)\"$")
	public void latopJsonDataForDeleteAsInFile(String delete_file) throws Throwable {
		System.out.println("-------------" + delete_file + "--------------------");
		String oldId = Helper.readDynamicDataFromFile(delete_file).get("Id").toString();
		Helper.writeDynamicDataToFile(delete_file, oldId, id);
		jsonString = Helper.readDynamicDataFromFile(delete_file);

		dataDj.data = given().contentType(ContentType.JSON).and().body(jsonString.toString());
		System.out.println("------delete  file of json: ------" + jsonString.toString());
	}

	@When("^I do delete request with id as\"([^\"]*)\"$")
	public void iDoDeleteRequestWithIdAs(String idValue) throws Throwable {
		System.out.println("------id is: ------" + id);
		if (idValue.equals("get_a_value")) {
			dataDj.resp = dataDj.data.delete("/delete/" + id);
		} else {
			dataDj.resp = dataDj.data.delete("/delete/" + Integer.parseInt(idValue));
		}

	}

	@And("^response delete status code should be right as \"([^\"]*)\"$")
	public void responseDeleteStatusCodeShouldBeRightAsSomething(String del_response_status) throws Throwable {
		dataDj.resp.then().assertThat().statusCode(equalTo(Integer.parseInt(del_response_status)));
		System.out.println("****************" + dataDj.resp.andReturn().getStatusLine() + "****************");
	}

	@When("^I do get request with same id as delete as \"([^\"]*)\"$")
	public void iDoGetRequestWithSameIdAsDeleteAs(String id) throws Throwable {
		dataDj.resp = dataDj.data.get("/find/" + id);
	}

	@Then("^response get status code should be right as \"([^\"]*)\"$")
	public void responseGetStatusCodeShouldBeRightAsSomething(String statusCode) throws Throwable {
		dataDj.resp.then().assertThat().statusCode(equalTo(Integer.parseInt(statusCode)));
	}

}
