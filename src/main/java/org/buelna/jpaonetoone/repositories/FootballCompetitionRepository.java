package org.buelna.jpaonetoone.repositories;

import org.buelna.jpaonetoone.entities.FootballCompetition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballCompetitionRepository extends JpaRepository<FootballCompetition, Long> {
}
