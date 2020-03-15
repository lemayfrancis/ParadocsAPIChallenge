package com.lemayfrancis.application.Trail;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.domain.Trail.Trail;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TrailModelAssembler
    implements RepresentationModelAssembler<Trail, EntityModel<Trail>> {

  @Override
  public EntityModel<Trail> toModel(Trail trail) {
    return new EntityModel<>(
        trail,
        linkTo(methodOn(TrailController.class).one(trail.getIdTrail())).withSelfRel(),
        linkTo(methodOn(TrailController.class).all()).withRel("trails"));
  }
}
