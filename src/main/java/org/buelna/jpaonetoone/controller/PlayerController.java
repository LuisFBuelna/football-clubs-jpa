package org.buelna.jpaonetoone.controller;

import org.buelna.jpaonetoone.dtos.player.PlayerRequestDto;
import org.buelna.jpaonetoone.dtos.player.PlayerResponseDto;
import org.buelna.jpaonetoone.dtos.player.SavePlayerRequest;
import org.buelna.jpaonetoone.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayeres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDto> createPlayer(@RequestBody SavePlayerRequest player) {
        return ResponseEntity.ok(playerService.savePlayer(player));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@PathVariable("id") Long id, @RequestBody PlayerRequestDto player) {
        return ResponseEntity.ok(playerService.updatePlayer(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }

}
