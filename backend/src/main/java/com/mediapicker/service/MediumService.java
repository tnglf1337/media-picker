package com.mediapicker.service;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.domain.mediathek.Mediathek;
import org.springframework.stereotype.Service;

@Service
public class MediumService {

  private final MediathekDao mediathekDao;

  public MediumService(MediathekDao mediathekDao) {
    this.mediathekDao = mediathekDao;
  }

  public void save(Mediathek mediathek) {
    mediathekDao.save(mediathek);
  }
}
