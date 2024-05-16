package org.buelna.jpaonetoone.controller;

import org.buelna.jpaonetoone.dtos.coach.ResponseCoachDto;
import org.buelna.jpaonetoone.entities.Coach;
import org.buelna.jpaonetoone.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coachs")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping
    public List<ResponseCoachDto> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    @GetMapping("/{id}")
    public ResponseCoachDto getCoachById(@PathVariable int id) {
        return coachService.getCoachById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseCoachDto> saveCoach(@RequestBody ResponseCoachDto coach) {
        return ResponseEntity.ok(coachService.saveCoach(coach));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseCoachDto> updateCoach(@PathVariable long id, @RequestBody ResponseCoachDto coach) {
        return ResponseEntity.ok(coachService.updateCoach(id, coach));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable long id) {
        coachService.deleteCoachById(id);
        return ResponseEntity.noContent().build();
    }
}
