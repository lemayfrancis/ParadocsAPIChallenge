package com.lemayfrancis.domain.Lift;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILiftService {
  public Optional<Lift> findById(UUID id);

  public List<Lift> findAll();

  public Lift createLift(Lift lift);

  public Lift updateLift(UUID id, Lift lift);

  public void deleteLift(UUID id);
}
