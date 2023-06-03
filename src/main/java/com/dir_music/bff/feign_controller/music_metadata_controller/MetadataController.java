package com.dir_music.bff.feign_controller.music_metadata_controller;



import com.dir_music.bff.feign_controller.music_metadata_controller.model.MetadataServiceMetadataListOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("dir-metadata-service")
public interface MetadataController {

    @GetMapping(path = "/search/{query}")
    ResponseEntity<MetadataServiceMetadataListOutput> search(
            @RequestHeader("Authorization") String token,
            @PathVariable String query
    );

    @PostMapping(path = "/create", consumes = "multipart/form-data", produces = "application/json")
    ResponseEntity<String> createMusic(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("album") String album,
            @RequestParam("genre") String genre,
            @RequestParam("year") String year,
            @RequestParam("duration") String duration
    );
}
