package com.mediapicker.web;

import com.mediapicker.domain.user.User;
import com.mediapicker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OfflineAppRegister {

  private final String DEFAULT_USER = "off_user";

  private final UserService userService;
  private static final Logger log = LoggerFactory.getLogger(OfflineAppRegister.class);

  public OfflineAppRegister(UserService userService) {
    this.userService = userService;
  }

  public void registriereDefaultUser() {
    if(!userService.istRegistriertByUsername(DEFAULT_USER)) {
      userService.registriereUser(new User(
        null,
        DEFAULT_USER,
        "1234",
        "default@default.de"
      ));
      log.info("DEFAULT_USER '%s' registriert".formatted(DEFAULT_USER));
    }
  }

}
