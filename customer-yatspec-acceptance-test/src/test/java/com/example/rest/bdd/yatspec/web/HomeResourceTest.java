package com.example.rest.bdd.yatspec.web;

import com.example.rest.bdd.WebApp;
import com.example.rest.bdd.config.WebConfig;
import com.example.rest.bdd.yatspec.config.WebTestConfig;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import com.googlecode.yatspec.state.givenwhenthen.InterestingGivens;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.googlecode.yatspec.state.givenwhenthen.SyntacticSugar.the;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpecRunner.class)
public class HomeResourceTest extends RestTestCase{

    @Test
    public void getHomePageReturnsOk() throws Exception {
        when(requested(get("/")));
        then(the(response(status().isOk())));
    }
}
