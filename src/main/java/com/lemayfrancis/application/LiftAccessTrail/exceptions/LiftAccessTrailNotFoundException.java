package com.lemayfrancis.application.LiftAccessTrail.exceptions;

public class LiftAccessTrailNotFoundException extends RuntimeException {
  public LiftAccessTrailNotFoundException(String id) {
    super("Could not find lodge " + id);
  }
}
