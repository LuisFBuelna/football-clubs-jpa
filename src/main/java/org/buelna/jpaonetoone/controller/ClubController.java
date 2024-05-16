package org.buelna.jpaonetoone.controller;

import org.buelna.jpaonetoone.dtos.club.RequestClubDto;
import org.buelna.jpaonetoone.dtos.club.ResponseClubDto;
import org.buelna.jpaonetoone.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    @Autowired
    ClubService clubService;

    @GetMapping
    public ResponseEntity<List<ResponseClubDto>> getClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClubDto> getClubById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseClubDto> saveClub (@RequestBody RequestClubDto requestClub) {
        return ResponseEntity.ok(clubService.saveClub(requestClub));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseClubDto> updateClub(@PathVariable Long id, @RequestBody RequestClubDto requestClub) {
        return ResponseEntity.ok(clubService.updateClub(requestClub));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
}
