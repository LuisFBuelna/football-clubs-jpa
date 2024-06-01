package org.buelna.jpaonetoone.mappers;

import org.buelna.jpaonetoone.dtos.footballcompetition.FootballCompetitionDto;
import org.buelna.jpaonetoone.entities.FootballCompetition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FootballCompetitionMapper {

    FootballCompetitionMapper mapper = Mappers.getMapper(FootballCompetitionMapper.class);

    FootballCompetitionDto entityToDto(FootballCompetition competition);
}
