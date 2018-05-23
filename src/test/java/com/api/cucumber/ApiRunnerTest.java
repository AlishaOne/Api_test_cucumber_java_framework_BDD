package com.api.cucumber;

//import org.junit.runner.RunWith;

import org.junit.runner.RunWith;

//import org.junit.runner.RunWith;

//import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/com/api/cucumber/featurefile" }, plugin = {
		"html:target/cucumber-html-report", "json:target/cucumber.json",
		"pretty:target/cucumber-pretty.txt" }, glue = { "com.api.cucumber.stepdfn", "com.api.cucumber.hooks" },
//		 tags= {
//				 "@delete",
//				 "@weekly",
//				 "@put",
//				 "@put_file"},
		monochrome = true, 
//		snippets = SnippetType.CAMELCASE,
		dryRun = false)
public class ApiRunnerTest extends TestCase {
	public ApiRunnerTest(String testName) {
		super(testName);
	}
}
