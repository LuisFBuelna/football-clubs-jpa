package org.buelna.jpaonetoone.services.impl;

import org.buelna.jpaonetoone.dtos.club.ResponseClubDto;
import org.buelna.jpaonetoone.dtos.coach.ResponseCoachDto;
import org.buelna.jpaonetoone.dtos.player.PlayerRequestDto;
import org.buelna.jpaonetoone.dtos.player.PlayerResponseDto;
import org.buelna.jpaonetoone.dtos.player.SavePlayerRequest;
import org.buelna.jpaonetoone.entities.Club;
import org.buelna.jpaonetoone.entities.Player;
import org.buelna.jpaonetoone.repositories.ClubRepository;
import org.buelna.jpaonetoone.repositories.PlayerRepository;
import org.buelna.jpaonetoone.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public PlayerResponseDto savePlayer(SavePlayerRequest player) {
        Club club = clubRepository.findById(player.getClub())
                .orElseThrow(() -> new RuntimeException("Club not found"));
        try {
            Player requestPlayer = new Player();
            requestPlayer.setName(player.getName());
            requestPlayer.setLastName(player.getLastName());
            requestPlayer.setNationality(player.getNationality());
            requestPlayer.setAge(player.getAge());
            requestPlayer.setPosition(player.getPosition());
            requestPlayer.setClub(club);
            Player savedPlayer = playerRepository.save(requestPlayer);

            ResponseClubDto clubResponse = new ResponseClubDto();
            ResponseCoachDto coach = new ResponseCoachDto();

            PlayerResponseDto playerResponse = new PlayerResponseDto(
                    requestPlayer.getName(),
                    requestPlayer.getLastName(),
                    requestPlayer.getNationality(),
                    requestPlayer.getAge(),
                    requestPlayer.getPosition());
            Optional<Club> optionalClub = clubRepository.findById(savedPlayer.getClub().getId());
            if (optionalClub.isPresent()) {
                club.setName(optionalClub.get().getName());
                coach.setName(optionalClub.get().getCoach().getName());
                coach.setLastName(optionalClub.get().getCoach().getLastName());
                coach.setNationality(optionalClub.get().getCoach().getNationality());
                coach.setAge(optionalClub.get().getCoach().getAge());
                clubResponse.setCoach(coach);

                playerResponse.setClub(clubResponse);
            }
            return playerResponse;

        } catch (Exception e) {
            throw new RuntimeException("Saving player failed", e);
        }
    }

    @Override
    public PlayerResponseDto updatePlayer(PlayerRequestDto player) {

        Player updatePlayer = playerRepository.findById(player.getId())
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Club foundClub = clubRepository.findById(player.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found"));
        updatePlayer.setClub(foundClub);

        playerRepository.save(updatePlayer);

        ResponseClubDto club = new ResponseClubDto();
        ResponseCoachDto coach = new ResponseCoachDto();

        PlayerResponseDto playerResponse = new PlayerResponseDto(
                updatePlayer.getName(),
                updatePlayer.getLastName(),
                updatePlayer.getNationality(),
                updatePlayer.getAge(),
                updatePlayer.getPosition());
        Optional<Club> optionalClub = clubRepository.findById(updatePlayer.getClub().getId());
        if (optionalClub.isPresent()) {
            club.setName(optionalClub.get().getName());
            coach.setName(optionalClub.get().getCoach().getName());
            coach.setLastName(optionalClub.get().getCoach().getLastName());
            coach.setNationality(optionalClub.get().getCoach().getNationality());
            coach.setAge(optionalClub.get().getCoach().getAge());
            club.setCoach(coach);

            playerResponse.setClub(club);
        }
        return playerResponse;
    }

    @Override
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public PlayerResponseDto getPlayerById(Long id) {
        Player playerRequest = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        try {
            ResponseClubDto club = new ResponseClubDto();
            ResponseCoachDto coach = new ResponseCoachDto();
            PlayerResponseDto playerResponse = new PlayerResponseDto(
                    playerRequest.getName(),
                    playerRequest.getLastName(),
                    playerRequest.getNationality(),
                    playerRequest.getAge(),
                    playerRequest.getPosition());
            Optional<Club> optionalClub = clubRepository.findById(playerRequest.getClub().getId());
            if (optionalClub.isPresent()) {
                club.setName(optionalClub.get().getName());
                coach.setName(optionalClub.get().getCoach().getName());
                coach.setLastName(optionalClub.get().getCoach().getLastName());
                coach.setNationality(optionalClub.get().getCoach().getNationality());
                coach.setAge(optionalClub.get().getCoach().getAge());
                club.setCoach(coach);

                playerResponse.setClub(club);
            }

            return playerResponse;
        } catch (Exception e) {
            throw new RuntimeException("Saving player failed", e);
        }
    }

    @Override
    public List<PlayerResponseDto> getAllPlayeres() {
        List<Player> playerList = playerRepository.findAll();

        try {
            List<PlayerResponseDto> playerResponseDtoList = new ArrayList<>();

            playerList.forEach(player -> {
                ResponseClubDto club = new ResponseClubDto();
                ResponseCoachDto coach = new ResponseCoachDto();
                PlayerResponseDto playerResponse = new PlayerResponseDto(
                        player.getName(),
                        player.getLastName(),
                        player.getNationality(),
                        player.getAge(),
                        player.getPosition());

                if (player.getClub() != null) {
                    club.setName(player.getClub().getName());
                    coach.setName(player.getClub().getCoach().getName());
                    coach.setLastName(player.getClub().getCoach().getLastName());
                    coach.setNationality(player.getClub().getCoach().getNationality());
                    coach.setAge(player.getClub().getCoach().getAge());
                    club.setCoach(coach);

                    playerResponse.setClub(club);
                }

                playerResponseDtoList.add(playerResponse);
            });
            return playerResponseDtoList;
        } catch (Exception e) {
            throw new RuntimeException("Saving player failed", e);
        }

    }
}
