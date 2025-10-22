package com.mediapicker.service;

import com.mediapicker.db.UserDao;
import com.mediapicker.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  private final UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public boolean istRegistriertByUsername(String username) {
    User u = userDao.findByUsername(username);
    return u != null;
  }

  public void registriereUser(User user) {
    this.userDao.save(user);
    log.info("User " + user + " erfolgreich registriert.");
  }
}
