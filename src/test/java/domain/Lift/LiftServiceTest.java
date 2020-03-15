package domain.Lift;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lemayfrancis.domain.Lift.ILiftRepository;
import com.lemayfrancis.domain.Lift.Lift;
import com.lemayfrancis.domain.Lift.LiftService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LiftServiceTest {
  private ILiftRepository repository;
  private LiftService underTest;

  @BeforeEach
  void setUp() {
    repository = mock(ILiftRepository.class);
    underTest = new LiftService(repository);
  }

  @Test
  void Should_CallRepository_When_FindById() {
    Lift lift = createLift();
    when(underTest.findById(lift.getIdLift())).thenReturn(Optional.of(lift));

    Optional<Lift> result = underTest.findById(lift.getIdLift());

    verify(repository).findById(any(UUID.class));
  }

  @Test
  void Should_ReturnLift_When_ExistingId() {
    Lift lift = createLift();
    when(underTest.findById(lift.getIdLift())).thenReturn(Optional.of(lift));

    Optional<Lift> result = underTest.findById(lift.getIdLift());

    assertEquals(result.orElseThrow(), lift);
  }

  @Test
  void Should_ReturnEmpty_When_NotExistingId() {
    UUID id = UUID.randomUUID();
    when(underTest.findById(id)).thenReturn(Optional.empty());

    Optional<Lift> result = underTest.findById(id);

    assertEquals(result, Optional.empty());
  }

  @Test
  void Should_CallRepository_When_FindAll() {
    List<Lift> lifts = underTest.findAll();

    verify(repository).findAll();
  }

  @Test
  void Should_ReturnEmptyList_When_NoLift() {
    List<Lift> lifts = underTest.findAll();

    assertEquals(lifts.size(), 0);
  }

  @Test
  void Should_ReturnListOfSizeOne_When_OneLift() {
    Lift lift = createLift();
    when(repository.findAll()).thenReturn(Collections.singletonList(lift));

    List<Lift> lifts = underTest.findAll();

    assertEquals(lifts.size(), 1);
  }

  @Test
  void Should_CallRepository_When_CreateLift() {
    Lift newLift = createLift();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLift));

    Lift result = underTest.createLift(newLift);

    verify(repository).save(any(Lift.class));
  }

  @Test
  void Should_ReturnLift_When_createLift() {
    Lift newLift = createLift();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLift));

    Lift result = underTest.createLift(newLift);

    assertEquals(newLift.getIdLift(), result.getIdLift());
  }

  @Test
  void Should_CallRepository_When_UpdateLift() {
    Lift newLift = createLift();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLift));

    Lift result = underTest.updateLift(newLift.getIdLift(), newLift);

    verify(repository).save(any(Lift.class));
  }

  @Test
  void Should_ReturnLift_When_UpdateLift() {
    Lift newLift = createLift();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLift));

    Lift result = underTest.updateLift(newLift.getIdLift(), newLift);

    assertEquals(result.getIdLift(), newLift.getIdLift());
  }

  @Test
  void Should_CallRepository_When_DeleteLift() {
    Lift Lift = createLift();

    underTest.deleteLift(Lift.getIdLift());

    verify(repository).delete(any(UUID.class));
  }

  private Lift createLift() {
    return new Lift(UUID.randomUUID(), "name", "description");
  }
}
