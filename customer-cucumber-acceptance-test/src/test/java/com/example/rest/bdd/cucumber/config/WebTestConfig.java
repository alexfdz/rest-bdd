package com.example.rest.bdd.cucumber.config;

import com.example.rest.bdd.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Configuration
@Import(WebConfig.class)
public class WebTestConfig {
    @Autowired
    private WebApplicationContext wac;

    @Bean
    @Scope("prototype")
    public MockMvc mockMvc() {
        return webAppContextSetup(wac).dispatchOptions(true).build();
    }
}
