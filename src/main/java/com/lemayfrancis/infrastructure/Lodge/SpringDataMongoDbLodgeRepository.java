package com.lemayfrancis.infrastructure.Lodge;

import com.lemayfrancis.domain.Lodge.Lodge;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoDbLodgeRepository extends MongoRepository<Lodge, UUID> {}
