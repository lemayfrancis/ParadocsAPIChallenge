package com.lemayfrancis.domain.Lodge;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LodgeService implements ILodgeService {
  private ILodgeRepository repository;

  public LodgeService(ILodgeRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Lodge> findById(UUID id) {
    return repository.findById(id);
  }

  @Override
  public List<Lodge> findAll() {
    return repository.findAll();
  }

  @Override
  public Lodge createLodge(Lodge newLodge) {
    repository.save(newLodge);

    Optional<Lodge> lodge = repository.findById(newLodge.getIdLodge());

    return lodge.orElse(null);
  }

  @Override
  public Lodge updateLodge(UUID id, Lodge newLodge) {
    repository
        .findById(id)
        .map(
            lodge -> {
              lodge.setName(newLodge.getName());
              lodge.setDescription(newLodge.getDescription());
              repository.save(lodge);
              return lodge;
            })
        .orElseGet(
            () -> {
              newLodge.setIdLodge(id);
              repository.save(newLodge);
              return newLodge;
            });

    Optional<Lodge> lodge = repository.findById(id);

    return lodge.orElse(null);
  }

  @Override
  public void deleteLodge(UUID id) {
    repository.delete(id);
  }
}
