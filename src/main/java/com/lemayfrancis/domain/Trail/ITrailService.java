package com.lemayfrancis.domain.Trail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITrailService {
  public Optional<Trail> findById(UUID id);

  public List<Trail> getAll();

  public Trail createTrail(Trail trail);

  public Trail updateTrail(UUID id, Trail trail);

  public void deleteTrail(UUID id);
}
