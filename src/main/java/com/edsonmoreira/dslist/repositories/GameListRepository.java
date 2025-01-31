package com.edsonmoreira.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.edsonmoreira.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{
	
	@Modifying
	@Query(nativeQuery = true, value = """
	        UPDATE tb_game_list SET name = :name
	        WHERE id = :listId
	    """)
	void updatetGameList(Long listId, String name);
	
	@Modifying
	@Query(nativeQuery = true, value = """
			UPDATE tb_belonging SET position = :newPosition
			WHERE list_id = :listId AND game_id = :gameId
			""")
	void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
	
	@Modifying
	@Query(nativeQuery = true, value = """
	        INSERT INTO tb_belonging (list_id, game_id, position)
	        VALUES (:listId, :gameId, :position)
	    """)
	void insertBelonging(Long listId, Long gameId, Integer position);
	
	@Query(nativeQuery = true, value = """
		    SELECT COUNT(*) FROM tb_belonging 
		    WHERE list_id = :listId
		""")
		int countGamesInList(Long listId);
}
