package com.dir_music.bff.feign_controller.playlist_controller.output;


import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistControllerFeignListOutput {
    private List<PlaylistControllerFeignPlaylistOutput> playlists;
}
