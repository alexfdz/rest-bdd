package com.example.rest.bdd.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value= "/")
public class HomeResource {

    @RequestMapping(method= GET)
    @ResponseStatus(OK)
    public void hello(){}
}
