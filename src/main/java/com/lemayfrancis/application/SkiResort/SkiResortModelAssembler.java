package com.lemayfrancis.application.SkiResort;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.domain.SkiResort.SkiResort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SkiResortModelAssembler
    implements RepresentationModelAssembler<SkiResort, EntityModel<SkiResort>> {
  @Override
  public EntityModel<SkiResort> toModel(SkiResort resort) {

    return new EntityModel<>(
        resort,
        linkTo(methodOn(SkiResortController.class).one(resort.getIdResort())).withSelfRel(),
        linkTo(methodOn(SkiResortController.class).all()).withRel("ski_resorts"));
  }
}
