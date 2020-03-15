package com.lemayfrancis.infrastructure.LiftAccessTrail;

import com.lemayfrancis.domain.LiftAccessTrail.LiftAccessTrail;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoDbLiftAccessTrailRepository
    extends MongoRepository<LiftAccessTrail, UUID> {}
