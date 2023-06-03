package com.dir_music.bff.feign_controller.music_metadata_controller.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MetadataServiceMetadataListOutput {
    private List<MetadataServiceMetadataOutput> results;


    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MetadataServiceMetadataOutput {
        private Long id;
        private String title;
        private String artist;
        private String album;
        private String genre;
        private String year;
        private String track;
        private Long durationMillis;
        private Long sizeBytes;
        private boolean isListenable;
    }
}
