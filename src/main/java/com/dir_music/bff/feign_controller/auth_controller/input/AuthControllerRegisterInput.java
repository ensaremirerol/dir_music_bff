package com.dir_music.bff.feign_controller.auth_controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthControllerRegisterInput {
    private String username;
    private String password;
}
