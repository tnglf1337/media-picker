package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class VideospielRequestDto extends MediumRequestDto {
  private Platform platform;

  public VideospielRequestDto(UUID mediumId,
                              LocalDateTime erstelltAm,
                              String titel,
                              Status status,
                              Integer rating,
                              List<String> notiz,
                              Platform platform) {
    super(mediumId, erstelltAm, titel, MediumTyp.VIDEOSPIEL, status, rating, notiz);
    this.platform = platform;
  }

  public VideospielRequestDto() {}

  public Videospiel toEntity() {
    return new Videospiel(
      getMediumId(),
      getErstelltAm(),
      getTitel(),
      getStatus(),
      getRating(),
      getNotiz(),
      platform
    );
  }

  public Platform getPlatform() {
    return platform;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  }

  @Override
  public String toString() {
    return "VideospielRequestDto{" +
      "platform=" + platform +
      '}';
  }
}
