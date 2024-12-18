package io.soulsong.dtos;

public record SpotifyDTO(
      Long trackId,
      double energy,
      double danceability,
      double tempo,
      double acousticness
) {}
