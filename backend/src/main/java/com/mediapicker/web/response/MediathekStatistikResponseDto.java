package com.mediapicker.web.response;

public record MediathekStatistikResponseDto(
  Integer anzahlMedien,
  Integer anzahlFilme,
  Integer anzahlSerien,
  Integer anzahlAnime,
  Integer anzahlVideospiele,
  Integer anzahlBuecher,
  Integer anzahlManga,
  Integer anzahlPodcasts,
  Integer anzahlFolgenGesehen,
  Integer anzahlSeitenGelesen,
  Integer anzahlMangaKapitelGelesen,
  Integer anzahlPodcastKapitelGehoert
) {
}
