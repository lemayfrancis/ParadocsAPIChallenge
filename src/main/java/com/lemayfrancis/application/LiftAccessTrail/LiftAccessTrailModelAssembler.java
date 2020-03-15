package com.lemayfrancis.application.LiftAccessTrail;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.domain.LiftAccessTrail.LiftAccessTrail;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class LiftAccessTrailModelAssembler
    implements RepresentationModelAssembler<LiftAccessTrail, EntityModel<LiftAccessTrail>> {

  @Override
  public EntityModel<LiftAccessTrail> toModel(LiftAccessTrail entity) {
    return new EntityModel<>(
        entity,
        linkTo(methodOn(LiftAccessTrailController.class).one(entity.getId())).withSelfRel(),
        linkTo(methodOn(LiftAccessTrailController.class).all()).withRel("lift-access-trails"));
  }
}
