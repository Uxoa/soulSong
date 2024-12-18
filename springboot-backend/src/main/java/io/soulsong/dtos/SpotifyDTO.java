package io.soulsong.dtos;

public record SpotifyDTO(
      String trackId,
      double energy,
      double danceability,
      double tempo,
      double acousticness
) {}
