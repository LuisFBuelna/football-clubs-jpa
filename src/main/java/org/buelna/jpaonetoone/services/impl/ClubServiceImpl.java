package org.buelna.jpaonetoone.services.impl;

import org.buelna.jpaonetoone.dtos.club.RequestClubDto;
import org.buelna.jpaonetoone.dtos.footballcompetition.ResponseClubCompetitionDto;
import org.buelna.jpaonetoone.dtos.club.ResponseClubDto;
import org.buelna.jpaonetoone.dtos.coach.ResponseCoachDto;
import org.buelna.jpaonetoone.entities.Club;
import org.buelna.jpaonetoone.entities.Coach;
import org.buelna.jpaonetoone.entities.FootballCompetition;
import org.buelna.jpaonetoone.repositories.ClubRepository;
import org.buelna.jpaonetoone.repositories.CoachRepository;
import org.buelna.jpaonetoone.repositories.FootballCompetitionRepository;
import org.buelna.jpaonetoone.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private FootballCompetitionRepository competitionRepository;

    @Override
    public List<ResponseClubDto> getAllClubs() {
        List<Club> listClubs = clubRepository.findAll();

        List<ResponseClubDto> responseListClubs = new ArrayList<>();

        listClubs.forEach(club -> {
            List<ResponseClubCompetitionDto> clubCompetitionList = new ArrayList<>();
            ResponseCoachDto responseCoach = new ResponseCoachDto();
            ResponseClubDto responseClub = new ResponseClubDto();
            Coach coach = club.getCoach();
            responseClub.setName(club.getName());

            if (coach != null) {
                responseCoach.setName(club.getCoach().getName());
                responseCoach.setLastName(club.getCoach().getLastName());
                responseCoach.setNationality(club.getCoach().getNationality());
                responseCoach.setAge(club.getCoach().getAge());
                responseClub.setCoach(responseCoach);
            }
            if (!club.getFootballCompetitions().isEmpty()) {
                club.getFootballCompetitions().forEach(competition -> {
                    ResponseClubCompetitionDto competitionDto = new ResponseClubCompetitionDto();
                    competitionDto.setName(competition.getName());
                    competitionDto.setStartDate(competition.getStartDate());
                    competitionDto.setEndDate(competition.getEndDate());

                    clubCompetitionList.add(competitionDto);
                });
            }
            responseListClubs.add(responseClub);
        });

        return responseListClubs;
    }

    @Override
    public ResponseClubDto getClubById(Long id) {
        Club findClub = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        try {
            Coach coach = findClub.getCoach();
            ResponseCoachDto responseCoach = new ResponseCoachDto(
                    coach.getName(),
                    coach.getLastName(),
                    coach.getNationality(),
                    coach.getAge());

            List<ResponseClubCompetitionDto> clubCompetitionList = new ArrayList<>();

            findClub.getFootballCompetitions().forEach(clubCompetition -> {
                ResponseClubCompetitionDto competitionDto = new ResponseClubCompetitionDto();
                competitionDto.setName(clubCompetition.getName());
                competitionDto.setStartDate(clubCompetition.getStartDate());
                competitionDto.setEndDate(clubCompetition.getEndDate());
                clubCompetitionList.add(competitionDto);
            });

            return new ResponseClubDto(findClub.getName(), responseCoach, clubCompetitionList);
        } catch (Exception e) {
            throw new RuntimeException("Club not found");
        }
    }

    @Override
    public ResponseClubDto saveClub(RequestClubDto requestClubDto) {

        if (requestClubDto == null) {
            throw new IllegalArgumentException("RequestClubDto is null");
        }
        try {
            Coach coach = coachRepository.findById(requestClubDto.getCoachId()).orElseThrow(() -> new IllegalArgumentException("Coach not found"));
            Club saveClub = new Club();
            saveClub.setName(requestClubDto.getName());

            List<FootballCompetition> competitions = competitionRepository.findAllById(requestClubDto.getCompetitionIds());

            return getResponseClubDto(coach, saveClub, competitions);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not save club", e);
        }
    }

    @Override
    public ResponseClubDto updateClub(RequestClubDto requestClubDto) {

        if (requestClubDto == null) {
            throw new IllegalArgumentException("RequestClubDto is null");
        }
        try {

            Coach coach = coachRepository.findById(requestClubDto.getCoachId())
                    .orElseThrow(() -> new IllegalArgumentException("Coach not found"));
            Club saveClub = clubRepository.findById(requestClubDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Club not found"));

            List<FootballCompetition> footballCompetitionList = competitionRepository.findAllById(requestClubDto.getCompetitionIds());

            footballCompetitionList.forEach(competition -> {
                FootballCompetition footballCompetition = new FootballCompetition();
                footballCompetition.setName(competition.getName());
                footballCompetition.setStartDate(competition.getStartDate());
                footballCompetition.setEndDate(competition.getEndDate());

                footballCompetitionList.add(footballCompetition);
            });
            return getResponseClubDto(coach, saveClub, footballCompetitionList);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not update club");
        }

    }

    @Override
    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }

    private ResponseClubDto getResponseClubDto(Coach coach, Club saveClub, List<FootballCompetition> competitions) {
        saveClub.setCoach(coach);
        Club savedClub = clubRepository.save(saveClub);

        ResponseCoachDto responseCoachDto = new ResponseCoachDto(
                savedClub.getCoach().getName(),
                savedClub.getCoach().getLastName(),
                savedClub.getCoach().getNationality(),
                savedClub.getCoach().getAge());

        List<ResponseClubCompetitionDto> clubCompetitionList = new ArrayList<>();
        competitions.forEach(competition -> {
            ResponseClubCompetitionDto competitionDto = new ResponseClubCompetitionDto();
            competitionDto.setName(competition.getName());
            competitionDto.setStartDate(competition.getStartDate());
            competitionDto.setEndDate(competition.getEndDate());
            clubCompetitionList.add(competitionDto);
        });


        ResponseClubDto responseClubDto = new ResponseClubDto();
        responseClubDto.setName(savedClub.getName());
        responseClubDto.setCoach(responseCoachDto);
        responseClubDto.setCompetition(clubCompetitionList);
        return responseClubDto;
    }
}
