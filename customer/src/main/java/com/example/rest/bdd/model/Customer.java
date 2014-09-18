package com.example.rest.bdd.model;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlAccessorType(FIELD)
@XmlRootElement(name = "customer")
public class Customer {
    @XmlElement
    @JsonProperty
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public static Customer customer(String name){
        Customer customer = new Customer();
        customer.setName(name);
        return customer;
    }
}
