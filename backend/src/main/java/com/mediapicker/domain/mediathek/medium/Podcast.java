package com.mediapicker.domain.mediathek.medium;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Podcast extends Medium {

  private Integer kapitel;
  private Integer currentKapitel;

  public Podcast() {}

  public Podcast(
    UUID mediumId,
    LocalDateTime erstelltAm,
    String titel,
    Status status,
    Integer rating,
    List<String> notiz,
    Integer kapitel,
    Integer currentKapitel) {
      super(mediumId, erstelltAm, titel, MediumTyp.PODCAST, status, rating, notiz);
      this.kapitel = kapitel;
      this.currentKapitel = currentKapitel;
  }

  public boolean inkrementCurrentKapitel() {
    if(currentKapitel + 1 < kapitel) {
      this.currentKapitel++;
      return true;
    } else if (currentKapitel + 1 == kapitel) {
      this.currentKapitel++;
      setBeendetStatus();
      return true;
    }
    return false;
  }

  public boolean dekrementCurrentKapitel() {
    if (currentKapitel - 1 >= 0) {
      this.currentKapitel--;
      return true;
    }
    return false;
  }

  public Integer getKapitel() {
    return kapitel;
  }

  public Integer getCurrentKapitel() {
    return currentKapitel;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Podcast podcast)) return false;
    if (!super.equals(o)) return false;
    return Objects.equals(getKapitel(), podcast.getKapitel()) && Objects.equals(getCurrentKapitel(), podcast.getCurrentKapitel());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getKapitel(), getCurrentKapitel());
  }

  @Override
  public String toString() {
    return "Podcast{" +
      "kapitel=" + kapitel +
      ", currentKapitel=" + currentKapitel +
      '}';
  }
}
