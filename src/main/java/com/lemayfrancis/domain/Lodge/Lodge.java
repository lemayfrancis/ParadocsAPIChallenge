package com.lemayfrancis.domain.Lodge;

import java.util.UUID;
import org.springframework.data.annotation.Id;

public class Lodge {
  @Id private UUID idLodge;
  private String name;
  private String description;

  public Lodge() {
    this.idLodge = UUID.randomUUID();
  }

  public Lodge(String name, String description) {
    this.idLodge = UUID.randomUUID();
    this.name = name;
    this.description = description;
  }

  public Lodge(UUID idLodge, String name, String description) {
    this.idLodge = idLodge;
    this.name = name;
    this.description = description;
  }

  public UUID getIdLodge() {
    return idLodge;
  }

  public void setIdLodge(UUID idLodge) {
    this.idLodge = idLodge;
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
