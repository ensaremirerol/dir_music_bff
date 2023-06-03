package com.dir_music.bff.feign_controller.song_streaming_controller.web.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicAvailableModel {
    private Long id;
    private Boolean isAvailable;
}
