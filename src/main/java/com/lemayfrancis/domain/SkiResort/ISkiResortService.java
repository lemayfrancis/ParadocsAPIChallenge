package com.lemayfrancis.domain.SkiResort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISkiResortService {
  public Optional<SkiResort> findById(UUID id);

  public List<SkiResort> findAll();

  public SkiResort createSkiResort(SkiResort resort);

  public SkiResort updateSkiResort(UUID id, SkiResort resort);

  public void deleteSkiResort(UUID id);
}
