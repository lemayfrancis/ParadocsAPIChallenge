package com.lemayfrancis.infrastructure.SkiResort;

import com.lemayfrancis.domain.SkiResort.SkiResort;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoDbSkiResortRepository extends MongoRepository<SkiResort, UUID> {}
