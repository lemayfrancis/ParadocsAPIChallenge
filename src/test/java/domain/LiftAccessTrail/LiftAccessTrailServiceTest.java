package domain.LiftAccessTrail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lemayfrancis.domain.LiftAccessTrail.ILiftAccessTrailRepository;
import com.lemayfrancis.domain.LiftAccessTrail.LiftAccessTrail;
import com.lemayfrancis.domain.LiftAccessTrail.LiftAccessTrailService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LiftAccessTrailServiceTest {
  private ILiftAccessTrailRepository repository;
  private LiftAccessTrailService underTest;

  @BeforeEach
  void setUp() {
    repository = mock(ILiftAccessTrailRepository.class);
    underTest = new LiftAccessTrailService(repository);
  }

  @Test
  void Should_CallRepository_When_FindById() {
    LiftAccessTrail liftAccessTrail = createLiftAccessTrail();
    when(underTest.findById(liftAccessTrail.getId())).thenReturn(Optional.of(liftAccessTrail));

    Optional<LiftAccessTrail> result = underTest.findById(liftAccessTrail.getId());

    verify(repository).findById(any(UUID.class));
  }

  @Test
  void Should_ReturnLiftAccessTrail_When_ExistingId() {
    LiftAccessTrail liftAccessTrail = createLiftAccessTrail();
    when(underTest.findById(liftAccessTrail.getId())).thenReturn(Optional.of(liftAccessTrail));

    Optional<LiftAccessTrail> result = underTest.findById(liftAccessTrail.getId());

    assertEquals(result.orElseThrow(), liftAccessTrail);
  }

  @Test
  void Should_ReturnEmpty_When_NotExistingId() {
    UUID id = UUID.randomUUID();
    when(underTest.findById(id)).thenReturn(Optional.empty());

    Optional<LiftAccessTrail> result = underTest.findById(id);

    assertEquals(result, Optional.empty());
  }

  @Test
  void Should_CallRepository_When_FindAll() {
    List<LiftAccessTrail> liftAccessTrails = underTest.findAll();

    verify(repository).findAll();
  }

  @Test
  void Should_ReturnEmptyList_When_NoLiftAccessTrail() {
    List<LiftAccessTrail> liftAccessTrails = underTest.findAll();

    assertEquals(liftAccessTrails.size(), 0);
  }

  @Test
  void Should_ReturnListOfSizeOne_When_OneLiftAccessTrail() {
    LiftAccessTrail liftAccessTrail = createLiftAccessTrail();
    when(repository.findAll()).thenReturn(Collections.singletonList(liftAccessTrail));

    List<LiftAccessTrail> liftAccessTrails = underTest.findAll();

    assertEquals(liftAccessTrails.size(), 1);
  }

  @Test
  void Should_CallRepository_When_CreateLiftAccessTrail() {
    LiftAccessTrail newLiftAccessTrail = createLiftAccessTrail();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLiftAccessTrail));

    LiftAccessTrail result = underTest.createLiftAccessTrail(newLiftAccessTrail);

    verify(repository).save(any(LiftAccessTrail.class));
  }

  @Test
  void Should_ReturnLiftAccessTrail_When_createLiftAccessTrail() {
    LiftAccessTrail newLiftAccessTrail = createLiftAccessTrail();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLiftAccessTrail));

    LiftAccessTrail result = underTest.createLiftAccessTrail(newLiftAccessTrail);

    assertEquals(newLiftAccessTrail.getId(), result.getId());
  }

  @Test
  void Should_CallRepository_When_UpdateLiftAccessTrail() {
    LiftAccessTrail newLiftAccessTrail = createLiftAccessTrail();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLiftAccessTrail));

    LiftAccessTrail result =
        underTest.updateLiftAccessTrail(newLiftAccessTrail.getId(), newLiftAccessTrail);

    verify(repository).save(any(LiftAccessTrail.class));
  }

  @Test
  void Should_ReturnLiftAccessTrail_When_UpdateLiftAccessTrail() {
    LiftAccessTrail newLiftAccessTrail = createLiftAccessTrail();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLiftAccessTrail));

    LiftAccessTrail result =
        underTest.updateLiftAccessTrail(newLiftAccessTrail.getId(), newLiftAccessTrail);

    assertEquals(result.getId(), newLiftAccessTrail.getId());
  }

  @Test
  void Should_CallRepository_When_DeleteLiftAccessTrail() {
    LiftAccessTrail LiftAccessTrail = createLiftAccessTrail();

    underTest.deleteLiftAccessTrail(LiftAccessTrail.getId());

    verify(repository).delete(any(UUID.class));
  }

  private LiftAccessTrail createLiftAccessTrail() {
    return new LiftAccessTrail(
        UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "name", "description");
  }
}
