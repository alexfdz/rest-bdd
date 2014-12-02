package com.example.rest.bdd.web;

import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class CustomerResourceTest {

    private CustomerResource resource = new CustomerResource();

    @Test(expected = HttpClientErrorException.class)
    public void throwsExceptionForUnknownCustomerId() throws Throwable {
        resource.getResource(3);
    }

    @Test
    public void returnsACostumerForAKnownId() throws Throwable {
        assertThat(resource.getResource(1), is(notNullValue()));
    }
}