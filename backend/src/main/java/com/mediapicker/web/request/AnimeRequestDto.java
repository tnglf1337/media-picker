package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.MediumTyp;
import com.mediapicker.domain.mediathek.medium.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AnimeRequestDto extends MediumRequestDto {
  private Integer season;
  private Integer folgen;
  private Integer currentFolge;

  public AnimeRequestDto(UUID mediumId,
                         LocalDateTime erstelltAm,
                         String titel,
                         Status status,
                         Integer rating,
                         List<String> notiz,
                         Integer season,
                         Integer folgen,
                         Integer currentFolge) {
    super(mediumId, erstelltAm, titel, MediumTyp.ANIME, status, rating, notiz);
    this.season = season;
    this.folgen = folgen;
    this.currentFolge = currentFolge;
  }

  public AnimeRequestDto() {}
}
