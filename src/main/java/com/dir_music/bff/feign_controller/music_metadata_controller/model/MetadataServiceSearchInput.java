package com.dir_music.bff.feign_controller.music_metadata_controller.model;

import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetadataServiceSearchInput {
    private String searchQuery;
}
