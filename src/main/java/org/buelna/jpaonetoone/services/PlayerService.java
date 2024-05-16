package org.buelna.jpaonetoone.services;

import org.buelna.jpaonetoone.dtos.player.PlayerRequestDto;
import org.buelna.jpaonetoone.dtos.player.PlayerResponseDto;
import org.buelna.jpaonetoone.dtos.player.SavePlayerRequest;

import java.util.List;

public interface PlayerService {

    PlayerResponseDto savePlayer(SavePlayerRequest player);

    PlayerResponseDto updatePlayer(PlayerRequestDto player);

    void deletePlayerById(Long id);

    PlayerResponseDto getPlayerById(Long id);

    List<PlayerResponseDto> getAllPlayeres();
}
