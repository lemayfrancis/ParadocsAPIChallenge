package com.lemayfrancis.application.SkiResort.exceptions;

public class SkiResortNotFoundException extends RuntimeException {

  public SkiResortNotFoundException(String id) {
    super("Could not find lift " + id);
  }
}
