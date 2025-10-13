package com.mediapicker;

import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.mediathek.medium.*;
import com.mediapicker.domain.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MediathekMother {

  public static Mediathek initFullMediathek(User user) {
    List<String> notizen = new ArrayList<>(List.of("Notiz 1", "Notiz 2"));

    Anime anime = new Anime(null, LocalDateTime.now(), "Naruto", Status.AM_SCHAUEN, 5, notizen, 1, 220, 50);
    Buch buch = new Buch(null, LocalDateTime.now(), "Der Herr der Ringe", Status.GEPLANT, 5, notizen, 500, 220);
    Film film = new Film(null, LocalDateTime.now(), "Inception", Status.BEENDET, 5, notizen);
    Manga manga = new Manga(null, LocalDateTime.now(), "One Piece", Status.AM_LESEN, 5, notizen, 5, 220, 15);
    Podcast podcast = new Podcast(null, LocalDateTime.now(), "Tech Talk", Status.AM_HOEREN, 5, notizen, 50, 40);
    Serie serie = new Serie(null, LocalDateTime.now(), "Breaking Bad", Status.BEENDET, 5, notizen, 5, 62, 13);
    Videospiel videospiel = new Videospiel(null, LocalDateTime.now(), "The Last of Us", Status.AM_SPIELEN, 5, notizen, Platform.PLAYSTATION_5);

    List<Medium> mediaList = new ArrayList<>();
    mediaList.add(anime);
    mediaList.add(buch);
    mediaList.add(film);
    mediaList.add(manga);
    mediaList.add(podcast);
    mediaList.add(serie);
    mediaList.add(videospiel);

    return new Mediathek(null, user, mediaList);
  }

  public static Mediathek initMediathekWithMedium(User u, Medium m) {
    List<Medium> mediaList = new ArrayList<>();
    mediaList.add(m);
    return new Mediathek(null, u, mediaList);
  }
}
