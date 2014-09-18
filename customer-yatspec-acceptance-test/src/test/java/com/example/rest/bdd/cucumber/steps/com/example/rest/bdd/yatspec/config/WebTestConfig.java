package com.example.rest.bdd.cucumber.steps.com.example.rest.bdd.yatspec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Configuration
public class WebTestConfig {
    @Autowired
    private WebApplicationContext wac;

    @Bean
    @Scope("prototype")
    public MockMvc getMockMvc() {
        return webAppContextSetup(wac).dispatchOptions(true).build();
    }

}
