package org.buelna.jpaonetoone.controller;

import org.buelna.jpaonetoone.dtos.footballcompetition.FootballCompetitionDto;
import org.buelna.jpaonetoone.services.FootballCompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions")
public class FootballCompetitionController {

    @Autowired
    private FootballCompetitionService competitionService;

    @GetMapping
    public ResponseEntity<List<FootballCompetitionDto>> getAllCompetitions() {
        return ResponseEntity.ok(competitionService.getFootballCompetitions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballCompetitionDto> getCompetitionById(@PathVariable Long id) {
        return ResponseEntity.ok(competitionService.getFootballCompetitionById(id));
    }

    @PostMapping
    public ResponseEntity<FootballCompetitionDto> createCompetition(@RequestBody FootballCompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionService.saveFootballCompetition(competitionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        competitionService.deleteFootballCompetitionById(id);
        return ResponseEntity.noContent().build();
    }
}
