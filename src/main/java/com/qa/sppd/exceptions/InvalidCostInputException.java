package com.qa.sppd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Cost value must be an integer in range (1, 7) inclusive")
public class InvalidCostInputException extends IllegalArgumentException{

    public InvalidCostInputException(String s) {
        super(s);
    }
}
