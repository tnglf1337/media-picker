package com.mediapicker.domain.mediathek.medium;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Anime extends Medium {
  private Integer season;
  private Integer folgen;
  private Integer currentFolge;

  public Anime() {}

  public Anime(
    UUID mediumId,
    LocalDateTime erstelltAm,
    String titel,
    Status status,
    Integer rating,
    List<String> notiz,
    Integer season,
    Integer folgen,
    Integer currentFolge) {
    super(mediumId, erstelltAm, titel, MediumTyp.ANIME, status, rating, notiz);
    this.season = season;
    this.folgen = folgen;
    this.currentFolge = currentFolge;
  }

  public boolean inkrementCurrentFolge() {
    if(currentFolge + 1 < folgen) {
      this.currentFolge++;
      return true;
    } else if (currentFolge + 1 == folgen) {
      this.currentFolge++;
      setBeendetStatus();
      return true;
    }
    return false;
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
    if (!(o instanceof Anime anime)) return false;
    if (!super.equals(o)) return false;
    return Objects.equals(getSeason(), anime.getSeason()) && Objects.equals(getFolgen(), anime.getFolgen()) && Objects.equals(getCurrentFolge(), anime.getCurrentFolge());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getSeason(), getFolgen(), getCurrentFolge());
  }

  @Override
  public String toString() {
    return "Anime{" +
      "season=" + season +
      ", folgen=" + folgen +
      ", currentFolge=" + currentFolge +
      '}';
  }
}
