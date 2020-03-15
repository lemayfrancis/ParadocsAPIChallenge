package com.lemayfrancis.domain.LiftAccessTrail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LiftAccessTrailService implements ILiftAccessTrailService {
  private ILiftAccessTrailRepository repository;

  public LiftAccessTrailService(ILiftAccessTrailRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<LiftAccessTrail> findById(UUID id) {
    return repository.findById(id);
  }

  @Override
  public List<LiftAccessTrail> findAll() {
    return repository.findAll();
  }

  @Override
  public LiftAccessTrail createLiftAccessTrail(LiftAccessTrail newLiftAccessTrail) {
    repository.save(newLiftAccessTrail);

    Optional<LiftAccessTrail> lift = repository.findById(newLiftAccessTrail.getId());

    return lift.orElse(null);
  }

  @Override
  public LiftAccessTrail updateLiftAccessTrail(UUID id, LiftAccessTrail newLiftAccessTrail) {
    repository
        .findById(id)
        .map(
            liftAccessTrail -> {
              liftAccessTrail.setIdLift(newLiftAccessTrail.getIdLift());
              liftAccessTrail.setIdTrail(newLiftAccessTrail.getIdTrail());
              liftAccessTrail.setName(newLiftAccessTrail.getName());
              liftAccessTrail.setDescription(newLiftAccessTrail.getDescription());
              repository.save(liftAccessTrail);
              return liftAccessTrail;
            })
        .orElseGet(
            () -> {
              newLiftAccessTrail.setId(id);
              repository.save(newLiftAccessTrail);
              return newLiftAccessTrail;
            });

    Optional<LiftAccessTrail> liftAccessTrail = repository.findById(id);

    return liftAccessTrail.orElse(null);
  }

  @Override
  public void deleteLiftAccessTrail(UUID id) {
    repository.delete(id);
  }
}
