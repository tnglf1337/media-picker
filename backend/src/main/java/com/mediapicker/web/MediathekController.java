package com.mediapicker.web;

import com.mediapicker.service.MediathekService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MediathekController {

  private MediathekService mediathekService;

  public MediathekController(MediathekService mediathekService) {
    this.mediathekService = mediathekService;
  }

  @GetMapping("/user-medien")
  public ResponseEntity<Void> getUserMedium() {
    // TODO implement
    return null;
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
