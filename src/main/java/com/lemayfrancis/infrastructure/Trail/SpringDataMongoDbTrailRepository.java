package com.lemayfrancis.infrastructure.Trail;

import com.lemayfrancis.domain.Trail.Trail;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoDbTrailRepository extends MongoRepository<Trail, UUID> {}
