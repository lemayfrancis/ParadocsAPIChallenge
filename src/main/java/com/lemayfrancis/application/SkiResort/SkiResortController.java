package com.lemayfrancis.application.SkiResort;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.application.SkiResort.exceptions.SkiResortNotFoundException;
import com.lemayfrancis.domain.SkiResort.ISkiResortService;
import com.lemayfrancis.domain.SkiResort.SkiResort;
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
@RequestMapping("/ski-resorts")
public class SkiResortController {
  private final ISkiResortService skiResortService;
  private final SkiResortModelAssembler assembler;

  @Autowired
  public SkiResortController(
      ISkiResortService skiResortService, SkiResortModelAssembler assembler) {
    this.skiResortService = skiResortService;
    this.assembler = assembler;
  }

  @GetMapping
  CollectionModel<EntityModel<SkiResort>> all() {
    List<EntityModel<SkiResort>> resorts =
        skiResortService.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

    return new CollectionModel<>(
        resorts, linkTo(methodOn(SkiResortController.class).all()).withSelfRel());
  }

  @GetMapping("/{id}")
  EntityModel<SkiResort> one(@PathVariable UUID id) {
    SkiResort resort =
        skiResortService
            .findById(id)
            .orElseThrow(() -> new SkiResortNotFoundException(id.toString()));

    return assembler.toModel(resort);
  }

  @PostMapping()
  ResponseEntity<?> newSkiResort(@RequestBody SkiResort newSkiResort) {
    SkiResort resort = skiResortService.createSkiResort(newSkiResort);

    return ResponseEntity.created(
            linkTo(methodOn(SkiResortController.class).one(resort.getIdResort())).toUri())
        .body(assembler.toModel(resort));
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceSkiResort(@RequestBody SkiResort newSkiResort, @PathVariable UUID id) {
    SkiResort resortToUpdate =
        skiResortService
            .findById(id)
            .orElseThrow(() -> new SkiResortNotFoundException(id.toString()));

    SkiResort resort = skiResortService.updateSkiResort(id, newSkiResort);

    return ResponseEntity.created(
            linkTo(methodOn(SkiResortController.class).one(resort.getIdResort())).toUri())
        .body(assembler.toModel(resort));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteSkiResort(@PathVariable UUID id) {
    SkiResort resortToDelete =
        skiResortService
            .findById(id)
            .orElseThrow(() -> new SkiResortNotFoundException(id.toString()));

    skiResortService.deleteSkiResort(id);

    return ResponseEntity.noContent().build();
  }
}
