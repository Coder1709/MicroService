package com.eazybytes.accounts.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping(path={"hello"})
    public String gethello(){

        return "Hello World";
    }
}
