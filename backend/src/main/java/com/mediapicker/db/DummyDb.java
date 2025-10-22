package com.mediapicker.db;

import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.*;
import com.mediapicker.domain.user.User;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class DummyDb {

  public static Mediathek initMediathek(User user) {
    Serie serie = new Serie(null, LocalDateTime.of(2025,2, 10, 12, 30), "Breaking Bad", Status.AM_SCHAUEN, 4, null, 1,12,3);
    Film film1 = new Film(null, LocalDateTime.of(2025,3, 10, 15, 30), "Inception", Status.BEENDET, 10, null);
    Film film2 = new Film(null, LocalDateTime.of(2025,3, 11, 10, 45), "Fight Club", Status.GEPLANT, 0, null);
    Film film3 = new Film(null, LocalDateTime.of(2025,3, 12, 20, 35), "Titanic", Status.ABGEBROCHEN, 2, null);
    Anime anime = new Anime(null, LocalDateTime.of(2024,6, 10, 12, 30), "NGE", Status.BEENDET, 10, null, 1,24,24);
    Manga manga = new Manga(null, LocalDateTime.of(2024,8, 17, 20, 30), "Gantz", Status.BEENDET, 8, null, 1,8,3);
    Videospiel videospiel1 = new Videospiel(null, LocalDateTime.of(2023,8, 17, 20, 30), "MGS3", Status.BEENDET, 10, null, Platform.PLAYSTATION_5);
    Videospiel videospiel2 = new Videospiel(null, LocalDateTime.of(2022,8, 17, 20, 30), "MGS4", Status.GEPLANT, 0, null, Platform.PLAYSTATION_4);
    Buch buch = new Buch(null, LocalDateTime.of(2025,2, 2, 2, 30), "Herr der Ringe 1", Status.AM_LESEN, 8, null, 888, 78);
    Podcast podcast = new Podcast(null, LocalDateTime.of(2022,7, 25, 9, 30), "Keine AHnung lol", Status.ABGEBROCHEN, 1, null, 10, 2);

    Mediathek mediathek = new Mediathek(null, user, new ArrayList<>());

    mediathek.mediumHinzufügen(serie);
    mediathek.mediumHinzufügen(film1);
    mediathek.mediumHinzufügen(film2);
    mediathek.mediumHinzufügen(film3);
    mediathek.mediumHinzufügen(anime);
    mediathek.mediumHinzufügen(manga);
    mediathek.mediumHinzufügen(videospiel1);
    mediathek.mediumHinzufügen(videospiel2);
    mediathek.mediumHinzufügen(buch);
    mediathek.mediumHinzufügen(podcast);

    return mediathek;
  }
}
