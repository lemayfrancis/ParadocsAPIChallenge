package com.lemayfrancis.domain.SkiResort;

import com.lemayfrancis.domain.Lift.Lift;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;

public class SkiResort {
  @Id private UUID idResort;
  private String name;
  private String description;
  private List<Lift> lifts;

  public SkiResort(UUID idResort, String name, String description, List<Lift> lifts) {
    this.idResort = idResort;
    this.name = name;
    this.description = description;
    this.lifts = lifts;
  }

  public SkiResort(String name, String description, List<Lift> lifts) {
    this.lifts = lifts;
    this.idResort = UUID.randomUUID();
    this.name = name;
    this.description = description;
  }

  public SkiResort(String name, String description) {
    this.idResort = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.lifts = Collections.emptyList();
  }

  public SkiResort() {
    this.idResort = UUID.randomUUID();
    this.lifts = Collections.emptyList();
  }

  public UUID getIdResort() {
    return idResort;
  }

  public void setIdResort(UUID idResort) {
    this.idResort = idResort;
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

  public List<Lift> getLifts() {
    return lifts;
  }

  public void setLifts(List<Lift> lifts) {
    this.lifts = lifts;
  }
}
