package com.lemayfrancis.application.Trail;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.application.Trail.exceptions.TrailNotFoundException;
import com.lemayfrancis.domain.Trail.ITrailService;
import com.lemayfrancis.domain.Trail.Trail;
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
@RequestMapping("/trails")
public class TrailController {
  private final ITrailService trailService;
  private final TrailModelAssembler assembler;

  @Autowired
  public TrailController(ITrailService trailService, TrailModelAssembler assembler) {
    this.trailService = trailService;
    this.assembler = assembler;
  }

  @GetMapping
  CollectionModel<EntityModel<Trail>> all() {
    List<EntityModel<Trail>> trails =
        trailService.getAll().stream().map(assembler::toModel).collect(Collectors.toList());

    return new CollectionModel<>(
        trails, linkTo(methodOn(TrailController.class).all()).withSelfRel());
  }

  @GetMapping("/{id}")
  EntityModel<Trail> one(@PathVariable UUID id) {
    Trail trail =
        trailService.findById(id).orElseThrow(() -> new TrailNotFoundException(id.toString()));

    return assembler.toModel(trail);
  }

  @PostMapping()
  ResponseEntity<?> newTrail(@RequestBody Trail newTrail) {
    Trail trail = trailService.createTrail(newTrail);

    return ResponseEntity.created(
            linkTo(methodOn(TrailController.class).one(trail.getIdTrail())).toUri())
        .body(assembler.toModel(trail));
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceTrail(@RequestBody Trail newTrail, @PathVariable UUID id) {
    Trail trailToUpdate =
        trailService.findById(id).orElseThrow(() -> new TrailNotFoundException(id.toString()));

    Trail trail = trailService.updateTrail(id, newTrail);

    return ResponseEntity.created(
            linkTo(methodOn(TrailController.class).one(trail.getIdTrail())).toUri())
        .body(assembler.toModel(trail));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteTrail(@PathVariable UUID id) {
    Trail trailToDelete =
        trailService.findById(id).orElseThrow(() -> new TrailNotFoundException(id.toString()));

    trailService.deleteTrail(id);

    return ResponseEntity.noContent().build();
  }
}
