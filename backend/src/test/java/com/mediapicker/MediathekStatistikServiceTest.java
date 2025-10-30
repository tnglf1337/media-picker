package com.mediapicker;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.db.UserDao;
import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.user.User;
import com.mediapicker.service.MediathekService;
import com.mediapicker.service.MediathekStatistikService;
import com.mediapicker.web.OfflineAppRegister;
import com.mediapicker.web.response.MediathekStatistikResponseDto;
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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MediathekStatistikServiceTest {

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

  MediathekStatistikService mediathekStatistikService;

  static String USERNAME = "testuser";

  static User user;

  static Mediathek mediathek;

  @BeforeAll
  static void setUp() {
    user = UserMother.initTestUser(USERNAME);
    mediathek = MediathekMother.initFullMediathek(user);
  }

  @BeforeEach
  void setup() {
    mediathekStatistikService = new MediathekStatistikService(mediathekDao, userDao);
    ReflectionTestUtils.setField(mediathekStatistikService, "username", USERNAME);
  }

  @Test
  @DisplayName("Für eine MEdiathek wird ein MediakthekStatistikResponseDto korrekt berechnet")
  void test1() {
    mediathekDao.save(mediathek);
    MediathekStatistikResponseDto expected = new MediathekStatistikResponseDto(
      8,
      1,
      1,
      1,
      2,
      1,
      1,
      1,
      63,
      220,
      15,
      40
    );

    MediathekStatistikResponseDto actual = mediathekStatistikService.getMediathekStatistik();

    assertThat(actual).isEqualTo(expected);
  }
}
