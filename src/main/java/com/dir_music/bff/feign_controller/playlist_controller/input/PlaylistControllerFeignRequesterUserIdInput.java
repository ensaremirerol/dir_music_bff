package com.dir_music.bff.feign_controller.playlist_controller.input;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerFeignRequesterUserIdInput {
    private Long requesterUserId;
}
