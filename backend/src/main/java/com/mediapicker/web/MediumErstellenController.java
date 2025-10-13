package com.mediapicker.web;

import com.mediapicker.domain.mediathek.medium.Videospiel;
import com.mediapicker.web.request.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medium-erstellen")
public class MediumErstellenController {

  @PostMapping("/serie")
  public ResponseEntity<Void> serieErstellen(@RequestBody SerieRequestDto req) {
    // TODO implement
    return null;
  }

  @PostMapping("/film")
  public ResponseEntity<Void> filmErstellen(@RequestBody FilmRequestDto req) {
    // TODO implement
    return null;
  }

  @PostMapping("/anime")
  public ResponseEntity<Void> animeErstellen(@RequestBody AnimeRequestDto req) {
    // TODO implement
    return null;
  }

  @PostMapping("/manga")
  public ResponseEntity<Void> mangaErstellen(@RequestBody MangaRequestDto req) {
    // TODO implement
    return null;
  }

  @PostMapping("/videospiel")
  public ResponseEntity<Void> videospielErstellen(@RequestBody VideospielRequestDto req) {
    // TODO implement
    return null;
  }

  @PostMapping("/buch")
  public ResponseEntity<Void> buchErstellen(@RequestBody BuchRequestDto req) {
    // TODO implement
    return null;
  }

  @PostMapping("/podcast")
  public ResponseEntity<Void> podcastErstellen(@RequestBody PodcastRequestDto req) {
    // TODO implement
    return null;
  }
}
