package com.mediapicker;

import com.mediapicker.domain.mediathek.medium.Serie;
import com.mediapicker.domain.mediathek.medium.Status;
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
}
