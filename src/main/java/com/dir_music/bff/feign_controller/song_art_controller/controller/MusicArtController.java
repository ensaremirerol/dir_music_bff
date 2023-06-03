package com.dir_music.bff.feign_controller.song_art_controller.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "cover-art-microservice")
public interface MusicArtController {


    @GetMapping("/gimme/{id}")
    ResponseEntity<Resource> streamMusicArt(@PathVariable Long id);

    @PostMapping("/upload/{id}")
    ResponseEntity<String> uploadMusicArt(@PathVariable Long id, @RequestParam("file")MultipartFile file) throws IOException;


    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteMusicArt(@PathVariable Long id);


}
