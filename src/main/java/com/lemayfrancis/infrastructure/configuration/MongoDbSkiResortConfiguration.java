package com.lemayfrancis.infrastructure.configuration;

import com.lemayfrancis.infrastructure.SkiResort.SpringDataMongoDbSkiResortRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringDataMongoDbSkiResortRepository.class)
public class MongoDbSkiResortConfiguration {}
