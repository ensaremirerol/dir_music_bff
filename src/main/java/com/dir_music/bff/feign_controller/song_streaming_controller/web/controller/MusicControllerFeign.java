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

@FeignClient(name = "dir-stream-song-service")
public interface MusicControllerFeign {

    @GetMapping("/songs/{id}/stream")
    ResponseEntity<Resource> streamMusic(@PathVariable Long id,
                                         @RequestHeader HttpHeaders headers);


    @PostMapping("/songs/isAvailable")
    ResponseEntity<MusicAvailableResponseModel> checkMusicAvailability(@RequestBody List<Long> musicIds);

    @PostMapping(value ="/songs/create/{artist}/{album}/{id}", consumes = "multipart/form-data", produces = "application/json")
    ResponseEntity<String> uploadMusicFile(@RequestPart("file") MultipartFile file,
                                           @PathVariable Long id,
                                           @PathVariable String artist,
                                           @PathVariable String album) throws IOException;

    @DeleteMapping("/songs/delete/{id}")
    ResponseEntity<String> deleteMusic(@PathVariable Long id) throws IOException;

}
