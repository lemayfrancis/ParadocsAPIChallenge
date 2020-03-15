package com.lemayfrancis.infrastructure.configuration;

import com.lemayfrancis.infrastructure.Trail.SpringDataMongoDbTrailRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringDataMongoDbTrailRepository.class)
public class MongoDbTrailConfiguration {}
