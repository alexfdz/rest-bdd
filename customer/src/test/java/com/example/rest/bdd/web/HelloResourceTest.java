package com.example.rest.bdd.web;

import org.junit.Test;

public class HelloResourceTest {

    private HomeResource resource = new HomeResource();

    @Test
    public void headRequestReturnsNothing(){
        resource.hello();
    }
}