package org.buelna.jpaonetoone.repositories;

import org.buelna.jpaonetoone.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
