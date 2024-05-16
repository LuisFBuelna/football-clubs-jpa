package org.buelna.jpaonetoone.services;

import org.buelna.jpaonetoone.dtos.footballcompetition.FootballCompetitionDto;

import java.util.List;

public interface FootballCompetitionService {

    List<FootballCompetitionDto> getFootballCompetitions();

    FootballCompetitionDto getFootballCompetitionById(Long id);

    FootballCompetitionDto saveFootballCompetition(FootballCompetitionDto competition);

    void deleteFootballCompetitionById(Long id);
}
