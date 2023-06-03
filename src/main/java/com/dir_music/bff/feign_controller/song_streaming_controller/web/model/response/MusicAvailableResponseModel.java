package com.dir_music.bff.feign_controller.song_streaming_controller.web.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicAvailableResponseModel {
    List<MusicAvailableModel> musicAvailableModelList;
}
