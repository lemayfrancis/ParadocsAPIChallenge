package com.lemayfrancis.application.Trail.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TrailNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(TrailNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String trailNotFoundHandler(TrailNotFoundException ex) {
    return ex.getMessage();
  }
}
