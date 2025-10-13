package com.mediapicker.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MediumController {

  @GetMapping("/user-medien")
  public ResponseEntity<Void> getUserMedium() {
    // TODO implement
    return null;
  }

  @PostMapping("/medium-erstellen")
  public ResponseEntity<Void> mediuErstellen() {
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
