package com.mediapicker.service;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.db.UserDao;
import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.*;
import com.mediapicker.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MediathekService {

  private static final Logger log = LoggerFactory.getLogger(MediathekService.class);

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
  public boolean mediumErstellen(Medium medium) {
    User user = userDao.findByUsername(username);
    Mediathek mediathek = mediathekDao.findByUser(user);
    mediathek.mediumHinzufügen(medium);
    try {
      mediathekDao.save(mediathek);
      log.info("Neues Medium hinzugefügt: " + medium);
      return true;
    } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
      log.error("Fehler beim Hinzufügen des Mediums: " + medium);
      System.out.println(e.getMessage());
      return false;
    }

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

  public boolean deleteByMediumId(UUID mediumId) {
    Mediathek mediathek = findMediathekByUser();
    List<Medium> l = mediathek.getMediaListe();

    l.removeIf(m -> m.getMediumId().equals(mediumId));

    try {
      mediathekDao.save(mediathek);
      log.info("Medium mit ID " + mediumId + " wurde gelöscht.");
      return true;
    } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
      log.error("Fehler beim Löschen des Mediums mit ID " + mediumId);
      System.out.println(e.getMessage());
      return false;
    }
  }

  public boolean inkrementMedium(UUID mediumId) {
    Mediathek mediathek = findMediathekByUser();
    List<Medium> l = mediathek.getMediaListe();

    Optional<? extends Medium> medium = l.stream().filter(m -> m.getMediumId().equals(mediumId)).findFirst();

    if (medium.isPresent() && medium.get() instanceof Serie) {
      Serie serie = (Serie) medium.get();
      serie.inkrementCurrentFolge();
    } else if (medium.isPresent() && medium.get() instanceof Anime) {
      Anime anime = (Anime) medium.get();
      anime.inkrementCurrentFolge();
    } else if (medium.isPresent() && medium.get() instanceof Manga) {
      Manga manga = (Manga) medium.get();
      manga.inkrementCurrentKapitel();
    } else if (medium.isPresent() && medium.get() instanceof Buch) {
      Buch buch = (Buch) medium.get();
      buch.inkrementCurrentSeite();
    } else if (medium.isPresent() && medium.get() instanceof Podcast) {
      Podcast podcast = (Podcast) medium.get();
      podcast.inkrementCurrentKapitel();
    } else {
      log.error("Medium mit ID " + mediumId + " nicht gefunden.");
      return false;
    }

    mediathekDao.save(mediathek);
    log.info("Medium " + medium + " wurde inkrementiert.");
    return true;
  }

  public boolean dekrementMedium(UUID mediumId) {
    Mediathek mediathek = findMediathekByUser();
    List<Medium> l = mediathek.getMediaListe();

    Optional<? extends Medium> medium = l.stream().filter(m -> m.getMediumId().equals(mediumId)).findFirst();

    if (medium.isPresent() && medium.get() instanceof Serie) {
      Serie serie = (Serie) medium.get();
      serie.dekrementCurrentFolge();
    } else if (medium.isPresent() && medium.get() instanceof Anime) {
      Anime anime = (Anime) medium.get();
      anime.dekrementCurrentFolge();
    } else if (medium.isPresent() && medium.get() instanceof Manga) {
      Manga manga = (Manga) medium.get();
      manga.dekrementCurrentKapitel();
    } else if (medium.isPresent() && medium.get() instanceof Buch) {
      Buch buch = (Buch) medium.get();
      buch.dekrementCurrentSeite();
    } else if (medium.isPresent() && medium.get() instanceof Podcast) {
      Podcast podcast = (Podcast) medium.get();
      podcast.dekrementCurrentKapitel();
    } else {
      log.error("Medium mit ID " + mediumId + " nicht gefunden.");
      return false;
    }

    mediathekDao.save(mediathek);
    log.info("Medium " + medium + " wurde dekrementiert.");
    return true;
  }
}
