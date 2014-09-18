package com.example.rest.bdd.yatspec.web;

import com.example.rest.bdd.yatspec.util.RestTestCase;
import com.googlecode.yatspec.junit.SpecRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.googlecode.yatspec.state.givenwhenthen.SyntacticSugar.the;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpecRunner.class)
public class HomeResourceTest extends RestTestCase {

    @Test
    public void getHomePageReturnsOk() throws Exception {
        when(requested(get("/")));
        then(the(response(status().isOk())));
    }
}
