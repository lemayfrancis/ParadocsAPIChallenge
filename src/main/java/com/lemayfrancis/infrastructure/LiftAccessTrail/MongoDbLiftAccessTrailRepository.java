package com.lemayfrancis.infrastructure.LiftAccessTrail;

import com.lemayfrancis.domain.LiftAccessTrail.ILiftAccessTrailRepository;
import com.lemayfrancis.domain.LiftAccessTrail.LiftAccessTrail;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbLiftAccessTrailRepository implements ILiftAccessTrailRepository {
  private final SpringDataMongoDbLiftAccessTrailRepository repository;

  @Autowired
  public MongoDbLiftAccessTrailRepository(
      final SpringDataMongoDbLiftAccessTrailRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<LiftAccessTrail> findById(UUID id) {
    return this.repository.findById(id);
  }

  @Override
  public List<LiftAccessTrail> findAll() {
    return this.repository.findAll();
  }

  @Override
  public void save(LiftAccessTrail liftAccessTrail) {
    this.repository.save(liftAccessTrail);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }
}
