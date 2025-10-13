package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.MediumTyp;
import com.mediapicker.domain.mediathek.medium.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BuchRequestDto extends MediumRequestDto {
  private Integer seiten;
  private Integer currentSeite;

  public BuchRequestDto(UUID mediumId,
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

  public BuchRequestDto() {}

  public Integer getCurrentSeite() {
    return currentSeite;
  }

  public void setCurrentSeite(Integer currentSeite) {
    this.currentSeite = currentSeite;
  }

  public Integer getSeiten() {
    return seiten;
  }

  public void setSeiten(Integer seiten) {
    this.seiten = seiten;
  }
}
