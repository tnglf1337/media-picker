package com.mediapicker.web;

import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.*;
import com.mediapicker.service.MediathekService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MediathekController {

  private MediathekService mediathekService;

  public MediathekController(MediathekService mediathekService) {
    this.mediathekService = mediathekService;
  }

  @GetMapping("/user-medien")
  public ResponseEntity<Mediathek> getUserMedium() {
    Mediathek mediathek = mediathekService.findMediathekByUser();
    return ResponseEntity.ok(mediathek);
  }

  @GetMapping("/get-medien-by-typ/{mediumTyp}")
  public ResponseEntity<List<? extends Medium>> getSerien(@PathVariable MediumTyp mediumTyp) {
    List<? extends Medium> medien = mediathekService.findAllByTyp(mediumTyp);
    return ResponseEntity.ok(medien);
  }

  @DeleteMapping("/medium-loeschen")
  public ResponseEntity<Void> mediumLoeschen() {
    // TODO implement
    return null;
  }

  @PutMapping("/status-aendern")
  public ResponseEntity<Void> statusAendern() {
    // TODO implement
    return null;
  }

  @PutMapping("/medium-inkrementieren")
  public ResponseEntity<Void> mediumInkrementieren() {
    // TODO implement
    return null;
  }
}
