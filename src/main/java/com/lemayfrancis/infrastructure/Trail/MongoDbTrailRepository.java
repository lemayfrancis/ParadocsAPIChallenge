package com.lemayfrancis.infrastructure.Trail;

import com.lemayfrancis.domain.Trail.ITrailRepository;
import com.lemayfrancis.domain.Trail.Trail;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbTrailRepository implements ITrailRepository {
  private final SpringDataMongoDbTrailRepository trailRepository;

  @Autowired
  public MongoDbTrailRepository(final SpringDataMongoDbTrailRepository trailRepository) {
    this.trailRepository = trailRepository;
  }

  @Override
  public Optional<Trail> findById(UUID id) {
    return this.trailRepository.findById(id);
  }

  @Override
  public List<Trail> getAll() {
    return this.trailRepository.findAll();
  }

  @Override
  public void save(Trail trail) {
    this.trailRepository.save(trail);
  }

  @Override
  public void delete(UUID id) {
    trailRepository.deleteById(id);
  }
}
