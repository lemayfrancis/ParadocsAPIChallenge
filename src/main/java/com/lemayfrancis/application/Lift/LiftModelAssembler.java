package com.lemayfrancis.application.Lift;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.domain.Lift.Lift;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class LiftModelAssembler implements RepresentationModelAssembler<Lift, EntityModel<Lift>> {

  @Override
  public EntityModel<Lift> toModel(Lift lift) {
    return new EntityModel<>(
        lift,
        linkTo(methodOn(LiftController.class).one(lift.getIdLift())).withSelfRel(),
        linkTo(methodOn(LiftController.class).all()).withRel("lifts"));
  }
}
