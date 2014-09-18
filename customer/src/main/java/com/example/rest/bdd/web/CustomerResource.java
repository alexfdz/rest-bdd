package com.example.rest.bdd.web;

import com.example.rest.bdd.model.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;

import static com.example.rest.bdd.model.Customer.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@ControllerAdvice
@RequestMapping(value= "/customers")
public class CustomerResource {

    @RequestMapping(method= HEAD)
    @ResponseStatus(OK)
    public void head(){}

    @RequestMapping(value = "/{id}", method= GET)
    @ResponseBody
    public Customer getResource(@PathVariable final Integer id, HttpServletRequest request) throws Throwable {
        if(id == 1) return customer("Paquirrin");
        throw new HttpClientErrorException(NOT_FOUND);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    protected HttpEntity handleExceptions(HttpStatusCodeException exception){
        return new ResponseEntity(exception.getStatusCode());
    }
}
