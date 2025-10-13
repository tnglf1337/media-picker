package com.mediapicker.web;

import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.user.User;
import com.mediapicker.service.MediathekService;
import com.mediapicker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OfflineAppRegister {

  private final String DEFAULT_USER = "off_user";

  private final UserService userService;
  private final MediathekService mediathekService;
  private static final Logger log = LoggerFactory.getLogger(OfflineAppRegister.class);

  public OfflineAppRegister(UserService userService,
                            MediathekService mediathekService) {
    this.userService = userService;
    this.mediathekService = mediathekService;
  }

  public void setupApplication() {
    if(!userService.istRegistriertByUsername(DEFAULT_USER)) {
      User user = new User(
        null,
        DEFAULT_USER,
        "1234",
        "default@default.de"
      );
      Mediathek mediathek = new Mediathek(null, user, null);
      mediathekService.save(mediathek);
      log.info("Mediathek für DEFAULT_USER '%s' erstellt.".formatted(DEFAULT_USER));
    }




  }
}
