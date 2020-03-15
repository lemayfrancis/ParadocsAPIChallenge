package com.lemayfrancis.application.Lift.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LiftNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(LiftNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String trailNotFoundHandler(LiftNotFoundException ex) {
    return ex.getMessage();
  }
}
