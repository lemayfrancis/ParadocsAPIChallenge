package com.lemayfrancis.domain.Trail;

import java.util.UUID;
import org.springframework.data.annotation.Id;

public class Trail {
  @Id private UUID idTrail;
  private String name;
  private String description;

  public Trail() {
    this.idTrail = UUID.randomUUID();
  }

  public Trail(String name, String description) {
    this.idTrail = UUID.randomUUID();
    this.name = name;
    this.description = description;
  }

  public Trail(UUID idTrail, String name, String description) {
    this.idTrail = idTrail;
    this.name = name;
    this.description = description;
  }

  public UUID getIdTrail() {
    return idTrail;
  }

  public void setIdTrail(UUID idTrail) {
    this.idTrail = idTrail;
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
