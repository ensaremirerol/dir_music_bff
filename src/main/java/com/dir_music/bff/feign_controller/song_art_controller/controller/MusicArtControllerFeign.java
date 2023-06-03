package com.dir_music.bff.feign_controller.song_art_controller.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "cover-art-microservice")
public interface MusicArtControllerFeign {


    @GetMapping("musicArt/gimme/{id}")
    ResponseEntity<Resource> streamMusicArt(@PathVariable Long id);

    @PostMapping(value="musicArt/upload/{id}", consumes = "multipart/form-data", produces = "application/json")
    ResponseEntity<String> uploadMusicArt(@PathVariable("id") Long id, @RequestPart("file")MultipartFile file);


    @DeleteMapping("musicArt/delete/{id}")
    ResponseEntity<String> deleteMusicArt(@PathVariable Long id);


}
