package com.lemayfrancis.infrastructure.Lift;

import com.lemayfrancis.domain.Lift.Lift;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoDbLiftRepository extends MongoRepository<Lift, UUID> {}
