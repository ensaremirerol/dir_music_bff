package com.dir_music.bff.feign_controller.auth_controller.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenValidationControllerOutput {
    private long subject;
    private String username;
    private String role;
    private String audience;
    private String issuer;
    private long expiration;
    private long issuedAt;
}
