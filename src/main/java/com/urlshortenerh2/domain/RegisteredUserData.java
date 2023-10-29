package com.urlshortenerh2.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisteredUserData() {
    @NotBlank
   static String username;

    @NotBlank
    @Email
    static String email;

    @NotBlank
   static String password;

}
