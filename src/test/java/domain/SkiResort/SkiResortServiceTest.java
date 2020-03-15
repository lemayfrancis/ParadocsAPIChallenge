package domain.SkiResort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lemayfrancis.domain.Lift.ILiftRepository;
import com.lemayfrancis.domain.SkiResort.ISkiResortRepository;
import com.lemayfrancis.domain.SkiResort.SkiResort;
import com.lemayfrancis.domain.SkiResort.SkiResortService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkiResortServiceTest {
  private ISkiResortRepository repository;
  private ILiftRepository liftRepository;
  private SkiResortService underTest;

  @BeforeEach
  void setUp() {
    repository = mock(ISkiResortRepository.class);
    underTest = new SkiResortService(repository, liftRepository);
  }

  @Test
  void Should_CallRepository_When_FindById() {
    SkiResort resort = createSkiResort();
    when(underTest.findById(resort.getIdResort())).thenReturn(Optional.of(resort));

    Optional<SkiResort> result = underTest.findById(resort.getIdResort());

    verify(repository).findById(any(UUID.class));
  }

  @Test
  void Should_ReturnSkiResort_When_ExistingId() {
    SkiResort resort = createSkiResort();
    when(underTest.findById(resort.getIdResort())).thenReturn(Optional.of(resort));

    Optional<SkiResort> result = underTest.findById(resort.getIdResort());

    assertEquals(result.orElseThrow(), resort);
  }

  @Test
  void Should_ReturnEmpty_When_NotExistingId() {
    UUID id = UUID.randomUUID();
    when(underTest.findById(id)).thenReturn(Optional.empty());

    Optional<SkiResort> result = underTest.findById(id);

    assertEquals(result, Optional.empty());
  }

  @Test
  void Should_CallRepository_When_FindAll() {
    List<SkiResort> resorts = underTest.findAll();

    verify(repository).findAll();
  }

  @Test
  void Should_ReturnEmptyList_When_NoSkiResort() {
    List<SkiResort> resorts = underTest.findAll();

    assertEquals(resorts.size(), 0);
  }

  @Test
  void Should_ReturnListOfSizeOne_When_OneSkiResort() {
    SkiResort resort = createSkiResort();
    when(repository.findAll()).thenReturn(Collections.singletonList(resort));

    List<SkiResort> resorts = underTest.findAll();

    assertEquals(resorts.size(), 1);
  }

  @Test
  void Should_CallRepository_When_CreateSkiResort() {
    SkiResort newSkiResort = createSkiResort();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newSkiResort));

    SkiResort result = underTest.createSkiResort(newSkiResort, newSkiResort.getLifts());

    verify(repository).save(any(SkiResort.class));
  }

  @Test
  void Should_ReturnSkiResort_When_createSkiResort() {
    SkiResort newSkiResort = createSkiResort();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newSkiResort));

    SkiResort result = underTest.createSkiResort(newSkiResort, newSkiResort.getLifts());

    assertEquals(newSkiResort.getIdResort(), result.getIdResort());
  }

  @Test
  void Should_CallRepository_When_UpdateSkiResort() {
    SkiResort newSkiResort = createSkiResort();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newSkiResort));

    SkiResort result = underTest.updateSkiResort(newSkiResort.getIdResort(), newSkiResort);

    verify(repository).save(any(SkiResort.class));
  }

  @Test
  void Should_ReturnSkiResort_When_UpdateSkiResort() {
    SkiResort newSkiResort = createSkiResort();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newSkiResort));

    SkiResort result = underTest.updateSkiResort(newSkiResort.getIdResort(), newSkiResort);

    assertEquals(result.getIdResort(), newSkiResort.getIdResort());
  }

  @Test
  void Should_CallRepository_When_DeleteSkiResort() {
    SkiResort SkiResort = createSkiResort();

    underTest.deleteSkiResort(SkiResort.getIdResort());

    verify(repository).delete(any(UUID.class));
  }

  private SkiResort createSkiResort() {
    return new SkiResort(UUID.randomUUID(), "name", "description", new ArrayList<>());
  }
}
