package com.mediapicker.domain.mediathek.medium;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Serie extends Medium {
  private Integer season;
  private Integer folgen;
  private Integer currentFolge;

  public Serie() {}

  public Serie(
    UUID mediumId,
    LocalDateTime erstelltAm,
    String titel,
    Status status,
    Integer rating,
    List<String> notiz,
    Integer season,
    Integer folgen,
    Integer currentFolge) {
    super(mediumId, erstelltAm, titel, MediumTyp.SERIE, status, rating, notiz);
    this.season = season;
    this.folgen = folgen;
    this.currentFolge = currentFolge;
  }

  public void inkrementCurrentFolge() {
    this.currentFolge++;
  }

  public Integer getSeason() {
    return season;
  }

  public Integer getFolgen() {
    return folgen;
  }

  public Integer getCurrentFolge() {
    return currentFolge;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Serie serie)) return false;
    if (!super.equals(o)) return false;
    return Objects.equals(getSeason(), serie.getSeason()) && Objects.equals(getFolgen(), serie.getFolgen()) && Objects.equals(getCurrentFolge(), serie.getCurrentFolge());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getSeason(), getFolgen(), getCurrentFolge());
  }

  @Override
  public String toString() {
    return "Serie{" +
      "season=" + season +
      ", folgen=" + folgen +
      ", currentFolge=" + currentFolge +
      '}';
  }
}
