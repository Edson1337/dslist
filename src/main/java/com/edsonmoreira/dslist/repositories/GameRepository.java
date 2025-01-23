package com.edsonmoreira.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edsonmoreira.dslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
