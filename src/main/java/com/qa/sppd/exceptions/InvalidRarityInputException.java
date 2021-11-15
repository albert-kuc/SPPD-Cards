package com.qa.sppd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,
        reason = "Legal values for Rarity are [common, rare, epic, legendary]")
public class InvalidRarityInputException extends IllegalArgumentException{

    public InvalidRarityInputException(String s) {
        super(s);
    }
}
