package com.lemayfrancis.domain.Lodge;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILodgeService {
  public Optional<Lodge> findById(UUID id);

  public List<Lodge> findAll();

  public Lodge createLodge(Lodge lodge);

  public Lodge updateLodge(UUID id, Lodge lodge);

  public void deleteLodge(UUID id);
}
