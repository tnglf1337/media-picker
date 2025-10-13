package com.mediapicker.domain.mediathek.medium;

public enum Status {
  BEENDET("beendet"),
  AM_SCHAUEN("am Schauen"),
  AM_SPIELEN("am Spielen"),
  AM_LESEN("am Lesen"),
  AM_HOEREN("am Hören"),
  GEPLANT("geplant"),
  PAUSIERT("pausiert"),
  ABGEBROCHEN("abgebrochen");

  private final String bezeichnung;

  Status(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  public String getStatusBezeichnung() {
    return bezeichnung;
  }
}
