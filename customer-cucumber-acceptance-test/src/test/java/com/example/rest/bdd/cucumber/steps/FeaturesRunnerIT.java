package com.example.rest.bdd.cucumber.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format   = {"json:target/cucumber.json"},
        glue = {"com.example.rest.bdd.cucumber.steps"},
        features = {"src/test/resources"})
public class FeaturesRunnerIT {
}
