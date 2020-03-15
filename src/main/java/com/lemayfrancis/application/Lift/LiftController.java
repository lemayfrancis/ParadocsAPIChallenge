package com.lemayfrancis.application.Lift;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.application.Lift.exceptions.LiftNotFoundException;
import com.lemayfrancis.domain.Lift.ILiftService;
import com.lemayfrancis.domain.Lift.Lift;
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
@RequestMapping("/lifts")
public class LiftController {
  private final ILiftService liftService;
  private final LiftModelAssembler assembler;

  @Autowired
  public LiftController(ILiftService liftService, LiftModelAssembler assembler) {
    this.liftService = liftService;
    this.assembler = assembler;
  }

  @GetMapping
  CollectionModel<EntityModel<Lift>> all() {
    List<EntityModel<Lift>> lifts =
        liftService.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

    return new CollectionModel<>(lifts, linkTo(methodOn(LiftController.class).all()).withSelfRel());
  }

  @GetMapping("/{id}")
  EntityModel<Lift> one(@PathVariable UUID id) {
    Lift lift =
        liftService.findById(id).orElseThrow(() -> new LiftNotFoundException(id.toString()));

    return assembler.toModel(lift);
  }

  @PostMapping()
  ResponseEntity<?> newLift(@RequestBody Lift newLift) {
    Lift lift = liftService.createLift(newLift);

    return ResponseEntity.created(
            linkTo(methodOn(LiftController.class).one(lift.getIdLift())).toUri())
        .body(assembler.toModel(lift));
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceLift(@RequestBody Lift newLift, @PathVariable UUID id) {
    Lift liftToUpdate =
        liftService.findById(id).orElseThrow(() -> new LiftNotFoundException(id.toString()));

    Lift lift = liftService.updateLift(id, newLift);

    return ResponseEntity.created(
            linkTo(methodOn(LiftController.class).one(lift.getIdLift())).toUri())
        .body(assembler.toModel(lift));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteLift(@PathVariable UUID id) {
    Lift liftToDelete =
        liftService.findById(id).orElseThrow(() -> new LiftNotFoundException(id.toString()));

    liftService.deleteLift(id);

    return ResponseEntity.noContent().build();
  }
}
