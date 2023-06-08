package com.dir_music.bff.feign_controller.playlist_controller.input;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerFeignCreateInput {
    private String playlistName;
    private Long userId;
    private String userName;
    private boolean isPublic;
}
