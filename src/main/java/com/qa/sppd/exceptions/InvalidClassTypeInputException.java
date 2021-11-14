package com.qa.sppd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,
        reason = "Legal values for ClassType are [fighter, assassin, ranged, tank, spell, totem, trap]")
public class InvalidClassTypeInputException extends IllegalArgumentException{

    public InvalidClassTypeInputException(String s) {
        super(s);
    }
}
