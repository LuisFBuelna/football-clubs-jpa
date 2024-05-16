package org.buelna.jpaonetoone.services;

import org.buelna.jpaonetoone.dtos.coach.ResponseCoachDto;

import java.util.List;

public interface CoachService {

    ResponseCoachDto saveCoach(ResponseCoachDto coach);

    ResponseCoachDto updateCoach(long id, ResponseCoachDto coach);

    void deleteCoachById(long id);

    ResponseCoachDto getCoachById(long id);

    List<ResponseCoachDto> getAllCoaches();
}
