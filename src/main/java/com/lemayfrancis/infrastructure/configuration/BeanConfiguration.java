package com.lemayfrancis.infrastructure.configuration;

import com.lemayfrancis.Main;
import com.lemayfrancis.domain.Lift.ILiftRepository;
import com.lemayfrancis.domain.Lift.LiftService;
import com.lemayfrancis.domain.Lodge.ILodgeRepository;
import com.lemayfrancis.domain.Lodge.LodgeService;
import com.lemayfrancis.domain.SkiResort.ISkiResortRepository;
import com.lemayfrancis.domain.SkiResort.SkiResortService;
import com.lemayfrancis.domain.Trail.ITrailRepository;
import com.lemayfrancis.domain.Trail.TrailService;
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

  @Bean
  LiftService liftService(final ILiftRepository liftRepository) {
    return new LiftService(liftRepository);
  }

  @Bean
  LodgeService lodgeService(final ILodgeRepository lodgeRepository) {
    return new LodgeService(lodgeRepository);
  }

  @Bean
  SkiResortService skiResortService(
      final ISkiResortRepository skiResortRepository,
      final ILiftRepository liftRepository,
      final ILodgeRepository lodgeRepository) {
    return new SkiResortService(skiResortRepository, liftRepository, lodgeRepository);
  }
}
