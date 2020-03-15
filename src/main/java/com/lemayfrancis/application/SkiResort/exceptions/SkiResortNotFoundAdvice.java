package com.lemayfrancis.application.SkiResort.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SkiResortNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(SkiResortNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String skiResortNotFoundHandler(SkiResortNotFoundException ex) {
    return ex.getMessage();
  }
}
