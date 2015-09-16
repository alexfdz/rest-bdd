package com.example.rest.bdd.cucumber.steps;

import com.example.rest.bdd.cucumber.config.WebTestConfig;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ContextConfiguration(classes = WebTestConfig.class)
public class StepDefinitions {

    @Autowired
    private MockMvc mockMvc;
    private ResultActions resultActions;

    @Then("^the response status is (\\d+)$")
    public void the_response_status_is(int statusCode) throws Throwable {
        resultActions.andExpect(status().is(equalTo(statusCode)));
    }

    @When("^requested get \"(.*?)\"")
    public void requested_get(String uri) throws Throwable {
        resultActions = mockMvc.perform(get(uri));
    }

    @When("^requested accept media type \"(.*?)\" for get \"(.*?)\"")
    public void requested_get_accept(String mediaType, String uri) throws Throwable {
        resultActions = mockMvc.perform(get(uri).accept(parseMediaType(mediaType)));
    }

    @Then("^the response string not is empty string$")
    public void response_string_not_is_empty_string() throws Throwable {
        resultActions.andExpect(content().string(not("")));
    }

    @Then("^the response media type is \"(.*?)\"$")
    public void response_media_type_is(String expectedMediaType) throws Throwable {
        resultActions.andExpect(content().contentType(expectedMediaType));
    }
}
