package com.lemayfrancis.application.Trail.exceptions;

public class TrailNotFoundException extends RuntimeException {

  public TrailNotFoundException(String id) {
    super("Could not find trail " + id);
  }
}
