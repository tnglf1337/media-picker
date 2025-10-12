package com.mediapicker.domain.user;

import com.mediapicker.domain.mediathek.Mediathek;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  @Column(name="user_id")
  private UUID userId;
  private String username;
  private String passwort;
  private String email;

  @OneToOne(mappedBy="user")
  private Mediathek mediathek;

  public User() {}

  public User(UUID userId, String username, String passwort, String email) {
    this.userId = userId;
    this.username = username;
    this.passwort = passwort;
    this.email = email;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswort() {
    return passwort;
  }

  public void setPasswort(String passwort) {
    this.passwort = passwort;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof User user)) return false;
    return Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPasswort(), user.getPasswort()) && Objects.equals(getEmail(), user.getEmail());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getUsername(), getPasswort(), getEmail());
  }

  @Override
  public String toString() {
    return "User{" +
      "userId=" + userId +
      ", username='" + username + '\'' +
      ", passwort='" + passwort + '\'' +
      ", email='" + email + '\'' +
      '}';
  }
}
