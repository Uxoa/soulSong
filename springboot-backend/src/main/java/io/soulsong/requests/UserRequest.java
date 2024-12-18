package io.soulsong.requests;

import java.time.LocalDate;

public record UserRequest(
      String firstName,
      String lastName,
      LocalDate birthday,
      String email,
      String phoneNumber) { }
