package org.buelna.jpaonetoone.services.impl;

import org.buelna.jpaonetoone.dtos.footballcompetition.FootballCompetitionDto;
import org.buelna.jpaonetoone.entities.FootballCompetition;
import org.buelna.jpaonetoone.repositories.FootballCompetitionRepository;
import org.buelna.jpaonetoone.services.FootballCompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FootballCompetitionServiceImpl implements FootballCompetitionService {

    @Autowired
    private FootballCompetitionRepository competitionRepository;

    @Override
    public List<FootballCompetitionDto> getFootballCompetitions() {
        List<FootballCompetition> competitions = competitionRepository.findAll();
        List<FootballCompetitionDto> competitionsDto = new ArrayList<>();

        competitions.forEach(competition -> {
            FootballCompetitionDto competitionDto = new FootballCompetitionDto();
            competitionDto.setName(competition.getName());
            competitionDto.setQuantityPrice(competition.getQuantityPrice());
            competitionDto.setStartDate(competition.getStartDate());
            competitionDto.setEndDate(competition.getEndDate());

            competitionsDto.add(competitionDto);
        });
        return competitionsDto;
    }

    @Override
    public FootballCompetitionDto getFootballCompetitionById(Long id) {

        FootballCompetition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        FootballCompetitionDto competitionDto = new FootballCompetitionDto();

        competitionDto.setName(competition.getName());
        competitionDto.setQuantityPrice(competition.getQuantityPrice());
        competitionDto.setStartDate(competition.getStartDate());
        competitionDto.setEndDate(competition.getEndDate());

        return competitionDto;
    }

    @Override
    public FootballCompetitionDto saveFootballCompetition(FootballCompetitionDto competition) {
        try {
            FootballCompetition competitionEntity = new FootballCompetition();

            competitionEntity.setName(competition.getName());
            competitionEntity.setQuantityPrice(competition.getQuantityPrice());
            competitionEntity.setStartDate(competition.getStartDate());
            competitionEntity.setEndDate(competition.getEndDate());
            competitionRepository.save(competitionEntity);

            FootballCompetitionDto competitionDto = new FootballCompetitionDto();
            competitionDto.setName(competition.getName());
            competitionDto.setQuantityPrice(competition.getQuantityPrice());
            competitionDto.setStartDate(competition.getStartDate());
            competitionDto.setEndDate(competition.getEndDate());

            return competitionDto;
        } catch (Exception e) {
            throw new RuntimeException("Saving Football Competition failed");
        }
    }

    @Override
    public void deleteFootballCompetitionById(Long id) {
        FootballCompetition findCompetition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        if (findCompetition != null) {
            competitionRepository.delete(findCompetition);
        }
    }
}
