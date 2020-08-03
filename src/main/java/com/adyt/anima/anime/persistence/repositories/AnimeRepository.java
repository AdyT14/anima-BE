package com.adyt.anima.anime.persistence.repositories;

import com.adyt.anima.anime.persistence.entities.AnimeJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<AnimeJpa, Long> {

}
