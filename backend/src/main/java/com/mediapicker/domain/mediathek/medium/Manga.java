package com.mediapicker.domain.mediathek.medium;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Manga extends Medium {
  private Integer band;
  private Integer kapitel;
  private Integer currentKapitel;

  public Manga() {}

  public Manga(
    UUID mediumId,
    LocalDateTime erstelltAm,
    String titel,
    Status status,
    Integer rating,
    List<String> notiz,
    Integer band,
    Integer kapitel,
    Integer currentKapitel) {
    super(mediumId, erstelltAm, titel, MediumTyp.MANGA, status, rating, notiz);
    this.band = band;
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

  public Integer getBand() {
    return band;
  }

  public Integer getKapitel() {
    return kapitel;
  }

  public Integer getCurrentKapitel() {
    return currentKapitel;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Manga manga)) return false;
    if (!super.equals(o)) return false;
    return Objects.equals(getBand(), manga.getBand()) && Objects.equals(getKapitel(), manga.getKapitel()) && Objects.equals(getCurrentKapitel(), manga.getCurrentKapitel());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getBand(), getKapitel(), getCurrentKapitel());
  }

  @Override
  public String toString() {
    return "Manga{" +
      "band=" + band +
      ", kapitel=" + kapitel +
      ", currentKapitel=" + currentKapitel +
      '}';
  }
}
