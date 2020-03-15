package com.lemayfrancis.domain.Lift;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILiftRepository {
  Optional<Lift> findById(UUID id);

  List<Lift> findAll();

  void save(Lift newLift);

  void delete(UUID id);
}
