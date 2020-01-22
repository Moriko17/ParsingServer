package me.moriko17.parsingServer.repository;

import me.moriko17.parsingServer.domain.AnimeEntity;
import org.springframework.data.repository.CrudRepository;

public interface AnimeRepository extends CrudRepository<AnimeEntity, Long> {}
