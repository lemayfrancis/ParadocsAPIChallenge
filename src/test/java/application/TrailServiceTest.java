package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lemayfrancis.domain.Trail.ITrailRepository;
import com.lemayfrancis.domain.Trail.Trail;
import com.lemayfrancis.domain.Trail.TrailService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrailServiceTest {
  private ITrailRepository trailRepository;
  private TrailService underTest;

  @BeforeEach
  void setUp() {
    trailRepository = mock(ITrailRepository.class);
    underTest = new TrailService(trailRepository);
  }

  @Test
  void Should_CallRepository_When_FindById() {
    Trail trail = new Trail(UUID.randomUUID(), "name", "description");
    when(underTest.findById(trail.getIdTrail())).thenReturn(Optional.of(trail));

    Optional<Trail> result = underTest.findById(trail.getIdTrail());

    verify(trailRepository).findById(any(UUID.class));
  }

  @Test
  void Should_ReturnTrail_When_ExistingId() {
    Trail trail = createTrail();
    when(underTest.findById(trail.getIdTrail())).thenReturn(Optional.of(trail));

    Optional<Trail> result = underTest.findById(trail.getIdTrail());

    assertEquals(result.orElseThrow(), trail);
  }

  @Test
  void Should_ReturnEmpty_When_NotExistingId() {
    UUID id = UUID.randomUUID();
    when(underTest.findById(id)).thenReturn(Optional.empty());

    Optional<Trail> result = underTest.findById(id);

    assertEquals(result, Optional.empty());
  }

  @Test
  void Should_CallRepository_When_GetAll() {
    List<Trail> trails = underTest.getAll();

    verify(trailRepository).getAll();
  }

  @Test
  void Should_ReturnEmptyList_When_NoTrail() {
    List<Trail> trails = underTest.getAll();

    assertEquals(trails.size(), 0);
  }

  @Test
  void Should_ReturnListOfSizeOne_When_OneTrail() {
    Trail trail = createTrail();
    when(trailRepository.getAll()).thenReturn(Collections.singletonList(trail));

    List<Trail> trails = underTest.getAll();

    assertEquals(trails.size(), 1);
  }

  @Test
  void Should_CallRepository_When_CreateTrail() {
    Trail newTrail = createTrail();
    when(trailRepository.findById(any(UUID.class))).thenReturn(Optional.of(newTrail));

    Trail result = underTest.createTrail(newTrail);

    verify(trailRepository).save(any(Trail.class));
  }

  @Test
  void Should_ReturnTrail_When_CreateTrail() {
    Trail newTrail = createTrail();
    when(trailRepository.findById(any(UUID.class))).thenReturn(Optional.of(newTrail));

    Trail result = underTest.createTrail(newTrail);

    assertEquals(newTrail.getIdTrail(), result.getIdTrail());
  }

  @Test
  void Should_CallRepository_When_UpdateTrail() {
    Trail newTrail = createTrail();
    when(trailRepository.findById(any(UUID.class))).thenReturn(Optional.of(newTrail));

    Trail result = underTest.updateTrail(newTrail.getIdTrail(), newTrail);

    verify(trailRepository).save(any(Trail.class));
  }

  @Test
  void Should_ReturnTrail_When_UpdateTrail() {
    Trail newTrail = createTrail();
    when(trailRepository.findById(any(UUID.class))).thenReturn(Optional.of(newTrail));

    Trail result = underTest.updateTrail(newTrail.getIdTrail(), newTrail);

    assertEquals(result.getIdTrail(), newTrail.getIdTrail());
  }

  @Test
  void Should_CallRepository_When_DeleteTrail() {
    Trail trail = createTrail();

    underTest.deleteTrail(trail.getIdTrail());

    verify(trailRepository).delete(any(UUID.class));
  }

  private Trail createTrail() {
    return new Trail(UUID.randomUUID(), "name", "description");
  }
}
