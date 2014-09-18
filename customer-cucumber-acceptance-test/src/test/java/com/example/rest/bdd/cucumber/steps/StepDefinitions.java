package com.example.rest.bdd.cucumber.steps;

import com.example.rest.bdd.cucumber.config.WebTestConfig;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ContextConfiguration(classes={WebTestConfig.class})
public class StepDefinitions {

    @Autowired
    private MockMvc mockMvc;
    private ResultActions resultActions;


    @When("^I get the home page$")
    public void i_get_the_home_pag() throws Throwable {
        resultActions = mockMvc.perform(get("/"));
    }

    @Then("^the response status is (\\d+)$")
    public void the_response_status_is(int statusCode) throws Throwable {
        resultActions.andExpect(status().is(equalTo(statusCode)));
    }

}
