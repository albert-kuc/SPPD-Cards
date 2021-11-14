package com.qa.sppd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,
        reason = "Legal values for Theme are the following [neutral, adventures, fantasy, mystical, sci-fi, superheroes]")
public class InvalidThemeInputException extends IllegalArgumentException{

    public InvalidThemeInputException(String s) {
        super(s);
    }
}
