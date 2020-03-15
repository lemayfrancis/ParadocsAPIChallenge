package com.lemayfrancis.domain.SkiResort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISkiResortRepository {
  Optional<SkiResort> findById(UUID id);

  List<SkiResort> findAll();

  void save(SkiResort resort);

  void delete(UUID id);
}
