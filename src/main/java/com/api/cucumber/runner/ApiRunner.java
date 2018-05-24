package com.api.cucumber.runner;
//import org.junit.runner.RunWith;

import org.junit.runner.RunWith;

//import org.junit.runner.RunWith;

//import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/java/com/api/cucumber/featurefile" },
		plugin = {"html:target/cucumber-html-report" },
		glue = { "com.api.cucumber.stepdfn",
				"com.api.cucumber.hooks" },
		tags = "@delete",
// monochrome = true
 snippets = SnippetType.CAMELCASE
// dryRun = true
)

public class ApiRunner {

}
