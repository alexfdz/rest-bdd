package com.example.rest.bdd.web;

import org.junit.Test;

public class HelloResourceTest {

    private HelloResource resource = new HelloResource();

    @Test
    public void headRequestReturnsNothing(){
        resource.hello();
    }
}