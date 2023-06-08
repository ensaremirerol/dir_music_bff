package com.dir_music.bff.feign_controller.playlist_controller.output;


import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerFeignPlaylistOutput {
    private String playlistId;
    private String playlistName;
    private Long userId;
    private String userName;
    private boolean isPublic;
    private List<Long> songIds;

}
