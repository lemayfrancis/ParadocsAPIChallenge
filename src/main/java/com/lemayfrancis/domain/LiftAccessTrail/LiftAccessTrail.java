package com.lemayfrancis.domain.LiftAccessTrail;

import java.util.UUID;
import org.springframework.data.annotation.Id;

public class LiftAccessTrail {

  @Id private UUID id;
  private UUID idLift;
  private UUID idTrail;
  private String name;
  private String description;

  public LiftAccessTrail() {
    this.id = UUID.randomUUID();
    ;
  }

  public LiftAccessTrail(UUID id, UUID idLift, UUID idTrail, String name, String description) {
    this.id = id;
    this.idLift = idLift;
    this.idTrail = idTrail;
    this.name = name;
    this.description = description;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getIdLift() {
    return idLift;
  }

  public void setIdLift(UUID idLift) {
    this.idLift = idLift;
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
