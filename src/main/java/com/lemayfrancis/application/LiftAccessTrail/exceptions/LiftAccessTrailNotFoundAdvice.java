package com.lemayfrancis.application.LiftAccessTrail.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LiftAccessTrailNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(LiftAccessTrailNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String liftAccessTrailNotFoundHandler(LiftAccessTrailNotFoundException ex) {
    return ex.getMessage();
  }
}
