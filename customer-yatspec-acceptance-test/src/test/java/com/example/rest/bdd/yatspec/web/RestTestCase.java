package com.example.rest.bdd.yatspec.web;

import com.example.rest.bdd.WebApp;
import com.example.rest.bdd.config.WebConfig;
import com.example.rest.bdd.yatspec.config.WebTestConfig;
import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.plugin.sequencediagram.ByNamingConventionMessageProducer;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramMessage;
import com.googlecode.yatspec.plugin.sequencediagram.SvgWrapper;
import com.googlecode.yatspec.rendering.html.DontHighlightRenderer;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import com.googlecode.yatspec.state.givenwhenthen.InterestingGivens;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.googlecode.yatspec.internal.totallylazy.$Sequences.sequence;

@RunWith(SpecRunner.class)
public class RestTestCase extends TestState implements WithCustomResultListeners {

    private static MockMvc mockMvc;
    private ResultActions resultActions;
    private SequenceDiagramGenerator sequenceDiagramGenerator;


    @BeforeClass
    public static void startServer() throws Exception {
        mockMvc = new WebApp().start(WebConfig.class, WebTestConfig.class).getBean(MockMvc.class);
    }

    @Before
    public void setup() {
        sequenceDiagramGenerator = new SequenceDiagramGenerator();
    }

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return sequence(
                new HtmlResultRenderer().
                        withCustomHeaderContent(SequenceDiagramGenerator.getHeaderContentForModalWindows()).
                        withCustomRenderer(SvgWrapper.class, new DontHighlightRenderer())).
                safeCast(SpecResultListener.class);
    }

    @After
    public void generateSequenceDiagram() {
        Iterable<SequenceDiagramMessage> messages = sequence(new ByNamingConventionMessageProducer().messages(capturedInputAndOutputs));
        capturedInputAndOutputs.add("Sequence Diagram", sequenceDiagramGenerator.generateSequenceDiagram(messages));
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
