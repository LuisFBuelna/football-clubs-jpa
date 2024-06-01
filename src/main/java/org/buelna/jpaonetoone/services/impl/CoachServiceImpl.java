package org.buelna.jpaonetoone.services.impl;

import org.buelna.jpaonetoone.dtos.coach.ResponseCoachDto;
import org.buelna.jpaonetoone.entities.Coach;
import org.buelna.jpaonetoone.exceptions.BadRequestException;
import org.buelna.jpaonetoone.exceptions.InternalServerException;
import org.buelna.jpaonetoone.exceptions.NotFoundException;
import org.buelna.jpaonetoone.mappers.CoachMapper;
import org.buelna.jpaonetoone.repositories.CoachRepository;
import org.buelna.jpaonetoone.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public ResponseCoachDto saveCoach(ResponseCoachDto coach) {
        Coach coachEntity = CoachMapper.mapper.coachDtoToCoach(coach);
        try {
            Coach savedCoach = coachRepository.save(coachEntity);
            return CoachMapper.mapper.coachToDto(savedCoach);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Bad request");
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }

    }

    @Override
    public ResponseCoachDto updateCoach(long id, ResponseCoachDto coachDto) {
        Coach coach = CoachMapper.mapper.coachDtoToCoach(coachDto);
        coach.setId(id);
        try {
            Coach updateCoach = coachRepository.save(coach);
            return CoachMapper.mapper.coachToDto(updateCoach);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Bad request");
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public void deleteCoachById(long id) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Coach not found"));

        if (coach != null) {
            coachRepository.deleteById(coach.getId());
        }
    }

    @Override
    public ResponseCoachDto getCoachById(long id) {
        Coach coach = coachRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Coach not found")
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
