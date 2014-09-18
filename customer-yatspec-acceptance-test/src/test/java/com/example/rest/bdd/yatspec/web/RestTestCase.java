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
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RunWith(SpecRunner.class)
public class RestTestCase extends TestState {

    private static MockMvc mockMvc;
    private ResultActions resultActions;

    @BeforeClass
    public static void startServer() throws Exception {
        mockMvc = new WebApp().start(WebConfig.class, WebTestConfig.class).getBean(MockMvc.class);
    }

    protected ResultActions response(ResultMatcher matcher) throws Exception {
        return resultActions.andExpect(matcher);
    }

    protected ActionUnderTest requested(final MockHttpServletRequestBuilder requestBuilder) {
        return new ActionUnderTest() {
            @Override
            public CapturedInputAndOutputs execute(InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws Exception {
                resultActions = mockMvc.perform(requestBuilder);
                return capturedInputAndOutputs;
            }
        };
    }

    public static <T> T then(T t) { return t;}

    public static <T> T to(T t) { return t;}
}
