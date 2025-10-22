package com.mediapicker.web;

import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.*;
import com.mediapicker.service.MediathekService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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

  @DeleteMapping("/medium-loeschen/{mediumId}")
  public ResponseEntity<Void> mediumLoeschen(@PathVariable UUID mediumId) {
    mediathekService.deleteByMediumId(mediumId);
    return null;
  }

  @PutMapping("/status-aendern")
  public ResponseEntity<Void> statusAendern() {
    // TODO implement
    return null;
  }

  @GetMapping("/medium-inkrement/{mediumId}")
  public ResponseEntity<Void> mediumInkrementieren(@PathVariable UUID mediumId) {
    mediathekService.inkrementMedium(mediumId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/medium-dekrement/{mediumId}")
  public ResponseEntity<Void> mediumDekrementieren(@PathVariable UUID mediumId) {
    mediathekService.dekrementMedium(mediumId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/medium-rating-inkrement/{mediumId}")
  public ResponseEntity<Void> mediumRatingInkrementieren(@PathVariable UUID mediumId) {
    mediathekService.inkrementMediumRating(mediumId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/medium-rating-dekrement/{mediumId}")
  public ResponseEntity<Void> mediumRatingDekrementieren(@PathVariable UUID mediumId) {
    mediathekService.dekrementMediumRating(mediumId);
    return ResponseEntity.ok().build();
  }
}
