package com.lemayfrancis.domain.SkiResort;

import com.lemayfrancis.domain.Lift.ILiftRepository;
import com.lemayfrancis.domain.Lift.Lift;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SkiResortService implements ISkiResortService {

  private ISkiResortRepository skiResortRepository;
  private ILiftRepository liftRepository;

  public SkiResortService(
      ISkiResortRepository skiResortRepository, ILiftRepository liftRepository) {
    this.skiResortRepository = skiResortRepository;
    this.liftRepository = liftRepository;
  }

  @Override
  public Optional<SkiResort> findById(UUID id) {
    return skiResortRepository.findById(id);
  }

  @Override
  public List<SkiResort> findAll() {
    return skiResortRepository.findAll();
  }

  @Override
  public SkiResort createSkiResort(SkiResort newSkiResort, List<Lift> lifts) {
    skiResortRepository.save(newSkiResort);
    lifts.forEach(
        lift -> {
          liftRepository.save(lift);
        });

    Optional<SkiResort> resort = skiResortRepository.findById(newSkiResort.getIdResort());

    return resort.orElse(null);
  }

  @Override
  public SkiResort updateSkiResort(UUID id, SkiResort newSkiResort) {
    skiResortRepository
        .findById(id)
        .map(
            resort -> {
              resort.setName(newSkiResort.getName());
              resort.setDescription(newSkiResort.getDescription());
              resort
                  .getLifts()
                  .forEach(
                      lift -> {
                        liftRepository.save(lift);
                      });
              skiResortRepository.save(resort);
              return resort;
            })
        .orElseGet(
            () -> {
              newSkiResort.setIdResort(id);
              newSkiResort
                  .getLifts()
                  .forEach(
                      lift -> {
                        liftRepository.save(lift);
                      });
              skiResortRepository.save(newSkiResort);
              return newSkiResort;
            });

    Optional<SkiResort> resort = skiResortRepository.findById(id);

    return resort.orElse(null);
  }

  @Override
  public void deleteSkiResort(UUID id) {
    skiResortRepository.delete(id);
  }
}
