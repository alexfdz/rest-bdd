package com.example.rest.yatspec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value= "/")
public class HelloResource {

    @RequestMapping(method= HEAD)
    @ResponseStatus(OK)
    public void head(){}
}
