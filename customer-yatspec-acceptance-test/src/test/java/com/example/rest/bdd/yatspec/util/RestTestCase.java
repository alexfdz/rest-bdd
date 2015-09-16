package com.example.rest.bdd.yatspec.util;

import com.example.rest.bdd.WebApp;
import com.example.rest.bdd.config.WebConfig;
import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.plugin.sequencediagram.ByNamingConventionMessageProducer;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramMessage;
import com.googlecode.yatspec.plugin.sequencediagram.SvgWrapper;
import com.googlecode.yatspec.rendering.html.DontHighlightRenderer;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.rendering.html.index.HtmlIndexRenderer;
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
import org.springframework.web.context.WebApplicationContext;

import static com.googlecode.yatspec.internal.totallylazy.$Sequences.sequence;
import static com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpecRunner.class)
public class RestTestCase extends TestState implements WithCustomResultListeners {

    private static WebApplicationContext applicationContext;
    private static MockMvc mockMvc;
    private ResultActions resultActions;
    private SequenceDiagramGenerator sequenceDiagramGenerator;

    @BeforeClass
    public static void startServer() throws Exception {
        if (applicationContext == null) {
            applicationContext = new WebApp().start(WebConfig.class);
        }
    }

    @Before
    public void setup() {
        sequenceDiagramGenerator = new SequenceDiagramGenerator();
        mockMvc = webAppContextSetup(applicationContext)
                .dispatchOptions(true)
                .alwaysDo(new CaptureResultHandler(capturedInputAndOutputs)).build();
    }

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return sequence(
                new HtmlResultRenderer().
                        withCustomHeaderContent(getHeaderContentForModalWindows()).
                        withCustomRenderer(SvgWrapper.class, new DontHighlightRenderer()),
                new HtmlIndexRenderer()).
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
        return (interestingGivens1, capturedInputAndOutputs1) -> {
            resultActions = mockMvc.perform(requestBuilder);
            return capturedInputAndOutputs1;
        };
    }

    public static <T> T then(T t) {
        return t;
    }

    public static <T> T to(T t) {
        return t;
    }
}
