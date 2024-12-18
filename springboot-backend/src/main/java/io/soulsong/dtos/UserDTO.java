package io.soulsong.dtos;

import java.util.List;

public record  UserDTO(
      Long id,
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      List<io.soulsong.entities.SongEssence> favoriteSongs) { }
