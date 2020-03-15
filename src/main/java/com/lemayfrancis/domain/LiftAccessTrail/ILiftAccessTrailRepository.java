package com.lemayfrancis.domain.LiftAccessTrail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILiftAccessTrailRepository {
  Optional<LiftAccessTrail> findById(UUID id);

  List<LiftAccessTrail> findAll();

  void save(LiftAccessTrail newLiftAccessTrail);

  void delete(UUID id);
}
