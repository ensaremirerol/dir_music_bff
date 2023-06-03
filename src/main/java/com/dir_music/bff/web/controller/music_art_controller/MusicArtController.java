package com.dir_music.bff.web.controller.music_art_controller;


import com.dir_music.bff.feign_controller.song_art_controller.controller.MusicArtControllerFeign;
import com.dir_music.bff.feign_controller.user_controller_feign.UserControllerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("MusicArtController")
@RequestMapping("/musicArt")
public class MusicArtController {
    final MusicArtControllerFeign musicArtController;

    @Autowired
    public MusicArtController(MusicArtControllerFeign musicArtController) {
        this.musicArtController = musicArtController;
    }

    @GetMapping("/gimme/{id}")
    ResponseEntity<Resource> streamMusicArt(@PathVariable Long id) {
        return musicArtController.streamMusicArt(id);
    }

    @PostMapping("/upload/{id}")
    ResponseEntity<String> uploadMusicArt(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return musicArtController.uploadMusicArt(id, file);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteMusicArt(@PathVariable Long id) {
        return musicArtController.deleteMusicArt(id);
    }

}
