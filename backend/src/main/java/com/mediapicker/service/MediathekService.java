package com.mediapicker.service;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.db.UserDao;
import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.*;
import com.mediapicker.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediathekService {

  @Value("${app.offline.default.username}")
  private String username;

  private final MediathekDao mediathekDao;
  private final UserDao userDao;

  public MediathekService(MediathekDao mediathekDao, UserDao userDao) {
    this.mediathekDao = mediathekDao;
    this.userDao = userDao;
  }

  public void save(Mediathek mediathek) {
    mediathekDao.save(mediathek);
  }

  @Transactional
  public void mediumErstellen(Medium medium) {
    User user = userDao.findByUsername(username);
    Mediathek mediathek = mediathekDao.findByUser(user);
    mediathek.mediumHinzufügen(medium);
    mediathekDao.save(mediathek);
  }

  public Mediathek findMediathekByUser() {
    User user = userDao.findByUsername(username);
    System.out.println(user);
    return mediathekDao.findByUser(user);
  }

  public <T extends Medium> List<T> findAllbyTyp(Class<T> c) {
    Mediathek m = findMediathekByUser();
    return m.getMediaListe().stream()
      .filter(c::isInstance)
      .map(c::cast)
      .collect(Collectors.toList());
  }

  public List<? extends Medium> findAllByTyp(MediumTyp mediumTyp) {
    return switch (mediumTyp) {
      case SERIE -> findAllbyTyp(Serie.class);
      case FILM -> findAllbyTyp(Film.class);
      case ANIME -> findAllbyTyp(Anime.class);
      case MANGA -> findAllbyTyp(Manga.class);
      case BUCH -> findAllbyTyp(Buch.class);
      case VIDEOSPIEL -> findAllbyTyp(Videospiel.class);
      case PODCAST -> findAllbyTyp(Podcast.class);
      default -> List.of();
    };
  }
}
