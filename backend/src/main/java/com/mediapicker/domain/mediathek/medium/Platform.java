package com.mediapicker.domain.mediathek.medium;

public enum Platform {
  PLAYSTATION_4("PlayStation 4"),
  PLAYSTATION_5("PlayStation 5"),
  PLAYSTATION_6("PlayStation 6"),
  PC("PC"),
  NINTENDO_SWITCH("Nintendo Switch"),
  NINTENDO_SWITCH_2("Nintendo Switch 2");

  private final String bezeichnung;

  Platform(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  public String getPlatformBezeichnung() {
    return bezeichnung;
  }
}
