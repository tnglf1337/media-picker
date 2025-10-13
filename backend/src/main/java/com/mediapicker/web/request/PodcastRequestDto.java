package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.Film;
import com.mediapicker.domain.mediathek.medium.MediumTyp;
import com.mediapicker.domain.mediathek.medium.Podcast;
import com.mediapicker.domain.mediathek.medium.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PodcastRequestDto extends MediumRequestDto {
  private Integer kapitel;
  private Integer currentKapitel;

  public PodcastRequestDto(UUID mediumId,
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

  public PodcastRequestDto() {}

  public Podcast toEntity() {
    return new Podcast(
      getMediumId(),
      getErstelltAm(),
      getTitel(),
      getStatus(),
      getRating(),
      getNotiz(),
      kapitel,
      currentKapitel
    );
  }

  public Integer getKapitel() {
    return kapitel;
  }

  public void setKapitel(Integer kapitel) {
    this.kapitel = kapitel;
  }

  public Integer getCurrentKapitel() {
    return currentKapitel;
  }

  public void setCurrentKapitel(Integer currentKapitel) {
    this.currentKapitel = currentKapitel;
  }

  @Override
  public String toString() {
    return "PodcastRequestDto{" +
      "kapitel=" + kapitel +
      ", currentKapitel=" + currentKapitel +
      '}';
  }
}
