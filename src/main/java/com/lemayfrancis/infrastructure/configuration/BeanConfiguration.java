package com.lemayfrancis.infrastructure.configuration;

import com.lemayfrancis.Main;
import com.lemayfrancis.application.Trail.TrailService;
import com.lemayfrancis.domain.Trail.ITrailRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Main.class)
public class BeanConfiguration {

  @Bean
  TrailService trailService(final ITrailRepository trailRepository) {
    return new TrailService(trailRepository);
  }
}
