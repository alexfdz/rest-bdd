package com.example.rest.bdd.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;
import static org.springframework.http.MediaType.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.rest.bdd")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(APPLICATION_JSON).
                mediaType("xml", APPLICATION_XML).
                mediaType("json", APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJacksonHttpMessageConverter.setSupportedMediaTypes(asList(APPLICATION_JSON));
        converters.add(mappingJacksonHttpMessageConverter);

        XStreamMarshaller marshaller = new XStreamMarshaller();
        MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
        converter.setSupportedMediaTypes(asList(APPLICATION_XML));
        converter.setMarshaller(marshaller);
        converter.setUnmarshaller(marshaller);
        converters.add(converter);
    }
}
