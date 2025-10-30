package com.mediapicker.service;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.db.UserDao;
import com.mediapicker.domain.mediathek.medium.*;
import com.mediapicker.domain.user.User;
import com.mediapicker.web.response.MediathekStatistikResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.ToIntFunction;

@Service
public class MediathekStatistikService {

  @Value("${app.offline.default.username}")
  private String username;

  private final MediathekDao mediathekDao;

  private final UserDao userDao;

  public MediathekStatistikService(MediathekDao mediathekDao, UserDao userDao) {
    this.mediathekDao = mediathekDao;
    this.userDao = userDao;
  }

  public MediathekStatistikResponseDto getMediathekStatistik() {
    User user = userDao.findByUsername(username);
    List<? extends Medium> medien = mediathekDao.findByUser(user).getMediaListe();

    Integer anzahlMedien                = medien.size();
    Integer anzahlFilme                 = sumForType(medien, Film.class);
    Integer anzahlSerien                = sumForType(medien, Serie.class);
    Integer anzahlAnime                 = sumForType(medien, Anime.class);
    Integer anzahlVideospiele           = sumForType(medien, Videospiel.class);
    Integer anzahlBuecher               = sumForType(medien, Buch.class);
    Integer anzahlManga                 = sumForType(medien, Manga.class);
    Integer anzahlPodcasts              = sumForType(medien, Podcast.class);

    Integer anzahlSerienFolgenGesehen   = sumForType(medien, Serie.class, Serie::getCurrentFolge);
    Integer anzahlAnimeFolgenGesehen    = sumForType(medien, Anime.class, Anime::getCurrentFolge);
    Integer anzahlSeitenGelesen         = sumForType(medien, Buch.class, Buch::getCurrentSeite);
    Integer anzahlMangaKapitelGelesen   = sumForType(medien, Manga.class, Manga::getCurrentKapitel);
    Integer anzahlPodcastKapitelGehoert = sumForType(medien, Podcast.class, Podcast::getCurrentKapitel);


    return new MediathekStatistikResponseDto(
      anzahlMedien,
      anzahlFilme,
      anzahlSerien,
      anzahlAnime,
      anzahlVideospiele,
      anzahlBuecher,
      anzahlManga,
      anzahlPodcasts,
      anzahlAnimeFolgenGesehen + anzahlSerienFolgenGesehen,
      anzahlSeitenGelesen,
      anzahlMangaKapitelGelesen,
      anzahlPodcastKapitelGehoert);
  }

  private <T> int sumForType(List<? extends Medium> medien,
                             Class<T> type,
                             ToIntFunction<T> mapper) {
    return medien.stream()
      .filter(type::isInstance)
      .map(type::cast)
      .mapToInt(mapper)
      .sum();
  }

  private <T> Integer sumForType(List<? extends Medium> medien,
                             Class<T> type) {
    return (int) medien.stream()
      .filter(type::isInstance)
      .count();
  }
}
