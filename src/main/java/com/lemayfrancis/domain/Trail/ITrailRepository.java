package com.lemayfrancis.domain.Trail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITrailRepository {
  Optional<Trail> findById(UUID id);

  List<Trail> getAll();

  void save(Trail trail);

  void delete(UUID id);
}
