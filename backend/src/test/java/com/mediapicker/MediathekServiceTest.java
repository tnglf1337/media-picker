package com.mediapicker;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.db.UserDao;
import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.Serie;
import com.mediapicker.domain.mediathek.medium.Videospiel;
import com.mediapicker.domain.user.User;
import com.mediapicker.service.MediathekService;
import com.mediapicker.web.OfflineAppRegister;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MediathekServiceTest {

  @TestConfiguration
  static class MockConfig {
    @Bean
    OfflineAppRegister offlineAppRegister() {
      return Mockito.mock(OfflineAppRegister.class);
    }
  }

  @Autowired
  UserDao userDao;

  @Autowired
  MediathekDao mediathekDao;

  MediathekService service;

  String USERNAME = "testuser";

  @BeforeEach
  void setup() {
    service = new MediathekService(mediathekDao, userDao);
    ReflectionTestUtils.setField(service, "username", USERNAME);
  }

  @Test
  @DisplayName("Alle Serien aus einer Mediathek werden gefunden")
  void test1() {
    User user = UserMother.initTestUser(USERNAME);
    Mediathek mediathek = MediathekMother.initFullMediathek(user);
    mediathekDao.save(mediathek);

    List<Serie> actual = service.findAllbyTyp(Serie.class);

    assertThat(actual).hasSize(1);
  }

  @Test
  @DisplayName("Alle Videospiele aus einer Mediathek werden gefunden")
  void test2() {
    User user = UserMother.initTestUser(USERNAME);
    Mediathek mediathek = MediathekMother.initFullMediathek(user);
    mediathekDao.save(mediathek);

    List<Videospiel> actual = service.findAllbyTyp(Videospiel.class);

    assertThat(actual).hasSize(2);
  }



}
