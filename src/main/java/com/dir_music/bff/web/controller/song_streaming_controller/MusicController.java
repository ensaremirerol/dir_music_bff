package com.dir_music.bff.web.controller.song_streaming_controller;


import com.dir_music.bff.feign_controller.song_streaming_controller.web.controller.MusicControllerFeign;
import com.dir_music.bff.feign_controller.song_streaming_controller.web.model.response.MusicAvailableResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("MusicController")
@RequestMapping("/songs")
public class MusicController {
    final MusicControllerFeign musicControllerFeign;


    public MusicController(MusicControllerFeign musicControllerFeign) {
        this.musicControllerFeign = musicControllerFeign;
    }

    @GetMapping("/{id}/stream")
    ResponseEntity<Resource> streamMusic(@PathVariable Long id,
                                         @RequestHeader HttpHeaders headers) {
        return musicControllerFeign.streamMusic(id, headers);

    }

    @PostMapping("isAvailable")
    ResponseEntity<MusicAvailableResponseModel> checkMusicAvailability(@RequestBody List<Long> musicIds) {
        return musicControllerFeign.checkMusicAvailability(musicIds);
    }

    @PostMapping("/create/{artist}/{album}/{id}")
    ResponseEntity<String> uploadMusicFile(@RequestParam("file") MultipartFile file,
                                           @PathVariable Long id,
                                           @PathVariable String artist,
                                           @PathVariable String album) throws IOException {
        return musicControllerFeign.uploadMusicFile(file, id, artist, album);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteMusic(@PathVariable Long id) throws IOException {
        return musicControllerFeign.deleteMusic(id);
    }



}
