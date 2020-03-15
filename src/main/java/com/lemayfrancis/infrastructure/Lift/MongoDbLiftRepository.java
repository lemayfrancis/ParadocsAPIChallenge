package com.lemayfrancis.infrastructure.Lift;

import com.lemayfrancis.domain.Lift.ILiftRepository;
import com.lemayfrancis.domain.Lift.Lift;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbLiftRepository implements ILiftRepository {
  private final SpringDataMongoDbLiftRepository repository;

  @Autowired
  public MongoDbLiftRepository(final SpringDataMongoDbLiftRepository liftRepository) {
    this.repository = liftRepository;
  }

  @Override
  public Optional<Lift> findById(UUID id) {
    return this.repository.findById(id);
  }

  @Override
  public List<Lift> findAll() {
    return this.repository.findAll();
  }

  @Override
  public void save(Lift lift) {
    this.repository.save(lift);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }
}
