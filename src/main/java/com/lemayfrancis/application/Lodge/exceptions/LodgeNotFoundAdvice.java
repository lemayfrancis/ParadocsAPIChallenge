package com.lemayfrancis.application.Lodge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LodgeNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(LodgeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String trailNotFoundHandler(LodgeNotFoundException ex) {
    return ex.getMessage();
  }
}
