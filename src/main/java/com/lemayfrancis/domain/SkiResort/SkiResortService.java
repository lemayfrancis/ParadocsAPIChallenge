package com.lemayfrancis.domain.SkiResort;

import com.lemayfrancis.domain.Lift.ILiftRepository;
import com.lemayfrancis.domain.Lodge.ILodgeRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SkiResortService implements ISkiResortService {

  private ISkiResortRepository skiResortRepository;
  private ILiftRepository liftRepository;
  private ILodgeRepository lodgeRepository;

  public SkiResortService(
      ISkiResortRepository skiResortRepository,
      ILiftRepository liftRepository,
      ILodgeRepository lodgeRepository) {
    this.skiResortRepository = skiResortRepository;
    this.liftRepository = liftRepository;
    this.lodgeRepository = lodgeRepository;
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
  public SkiResort createSkiResort(SkiResort newSkiResort) {
    skiResortRepository.save(newSkiResort);

    newSkiResort.getLifts().forEach(lift -> liftRepository.save(lift));

    newSkiResort.getLodges().forEach(lodge -> lodgeRepository.save(lodge));

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
              resort.setLifts(newSkiResort.getLifts());
              resort.setLodges(newSkiResort.getLodges());
              resort.getLifts().forEach(lift -> liftRepository.save(lift));
              resort.getLodges().forEach(lodge -> lodgeRepository.save(lodge));
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
              newSkiResort.getLodges().forEach(lodge -> lodgeRepository.save(lodge));
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
