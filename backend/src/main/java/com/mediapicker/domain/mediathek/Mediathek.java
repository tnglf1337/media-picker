package com.mediapicker.domain.mediathek;

import com.mediapicker.domain.mediathek.medium.Medium;
import com.mediapicker.domain.user.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Mediathek {

  @Id
  @GeneratedValue
  @Column(name="mediathek_id")
  private UUID mediathekId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Medium> mediaListe;

  public Mediathek() {}

  public Mediathek(UUID mediathekId, User user, List<Medium> mediaListe) {
    this.mediathekId = mediathekId;
    this.user = user;
    this.mediaListe = mediaListe;
  }

  public void mediumHinzufügen(Medium medium) {
    this.mediaListe.add(medium);
  }

  public boolean mediumEntfernen(UUID mediumId) {
    Medium m = mediaListe.stream().filter(e -> e.getMediumId().toString().equals(mediumId.toString())).findFirst().orElse(null);

    System.out.println("to remove: " + m);
    if (m != null) {
      this.mediaListe.remove(m);
      return true;
    }
    return false;
  }

  public UUID getMediathekId() {
    return mediathekId;
  }

  public User getUser() {
    return user;
  }

  public List<Medium> getMediaListe() {
    return mediaListe;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Mediathek mediathek)) return false;
    return Objects.equals(getMediathekId(), mediathek.getMediathekId()) && Objects.equals(getUser(), mediathek.getUser()) && Objects.equals(getMediaListe(), mediathek.getMediaListe());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMediathekId(), getUser(), getMediaListe());
  }

  @Override
  public String toString() {
    return "Mediathek{" +
      "mediathekId=" + mediathekId +
      ", user=" + user +
      ", mediaListe=" + mediaListe +
      '}';
  }
}
