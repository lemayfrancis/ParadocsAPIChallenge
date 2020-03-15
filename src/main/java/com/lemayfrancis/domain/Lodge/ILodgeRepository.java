package com.lemayfrancis.domain.Lodge;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILodgeRepository {
  Optional<Lodge> findById(UUID id);

  List<Lodge> findAll();

  void save(Lodge newLodge);

  void delete(UUID id);
}
