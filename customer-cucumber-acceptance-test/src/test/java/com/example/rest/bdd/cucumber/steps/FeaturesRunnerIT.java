package com.example.rest.bdd.cucumber.steps;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(
		format = {"html:target/cucumber-html-report" },
		glue={"com.example.rest.bdd.cucumber.steps"})
public class FeaturesRunnerIT {

}
