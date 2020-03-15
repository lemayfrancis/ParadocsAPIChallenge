package com.lemayfrancis.domain.Lift;

import java.util.UUID;
import org.springframework.data.annotation.Id;

public class Lift {
  @Id private UUID idLift;
  private String name;
  private String description;

  public Lift() {
    this.idLift = UUID.randomUUID();
  }

  public Lift(String name, String description) {
    this.idLift = UUID.randomUUID();
    this.name = name;
    this.description = description;
  }

  public Lift(UUID idLift, String name, String description) {
    this.idLift = idLift;
    this.name = name;
    this.description = description;
  }

  public UUID getIdLift() {
    return idLift;
  }

  public void setIdLift(UUID idLift) {
    this.idLift = idLift;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
