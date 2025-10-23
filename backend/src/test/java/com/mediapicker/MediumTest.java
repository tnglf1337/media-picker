package com.mediapicker;

import com.mediapicker.domain.mediathek.medium.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MediumTest {

  @Test
  @DisplayName("Eine Serie mit 12 Folgen, die aktuell bei Folge 10 ist, wird erfolgreich inkrementiert")
  void test1() {
    int folgen = 12;
    int currentFolge = 10;
    Serie s = new Serie(UUID.randomUUID(),
      LocalDateTime.now(),
      "Coole Serie",
      Status.AM_SCHAUEN,
      null,
      List.of("Notiz 1"),
      1,
      folgen,
      currentFolge);

    boolean success = s.inkrementCurrentFolge();

    assertThat(success).isTrue();
    assertThat(s.getCurrentFolge()).isEqualTo(currentFolge + 1);
    assertThat(s.getStatus()).isEqualTo(Status.AM_SCHAUEN);
  }

  @Test
  @DisplayName("Eine Serie mit 12 Folgen, die aktuell bei Folge 11 ist, wird erfolgreich inkrementiert und der Status auf BEENDET gesetzt.")
  void test2() {
    int folgen = 12;
    int currentFolge = 11;
    Serie s = new Serie(UUID.randomUUID(),
      LocalDateTime.now(),
      "Coole Serie",
      Status.AM_SCHAUEN,
      null,
      List.of("Notiz 1"),
      1,
      folgen,
      currentFolge);

    boolean success = s.inkrementCurrentFolge();

    assertThat(success).isTrue();
    assertThat(s.getCurrentFolge()).isEqualTo(currentFolge + 1);
    assertThat(s.getStatus()).isEqualTo(Status.BEENDET);
  }

  @Test
  @DisplayName("Eine Serie mit 12 Folgen, die aktuell bei Folge 11 ist, kann nicht zweimal inkrementiert werden.")
  void test3() {
    int folgen = 12;
    int currentFolge = 11;
    Serie s = new Serie(UUID.randomUUID(),
      LocalDateTime.now(),
      "Coole Serie",
      Status.AM_SCHAUEN,
      null,
      List.of("Notiz 1"),
      1,
      folgen,
      currentFolge);

    s.inkrementCurrentFolge();
    boolean success = s.inkrementCurrentFolge();

    assertThat(success).isFalse();
    assertThat(s.getCurrentFolge()).isEqualTo(12);
    assertThat(s.getStatus()).isEqualTo(Status.BEENDET);
  }

  @Test
  @DisplayName("Medien mit dem Status 'AM_...' werden als 'currently consuming' erkannt")
  void test4() {
    Medium m1 = new Serie(UUID.randomUUID(),
      LocalDateTime.now(),
      "Serie 1",
      Status.AM_SCHAUEN,
      null,
      List.of(),
      1,
      10,
      5);

    Medium m2 = new Buch(UUID.randomUUID(),
      LocalDateTime.now(),
      "Serie 2",
      Status.AM_LESEN,
      null,
      List.of(),
      1,
      10);

    Medium m3 = new Videospiel(UUID.randomUUID(),
      LocalDateTime.now(),
      "Serie 3",
      Status.AM_SPIELEN,
      null,
      List.of(),
      Platform.PLAYSTATION_5);

    Medium m4 = new Podcast(UUID.randomUUID(),
      LocalDateTime.now(),
      "Serie 4",
      Status.AM_HOEREN,
      null,
      List.of(),
      1,
      10);

    Medium m5 = new Podcast(UUID.randomUUID(),
      LocalDateTime.now(),
      "Serie 4",
      Status.BEENDET,
      null,
      List.of(),
      1,
      10);

    assertThat(m1.isCurrentlyConsuming()).isTrue();
    assertThat(m2.isCurrentlyConsuming()).isTrue();
    assertThat(m3.isCurrentlyConsuming()).isTrue();
    assertThat(m4.isCurrentlyConsuming()).isTrue();
    assertThat(m5.isCurrentlyConsuming()).isFalse();
  }
}
