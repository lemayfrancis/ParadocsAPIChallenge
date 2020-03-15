package com.lemayfrancis.domain.Trail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrailService implements ITrailService {

  private ITrailRepository trailRepository;

  public TrailService(ITrailRepository trailRepository) {
    this.trailRepository = trailRepository;
  }

  @Override
  public Optional<Trail> findById(UUID id) {
    return trailRepository.findById(id);
  }

  public List<Trail> getAll() {
    return trailRepository.getAll();
  }

  @Override
  public Trail createTrail(Trail newTrail) {
    trailRepository.save(newTrail);

    Optional<Trail> trail = trailRepository.findById(newTrail.getIdTrail());

    return trail.orElse(null);
  }

  @Override
  public Trail updateTrail(UUID id, Trail newTrail) {
    trailRepository
        .findById(id)
        .map(
            trail -> {
              trail.setName(newTrail.getName());
              trail.setDescription(newTrail.getDescription());
              trailRepository.save(trail);
              return trail;
            })
        .orElseGet(
            () -> {
              newTrail.setIdTrail(id);
              trailRepository.save(newTrail);
              return newTrail;
            });

    Optional<Trail> trail = trailRepository.findById(id);

    return trail.orElse(null);
  }

  @Override
  public void deleteTrail(UUID id) {
    trailRepository.delete(id);
  }
}
