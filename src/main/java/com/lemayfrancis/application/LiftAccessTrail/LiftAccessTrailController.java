package com.lemayfrancis.application.LiftAccessTrail;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.application.LiftAccessTrail.exceptions.LiftAccessTrailNotFoundException;
import com.lemayfrancis.domain.LiftAccessTrail.ILiftAccessTrailService;
import com.lemayfrancis.domain.LiftAccessTrail.LiftAccessTrail;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lift-access-trails")
public class LiftAccessTrailController {
  private final ILiftAccessTrailService liftAccessTrailService;
  private final LiftAccessTrailModelAssembler assembler;

  @Autowired
  public LiftAccessTrailController(
      ILiftAccessTrailService liftAccessTrailService, LiftAccessTrailModelAssembler assembler) {
    this.liftAccessTrailService = liftAccessTrailService;
    this.assembler = assembler;
  }

  @GetMapping
  CollectionModel<EntityModel<LiftAccessTrail>> all() {
    List<EntityModel<LiftAccessTrail>> liftAccessTrails =
        liftAccessTrailService.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

    return new CollectionModel<>(
        liftAccessTrails, linkTo(methodOn(LiftAccessTrailController.class).all()).withSelfRel());
  }

  @GetMapping("/{id}")
  EntityModel<LiftAccessTrail> one(@PathVariable UUID id) {
    LiftAccessTrail liftAccessTrail =
        liftAccessTrailService
            .findById(id)
            .orElseThrow(() -> new LiftAccessTrailNotFoundException(id.toString()));

    return assembler.toModel(liftAccessTrail);
  }

  @PostMapping()
  ResponseEntity<?> newLiftAccessTrail(@RequestBody LiftAccessTrail newLiftAccessTrail) {
    LiftAccessTrail liftAccessTrail =
        liftAccessTrailService.createLiftAccessTrail(newLiftAccessTrail);

    return ResponseEntity.created(
            linkTo(methodOn(LiftAccessTrailController.class).one(liftAccessTrail.getId())).toUri())
        .body(assembler.toModel(liftAccessTrail));
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceLiftAccessTrail(
      @RequestBody LiftAccessTrail newLiftAccessTrail, @PathVariable UUID id) {
    LiftAccessTrail liftAccessTrailToUpdate =
        liftAccessTrailService
            .findById(id)
            .orElseThrow(() -> new LiftAccessTrailNotFoundException(id.toString()));

    LiftAccessTrail liftAccessTrail =
        liftAccessTrailService.updateLiftAccessTrail(id, newLiftAccessTrail);

    return ResponseEntity.created(
            linkTo(methodOn(LiftAccessTrailController.class).one(liftAccessTrail.getId())).toUri())
        .body(assembler.toModel(liftAccessTrail));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteLiftAccessTrail(@PathVariable UUID id) {
    LiftAccessTrail liftAccessTrailToDelete =
        liftAccessTrailService
            .findById(id)
            .orElseThrow(() -> new LiftAccessTrailNotFoundException(id.toString()));

    liftAccessTrailService.deleteLiftAccessTrail(id);

    return ResponseEntity.noContent().build();
  }
}
