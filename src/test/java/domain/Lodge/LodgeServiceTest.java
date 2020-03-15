package domain.Lodge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lemayfrancis.domain.Lodge.ILodgeRepository;
import com.lemayfrancis.domain.Lodge.Lodge;
import com.lemayfrancis.domain.Lodge.LodgeService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LodgeServiceTest {
  private ILodgeRepository repository;
  private LodgeService underTest;

  @BeforeEach
  void setUp() {
    repository = mock(ILodgeRepository.class);
    underTest = new LodgeService(repository);
  }

  @Test
  void Should_CallRepository_When_FindById() {
    Lodge lodge = createLodge();
    when(underTest.findById(lodge.getIdLodge())).thenReturn(Optional.of(lodge));

    Optional<Lodge> result = underTest.findById(lodge.getIdLodge());

    verify(repository).findById(any(UUID.class));
  }

  @Test
  void Should_ReturnLodge_When_ExistingId() {
    Lodge lodge = createLodge();
    when(underTest.findById(lodge.getIdLodge())).thenReturn(Optional.of(lodge));

    Optional<Lodge> result = underTest.findById(lodge.getIdLodge());

    assertEquals(result.orElseThrow(), lodge);
  }

  @Test
  void Should_ReturnEmpty_When_NotExistingId() {
    UUID id = UUID.randomUUID();
    when(underTest.findById(id)).thenReturn(Optional.empty());

    Optional<Lodge> result = underTest.findById(id);

    assertEquals(result, Optional.empty());
  }

  @Test
  void Should_CallRepository_When_FindAll() {
    List<Lodge> lodges = underTest.findAll();

    verify(repository).findAll();
  }

  @Test
  void Should_ReturnEmptyList_When_NoLodge() {
    List<Lodge> lodges = underTest.findAll();

    assertEquals(lodges.size(), 0);
  }

  @Test
  void Should_ReturnListOfSizeOne_When_OneLodge() {
    Lodge lodge = createLodge();
    when(repository.findAll()).thenReturn(Collections.singletonList(lodge));

    List<Lodge> lodges = underTest.findAll();

    assertEquals(lodges.size(), 1);
  }

  @Test
  void Should_CallRepository_When_CreateLodge() {
    Lodge newLodge = createLodge();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLodge));

    Lodge result = underTest.createLodge(newLodge);

    verify(repository).save(any(Lodge.class));
  }

  @Test
  void Should_ReturnLodge_When_createLodge() {
    Lodge newLodge = createLodge();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLodge));

    Lodge result = underTest.createLodge(newLodge);

    assertEquals(newLodge.getIdLodge(), result.getIdLodge());
  }

  @Test
  void Should_CallRepository_When_UpdateLodge() {
    Lodge newLodge = createLodge();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLodge));

    Lodge result = underTest.updateLodge(newLodge.getIdLodge(), newLodge);

    verify(repository).save(any(Lodge.class));
  }

  @Test
  void Should_ReturnLodge_When_UpdateLodge() {
    Lodge newLodge = createLodge();
    when(repository.findById(any(UUID.class))).thenReturn(Optional.of(newLodge));

    Lodge result = underTest.updateLodge(newLodge.getIdLodge(), newLodge);

    assertEquals(result.getIdLodge(), newLodge.getIdLodge());
  }

  @Test
  void Should_CallRepository_When_DeleteLodge() {
    Lodge Lodge = createLodge();

    underTest.deleteLodge(Lodge.getIdLodge());

    verify(repository).delete(any(UUID.class));
  }

  private Lodge createLodge() {
    return new Lodge(UUID.randomUUID(), "name", "description");
  }
}
