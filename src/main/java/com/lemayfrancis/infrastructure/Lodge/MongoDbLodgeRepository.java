package com.lemayfrancis.infrastructure.Lodge;

import com.lemayfrancis.domain.Lodge.ILodgeRepository;
import com.lemayfrancis.domain.Lodge.Lodge;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbLodgeRepository implements ILodgeRepository {
  private final SpringDataMongoDbLodgeRepository repository;

  @Autowired
  public MongoDbLodgeRepository(final SpringDataMongoDbLodgeRepository lodgeRepository) {
    this.repository = lodgeRepository;
  }

  @Override
  public Optional<Lodge> findById(UUID id) {
    return this.repository.findById(id);
  }

  @Override
  public List<Lodge> findAll() {
    return this.repository.findAll();
  }

  @Override
  public void save(Lodge lodge) {
    this.repository.save(lodge);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }
}
