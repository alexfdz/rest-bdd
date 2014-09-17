package com.example.rest.yatspec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.support.AbstractMarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableEntityLinks
@ComponentScan(basePackages="com.example.rest.yatspec")
public class WebConfig extends WebMvcConfigurerAdapter {

    private XStreamMarshaller marshaller;

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJacksonHttpMessageConverter();
        mappingJacksonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(mappingJacksonHttpMessageConverter);

        MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
        converter.setMarshaller(marshaller());
        converter.setUnmarshaller(marshaller());
        converters.add(converter);
    }

    @Bean
    public AbstractMarshaller marshaller() {
        if (marshaller == null) {
            return new XStreamMarshaller();
        } else {
            return marshaller;
        }
    }
}
