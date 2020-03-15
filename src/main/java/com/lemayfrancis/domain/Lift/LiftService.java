package com.lemayfrancis.domain.Lift;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LiftService implements ILiftService {

  private ILiftRepository repository;

  public LiftService(ILiftRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Lift> findById(UUID id) {
    return repository.findById(id);
  }

  @Override
  public List<Lift> findAll() {
    return repository.findAll();
  }

  @Override
  public Lift createLift(Lift newLift) {
    repository.save(newLift);

    Optional<Lift> lift = repository.findById(newLift.getIdLift());

    return lift.orElse(null);
  }

  @Override
  public Lift updateLift(UUID id, Lift newLift) {
    repository
        .findById(id)
        .map(
            lift -> {
              lift.setName(newLift.getName());
              lift.setDescription(newLift.getDescription());
              repository.save(lift);
              return lift;
            })
        .orElseGet(
            () -> {
              newLift.setIdLift(id);
              repository.save(newLift);
              return newLift;
            });

    Optional<Lift> lift = repository.findById(id);

    return lift.orElse(null);
  }

  @Override
  public void deleteLift(UUID id) {
    repository.delete(id);
  }
}
