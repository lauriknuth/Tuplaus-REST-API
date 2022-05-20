package com.lauriknuth.tuplausrestapi.repositories;

import com.lauriknuth.tuplausrestapi.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository <Game, Long>{

}
