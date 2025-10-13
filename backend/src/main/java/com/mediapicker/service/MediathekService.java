package com.mediapicker.service;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.db.UserDao;
import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.Medium;
import com.mediapicker.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
    return mediathekDao.findByUser(user);
  }
}
