package com.example.rest.bdd.yatspec.web;

import com.example.rest.bdd.yatspec.util.RestTestCase;
import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.Table;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.googlecode.yatspec.state.givenwhenthen.SyntacticSugar.the;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.http.MediaType.parseMediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpecRunner.class)
public class CustomerResourceTest extends RestTestCase {

    @Test
    public void getWrongPathReturnsNotFound() throws Exception {
        when(requested(get("/custodsfghytdrmers")));
        then(the(response(status().isNotFound())));
    }

    @Test
    public void getCustomerPageWithInvalidIdReturnsBadRequest() throws Exception {
        when(requested(get("/customers/Aksdoie")));
        then(the(response(status().isBadRequest())));
    }

    @Test
    public void getCustomerPageWithWrongIdReturnsNotFound() throws Exception {
        when(requested(get("/customers/9889")));
        then(the(response(status().isNotFound())));
    }

    @Test
    public void getCustomerForUnKnownMediaTypesReturnsNotAcceptable() throws Exception {
        when(requested(get("/customers/1").accept(TEXT_PLAIN)));
        then(the(response(status().isNotAcceptable())));
    }

    @Test
    public void getCustomerPageWithExistingIdReturnsOk() throws Exception {
        when(requested(get("/customers/1")));
        then(the(response(status().isOk()).andExpect(content().string(not(isEmptyString())))));
    }

    @Test
    @Table({
            @Row("application/json"),
            @Row("application/xml")
    })
    public void getCustomerForKnownMediaTypesReturnsExpectedContentType(String mediaType) throws Exception {
        when(requested(get("/customers/1").accept(parseMediaType(mediaType))));
        then(the(response(status().isOk()).andExpect(content().contentType(mediaType))));
    }
}
