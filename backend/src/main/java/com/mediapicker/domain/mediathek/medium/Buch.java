package com.mediapicker.domain.mediathek.medium;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Buch extends Medium {

  private Integer seiten;
  private Integer currentSeite;

  public Buch() {}

  public Buch(
    UUID mediumId,
    LocalDateTime erstelltAm,
    String titel,
    Status status,
    Integer rating,
    List<String> notiz,
    Integer seiten,
    Integer currentSeite) {
      super(mediumId, erstelltAm, titel, MediumTyp.BUCH, status, rating, notiz);
      this.seiten = seiten;
      this.currentSeite = currentSeite;
  }

  public boolean inkrementCurrentSeite() {
    if(currentSeite + 1 < seiten) {
      this.currentSeite++;
      return true;
    } else if (currentSeite + 1 == seiten) {
      this.currentSeite++;
      setBeendetStatus();
      return true;
    }
    return false;
  }

  public boolean dekrementCurrentSeite() {
    if (currentSeite - 1 >= 0) {
      this.currentSeite--;
      return true;
    }
    return false;
  }

  public Integer getSeiten() {
    return seiten;
  }

  public Integer getCurrentSeite() {
    return currentSeite;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Buch buch)) return false;
    if (!super.equals(o)) return false;
    return Objects.equals(getSeiten(), buch.getSeiten()) && Objects.equals(getCurrentSeite(), buch.getCurrentSeite());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getSeiten(), getCurrentSeite());
  }

  @Override
  public String toString() {
    return "Buch{" +
      "seiten=" + seiten +
      ", currentSeite=" + currentSeite +
      '}';
  }
}
