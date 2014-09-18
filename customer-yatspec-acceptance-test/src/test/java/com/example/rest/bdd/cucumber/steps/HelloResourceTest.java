package com.example.rest.bdd.cucumber.steps;

import com.example.rest.bdd.WebApp;
import com.example.rest.bdd.config.WebConfig;
import com.example.rest.bdd.cucumber.steps.com.example.rest.bdd.yatspec.config.WebTestConfig;
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
public class HelloResourceTest extends TestState{

    private static MockMvc mockMvc;
    private ResultActions resultActions;

    @BeforeClass
    public static void startServer() throws Exception {
        mockMvc = new WebApp().start(WebConfig.class, WebTestConfig.class).getBean(MockMvc.class);
    }

    @Test
    public void getHomePageReturnsOk() throws Exception {
        when(requested(get("/")));
        then(the(response(status().is(equalTo(200)))));
    }

    private ResultActions response(ResultMatcher matcher) throws Exception {
        return resultActions.andExpect(matcher);
    }

    private ActionUnderTest requested(final MockHttpServletRequestBuilder requestBuilder) {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                resultActions = mockMvc.perform(requestBuilder);
                return capturedInputAndOutputs;
            }
        };
    }

    private static <T> T then(T t) { return t;}
}
