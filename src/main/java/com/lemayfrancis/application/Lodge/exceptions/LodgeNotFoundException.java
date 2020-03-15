package com.lemayfrancis.application.Lodge.exceptions;

public class LodgeNotFoundException extends RuntimeException {
  public LodgeNotFoundException(String id) {
    super("Could not find lodge " + id);
  }
}
