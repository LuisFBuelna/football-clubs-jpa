package org.buelna.jpaonetoone.mappers;

import org.buelna.jpaonetoone.dtos.coach.ResponseCoachDto;
import org.buelna.jpaonetoone.entities.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoachMapper {

CoachMapper mapper = Mappers.getMapper(CoachMapper.class);

ResponseCoachDto coachToDto(Coach coach);

Coach coachDtoToCoach(ResponseCoachDto dto);
}
