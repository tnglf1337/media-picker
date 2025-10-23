package com.mediapicker.domain.mediathek.medium;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Check(constraints = "rating >= 0 AND rating <= 10")
@Inheritance(strategy = InheritanceType.JOINED)
public class Medium {

  @Id
  @GeneratedValue
  @Column(name="medium_id")
  private UUID mediumId;

  @Column(nullable = false)
  private LocalDateTime erstelltAm;

  @Column(nullable = false, length = 150)
  private String titel;

  @Column(nullable = false)
  private MediumTyp mediumTyp;

  @Column(nullable = false)
  private Status status;

  private Integer rating;

  @ElementCollection
  private List<String> notiz;

  public Medium() {}

  public Medium(UUID mediumId, LocalDateTime erstelltAm, String titel, MediumTyp mediumTyp, Status status, Integer rating, List<String> notiz) {
    this.mediumId = mediumId;
    this.erstelltAm = erstelltAm;
    this.titel = titel;
    this.mediumTyp = mediumTyp;
    this.status = status;
    this.rating = rating;
    this.notiz = notiz;
  }

  public void inkrementRating() {
    if (this.rating < 10) {
      this.rating += 1;
    }
  }

  public void dekrementRating() {
    if (this.rating > 0) {
      this.rating -= 1;
    }
  }

  public boolean isCurrentlyConsuming() {
    return this.status == Status.AM_SCHAUEN ||
      this.status == Status.AM_LESEN ||
      this.status == Status.AM_SPIELEN ||
      this.status == Status.AM_HOEREN;
  }

  public void notizHinzufuegen(String notiz) {
    this.notiz.add(notiz);
  }

  public UUID getMediumId() {
    return mediumId;
  }

  public LocalDateTime getErstelltAm() {
    return erstelltAm;
  }

  public String getTitel() {
    return titel;
  }

  public MediumTyp getMediumTyp() {
    return mediumTyp;
  }

  public Status getStatus() {
    return status;
  }

  public void setBeendetStatus() {
    this.status = Status.BEENDET;
  }

  public Integer getRating() {
    return rating;
  }

  public List<String> getNotiz() {
    return notiz;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Medium medium)) return false;
    return Objects.equals(getMediumId(), medium.getMediumId())  && Objects.equals(getErstelltAm(), medium.getErstelltAm()) && Objects.equals(getTitel(), medium.getTitel()) && getMediumTyp() == medium.getMediumTyp() && getStatus() == medium.getStatus() && Objects.equals(getRating(), medium.getRating()) && Objects.equals(getNotiz(), medium.getNotiz());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMediumId(), getErstelltAm(), getTitel(), getMediumTyp(), getStatus(), getRating(), getNotiz());
  }

  @Override
  public String toString() {
    return "Medium{" +
      "mediumId=" + mediumId +
      ", erstelltAm=" + erstelltAm +
      ", titel='" + titel + '\'' +
      ", mediumTyp=" + mediumTyp +
      ", status=" + status +
      ", rating=" + rating +
      ", notiz=" + notiz +
      '}';
  }
}
