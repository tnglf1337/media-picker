package com.mediapicker.domain.mediathek.medium;

import jakarta.persistence.Entity;

import java.util.Objects;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Videospiel extends Medium {
  private Platform platform;

  public Videospiel() {}

  public Videospiel(
    UUID mediumId,
    LocalDateTime erstelltAm,
    String titel,
    Status status,
    Integer rating,
    List<String> notiz,
    Platform platform) {
    super(mediumId, erstelltAm, titel, MediumTyp.VIDEOSPIEL, status, rating, notiz);
    this.platform = platform;
  }

  public Platform getPlatform() {
    return platform;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Videospiel that)) return false;
    if (!super.equals(o)) return false;
    return getPlatform() == that.getPlatform();
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getPlatform());
  }

  @Override
  public String toString() {
    return "Videospiel{" +
      "platform=" + platform +
      '}';
  }
}
