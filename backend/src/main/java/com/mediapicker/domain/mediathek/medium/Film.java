package com.mediapicker.domain.mediathek.medium;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Film extends Medium {

  public Film() {}

  public Film(
      UUID mediumId,
      LocalDateTime erstelltAm,
      String titel,
      Status status,
      Integer rating,
      List<String> notiz) {
    super(mediumId, erstelltAm, titel, MediumTyp.FILM, status, rating, notiz);
  }
}
