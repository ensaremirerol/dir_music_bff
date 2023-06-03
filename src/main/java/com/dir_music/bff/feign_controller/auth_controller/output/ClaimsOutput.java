package com.dir_music.bff.feign_controller.auth_controller.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimsOutput {
    private String username;
    private String userId;
    private String role;
}
