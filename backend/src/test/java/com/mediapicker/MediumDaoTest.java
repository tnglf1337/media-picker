package com.mediapicker;

import com.mediapicker.db.MediathekDao;
import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.Film;
import com.mediapicker.domain.mediathek.medium.Serie;
import com.mediapicker.domain.mediathek.medium.Status;
import com.mediapicker.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mediapicker.MediathekMother.initFullMediathek;
import static com.mediapicker.MediathekMother.initMediathekWithMedium;
import static com.mediapicker.UserMother.initTestUser;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MediumDaoTest {

  @Autowired
  private MediathekDao dao;

  @Test
  @DisplayName("Eine befüllte Mediathek eines Users wird erfolgreich in der Datenbank abgespeichert.")
  void test1() {
    User user = initTestUser("peter");
    Mediathek mediathek = initFullMediathek(user);

    dao.save(mediathek);

    Mediathek fromDb = dao.findById(mediathek.getMediathekId()).orElseThrow();
    assertThat(fromDb.getMediathekId()).isNotNull();
    assertThat(fromDb.getMediaListe()).hasSize(7);
    assertThat(fromDb.getUser().getUserId()).isNotNull();
  }

  @Test
  @DisplayName("Einer vorhandenen Mediathek eines Users wird erfolgreich ein weiteres Medium hinzugefügt und in der Datenbank abgespeichert.")
  void test2() {
    User user = initTestUser("peter");
    Mediathek mediathek = initFullMediathek(user);
    dao.save(mediathek);
    Mediathek fromDbBefore = dao.findById(mediathek.getMediathekId()).orElseThrow();
    int sizeBeforeAdd = fromDbBefore.getMediaListe().size();
    Film neuerFilm = new Film(null, LocalDateTime.now(), "Avatar", Status.GEPLANT, 0, null);
    fromDbBefore.mediumHinzufügen(neuerFilm);

    dao.save(fromDbBefore);

    Mediathek fromDbAfter = dao.findById(fromDbBefore.getMediathekId()).orElseThrow();

    assertThat(sizeBeforeAdd).isEqualTo(7);
    assertThat(fromDbAfter.getMediaListe()).hasSize(sizeBeforeAdd + 1);
  }

  @Test
  @DisplayName("Einer vorhandenen Mediathek eines Users kann ein vorhandenes Medium entfernt werden.")
  void test3() {
    User user = initTestUser("peter");
    Mediathek mediathek = initFullMediathek(user);
    dao.save(mediathek);

    Mediathek fromDbBefore = dao.findById(mediathek.getMediathekId()).orElseThrow();
    UUID mediumIdToRemove = fromDbBefore.getMediaListe().get(2).getMediumId();
    int sizeBeforeRemove = fromDbBefore.getMediaListe().size();
    fromDbBefore.mediumEntfernen(mediumIdToRemove);
    dao.save(fromDbBefore);
    Mediathek fromDbAfter = dao.findById(fromDbBefore.getMediathekId()).orElseThrow();

    assertThat(sizeBeforeRemove).isEqualTo(7);
    assertThat(fromDbAfter.getMediaListe()).hasSize(sizeBeforeRemove - 1);
  }

  @Test
  @DisplayName("Die aktuelle Folge einer Serie in einer vorhandenen Mediathek wird erfolgreich hochgezählt.")
  void test4() {
    User user = initTestUser("peter");
    int initCurrentFolge = 13;
    Serie serie = new Serie(null, LocalDateTime.now(), "Breaking Bad", Status.BEENDET, 5, new ArrayList<>(List.of("Notiz 1", "Notiz 2")), 5, 62, initCurrentFolge);
    Mediathek mediathek = initMediathekWithMedium(user, serie);
    dao.save(mediathek);

    Mediathek fromDbBefore = dao.findById(mediathek.getMediathekId()).orElseThrow();
    Serie thisSerie = (Serie) fromDbBefore.getMediaListe().get(0);
    thisSerie.inkrementCurrentFolge();
    dao.save(fromDbBefore);
    Mediathek fromDbAfter = dao.findById(mediathek.getMediathekId()).orElseThrow();

    Serie thisUpdatedSerie = (Serie) fromDbAfter.getMediaListe().get(0);
    assertThat(thisUpdatedSerie.getCurrentFolge()).isEqualTo(initCurrentFolge + 1);
  }

  @Test
  @DisplayName("Eine neu hinzugefügte Notiz zu einem Medium wird erfolgreich abgespeichert.")
  void test5() {
    String neueNotiz = "noch bewerten";
    User user = initTestUser("peter");
    Serie serie = new Serie(null, LocalDateTime.now(), "Breaking Bad", Status.BEENDET, 5, new ArrayList<>(List.of("Notiz 1")), 5, 62, 62);
    int notizenAnzahlBefore = serie.getNotiz().size();
    Mediathek mediathek = initMediathekWithMedium(user, serie);
    dao.save(mediathek);

    Mediathek fromDbBefore = dao.findById(mediathek.getMediathekId()).orElseThrow();
    Serie thisSerie = (Serie) fromDbBefore.getMediaListe().get(0);
    thisSerie.notizHinzufuegen(neueNotiz);
    dao.save(fromDbBefore);
    Mediathek fromDbAfter = dao.findById(mediathek.getMediathekId()).orElseThrow();

    Serie thisSerieAfter = (Serie) fromDbAfter.getMediaListe().get(0);
    assertThat(thisSerieAfter.getNotiz().size()).isEqualTo(notizenAnzahlBefore + 1);
    assertThat(thisSerieAfter.getNotiz()).contains(neueNotiz);

  }
}
