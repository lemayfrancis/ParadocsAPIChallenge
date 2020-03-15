package com.lemayfrancis.infrastructure.SkiResort;

import com.lemayfrancis.domain.SkiResort.ISkiResortRepository;
import com.lemayfrancis.domain.SkiResort.SkiResort;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbSkiResortRepository implements ISkiResortRepository {
  private final SpringDataMongoDbSkiResortRepository repository;

  @Autowired
  public MongoDbSkiResortRepository(final SpringDataMongoDbSkiResortRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<SkiResort> findById(UUID id) {
    return this.repository.findById(id);
  }

  @Override
  public List<SkiResort> findAll() {
    return this.repository.findAll();
  }

  @Override
  public void save(SkiResort lift) {
    this.repository.save(lift);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }
}
