package com.lemayfrancis.application.Lodge;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.lemayfrancis.application.Lodge.exceptions.LodgeNotFoundException;
import com.lemayfrancis.domain.Lodge.ILodgeService;
import com.lemayfrancis.domain.Lodge.Lodge;
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
@RequestMapping("/lodges")
public class LodgeController {
  private final ILodgeService lodgeService;
  private final LodgeModelAssembler assembler;

  @Autowired
  public LodgeController(ILodgeService lodgeService, LodgeModelAssembler assembler) {
    this.lodgeService = lodgeService;
    this.assembler = assembler;
  }

  @GetMapping
  CollectionModel<EntityModel<Lodge>> all() {
    List<EntityModel<Lodge>> lodges =
        lodgeService.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

    return new CollectionModel<>(
        lodges, linkTo(methodOn(LodgeController.class).all()).withSelfRel());
  }

  @GetMapping("/{id}")
  EntityModel<Lodge> one(@PathVariable UUID id) {
    Lodge lodge =
        lodgeService.findById(id).orElseThrow(() -> new LodgeNotFoundException(id.toString()));

    return assembler.toModel(lodge);
  }

  @PostMapping()
  ResponseEntity<?> newLodge(@RequestBody Lodge newLodge) {
    Lodge lodge = lodgeService.createLodge(newLodge);

    return ResponseEntity.created(
            linkTo(methodOn(LodgeController.class).one(lodge.getIdLodge())).toUri())
        .body(assembler.toModel(lodge));
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceLodge(@RequestBody Lodge newLodge, @PathVariable UUID id) {
    Lodge lodgeToUpdate =
        lodgeService.findById(id).orElseThrow(() -> new LodgeNotFoundException(id.toString()));

    Lodge lodge = lodgeService.updateLodge(id, newLodge);

    return ResponseEntity.created(
            linkTo(methodOn(LodgeController.class).one(lodge.getIdLodge())).toUri())
        .body(assembler.toModel(lodge));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteLodge(@PathVariable UUID id) {
    Lodge lodgeToDelete =
        lodgeService.findById(id).orElseThrow(() -> new LodgeNotFoundException(id.toString()));

    lodgeService.deleteLodge(id);

    return ResponseEntity.noContent().build();
  }
}
