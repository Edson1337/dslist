package com.edsonmoreira.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edsonmoreira.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{

}
