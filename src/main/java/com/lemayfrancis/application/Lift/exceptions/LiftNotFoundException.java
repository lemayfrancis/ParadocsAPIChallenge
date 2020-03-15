package com.lemayfrancis.application.Lift.exceptions;

public class LiftNotFoundException extends RuntimeException {
  public LiftNotFoundException(String id) {
    super("Could not find lift " + id);
  }
}
