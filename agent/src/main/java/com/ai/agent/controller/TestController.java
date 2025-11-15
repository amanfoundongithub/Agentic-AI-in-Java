package com.ai.agent.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    public String test_hello() { return "hello";}

}
