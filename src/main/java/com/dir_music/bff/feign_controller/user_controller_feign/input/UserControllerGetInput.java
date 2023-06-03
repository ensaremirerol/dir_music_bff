package com.dir_music.bff.feign_controller.user_controller_feign.input;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserControllerGetInput {
    private Long userId;
    private boolean isDetailed;
}
