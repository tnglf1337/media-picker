package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.Medium;
import com.mediapicker.domain.mediathek.medium.MediumTyp;
import com.mediapicker.domain.mediathek.medium.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MediumRequestDto {

  private UUID mediumId;
  private LocalDateTime erstelltAm;
  private String titel;
  private MediumTyp mediumTyp;
  private Status status;
  private Integer rating;
  private List<String> notiz;

  public MediumRequestDto(UUID mediumId,
                          LocalDateTime erstelltAm,
                          String titel,
                          MediumTyp mediumTyp,
                          Status status,
                          Integer rating,
                          List<String> notiz) {
    this.mediumId = mediumId;
    this.erstelltAm = erstelltAm;
    this.titel = titel;
    this.mediumTyp = mediumTyp;
    this.status = status;
    this.rating = rating;
    this.notiz = notiz;
  }

  public MediumRequestDto() {
  }

  public UUID getMediumId() {
    return mediumId;
  }

  public void setMediumId(UUID mediumId) {
    this.mediumId = mediumId;
  }

  public LocalDateTime getErstelltAm() {
    return erstelltAm;
  }

  public void setErstelltAm(LocalDateTime erstelltAm) {
    this.erstelltAm = erstelltAm;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public MediumTyp getMediumTyp() {
    return mediumTyp;
  }

  public void setMediumTyp(MediumTyp mediumTyp) {
    this.mediumTyp = mediumTyp;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public List<String> getNotiz() {
    return notiz;
  }

  public void setNotiz(List<String> notiz) {
    this.notiz = notiz;
  }

  @Override
  public String toString() {
    return "MediumRequestDto{" +
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
