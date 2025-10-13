package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FilmRequestDto extends MediumRequestDto{

  public FilmRequestDto(UUID mediumId,
                        LocalDateTime erstelltAm,
                        String titel,
                        Status status,
                        Integer rating,
                        List<String> notiz) {
    super(mediumId, erstelltAm, titel, MediumTyp.FILM, status, rating, notiz);
  }

  public FilmRequestDto() {}

  public Film toEntity() {
    return new Film(
      getMediumId(),
      getErstelltAm(),
      getTitel(),
      getStatus(),
      getRating(),
      getNotiz()
    );
  }

}
