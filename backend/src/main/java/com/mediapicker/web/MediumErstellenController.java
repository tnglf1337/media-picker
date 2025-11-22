package com.mediapicker.web;

import com.mediapicker.service.MediathekService;
import com.mediapicker.web.request.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medium-erstellen")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class MediumErstellenController {

  private final MediathekService mediathekService;

  public MediumErstellenController(MediathekService mediathekService) {
    this.mediathekService = mediathekService;
  }

  @PostMapping("/serie")
  public ResponseEntity<Void> serieErstellen(@RequestBody SerieRequestDto req) {
    boolean success = mediathekService.mediumErstellen(req.toEntity());
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/film")
  public ResponseEntity<Void> filmErstellen(@RequestBody FilmRequestDto req) {
    boolean success = mediathekService.mediumErstellen(req.toEntity());
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/anime")
  public ResponseEntity<Void> animeErstellen(@RequestBody AnimeRequestDto req) {
    boolean success = mediathekService.mediumErstellen(req.toEntity());
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/manga")
  public ResponseEntity<Void> mangaErstellen(@RequestBody MangaRequestDto req) {
    boolean success = mediathekService.mediumErstellen(req.toEntity());
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/videospiel")
  public ResponseEntity<Void> videospielErstellen(@RequestBody VideospielRequestDto req) {
    boolean success = mediathekService.mediumErstellen(req.toEntity());
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/buch")
  public ResponseEntity<Void> buchErstellen(@RequestBody BuchRequestDto req) {
    boolean success = mediathekService.mediumErstellen(req.toEntity());
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/podcast")
  public ResponseEntity<Void> podcastErstellen(@RequestBody PodcastRequestDto req) {
    boolean success = mediathekService.mediumErstellen(req.toEntity());
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.internalServerError().build();
    }
  }
}
