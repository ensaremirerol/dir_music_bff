package com.dir_music.bff.web.controller.metadata_controller;


import com.dir_music.bff.feign_controller.music_metadata_controller.MetadataControllerFeign;
import com.dir_music.bff.feign_controller.music_metadata_controller.model.MetadataServiceMetadataListOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("MetadataController")
@RequestMapping("/")
public class MetadataController {
    final MetadataControllerFeign metadataControllerFeign;

    public MetadataController(MetadataControllerFeign metadataControllerFeign) {
        this.metadataControllerFeign = metadataControllerFeign;
    }

    @GetMapping(path = "/search/{query}")
    ResponseEntity<MetadataServiceMetadataListOutput> search(
            @RequestHeader("Authorization") String token,
            @PathVariable String query
    ) {
        return metadataControllerFeign.search(token, query);
    }

    @PostMapping("/create")
    ResponseEntity<String> createMusic(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("album") String album,
            @RequestParam("genre") String genre,
            @RequestParam("year") String year,
            @RequestParam("duration") String duration
    ) {
        return metadataControllerFeign.createMusic(file, title, artist, album, genre, year, duration);
    }
}
