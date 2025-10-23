package com.mediapicker;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.db.UserDao;
import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.Medium;
import com.mediapicker.domain.mediathek.medium.MediumTyp;
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
import java.util.Map;
import java.util.UUID;
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
    service = new MediathekService(mediathekDao, userDao);
    ReflectionTestUtils.setField(service, "username", USERNAME);
  }

  @Test
  @DisplayName("Alle Serien aus einer Mediathek werden gefunden")
  void test1() {
    mediathekDao.save(mediathek);

    List<Serie> actual = service.findAllbyTyp(Serie.class);

    assertThat(actual).hasSize(1);
  }

  @Test
  @DisplayName("Alle Videospiele aus einer Mediathek werden gefunden")
  void test2() {
    mediathekDao.save(mediathek);

    List<Videospiel> actual = service.findAllbyTyp(Videospiel.class);

    assertThat(actual).hasSize(2);
  }

  @Test
  @DisplayName("Ein Film wird aus der Mediathek gelöscht")
  void test3() {
    int sizeBefore = mediathek.getMediaListe().size();
    mediathekDao.save(mediathek);
    Mediathek mediathek2 = mediathekDao.findByUser(user);
    UUID mediumIdToDelete = mediathek2.getMediaListe().get(2).getMediumId();

    boolean success = service.deleteByMediumId(mediumIdToDelete);

    Mediathek mediathekAfter = mediathekDao.findByUser(user);
    assertThat(mediathekAfter.getMediaListe()).hasSize(sizeBefore - 1);
    assertThat(success).isTrue();
  }

  @Test
  @DisplayName("Die currentFolge einer Serie wird erfolgreich inkrementiert")
  void test4() {
    mediathekDao.save(mediathek);
    Mediathek mediathekBefore = mediathekDao.findByUser(user);
    UUID serieId = mediathekBefore.getMediaListe().get(5).getMediumId();

    service.inkrementMedium(serieId);

    Mediathek mediathekAfter = mediathekDao.findByUser(user);
    Serie inkrementiertesMedium = (Serie) mediathekAfter.getMediaListe().get(5);
    assertThat(inkrementiertesMedium.getCurrentFolge()).isEqualTo(14);
  }

  @Test
  @DisplayName("Die currentFolge einer Serie wird erfolgreich dekrementiert")
  void test5() {
    mediathekDao.save(mediathek);
    Mediathek mediathekBefore = mediathekDao.findByUser(user);
    UUID serieId = mediathekBefore.getMediaListe().get(5).getMediumId();

    service.dekrementMedium(serieId);

    Mediathek mediathekAfter = mediathekDao.findByUser(user);
    Serie inkrementiertesMedium = (Serie) mediathekAfter.getMediaListe().get(5);
    assertThat(inkrementiertesMedium.getCurrentFolge()).isEqualTo(12);
  }

  @Test
  @DisplayName("Alle Medien mit einem Status 'AM_...' werden aus der Mediatek eines Users gefunden")
  void test6() {
    mediathekDao.save(mediathek);

    Map<MediumTyp, List<? extends Medium>> actual = service.findAllCurrentMedien();

    assertThat(actual).hasSize(4);
    assertThat(actual.get(MediumTyp.ANIME)).hasSize(1);
    assertThat(actual.get(MediumTyp.MANGA)).hasSize(1);
    assertThat(actual.get(MediumTyp.PODCAST)).hasSize(1);
    assertThat(actual.get(MediumTyp.VIDEOSPIEL)).hasSize(2);
  }
}
