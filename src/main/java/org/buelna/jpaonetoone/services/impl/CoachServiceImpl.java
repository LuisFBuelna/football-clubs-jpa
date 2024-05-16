package org.buelna.jpaonetoone.services.impl;

import org.buelna.jpaonetoone.dtos.coach.ResponseCoachDto;
import org.buelna.jpaonetoone.entities.Coach;
import org.buelna.jpaonetoone.mappers.CoachMapper;
import org.buelna.jpaonetoone.repositories.CoachRepository;
import org.buelna.jpaonetoone.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public ResponseCoachDto saveCoach(ResponseCoachDto coach) {
        Coach coachEntity = CoachMapper.mapper.coachDtoToCoach(coach);
        Coach savedCoach = coachRepository.save(coachEntity);
        return CoachMapper.mapper.coachToDto(savedCoach);
    }

    @Override
    public ResponseCoachDto updateCoach(long id, ResponseCoachDto coachDto) {
        Coach coach = CoachMapper.mapper.coachDtoToCoach(coachDto);
        coach.setId(id);
        Coach updateCoach = coachRepository.save(coach);
        return CoachMapper.mapper.coachToDto(updateCoach);
    }

    @Override
    public void deleteCoachById(long id) {
        coachRepository.deleteById(id);
    }

    @Override
    public ResponseCoachDto getCoachById(long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Coach not found")
        );
        ResponseCoachDto coachDto = new ResponseCoachDto();

        if (coach != null) {
            coachDto = CoachMapper.mapper.coachToDto(coach);
        }
        return coachDto;
    }

    @Override
    public List<ResponseCoachDto> getAllCoaches() {
        List<Coach> coaches = coachRepository.findAll();
        List<ResponseCoachDto> coachDtos = new ArrayList<>();

        coaches.forEach(coach -> {
            ResponseCoachDto coachDto = CoachMapper.mapper.coachToDto(coach);
            coachDtos.add(coachDto);
        });
        return coachDtos;
    }
}
