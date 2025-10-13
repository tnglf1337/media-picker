package com.mediapicker;

import com.mediapicker.domain.user.User;

public class UserMother {
  public static User initTestUser(String username) {
    return new User(null, username, "password", username + "@example.com");
  }
}
