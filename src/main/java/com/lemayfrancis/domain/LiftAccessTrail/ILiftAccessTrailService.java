package com.lemayfrancis.domain.LiftAccessTrail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILiftAccessTrailService {
  public Optional<LiftAccessTrail> findById(UUID id);

  public List<LiftAccessTrail> findAll();

  public LiftAccessTrail createLiftAccessTrail(LiftAccessTrail liftAccessTrail);

  public LiftAccessTrail updateLiftAccessTrail(UUID id, LiftAccessTrail liftAccessTrail);

  public void deleteLiftAccessTrail(UUID id);
}
