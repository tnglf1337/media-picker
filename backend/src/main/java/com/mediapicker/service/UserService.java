package com.mediapicker.service;

import com.mediapicker.db.UserDao;
import com.mediapicker.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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
  }
}
