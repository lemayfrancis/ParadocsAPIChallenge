package com.lemayfrancis.application.Lodge;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.domain.Lodge.Lodge;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class LodgeModelAssembler
    implements RepresentationModelAssembler<Lodge, EntityModel<Lodge>> {

  @Override
  public EntityModel<Lodge> toModel(Lodge entity) {
    return new EntityModel<>(
        entity,
        linkTo(methodOn(LodgeController.class).one(entity.getIdLodge())).withSelfRel(),
        linkTo(methodOn(LodgeController.class).all()).withRel("lodges"));
  }
}
