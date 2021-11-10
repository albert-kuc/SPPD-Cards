package com.qa.sppd.card;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "card")
public class CardController {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello world!";
    }
}
