package com.dir_music.bff.feign_controller.song_streaming_controller.web.controller;

import com.dir_music.bff.feign_controller.song_streaming_controller.web.model.response.MusicAvailableResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@FeignClient(name = "song-streaming-microservice")
public interface MusicController {

    @GetMapping("/{id}/stream")
    ResponseEntity<Resource> streamMusic(@PathVariable Long id,
                                         @RequestHeader HttpHeaders headers);


    @PostMapping("isAvailable")
    ResponseEntity<MusicAvailableResponseModel> checkMusicAvailability(@RequestBody List<Long> musicIds);

    @PostMapping("/create/{artist}/{album}/{id}")
    ResponseEntity<String> uploadMusicFile(@RequestParam("file") MultipartFile file,
                                           @PathVariable Long id,
                                           @PathVariable String artist,
                                           @PathVariable String album) throws IOException;

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteMusic(@PathVariable Long id) throws IOException;

}
