package org.buelna.jpaonetoone.repositories;

import org.buelna.jpaonetoone.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
