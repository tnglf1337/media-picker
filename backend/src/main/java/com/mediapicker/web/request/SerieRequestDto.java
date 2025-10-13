package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.MediumTyp;
import com.mediapicker.domain.mediathek.medium.Podcast;
import com.mediapicker.domain.mediathek.medium.Serie;
import com.mediapicker.domain.mediathek.medium.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SerieRequestDto extends MediumRequestDto{
  private Integer season;
  private Integer folgen;
  private Integer currentFolge;

  public SerieRequestDto(UUID mediumId,
                         LocalDateTime erstelltAm,
                         String titel,
                         MediumTyp mediumTyp,
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

  public SerieRequestDto() {}

  public Serie toEntity() {
    return new Serie(
      getMediumId(),
      getErstelltAm(),
      getTitel(),
      getStatus(),
      getRating(),
      getNotiz(),
      season,
      folgen,
      currentFolge
    );
  }

  public Integer getSeason() {
    return season;
  }

  public void setSeason(Integer season) {
    this.season = season;
  }

  public Integer getFolgen() {
    return folgen;
  }

  public void setFolgen(Integer folgen) {
    this.folgen = folgen;
  }

  public Integer getCurrentFolge() {
    return currentFolge;
  }

  public void setCurrentFolge(Integer currentFolge) {
    this.currentFolge = currentFolge;
  }

  @Override
  public String toString() {
    return "SerieRequestDto{" +
      "season=" + season +
      ", folgen=" + folgen +
      ", currentFolge=" + currentFolge +
      '}';
  }
}
