package org.buelna.jpaonetoone.repositories;

import org.buelna.jpaonetoone.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
