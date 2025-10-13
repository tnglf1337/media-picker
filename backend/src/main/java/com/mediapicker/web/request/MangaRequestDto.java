package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.MediumTyp;
import com.mediapicker.domain.mediathek.medium.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MangaRequestDto extends  MediumRequestDto {
  private Integer band;
  private Integer kapitel;
  private Integer currentKapitel;

  public MangaRequestDto(UUID mediumId,
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

  public MangaRequestDto() {}

  public Integer getBand() {
    return band;
  }

  public void setBand(Integer band) {
    this.band = band;
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
}
