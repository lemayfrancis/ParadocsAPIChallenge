package com.lemayfrancis.infrastructure.configuration;

import com.lemayfrancis.infrastructure.Lift.SpringDataMongoDbLiftRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringDataMongoDbLiftRepository.class)
public class MongoDbLiftConfiguration {}
